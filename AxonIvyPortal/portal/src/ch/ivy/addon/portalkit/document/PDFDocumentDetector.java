package ch.ivy.addon.portalkit.document;

import java.io.InputStream;

import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;

import ch.ivyteam.ivy.environment.Ivy;

public class PDFDocumentDetector implements DocumentDetector {

  @Override
  public boolean isSafe(InputStream inputStream) {
    boolean safeState = false;
    PdfReader reader = null;
    try {
      if (inputStream != null) {
        reader = new PdfReader(inputStream);

        // Check 1: Detect JavaScript execution
        String jsCode = reader.getJavaScript();
        if (jsCode == null || jsCode.trim().isEmpty()) {

          // Check 2: Detect embedded files
          PdfDictionary root = reader.getCatalog();
          PdfDictionary names = root.getAsDict(PdfName.NAMES);
          PdfArray namesArray = checkEmbeddedFilesInPDF(names);

          // Check 3: Detect AcroForms (forms can trigger JavaScript)
          PdfDictionary acroForm = root.getAsDict(PdfName.ACROFORM);
          boolean hasAcroForm = (acroForm != null);

          // Check 4: Detect OpenAction (auto-trigger scripts)
          PdfDictionary openAction = root.getAsDict(PdfName.OPENACTION);
          boolean hasOpenAction = (openAction != null);

          // Safe if no JS, no embedded files, no AcroForms, and no OpenAction
          safeState = (namesArray == null || namesArray.isEmpty()) && !hasAcroForm && !hasOpenAction;
        }
      }
    } catch (Exception e) {
      Ivy.log().error("PDF security check failed", e);
      safeState = false;
    } finally {
      if (reader != null) {
        reader.close();
      }
    }
    return safeState;
  }

  private PdfArray checkEmbeddedFilesInPDF(PdfDictionary names) {
    PdfArray namesArr = null;
    if (names != null) {
      PdfDictionary embeddedFiles = names.getAsDict(PdfName.EMBEDDEDFILES);
      if (embeddedFiles != null) {
        namesArr = embeddedFiles.getAsArray(PdfName.NAMES);
      }
    }
    return namesArr;
  }
}
