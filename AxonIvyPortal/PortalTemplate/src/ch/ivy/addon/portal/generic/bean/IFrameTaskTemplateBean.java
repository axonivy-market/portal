package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
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

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivyteam.ivy.request.OpenRedirectVulnerabilityUtil;

@ManagedBean(name = "iFrameTaskTemplateBean")
@ViewScoped
public class IFrameTaskTemplateBean extends AbstractTaskTemplateBean implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private static final String TASK_ID_PARAM = "taskId";
  private static final String URL_PARAM = "url";
  private static final String PROCESS_CHAIN_SHAPE_PARAM = "processChainShape";
  private static final String PROCESS_CHAIN_DIRECTION_PARAM = "processChainDirection";
  private static final String PROCESS_STEPS_PARAM = "processSteps";
  private static final String CURRENT_PROCESS_STEP_PARAM = "currentProcessStep";
  
  private int currentProcessStep;
  private List<String> processSteps;
  private String processChainDirection;
  private String processChainShape;
  private PortalNavigator navigator = new PortalNavigator();

  public void navigateToHomePage() throws MalformedURLException {
    navigator.navigateToPortalHome();
  }
  
  public void navigateToEndPage() throws MalformedURLException {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String taskId = requestParamMap.get(TASK_ID_PARAM);
    
    if (StringUtils.isNotBlank(taskId)) {
      navigator.navigateToPortalEndPage(Long.parseLong(taskId));
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
    processSteps = StringUtils.isNotBlank(requestParamMap.get(PROCESS_STEPS_PARAM)) ? Arrays.asList(requestParamMap.get(PROCESS_STEPS_PARAM).split(",")) : new ArrayList<>();
    processChainDirection = Optional.ofNullable(requestParamMap.get(PROCESS_CHAIN_DIRECTION_PARAM)).orElse(StringUtils.EMPTY);
    processChainShape = Optional.ofNullable(requestParamMap.get(PROCESS_CHAIN_SHAPE_PARAM)).map(Object::toString).orElse(StringUtils.EMPTY);
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

  public void setProcessSteps(List<String> processSteps) {
    this.processSteps = processSteps;
  }

  public String getProcessChainDirection() {
    return processChainDirection;
  }

  public void setProcessChainDirection(String processChainDirection) {
    this.processChainDirection = processChainDirection;
  }

  public String getProcessChainShape() {
    return processChainShape;
  }

  public void setProcessChainShape(String processChainShape) {
    this.processChainShape = processChainShape;
  }
}
