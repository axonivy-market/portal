package ch.ivy.addon.portalkit.document;

import org.apache.commons.lang.StringUtils;

public class DocumentDetectorFactory {
  
  public DocumentDetector getDocumentDetector(String fileExtension) {
    if(fileExtension == null || StringUtils.EMPTY.equals(fileExtension)){
      return null;
    }
    if(DocumentExtensionConstants.ALLOWED_EXCEL_FORMAT.contains(fileExtension)) {
      return new ExcelDocumentDetector();
    }
    else if(DocumentExtensionConstants.ALLOWED_WORD_FORMAT.contains(fileExtension)) {
      return new WordDocumentDetector();
    }
    else if(DocumentExtensionConstants.ALLOWED_PDF_FORMAT.contains(fileExtension)) {
      return new PDFDocumentDetector();
    }
    return null;
  }
}
