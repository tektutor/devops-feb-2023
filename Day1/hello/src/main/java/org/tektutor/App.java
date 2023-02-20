package org.tektutor;

public class App {

	public String sayHello() {
		return "Hello Java - Maven Project !";
	}

	public double addNumbers( double x, double y ) {
		return x + y;
	}

	public static void main ( String[] args ) {

		App hello = new App();
		System.out.println ( hello.sayHello() );

	}

}
