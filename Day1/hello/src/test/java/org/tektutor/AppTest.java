package org.tektutor;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

	@Test
	public void testSayHello() {
		App hello = new App();

		String actualResponse   = hello.sayHello();
		String expectedResponse = "Hello Java - Maven Project !";

		assertEquals ( expectedResponse, actualResponse );
	}

	@Test
	public void testAddition() {
		App hello = new App();

		double actualResponse = hello.addNumbers( 10.5, 11.5 );
		//double expectedResponse = 22.0;
		double expectedResponse = 122.0;

		assertEquals ( expectedResponse, actualResponse, 0.001 );
	}

}
