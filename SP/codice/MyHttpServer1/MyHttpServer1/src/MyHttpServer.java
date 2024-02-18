import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Hashtable;

import javax.servlet.http.HttpServlet;

public class MyHttpServer {
	public static final String WEB_ROOT = "C:\\Users\\akrom\\OneDrive\\Desktop\\uni\\SP\\codice\\compito9\\SP";

	public static void  main(String[] args) {
		ServletHashTable servletHashTable = new ServletHashTable();

		ManagementConsole managementConsole = new ManagementConsole();
		managementConsole.start();

		MyHttpServer myHttpServer = new MyHttpServer();
		myHttpServer.await();
		System.out.println("Exiting...");
	}

	MyHttpServer() {
	}

	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (!Shutdown.flag) {
			// Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				try { 
					serverSocket.setSoTimeout(2000);
					Socket socket = serverSocket.accept();
					socket.setSoTimeout(2000);
					input = socket.getInputStream();
					output = socket.getOutputStream();
					Request request = new Request(input);
					request.parse();
					Response response = new Response(output);
					response.setRequest(request);
					if (request.getUri() != null) {
						if (request.getUri().startsWith("/servlet")) {
							MyServletProcessor processor = new MyServletProcessor();
							processor.process(request, response);
						}
						else {
							MyStaticResourceProcessor processor = new MyStaticResourceProcessor();
							processor.process(request, response);
						}				
					}
					socket.close();
				} catch (SocketTimeoutException se) {
					if (Shutdown.flag)	return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}
