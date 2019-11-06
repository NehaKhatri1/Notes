package com.java8.poc;

public class Lambda {

	   public static void main(String args[]) {
	      Lambda tester = new Lambda();
			
	      //with type declaration
	      MathOperation addition = (int a, int b) -> a + b; // right hand side after addition which is reference to interface is the object value of the function 
	      // injection of function implementation , also called functional objects , instead of object of implementing class being injected , directly object of 
	      // function is injected which is functional object
			
	      //with out type declaration
	      MathOperation subtraction = (a, b) -> a - b;
			
	      //with return statement along with curly braces
	      MathOperation multiplication = (int a, int b) -> { return a * b; };
			
	      //without return statement and without curly braces
	      MathOperation division = (int a, int b) -> a / b;
			
	      System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
	      System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
	      System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
	      System.out.println("10 / 5 = " + tester.operate(10, 5, division));
			
	      //without parenthesis
	      GreetingService greetService1 = ( String message ) ->
	      {
//	    		  System.out.println("Hello " + message);
	    	  return message;
	      };
			
	      //with parenthesis
	     /* GreetingService greetService2 = (message) ->
	      System.out.println("Hello " + message);*/
			
	      String returnedString = greetService1.sayMessage("Gaurav");
	      System.out.println("returned string is : "+returnedString);
//	      greetService2.sayMessage("Suresh");
	   }
		
	   interface MathOperation {
	      int operation(int a, int b); // function declaration
	   }
		
	   interface GreetingService {
	      String sayMessage(String message);
	   }
		
	   private int operate(int a, int b, MathOperation mathOperation) {
	      return mathOperation.operation(a, b);
	   }
	}
