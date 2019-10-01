package ch.ivyteam.ivy.project.portal.examples.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.enums.DocumentType;
import ch.ivyteam.ivy.project.portal.examples.enums.ExtendedDocumentType;

@ManagedBean
@ViewScoped
public class DocumentTableUsageBean {

  public DocumentType[] getDocumentTypes() {
    return ExtendedDocumentType.values();
  }
}
