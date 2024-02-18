import java.util.Enumeration;
import java.util.Hashtable;

public class AnnotationHashTable {
	static Hashtable<String, String> ht;
	AnnotationHashTable() {
		ht = new Hashtable<String, String>();
	}
	static void put (String s, String h){
		ht.put(s,  h);
	}
	static boolean contains (String s){
		return ht.containsKey(s);
	}
	static String get(String s) {
		return ht.get(s);
	}
	static void remove(String s) {
		ht.remove(s);
	}
	static void listElements() {
		Enumeration<String> keys = ht.keys();
		System.out.println(keys);
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = ht.get(key);
			System.out.println("Urls: " + key + ", Alias: " + value);
		}
	}
}
