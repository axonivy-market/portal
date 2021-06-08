package ch.ivy.addon.portalkit.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import ch.ivy.addon.portalkit.bo.ExcelExportSheet;
import ch.ivy.addon.portalkit.service.DateTimeGlobalSettingService;
import ch.ivyteam.ivy.scripting.objects.Date;
import ch.ivyteam.ivy.scripting.objects.DateTime;
import ch.ivyteam.ivy.scripting.objects.Time;
import ch.ivyteam.ivy.scripting.objects.util.IvyDefaultValues;

public final class ExcelExport
{

  private static final int NUMBER_OF_TRACKED_ROWS_TO_AUTOSIZE_COLUMN = 100;

  private static final int EXCEL_TIME_CELL_FORMAT = 0x15;

  private static final int EXCEL_DATE_CELL_FORMAT = 0x0e;

  private static final String INTERNAL_DATE_FORMAT = "yyyyMMdd'T'HHmmss";

  private static final String DEFAULT_SHEET_NAME = "Table";

  private SXSSFWorkbook workBook;

  private List<SXSSFSheet> sheets;

  private SXSSFRow excelRow;

  private SXSSFCell cell;

  private CellStyle defaultCellStyle;

  private CellStyle headerCellStyle;

  private CellStyle dateCellStyle;

  private CellStyle timeCellStyle;

  private CellStyle dateTimeCellStyle;

  private DateFormat dateFormatter;

  private ExcelExport()
  {
    this(null);
  }

  private ExcelExport(List<String> sheetNames)
  {
    Font font;

    sheets = new ArrayList<>();

    workBook = new SXSSFWorkbook(1000);
    defaultCellStyle = workBook.createCellStyle();
    defaultCellStyle.setWrapText(true);
    dateCellStyle = workBook.createCellStyle();
    dateCellStyle.setDataFormat((short) EXCEL_DATE_CELL_FORMAT);
    timeCellStyle = workBook.createCellStyle();
    timeCellStyle.setDataFormat((short) EXCEL_TIME_CELL_FORMAT);
    dateTimeCellStyle = workBook.createCellStyle();
    dateTimeCellStyle.setDataFormat(getDateFormat());

    headerCellStyle = workBook.createCellStyle();
    headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
    font = workBook.createFont();
    font.setBold(true);
    headerCellStyle.setFont(font);
    dateFormatter = new SimpleDateFormat(INTERNAL_DATE_FORMAT);

    for (String sheetName : sheetNames) {
      sheets.add(workBook.createSheet(sheetName == null ? DEFAULT_SHEET_NAME : sheetName));
    }
  }

  private short getDateFormat() {
    String dateFormat = DateTimeGlobalSettingService.getInstance().getGlobalSettingPattern();
    return workBook.createDataFormat().getFormat(dateFormat);
  }

  private static SXSSFWorkbook exportListAsExcel(List<ExcelExportSheet> sheets)
  {
    ExcelExport export;

    List<String> sheetNames = new ArrayList<>(); 
    for (ExcelExportSheet sheet : sheets) {
      sheetNames.add(sheet.getSheetName());
    }
    export = new ExcelExport(sheetNames);

    return export.exportListToWorkbook(sheets);
  }

  /**
   * Exports a java list of list of object to a stream with Excel content.
   * 
   * @param sheets a list of sheet to build excel file
   * @param outputStream outputStream stream into which the Excel content is written
   * @throws IOException
   */
  public static void exportListAsExcel(List<ExcelExportSheet> sheets,
          OutputStream outputStream) throws IOException
  {
    SXSSFWorkbook workbook;
    workbook = exportListAsExcel(sheets);

    write(workbook, outputStream);
  }

  private static void write(SXSSFWorkbook workbook, OutputStream outputStream) throws IOException
  {
    workbook.write(outputStream);
  }

