package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portalkit.service.DocumentUploadGlobalSettingService;

@ManagedBean
@SessionScoped
public class DocumentUploadGlobalSettingBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private DocumentUploadGlobalSettingService documentUploadGlobalSettingService;

  @PostConstruct
  public void init() {
    documentUploadGlobalSettingService = new DocumentUploadGlobalSettingService();
  }

  public String getUploadDocumentWhitelistExtensions() {
    return documentUploadGlobalSettingService.findUploadDocumentWhitelistExtensionSettingValue();
  }

  public boolean getEnableScriptCheckingForUploadedDocument() {
    return documentUploadGlobalSettingService.findEnableScriptCheckingForUploadedDocument();
  }

  public boolean getEnableVirusScannerForUploadedDocument() {
    return documentUploadGlobalSettingService.findEnableVirusScannerForUploadedDocument();
  }
}
