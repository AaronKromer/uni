import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.*;

public class RSocketWithThreads {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println ("command <pool size>");
			System.exit(0);
		}
		int poolSize = Integer.parseInt(args[0]);
		ExecutorService pool = Executors.newFixedThreadPool(poolSize);
		try { 
			ServerSocket sSoc = new ServerSocket(9000);
			int threadCounter = 1;
			while (true){
				Socket inSoc = sSoc.accept();
				System.out.println("Generating Thread n. "+ threadCounter);
				MyThread T= new MyThread(inSoc, threadCounter);
				pool.execute(T);
				threadCounter++;
			}		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
