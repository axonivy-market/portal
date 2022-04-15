package ch.ivy.addon.portalkit.exporter.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ch.ivy.addon.portalkit.bean.CaseBean;
import ch.ivy.addon.portalkit.bo.ExcelExportSheet;
import ch.ivy.addon.portalkit.bo.History;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivy.addon.portalkit.util.ExcelExport;
import ch.ivy.addon.portalkit.util.SecurityMemberDisplayNameUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;

public class NoteHistoryExporter {

  public StreamedContent getStreamedContentOfTaskNoteHistory(List<INote> taskNoteHistory, String fileName) {
    List<List<Object>> rows = generateDataForTaskNoteHistory(taskNoteHistory);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try {
      ExcelExportSheet sheet = new ExcelExportSheet();
      sheet.setHeaders(generateHeaders(false));
      sheet.setRows(rows);
      sheet.setSheetName("notes-table");
      List<ExcelExportSheet> sheets = Arrays.asList(sheet);
      ExcelExport.exportListAsExcel(sheets, outputStream);
    } catch (IOException e) {
      Ivy.log().error(e);
    }
    ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
    return DefaultStreamedContent
        .builder()
        .stream(() -> inputStream)
        .contentType("application/xlsx")
        .name(fileName)
        .build();
  }

  public StreamedContent getStreamedContentOfCaseNoteHistory(List<History> caseNoteHistory, ICase iCase, String fileName) {
    List<List<Object>> caseNoteRows = generateDataForCaseNoteHistory(caseNoteHistory, iCase.isBusinessCase());
    List<List<Object>> generateDataForCaseInfo = generateDataForCaseInfo(iCase);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try {
      // first sheet for note history
      ExcelExportSheet caseNoteHistorySheet = new ExcelExportSheet();
      caseNoteHistorySheet.setHeaders(generateHeaders(iCase.isBusinessCase()));
      caseNoteHistorySheet.setRows(caseNoteRows);
      caseNoteHistorySheet.setSheetName("notes-table");

      // second sheet for case info
      ExcelExportSheet caseInfoSheet = new ExcelExportSheet();
      caseInfoSheet.setHeaders(generateHeaderForCaseInfo());
      caseInfoSheet.setRows(generateDataForCaseInfo);
      caseInfoSheet.setSheetName("case-info");
      List<ExcelExportSheet> sheets = Arrays.asList(caseNoteHistorySheet, caseInfoSheet);
      ExcelExport.exportListAsExcel(sheets, outputStream);
    } catch (IOException e) {
      Ivy.log().error(e);
    }
    ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
    return DefaultStreamedContent
        .builder()
        .stream(() -> inputStream)
        .contentType("application/xlsx")
        .name(fileName)
        .build();
  }

  private List<String> generateHeaders(boolean isBusinessCase) {
    List<String> headers = new ArrayList<>();
    headers.add(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/columnContent"));
    if (isBusinessCase) {
      headers.add(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/relatedCase"));
    }
    headers.add(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/author"));
    headers.add(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/creationDate"));
    return headers;
  }

  private List<List<Object>> generateDataForTaskNoteHistory(List<INote> taskNoteHistory) {
    List<List<Object>> rows = new ArrayList<>();
    for (INote taskNote : taskNoteHistory) {
      List<Object> row = new ArrayList<>();
      row.add(taskNote.getMessage());
      row.add(SecurityMemberDisplayNameUtils.generateBriefDisplayNameForUser(taskNote.getWritter(), taskNote.getWritter().getName()));
      row.add(formatDate(taskNote.getCreationTimestamp()));
      rows.add(row);
    }
    return rows;
  }

  private List<String> generateHeaderForCaseInfo() {
    List<String> headers = new ArrayList<>();
    headers.add(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskList/name"));
    headers.add(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/ID"));
    headers.add(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/CREATOR"));
    headers.add(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/STATE"));
    return headers;
  }

  private List<List<Object>> generateDataForCaseInfo(ICase iCase) {
    List<List<Object>> rows = new ArrayList<>();
    List<Object> row = new ArrayList<>();
    row.add(iCase.getName());
    row.add(iCase.getId());
    row.add(SecurityMemberDisplayNameUtils.generateFullDisplayNameForUser(iCase.getCreatorUser(), iCase.getCreatorUser().getName()));
    row.add(new CaseBean().getState(iCase));
    rows.add(row);
    return rows;
  }

  private List<List<Object>> generateDataForCaseNoteHistory(List<History> caseNoteHistory, boolean isBusinessCase) {
    List<List<Object>> rows = new ArrayList<>();
    for (History caseNote : caseNoteHistory) {
      List<Object> row = new ArrayList<>();
      row.add(caseNote.getContent());
      if (isBusinessCase) {
        row.add(caseNote.getDisplayCaseName());
      }
      row.add(caseNote.getDisplayName());
      row.add(formatDate(caseNote.getTimestamp()));
      rows.add(row);
    }
    return rows;
  }

  private String formatDate(Date datetime) {
    return new SimpleDateFormat(DateTimeGlobalSettingService.getInstance().getDateTimePattern(), Ivy.session().getContentLocale()).format(datetime);
  }

}
