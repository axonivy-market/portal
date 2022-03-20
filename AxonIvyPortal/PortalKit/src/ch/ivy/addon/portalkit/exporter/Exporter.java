package ch.ivy.addon.portalkit.exporter;

/**
 * Base class for export
 * Provide function to get stream content, default header ..
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.collections4.ListUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.bo.ExcelExportSheet;
import ch.ivy.addon.portalkit.util.ExcelExport;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Base class for export
 * Provide function to get stream content, default header ..
 *
 */
public abstract class Exporter {
  private static final String ZIP = "zip";
  private static final String XLSX = "xlsx";
  /**
   * Excel limit row number
   */
  public static final int MAX_ROW_NUMBER_IN_EXCEL = 1048575; // = MAX ROWS (1048576) - 1 (for header row)
  private static final String FILE_NAME_SUFFIX_FOR_EXCEL_IN_ZIP = "_%s";
  /**
   * List of columns for export
   */
  protected List<String> columnsVisibility;

  public Exporter() {}

  public Exporter(List<String> columnsVisibility) {
    this.setColumnsVisibility(columnsVisibility);
  }

  /**
   * Get display column label based on session language
   * @param column column name
   * @return column column label
   */
  public abstract String getColumnName(String column);

  /**
   * Generate file name
   * @param creationDate
   * @param extension
   * @param suffix
   * @return file name
   */
  protected abstract String generateFileName(Date creationDate, String extension, String suffix);

  /**
   * Implement this method to generate export data
   * @param <T>
   * @param data list of task or case
   * @return data for export
   */
  protected abstract <T> List<List<Object>> generateData(List<T> data);

  /**
   * Create stream content {@link StreamedContent} to export
   * @param <T> data type {@link ITask} or {@link ICase}
   * @param data list of tasks or cases to export
   * @return stream content
   * @throws IOException
   */
  public <T> StreamedContent getStreamedContent(List<T> data) throws IOException {
    Date creationDate = new Date();
    StreamedContent file;
    if (data.size() > MAX_ROW_NUMBER_IN_EXCEL) {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(generateZipContent(data, creationDate));
      file = DefaultStreamedContent
          .builder()
          .stream(() -> inputStream)
          .contentType("application/zip")
          .name(getFileName(creationDate, ZIP))
          .build();
    } else {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(generateExcelContent(data));
      file = DefaultStreamedContent
          .builder()
          .stream(() -> inputStream)
          .contentType("application/xlsx")
          .name(getFileName(creationDate, XLSX))
          .build();
    }
    return file;
  }

  protected List<String> getColumnsVisibility() {
    return columnsVisibility;
  }

  protected void setColumnsVisibility(List<String> columnsVisibility) {
    this.columnsVisibility = columnsVisibility;
  }

  /**
   * Create header list
   * @return header list of export file
   */
  protected List<String> generateHeaders() {
    List<String> headers = new ArrayList<>();
    for (String column : columnsVisibility) {
      headers.add(getColumnName(column));
    }
    return headers;
  }

  /**
   * Create suffix name of export file
   * @param creationDate created date, get current if null
   * @param suffix
   * @return name of export file
   */
  protected String createFileNameSuffix(Date creationDate, String suffix) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmm");
    Date createdFileTime = creationDate != null ? creationDate : new Date();
    String fileNameSuffix = suffix == null ? dateFormat.format(createdFileTime) : dateFormat.format(createdFileTime) + suffix;
    return fileNameSuffix;
  }

  private <T> byte[] generateZipContent(List<T> data, Date creationDate) throws IOException {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
      List<List<T>> tasksInFiles = ListUtils.partition(data, MAX_ROW_NUMBER_IN_EXCEL);
      for (int i = 0; i < tasksInFiles.size(); i++) {
        String excelFileName = generateFileName(creationDate, XLSX, String.format(FILE_NAME_SUFFIX_FOR_EXCEL_IN_ZIP, i + 1));
        byte[] content = generateExcelContent(tasksInFiles.get(i));
        zipOutputStream.putNextEntry(new ZipEntry(excelFileName));
        zipOutputStream.write(content);
        zipOutputStream.closeEntry();
      }
      zipOutputStream.close();
      return outputStream.toByteArray();
    }
  }

  private <T> byte[] generateExcelContent(List<T> data) throws IOException {
    List<List<Object>> rows = generateData(data);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ExcelExportSheet sheet = new ExcelExportSheet();
    sheet.setHeaders(generateHeaders());
    sheet.setRows(rows);
    List<ExcelExportSheet> sheets = Arrays.asList(sheet);
    ExcelExport.exportListAsExcel(sheets, outputStream);
    return outputStream.toByteArray();
  }

  private String getFileName(Date creationDate, String extension) {
    return generateFileName(creationDate, extension, null);
  }
}
