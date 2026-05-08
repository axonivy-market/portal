package com.axonivy.portal.components.document;

import java.io.InputStream;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;

import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.environment.Ivy;

public class PDFDocumentDetector implements DocumentDetector {

  @Override
  public boolean isSafe(InputStream inputStream) {
    if (inputStream == null) {
      return false;
    }
    try (PDDocument document = Loader.loadPDF(inputStream.readAllBytes())) {
      COSDictionary root = document.getDocumentCatalog().getCOSObject();

      if (detectJavaScript(root)) {
        return false;
      }

      COSDictionary names = root.getCOSDictionary(COSName.NAMES);
      COSArray embeddedFiles = getEmbeddedFiles(names);
      boolean hasEmbeddedFiles = embeddedFiles != null && embeddedFiles.size() > 0;
      boolean hasAcroForm = root.getCOSDictionary(COSName.ACRO_FORM) != null;
      boolean hasOpenAction = root.getDictionaryObject(COSName.OPEN_ACTION) != null;

      return !hasEmbeddedFiles && !hasAcroForm && !hasOpenAction;
    } catch (NoClassDefFoundError e) {
      Ivy.log().error("This file is encrypted and cannot be scanned for security threats before uploading.");
      BpmError.create("portal:file:encrypted").throwError();
    } catch (Exception e) {
      Ivy.log().error("PDF security check failed", e);
    }
    return false;
  }

  private boolean detectJavaScript(COSDictionary root) {
    COSDictionary names = root.getCOSDictionary(COSName.NAMES);
    if (names != null && names.getCOSDictionary(COSName.getPDFName("JavaScript")) != null) {
      return true;
    }
    return root.getCOSDictionary(COSName.AA) != null;
  }

  private COSArray getEmbeddedFiles(COSDictionary names) {
    if (names == null) {
      return null;
    }
    COSDictionary embeddedFiles = names.getCOSDictionary(COSName.EMBEDDED_FILES);
    if (embeddedFiles == null) {
      return null;
    }
    return (COSArray) embeddedFiles.getDictionaryObject(COSName.NAMES);
  }
}
