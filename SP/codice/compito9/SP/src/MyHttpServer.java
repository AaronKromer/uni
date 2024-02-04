import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHttpServer {
	
	public static final String WEB_ROOT = "C:/Temp/WebContent";
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	private boolean shutdown = false;
	public static void  main(String[] args) {
		MyHttpServer server = new MyHttpServer();
		System.out.println("Web Root = " + WEB_ROOT);
		ManagementConsole managementConsole = new ManagementConsole();
		ServletHashTable servletHashTable = new ServletHashTable();
		managementConsole.start();
		server.await();
	}
	public void await() {
		ServerSocket serverSocket = null;
		ThreadPoolServer threadPool = new ThreadPoolServer(2); 
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				Request request = new Request(input);
				request.parse();
				Response response = new Response(output);
				response.setRequest(request);
				threadPool.go(request,response,10);
			}
			catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}
