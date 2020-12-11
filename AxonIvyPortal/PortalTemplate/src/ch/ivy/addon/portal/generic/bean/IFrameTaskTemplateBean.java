package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivyteam.ivy.request.OpenRedirectVulnerabilityUtil;

@ManagedBean(name = "iFrameTaskTemplateBean")
@ViewScoped
public class IFrameTaskTemplateBean extends AbstractTaskTemplateBean implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private static final String TASK_ID_PARAM = "taskId";
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
  
  public void navigateToEndPage() {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String taskId = requestParamMap.get(TASK_ID_PARAM);
    
    if (StringUtils.isNotBlank(taskId)) {
      PortalNavigator.navigateToPortalEndPage(Long.parseLong(taskId));
    }
  }
  
  public void navigateToUrl() throws IOException {
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
    isWorkingOnATask = Optional.ofNullable(requestParamMap.get(IS_WORKING_ON_A_TASK)).map(p -> StringUtils.isNotBlank(p) ? BooleanUtils.toBoolean(p) : true).get();
  }
  
  public String getViewName() {
    Map<String, String> requestParamMap = getRequestParameterMap();
    return StringUtils.defaultString(requestParamMap.get(VIEW_NAME));
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
}
