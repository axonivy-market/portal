package ch.ivy.addon.portalkit.ivydata.exception;

import ch.ivyteam.ivy.environment.Ivy;

public enum PortalIvyDataErrorType {

  APP_NOT_FOUND,
  USER_NOT_FOUND,
  
  // Language
  SUPPORTED_LANGUAGES_NOT_FOUND,
  FAIL_TO_LOAD_LANGUAGE,
  FAIL_TO_SAVE_LANGUAGE,

  // Email settings
  FAIL_TO_LOAD_EMAIL_SETTING,
  FAIL_TO_SAVE_EMAIL_SETTING,
  
  // Change password
  FAIL_TO_CHANGE_PASSWORD,
  
  // Absence
  FAIL_TO_LOAD_ABSENCE,
  FAIL_TO_CREATE_ABSENCE,
  FAIL_TO_UPDATE_ABSENCE,
  FAIL_TO_DELETE_ABSENCE,
  
  // Substitute
  FAIL_TO_LOAD_SUBSTITUTE,
  FAIL_TO_SAVE_SUBSTITUTE,
  
  // User processes
  FAIL_TO_LOAD_PROCESS;
  
  @Override
  public String toString() {
    String translatedEnum =
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/" + getClass().getSimpleName() + "/" + name());
    return translatedEnum.isEmpty() ? name() : translatedEnum;
  }
}
