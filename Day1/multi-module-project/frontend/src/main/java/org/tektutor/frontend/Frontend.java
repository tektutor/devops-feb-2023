package org.tektutor.frontend;

import org.tektutor.businesslayer.*;

public class Frontend {

	private BusinessLayer bl = new BusinessLayer();

	public String getModuleName() {
		bl.getModuleName();	

		return "Frontend";
	}

}
