package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.FileUtils;

import com.axonivy.portal.components.enums.BasicDocumentType;
import com.axonivy.portal.components.enums.DocumentType;
import com.axonivy.portal.components.enums.GlobalVariable;
import com.axonivy.portal.components.masterdata.MasterData;
import com.axonivy.portal.components.service.GlobalSettingService;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class DocumentUploadBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long customFileSizeLimit;

  public Long getFileUploadSizeLimit() {
    return (customFileSizeLimit != null && customFileSizeLimit > 0) ? customFileSizeLimit : MasterData.getFileUploadSizeLimit();
  }
  
  public String getFileUploadInvalidSizeMessage(Long fileUploadSizeLimit) {
    return Ivy.cms().co("/Dialogs/com/axonivy/portal/components/DocumentTable/ErrorFileUploadSize",
        Arrays.asList(FileUtils.byteCountToDisplaySize(fileUploadSizeLimit)));
  }
  
  public DocumentType[] getDocumentTypes() {
    return BasicDocumentType.values();
  }

  public boolean getPortalScriptCheckingSettingOrDefault(boolean defaultIfEmpty) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT, defaultIfEmpty);
  }

  public boolean getPortalVirusScannerSettingOrDefault(boolean defaultIfEmpty) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_VIRUS_SCANNER_FOR_UPLOADED_DOCUMENT, defaultIfEmpty);
  }

  public String getPortalAllowedUploadFileTypesSettingOrDefault(String defaultIfEmpty) {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsString(GlobalVariable.UPLOAD_DOCUMENT_WHITELIST_EXTENSION, defaultIfEmpty);
  }

  public Long getCustomFileSizeLimit() {
    return customFileSizeLimit;
  }

  public void setCustomFileSizeLimit(Long customFileSizeLimit) {
    this.customFileSizeLimit = customFileSizeLimit;
  }
}
