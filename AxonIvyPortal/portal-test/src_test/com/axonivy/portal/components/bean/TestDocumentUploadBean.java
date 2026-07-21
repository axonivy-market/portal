package com.axonivy.portal.components.bean;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.enums.GlobalVariable;
import com.axonivy.portal.components.ivydata.bo.IvyDocument;
import com.axonivy.portal.components.masterdata.MasterData;

import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestDocumentUploadBean {

  private final DocumentUploadBean bean = new DocumentUploadBean();

  @Test
  void getFileUploadSizeLimit_noCustomLimit_returnsMasterDataDefault() {
    assertThat(bean.getFileUploadSizeLimit()).isEqualTo(MasterData.getFileUploadSizeLimit());
  }

  @Test
  void getFileUploadSizeLimit_positiveCustomLimit_returnsCustomLimit() {
    bean.setCustomFileSizeLimit(1024L);
    assertThat(bean.getFileUploadSizeLimit()).isEqualTo(1024L);
  }

  @Test
  void getFileUploadSizeLimit_nonPositiveCustomLimit_fallsBackToDefault() {
    bean.setCustomFileSizeLimit(0L);
    assertThat(bean.getFileUploadSizeLimit()).isEqualTo(MasterData.getFileUploadSizeLimit());
  }

  @Test
  void getFileUploadInvalidSizeMessage_returnsNonBlankMessage() {
    assertThat(bean.getFileUploadInvalidSizeMessage(1024L)).isNotNull();
  }

  @Test
  void getDocumentTypes_returnsNonEmptyArray() {
    assertThat(bean.getDocumentTypes()).isNotEmpty();
  }

  @Test
  void getPortalScriptCheckingSettingOrDefault_empty_returnsDefault(AppFixture fixture) {
    fixture.var(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getKey(), "");
    assertThat(bean.getPortalScriptCheckingSettingOrDefault(true)).isTrue();
    assertThat(bean.getPortalScriptCheckingSettingOrDefault(false)).isFalse();
  }

  @Test
  void getPortalScriptCheckingSettingOrDefault_explicitValue_ignoresDefault(AppFixture fixture) {
    fixture.var(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.getKey(), "false");
    assertThat(bean.getPortalScriptCheckingSettingOrDefault(true)).isFalse();
  }

  @Test
  void getPortalVirusScannerSettingOrDefault_empty_returnsDefault(AppFixture fixture) {
    fixture.var(GlobalVariable.ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT.getKey(), "");
    assertThat(bean.getPortalVirusScannerSettingOrDefault(true)).isTrue();
    assertThat(bean.getPortalVirusScannerSettingOrDefault(false)).isFalse();
  }

  @Test
  void getPortalVirusScannerSettingOrDefault_explicitValue_ignoresDefault(AppFixture fixture) {
    fixture.var(GlobalVariable.ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT.getKey(), "true");
    assertThat(bean.getPortalVirusScannerSettingOrDefault(false)).isTrue();
  }

  @Test
  void getPortalAllowedUploadFileTypesSettingOrDefault_empty_returnsDefault(AppFixture fixture) {
    fixture.var(GlobalVariable.UPLOAD_DOCUMENT_WHITELIST_EXTENSION.getKey(), "");
    assertThat(bean.getPortalAllowedUploadFileTypesSettingOrDefault(".pdf,.docx")).isEqualTo(".pdf,.docx");
  }

  @Test
  void getPortalAllowedUploadFileTypesSettingOrDefault_explicitValue_ignoresDefault(AppFixture fixture) {
    fixture.var(GlobalVariable.UPLOAD_DOCUMENT_WHITELIST_EXTENSION.getKey(), ".png");
    assertThat(bean.getPortalAllowedUploadFileTypesSettingOrDefault(".pdf,.docx")).isEqualTo(".png");
  }

  @Test
  void canPreviewDocument_nullDocument_returnsFalse() {
    assertThat(bean.canPreviewDocument(null, true)).isFalse();
  }

  @Test
  void canPreviewDocument_previewDisabled_returnsFalse() {
    IvyDocument document = IvyDocument.builder().path("report.pdf").create();
    assertThat(bean.canPreviewDocument(document, false)).isFalse();
  }

  @Test
  void canPreviewDocument_unsupportedTypeEvenIfEnabled_returnsFalse() {
    IvyDocument document = IvyDocument.builder().path("archive.zip").create();
    assertThat(bean.canPreviewDocument(document, true)).isFalse();
  }

  @Test
  void canPreviewDocument_supportedTypeAndEnabled_returnsTrue() {
    IvyDocument document = IvyDocument.builder().path("report.pdf").create();
    assertThat(bean.canPreviewDocument(document, true)).isTrue();
  }
}
