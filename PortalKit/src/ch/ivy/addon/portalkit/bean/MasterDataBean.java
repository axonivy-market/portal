package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portalkit.enums.ApplicationType;
import ch.ivy.addon.portalkit.enums.WSAuthenticationType;
import ch.ivy.addon.portalkit.masterdata.AwesomeIcon;
import ch.ivy.ws.addon.SortType;

@ManagedBean
@SessionScoped
public class MasterDataBean implements Serializable {

  private static final long serialVersionUID = 1L;
  
  public AwesomeIcon[] getAwesomeIcons() {
    return AwesomeIcon.values();
  }

  public ApplicationType[] getApplicationTypes() {
    return ApplicationType.values();
  }

  public WSAuthenticationType[] getWsAuthenticationType() {
    return WSAuthenticationType.values();
  }
  
  public SortType getSortTypePriority() {
    return SortType.PRIORITY;
  }
  
  public SortType getSortTypeName() {
    return SortType.NAME;
  }
  
  public SortType getSortTypeActivator() {
    return SortType.ACTIVATOR;
  }
  
  public SortType getSortTypeId() {
    return SortType.ID;
  }
  
  public SortType getSortTypeCreationTime() {
    return SortType.CREATION_TIME;
  }
  
  public SortType getSortTypeExpiryTime() {
    return SortType.EXPIRY_TIME;
  }
  
  public SortType getSortTypeState() {
    return SortType.STATE;
  }
}
