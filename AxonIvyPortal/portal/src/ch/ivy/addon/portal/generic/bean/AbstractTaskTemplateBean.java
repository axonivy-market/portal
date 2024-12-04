package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.casemap.runtime.ICaseMapService;
import ch.ivyteam.ivy.casemap.runtime.model.ICaseMap;
import ch.ivyteam.ivy.casemap.runtime.model.IStage;
import ch.ivyteam.ivy.casemap.runtime.model.IStartableSideStep;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.query.UserQuery;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

public abstract class AbstractTaskTemplateBean implements Serializable {
  private static final long serialVersionUID = 1L;

  protected List<IStartableSideStep> sideStepList;
  protected IStartableSideStep selectedSideStep;
  protected String caseDetailsLink;
  protected ITask task;
  protected ITask currentTask;

  public List<IStartableSideStep> getSideStepList() {
    return sideStepList;
  }

  public void setSelectedSideStep(IStartableSideStep selectedSideStep) {
    this.selectedSideStep = selectedSideStep;
  }

  public void startSideStep(ITask task) {
    TaskUtils.resetTask(task);
    String link = UrlUtils.formatLinkWithEmbedInFrameParam(selectedSideStep.getStartLink().getRelativeEncoded());
    PortalNavigator.redirect(link);
  }

  public List<IStartableSideStep> generateSideStepList(ICase iCase) {
    if (iCase == null) {
      return Collections.emptyList();
    }

    if (sideStepList == null || isSkippingTaskList()) {
      ICaseMapService caseMapService =
          ICaseMapService.current().getCaseMapService(iCase.getBusinessCase(),
              Ivy.session().getSessionUser().getUserToken());
      sideStepList = caseMapService.findStartableSideSteps();
      sortSideStepsByName(sideStepList);
    }

    currentTask = task != null ? task : null;
    return sideStepList;
  }

  private boolean isSkippingTaskList() {
    return task != null && currentTask != null && currentTask.getId() != task.getId();
  }

  public static String getUserByUsernameAndExternalName(String username, String externalId) {
    if (StringUtils.isBlank(username)) {
      return "";
    }

    return Sudo.get(() -> {
      IUser user = UserUtils.findUserByUsername(username);
      if (user == null) {
        UserQuery query = ISecurityContext.current().users().query();
        query.where().externalId().isEqual(externalId);
        user = query.executor().firstResult();
      }
      return user == null ? "" : UserUtils.getFullName(user);
    });
  }

  public static IUser getUserByUsernameAndExternalId(String username, String externalId) {
    if (StringUtils.isBlank(username)) {
      return null;
    }
    return Sudo.get(() -> {
      IUser user = UserUtils.findUserByUsername(username);
      if (user == null) {
        UserQuery query = ISecurityContext.current().users().query();
        query.where().externalId().isEqual(externalId);
        user = query.executor().firstResult();
      }
      return user;
    });
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

  public void generateCaseDetailInFrame(ICase currentCase) {
    setCaseDetailsLink(PortalNavigator.buildPortalCaseDetailInFrameUrl(currentCase != null ? currentCase.uuid() : null));
  }

  public Long getIntervalForPollingWhenOpenCaseDetails() {
    Long timeoutInMinute = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMaxInactiveInterval() / 60);
    // interval value should be one minute and 5 seconds before client side timeout to hide Extend session dialog
    Long intervalValue = (timeoutInMinute - 1)*60 - 5;
    return intervalValue < 0 ? null : intervalValue;
  }

  public String getCaseDetailsLink() {
    return caseDetailsLink;
  }

  public void setCaseDetailsLink(String caseDetailsLink) {
    this.caseDetailsLink = caseDetailsLink;
  }

  public ITask getTask() {
    return task;
  }

  public void setTask(ITask task) {
    this.task = task;
  }

  public void doNothing() {
  }
}
