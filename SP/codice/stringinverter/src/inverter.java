import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class inverter(){
    
	public String stringinverter(String stringa) {
		int len=stringa.length();
		String definitiva="";
		for(int i=0;i<len;i++) {
			definitiva=definitiva+stringa.charAt(i);
		}
		return definitiva;
	}
    
}