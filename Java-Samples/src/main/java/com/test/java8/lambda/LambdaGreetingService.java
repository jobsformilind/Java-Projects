package com.test.java8.lambda;

public class LambdaGreetingService {
	final static String salutation = "Hello! ";

	public static void main(String args[]) {
		GreetingService greetService1 = message -> {System.out.println(salutation + message);};
		greetService1.sayMessage("Milind");
	}

	interface GreetingService {
		void sayMessage(String message);
	}
}
