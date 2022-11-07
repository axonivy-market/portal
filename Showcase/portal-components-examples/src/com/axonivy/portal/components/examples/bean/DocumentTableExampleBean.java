package com.axonivy.portal.components.examples.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.enums.DocumentType;
import com.axonivy.portal.components.examples.enums.ExtendedDocumentType;

@ManagedBean
@ViewScoped
public class DocumentTableExampleBean implements Serializable {

  private static final long serialVersionUID = -4285398008730925598L;

  public DocumentType[] getDocumentTypes() {
    return ExtendedDocumentType.values();
  }
}
