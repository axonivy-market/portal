package com.axonivy.portal.components.ivydata.exception;

import ch.ivyteam.ivy.environment.Ivy;

public enum PortalIvyDataErrorType {

  APP_NOT_FOUND,
  FAIL_TO_LOAD_CASE,
  FAIL_TO_COUNT_CASE;

  @Override
  public String toString() {
    String translatedEnum =
        Ivy.cms().co("/Enums/" + getClass().getSimpleName() + "/" + name());
    return translatedEnum.isEmpty() ? name() : translatedEnum;
  }
}
