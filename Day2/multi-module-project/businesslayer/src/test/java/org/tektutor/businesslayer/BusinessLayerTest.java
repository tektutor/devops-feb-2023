package org.tektutor.businesslayer;

import org.junit.*;
import static org.junit.Assert.*;

public class BusinessLayerTest {

	private String expectedResponse;
	private String actualResponse;
	private BusinessLayer bl;

	@Before
	public void init() {
		bl = new BusinessLayer();
	}

	@After
	public void cleanUp() {
		bl = null;
	}

	@Test
	public void testBusinessLayer() {
		actualResponse = bl.getModuleName();
		expectedResponse = "BusinessLayer";

		assertEquals ( expectedResponse, actualResponse );
	}

}
