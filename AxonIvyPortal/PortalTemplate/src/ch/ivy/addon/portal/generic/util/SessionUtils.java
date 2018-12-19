package ch.ivy.addon.portal.generic.util;

import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;

public class SessionUtils {
  public static final String LOGOUT_PAGE_ATTRIBUTE = "LOGOUT_PAGE_URL";
  private SessionUtils(){}
  public static void removeAttributes(){
    Ivy.session().removeAttribute(GlobalSettingService.HIDE_TIME);
    Ivy.session().removeAttribute(GlobalVariable.SHOW_ENVIRONMENT_INFO);
    Ivy.session().removeAttribute(GlobalVariable.HIDE_LOGOUT_BUTTON);
    Ivy.session().removeAttribute(GlobalVariable.HIDE_CHANGE_PASSWORD_BUTTON);
    Ivy.session().removeAttribute(LOGOUT_PAGE_ATTRIBUTE);
  }
}
