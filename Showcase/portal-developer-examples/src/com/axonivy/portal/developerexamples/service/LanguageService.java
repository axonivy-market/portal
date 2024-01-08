package com.axonivy.portal.developerexamples.service;

import java.util.Locale;

import ch.ivyteam.ivy.language.LanguageConfigurator;
import ch.ivyteam.ivy.security.ISecurityContext;

public class LanguageService {

  private LanguageService() {}

  public static LanguageService newInstance() {
    return new LanguageService();
  }


  public Locale getDefaultEmailLanguage() {
    return getLanguageConfigurator().content();
  }

  public Locale getDefaultFormattingLanguage() {
    return getLanguageConfigurator().formatting();
  }
  
  private LanguageConfigurator getLanguageConfigurator() {
    return new LanguageConfigurator(ISecurityContext.current());
  }
  
}
