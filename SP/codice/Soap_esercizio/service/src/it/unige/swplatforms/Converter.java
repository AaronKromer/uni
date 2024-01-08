package it.unige.swplatforms;

import javax.jws.WebService;

@WebService(endpointInterface = "it.unige.swplatforms.ConverterInterface")
public class Converter implements ConverterInterface {
   public double c2f(double degrees){
      return degrees * 9.0 / 5.0 + 32;
   }
}