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

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

@ManagedBean
@SessionScoped
public class DocumentIconBean implements Serializable {

  private static final long serialVersionUID = -6881737983432982774L;
  /**
   * DocumentIconBean detects icon class base on Ivy Icons.
   */

  private static final String FILE_NAME_REGEX = "[\\/:*?\"<>|]";
  private static final String PREFIX = "si si-office-file-";
  private static final String SUFFIX = "-1";

  // Known extensions
  private static final String WORD = "doc";
  private static final String EXCEL = "xls";
  private static final String PDF = "pdf";
  private static final String POWER_POINT = "ppt";
  private static final String TEXT = "txt";
  private static final String CSV = "si si-file-csv";
  private static final String ARCHIVE = "si si-file-zip";
  private static final String XML = "si si-file-xml";
  private static final String IMAGE = "si si-image-file-landscape";
  private static final String EMAIL = "si si-email-action-unread";
  private static final String DEFAULT = "si si-common-file-empty";

  /**
   * Base on extension of document, getIconCssClass method detects a corresponding ivy icon.
   * 
   * @param documentName is a name of file
   * @return return CSS class for icon
   */
  public static String getIconCssClass(String documentName) {
    String iconClass = StringUtils.EMPTY;
    if (StringUtils.isNotEmpty(documentName)) {
      String fileExtension = getExtensionByFileName(documentName);
      if (ALLOWED_WORD_FORMAT.contains(fileExtension)) {
        iconClass = WORD;
      } else if (ALLOWED_EXCEL_FORMAT.contains(fileExtension)) {
        iconClass = EXCEL;
      } else if (ALLOWED_PDF_FORMAT.contains(fileExtension)) {
        iconClass = PDF;
      } else if (POWER_POINT_FORMAT.contains(fileExtension)) {
        iconClass = POWER_POINT;
      } else if (TEXT_FORMAT.contains(fileExtension)) {
        iconClass = TEXT;
      } else if (ARCHIVE_FORMAT.contains(fileExtension)) {
        return ARCHIVE;
      } else if (XML_FORMAT.contains(fileExtension)) {
        return XML;
      } else if (CSV_FORMAT.contains(fileExtension)) {
        return CSV;
      } else if (IMAGE_FORMAT.contains(fileExtension)) {
        return IMAGE;
      } else if (EMAIL_FORMAT.contains(fileExtension)) {
        return EMAIL;
      } else {
        return DEFAULT;
      }
      return PREFIX + iconClass + SUFFIX;
    }
    return iconClass;
  }

  private static String getExtensionByFileName(String documentName) {
    String fileName = StringUtils.trimToEmpty(documentName);
    fileName = RegExUtils.removeAll(fileName, FILE_NAME_REGEX);
    return FilenameUtils.getExtension(StringUtils.lowerCase(fileName));
  }

}
