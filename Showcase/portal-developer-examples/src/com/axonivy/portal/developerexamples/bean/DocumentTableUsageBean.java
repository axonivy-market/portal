package com.axonivy.portal.developerexamples.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.component.enums.DocumentType;
import com.axonivy.portal.developerexamples.enums.ExtendedDocumentType;

import ch.ivy.addon.portalkit.service.CaseDocumentService;

@ManagedBean
@ViewScoped
public class DocumentTableUsageBean implements Serializable {

  private static final long serialVersionUID = -1792023391472222134L;

  public DocumentType[] getDocumentTypes() {
    return ExtendedDocumentType.values();
  }

  public List<String> getAllowedUploadFileType() {
    return CaseDocumentService.getAllowedUploadFileType();
  }

  public boolean getEnableScriptCheckingForUploadedDocument() {
    return CaseDocumentService.enableScriptCheckingForUploadedDocument();
  }

  public boolean getEnableVirusScannerForUploadedDocument() {
    return CaseDocumentService.enableVirusScannerForUploadedDocument();
  }
}
