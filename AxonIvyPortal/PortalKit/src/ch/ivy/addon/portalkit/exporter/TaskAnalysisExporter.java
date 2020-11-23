package ch.ivy.addon.portalkit.exporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.bo.ExcelExportSheet;
import ch.ivy.addon.portalkit.enums.TaskAndCaseAnalysisColumn;
import ch.ivy.addon.portalkit.util.ExcelExport;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class TaskAnalysisExporter {
  private static final String ZIP = "zip";
  private static final String XLSX = "xlsx";
  public static final int MAX_TASK_NUMBER_IN_EXCEL = 1048575; // = MAX ROWS (1048576) - 1 (for header row)
  private static final String FILE_NAME_SUFFIX_FOR_EXCEL_IN_ZIP = "_%s";
  private Map<String, Boolean> columnsVisibility;
  
  public TaskAnalysisExporter(Map<String, Boolean> columnsVisibility) {
    this.columnsVisibility = columnsVisibility;
  }

  public StreamedContent getStreamedContent(List<ITask> tasks) throws IOException {
    Date creationDate = new Date();
    StreamedContent file;
    if (tasks.size() > MAX_TASK_NUMBER_IN_EXCEL) {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(generateZipContent(tasks, creationDate));
      file = new DefaultStreamedContent(inputStream, "application/zip", getFileName(creationDate, ZIP));
    } else {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(generateExcelContent(tasks));
      file = new DefaultStreamedContent(inputStream, "application/xlsx", getFileName(creationDate, XLSX));
    }
    return file;
  }

  private byte[] generateZipContent(List<ITask> tasks, Date creationDate) throws IOException {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
      List<List<ITask>> tasksInFiles = ListUtils.partition(tasks, MAX_TASK_NUMBER_IN_EXCEL);
      for (int i = 0; i < tasksInFiles.size(); i++) {
        String excelFileName = getFileName(creationDate, XLSX, String.format(FILE_NAME_SUFFIX_FOR_EXCEL_IN_ZIP, i + 1));
        try {
          byte[] content = generateExcelContent(tasksInFiles.get(i));
          zipOutputStream.putNextEntry(new ZipEntry(excelFileName));
          zipOutputStream.write(content);
          zipOutputStream.closeEntry();
        } catch (IOException e) {
          Ivy.log().error("The " + excelFileName + " file can't be exported", e);
        }
      }
      zipOutputStream.close();
      return outputStream.toByteArray();
    }
  }

  private byte[] generateExcelContent(List<ITask> tasks) throws IOException {
    List<List<Object>> rows = generateData(tasks);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ExcelExportSheet sheet = new ExcelExportSheet();
    sheet.setHeaders(generateHeaders());
    sheet.setRows(rows);
    List<ExcelExportSheet> sheets = Arrays.asList(sheet);
    ExcelExport.exportListAsExcel(sheets, outputStream);
    return outputStream.toByteArray();
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
        return StringUtils.isEmpty(task.getCase().getName()) ? Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/caseNameNotAvailable") : task.getCase().getName();
      case CASE_DESCRIPTION:
        return task.getCase().getDescription();
      case CASE_ID:
        return String.valueOf(task.getCase().getId());
      case CASE_CATEGORY:
        return task.getCase().getCategoryPath();
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

  private String getFileName(Date creationDate, String extension) {
    return getFileName(creationDate, extension, null);
  }

  private String getFileName(Date creationDate, String extension, String suffix) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmm");
    Date createdFileTime = creationDate != null ? creationDate : new Date();
    String fileNameSuffix = suffix == null ? dateFormat.format(createdFileTime) : dateFormat.format(createdFileTime) + suffix; 
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/exportedTasksCasesFileName",
        Arrays.asList(fileNameSuffix, extension));
  }
  
  private boolean isColumnVisible(TaskAndCaseAnalysisColumn column) {
    return columnsVisibility.get(column.name());
  }
}
