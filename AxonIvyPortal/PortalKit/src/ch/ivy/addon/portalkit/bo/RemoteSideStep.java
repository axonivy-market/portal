package ch.ivy.addon.portalkit.bo;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import ch.ivyteam.ivy.casemap.runtime.ISideStepProcess;

public class RemoteSideStep implements ISideStepProcess {

  private String name;
  private String startLink;
  private URI startRequestUri;


  @Override
  public String getName() {
    return name;
  }

  @Override
  public URI getStartRequestUri() {
    return startRequestUri;
  }

  @Override
  public URI getStartRequestUri(HttpServletRequest paramHttpServletRequest) {
    return null;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStartRequestUri(URI startRequestUri) {
    this.startRequestUri = startRequestUri;
  }

  public String getStartLink() {
    return startLink;
  }

  public void setStartLink(String startLink) {
    this.startLink = startLink;
  }


}
