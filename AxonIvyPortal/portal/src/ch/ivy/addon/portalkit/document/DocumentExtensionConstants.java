package ch.ivy.addon.portalkit.document;

import java.util.List;

public class DocumentExtensionConstants {
  
  public static final List<String> ALLOWED_WORD_FORMAT = List.of("doc", "docx", "docm", "dot", "dotm", "dotx");
  public static final List<String> ALLOWED_EXCEL_FORMAT = List.of("xls", "xlsx", "xlsm", "xlsb", "xlt", "xltm");
  public static final List<String> ALLOWED_PDF_FORMAT = List.of("pdf");
  public static final List<String> ALLOWED_SVG_FORMAT = List.of("svg");
  public static final List<String> DEFAULT_WHITELIST_EXTENSION = List.of("doc", "docx", "xls", "xlsx", "xlsm", "csv", "pdf", "ppt", "pptx", "txt" ,"zip", "jpg", "jpeg", "bmp", "png");
  public static final List<String> ARCHIVE_FORMAT = List.of("zip", "rar", "7z");
  public static final List<String> IMAGE_FORMAT = List.of("jpg", "jpeg", "bmp", "png", "gif");
  public static final List<String> POWER_POINT_FORMAT =  List.of("ppt", "pptx");
  public static final List<String> EMAIL_FORMAT =  List.of("msg", "eml");
  public static final List<String> TEXT_FORMAT =  List.of("txt");
  public static final List<String> XML_FORMAT =  List.of("xps", "xml");
  public static final List<String> CSV_FORMAT =  List.of("csv");
  
  private DocumentExtensionConstants() {}
  
}
