package ch.ivy.addon.portalkit.exporter.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.TaskAndCaseAnalysisColumn;
import ch.ivy.addon.portalkit.exporter.Exporter;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class TaskAnalysisExporter extends Exporter{
  private Map<String, Boolean> taskAnalysisColumnsVisibility;
  
  public TaskAnalysisExporter(Map<String, Boolean> columnsVisibility) {
    this.taskAnalysisColumnsVisibility = columnsVisibility;
  }

  @Override
  protected List<String> generateHeaders() {
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
      case CASE_OWNER:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/caseOwner");
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

  private Object getColumnValue(TaskAndCaseAnalysisColumn column, ITask task) {
    switch (column) {
      case CASE_NAME:
        return StringUtils.isEmpty(task.getCase().names().current()) ? Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/caseNameNotAvailable") : task.getCase().names().current();
      case CASE_DESCRIPTION:
        return task.getCase().descriptions().current();
      case CASE_ID:
        return String.valueOf(task.getCase().getId());
      case CASE_CATEGORY:
        return task.getCase().getCategory().getPath();
      case CASE_CREATOR:
        if (task.getCase().getCreatorUserName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(task.getCase().getCreatorUser(), task.getCase().getCreatorUserName());
      case CASE_OWNER:
        if (task.getCase().getOwnerName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(task.getCase().getOwner(), task.getCase().getOwnerName());
      case CASE_STATE:
        return task.getCase().getState().toString();
      case TASK_NAME:
        return StringUtils.isEmpty(task.names().current()) ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskNameNotAvailable") : task.names().current();
      case TASK_ID:
        return String.valueOf(task.getId());
      case TASK_CATEGORY:
        return task.getCategory().getPath();
      case TASK_DESCRIPTION:
        return task.descriptions().current();
      case TASK_ACTIVATOR:
        if (task.getActivatorName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(task.getActivator(), task.getActivatorName());
      case TASK_WORKER:
        if (task.getWorkerUserName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForUser(task.getWorkerUser(), task.getWorkerUserName());
      case TASK_PRIORITY:
        return task.getPriority().toString();
      case TASK_STATE:
        return task.getState().toString();
      case TASK_CREATION_TIME:
        return task.getStartTimestamp();
      case TASK_EXPIRY_TIME:
        return task.getExpiryTimestamp();
      case TASK_FINISHED_TIME:
        return task.getEndTimestamp();
      default:
        return "";
    }
  }
  
  @Override
  public <T> List<List<Object>> generateData(List<T> tasks) {
    List<List<Object>> rows = new ArrayList<>();
    for (T t : tasks) {
      if (t instanceof ITask) {
        List<Object> row = new ArrayList<>();
        for (TaskAndCaseAnalysisColumn column : TaskAndCaseAnalysisColumn.values()) {
          if (isColumnVisible(column))
          row.add(getColumnValue(column, (ITask)t));
        }
        rows.add(row);
      }
    }
    return rows;

  }

  @Override
  public String generateFileName(Date creationDate, String extension, String suffix) {
    String fileNameSuffix = createFileNameSuffix(creationDate, suffix);  
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/exportedTasksCasesFileName",
        Arrays.asList(fileNameSuffix, extension));
  }
  
  private boolean isColumnVisible(TaskAndCaseAnalysisColumn column) {
    return taskAnalysisColumnsVisibility.get(column.name());
  }

  @Override
  public String getColumnName(String column) {
    return null;
  }
}
