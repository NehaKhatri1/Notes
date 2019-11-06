package com.java8.poc;


public class InterfaceDemoOneForFunctionalObjects {
	
	public static void main(String[] args){
	
    GreetingService greetService1 = ( String message ) ->
    {
  	  return message;
    };// body of the declared method which is the functional object is directly here injected to the reference of the interface
    
    String str = greetService1.sayMessage("hello gaurav");
    System.out.println(str);

	}
}

interface GreetingService {
    String sayMessage(String message);
 }
