package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portal.generic.navigation.PortalPage;

@ManagedBean
@RequestScoped
public class NavigatorBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public void navigateToCaseDetail(String caseName, long caseId, long serverId) throws Exception {
    PortalNavigator portalNavigator = new PortalNavigator();
    Map<String, String> dialogParameters = new HashMap<String, String>();
    dialogParameters.put("caseName", caseName);
    dialogParameters.put("caseId", String.valueOf(caseId));
    dialogParameters.put("serverId", String.valueOf(serverId));
    String url = portalNavigator.getPortalStartUrlOf(PortalPage.CASE_DETAIL_FROM_TASK, dialogParameters);
    portalNavigator.redirect(url);
  }
}
