/**
 * 
 */
/**
 * 
 */
public class InverterString{
	public String swapper(String stringa) {
		int len=stringa.length();
		String definitiva="";
		for(int i=0;i<len;i++) {
			definitiva=definitiva+stringa.charAt(i);
		}
		return definitiva;
	}
}