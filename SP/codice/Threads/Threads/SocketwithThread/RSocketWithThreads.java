import java.net.ServerSocket;
import java.net.Socket;


public class RSocketWithThreads {
	public static void main(String[] args) {
		int i = 0;
		try { 
			ServerSocket sSoc = new ServerSocket(9000);
			while (true){
				Socket inSoc = sSoc.accept();
				System.out.println("Generating Thread n. "+ i);
				MyThread T= new MyThread(inSoc);
				T.start();
				i++;
			}		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
