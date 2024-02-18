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
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		if (!ServletHashTable.contains(servletName)) {
			if (AnnotationHashTable.contains(uri)){
				servletName = AnnotationHashTable.get(uri);
				HttpServlet servlet = ServletHashTable.get(servletName);
				try {
					servlet.service((ServletRequest) request, (ServletResponse) response);
				}
				catch (Exception e) {
					System.out.println(e.toString());
				}
				catch (Throwable e) {
					System.out.println(e.toString());
				}
			}
		} else {
			HttpServlet servlet = ServletHashTable.get(servletName);
			try {
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
}
