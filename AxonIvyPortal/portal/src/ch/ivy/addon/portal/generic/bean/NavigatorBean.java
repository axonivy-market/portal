package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

@ManagedBean
@RequestScoped
public class NavigatorBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public void navigateToCaseDetail(String uuid) {
    PortalNavigator.navigateToPortalCaseDetails(uuid);
  }

}
