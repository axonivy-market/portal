package com.axonivy.portal.components.document;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TestDocumentDetectorFactory {

  private final DocumentDetectorFactory factory = new DocumentDetectorFactory();

  @ParameterizedTest
  @NullAndEmptySource
  void getDocumentDetector_blankExtension_returnsNull(String value) {
    assertThat(factory.getDocumentDetector(value)).isNull();
  }

  @ParameterizedTest
  @ValueSource(strings = { "xls", "xlsx", "xlsm" })
  void getDocumentDetector_excelExtension_returnsExcelDetector(String value) {
    assertThat(factory.getDocumentDetector(value)).isInstanceOf(ExcelDocumentDetector.class);
  }

  @ParameterizedTest
  @ValueSource(strings = { "doc", "docx", "dotm" })
  void getDocumentDetector_wordExtension_returnsWordDetector(String value) {
    assertThat(factory.getDocumentDetector(value)).isInstanceOf(WordDocumentDetector.class);
  }

  @Test
  void getDocumentDetector_pdfExtension_returnsPdfDetector() {
    assertThat(factory.getDocumentDetector("pdf")).isInstanceOf(PDFDocumentDetector.class);
  }

  @Test
  void getDocumentDetector_svgExtension_returnsSvgSecurityScanner() {
    assertThat(factory.getDocumentDetector("svg")).isInstanceOf(SVGSecurityScanner.class);
  }

  @ParameterizedTest
  @ValueSource(strings = { "txt", "zip", "png", "unknown" })
  void getDocumentDetector_unsupportedExtension_returnsNull(String value) {
    assertThat(factory.getDocumentDetector(value)).isNull();
  }
}
