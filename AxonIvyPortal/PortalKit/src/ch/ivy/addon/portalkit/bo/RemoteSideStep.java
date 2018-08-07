package ch.ivy.addon.portalkit.bo;

import javax.servlet.http.HttpServletRequest;

import ch.ivyteam.ivy.casemap.runtime.model.IStartableSideStep;
import ch.ivyteam.ivy.model.value.WebLink;

public class RemoteSideStep implements IStartableSideStep {

	private String name;
	private WebLink webLink;
	private Boolean isAdhoc;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setStartLink(WebLink webLink) {
		this.webLink = webLink;
	}

	@Override
	public WebLink getStartLink() {
		return webLink;
	}

	@Override
	public WebLink getStartLink(HttpServletRequest arg0) {
		return webLink;
	}

	public Boolean getIsAdhoc() {
		return isAdhoc;
	}

	public void setIsAdhoc(Boolean isAdhoc) {
		this.isAdhoc = isAdhoc;
	}

}
