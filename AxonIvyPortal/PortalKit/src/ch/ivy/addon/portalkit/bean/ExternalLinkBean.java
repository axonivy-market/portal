package ch.ivy.addon.portalkit.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import ch.ivy.addon.portalkit.bo.ExternalLink;
import ch.ivy.addon.portalkit.enums.Protocol;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ExternalLinkBean {
  private ExternalLink externalLink;
  private ExternalLinkService externaLinkService;
  
  @PostConstruct
  public void init() {
    externaLinkService = new ExternalLinkService();
  }
  
  public void addNewExternalLink(String clientId) {
    externalLink =  new ExternalLink();
    PrimeFaces.current().resetInputs(clientId + ":add-external-link-form");
  }
  
  public ExternalLink saveNewExternalLink() {
    externalLink.setUsername(Ivy.session().getSessionUser().getName());
    correctLink();
    externaLinkService.save(externalLink);
    return externalLink;
  }
  
  public boolean hasPublicLinkCreationPermission() {
    return PermissionUtils.checkPublicLinkCreationPermission();
  }
  
  private void correctLink() {
    String processLink = externalLink.getLink().trim();
    if (!isValidProcessLink(processLink)) {
      processLink = Protocol.HTTP.getValue() + processLink;
      externalLink.setLink(processLink);
    }
  }

  private boolean isValidProcessLink(String processLink) {
    String linkInLowerCase = processLink.toLowerCase();
    return linkInLowerCase.startsWith(Protocol.HTTP.getValue())
        || linkInLowerCase.startsWith(Protocol.HTTPS.getValue()) || linkInLowerCase.startsWith("/");
  }

  
  public void startExternalLink(ExternalLink selectedExternalLink) throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().redirect(selectedExternalLink.getLink());
  }

  public ExternalLink getExternalLink() {
    return externalLink;
  }

  public void setExternalLink(ExternalLink externalLink) {
    this.externalLink = externalLink;
  }
}
