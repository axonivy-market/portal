package com.axonivy.portal.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.document.ExcelDocumentDetector;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestExcelDocumentDetector {

  ExcelDocumentDetector detector = new ExcelDocumentDetector();

  @Test
  void xlsxFile_isSafe() {
    // xlsx is ZIP-based (not OLE2); VBAMacroReader throws IllegalArgumentException → no macros → safe
    byte[] zipMagic = {0x50, 0x4B, 0x03, 0x04, 0x00, 0x00, 0x00, 0x00};
    assertThat(detector.isSafe(new ByteArrayInputStream(zipMagic))).isTrue();
  }

  @Test
  void corruptedStream_isSafe() {
    // Unreadable content: IOException path in hasMacro → returns false → isSafe = true
    byte[] garbage = "not-an-excel-file".getBytes();
    assertThat(detector.isSafe(new ByteArrayInputStream(garbage))).isTrue();
  }

  //TODO:Testing "has macros → not safe" requires a real OLE2 (.xls/.xlsm) file with embedded VBA or ActiveX controls.

}
