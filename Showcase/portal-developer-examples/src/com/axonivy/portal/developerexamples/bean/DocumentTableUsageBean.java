package com.axonivy.portal.developerexamples.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.developerexamples.enums.ExtendedDocumentType;

import ch.ivy.addon.portalkit.enums.DocumentType;

@ManagedBean
@ViewScoped
public class DocumentTableUsageBean implements Serializable {

  private static final long serialVersionUID = -1792023391472222134L;

  public DocumentType[] getDocumentTypes() {
    return ExtendedDocumentType.values();
  }
}
