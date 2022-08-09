package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.enums.MenuKind;

@ManagedBean
@RequestScoped
public class NavigatorBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public void navigateToCaseDetail(long caseId) {
    PortalNavigator.navigateToPortalCaseDetails(caseId);
  }
  
  public String getProcessPage() {
    return MenuKind.PROCESS.toString();
  }
  
  public String getTaskPage() {
    return MenuKind.TASK.toString();
  }
  
  public String getCasePage() {
    return MenuKind.CASE.toString();
  }
  
  public String getStatisticPage() {
    return MenuKind.STATISTICS.toString();
  }
}
