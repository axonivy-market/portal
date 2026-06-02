package com.axonivy.portal.components.document;

import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;

import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.environment.Ivy;

public class PDFDocumentDetector implements DocumentDetector {

  @Override
  public boolean isSafe(InputStream inputStream) {
    if (inputStream == null) {
      return false;
    }
    try (PDDocument document = Loader.loadPDF(inputStream.readAllBytes())) {
      return !containsJavaScript(document)
          && !containsEmbeddedFiles(document)
          && !containsAcroForm(document)
          && !containsOpenAction(document);
    } catch (InvalidPasswordException e) {
      Ivy.log().error("This file is encrypted and cannot be scanned for security threats before uploading.", e);
      BpmError.create("portal:file:encrypted").throwError();
    } catch (Exception e) {
      Ivy.log().error("PDF security check failed", e);
    }
    return false;
  }

  private boolean containsJavaScript(PDDocument document) throws IOException {
    if (hasCatalogLevelJavaScript(document)) {
      return true;
    }
    return hasPageOrAnnotationJavaScript(document);
  }

  private boolean hasCatalogLevelJavaScript(PDDocument document) {
    COSDictionary catalog = document.getDocumentCatalog().getCOSObject();
    COSDictionary names = catalog.getCOSDictionary(COSName.NAMES);
    boolean hasJavaScriptNameTree = names != null && names.getCOSDictionary(COSName.JAVA_SCRIPT) != null;
    boolean hasCatalogAdditionalActions = catalog.getCOSDictionary(COSName.AA) != null;
    return hasJavaScriptNameTree || hasCatalogAdditionalActions;
  }

  private boolean hasPageOrAnnotationJavaScript(PDDocument document) throws IOException {
    for (PDPage page : document.getPages()) {
      if (hasJavaScriptInActions(page.getCOSObject())) {
        return true;
      }
      for (PDAnnotation annotation : page.getAnnotations()) {
        if (hasJavaScriptInActions(annotation.getCOSObject())) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean hasJavaScriptInActions(COSDictionary cosObject) {
    if (hasJavaScriptInAdditionalActions(cosObject)) {
      return true;
    }
    COSDictionary directAction = cosObject.getCOSDictionary(COSName.A);
    return directAction != null && isJavaScriptAction(directAction);
  }

  private boolean hasJavaScriptInAdditionalActions(COSDictionary cosObject) {
    COSDictionary additionalActions = cosObject.getCOSDictionary(COSName.AA);
    if (additionalActions == null) {
      return false;
    }
    for (COSName key : additionalActions.keySet()) {
      COSBase actionEntry = additionalActions.getDictionaryObject(key);
      if (actionEntry instanceof COSDictionary && isJavaScriptAction((COSDictionary) actionEntry)) {
        return true;
      }
    }
    return false;
  }

  private boolean isJavaScriptAction(COSDictionary action) {
    COSBase actionType = action.getDictionaryObject(COSName.S);
    return COSName.JAVA_SCRIPT.equals(actionType);
  }

  private boolean containsEmbeddedFiles(PDDocument document) {
    PDDocumentNameDictionary namesDictionary = document.getDocumentCatalog().getNames();
    if (namesDictionary == null) {
      return false;
    }
    PDEmbeddedFilesNameTreeNode embeddedFilesTree = namesDictionary.getEmbeddedFiles();
    if (embeddedFilesTree == null) {
      return false;
    }
    COSDictionary treeNode = embeddedFilesTree.getCOSObject();
    return hasNonEmptyArray(treeNode, COSName.NAMES) || hasNonEmptyArray(treeNode, COSName.KIDS);
  }

  private boolean hasNonEmptyArray(COSDictionary dictionary, COSName key) {
    COSBase value = dictionary.getDictionaryObject(key);
    return value instanceof COSArray && ((COSArray) value).size() > 0;
  }

  private boolean containsAcroForm(PDDocument document) {
    COSDictionary catalog = document.getDocumentCatalog().getCOSObject();
    return catalog.getCOSDictionary(COSName.ACRO_FORM) != null;
  }

  private boolean containsOpenAction(PDDocument document) {
    COSDictionary catalog = document.getDocumentCatalog().getCOSObject();
    COSBase openAction = catalog.getDictionaryObject(COSName.OPEN_ACTION);
    return openAction instanceof COSDictionary;
  }
}
