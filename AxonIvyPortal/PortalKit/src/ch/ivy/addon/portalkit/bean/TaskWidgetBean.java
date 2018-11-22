package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.TaskFilterService;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class TaskWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;
  public static final int DEFAULT_TASK_LIST_REFRESH_INTERVAL = 10000;
  private Long taskListRefreshInterval;
  private Long expandedTaskId;
  private TaskLazyDataModel dataModel;
  private Boolean isTaskDetailOpenning;
  private TaskSortField[] taskSortFields = new TaskSortField[]{TaskSortField.CREATION_TIME, TaskSortField.EXPIRY_TIME, TaskSortField.PRIORITY};
  private TaskSortField selectedSort;

  public TaskWidgetBean() {
    expandedTaskId = -1L;
    if(dataModel == null) {
      dataModel = new TaskLazyDataModel();
      dataModel.setCompactMode(true);
    }
    String taskListRefreshIntervalUserSetting =
        new GlobalSettingService().findGlobalSettingValue(GlobalVariable.REFRESH_TASK_LIST_INTERVAL.toString());
    taskListRefreshInterval =
        StringUtils.isNumeric(taskListRefreshIntervalUserSetting) ? Long.parseLong(taskListRefreshIntervalUserSetting)
            : DEFAULT_TASK_LIST_REFRESH_INTERVAL;
  }

  public Long getExpandedTaskId() {
    return expandedTaskId;
  }

  public void setExpandedTaskId(Long expandedTaskId, boolean alreadyExpanded) {
    if (alreadyExpanded) {
      setIsTaskDetailOpenning(false);
      this.expandedTaskId = 0L;
    } else {
      setIsTaskDetailOpenning(true);
      this.expandedTaskId = expandedTaskId;
    }
  }

  public TaskLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(TaskLazyDataModel dataModel) {
    this.dataModel = dataModel;
  }

  public String sanitizeHTML(String text) {
    String sanitizedText = sanitize(text);
    if (StringUtils.isBlank(extractTextFromHtml(sanitizedText))) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/noDescription");
    }
    return sanitizedText;
  }

  public String createTaskDescriptionInTaskStart(String text) {
    String extractedText = extractTextFromHtml(text);
    if (StringUtils.isBlank(extractedText)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/noDescription");
    }
    return extractedText;
  }

  private String extractTextFromHtml(String text) {
    String sanitizedText = sanitize(text);
    Document doc = Jsoup.parse(sanitizedText);
    return doc.body().text();
  }

  private String sanitize(String text) {
    return Jsoup.clean(text, Whitelist.relaxed().addAttributes(":all", "style"));
  }

  public boolean isDeleteFilterEnabledFor(TaskFilterData filterData) {
    TaskFilterService filterService = new TaskFilterService();
    return filterService.isDeleteFilterEnabledFor(filterData);
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

  public TaskSortField[] getTaskSortFields() {
    return taskSortFields;
  }
  
  public TaskSortField getSelectedSort() {
    return selectedSort;
  }

  public void setSelectedSort(TaskSortField selectedSort) {
    this.selectedSort = selectedSort;
  }
  
  public void sortTask() {
    if (selectedSort != null) {
      FacesContext context = FacesContext.getCurrentInstance();
      TaskLazyDataModel dataModel = context.getApplication().evaluateExpressionGet(context, "#{data.dataModel}", TaskLazyDataModel.class);
      dataModel.setSortField(selectedSort.name(), false);
    }
  }
}
