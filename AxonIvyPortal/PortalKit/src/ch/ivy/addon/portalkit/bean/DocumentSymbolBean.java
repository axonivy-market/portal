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
public class DocumentSymbolBean {
  /**
   * DocumentSymbolBean detects symbol icon base on Font Awesome 4.7.0. In case, Font Awesome library is older or
   * higher, please consider to change prefix and suffix for icon class can be worked
   */

  // Default format for icon class of Font Awesome 4.7.0
  private static final String PREFIX = "fa fa-file-";
  private static final String SUFFIX = "-o";

  // Known extensions
  private static final String WORD_SYMBOL = "word";
  private static final String EXCEL_SYMBOL = "excel";
  private static final String PDF_SYMBOL = "pdf";
  private static final String ARCHIVE_SYMBOL = "archive";
  private static final String IMAGE_SYMBOL = "image";
  private static final String POWER_POINT = "powerpoint";
  private static final String TEXT_SYMBOL = "text";
  private static final String XML_SUMBOL = "code";
  private static final String EMAIL_SYMBOL = "fa fa-envelope-o";
  private static final String DEFAULT_SYMBOL = "fa fa-file-o";

  /**
   * Base on extensions of document, getSymbol method detects a corresponding Font Awesome icon Note: Font Awesome
   * version is 4.7.0
   * 
   * @param documentName is a name of file
   * @return icon of document
   */
  public static String getSymbol(String documentName) {
    String symbolClass = StringUtils.EMPTY;
    if (StringUtils.isNotEmpty(documentName)) {
      boolean unknownExtension = false;
      String extensionType = FilenameUtils.getExtension(StringUtils.lowerCase(documentName));
      if (ALLOWED_WORD_FORMAT.contains(extensionType)) {
        symbolClass = WORD_SYMBOL;
      } else if (ALLOWED_EXCEL_FORMAT.contains(extensionType) || CSV_FORMAT.contains(extensionType)) {
        symbolClass = EXCEL_SYMBOL;
      } else if (ALLOWED_PDF_FORMAT.contains(extensionType)) {
        symbolClass = PDF_SYMBOL;
      } else if (ARCHIVE_FORMAT.contains(extensionType)) {
        symbolClass = ARCHIVE_SYMBOL;
      } else if (IMAGE_FORMAT.contains(extensionType)) {
        symbolClass = IMAGE_SYMBOL;
      } else if (POWER_POINT_FORMAT.contains(extensionType)) {
        symbolClass = POWER_POINT;
      } else if (TEXT_FORMAT.contains(extensionType)) {
        symbolClass = TEXT_SYMBOL;
      } else if (XML_FORMAT.contains(extensionType)) {
        symbolClass = XML_SUMBOL;
      } else if (EMAIL_FORMAT.contains(extensionType)) {
        unknownExtension = true;
        symbolClass = EMAIL_SYMBOL;
      } else {
        unknownExtension = true;
        symbolClass = DEFAULT_SYMBOL;
      }

      if (!unknownExtension) {
        symbolClass = PREFIX.concat(symbolClass).concat(SUFFIX);
      }
    }
    return symbolClass;
  }

}
