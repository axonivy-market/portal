package ch.ivy.addon.portalkit.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.enums.Protocol;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class ExternalLinkBean implements Serializable {

private static final long serialVersionUID = 4772777911430826945L;
  private ExternalLink externalLink;
  private ExternalLinkService externaLinkService;
  
  @PostConstruct
  public void init() {
    externaLinkService = ExternalLinkService.getInstance();
  }
  
  public void addNewExternalLink(String clientId) {
    externalLink = new ExternalLink();
    externalLink.setIcon(ExternalLinkProcessItem.DEFAULT_ICON);
    PrimeFaces.current().resetInputs(clientId + ":add-external-link-form");
  }

  public ExternalLink saveNewExternalLink() {
    IUser sessionUser = Ivy.session().getSessionUser();
    externalLink.setCreatorId(sessionUser.getId());
    String processLink = correctLink(externalLink.getLink());
    externalLink.setLink(processLink);

    externaLinkService.save(externalLink);
    return externalLink;
  }

  public boolean hasPublicLinkCreationPermission() {
    return PermissionUtils.checkPublicLinkCreationPermission();
  }
  
  public String correctLink(String link) {
    String processLink = link.trim();
    if (!isValidProcessLink(processLink)) {
      processLink = Protocol.HTTP.getValue() + processLink;
    }
    return processLink;
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
