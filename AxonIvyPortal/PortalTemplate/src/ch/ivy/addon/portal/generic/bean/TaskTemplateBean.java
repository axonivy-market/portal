package ch.ivy.addon.portal.generic.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portal.generic.navigation.PortalPage;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class TaskTemplateBean {

  private String linkToTask;

  public void generateLinkToTask(final long taskId) throws Exception {
    PortalNavigator navigator = new PortalNavigator();
    Map<String, String> params = new HashMap<>();
    params.put("taskId", Long.toString(taskId));
    linkToTask = navigator.getPortalStartAbsoluteUrlOf(PortalPage.LINK_TO_TASK, params);
  }

  public String getLinkToTask() {
    return linkToTask;
  }
  
  public void startAdhoc() throws Exception{
    PortalNavigator portalNavigator = new PortalNavigator();
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String  url = processStartCollector.findACMLink();
    url = url + "?businessCaseId=" + Ivy.wfCase().getBusinessCase().getId() + "&originalTaskId=" + Ivy.wfTask().getId();
    portalNavigator.redirect(url);
  }
  
}
