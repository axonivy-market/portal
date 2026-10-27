package com.axonivy.portal.components.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestImageDocumentDetector {

  private static final byte[] VALID_PNG_BYTES = new byte[] {
      (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A,
      0x00, 0x00, 0x00, 0x0D, 0x49, 0x48, 0x44, 0x52
  };

  private static final byte[] VALID_JPEG_BYTES = new byte[] {
      (byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0,
      0x00, 0x10, 0x4A, 0x46, 0x49, 0x46
  };

  private static final String SAFE_SVG =
      "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"100\" height=\"100\"><rect width=\"100\" height=\"100\"/></svg>";

  private static final String SCRIPT_SVG =
      "<svg xmlns=\"http://www.w3.org/2000/svg\"><script>alert('xss')</script></svg>";

  private final ImageDocumentDetector detector = new ImageDocumentDetector();

  @ParameterizedTest
  @ValueSource(strings = {"png", "PNG", "jpg", "JPG", "jpeg", "JPEG", "svg", "SVG"})
  void isAllowedImageExtension_validExtensions_returnsTrue(String extension) {
    assertThat(ImageDocumentDetector.isAllowedImageExtension(extension)).isTrue();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"gif", "bmp", "webp", "pdf", "exe", "html", "js", "sh", "txt"})
  void isAllowedImageExtension_invalidExtensions_returnsFalse(String extension) {
    assertThat(ImageDocumentDetector.isAllowedImageExtension(extension)).isFalse();
  }

  @Test
  void isImageSafe_validPng_returnsTrue() {
    assertThat(ImageDocumentDetector.isImageSafe(VALID_PNG_BYTES)).isTrue();
  }

  @Test
  void isImageSafe_validJpeg_returnsTrue() {
    assertThat(ImageDocumentDetector.isImageSafe(VALID_JPEG_BYTES)).isTrue();
  }

  @Test
  void isImageSafe_validSvg_returnsTrue() {
    byte[] svgBytes = SAFE_SVG.getBytes(StandardCharsets.UTF_8);
    assertThat(ImageDocumentDetector.isImageSafe(svgBytes)).isTrue();
  }

  @Test
  void isImageSafe_nullOrEmptyData_returnsFalse() {
    assertThat(ImageDocumentDetector.isImageSafe((byte[]) null)).isFalse();
    assertThat(ImageDocumentDetector.isImageSafe((java.io.InputStream) null)).isFalse();
    assertThat(ImageDocumentDetector.isImageSafe(new byte[0])).isFalse();
  }

  @Test
  void isImageSafe_pngWrongMagicBytes_returnsFalse() {
    byte[] badPng = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
    assertThat(ImageDocumentDetector.isImageSafe(badPng)).isFalse();
  }

  @Test
  void isImageSafe_jpegWrongMagicBytes_returnsFalse() {
    byte[] badJpeg = new byte[] {0x00, 0x01, 0x02, 0x03};
    assertThat(ImageDocumentDetector.isImageSafe(badJpeg)).isFalse();
  }

  @Test
  void isImageSafe_pngContainingScriptTag_returnsFalse() {
    byte[] scriptBytes = "<script>alert(1)</script>".getBytes(StandardCharsets.UTF_8);
    byte[] merged = new byte[VALID_PNG_BYTES.length + scriptBytes.length];
    System.arraycopy(VALID_PNG_BYTES, 0, merged, 0, VALID_PNG_BYTES.length);
    System.arraycopy(scriptBytes, 0, merged, VALID_PNG_BYTES.length, scriptBytes.length);

    assertThat(ImageDocumentDetector.isImageSafe(merged)).isFalse();
  }

  @Test
  void isImageSafe_jpegContainingJavascriptUri_returnsFalse() {
    byte[] scriptBytes = "javascript:alert(1)".getBytes(StandardCharsets.UTF_8);
    byte[] merged = new byte[VALID_JPEG_BYTES.length + scriptBytes.length];
    System.arraycopy(VALID_JPEG_BYTES, 0, merged, 0, VALID_JPEG_BYTES.length);
    System.arraycopy(scriptBytes, 0, merged, VALID_JPEG_BYTES.length, scriptBytes.length);

    assertThat(ImageDocumentDetector.isImageSafe(merged)).isFalse();
  }

  @Test
  void isImageSafe_svgContainingScript_returnsFalse() {
    byte[] svgBytes = SCRIPT_SVG.getBytes(StandardCharsets.UTF_8);
    assertThat(ImageDocumentDetector.isImageSafe(svgBytes)).isFalse();
  }

  @Test
  void isSafe_streamValidPng_returnsTrue() throws IOException {
    try (ByteArrayInputStream in = new ByteArrayInputStream(VALID_PNG_BYTES)) {
      assertThat(detector.isSafe(in)).isTrue();
    }
  }

  @Test
  void isSafe_streamValidJpeg_returnsTrue() throws IOException {
    try (ByteArrayInputStream in = new ByteArrayInputStream(VALID_JPEG_BYTES)) {
      assertThat(detector.isSafe(in)).isTrue();
    }
  }

  @Test
  void isSafe_streamValidSvg_returnsTrue() throws IOException {
    try (ByteArrayInputStream in = new ByteArrayInputStream(SAFE_SVG.getBytes(StandardCharsets.UTF_8))) {
      assertThat(detector.isSafe(in)).isTrue();
    }
  }

  @Test
  void isSafe_streamNull_returnsFalse() {
    assertThat(detector.isSafe(null)).isFalse();
  }

  @Test
  void isSafe_streamInvalidContent_returnsFalse() throws IOException {
    try (ByteArrayInputStream in = new ByteArrayInputStream("random plain text".getBytes(StandardCharsets.UTF_8))) {
      assertThat(detector.isSafe(in)).isFalse();
    }
  }

  @Test
  void isImageSafe_bytesWithoutExtension_detectsValidImages() {
    assertThat(ImageDocumentDetector.isImageSafe(VALID_PNG_BYTES)).isTrue();
    assertThat(ImageDocumentDetector.isImageSafe(VALID_JPEG_BYTES)).isTrue();
    byte[] svgBytes = SAFE_SVG.getBytes(StandardCharsets.UTF_8);
    assertThat(ImageDocumentDetector.isImageSafe(svgBytes)).isTrue();
  }

  @Test
  void isImageSafe_bytesWithoutExtension_rejectsInvalidContent() {
    byte[] badBytes = new byte[] {0x00, 0x01, 0x02, 0x03};
    assertThat(ImageDocumentDetector.isImageSafe(badBytes)).isFalse();
    byte[] plainText = "plain text not an image".getBytes(StandardCharsets.UTF_8);
    assertThat(ImageDocumentDetector.isImageSafe(plainText)).isFalse();
  }

  @Test
  void isImageSafe_nonSvgXml_returnsFalse() {
    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><document><title>Not SVG</title></document>";
    byte[] xmlBytes = xml.getBytes(StandardCharsets.UTF_8);
    assertThat(ImageDocumentDetector.isImageSafe(xmlBytes)).isFalse();

    String xmlWithSvgComment = "<document><!-- <svg/> --></document>";
    assertThat(ImageDocumentDetector.isImageSafe(xmlWithSvgComment.getBytes(StandardCharsets.UTF_8))).isFalse();
  }

  @Test
  void isImageSafe_prefixedSvg_returnsTrue() {
    String prefixedSvg = "<s:svg xmlns:s=\"http://www.w3.org/2000/svg\" width=\"50\" height=\"50\"><rect width=\"50\" height=\"50\"/></s:svg>";
    byte[] svgBytes = prefixedSvg.getBytes(StandardCharsets.UTF_8);
    assertThat(ImageDocumentDetector.isImageSafe(svgBytes)).isTrue();
  }

  @Test
  void isImageSafe_svgWithLongLeadingComment_returnsTrue() {
    String longComment = "<!-- " + "A".repeat(2048) + " -->\n" + SAFE_SVG;
    byte[] svgBytes = longComment.getBytes(StandardCharsets.UTF_8);
    assertThat(ImageDocumentDetector.isImageSafe(svgBytes)).isTrue();
  }
}
