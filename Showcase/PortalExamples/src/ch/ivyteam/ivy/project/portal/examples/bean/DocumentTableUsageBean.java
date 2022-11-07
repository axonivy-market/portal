package ch.ivyteam.ivy.project.portal.examples.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.enums.DocumentType;

import ch.ivyteam.ivy.project.portal.examples.enums.ExtendedDocumentType;

@ManagedBean
@ViewScoped
public class DocumentTableUsageBean implements Serializable {

  private static final long serialVersionUID = -6246306403609197553L;

  public DocumentType[] getDocumentTypes() {
    return ExtendedDocumentType.values();
  }
}
