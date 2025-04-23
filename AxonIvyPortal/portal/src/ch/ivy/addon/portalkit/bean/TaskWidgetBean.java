package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.SearchScopeTaskField;
import com.axonivy.portal.service.GlobalSearchService;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInTaskList;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class TaskWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;
  public static final int DEFAULT_TASK_LIST_REFRESH_INTERVAL = 10000;
  private Long taskListRefreshInterval;
  private Long expandedTaskId;
  private Long selectedTaskItemId;
  private String selectedTaskName;
  private TaskLazyDataModel dataModel;
  private Boolean isTaskDetailOpenning;
  private boolean isShowFullTaskList;
  private boolean isAdminTaskStateIncluded;
  private boolean isRunningTaskWhenClickingOnTaskInList;

  public TaskWidgetBean() {
    expandedTaskId = -1L;
    String taskListRefreshIntervalUserSetting = "10000"; // set default value instead of variable
    taskListRefreshInterval =
        StringUtils.isNumeric(taskListRefreshIntervalUserSetting) ? Long.parseLong(taskListRefreshIntervalUserSetting)
            : DEFAULT_TASK_LIST_REFRESH_INTERVAL;
    isShowFullTaskList = PermissionUtils.checkAccessFullTaskListPermission();
    isRunningTaskWhenClickingOnTaskInList = GlobalSettingService.getInstance()
        .findGlobalSettingValue(GlobalVariable.DEFAULT_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST)
        .equals(BehaviourWhenClickingOnLineInTaskList.RUN_TASK.name());
  }
  
  public void preRender(@SuppressWarnings("unused") TaskLazyDataModel dataModel) {}

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
  
  public void expiryTask(Long taskId) {
    TaskUtils.expiryTaskById(taskId);
  }

  /**
   * Gets visible columns on Task list page.
   * 
   * @param dataModel
   * @return visible columns
   */
  public List<String> getColumns(TaskLazyDataModel dataModel) {
    List<String> visibilityColumns = new ArrayList<>();
    visibilityColumns.addAll(dataModel.getSelectedColumns());

    /*
     * In UI we have a column called "Name / Description", but PortalRequiredColumns contains only "Name" column, so
     * that we need to check and add "Description" to Excel file
     */
    List<String> requiredColumns = dataModel.getPortalRequiredColumns();
    if (requiredColumns != null && requiredColumns.contains(TaskSortField.NAME.name())) {
      visibilityColumns.add(TaskLazyDataModel.DESCRIPTION);
    }
    return visibilityColumns;
  }

  public int getMaxTaskNumberInExcel() {
    return Exporter.MAX_ROW_NUMBER_IN_EXCEL;
  }

  public boolean isRunningTaskWhenClickingOnTaskInList() {
    return isRunningTaskWhenClickingOnTaskInList;
  }

  public void setRunningTaskWhenClickingOnTaskInList(boolean isRunningTaskWhenClickingOnTaskInList) {
    this.isRunningTaskWhenClickingOnTaskInList = isRunningTaskWhenClickingOnTaskInList;
  }

  public String getGlobalSearchText(TaskLazyDataModel model) {
    String result = "";
    if (model.getCriteria().isGlobalSearch()) {
      String keyword = Optional.ofNullable(model.getCriteria().getKeyword()).orElse("");
      String searchScopeTaskFieldsString = "";

      if (CollectionUtils.isNotEmpty(model.getCriteria().getSearchScopeTaskFields())) {
        searchScopeTaskFieldsString
          = ", ".concat(String.join(", ", model.getCriteria().getSearchScopeTaskFields().stream().map(SearchScopeTaskField::getLabel).collect(Collectors.toList())));
      }

      result = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskView/GlobalSearchText", Arrays.asList(keyword, searchScopeTaskFieldsString));
    }
    return result;
  }
  
  public boolean isShowGlobalSearchScope() {
    return GlobalSearchService.getInstance().isShowGlobalSearchByTasks();
  }
  
  public String getSelectedTaskName() {
    return this.selectedTaskName;
  }
  
  public void setSelectedTaskName(String taskName) {
    this.selectedTaskName = taskName;
  }
  
  public void updateSelectedTaskName(String taskName) {
    setSelectedTaskName(taskName);
  }
  
  public void updateSelectedTask(Boolean isShowInTaskList, ITask task) {
    if (isShowInTaskList) {
      setSelectedTaskItemId(task.getId());
      setSelectedTaskName(task.names().current());
    }
  }
  
  public String destroyTaskMessage() {
    String taskName = "Unknown Task";
    String taskId = "Unknown ID";
    if (this.selectedTaskItemId != null) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/destroyTaskMessage", List.of(this.selectedTaskName, this.selectedTaskItemId));
    }
    else return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/destroyTaskMessage", List.of(taskName, taskId));
  }
}
