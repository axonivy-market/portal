package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.dto.taskdetails.TaskDetails;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

@ViewScoped
@ManagedBean
public class TaskDetailsBean extends AbstractConfigurableContentBean<TaskDetails> implements Serializable {

  private static final long serialVersionUID = 8566646437739271552L;
  private boolean hasShowDurationTime;

  @PostConstruct
  public void init() {
    super.initConfig();
    try {
      hasShowDurationTime = Boolean.parseBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.SHOW_TASK_DURATION_TIME));
      loadWidgets();
    } catch (Exception e) {
      Ivy.log().error("Exception at method init of Class TaskDetailsBean", e);
    }
  }

  @Override
  protected void loadWidgets() throws Exception {
    ITask selectedTask = getSelectedTaskFromData();
    if (selectedTask == null) {
      return;
    }
    
    super.loadWidgets();
  }

  protected ITask getSelectedTaskFromData() {
    return Attrs.currentContext().getAttribute("#{data.task}", ITask.class);
  }

  public boolean isShowDurationTime() {
    return hasShowDurationTime;
  }

  public void setShowDurationTime(boolean hasShowDurationTime) {
    this.hasShowDurationTime = hasShowDurationTime;
  }

  @Override
  public String getVariableKey() {
    return PortalVariable.TASK_DETAIL.key;
  }

  @Override
  protected boolean findConfigByPredefinedFilters(List<TaskDetails> configurations) {
    ITask currentTask = getSelectedTaskFromData();
    for (TaskDetails config : configurations) {
      // found configuration for current task by predefined filters
      if (isFilterByCategories(currentTask.getCategoryPath(), config.getFilters())
          || isFilterByStates(currentTask.getState().name(), config.getFilters())) {
        configuration = config;
        return true;
      }
    }
    return false;
  }

  @Override
  protected Class<TaskDetails> getConfigurationType() {
    return TaskDetails.class;
  }

  @Override
  protected String getDefaultConfigId() {
    return "default-task-detail";
  }

}
