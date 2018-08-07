package ch.ivy.gawfs;

import java.io.Serializable;

public class FormelementOption implements Serializable{

	private static final long serialVersionUID = 4574845829509559652L;
	private String value;
	
	
	public FormelementOption(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}


