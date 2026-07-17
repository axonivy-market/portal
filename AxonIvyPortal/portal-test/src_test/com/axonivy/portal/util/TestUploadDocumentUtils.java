package com.axonivy.portal.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestUploadDocumentUtils {

  private static final String SAFE_SVG = "<svg xmlns=\"http://www.w3.org/2000/svg\"><rect width=\"10\" height=\"10\"/></svg>";
  private static final String UNSAFE_SVG = "<svg xmlns=\"http://www.w3.org/2000/svg\"><script>alert(1)</script></svg>";

  @BeforeEach
  void resetGlobalVariables() {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getDefaultValue());
    Ivy.var().set(GlobalVariable.DOCUMENT_UPLOAD_SIZE_LIMIT.getKey(), GlobalVariable.DOCUMENT_UPLOAD_SIZE_LIMIT.getDefaultValue());
    Ivy.var().set(GlobalVariable.ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT.getKey(),
        GlobalVariable.ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT.getDefaultValue());
    Ivy.var().set(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getKey(),
        GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getDefaultValue());
  }

  private static UploadedFile fakeFile(String fileName, byte[] content) {
    return new UploadedFile() {
      @Override
      public String getFileName() {
        return fileName;
      }

      @Override
      public InputStream getInputStream() {
        return new ByteArrayInputStream(content);
      }

      @Override
      public byte[] getContent() {
        return content;
      }

      @Override
      public String getContentType() {
        return "application/octet-stream";
      }

      @Override
      public long getSize() {
        return content == null ? 0 : content.length;
      }

      @Override
      public void write(String targetFilePath) throws Exception {
        // not needed for tests
      }

      @Override
      public void delete() throws IOException {
        // not needed for tests
      }
    };
  }

  private static byte[] buildMinimalPdf() throws IOException {
    try (PDDocument document = new PDDocument(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      document.addPage(new PDPage());
      document.save(baos);
      return baos.toByteArray();
    }
  }

  @Test
  void getImageUploadSizeLimit_validNumber_returnsBytes() {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), "10");
    assertThat(UploadDocumentUtils.getImageUploadSizeLimit()).isEqualTo(10L * 1024 * 1024);
  }

  @Test
  void getImageUploadSizeLimit_blank_returnsZero() {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), "");
    assertThat(UploadDocumentUtils.getImageUploadSizeLimit()).isZero();
  }

  @Test
  void getImageUploadSizeLimit_nonNumeric_returnsZero() {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), "not-a-number");
    assertThat(UploadDocumentUtils.getImageUploadSizeLimit()).isZero();
  }

  @Test
  void isBase64ImageSizeExceeded_blank_returnsFalse() {
    assertThat(UploadDocumentUtils.isBase64ImageSizeExceeded(null)).isFalse();
    assertThat(UploadDocumentUtils.isBase64ImageSizeExceeded("")).isFalse();
  }

  @Test
  void isBase64ImageSizeExceeded_withinLimit_returnsFalse() {
    assertThat(UploadDocumentUtils.isBase64ImageSizeExceeded("QUJDREVGRw==")).isFalse();
  }

  @Test
  void isBase64ImageSizeExceeded_limitZero_anyContentExceeds() {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), "0");
    assertThat(UploadDocumentUtils.isBase64ImageSizeExceeded("QUJDREVGRw==")).isTrue();
  }

  @Test
  void isBase64ImageSizeExceeded_largeContentOverSmallLimit_returnsTrue() {
    Ivy.var().set(GlobalVariable.IMAGE_UPLOAD_SIZE_LIMIT.getKey(), "1");
    String base64Content = "A".repeat(2_000_000);
    assertThat(UploadDocumentUtils.isBase64ImageSizeExceeded(base64Content)).isTrue();
  }

  @Test
  void getDocumentUploadSizeLimit_validNumber_returnsBytes() {
    Ivy.var().set(GlobalVariable.DOCUMENT_UPLOAD_SIZE_LIMIT.getKey(), "20");
    assertThat(UploadDocumentUtils.getDocumentUploadSizeLimit()).isEqualTo(20L * 1024 * 1024);
  }

  @Test
  void getDocumentUploadSizeLimit_blank_returnsZero() {
    Ivy.var().set(GlobalVariable.DOCUMENT_UPLOAD_SIZE_LIMIT.getKey(), "");
    assertThat(UploadDocumentUtils.getDocumentUploadSizeLimit()).isZero();
  }

  @Test
  void getDocumentUploadSizeLimit_nonNumeric_returnsZero() {
    Ivy.var().set(GlobalVariable.DOCUMENT_UPLOAD_SIZE_LIMIT.getKey(), "abc");
    assertThat(UploadDocumentUtils.getDocumentUploadSizeLimit()).isZero();
  }

  @Test
  void enableVirusScannerForUploadedDocument_true_returnsTrue() {
    Ivy.var().set(GlobalVariable.ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT.getKey(), "true");
    assertThat(UploadDocumentUtils.enableVirusScannerForUploadedDocument()).isTrue();
  }

  @Test
  void enableVirusScannerForUploadedDocument_false_returnsFalse() {
    Ivy.var().set(GlobalVariable.ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT.getKey(), "false");
    assertThat(UploadDocumentUtils.enableVirusScannerForUploadedDocument()).isFalse();
  }

  @Test
  void enableScriptCheckingForUploadedDocument_true_returnsTrue() {
    Ivy.var().set(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getKey(), "true");
    assertThat(UploadDocumentUtils.enableScriptCheckingForUploadedDocument()).isTrue();
  }

  @Test
  void enableScriptCheckingForUploadedDocument_false_returnsFalse() {
    Ivy.var().set(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getKey(), "false");
    assertThat(UploadDocumentUtils.enableScriptCheckingForUploadedDocument()).isFalse();
  }

  @Test
  void isDocumentSafe_unsupportedExtension_alwaysTrue() {
    UploadedFile file = fakeFile("data.json", "{}".getBytes(StandardCharsets.UTF_8));
    assertThat(UploadDocumentUtils.isDocumentSafe(file)).isTrue();
  }

  @Test
  void isDocumentSafe_safeSvg_returnsTrue() {
    UploadedFile file = fakeFile("image.svg", SAFE_SVG.getBytes(StandardCharsets.UTF_8));
    assertThat(UploadDocumentUtils.isDocumentSafe(file)).isTrue();
  }

  @Test
  void isDocumentSafe_unsafeSvgWithScriptTag_returnsFalse() {
    UploadedFile file = fakeFile("image.svg", UNSAFE_SVG.getBytes(StandardCharsets.UTF_8));
    assertThat(UploadDocumentUtils.isDocumentSafe(file)).isFalse();
  }

  @Test
  void isDocumentSafe_malformedPdf_returnsFalse() {
    UploadedFile file = fakeFile("document.pdf", "not a real pdf".getBytes(StandardCharsets.UTF_8));
    assertThat(UploadDocumentUtils.isDocumentSafe(file)).isFalse();
  }

  @Test
  void isDocumentSafe_validPlainPdf_returnsTrue() throws IOException {
    UploadedFile file = fakeFile("document.pdf", buildMinimalPdf());
    assertThat(UploadDocumentUtils.isDocumentSafe(file)).isTrue();
  }

  @Test
  void isDocumentSafe_nullFile_returnsFalse() {
    assertThat(UploadDocumentUtils.isDocumentSafe(null)).isFalse();
  }

  @Test
  void isEncrypted_nullStreamedContent_returnsFalse() {
    assertThat(UploadDocumentUtils.isEncrypted(null)).isFalse();
  }

  @Test
  void isEncrypted_nullName_returnsFalse() {
    StreamedContent content = DefaultStreamedContent.builder()
        .stream(() -> new ByteArrayInputStream(new byte[0]))
        .build();
    assertThat(UploadDocumentUtils.isEncrypted(content)).isFalse();
  }

  @Test
  void isEncrypted_unsupportedExtension_returnsFalse() {
    StreamedContent content = DefaultStreamedContent.builder()
        .name("data.json")
        .stream(() -> new ByteArrayInputStream("{}".getBytes(StandardCharsets.UTF_8)))
        .build();
    assertThat(UploadDocumentUtils.isEncrypted(content)).isFalse();
  }

  @Test
  void isEncrypted_pdfExtensionWithoutEncryption_returnsFalse() throws IOException {
    byte[] pdfBytes = buildMinimalPdf();
    StreamedContent content = DefaultStreamedContent.builder()
        .name("document.pdf")
        .stream(() -> new ByteArrayInputStream(pdfBytes))
        .build();
    assertThat(UploadDocumentUtils.isEncrypted(content)).isFalse();
  }

  @Test
  void validateUploadedFile_nullFile_returnsErrorMessage() {
    assertThat(UploadDocumentUtils.validateUploadedFile(null)).isNotBlank();
  }

  @Test
  void validateUploadedFile_emptyFile_returnsErrorMessage() {
    UploadedFile file = fakeFile("dashboard.json", new byte[0]);
    assertThat(UploadDocumentUtils.validateUploadedFile(file)).isNotBlank();
  }

  @Test
  void validateUploadedFile_wrongExtension_returnsErrorMessage() {
    Ivy.var().set(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getKey(), "false");
    UploadedFile file = fakeFile("dashboard.txt", "not json".getBytes(StandardCharsets.UTF_8));
    assertThat(UploadDocumentUtils.validateUploadedFile(file)).isNotBlank();
  }

  @Test
  void validateUploadedFile_validJsonFile_returnsEmpty() {
    UploadedFile file = fakeFile("dashboard.json", "{}".getBytes(StandardCharsets.UTF_8));
    assertThat(UploadDocumentUtils.validateUploadedFile(file)).isBlank();
  }

  @Test
  void validateUploadedFile_scriptCheckingEnabledWithUnsafeSvgContent_returnsErrorBeforeExtensionCheck() {
    Ivy.var().set(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getKey(), "true");
    UploadedFile file = fakeFile("image.svg", UNSAFE_SVG.getBytes(StandardCharsets.UTF_8));
    assertThat(UploadDocumentUtils.validateUploadedFile(file)).isNotBlank();
  }

  @Test
  void validateUploadedFile_scriptCheckingDisabled_wrongExtensionStillRejected() {
    Ivy.var().set(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getKey(), "false");
    UploadedFile file = fakeFile("image.svg", UNSAFE_SVG.getBytes(StandardCharsets.UTF_8));
    assertThat(UploadDocumentUtils.validateUploadedFile(file)).isNotBlank();
  }
}
