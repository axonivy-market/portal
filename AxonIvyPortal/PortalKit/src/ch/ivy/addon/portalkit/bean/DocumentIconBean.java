package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.document.DocumentExtensionConstants.ALLOWED_EXCEL_FORMAT;
import static ch.ivy.addon.portalkit.document.DocumentExtensionConstants.ALLOWED_PDF_FORMAT;
import static ch.ivy.addon.portalkit.document.DocumentExtensionConstants.ALLOWED_WORD_FORMAT;
import static ch.ivy.addon.portalkit.document.DocumentExtensionConstants.ARCHIVE_FORMAT;
import static ch.ivy.addon.portalkit.document.DocumentExtensionConstants.CSV_FORMAT;
import static ch.ivy.addon.portalkit.document.DocumentExtensionConstants.EMAIL_FORMAT;
import static ch.ivy.addon.portalkit.document.DocumentExtensionConstants.IMAGE_FORMAT;
import static ch.ivy.addon.portalkit.document.DocumentExtensionConstants.POWER_POINT_FORMAT;
import static ch.ivy.addon.portalkit.document.DocumentExtensionConstants.TEXT_FORMAT;
import static ch.ivy.addon.portalkit.document.DocumentExtensionConstants.XML_FORMAT;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

@ManagedBean
@SessionScoped
public class DocumentIconBean {
  /**
   * DocumentIconBean detects icon class base on Font Awesome 4.7.0 library.
   * If upgrade to different version, consider to change
   */

  // Default format for icon class of Font Awesome 4.7.0
  private static final String PREFIX = "fa fa-file-";
  private static final String SUFFIX = "-o";

  // Known extensions
  private static final String WORD = "word";
  private static final String EXCEL = "excel";
  private static final String PDF = "pdf";
  private static final String ARCHIVE = "archive";
  private static final String IMAGE = "image";
  private static final String POWER_POINT = "powerpoint";
  private static final String TEXT = "text";
  private static final String XML = "code";
  private static final String EMAIL = "fa fa-envelope-o";
  private static final String DEFAULT = "fa fa-file-o";

  /**
   * Base on extension of document, getIconCssClass method detects a corresponding Font Awesome icon.
   * Note: Font Awesome version is 4.7.0
   * 
   * @param documentName is a name of file
   * @return return css class for icon
   */
  public static String getIconCssClass(String documentName) {
    String iconClass = StringUtils.EMPTY;
    if (StringUtils.isNotEmpty(documentName)) {
      String fileExtension = FilenameUtils.getExtension(StringUtils.lowerCase(documentName));
      if (ALLOWED_WORD_FORMAT.contains(fileExtension)) {
        iconClass = WORD;
      } else if (ALLOWED_EXCEL_FORMAT.contains(fileExtension) || CSV_FORMAT.contains(fileExtension)) {
        iconClass = EXCEL;
      } else if (ALLOWED_PDF_FORMAT.contains(fileExtension)) {
        iconClass = PDF;
      } else if (ARCHIVE_FORMAT.contains(fileExtension)) {
        iconClass = ARCHIVE;
      } else if (IMAGE_FORMAT.contains(fileExtension)) {
        iconClass = IMAGE;
      } else if (POWER_POINT_FORMAT.contains(fileExtension)) {
        iconClass = POWER_POINT;
      } else if (TEXT_FORMAT.contains(fileExtension)) {
        iconClass = TEXT;
      } else if (XML_FORMAT.contains(fileExtension)) {
        iconClass = XML;
      } else if (EMAIL_FORMAT.contains(fileExtension)) {
        return EMAIL;
      } else {
        return DEFAULT;
      }
      return PREFIX + iconClass + SUFFIX;
    }
    return iconClass;
  }

}
