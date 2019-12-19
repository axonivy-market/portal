package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.casemap.runtime.ICaseMapService;
import ch.ivyteam.ivy.casemap.runtime.model.ICaseMap;
import ch.ivyteam.ivy.casemap.runtime.model.IStage;
import ch.ivyteam.ivy.casemap.runtime.model.IStartableSideStep;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class TaskTemplateBean implements Serializable{
  private static final long serialVersionUID = 1L;

  private List<IStartableSideStep> sideStepList;
  private IStartableSideStep selectedSideStep;

  public List<IStartableSideStep> getSideStepList() {
    return sideStepList;
  }

  public void setSelectedSideStep(IStartableSideStep selectedSideStep) {
    this.selectedSideStep = selectedSideStep;
  }

  public void startAdhoc(ITask task) throws Exception {
    PortalNavigator portalNavigator = new PortalNavigator();
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String url = processStartCollector.findACMLink();
    url = url + "?originalTaskId=" + task.getId();
    portalNavigator.redirect(url);
  }

  public void startSideStep(ITask task) throws Exception {
    TaskUtils.resetTask(task);
    PortalNavigator portalNavigator = new PortalNavigator();
    portalNavigator.redirect(selectedSideStep.getStartLink().getAbsoluteEncoded());
  }

  public boolean hasSelfService() throws Exception {
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String adhocUrl = processStartCollector.findACMLink();
    return !adhocUrl.isEmpty();
  }

  public List<IStartableSideStep> generateSideStepList(String caseId) throws Exception {
    if (StringUtils.isBlank(caseId)) {
      return Collections.emptyList();
    }

    if (sideStepList == null) {
      ICase internalCase = Ivy.wf().findCase(Long.parseLong(caseId));
      ICaseMapService caseMapService =
          ICaseMapService.get().getCaseMapService(internalCase.getBusinessCase(),
              Ivy.session().getSessionUser().getUserToken());
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

  public List<IStage> getStagesBaseOnCurrentStage(String caseId) {
    if(StringUtils.isBlank(caseId)) {
      return Collections.emptyList();
    }
    ICase wfCase = Ivy.wf().findCase(Long.parseLong(caseId));
    if (wfCase == null) {
      return Collections.emptyList();
    }
    ICaseMap caseMap = getCaseMapService(wfCase.getBusinessCase()).findCaseMap();
    if (caseMap == null) {
      return Collections.emptyList();
    }
    List<IStage> stages = caseMap.getStages();
    // if current stage is in the main flow, do not display secondary flow
    int firstTerminatingStageIndex = getFirstTerminatingStageIndex(stages);
    if (firstTerminatingStageIndex >= getIndexOfCurrentStage(caseId)) {
      stages = stages.subList(0, firstTerminatingStageIndex + 1);
    }
    return stages;
  }

  public int getIndexOfCurrentStage(String caseId) {
    if(StringUtils.isBlank(caseId)) {
      return -1;
    }
    ICase wfCase = Ivy.wf().findCase(Long.parseLong(caseId));
    if (wfCase == null) {
      return -1;
    }
    return getStages(wfCase).indexOf(getCaseMapService(wfCase).findCurrentStage());
  }

  private List<IStage> getStages(ICase wfCase) {
    if (wfCase == null) {
      return Collections.emptyList();
    }
    ICaseMap caseMap = getCaseMapService(wfCase.getBusinessCase()).findCaseMap();
    if (caseMap == null) {
      return Collections.emptyList();
    }
    return caseMap.getStages();
  }

  private ICaseMapService getCaseMapService(ICase wfCase) {
    return Ivy.get(ICaseMapService.class).getCaseMapService(wfCase.getBusinessCase());
  }

  private int getFirstTerminatingStageIndex(List<IStage> stages) {
    int counter = 0;
    for (IStage stage : stages) {
      if (stage.isTerminating()) {
        return counter;
      }
      counter++;
    }
    return -1;
  }
}
