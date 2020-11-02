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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.bo.ExcelExportSheet;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class TaskExporter {
  private static final String ZIP = "zip";
  private static final String XLSX = "xlsx";
  public static final int MAX_TASK_NUMBER_IN_EXCEL = 1048575; // = MAX ROWS (1048576) - 1 (for header row)
  private static final String FILE_NAME_SUFFIX_FOR_EXCEL_IN_ZIP = "_%s";
  private List<String> columnsVisibility;
  
  public TaskExporter(List<String> columnsVisibility) {
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
    for (String column : columnsVisibility) {
      headers.add(getColumnName(column));
    }
    return headers;
  }
  
  private String getColumnName(String column) { 
    switch (column) {
      case TaskLazyDataModel.NAME:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/taskName");
      case TaskLazyDataModel.DESCRIPTION:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/description");
      case TaskLazyDataModel.ID:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ID");
      case TaskLazyDataModel.ACTIVATOR:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ACTIVATOR");
      case TaskLazyDataModel.PRIORITY:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/PRIORITY");
      case TaskLazyDataModel.STATE:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/STATE");
      case TaskLazyDataModel.CREATION_TIME:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/create");
      case TaskLazyDataModel.EXPIRY_TIME:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/EXPIRY_TIME");
      case TaskLazyDataModel.COMPLETED_ON:
        return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/COMPLETED_ON");
      default:
        return getCustomColumnName(column);
    }
  }
  
  protected String getCustomColumnName(String column) {
    return "";
  }

  private String getColumnValue(String column, ITask task) { 
    switch (column) {
      case TaskLazyDataModel.NAME:
        return StringUtils.isEmpty(task.getName()) ? Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/taskNameNotAvailable") : task.getName();
      case TaskLazyDataModel.DESCRIPTION:
        return task.getDescription();
      case TaskLazyDataModel.ID:
        return String.valueOf(task.getId());
      case TaskLazyDataModel.ACTIVATOR:
        if (task.getActivatorName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(task.getActivator(), task.getActivatorName());
      case TaskLazyDataModel.PRIORITY:
        return task.getPriority().toString();
      case TaskLazyDataModel.STATE:
        return task.getState().toString();
      case TaskLazyDataModel.CREATION_TIME:
        return formatDate(task.getStartTimestamp());
      case TaskLazyDataModel.EXPIRY_TIME:
        Date expiryTimestamp = task.getExpiryTimestamp();
        return expiryTimestamp != null ? formatDate(expiryTimestamp): "";
      case TaskLazyDataModel.COMPLETED_ON:
        Date endTimestamp = task.getEndTimestamp();
        return endTimestamp != null ? formatDate(endTimestamp): "";
      default:
        return getCustomColumnValue(column, task);
    }
  }
  
  protected String getCustomColumnValue(String column, ITask task) {
    return "";
  }
  
  private List<List<Object>> generateData(List<ITask> tasks) {
    List<List<Object>> rows = new ArrayList<>();
    for (ITask task : tasks) {
      List<Object> row = new ArrayList<>();
      for (String column : columnsVisibility) {
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
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/exportedTasksFileName",
        Arrays.asList(fileNameSuffix, extension));
  }
  
  protected String formatDate(Date datetime) {
    String pattern =
        Ivy.cms().findContentObjectValue("/patterns/dateTimePattern", Locale.ENGLISH).getContentAsString();
    return new SimpleDateFormat(pattern).format(datetime);
  }
}