  private void addCell(int currentColumn, Object cellContent)
  {
    Object usedCellContent;
    usedCellContent = cellContent;

    // if the object contains ivy default values do not export that value
    if (IvyDefaultValues.isDefaultObject(cellContent))
    {
      usedCellContent = null;
    }
    else if (cellContent instanceof Date)
    {
      usedCellContent = ((Date) cellContent).toJavaDate();
    }
    else if (cellContent instanceof Time)
    {
      usedCellContent = ((Time) cellContent).toJavaDate();
    }
    else if (cellContent instanceof DateTime)
    {
      usedCellContent = ((DateTime) cellContent).toJavaDate();
    }

    if (usedCellContent instanceof Number)
    {
      cell = excelRow.createCell(currentColumn, CellType.NUMERIC);
      cell.setCellValue(((Number) usedCellContent).doubleValue());
      cell.setCellStyle(defaultCellStyle);
    }
    else if (usedCellContent instanceof java.util.Date)
    {
      java.util.Date date = (java.util.Date) usedCellContent;
      String dateString;

      dateString = dateFormatter.format(date);

      cell = excelRow.createCell(currentColumn, CellType.NUMERIC);
      cell.setCellValue((java.util.Date) usedCellContent);
      if (dateString.startsWith("00000000T") || cellContent instanceof Time)
      {
        cell.setCellStyle(timeCellStyle);
      }
      else if (dateString.endsWith("T000000") || cellContent instanceof Date)
      {
        cell.setCellStyle(dateCellStyle);
      }
      else
      {
        cell.setCellStyle(dateTimeCellStyle);
      }
    }
    else
    {
      cell = excelRow.createCell(currentColumn);
      cell.setCellValue(usedCellContent == null ? "" : usedCellContent.toString());
      cell.setCellStyle(defaultCellStyle);
    }
  }

  private void addHeaderCell(int currentColumn, String columnHeader)
  {
    cell = excelRow.createCell(currentColumn);
    cell.setCellValue(new XSSFRichTextString(columnHeader));
    cell.setCellStyle(headerCellStyle);
  }

  private SXSSFWorkbook exportListToWorkbook(List<ExcelExportSheet> sheets) {
    for (ExcelExportSheet sheet : sheets) {
      exportListAsExcel(sheet.getHeaders(), sheet.getRows(), sheets.indexOf(sheet));
    }
    return workBook;
  }
  
  private void exportListAsExcel(List<String> headers, List<List<Object>> rows, int sheetIndex)
  {
    int currentColumn;
    int currentRow;

    currentRow = 0;

    if (headers != null)
    {
      sheets.get(sheetIndex).trackAllColumnsForAutoSizing();
      excelRow = sheets.get(sheetIndex).createRow(currentRow++);
      currentColumn = 0;
      for (String columnHeader : headers)
      {
        addHeaderCell(currentColumn++, columnHeader);
      }
    }

    if (rows != null)
    {
      for (int i = 0; i < rows.size(); i++)
      {
        excelRow = sheets.get(sheetIndex).createRow(currentRow++);
        currentColumn = 0;
        for (Object cellContent : rows.get(i))
        {
          addCell(currentColumn, cellContent);
          currentColumn++;
        }
        if (i == NUMBER_OF_TRACKED_ROWS_TO_AUTOSIZE_COLUMN && headers != null) {
          autoSizeColumn(headers.size(), sheetIndex);
        }
      }
      if (rows.size() < NUMBER_OF_TRACKED_ROWS_TO_AUTOSIZE_COLUMN && headers != null) {
        autoSizeColumn(headers.size(), sheetIndex);
      }
    }

  }

  private void autoSizeColumn(int numberOfColumn, int sheetIndex) {
    for (int colIndex = 0; colIndex < numberOfColumn; colIndex++)
    {
      sheets.get(sheetIndex).autoSizeColumn(colIndex, false);
    }
    sheets.get(sheetIndex).untrackAllColumnsForAutoSizing();
  }
}
