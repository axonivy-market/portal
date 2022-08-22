package ch.ivy.addon.portalkit.enums;

public enum Protocol {

	HTTP("http://"), HTTPS("https://"), FTP("ftp://");

	private String value;
	
	private Protocol(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
