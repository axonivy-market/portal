package ch.ivy.addon.portalkit.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.bean.UserFormatBean;
import ch.ivy.addon.portalkit.bo.ExcelExportSheet;
import ch.ivy.addon.portalkit.enums.TaskAndCaseAnalysisColumn;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class TaskAnalysisExporter {
  private Map<String, Boolean> columnsVisibility;
  private UserFormatBean userFormatBean;
  
  public TaskAnalysisExporter(Map<String, Boolean> columnsVisibility) {
    this.columnsVisibility = columnsVisibility;
    this.userFormatBean = new UserFormatBean();
  }

  public StreamedContent getStreamedContent(List<ITask> tasks) {
    List<List<Object>> rows = generateData(tasks);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try {
      ExcelExportSheet sheet = new ExcelExportSheet();
      sheet.setHeaders(generateHeaders());
      sheet.setRows(rows);
      List<ExcelExportSheet> sheets = Arrays.asList(sheet);
      ExcelExport.exportListAsExcel(sheets, outputStream);
    } catch (IOException e) {
      Ivy.log().error(e);
    }
    ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
    return new DefaultStreamedContent(inputStream, "application/xlsx", getFileName());

  }

  private List<String> generateHeaders() {
    List<String> headers = new ArrayList<>();
    for (TaskAndCaseAnalysisColumn column : TaskAndCaseAnalysisColumn.values()) {
      if (isColumnVisible(column))
      headers.add(getColumnName(column));
    }
    return headers;
  }
  
  private String getColumnName(TaskAndCaseAnalysisColumn column) { 
    switch (column) {
      case CASE_NAME:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/caseName");
      case CASE_DESCRIPTION:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/caseDescription");
      case CASE_ID:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/caseID");
      case CASE_CATEGORY:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/caseCategory");
      case CASE_CREATOR:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/caseCreator");
      case CASE_STATE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/caseState");
      case TASK_NAME:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/taskName");
      case TASK_ID:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/taskID");
      case TASK_CATEGORY:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/taskCategory");
      case TASK_DESCRIPTION:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/taskDescription");
      case TASK_ACTIVATOR:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/taskResponsible");
      case TASK_WORKER:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/taskWorker");
      case TASK_PRIORITY:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/taskPriority");
      case TASK_STATE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/taskState");
      case TASK_CREATION_TIME:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/taskCreationTime");
      case TASK_EXPIRY_TIME:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/taskExpiryTime");
      case TASK_FINISHED_TIME:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/taskFinishedTime");
      default:
        return "";
    }
  }

  private String getColumnValue(TaskAndCaseAnalysisColumn column, ITask task) { 
    switch (column) {
      case CASE_NAME:
        return StringUtils.isEmpty(task.getCase().getName()) ? Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/caseNameNotAvailable") : task.getCase().getName();
      case CASE_DESCRIPTION:
        return task.getCase().getDescription();
      case CASE_ID:
        return String.valueOf(task.getCase().getId());
      case CASE_CATEGORY:
        return task.getCase().getCategoryPath();
      case CASE_CREATOR:
        return task.getCase().getCreatorUserName();
      case CASE_STATE:
        return task.getCase().getState().toString();
      case TASK_NAME:
        return StringUtils.isEmpty(task.getName()) ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskNameNotAvailable") : task.getName();
      case TASK_ID:
        return String.valueOf(task.getId());
      case TASK_CATEGORY:
        return task.getCategoryPath();
      case TASK_DESCRIPTION:
        return task.getDescription();
      case TASK_ACTIVATOR:
        if (task.getActivatorName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return userFormatBean.formatWithTip(task.getActivator().getDisplayName(), task.getActivatorName());
      case TASK_WORKER:
        if (task.getWorkerUserName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return userFormatBean.formatWithTip(task.getWorkerUser().getFullName(), task.getWorkerUserName());
      case TASK_PRIORITY:
        return task.getPriority().toString();
      case TASK_STATE:
        return task.getState().toString();
      case TASK_CREATION_TIME:
        return formatDate(task.getStartTimestamp());
      case TASK_EXPIRY_TIME:
        Date expiryTimestamp = task.getExpiryTimestamp();
        return expiryTimestamp != null ? formatDate(expiryTimestamp): "";
      case TASK_FINISHED_TIME:
        Date endTimestamp = task.getEndTimestamp();
        return endTimestamp != null ? formatDate(endTimestamp): "";
      default:
        return "";
    }
  }
  private List<List<Object>> generateData(List<ITask> tasks) {
    List<List<Object>> rows = new ArrayList<>();
    for (ITask task : tasks) {
      List<Object> row = new ArrayList<>();
      for (TaskAndCaseAnalysisColumn column : TaskAndCaseAnalysisColumn.values()) {
        if (isColumnVisible(column))
        row.add(getColumnValue(column, task));
      }
        rows.add(row);
    }
    return rows;

  }

  public String getFileName() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmm");
    Date createdFileTime = new Date();
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/exportedTasksCasesFileName",
        Arrays.asList(dateFormat.format(createdFileTime)));
  }
  
  private boolean isColumnVisible(TaskAndCaseAnalysisColumn column) {
    return columnsVisibility.get(column.name());
  }
  
  private String formatDate(Date datetime) {
    String pattern =
        Ivy.cms().findContentObjectValue("/patterns/dateTimePattern", Locale.ENGLISH).getContentAsString();
    return new SimpleDateFormat(pattern).format(datetime);
  }
}
