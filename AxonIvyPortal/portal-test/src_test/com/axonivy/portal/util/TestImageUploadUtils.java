package com.axonivy.portal.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.application.app.Application;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestImageUploadUtils {

  private static final String TEST_DIR = ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY;
  private static final byte[] VALID_PNG = new byte[] {
      (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A,
      0x00, 0x00, 0x00, 0x0D, 0x49, 0x48, 0x44, 0x52
  };
  private static final String VALID_PNG_BASE64 = Base64.getEncoder().encodeToString(VALID_PNG);
  private static final String UNSAFE_SVG_BASE64 =
      Base64.getEncoder().encodeToString("<svg><script>alert(1)</script></svg>".getBytes(StandardCharsets.UTF_8));
  private static final String SAFE_SVG_BASE64 =
      Base64.getEncoder().encodeToString("<svg xmlns=\"http://www.w3.org/2000/svg\"><rect width=\"10\" height=\"10\"/></svg>".getBytes(StandardCharsets.UTF_8));

  @AfterEach
  void cleanCmsFolder() {
    folder().ifPresent(f -> {
      for (ContentObject child : f.children()) {
        child.delete();
      }
    });
  }

  @Test
  void imageBase64ToApplicationCMSFile_validPng_writesAndReturnsUri() {
    String uri = ImageUploadUtils.imageBase64ToApplicationCMSFile(VALID_PNG_BASE64, "png", TEST_DIR);
    assertThat(uri).isNotEmpty();
  }

  @Test
  void imageBase64ToApplicationCMSFile_safeSvg_writesAndReturnsUri() {
    String uri = ImageUploadUtils.imageBase64ToApplicationCMSFile(SAFE_SVG_BASE64, "svg", TEST_DIR);
    assertThat(uri).isNotEmpty();
  }

  @Test
  void imageBase64ToApplicationCMSFile_unsafeSvg_returnsEmpty() {
    String uri = ImageUploadUtils.imageBase64ToApplicationCMSFile(UNSAFE_SVG_BASE64, "svg", TEST_DIR);
    assertThat(uri).isEmpty();
  }

  @Test
  void imageBase64ToApplicationCMSFile_invalidExtension_returnsEmpty() {
    String uri = ImageUploadUtils.imageBase64ToApplicationCMSFile(VALID_PNG_BASE64, "exe", TEST_DIR);
    assertThat(uri).isEmpty();
  }

  @Test
  void imageBase64ToApplicationCMSFile_oversized_returnsEmpty() {
    long limit = UploadDocumentUtils.getImageUploadSizeLimit();
    int size = (int) Math.max(limit + 1024, 3 * 1024 * 1024);
    byte[] largeContent = new byte[size];
    System.arraycopy(VALID_PNG, 0, largeContent, 0, VALID_PNG.length);
    String largeBase64 = Base64.getEncoder().encodeToString(largeContent);
    String uri = ImageUploadUtils.imageBase64ToApplicationCMSFile(largeBase64, "png", TEST_DIR);
    assertThat(uri).isEmpty();
  }

  @Test
  void imageToBase64_blankLocation_returnsEmpty() {
    assertThat(ImageUploadUtils.imageToBase64(null, "png", TEST_DIR)).isEmpty();
    assertThat(ImageUploadUtils.imageToBase64("", "png", TEST_DIR)).isEmpty();
  }

  @Test
  void imageToBase64_existingImage_returnsEncodedContent() {
    String uri = ImageUploadUtils.imageBase64ToApplicationCMSFile(VALID_PNG_BASE64, "png", TEST_DIR);
    assertThat(uri).isNotEmpty();

    String encoded = ImageUploadUtils.imageToBase64(uri, "png", TEST_DIR);
    assertThat(encoded).isEqualTo(VALID_PNG_BASE64);
  }

  @Test
  void isValidImageUrl_blank_returnsFalse() {
    assertThat(ImageUploadUtils.isValidImageUrl(null, "png")).isFalse();
    assertThat(ImageUploadUtils.isValidImageUrl("", "png")).isFalse();
  }

  @Test
  void isValidImageUrl_nonExistent_returnsFalse() {
    assertThat(ImageUploadUtils.isValidImageUrl("non_existent_image", "png")).isFalse();
  }

  private static Optional<ContentObject> folder() {
    return ContentManagement.cms(Application.current()).root().child().get(TEST_DIR);
  }
}
