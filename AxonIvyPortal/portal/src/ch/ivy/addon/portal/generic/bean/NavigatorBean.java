package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

@Named
@RequestScoped
public class NavigatorBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public void navigateToCaseDetail(String uuid) {
    PortalNavigator.navigateToPortalCaseDetails(uuid);
  }

}
