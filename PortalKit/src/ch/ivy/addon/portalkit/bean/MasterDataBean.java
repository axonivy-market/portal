package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portalkit.enums.ApplicationType;
import ch.ivy.addon.portalkit.enums.WSAuthenticationType;
import ch.ivy.addon.portalkit.masterdata.AwesomeIcon;

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
}
