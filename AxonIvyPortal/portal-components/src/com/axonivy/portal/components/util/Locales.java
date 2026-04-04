package com.axonivy.portal.components.util;

import java.util.Locale;

import com.axonivy.portal.components.service.LanguageService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.language.LanguageConfigurator;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;

public class Locales {
  public static Locale getCurrentLocale() {
    Locale currentLocale = Sudo.get(() -> {
      Locale userLanguage = Ivy.session().getSessionUser().getLanguage();
      if (userLanguage != null) {
        return userLanguage;
      }
      return new LanguageConfigurator(ISecurityContext.current()).content();
    });
    return LanguageService.getInstance().convertToPortalUserLocale(currentLocale);
  }
}