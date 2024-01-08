package it.unige.swplatforms;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ConverterInterface {
   @WebMethod 
   double c2f(double degrees);
}
