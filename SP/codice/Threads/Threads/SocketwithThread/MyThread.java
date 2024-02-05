import java.io.PrintStream;
import java.net.Socket;
import java.io.*;


public class MyThread extends Thread {
	private Socket localThreadSocket;
	static int n=0;
	MyThread(Socket s){
		localThreadSocket=s;
	}
	public void run (){
//		String s = Thread.currentThread().toString();
		String threadName = Thread.currentThread().getName(); 
		try{
			PrintStream out = new PrintStream(localThreadSocket.getOutputStream());
			out.println(threadName);
			out.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		n++;
	
		for (int i=0; i < 10; i++) {
			try {
				Thread.currentThread().sleep(1000);
				System.out.println(threadName + " " + i);
			} catch (IOError | InterruptedException  e) {
				System.out.println(e.toString());
			}
		}
	}
}
