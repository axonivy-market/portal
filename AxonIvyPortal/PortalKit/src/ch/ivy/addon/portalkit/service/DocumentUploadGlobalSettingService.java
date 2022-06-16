package ch.ivy.addon.portalkit.service;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.document.DocumentExtensionConstants;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;

@SuppressWarnings("deprecation")
public class DocumentUploadGlobalSettingService {

  private GlobalSettingService globalSettingService;

  public DocumentUploadGlobalSettingService() {
    globalSettingService = new GlobalSettingService();
  }

  public String findUploadDocumentWhitelistExtensionSettingValue() {
    if (globalSettingService.isGlobalSettingAvailable(GlobalVariable.UPLOAD_DOCUMENT_WHITELIST_EXTENSION.toString(),
        true)) {
      GlobalSetting documentSetting =
          globalSettingService.findGlobalSettingByKey(GlobalVariable.UPLOAD_DOCUMENT_WHITELIST_EXTENSION.toString());
      if (StringUtils.EMPTY.equals(documentSetting.getValue())) {
        return StringUtils.EMPTY;
      } else {
        return documentSetting.getValue();
      }
    }

    return String.join(", ", DocumentExtensionConstants.DEFAULT_WHITELIST_EXTENSION);
  }

  public boolean findEnableScriptCheckingForUploadedDocument() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    String enableScriptCheckingForUploadedDocument = globalSettingService
        .findGlobalSettingValue(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.toString());
    return Boolean.parseBoolean(enableScriptCheckingForUploadedDocument);
  }

  public boolean findEnableVirusScannerForUploadedDocument() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    return globalSettingService
        .findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT.toString());
  }
}
