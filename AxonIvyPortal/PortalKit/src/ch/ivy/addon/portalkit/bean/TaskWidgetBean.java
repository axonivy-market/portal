package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.filter.AbstractFilter.ALL;
import static ch.ivyteam.ivy.workflow.TaskState.DELAYED;
import static ch.ivyteam.ivy.workflow.TaskState.DESTROYED;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.GuidePool;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.TaskFilterService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivy.addon.portalkit.taskfilter.TaskStateFilter;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class TaskWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;
  public static final int DEFAULT_TASK_LIST_REFRESH_INTERVAL = 10000;
  private Long taskListRefreshInterval;
  private Long expandedTaskId;
  private Long selectedTaskItemId;
  private TaskLazyDataModel dataModel;
  private Boolean isTaskDetailOpenning;
  private boolean isShowFullTaskList;
  private boolean isGuide;
  private boolean isAdminTaskStateIncluded;

  public TaskWidgetBean() {
    expandedTaskId = -1L;
    String taskListRefreshIntervalUserSetting =
        new GlobalSettingService().findGlobalSettingValue(GlobalVariable.REFRESH_TASK_LIST_INTERVAL.toString());
    taskListRefreshInterval =
        StringUtils.isNumeric(taskListRefreshIntervalUserSetting) ? Long.parseLong(taskListRefreshIntervalUserSetting)
            : DEFAULT_TASK_LIST_REFRESH_INTERVAL;
    isShowFullTaskList = PermissionUtils.checkAccessFullTaskListPermission();
  }
  
  public void preRender(TaskLazyDataModel dataModel) {
    isGuide = GuidePool.instance().guide(Ivy.session().getSessionUserName()).isGuideShown();
    dataModel.setIsGuide(isGuide);
  }

  public Long getExpandedTaskId() {
    return expandedTaskId;
  }

  public void setExpandedTaskId(Long expandedTaskId, boolean alreadyExpanded) {
    setIsTaskDetailOpenning(!alreadyExpanded);
    this.expandedTaskId = alreadyExpanded ? 0L : expandedTaskId;
  }

  public TaskLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(TaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public String sanitizeHTML(String text) {
    return HtmlParser.sanitizeHTML(text);
  }

  public String createTaskDescriptionInTaskStart(String text) {
    return HtmlParser.extractTextFromHtml(text);
  }

  public boolean isDeleteFilterEnabledFor(TaskFilterData filterData) {
    TaskFilterService filterService = new TaskFilterService();
    return filterService.isDeleteFilterEnabledFor(filterData);
  }

  /**
   * If Task State filter is selecting DELAYED or DESTROYED
   * Then disable option save a filter for all user
   * @param taskFilters is selected filters
   */
  public void verifyTaskStateFilter(List<TaskFilter> taskFilters) {
    if (!PermissionUtils.checkReadAllTasksPermission()) {
      isAdminTaskStateIncluded = false;
      return;
    }
    for (TaskFilter filter : taskFilters) {
      if (filter instanceof TaskStateFilter) {
        TaskStateFilter taskStateFilter = (TaskStateFilter) filter;
        if (!taskStateFilter.value().equals(ALL)) {
          isAdminTaskStateIncluded = taskStateFilter.getSelectedFilteredStates().contains(DELAYED)
              || taskStateFilter.getSelectedFilteredStates().contains(DESTROYED);
        }
        break;
      }
    }
  }

  public boolean isAdminTaskStateIncluded() {
    return isAdminTaskStateIncluded;
  }

  public void setAdminTaskStateIncluded(boolean isAdminTaskStateIncluded) {
    this.isAdminTaskStateIncluded = isAdminTaskStateIncluded;
  }

  public Long getTaskListRefreshInterval() {
    return taskListRefreshInterval;
  }

  public Boolean getIsTaskDetailOpenning() {
    return isTaskDetailOpenning;
  }

  public void setIsTaskDetailOpenning(Boolean isTaskDetailOpenning) {
    this.isTaskDetailOpenning = isTaskDetailOpenning;
  }

  public boolean isShowFullTaskList() {
    return isShowFullTaskList;
  }

  public void setShowFullTaskList(boolean isShowFullTaskList) {
    this.isShowFullTaskList = isShowFullTaskList;
  }

  public Long getSelectedTaskItemId() {
    return selectedTaskItemId;
  }

  public void setSelectedTaskItemId(Long selectedTaskItemId) {
    this.selectedTaskItemId = selectedTaskItemId;
  }
  
  public void destroyTask(Long taskId) {
    TaskUtils.destroyTaskById(taskId);
  }
  
}
