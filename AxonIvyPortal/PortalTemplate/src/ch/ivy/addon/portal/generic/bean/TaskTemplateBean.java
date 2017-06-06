package ch.ivy.addon.portal.generic.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portal.generic.navigation.PortalPage;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.SideStepUtils;
import ch.ivyteam.ivy.casemap.runtime.ISideStepProcess;
import ch.ivyteam.ivy.casemap.runtime.SideStepService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean
@ViewScoped
public class TaskTemplateBean {

  private String linkToTask;
  private List<ISideStepProcess> sideStepList;
  private ISideStepProcess selectedSideStep;

  public void generateLinkToTask(final long taskId) throws Exception {
    PortalNavigator navigator = new PortalNavigator();
    Map<String, String> params = new HashMap<>();
    params.put("taskId", Long.toString(taskId));
    linkToTask = navigator.getPortalStartAbsoluteUrlOf(PortalPage.LINK_TO_TASK, params);
  }

  public String getLinkToTask() {
    return linkToTask;
  }

  public List<ISideStepProcess> getSideStepList() {
    return sideStepList;
  }

  public void setSelectedSideStep(ISideStepProcess selectedSideStep) {
    this.selectedSideStep = selectedSideStep;
  }

  public void startAdhoc() throws Exception{
    PortalNavigator portalNavigator = new PortalNavigator();
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String  url = processStartCollector.findACMLink();
    url = url + "?businessCaseId=" + Ivy.wfCase().getBusinessCase().getId() + "&originalTaskId=" + Ivy.wfTask().getId();
    portalNavigator.redirect(url);
  }

  public void startSideStep() throws Exception {
    PortalNavigator portalNavigator = new PortalNavigator();
    portalNavigator.redirect(selectedSideStep.getStartRequestUri().toString());
  }

  public boolean hasSelfService() throws Exception {
    return SideStepUtils.hasSelfService();
  }

  public List<ISideStepProcess> generateSideStepList(String caseId) throws Exception{
    if (sideStepList == null && !caseId.isEmpty()) {
      ICase internalCase = Ivy.wf().findCase(Long.parseLong(caseId));
      sideStepList = SideStepService.get().findStartable(internalCase.getBusinessCase());
    }
    return sideStepList;
  }

  public boolean checkSideStepsEnabled(String caseId) throws Exception {
    return !generateSideStepList(caseId).isEmpty();
  }
}
