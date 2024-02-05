import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public class CSocketWithThreads {

	public static void main(String[] args) {
		try 
		{
			Socket s = new Socket("localhost", 9000);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(in.readLine());
			s.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}
}
