package org.tektutor.main;

import org.junit.*;
import static org.junit.Assert.*;

public class MainTest {

	private String expectedResponse;
	private String actualResponse;
	private Main mainObj;

	@Before
	public void init() {
		mainObj = new Main();
	}

	@After
	public void cleanUp() {
		mainObj = null;
	}

	@Test
	public void testMain() {
		actualResponse = mainObj.getModuleName();
		expectedResponse = "Main";

		assertEquals ( expectedResponse, actualResponse );
	}

}
