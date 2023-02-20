package org.tektutor.backend;

import org.junit.*;
import static org.junit.Assert.*;

public class BackendTest {

	private String expectedResponse;
	private String actualResponse;
	private Backend dal;

	@Before
	public void init() {
		dal = new Backend();
	}

	@After
	public void cleanUp() {
		dal = null;
	}

	@Test
	public void testBackend() {
		actualResponse = dal.getModuleName();
		expectedResponse = "Backend";

		assertEquals ( expectedResponse, actualResponse );
	}

}
