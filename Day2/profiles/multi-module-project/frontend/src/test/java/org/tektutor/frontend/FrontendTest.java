package org.tektutor.frontend;

import org.junit.*;
import static org.junit.Assert.*;

public class FrontendTest {

	private String expectedResponse;
	private String actualResponse;
	private Frontend fe;

	@Before
	public void init() {
		fe = new Frontend();
	}

	@After
	public void cleanUP() {
		fe = null;
	}

	@Test
	public void testFrontend() {
		actualResponse = fe.getModuleName();
		expectedResponse = "Frontend";

		assertEquals ( expectedResponse, actualResponse );
	}

}
