import it.unige.swplatforms.*;

public class Client {
	public static void main(String...arg){
	InverterString service = new InverterString();
	InverterStringPortType port = service.getAdderServiceHttpSoap11Endpoint();
	int s = port.swapper("abcdefg");
	System.out.println("Result = " + s);
	}
}
