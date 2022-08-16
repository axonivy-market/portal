package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.FileUtils;

import com.axonivy.portal.components.enums.BasicDocumentType;
import com.axonivy.portal.components.enums.DocumentType;
import com.axonivy.portal.components.masterdata.MasterData;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class DocumentUploadBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public Long getFileUploadSizeLimit() {
    return MasterData.getFileUploadSizeLimit();
  }
  
  public String getFileUploadInvalidSizeMessage() {
    return Ivy.cms().co("/Dialogs/com/axonivy/portal/components/DocumentTable/ErrorFileUploadSize",
        Arrays.asList(FileUtils.byteCountToDisplaySize(getFileUploadSizeLimit())));
  }
  
  public DocumentType[] getDocumentTypes() {
    return BasicDocumentType.values();
  }
}
