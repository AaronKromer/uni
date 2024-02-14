import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Hashtable;

import javax.servlet.http.HttpServlet;

public class ManagementConsole extends Thread {
	ManagementConsole(){
	}

	String firstWord(String command) {
		if (command.contains(" ")){
			int index = command.indexOf(" ");
			return command.substring(0, index);
		} else return command;
		
	}

	String secondWord(String command) {
		if (command.contains(" ")) {
			int index = command.indexOf(" ");
			return command.substring(index+1, command.length());
			} else {
				return command;
			}
	}

	void executeUnload(String servletInternalName){
		if (!ServletHashTable.contains(servletInternalName)) {
			System.out.println("Servlet " + servletInternalName + " not in the servlet repository");
		} else {
			ServletHashTable.remove(servletInternalName);
			System.out.println("Servlet " + servletInternalName + " removed");
		}
	}
	void executeList(){
		ServletHashTable.listElements();
		System.out.println("dsa");
	}
	void executeLoad(String servletInternalName){
		if (ServletHashTable.contains(servletInternalName)) {
			System.out.println("Servlet " + servletInternalName + " already in the servlet repository");
		} else {
			String servletClassName = null;
			String servletRepository = new String(MyHttpServer.WEB_ROOT + File.separator + "servletrepository").trim();
			String servletDir = new String(servletRepository + File.separator+ servletInternalName).trim();
			File f = new File(servletDir);
			if (!(f.exists() && f.isDirectory())) {
				System.out.println("Directory " + servletDir + " does not exists");
				return; 
			} else {
				FileReader fr=null;
				try
				{ 
					String metadataFile = servletDir+File.separator+"metadata.txt";
					BufferedReader reader = new BufferedReader(new FileReader(metadataFile));
					String command = reader.readLine();
					while (command != null) {
						if (command.contains("=")){
							int index = command.indexOf("=");
							servletClassName = command.substring(index+1, command.length());
						}
						command = reader.readLine();
					}
				} 
				catch (FileNotFoundException fe) 
				{ 
					System.out.println("File not found"); 
				} catch (IOException e) {
					e.printStackTrace();
				} 		

				URLClassLoader loader = null;
				try {
					URL[] urls = new URL[1];
					urls[0] = new URL("file:" + servletDir + File.separator);
					loader = new URLClassLoader(urls);	
				}
				catch (IOException e) {
					System.out.println(e.toString() );
				}

				Class myClass = null;
				try {
					myClass = loader.loadClass(servletClassName);
				}
				catch (ClassNotFoundException e) {
					System.out.println(e.toString());
				}
				HttpServlet servlet = null;
				try {
					servlet = (HttpServlet) myClass.newInstance();
				}
				catch (Exception e) {
					System.out.println(e.toString());
				}
				ServletHashTable.put(servletInternalName, servlet);
				System.out.println("Servlet " + servletInternalName + " added");
			}
		}
	}

	void executeCommand(String command) {
		if (firstWord(command).equals("load")) {
			executeLoad(secondWord(command));
			return;
		}
		if (firstWord(command).equals("unload")) {
			executeUnload(secondWord(command));
			return;
		}
		if (firstWord(command).equals("list")) {
			executeList();
			return;
		}
		if (firstWord(command).equals("quit")) {
			return;
		}
		System.out.print("Command unknown: ");
		System.out.println("Commands supported: load <servlet> unload <servlet> list quit");
	}
	
	public void run() {
		String command= null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print ("Command: ");
		try {
			command  = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		executeCommand(command);
		while (!command.equals("quit")){
			System.out.print ("Command: ");
			try {
				command  = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			executeCommand(command);
		}
		Shutdown.flag=true;	
	}

}
