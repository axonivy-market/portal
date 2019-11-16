package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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

/**
 * Used in DeprecatedTaskTemplate7.xhml for Portal version 7 
 */
@ManagedBean
@ViewScoped
@Deprecated
public class DeprecatedTaskTemplate7Bean implements Serializable{
  private static final long serialVersionUID = 1L;

  private List<IStartableSideStep> sideStepList;
  private IStartableSideStep selectedSideStep;
  private List<AdhocHistory> adhocHistories;

  public List<IStartableSideStep> getSideStepList() {
    return sideStepList;
  }

  public void setSelectedSideStep(IStartableSideStep selectedSideStep) {
    this.selectedSideStep = selectedSideStep;
  }

  public void startAdhoc() {
    PortalNavigator portalNavigator = new PortalNavigator();
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
    String url = processStartCollector.findExpressAdhocWFLink();
    url = url + "?originalTaskId=" + Ivy.wfTask().getId();
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

  public List<IStartableSideStep> generateSideStepList(String caseId) {
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
  
  public boolean hasAdhocTasks() {
    return AdditionalProperty.ORIGINAL_ADHOC_EXPRESS_TASK.toString().equals(Ivy.wfTask().customFields().stringField(AdditionalProperty.ORIGINAL_ADHOC_EXPRESS_TASK.toString()).getOrNull());
  }
  
  public boolean getIsFirstTimeOpenOriginalAdhocTask() {
    return AdditionalProperty.FIRST_TIME_OPEN_ORIGINAL_ADHOC_TASK.toString().equals(Ivy.wfTask().customFields().stringField(AdditionalProperty.FIRST_TIME_OPEN_ORIGINAL_ADHOC_TASK.toString()).getOrNull());
  }
  
  public void onCloseAdhocTaskHistoryDialog() {
    Ivy.wfTask().customFields().stringField(AdditionalProperty.FIRST_TIME_OPEN_ORIGINAL_ADHOC_TASK.toString()).delete();
  }
  
  public String getAdhocCreationMessage() {
    AdhocHistoryService adhocHistoryService = new AdhocHistoryService();
    boolean hasAdhocHistory = adhocHistoryService.hasAdhocHistory(Ivy.wfTask().getId());
    return hasAdhocHistory ? Ivy.cms().co("/ch.ivy.addon.portal.generic/OpenTaskTemplate/reCreateAdhocWarning") : Ivy.cms().co("/ch.ivy.addon.portal.generic/OpenTaskTemplate/goToAdhocWarning");
  }
  
  public List<AdhocHistory> getAllAdhocHistories() {
    if (adhocHistories == null) {
      AdhocHistoryService adhocHistoryService = new AdhocHistoryService();
      adhocHistories = adhocHistoryService.getHistoriesByTaskID(Ivy.wfTask().getId());
      adhocHistories.sort((first, second) -> second.getTimestamp().compareTo(first.getTimestamp()));
    }
    return adhocHistories;
  }

  public boolean checkSideStepsEnabled(String caseId) {
    return !generateSideStepList(caseId).isEmpty();
  }

  private void sortSideStepsByName(List<IStartableSideStep> sideSteps) {
    sideSteps.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
  }

  public List<IStage> getStagesBaseOnCurrentStage(String caseId, List<String> stepsProcess) {
    if(StringUtils.isBlank(caseId) || CollectionUtils.isNotEmpty(stepsProcess)) {
      return Collections.emptyList();
    }
    ICase wfCase = Ivy.wf().findCase(Long.parseLong(caseId));
    if (wfCase == null) {
      return Collections.emptyList();
    }
    ICaseMap caseMap = getCaseMapService(wfCase.getBusinessCase()).find().current();
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
