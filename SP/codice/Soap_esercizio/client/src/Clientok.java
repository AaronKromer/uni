import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import it.unige.swplatforms.ConverterInterface;

public class Client{
   public static void main(String[] args) throws Exception {
	URL wsdlUrl = new URL("http://localhost:7654/Converter");
      	QName q0 = new QName("http://swplatforms.unige.it/", "ConverterService");

//	Create the service from its wsdl description and from its name
	Service service = Service.create(wsdlUrl, q0);	

      	QName q1 = new QName("http://swplatforms.unige.it/", "ConverterPort");

//	Create the stub  from its wsdl description and from its name
	ConverterInterface stub = service.getPort(q1, ConverterInterface.class);

	System.out.printf("DC to DF: 37 DC = %f DF%n", stub.c2f(37.0));      

   }
}
