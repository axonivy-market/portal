package com.axonivy.portal.component.example.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.component.enums.DocumentType;
import com.axonivy.portal.component.example.enums.ExtendedDocumentType;

@ManagedBean
@ViewScoped
public class documentTableExampleBean implements Serializable {

  private static final long serialVersionUID = -4285398008730925598L;

  public DocumentType[] getDocumentTypes() {
    return ExtendedDocumentType.values();
  }
}
