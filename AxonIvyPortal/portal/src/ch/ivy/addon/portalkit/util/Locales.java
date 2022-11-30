package ch.ivy.addon.portalkit.util;

import java.util.Locale;

import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class Locales {
  public Locale getCurrentLocale() {
    return IvyExecutor.executeAsSystem(() -> {
      IUser user = Ivy.session().getSessionUser();
      Locale userLanguage = user.getLanguage();
      if (userLanguage != null) {
        return userLanguage;
      }
      return LanguageService.newInstance().getDefaultEmailLanguage();
    });
  }
}
