package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.GrowlMessageService;
import ch.ivyteam.ivy.dialog.execution.api.DialogInstance;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.OpenRedirectVulnerabilityUtil;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean(name = "iFrameTaskTemplateBean")
@ViewScoped
public class IFrameTaskTemplateBean extends AbstractTaskTemplateBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final String TASK_ID_PARAM = "taskId";
  public static final String CASE_ID_PARAM = "caseId";
  private static final String URL_PARAM = "url";
  private static final String IS_SHOW_ALL_STEPS_PARAM = "isShowAllSteps";
  private static final String PROCESS_CHAIN_SHAPE_PARAM = "processChainShape";
  private static final String PROCESS_CHAIN_DIRECTION_PARAM = "processChainDirection";
  private static final String PROCESS_STEPS_PARAM = "processSteps";
  private static final String ANNOUNCEMENT_INVISIBLE_PARAM = "announcementInvisible";
  private static final String CURRENT_PROCESS_STEP_PARAM = "currentProcessStep";
  private static final String IS_HIDE_CASE_INFO = "isHideCaseInfo";
  private static final String IS_HIDE_TASK_NAME = "isHideTaskName";
  private static final String IS_HIDE_TASK_ACTION = "isHideTaskAction";
  private static final String IS_WORKING_ON_A_TASK = "isWorkingOnATask";
  private static final String VIEW_NAME = "viewName";
  private static final String TASK_NAME = "taskName";
  private static final String TASK_ICON = "taskIcon";
  public static final String PORTAL_GROWL_MESSGE_PARAM = "portalGrowlMessage";
  private static final String DEFAULT_TASK_ICON = "si si-task-list-edit";

  private int currentProcessStep;
  private List<String> processSteps;
  private List<String> stepIndexes;
  private boolean isShowAllSteps;
  private String processChainDirection;
  private String processChainShape;
  private boolean announcementInvisible = true;
  //In Iframe, when initial loading the page we hide both 
  //3 items: task action, task name and case info, to avoid blinking problem
  private boolean isHideTaskAction = true;
  private boolean isHideTaskName = true;
  private boolean isHideCaseInfo = true;
  private boolean isWorkingOnATask = true;
  private String taskName;
  private Map<String, Object> overridePortalGrowlMap = new HashMap<>();
  private String taskIcon;

  private Long caseId = null;

  public void useTaskInIFrame() {
    keepOverridePortalGrowl();
    Map<String, String> requestParamMap = getRequestParameterMap();
    String url = requestParamMap.get(URL_PARAM);
    if (StringUtils.isNotBlank(url)) {
      super.setTask(DialogInstance.of(url).task());
    }
  }

  public void navigateToEndPage() {
    keepOverridePortalGrowl();
    Map<String, String> requestParamMap = getRequestParameterMap();
    String taskId = requestParamMap.get(TASK_ID_PARAM);
    if (StringUtils.isNotBlank(taskId)) {
      PortalNavigator.navigateToPortalEndPage(Long.parseLong(taskId));
    }
  }

  private void keepOverridePortalGrowl() {
    if (task != null) {
      long taskId = task.getId();
      Boolean overridePortalGrowl = (Boolean) overridePortalGrowlMap.get(GrowlMessageService.OVERRIDE_PORTAL_GROWL + taskId);
      if (overridePortalGrowl != null && overridePortalGrowl) {
        String portalGlobalGrowlMessage = String.valueOf(overridePortalGrowlMap.get(IFrameTaskTemplateBean.PORTAL_GROWL_MESSGE_PARAM + taskId));
        FacesMessage message = FacesMessageUtils.sanitizedMessage(portalGlobalGrowlMessage, "");
        FacesContext.getCurrentInstance().addMessage(GrowlMessageService.PORTAL_GLOBAL_GROWL_MESSAGE, message);

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put(GrowlMessageService.OVERRIDE_PORTAL_GROWL, overridePortalGrowl);
        flash.setRedirect(true);
        flash.setKeepMessages(true);

        addFeedbackMessageForTask(taskId);

        overridePortalGrowlMap.remove(GrowlMessageService.OVERRIDE_PORTAL_GROWL + taskId);
        overridePortalGrowlMap.remove(IFrameTaskTemplateBean.PORTAL_GROWL_MESSGE_PARAM + taskId);
      }
    }
  }

  public void displayPortalGrowlMessage() {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String taskId = requestParamMap.get(IFrameTaskTemplateBean.TASK_ID_PARAM);
    Boolean overridePortalGrowl = Boolean.valueOf(requestParamMap.get(GrowlMessageService.OVERRIDE_PORTAL_GROWL));
    if (overridePortalGrowl) {
      String portalGlobalGrowlMessage = requestParamMap.get(IFrameTaskTemplateBean.PORTAL_GROWL_MESSGE_PARAM);
      overridePortalGrowlMap.put(IFrameTaskTemplateBean.PORTAL_GROWL_MESSGE_PARAM + taskId, portalGlobalGrowlMessage);
      overridePortalGrowlMap.put(GrowlMessageService.OVERRIDE_PORTAL_GROWL + taskId, overridePortalGrowl);
    }
  }

  private void addFeedbackMessageForTask(Long taskId) {
    ITask finishedTask = Ivy.wf().findTask(taskId);
    if (finishedTask != null) {
      boolean isTaskFinished = finishedTask.getEndTimestamp() != null;
      GrowlMessageService.getInstance().addFeedbackMessage(isTaskFinished, finishedTask.getCase());
    }
  }

  public void navigateToUrl() throws IOException {
    keepOverridePortalGrowl();
    Map<String, String> requestParamMap = getRequestParameterMap();
    String url = requestParamMap.get(URL_PARAM);
    HttpServletRequest request = null;
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    if (context != null){
      request = (HttpServletRequest) context.getRequest();
    }
    if (StringUtils.isNotBlank(url) && OpenRedirectVulnerabilityUtil.isValid(url, request)) {
      FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }
  }

  public void getDataFromIFrame() throws Exception {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String currentProcessStepText = requestParamMap.get(CURRENT_PROCESS_STEP_PARAM);
    processSteps = StringUtils.isNotBlank(requestParamMap.get(PROCESS_STEPS_PARAM))
            ? BusinessEntityConverter.convertJsonToListString(requestParamMap.get(PROCESS_STEPS_PARAM))
            : new ArrayList<>();
    stepIndexes = new ArrayList<>();
    for (int i= 0; i < processSteps.size(); i++) {
      stepIndexes.add(String.valueOf(i));
    }
    currentProcessStep = StringUtils.isBlank(currentProcessStepText) ? 0
        : (NumberUtils.isCreatable(currentProcessStepText) ? Integer.parseInt(currentProcessStepText)
            : Math.max(processSteps.indexOf(currentProcessStepText), 0));
    isShowAllSteps = Optional.ofNullable(requestParamMap.get(IS_SHOW_ALL_STEPS_PARAM)).map(BooleanUtils::toBoolean).orElse(false);
    processChainDirection = Optional.ofNullable(requestParamMap.get(PROCESS_CHAIN_DIRECTION_PARAM)).orElse(StringUtils.EMPTY);
    processChainShape = Optional.ofNullable(requestParamMap.get(PROCESS_CHAIN_SHAPE_PARAM)).orElse(StringUtils.EMPTY);
    announcementInvisible = Optional.ofNullable(requestParamMap.get(ANNOUNCEMENT_INVISIBLE_PARAM)).map(p -> StringUtils.isNotBlank(p) ? BooleanUtils.toBoolean(p) : true).get();
    isHideCaseInfo = Optional.ofNullable(requestParamMap.get(IS_HIDE_CASE_INFO)).map(BooleanUtils::toBoolean).orElse(false);
    isHideTaskName = Optional.ofNullable(requestParamMap.get(IS_HIDE_TASK_NAME)).map(BooleanUtils::toBoolean).orElse(false);
    isHideTaskAction = Optional.ofNullable(requestParamMap.get(IS_HIDE_TASK_ACTION)).map(BooleanUtils::toBoolean).orElse(false);
    isWorkingOnATask = Optional.ofNullable(requestParamMap.get(IS_WORKING_ON_A_TASK)).map(p -> StringUtils.isNotBlank(p) ? BooleanUtils.toBoolean(p) : true).get();
    caseId = Optional.ofNullable(requestParamMap.get(CASE_ID_PARAM)).map(p -> StringUtils.isNotBlank(p) ? Long.parseLong(p) : null).orElse(null);
    taskName = Optional.ofNullable(requestParamMap.get(TASK_NAME)).orElse(StringUtils.EMPTY);
    taskIcon = StringUtils.defaultIfBlank(requestParamMap.get(TASK_ICON), DEFAULT_TASK_ICON);
  }

  private Map<String, String> getRequestParameterMap() {
    Map<String, String> requestParamMap =
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    return requestParamMap;
  }

  public int getCurrentProcessStep() {
    return currentProcessStep;
  }

  public void setCurrentProcessStep(int currentProcessStep) {
    this.currentProcessStep = currentProcessStep;
  }

  public List<String> getProcessSteps() {
    return processSteps;
  }

  public boolean getIsShowAllSteps() {
    return isShowAllSteps;
  }

  public String getProcessChainDirection() {
    return processChainDirection;
  }

  public String getProcessChainShape() {
    return processChainShape;
  }
  
  public boolean getAnnouncementInvisible() {
    return announcementInvisible;
  }

  public boolean getIsHideTaskAction() {
    return isHideTaskAction;
  }

  public boolean getIsHideTaskName() {
    return isHideTaskName;
  }

  public boolean getIsHideCaseInfo() {
    return isHideCaseInfo;
  }

  public boolean getIsWorkingOnATask() {
    return isWorkingOnATask;
  }

  public String getViewName() {
    Map<String, String> requestParamMap = getRequestParameterMap();
    return StringUtils.defaultString(requestParamMap.get(VIEW_NAME));
  }

public Long getCaseId() {
	return caseId;
}

public void setCaseId(Long caseId) {
	this.caseId = caseId;
}

  public String getTaskName() {
    return taskName;
  }

  public List<String> getStepIndexes() {
    return stepIndexes;
  }

  public void setStepIndexes(List<String> stepIndexes) {
    this.stepIndexes = stepIndexes;
  }

  public String getTaskIcon() {
    return taskIcon;
  }

  public void setTaskIcon(String taskIcon) {
    this.taskIcon = taskIcon;
  }
}
