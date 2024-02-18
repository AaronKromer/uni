import java.util.Hashtable;
import java.util.Enumeration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServlet;

public class ServletHashTable {
	static Hashtable<String, HttpServlet> ht;
	ServletHashTable() {
		ht = new Hashtable<String, HttpServlet>();
	}
	static void put (String s, HttpServlet h){
		ht.put(s,  h);
	}
	static boolean contains (String s){
		return ht.containsKey(s);
	}
	static HttpServlet get(String s) {
		return ht.get(s);
	}
	static void remove(String s) {
		ht.remove(s);
	}
	static void listElements() {
		Enumeration<String> keys = ht.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			HttpServlet value = ht.get(key);
			System.out.println("Servlet: " + key + ", Class: " + value);
		}
	}
}
