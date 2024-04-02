package com.axonivy.portal.components.examples.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.axonivy.portal.components.enums.DocumentType;
import com.axonivy.portal.components.examples.enums.ExtendedDocumentType;
import com.axonivy.portal.components.publicapi.ProcessStartAPI;

@ManagedBean
@ViewScoped
public class DocumentTableExampleBean implements Serializable {

  private static final long serialVersionUID = 393379085832602653L;

  public DocumentType[] getDocumentTypes() {
    return ExtendedDocumentType.values();
  }

  public String getSerenityExampleLink() {
    return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath("Start Processes/DocumentTableExample/showDefaultDocumentTableExampleSerenity.ivp");
  }

  public String getSerenityCustomExampleLink() {
    return ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath("Start Processes/DocumentTableExample/showCustomizedDocumentTableExampleSerenity.ivp");
  }
}
