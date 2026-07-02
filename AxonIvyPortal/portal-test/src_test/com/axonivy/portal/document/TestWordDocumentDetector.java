package com.axonivy.portal.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.document.WordDocumentDetector;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestWordDocumentDetector {

  WordDocumentDetector detector = new WordDocumentDetector();

  @Test
  void docxFile_isSafe() {
    // docx is ZIP-based (not OLE2); POIFSFileSystem throws OfficeXmlFileException
    // (IllegalArgumentException) → containsMacrosOrActiveX = false → safe
    byte[] zipMagic = {0x50, 0x4B, 0x03, 0x04, 0x00, 0x00, 0x00, 0x00};
    assertThat(detector.isSafe(new ByteArrayInputStream(zipMagic))).isTrue();
  }

  @Test
  void corruptedStream_isSafe() {
    // Unreadable content: IOException path → containsMacrosOrActiveX = false → safe
    byte[] garbage = "not-a-word-file".getBytes();
    assertThat(detector.isSafe(new ByteArrayInputStream(garbage))).isTrue();
  }

  @Test
  void docWithMacros_isNotSafe() throws Exception {
    try (InputStream in = TestWordDocumentDetector.class.getResourceAsStream("bad.doc")) {
      assertThat(detector.isSafe(in)).isFalse();
    }
  }
}
