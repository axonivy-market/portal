package ch.ivy.addon.portalkit.bo;

import java.util.List;

public class ExcelExportSheet {
  
  List<String> headers;
  List<List<Object>> rows;
  String sheetName;
  
  public List<String> getHeaders() {
    return headers;
  }
  
  public void setHeaders(List<String> headers) {
    this.headers = headers;
  }
  
  public List<List<Object>> getRows() {
    return rows;
  }
  
  public void setRows(List<List<Object>> rows) {
    this.rows = rows;
  }
  
  public String getSheetName() {
    return sheetName;
  }
  
  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }
  
}
