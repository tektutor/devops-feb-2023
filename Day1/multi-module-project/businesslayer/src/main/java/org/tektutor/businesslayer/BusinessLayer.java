package org.tektutor.businesslayer;

import org.tektutor.backend.*;

public class BusinessLayer {

	private Backend dal = new Backend();

	public String getModuleName() {
		dal.getModuleName();
		return "BusinessLayer";
	}

}
