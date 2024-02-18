import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@MyAnnotation(url = "/servlet/ad", value = "AlfabetoAnnotation")
public class AlfabetoAnnotation extends HttpServlet {
 
   private String message;
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
         String alphabet="abcdefghijklmnopqrstuvwxyz";
            message = "HTTP/1.1 200 OK\r\n"+
               "Content-Type: text/html\r\n"+
               "\r\n";
            PrintWriter out = response.getWriter();
            out.println(message);
         for (int i=0 ; i<26;i++){
            try{
               Thread.sleep(400);
            }
            catch(InterruptedException e){
               Thread.currentThread().interrupt();
            }
            message = "<h1>"+alphabet.charAt(i)+"</h1>"+"\n";
            out.println(message);
         }
      
   }
}