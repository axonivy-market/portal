package ch.ivy.addon.portal.generic.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portal.generic.navigation.PortalPage;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.casemap.runtime.ICaseMapService;
import ch.ivyteam.ivy.casemap.runtime.model.IStartableSideStep;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

@ManagedBean
@ViewScoped
public class TaskTemplateBean {

  private String linkToTask;
  private List<IStartableSideStep> sideStepList;
  private IStartableSideStep selectedSideStep;

  public void generateLinkToTask(final long taskId) throws Exception {
    PortalNavigator navigator = new PortalNavigator();
    Map<String, String> params = new HashMap<>();
    params.put("taskId", Long.toString(taskId));
    linkToTask = navigator.getPortalStartAbsoluteUrlOf(PortalPage.LINK_TO_TASK, params);
  }

  public String getLinkToTask() {
    return linkToTask;
  }

  public List<IStartableSideStep> getSideStepList() {
    return sideStepList;
  }

  public void setSelectedSideStep(IStartableSideStep selectedSideStep) {
    this.selectedSideStep = selectedSideStep;
  }

  public void startAdhoc() throws Exception{
    PortalNavigator portalNavigator = new PortalNavigator();
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String  url = processStartCollector.findACMLink();
    url = url + "?originalTaskId=" + Ivy.wfTask().getId();
    portalNavigator.redirect(url);
  }

  public void startSideStep() throws Exception {
    PortalNavigator portalNavigator = new PortalNavigator();
    portalNavigator.redirect(selectedSideStep.getStartLink().getAbsoluteEncoded());
  }

  public boolean hasSelfService() throws Exception {
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String adhocUrl = processStartCollector.findACMLink();
    return !adhocUrl.isEmpty();
  }

  public List<IStartableSideStep> generateSideStepList(String caseId) throws Exception{
    if (sideStepList == null && !caseId.isEmpty()) {
      ICase internalCase = Ivy.wf().findCase(Long.parseLong(caseId));
      ICaseMapService caseMapService = ICaseMapService.get().getCaseMapService(internalCase.getBusinessCase(), Ivy.session().getSessionUser().getUserToken());
			sideStepList = caseMapService.findStartableSideSteps();
      sortSideStepsByName(sideStepList);
    }
    return sideStepList;
  }

  public boolean checkSideStepsEnabled(String caseId) throws Exception {
    return !generateSideStepList(caseId).isEmpty();
  }

  private void sortSideStepsByName(List<IStartableSideStep> sideSteps) {
    sideSteps.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
  }
}
