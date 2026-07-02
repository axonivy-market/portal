package com.axonivy.portal.document;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.axonivy.portal.components.document.ExcelDocumentDetector;
import com.axonivy.portal.components.document.PDFDocumentDetector;
import com.axonivy.portal.components.document.SVGSecurityScanner;
import com.axonivy.portal.components.document.WordDocumentDetector;

import ch.ivy.addon.portalkit.document.DocumentDetectorFactory;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestDocumentDetectorFactory {

  DocumentDetectorFactory factory = new DocumentDetectorFactory();

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"", "txt", "png", "ppt", "zip", "csv"})
  void returnsNull_forUnsupportedExtension(String extension) {
    assertThat(factory.getDocumentDetector(extension)).isNull();
  }

  @ParameterizedTest
  @ValueSource(strings = {"pdf"})
  void returnsPDFDetector(String extension) {
    assertThat(factory.getDocumentDetector(extension)).isInstanceOf(PDFDocumentDetector.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"xls", "xlsx", "xlsm", "xlsb", "xlt", "xltm"})
  void returnsExcelDetector(String extension) {
    assertThat(factory.getDocumentDetector(extension)).isInstanceOf(ExcelDocumentDetector.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"doc", "docx", "docm", "dot", "dotm", "dotx"})
  void returnsWordDetector(String extension) {
    assertThat(factory.getDocumentDetector(extension)).isInstanceOf(WordDocumentDetector.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"svg"})
  void returnsSVGScanner(String extension) {
    assertThat(factory.getDocumentDetector(extension)).isInstanceOf(SVGSecurityScanner.class);
  }
}
