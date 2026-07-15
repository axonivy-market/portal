package com.axonivy.portal.components.document;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Only the null-input guard is covered here without an Ivy runtime: exercising the
 * actual PDF-parsing branches needs sample PDF fixtures with/without JavaScript,
 * embedded files, AcroForm, or OpenAction, which is a bigger effort left for later.
 */
class TestPDFDocumentDetector {

  private final PDFDocumentDetector detector = new PDFDocumentDetector();

  @Test
  void isSafe_nullInputStream_returnsFalse() {
    assertThat(detector.isSafe(null)).isFalse();
  }
}
