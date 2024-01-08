package it.unige.swplatforms;
import javax.jws.WebService;
import javax.jws.WebMethod;
@WebService
public class InverterString{
@WebMethod
	public String swapper(String stringa) {
		int len=stringa.length();
		String definitiva="";
		for(int i=0;i<len;i++) {
			definitiva=definitiva+stringa.charAt(i);
		}
		return definitiva;
	}
}