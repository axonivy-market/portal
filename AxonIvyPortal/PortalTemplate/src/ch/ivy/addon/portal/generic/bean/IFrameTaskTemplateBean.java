package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class IFrameTaskTemplateBean extends AbstractTaskTemplateBean implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private static final String TASK_ID_PARAM = "taskId";
  private static final String PROCESS_CHAIN_SHAPE_PARAM = "processChainShape";
  private static final String PROCESS_CHAIN_DIRECTION_PARAM = "processChainDirection";
  private static final String PROCESS_STEPS_PARAM = "processSteps";
  private static final String CURRENT_PROCESS_STEP_PARAM = "currentProcessStep";
  
  private ITask task;
  private int currentProcessStep;
  private List<String> processSteps;
  private String processChainDirection;
  private String processChainShape;

  public void navigateToEndPage() throws MalformedURLException {
    Map<String, String> requestParamMap = getRequestParameterMap();
    String taskId = requestParamMap.get(TASK_ID_PARAM);
    
    if (StringUtils.isNotBlank(taskId)) {
      PortalNavigator navigator = new PortalNavigator();
      navigator.navigateToPortalEndPage(Long.parseLong(taskId));
    }
  }

  public void getDataFromIFrame() throws Exception {
    Map<String, String> requestParamMap = getRequestParameterMap();

    String taskId = requestParamMap.get(TASK_ID_PARAM);
    if (StringUtils.isNotBlank(taskId)) {
      SecurityManagerFactory.getSecurityManager()
          .executeAsSystem(() -> task = Ivy.wf().findTask(Long.parseLong(requestParamMap.get(TASK_ID_PARAM))));
      currentProcessStep = Optional.ofNullable(requestParamMap.get(CURRENT_PROCESS_STEP_PARAM))
          .filter(str -> str.matches("-?\\d+"))
          .map(Integer::parseInt)
          .orElse(0);
      processSteps = StringUtils.isNotBlank(requestParamMap.get(PROCESS_STEPS_PARAM)) ? Arrays.asList(requestParamMap.get(PROCESS_STEPS_PARAM).split(",")) : new ArrayList<>();
      processChainDirection = Optional.ofNullable(requestParamMap.get(PROCESS_CHAIN_DIRECTION_PARAM)).orElse(StringUtils.EMPTY);
      processChainShape = Optional.ofNullable(requestParamMap.get(PROCESS_CHAIN_SHAPE_PARAM)).map(Object::toString).orElse(StringUtils.EMPTY);
    }
  }

  private Map<String, String> getRequestParameterMap() {
    Map<String, String> requestParamMap =
        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    return requestParamMap;
  }

  public ITask getTask() {
    return task;
  }

  public void setTask(ITask task) {
    this.task = task;
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
