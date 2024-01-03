package com.axonivy.portal.developerexamples.service;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;

import com.axonivy.portal.developerexamples.dto.IvyLanguage;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class LanguageService {

  private LanguageService() {}

  public static LanguageService newInstance() {
    return new LanguageService();
  }

  public IvyLanguage getIvyLanguageOfUser() {
    IvyLanguage ivyLanguage = new IvyLanguage();
    ivyLanguage.setUserLanguage(getUserLanguage());
    return ivyLanguage;
  }

  public String getUserLanguage() {
    return loadLanguage(IUser::getLanguage);
  }

  private String loadLanguage(Function<IUser, Locale> userLocaleLoader) {
    var languageTag = "";
    if (Ivy.session().isSessionUserUnknown()) {
      languageTag = "";
    } else {
      Locale apply = userLocaleLoader.apply(Ivy.session().getSessionUser());
      languageTag = Objects.nonNull(apply) ? apply.toLanguageTag() : languageTag;
    }
    return languageTag;
  }
}
