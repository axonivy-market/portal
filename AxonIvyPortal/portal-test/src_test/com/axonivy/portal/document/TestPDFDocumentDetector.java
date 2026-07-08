package com.axonivy.portal.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.document.PDFDocumentDetector;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestPDFDocumentDetector {

  PDFDocumentDetector detector = new PDFDocumentDetector();

  @Test
  void nullStream_isNotSafe() {
    assertThat(detector.isSafe(null)).isFalse();
  }

  @Test
  void cleanPdf_isSafe() throws Exception {
    assertThat(detector.isSafe(minimalPdf())).isTrue();
  }

  @Test
  void pdfWithAcroForm_isNotSafe() throws Exception {
    try (PDDocument doc = new PDDocument()) {
      doc.addPage(new PDPage());
      doc.getDocumentCatalog().getCOSObject().setItem(COSName.ACRO_FORM, new COSDictionary());
      assertThat(detector.isSafe(toStream(doc))).isFalse();
    }
  }

  @Test
  void pdfWithOpenAction_isNotSafe() throws Exception {
    try (PDDocument doc = new PDDocument()) {
      doc.addPage(new PDPage());
      COSDictionary openAction = new COSDictionary();
      openAction.setName(COSName.S, "JavaScript");
      doc.getDocumentCatalog().getCOSObject().setItem(COSName.OPEN_ACTION, openAction);
      assertThat(detector.isSafe(toStream(doc))).isFalse();
    }
  }

  @Test
  void pdfWithEmbeddedFile_isNotSafe() throws Exception {
    try (PDDocument doc = new PDDocument()) {
      doc.addPage(new PDPage());
      // Add a Names dict with an EmbeddedFiles entry containing a non-empty Names array
      COSDictionary embeddedFile = new COSDictionary();
      embeddedFile.setName(COSName.TYPE, "Filespec");

      org.apache.pdfbox.cos.COSArray namesArray = new org.apache.pdfbox.cos.COSArray();
      namesArray.add(new org.apache.pdfbox.cos.COSString("evil.exe"));
      namesArray.add(embeddedFile);

      COSDictionary embeddedFilesTree = new COSDictionary();
      embeddedFilesTree.setItem(COSName.NAMES, namesArray);

      COSDictionary embeddedFilesEntry = new COSDictionary();
      embeddedFilesEntry.setItem(COSName.getPDFName("EmbeddedFiles"), embeddedFilesTree);

      doc.getDocumentCatalog().getCOSObject().setItem(COSName.NAMES, embeddedFilesEntry);
      assertThat(detector.isSafe(toStream(doc))).isFalse();
    }
  }

  private InputStream minimalPdf() throws Exception {
    try (PDDocument doc = new PDDocument()) {
      doc.addPage(new PDPage());
      return toStream(doc);
    }
  }

  private InputStream toStream(PDDocument doc) throws Exception {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    doc.save(bos);
    return new ByteArrayInputStream(bos.toByteArray());
  }
}
