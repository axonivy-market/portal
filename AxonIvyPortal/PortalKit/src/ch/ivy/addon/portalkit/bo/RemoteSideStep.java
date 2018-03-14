package ch.ivy.addon.portalkit.bo;

import javax.servlet.http.HttpServletRequest;

import ch.ivyteam.ivy.model.value.WebLink;

public class RemoteSideStep {

	private String name;
	private WebLink webLink;
	private Boolean isAdhoc;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setStartLink(WebLink webLink) {
		this.webLink = webLink;
	}

	public WebLink getStartLink() {
		return webLink;
	}

	public WebLink getStartLink(@SuppressWarnings("unused") HttpServletRequest arg0) {
		return webLink;
	}

	public Boolean getIsAdhoc() {
		return isAdhoc;
	}

	public void setIsAdhoc(Boolean isAdhoc) {
		this.isAdhoc = isAdhoc;
	}

}
