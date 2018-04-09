package ch.ivy.addon.portalkit.document;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class DocumentExtensionConstants {
  
  public static final List<String> ALLOWED_WORD_FORMAT = ImmutableList.of("doc", "docx", "docm", "dot", "dotm", "dotx");
  public static final List<String> ALLOWED_EXCEL_FORMAT = ImmutableList.of("xls", "xlsx", "xlsm", "xlsb", "xlt", "xltm");
  public static final List<String> ALLOWED_PDF_FORMAT = ImmutableList.of("pdf");
  public static final List<String> DEFAULT_WHITELIST_EXTENSION = ImmutableList.of("doc", "docx", "xls", "xlsx", "xlsm", "csv", "pdf", "ppt", "pptx", "txt" ,"zip", "jpg", "jpeg", "bmp", "png");
  
  private DocumentExtensionConstants() {}
}
