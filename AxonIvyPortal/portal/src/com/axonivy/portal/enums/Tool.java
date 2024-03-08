package com.axonivy.portal.enums;

public enum Tool {
FIND_USER("findUser", "findUser(String, String)");
	
	private String name;
	private String signature;
	
	Tool(String name, String signature) {
		this.setName(name);
		this.setSignature(signature);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getSignature() {
		return signature;
	}

	private void setSignature(String signature) {
		this.signature = signature;
	}
}
