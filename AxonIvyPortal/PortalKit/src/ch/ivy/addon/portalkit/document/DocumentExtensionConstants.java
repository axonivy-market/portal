package ch.ivy.addon.portalkit.document;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * @deprecated use DocumentExtensionConstants in package com.axonivy.portal.components.document
 *
 */
@Deprecated(since="8.0.27")
public class DocumentExtensionConstants {
  
  public static final List<String> ALLOWED_WORD_FORMAT = ImmutableList.of("doc", "docx", "docm", "dot", "dotm", "dotx");
  public static final List<String> ALLOWED_EXCEL_FORMAT = ImmutableList.of("xls", "xlsx", "xlsm", "xlsb", "xlt", "xltm");
  public static final List<String> ALLOWED_PDF_FORMAT = ImmutableList.of("pdf");
  public static final List<String> DEFAULT_WHITELIST_EXTENSION = ImmutableList.of("doc", "docx", "xls", "xlsx", "xlsm", "csv", "pdf", "ppt", "pptx", "txt" ,"zip", "jpg", "jpeg", "bmp", "png");
  public static final List<String> ARCHIVE_FORMAT = ImmutableList.of("zip", "rar", "7z");
  public static final List<String> IMAGE_FORMAT = ImmutableList.of("jpg", "jpeg", "bmp", "png", "gif");
  public static final List<String> POWER_POINT_FORMAT =  ImmutableList.of("ppt", "pptx");
  public static final List<String> EMAIL_FORMAT =  ImmutableList.of("msg", "eml");
  public static final List<String> TEXT_FORMAT =  ImmutableList.of("txt");
  public static final List<String> XML_FORMAT =  ImmutableList.of("xps", "xml");
  public static final List<String> CSV_FORMAT =  ImmutableList.of("csv");
  
  private DocumentExtensionConstants() {}
  
}
