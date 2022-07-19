package com.axonivy.portal.component.bean;

import java.io.Serializable;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.FileUtils;

import com.axonivy.portal.component.enums.BasicDocumentType;
import com.axonivy.portal.component.enums.DocumentType;
import com.axonivy.portal.component.enums.GlobalVariable;
import com.axonivy.portal.component.masterdata.MasterData;
import com.axonivy.portal.component.service.GlobalSettingService;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class DocumentUploadBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public Long getFileUploadSizeLimit() {
    return MasterData.getFileUploadSizeLimit();
  }
  
  public String getFileUploadInvalidSizeMessage() {
    return Ivy.cms().co("/Dialogs/com/axonivy/portal/component/DocumentTable/ErrorFileUploadSize",
        Arrays.asList(FileUtils.byteCountToDisplaySize(getFileUploadSizeLimit())));
  }
  
  public DocumentType[] getDocumentTypes() {
    return BasicDocumentType.values();
  }

  public boolean enableScriptCheckingForUploadedDocument() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT);
  }

  public boolean enableVirusScannerForUploadedDocument() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT);
  }

  public String getAllowedUploadFileTypes() {
    return GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.UPLOAD_DOCUMENT_WHITELIST_EXTENSION);
  }
}
