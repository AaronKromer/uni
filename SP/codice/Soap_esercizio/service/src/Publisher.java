import javax.xml.ws.Endpoint;

import it.unige.swplatforms.Converter;

public class Publisher {
   public static void main(String[] args){
      Endpoint.publish("http://localhost:7654/Converter", new Converter());
   }
}