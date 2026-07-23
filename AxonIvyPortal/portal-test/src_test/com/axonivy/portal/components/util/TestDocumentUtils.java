package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.ivydata.bo.IvyDocument;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * No DocFactory sub process is registered in this test environment, so
 * {@code isSupportedPreviewType} falls back to the static supported-extension list.
 */
@IvyTest
class TestDocumentUtils {

  @Test
  void isImageDocument_nullDocument_returnsFalse() {
    assertThat(DocumentUtils.isImageDocument(null)).isFalse();
  }

  @Test
  void isImageDocument_imageContentType_returnsTrue() {
    IvyDocument document = IvyDocument.builder().contentType("image/png").create();
    assertThat(DocumentUtils.isImageDocument(document)).isTrue();
  }

  @Test
  void isImageDocument_nonImageContentType_returnsFalse() {
    IvyDocument document = IvyDocument.builder().contentType("application/pdf").create();
    assertThat(DocumentUtils.isImageDocument(document)).isFalse();
  }

  @Test
  void isSupportedPreviewType_pdfExtension_returnsTrue() {
    IvyDocument document = IvyDocument.builder().path("report.pdf").create();
    assertThat(DocumentUtils.isSupportedPreviewType(document)).isTrue();
  }

  @Test
  void isSupportedPreviewType_unsupportedExtension_returnsFalse() {
    IvyDocument document = IvyDocument.builder().path("archive.zip").create();
    assertThat(DocumentUtils.isSupportedPreviewType(document)).isFalse();
  }
}
