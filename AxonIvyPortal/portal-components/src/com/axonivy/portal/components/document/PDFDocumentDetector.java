package com.axonivy.portal.components.document;

import java.io.InputStream;

import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;

import ch.ivyteam.ivy.environment.Ivy;

public class PDFDocumentDetector implements DocumentDetector{
  
  @Override
  public boolean isSafe(InputStream inputStream) {
    boolean safeState = false;
    try {
      if (inputStream != null) {
        // Load stream in PDF parser
        // If the stream is not a PDF then exception will be throwed
        // here and safe state will be set to FALSE
        PdfReader reader = new PdfReader(inputStream);
        // Check 1:
        // Detect if the document contains any JavaScript code
        String jsCode = reader.getJavaScript();
        if (jsCode == null) {
          // OK no JS code then when pass to check 2:
          // Detect if the document has any embedded files
          PdfDictionary root = reader.getCatalog();
          PdfDictionary names = root.getAsDict(PdfName.NAMES);
          PdfArray namesArray = null;
          namesArray = checkEmbeddedFilesInPDF(names);
          // Get safe state from number of embedded files
          safeState = ((namesArray == null) || namesArray.isEmpty());
        }
      }
    } catch (Exception e) {
      Ivy.log().error(e);
      safeState = false;
    }
    return safeState;
  }

  private PdfArray checkEmbeddedFilesInPDF(PdfDictionary names) {
    PdfArray namesArr = null;
    if (names != null) {
      PdfDictionary embeddedFiles = names.getAsDict(PdfName.EMBEDDEDFILES);
      namesArr = embeddedFiles.getAsArray(PdfName.NAMES);
    }
    return namesArr;
  }
}
