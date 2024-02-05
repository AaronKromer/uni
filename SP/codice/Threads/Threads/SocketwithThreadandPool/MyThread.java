import java.io.PrintStream;
import java.net.Socket;
import java.io.*;


public class MyThread extends Thread {
	Socket	localThreadSocket;
	int		threadCounter;
	
	MyThread(Socket s, int threadCounter){
		this.threadCounter = threadCounter;
		localThreadSocket=s;
	}
	public void run (){
		String threadName = Thread.currentThread().getName(); 
		try{
			PrintStream out = new PrintStream(localThreadSocket.getOutputStream());
			out.println(threadName + " " + threadCounter);
			out.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	
		for (int i=0; i < 10; i++) {
			try {
				Thread.currentThread().sleep(1000);
				System.out.println(threadName + " " + threadCounter + " " + i);
			} catch (IOError | InterruptedException  e) {
				System.out.println(e.toString());
			}
		}
	}
}
