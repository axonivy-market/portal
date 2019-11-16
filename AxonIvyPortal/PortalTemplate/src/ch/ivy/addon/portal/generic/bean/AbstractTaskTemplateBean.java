package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.PrimeFaces;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bo.AdhocHistory;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.service.AdhocHistoryService;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.casemap.runtime.ICaseMapService;
import ch.ivyteam.ivy.casemap.runtime.model.ICaseMap;
import ch.ivyteam.ivy.casemap.runtime.model.IStage;
import ch.ivyteam.ivy.casemap.runtime.model.IStartableSideStep;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

public abstract class AbstractTaskTemplateBean implements Serializable {
  private static final long serialVersionUID = 1L;

  protected List<IStartableSideStep> sideStepList;
  protected IStartableSideStep selectedSideStep;
  protected List<AdhocHistory> adhocHistories;

  public List<IStartableSideStep> getSideStepList() {
    return sideStepList;
  }

  public void setSelectedSideStep(IStartableSideStep selectedSideStep) {
    this.selectedSideStep = selectedSideStep;
  }

  public void startAdhoc(Long taskId) {
    PortalNavigator portalNavigator = new PortalNavigator();
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String url = processStartCollector.findExpressAdhocWFLink();
    url = url + "?originalTaskId=" + taskId;
    portalNavigator.redirect(url);
  }

  public void startSideStep() {
    PortalNavigator portalNavigator = new PortalNavigator();
    portalNavigator.redirect(selectedSideStep.getStartLink().getAbsoluteEncoded());
  }

  public boolean hasExpressAdhocWF() {
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String adhocUrl = processStartCollector.findExpressAdhocWFLink();
    return !adhocUrl.isEmpty();
  }

  public List<IStartableSideStep> generateSideStepList(ICase iCase) {
    if (iCase == null) {
      return Collections.emptyList();
    }

    if (sideStepList == null) {
      ICaseMapService caseMapService =
          ICaseMapService.get().getCaseMapService(iCase.getBusinessCase(),
              Ivy.session().getSessionUser().getUserToken());
      sideStepList = caseMapService.findStartableSideSteps();
      sortSideStepsByName(sideStepList);
    }
    return sideStepList;
  }
  
  public boolean hasAdhocTasks(ITask task) {
    if (task == null) {
      return false;
    }
    boolean isFirstTimeOpenOriginalAdhocTask = AdditionalProperty.FIRST_TIME_OPEN_ORIGINAL_ADHOC_TASK.toString()
        .equals(task.customFields().stringField(AdditionalProperty.FIRST_TIME_OPEN_ORIGINAL_ADHOC_TASK.toString()).getOrNull());
    if (isFirstTimeOpenOriginalAdhocTask) {
      PrimeFaces.current().executeScript("PF('adhoc-task-history-dialog').show()");
    }
    return AdditionalProperty.ORIGINAL_ADHOC_EXPRESS_TASK.toString().equals(task.customFields().stringField(AdditionalProperty.ORIGINAL_ADHOC_EXPRESS_TASK.toString()).getOrNull());
  }
  
  public void onCloseAdhocTaskHistoryDialog(ITask task) {
    task.customFields().stringField(AdditionalProperty.FIRST_TIME_OPEN_ORIGINAL_ADHOC_TASK.toString()).delete();
  }
  
  public String getAdhocCreationMessage(Long taskId) {
    AdhocHistoryService adhocHistoryService = new AdhocHistoryService();
    boolean hasAdhocHistory = adhocHistoryService.hasAdhocHistory(taskId);
    return hasAdhocHistory ? Ivy.cms().co("/ch.ivy.addon.portal.generic/OpenTaskTemplate/reCreateAdhocWarning") : Ivy.cms().co("/ch.ivy.addon.portal.generic/OpenTaskTemplate/goToAdhocWarning");
  }
  
  public List<AdhocHistory> getAllAdhocHistories(ITask task) {
    if (adhocHistories == null && task != null) {
      AdhocHistoryService adhocHistoryService = new AdhocHistoryService();
      adhocHistories = adhocHistoryService.getHistoriesByTaskID(task.getId());
      adhocHistories.sort((first, second) -> second.getTimestamp().compareTo(first.getTimestamp()));
    }
    return adhocHistories;
  }

  public boolean checkSideStepsEnabled(ICase iCase) {
    return CollectionUtils.isNotEmpty(generateSideStepList(iCase));
  }

  private void sortSideStepsByName(List<IStartableSideStep> sideSteps) {
    sideSteps.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
  }

  public List<IStage> getStagesBaseOnCurrentStage(ICase iCase, List<String> processSteps) {
    if(iCase == null || CollectionUtils.isNotEmpty(processSteps)) {
      return Collections.emptyList();
    }
    ICaseMap caseMap = getCaseMapService(iCase).find().current();
    if (caseMap == null) {
      return Collections.emptyList();
    }
    List<IStage> stages = caseMap.getStages();
    // if current stage is in the main flow, do not display secondary flow
    int firstTerminatingStageIndex = getFirstTerminatingStageIndex(stages);
    if (firstTerminatingStageIndex >= getIndexOfCurrentStage(iCase)) {
      stages = stages.subList(0, firstTerminatingStageIndex + 1);
    }
    return stages;
  }

  public int getIndexOfCurrentStage(ICase iCase) {
    if (iCase == null) {
      return -1;
    }
    return getStages(iCase).indexOf(getCaseMapService(iCase).findCurrentStage());
  }

  private List<IStage> getStages(ICase wfCase) {
    if (wfCase == null) {
      return Collections.emptyList();
    }
    ICaseMap caseMap = getCaseMapService(wfCase.getBusinessCase()).find().current();
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
