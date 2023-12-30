import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServletProcessor {
	public void process(Request request, Response response) {
	
		String repository = new String("file:" + MyHttpServer.WEB_ROOT + File.separator+"servletrepository"+ File.separatorChar).trim();
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		
		URLClassLoader loader = null;
		try {
			URL[] urls = new URL[1];
			urls[0] = new URL(repository);
			loader = new URLClassLoader(urls);	
		}
		catch (IOException e) {
			System.out.println(e.toString() );
		}

		Class myClass = null;
		try {
			myClass = loader.loadClass(servletName);
		}
		catch (ClassNotFoundException e) {
			// System.out.println("Class "+ repository+"/"+servletName + " not found");
			return;
		}
		HttpServlet servlet = null;
		try {
			servlet = (HttpServlet) myClass.newInstance();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		try {
			System.out.println("Request uri : "+ request.uri +"Request input : "+ request.input+" Response request:"+response.request+" Response output:"+response.output+" Response writer:"+response.writer);
			servlet.service((ServletRequest) request, (ServletResponse) response);
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		catch (Throwable e) {
			System.out.println(e.toString());
		}
	}
}
