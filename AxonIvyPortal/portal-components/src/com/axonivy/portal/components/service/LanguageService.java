package com.axonivy.portal.components.service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.constant.IvyCacheIdentifier;
import com.axonivy.portal.components.enums.SessionAttribute;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.language.LanguageConfigurator;
import ch.ivyteam.ivy.language.LanguageManager;
import ch.ivyteam.ivy.language.LanguageRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;

public class LanguageService {
  private static LanguageService instance;
  private String DEFAULT_LOCALE_CODE = "en";
  
  public static LanguageService getInstance() {
    if (instance == null) {
      synchronized (LanguageService.class) {
        if (instance == null) {
          instance = new LanguageService();
        }
      }
    }
    return instance;
  }
  
  public String getUserLanguage() {
    String languageTag = loadLanguage(IUser::getLanguage);
    
    if (languageTag == StringUtils.EMPTY) {
    return getDefaultLanguage().toLanguageTag();
    }
    
    Locale userLocale = LocaleUtils.toLocale(languageTag);
    if (getContentLocales().contains(userLocale)) {
      return languageTag;
    }
    return getSupportedLanguages().contains(userLocale.getLanguage()) ? userLocale.getLanguage() : getDefaultLanguage().toLanguageTag();
  }
  
  public Locale convertToPortalUserLocale(Locale locale) {
    if (isLocaleSupported(locale)) {
      return locale;
    }

    if (hasCountry(locale)) {
      String language = locale.getLanguage();
      return isLanguageSupported(language) ? LocaleUtils.toLocale(language) : LocaleUtils.toLocale(DEFAULT_LOCALE_CODE);
    }
    return LocaleUtils.toLocale(DEFAULT_LOCALE_CODE);
  }
  
  public Locale getUserLocale() {
    return convertToPortalUserLocale(Ivy.session().getContentLocale());
  }
  
  public boolean isLanguageSupported(String language) {
    return getSupportedLanguages().contains(language);
  }
  
  public boolean hasCountry(Locale locale) {
    return !locale.getCountry().isEmpty();
  }
  
  public boolean isLocaleSupported(Locale locale) {
    return getContentLocales().contains(locale);
  }
  
  private List<String> getSupportedLanguages() {
    return getContentLocales().stream().map(Locale::toLanguageTag).collect(Collectors.toList());
  }
  
  private String loadLanguage(Function<IUser, Locale> userLocaleLoader) {
    String languageTag = "";
    if (Ivy.session().isSessionUserUnknown()) {
      languageTag = "";
    } else {
      Locale apply = userLocaleLoader.apply(Ivy.session().getSessionUser());
      languageTag = Objects.nonNull(apply) ? apply.toLanguageTag() : languageTag;
    }
    return languageTag;
  }
  
  @SuppressWarnings("unchecked")
  public List<Locale> getContentLocales() {
    String sessionUserId = getSessionUserId();
    IvyCacheService cacheService = IvyCacheService.getInstance();
    Optional<Object> result = cacheService.getSessionCacheValue(IvyCacheIdentifier.PORTAL_CONTENT_LOCALES,
        sessionUserId);
    if (result.isPresent()) {
      return (List<Locale>) result.get();
    }
    
    List<Locale> locales = locales(LanguageRepository::allContent);
    cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_CONTENT_LOCALES, sessionUserId, locales);
    return locales;
  }
  
  private String getSessionUserId() {
    String sessionIdAttribute = SessionAttribute.SESSION_IDENTIFIER.toString();
    if (Ivy.session().getAttribute(sessionIdAttribute) == null) {
      Ivy.session().setAttribute(sessionIdAttribute, UUID.randomUUID().toString());
    }
    return (String) Ivy.session().getAttribute(sessionIdAttribute);
  }
  
  private List<Locale> locales(Function<LanguageRepository, List<Locale>> loader) {
    LanguageManager languageManager = LanguageManager.instance();
    ISecurityContext securityContext = ISecurityContext.current();
    return loader.apply(languageManager.languages(securityContext));
  }

  public Locale getDefaultLanguage() {
    return getLanguageConfigurator().content();
  }
  
  private LanguageConfigurator getLanguageConfigurator() {
    return new LanguageConfigurator(ISecurityContext.current());
  }
}
