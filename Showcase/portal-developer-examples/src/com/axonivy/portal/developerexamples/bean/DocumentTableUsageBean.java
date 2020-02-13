package com.axonivy.portal.developerexamples.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.developerexamples.enums.ExtendedDocumentType;

import ch.ivy.addon.portalkit.enums.DocumentType;

@ManagedBean
@ViewScoped
public class DocumentTableUsageBean {

  public DocumentType[] getDocumentTypes() {
    return ExtendedDocumentType.values();
  }
}
