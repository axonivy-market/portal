package com.axonivy.portal.components.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum BasicDocumentType implements DocumentType {

  DOCUMENTATION, CONTRACT, INFORMATION, EMAIL, OTHERS;
  
  @Override
  public String toString() {
    String translatedEnum =
        Ivy.cms().co("/Dialogs/com/axonivy/portal/components/DocumentTable/" + getClass().getSimpleName() + "/" + name());
    return translatedEnum.isEmpty() ? name() : translatedEnum;
  }
}
