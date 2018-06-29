package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import ch.ivy.addon.portalkit.datamodel.TaskAnalysisLazyDataModel;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.TaskAndCaseAnalysisColumn;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.TaskAnalysisFilterService;
import ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class TaskAnalysisWidgetBean implements Serializable {

  private static final long serialVersionUID = 1L;
  public static final int DEFAULT_TASK_LIST_REFRESH_INTERVAL = 10000;
  private Long taskListRefreshInterval;
  private Long expandedTaskId;
  private TaskAnalysisLazyDataModel dataModel;
  private Map<String, Boolean> columns;
  private String fileName;

  public TaskAnalysisWidgetBean() {
    expandedTaskId = -1L;
    dataModel = new TaskAnalysisLazyDataModel();
    String taskListRefreshIntervalUserSetting =
        new GlobalSettingService().findGlobalSettingValue(GlobalVariable.REFRESH_TASK_LIST_INTERVAL.toString());
    taskListRefreshInterval =
        StringUtils.isNumeric(taskListRefreshIntervalUserSetting) ? Long.parseLong(taskListRefreshIntervalUserSetting)
            : DEFAULT_TASK_LIST_REFRESH_INTERVAL;
  }

  @PostConstruct
  public void init() {
    columns = new HashMap<>();
    for(TaskAndCaseAnalysisColumn column : TaskAndCaseAnalysisColumn.values()) {
      columns.put(column.name(), column.isDefaultColumn());
    }
  }

  public Long getExpandedTaskId() {
    return expandedTaskId;
  }

  public TaskAnalysisLazyDataModel getDataModel() {
    return dataModel;
  }

  public void setDataModel(TaskAnalysisLazyDataModel dataModel) {
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

  public boolean isDeleteFilterEnabledFor(TaskAnalysisFilterData filterData) {
    TaskAnalysisFilterService filterService = new TaskAnalysisFilterService();
    return filterService.isDeleteFilterEnabledFor(filterData);
  }
  
  public void onToggleColumns(ToggleEvent e) {
    TaskAndCaseAnalysisColumn toggledColumn = TaskAndCaseAnalysisColumn.values()[(Integer) e.getData()];
    columns.put(toggledColumn.name(), e.getVisibility() == Visibility.VISIBLE);
  }
  
  public void formatFileName() {
	SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmm");
	Date createdFileTime = new Date();
	fileName = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/exportedTasksCasesFileName", Arrays.asList(dateFormat.format(createdFileTime)));
  }

  public Long getTaskListRefreshInterval() {
    return taskListRefreshInterval;
  }

  public String getFileName() {
	formatFileName();
	return fileName;
  }

  public Map<String, Boolean> getColumns() {
    return columns;
  }

  public void setColumns(Map<String, Boolean> columns) {
    this.columns = columns;
  }
}
