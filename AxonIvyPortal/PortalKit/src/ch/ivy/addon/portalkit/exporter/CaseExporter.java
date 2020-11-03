package ch.ivy.addon.portalkit.exporter;

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
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.util.ExcelExport;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

public class CaseExporter {
  private static final String ZIP = "zip";
  private static final String XLSX = "xlsx";
  public static final int MAX_CASE_NUMBER_IN_EXCEL = 1048575; // = MAX ROWS (1048576) - 1 (for header row)
  private static final String FILE_NAME_SUFFIX_FOR_EXCEL_IN_ZIP = "_%s";
  private List<String> columnsVisibility;
  
  public CaseExporter(List<String> columnsVisibility) {
    this.columnsVisibility = columnsVisibility;
  }

  public StreamedContent getStreamedContent(List<ICase> cases) throws IOException {
    Date creationDate = new Date();
    StreamedContent file;
    if (cases.size() > MAX_CASE_NUMBER_IN_EXCEL) {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(generateZipContent(cases, creationDate));
      file = new DefaultStreamedContent(inputStream, "application/zip", getFileName(creationDate, ZIP));
    } else {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(generateExcelContent(cases));
      file = new DefaultStreamedContent(inputStream, "application/xlsx", getFileName(creationDate, XLSX));
    }
    return file;
  }

  private byte[] generateZipContent(List<ICase> cases, Date creationDate) throws IOException {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
      List<List<ICase>> casesInFiles = ListUtils.partition(cases, MAX_CASE_NUMBER_IN_EXCEL);
      for (int i = 0; i < casesInFiles.size(); i++) {
        String excelFileName = getFileName(creationDate, XLSX, String.format(FILE_NAME_SUFFIX_FOR_EXCEL_IN_ZIP, i + 1));
        try {
          byte[] content = generateExcelContent(casesInFiles.get(i));
          zipOutputStream.putNextEntry(new ZipEntry(excelFileName));
          zipOutputStream.write(content);
          zipOutputStream.closeEntry();
        } catch (IOException e) {
          Ivy.log().error("The {0} file can't be exported", e, excelFileName);
        }
      }
      zipOutputStream.close();
      return outputStream.toByteArray();
    }
  }

  private byte[] generateExcelContent(List<ICase> cases) throws IOException {
    List<List<Object>> rows = generateData(cases);
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

  /**
   * <p>
   * Gets column name.
   * </p>
   * <p>
   * In case you adds new columns, these columns need cms to show in excel file
   * </p>
   * <p>
   * You can either add new entry to default folder below in PortalStyle or override this method to create your own
   * folder column must be the same with sortField
   * </p>
   * 
   * @param column
   * @return column name
   */
  protected String getColumnName(String column) {
    String columnName = getSpecialColumnName(column);
    return columnName != null ? columnName
        : Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/" + column);
  }

  /**
   * Gets column name that is differ from UI.
   * 
   * @param column
   * @return column name
   */
  protected String getSpecialColumnName(String column) {
    if (CaseSortField.NAME.name().equals(column)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/taskAnalysis/caseName");
    } else if (CaseLazyDataModel.DESCRIPTION.equals(column)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/description");
    }
    return null;
  }

  /**
   * Gets case column value.
   * 
   * @param column
   * @param caseItem
   * @return case column value
   */
  protected String getColumnValue(String column, ICase caseItem) {
    return getCommonColumnValue(column, caseItem);
  }

  protected String getCommonColumnValue(String column, ICase caseItem) {
    if (StringUtils.equals(column, CaseLazyDataModel.DESCRIPTION)) {
      return caseItem.getDescription();
    }

    CaseSortField sortField = CaseSortField.valueOf(column);
    switch (sortField) {
      case NAME:
        return StringUtils.isEmpty(caseItem.getName()) ? Ivy.cms().co("/Dialogs/ch/ivy/addon/portalkit/component/CaseWidget/caseNameNotAvailable") : caseItem.getName();
      case ID:
        return String.valueOf(caseItem.getId());
      case CREATOR:
        if (caseItem.getCreatorUserName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(caseItem.getCreatorUser(), caseItem.getCreatorUserName());
      case OWNER:
        if (caseItem.getOwnerName() == null) {
          return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
        }
        return SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(caseItem.getOwner(), caseItem.getOwnerName());
      case CREATION_TIME:
        return formatDate(caseItem.getStartTimestamp());
      case FINISHED_TIME:
        Date endTimestamp = caseItem.getEndTimestamp();
        return endTimestamp != null ? formatDate(endTimestamp): "";
      case STATE:
        return caseItem.getState().toString();
      default:
        return "";
    }
  }

  private List<List<Object>> generateData(List<ICase> cases) {
    List<List<Object>> rows = new ArrayList<>();
    for (ICase caseItem : cases) {
      List<Object> row = new ArrayList<>();
      for (String column : columnsVisibility) {
        row.add(getColumnValue(column, caseItem));
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
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/exportedCasesFileName",
        Arrays.asList(fileNameSuffix, extension));
  }
  
  protected String formatDate(Date datetime) {
    String pattern =
        Ivy.cms().findContentObjectValue("/patterns/dateTimePattern", Locale.ENGLISH).getContentAsString();
    return new SimpleDateFormat(pattern).format(datetime);
  }
}
