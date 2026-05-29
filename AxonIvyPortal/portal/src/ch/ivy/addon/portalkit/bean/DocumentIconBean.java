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

  // Known extensions
  private static final String WORD = "ti ti-file-type-doc";
  private static final String EXCEL = "ti ti-file-type-xls";
  private static final String PDF = "ti ti-file-type-pdf";
  private static final String POWER_POINT = "ti ti-file-type-ppt";
  private static final String TEXT = "ti ti-file-type-txt";
  private static final String CSV = "ti ti-file-type-csv";
  private static final String ARCHIVE = "ti ti-file-type-zip";
  private static final String XML = "ti ti-file-type-xml";
  private static final String IMAGE = "ti ti-photo";
  private static final String EMAIL = "ti ti-mail";
  private static final String DEFAULT = "ti ti-file";

  /**
   * Base on extension of document, getIconCssClass method detects a corresponding ivy icon.
   * 
   * @param documentName is a name of file
   * @return return CSS class for icon
   */
  public String getIconCssClass(String documentName) {
    String iconClass = StringUtils.EMPTY;
    if (StringUtils.isNotEmpty(documentName)) {
      String fileExtension = getExtensionByFileName(documentName);
      if (ALLOWED_WORD_FORMAT.contains(fileExtension)) {
        return WORD;
      } else if (ALLOWED_EXCEL_FORMAT.contains(fileExtension)) {
        return EXCEL;
      } else if (ALLOWED_PDF_FORMAT.contains(fileExtension)) {
        return PDF;
      } else if (POWER_POINT_FORMAT.contains(fileExtension)) {
        return POWER_POINT;
      } else if (TEXT_FORMAT.contains(fileExtension)) {
        return TEXT;
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
    }
    return iconClass;
  }

  private String getExtensionByFileName(String documentName) {
    String fileName = StringUtils.trimToEmpty(documentName);
    fileName = RegExUtils.removeAll(fileName, FILE_NAME_REGEX);
    return FilenameUtils.getExtension(StringUtils.lowerCase(fileName));
  }

}
