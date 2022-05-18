package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.publicapi.PortalNavigatorAPI;
import ch.ivy.addon.portalkit.util.GrowlMessageUtils;
import ch.ivyteam.ivy.dialog.execution.api.DialogInstance;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.OpenRedirectVulnerabilityUtil;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

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
  public static final String PORTAL_GROWL_MESSGE_PARAM = "portalGrowlMessage";
  
  private int currentProcessStep;
  private List<String> processSteps;
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
  private Map<String, Object> overridePortalGrowlMap = new HashMap<>();

  private Integer caseId = null;
  private ICase caseToDisplay;
  
  private PortalNavigator navigator = new PortalNavigator();

  public void navigateToHomePage() {
    keepOverridePortalGrowl();
    PortalNavigatorAPI.navigateToPortalHome();
  }

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
      navigator.navigateToPortalEndPage(Long.parseLong(taskId));
    }
  }

  private void keepOverridePortalGrowl() {
    if (task != null) {
      long taskId = task.getId();
      Boolean overridePortalGrowl = (Boolean) overridePortalGrowlMap.get(GrowlMessageUtils.OVERRIDE_PORTAL_GROWL + taskId);
      if (overridePortalGrowl != null && overridePortalGrowl) {
        String portalGlobalGrowlMessage = String.valueOf(overridePortalGrowlMap.get(IFrameTaskTemplateBean.PORTAL_GROWL_MESSGE_PARAM + taskId));
        FacesMessage message = new FacesMessage(portalGlobalGrowlMessage, "");
        FacesContext.getCurrentInstance().addMessage(GrowlMessageUtils.PORTAL_GLOBAL_GROWL_MESSAGE, message);

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put(GrowlMessageUtils.OVERRIDE_PORTAL_GROWL, overridePortalGrowl);
        flash.setRedirect(true);
        flash.setKeepMessages(true);

        addFeedbackMessageForTask(taskId);

        overridePortalGrowlMap.remove(GrowlMessageUtils.OVERRIDE_PORTAL_GROWL + taskId);
        overridePortalGrowlMap.remove(IFrameTaskTemplateBean.PORTAL_GROWL_MESSGE_PARAM + taskId);
      }
    }
  }

  public void displayPortalGrowlMessage() {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String taskId = requestParamMap.get(IFrameTaskTemplateBean.TASK_ID_PARAM);
    Boolean overridePortalGrowl = Boolean.valueOf(requestParamMap.get(GrowlMessageUtils.OVERRIDE_PORTAL_GROWL));
    if (overridePortalGrowl) {
      String portalGlobalGrowlMessage = requestParamMap.get(IFrameTaskTemplateBean.PORTAL_GROWL_MESSGE_PARAM);
      overridePortalGrowlMap.put(IFrameTaskTemplateBean.PORTAL_GROWL_MESSGE_PARAM + taskId, portalGlobalGrowlMessage);
      overridePortalGrowlMap.put(GrowlMessageUtils.OVERRIDE_PORTAL_GROWL + taskId, overridePortalGrowl);
    }
  }

  private void addFeedbackMessageForTask(Long taskId) {
    ITask finishedTask = Ivy.wf().findTask(taskId);
    if (finishedTask != null) {
      boolean isTaskFinished = finishedTask.getEndTimestamp() != null;
      GrowlMessageUtils.addFeedbackMessage(isTaskFinished, finishedTask.getCase());
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
    currentProcessStep = Optional.ofNullable(requestParamMap.get(CURRENT_PROCESS_STEP_PARAM))
        .filter(str -> str.matches("-?\\d+"))
        .map(Integer::parseInt)
        .orElse(0);
    processSteps = StringUtils.isNotBlank(requestParamMap.get(PROCESS_STEPS_PARAM)) ? Arrays.asList(requestParamMap.get(PROCESS_STEPS_PARAM).split("\\s*,\\s*")) : new ArrayList<>();
    isShowAllSteps = Optional.ofNullable(requestParamMap.get(IS_SHOW_ALL_STEPS_PARAM)).map(BooleanUtils::toBoolean).orElse(false);
    processChainDirection = Optional.ofNullable(requestParamMap.get(PROCESS_CHAIN_DIRECTION_PARAM)).orElse(StringUtils.EMPTY);
    processChainShape = Optional.ofNullable(requestParamMap.get(PROCESS_CHAIN_SHAPE_PARAM)).orElse(StringUtils.EMPTY);
    announcementInvisible = Optional.ofNullable(requestParamMap.get(ANNOUNCEMENT_INVISIBLE_PARAM)).map(p -> StringUtils.isNotBlank(p) ? BooleanUtils.toBoolean(p) : true).get();
    isHideCaseInfo = Optional.ofNullable(requestParamMap.get(IS_HIDE_CASE_INFO)).map(BooleanUtils::toBoolean).orElse(false);
    isHideTaskName = Optional.ofNullable(requestParamMap.get(IS_HIDE_TASK_NAME)).map(BooleanUtils::toBoolean).orElse(false);
    isHideTaskAction = Optional.ofNullable(requestParamMap.get(IS_HIDE_TASK_ACTION)).map(BooleanUtils::toBoolean).orElse(false);
    caseId = Optional.ofNullable(requestParamMap.get(CASE_ID_PARAM)).map(p -> StringUtils.isNotBlank(p) ? Integer.parseInt(p) : null).orElse(null);
    isWorkingOnATask = Optional.ofNullable(requestParamMap.get(IS_WORKING_ON_A_TASK)).map(p -> StringUtils.isNotBlank(p) ? BooleanUtils.toBoolean(p) : true).get();

    if(caseId != null) {
      CaseQuery query = CaseQuery.create();
      caseToDisplay = query.where().caseId().isEqual(caseId).executor().firstResult();
    }
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

  public ICase getCaseToDisplay() {
    return caseToDisplay;
  }

  public void setCaseToDisplay(ICase caseToDisplay) {
    this.caseToDisplay = caseToDisplay;
  }
}
