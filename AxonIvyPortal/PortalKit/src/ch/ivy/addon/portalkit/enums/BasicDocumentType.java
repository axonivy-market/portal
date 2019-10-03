package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum BasicDocumentType implements DocumentType {

  DOCUMENTATION, CONTRACT, INFORMATION, EMAIL, OTHERS;
  
  @Override
  public String toString() {
    String translatedEnum =
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/" + getClass().getSimpleName() + "/" + name());
    return translatedEnum.isEmpty() ? name() : translatedEnum;
  }
}
