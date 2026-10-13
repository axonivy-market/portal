package com.axonivy.portal.components.document;

import java.io.IOException;
import java.io.InputStream;

import com.itextpdf.text.exceptions.BadPasswordException;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;

import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.environment.Ivy;

public class PDFDocumentDetector implements DocumentDetector {

  @Override
  public boolean isSafe(InputStream inputStream) {
    if (inputStream == null) {
      return false;
    }
    PdfReader reader = null;
    try {
      reader = new PdfReader(inputStream);

      if (reader.isEncrypted()) {
        Ivy.log().error("This file is encrypted and cannot be scanned for security threats before uploading.");
        BpmError.create("portal:file:encrypted").throwError();
      }

      PdfDictionary catalog = reader.getCatalog();
      return !containsJavaScript(reader, catalog)
          && !containsEmbeddedFiles(catalog)
          && !containsAcroForm(catalog)
          && !containsOpenAction(catalog);
    } catch (BpmError e) {
      throw e;
    } catch (BadPasswordException | NoClassDefFoundError e) {
      Ivy.log().error("This file is encrypted and cannot be scanned for security threats before uploading.", e);
      BpmError.create("portal:file:encrypted").throwError();
    } catch (Exception e) {
      Ivy.log().error("PDF security check failed", e);
      return false;
    } finally {
      if (reader != null) {
        reader.close();
      }
    }
    return false;
  }

  private boolean containsJavaScript(PdfReader reader, PdfDictionary catalog) throws IOException {
    if (hasCatalogLevelJavaScript(reader, catalog)) {
      return true;
    }
    return hasPageOrAnnotationJavaScript(reader);
  }

  private boolean hasCatalogLevelJavaScript(PdfReader reader, PdfDictionary catalog) throws IOException {
    String jsCode = reader.getJavaScript();
    if (jsCode != null && !jsCode.trim().isEmpty()) {
      return true;
    }
    if (catalog == null) {
      return false;
    }
    PdfDictionary names = catalog.getAsDict(PdfName.NAMES);
    boolean hasJavaScriptNameTree = names != null && names.getAsDict(PdfName.JAVASCRIPT) != null;
    boolean hasCatalogJavaScriptActions = hasJavaScriptInAdditionalActions(catalog);
    return hasJavaScriptNameTree || hasCatalogJavaScriptActions;
  }

  private boolean hasPageOrAnnotationJavaScript(PdfReader reader) {
    int totalPages = reader.getNumberOfPages();
    for (int i = 1; i <= totalPages; i++) {
      PdfDictionary page = reader.getPageN(i);
      if (page != null) {
        if (hasJavaScriptInActions(page)) {
          return true;
        }
        PdfArray annots = page.getAsArray(PdfName.ANNOTS);
        if (annots != null) {
          for (int j = 0; j < annots.size(); j++) {
            PdfDictionary annot = annots.getAsDict(j);
            if (annot != null && hasJavaScriptInActions(annot)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  private boolean hasJavaScriptInActions(PdfDictionary dict) {
    if (dict == null) {
      return false;
    }
    if (hasJavaScriptInAdditionalActions(dict)) {
      return true;
    }
    PdfDictionary directAction = dict.getAsDict(PdfName.A);
    return directAction != null && isJavaScriptAction(directAction);
  }

  private boolean hasJavaScriptInAdditionalActions(PdfDictionary dict) {
    if (dict == null) {
      return false;
    }
    PdfDictionary additionalActions = dict.getAsDict(PdfName.AA);
    if (additionalActions == null) {
      return false;
    }
    for (PdfName key : additionalActions.getKeys()) {
      PdfDictionary actionEntry = additionalActions.getAsDict(key);
      if (actionEntry != null && isJavaScriptAction(actionEntry)) {
        return true;
      }
    }
    return false;
  }

  private boolean isJavaScriptAction(PdfDictionary action) {
    if (action == null) {
      return false;
    }
    PdfName actionType = action.getAsName(PdfName.S);
    if (PdfName.JAVASCRIPT.equals(actionType) || PdfName.JS.equals(actionType)) {
      return true;
    }
    PdfDictionary nextDict = action.getAsDict(PdfName.NEXT);
    if (nextDict != null && isJavaScriptAction(nextDict)) {
      return true;
    }
    PdfArray nextArray = action.getAsArray(PdfName.NEXT);
    if (nextArray != null) {
      for (int i = 0; i < nextArray.size(); i++) {
        PdfDictionary nextItem = nextArray.getAsDict(i);
        if (nextItem != null && isJavaScriptAction(nextItem)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean containsEmbeddedFiles(PdfDictionary catalog) {
    if (catalog == null) {
      return false;
    }
    PdfDictionary names = catalog.getAsDict(PdfName.NAMES);
    if (names == null) {
      return false;
    }
    PdfDictionary embeddedFiles = names.getAsDict(PdfName.EMBEDDEDFILES);
    return hasEmbeddedFilesTree(embeddedFiles);
  }

  private boolean hasEmbeddedFilesTree(PdfDictionary node) {
    if (node == null) {
      return false;
    }
    PdfArray namesArray = node.getAsArray(PdfName.NAMES);
    if (namesArray != null && !namesArray.isEmpty()) {
      return true;
    }
    PdfArray kidsArray = node.getAsArray(PdfName.KIDS);
    if (kidsArray != null && !kidsArray.isEmpty()) {
      for (int i = 0; i < kidsArray.size(); i++) {
        PdfDictionary kidNode = kidsArray.getAsDict(i);
        if (hasEmbeddedFilesTree(kidNode)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean containsAcroForm(PdfDictionary catalog) {
    return catalog != null && catalog.getAsDict(PdfName.ACROFORM) != null;
  }

  private boolean containsOpenAction(PdfDictionary catalog) {
    if (catalog == null) {
      return false;
    }
    PdfObject openAction = PdfReader.getPdfObject(catalog.get(PdfName.OPENACTION));
    return openAction != null && openAction.isDictionary();
  }
}
