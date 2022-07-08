package com.axonivy.portal.component.example.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.axonivy.portal.component.enums.DocumentType;
import com.axonivy.portal.component.example.enums.ExtendedDocumentType;

@ManagedBean
@ViewScoped
public class DocumentTableExampleBean implements Serializable {

  private static final long serialVersionUID = 393379085832602653L;

  public DocumentType[] getDocumentTypes() {
    return ExtendedDocumentType.values();
  }
}
