package ch.ivy.addon.portalkit.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import ch.ivyteam.ivy.scripting.objects.Date;
import ch.ivyteam.ivy.scripting.objects.DateTime;
import ch.ivyteam.ivy.scripting.objects.Time;
import ch.ivyteam.ivy.scripting.objects.util.IvyDefaultValues;

/**
 * Helper methods that permits the export to Excel.
 * 
 * @author Rifat Binjos, TI-Informatique
 * @author Patrick Joly, TI-Informatique
 * @author Emmanuel Comba, Soreco
 * @since 11.05.2010
 */
public final class ExcelExport
{
  // @see org.apache.poi.ss.usermodel.BuiltinFormats
  // 0xe, "m/d/yy"
  // 0x15, "h:mm:ss"
  // 0x16, "m/d/yy h:mm"

  private static final int EXCEL_DATETIME_CELL_FORMAT = 0x16;

  private static final int EXCEL_TIME_CELL_FORMAT = 0x15;

  private static final int EXCEL_DATE_CELL_FORMAT = 0x0e;

  private static final String INTERNAL_DATE_FORMAT = "yyyyMMdd'T'HHmmss";

  private static final String DEFAULT_SHEET_NAME = "Table";

  private HSSFWorkbook workBook;

  private HSSFSheet sheet;

  private HSSFRow excelRow;

  private HSSFCell cell;

  private HSSFCellStyle defaultCellStyle;

  private HSSFCellStyle headerCellStyle;

  private HSSFCellStyle dateCellStyle;

  private HSSFCellStyle timeCellStyle;

  private HSSFCellStyle dateTimeCellStyle;

  private DateFormat dateFormatter;

  private ExcelExport()
  {
    this(null);
  }

  private ExcelExport(String sheetName)
  {
    HSSFFont font;

    sheet = null;

    workBook = new HSSFWorkbook();
    defaultCellStyle = workBook.createCellStyle();
    defaultCellStyle.setWrapText(true);
    dateCellStyle = workBook.createCellStyle();
    dateCellStyle.setDataFormat((short) EXCEL_DATE_CELL_FORMAT);
    timeCellStyle = workBook.createCellStyle();
    timeCellStyle.setDataFormat((short) EXCEL_TIME_CELL_FORMAT);
    dateTimeCellStyle = workBook.createCellStyle();
    dateTimeCellStyle.setDataFormat((short) EXCEL_DATETIME_CELL_FORMAT);

    headerCellStyle = workBook.createCellStyle();
    headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    font = workBook.createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    headerCellStyle.setFont(font);
    dateFormatter = new SimpleDateFormat(INTERNAL_DATE_FORMAT);

    sheet = workBook.createSheet(sheetName == null ? DEFAULT_SHEET_NAME : sheetName);
  }

  private static HSSFWorkbook exportListAsExcel(List<String> headers, List<List<Object>> rows,
          String sheetName)
  {
    ExcelExport export;

    export = new ExcelExport(sheetName);

    return export.exportListAsExcel(headers, rows);
  }

  /**
   * Exports a java list of list of object to a stream with Excel content.
   * 
   * @param headers a list of String that is used to fill the 1st line of the Excel sheet
   * @param rows value contents of the sheet
   * @param sheetName name of the Excel worksheet into the Excel file
   * @param outputStream outputStream stream into which the Excel content is written
   * @throws IOException
   */
  public static void exportListAsExcel(List<String> headers, List<List<Object>> rows, String sheetName,
          OutputStream outputStream) throws IOException
  {
    HSSFWorkbook workbook;

    workbook = exportListAsExcel(headers, rows, sheetName);

    write(workbook, outputStream);
  }

  private static void write(HSSFWorkbook workbook, OutputStream outputStream) throws IOException
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
      cell = excelRow.createCell(currentColumn, Cell.CELL_TYPE_NUMERIC);
      cell.setCellValue(((Number) usedCellContent).doubleValue());
      cell.setCellStyle(defaultCellStyle);
    }
    else if (usedCellContent instanceof java.util.Date)
    {
      java.util.Date date = (java.util.Date) usedCellContent;
      String dateString;

      dateString = dateFormatter.format(date);

      cell = excelRow.createCell(currentColumn, Cell.CELL_TYPE_NUMERIC);
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
      cell.setCellValue(new HSSFRichTextString(usedCellContent == null ? "" : usedCellContent.toString()));
      cell.setCellStyle(defaultCellStyle);
    }
  }

  private void addHeaderCell(int currentColumn, String columnHeader)
  {
    cell = excelRow.createCell(currentColumn);
    cell.setCellValue(new HSSFRichTextString(columnHeader));
    cell.setCellStyle(headerCellStyle);
  }

  private HSSFWorkbook exportListAsExcel(List<String> headers, List<List<Object>> rows)
  {
    int currentColumn;
    short currentRow;
    short columnNumber;

    currentRow = 0;

    columnNumber = 0;
    if (headers != null)
    {
      excelRow = sheet.createRow(currentRow++);
      currentColumn = 0;
      for (String columnHeader : headers)
      {
        addHeaderCell(currentColumn++, columnHeader);
      }
      columnNumber = (short) currentColumn;
    }

    if (rows != null)
    {
      for (Iterable<Object> row : rows)
      {
        excelRow = sheet.createRow(currentRow++);
        currentColumn = 0;
        for (Object cellContent : row)
        {
          addCell(currentColumn, cellContent);
          currentColumn++;
        }
      }
    }
    for (short i = 0; i < columnNumber; i++)
    {
      sheet.autoSizeColumn(i, false);
    }

    return workBook;
  }
}
