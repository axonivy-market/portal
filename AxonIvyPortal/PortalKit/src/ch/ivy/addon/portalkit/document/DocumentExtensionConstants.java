package ch.ivy.addon.portalkit.document;

import java.util.Arrays;
import java.util.List;

public class DocumentExtensionConstants {
  public static final List<String> ALLOWED_WORD_FORMAT = Arrays.asList(new String[] { "doc", "docx", "docm", "dot", "dotm", "dotx" });
  public static final List<String> ALLOWED_EXCEL_FORMAT = Arrays.asList(new String[] { "xls", "xlsx", "xlsm", "xlsb", "xlt", "xltm" });
  public static final List<String> ALLOWED_PDF_FORMAT = Arrays.asList(new String[] { "pdf" });
  public static final List<String> DEFAULT_WHITELIST_EXTENSION = Arrays.asList(new String[] { "doc", "docx", "xls", "xlsx", "xlsm", "csv", "pdf", "ppt", "pptx", "txt" ,"zip", "jpg", "jpeg", "bmp", "png" });
}
