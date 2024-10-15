package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.IvyCacheService;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.util.ListUtilities;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.language.LanguageConfigurator;
import ch.ivyteam.ivy.language.LanguageManager;
import ch.ivyteam.ivy.language.LanguageRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;

public class LanguageService {

  private static LanguageService instance;

  private LanguageService() {}

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

  public IvyLanguage getIvyLanguageOfUser() {
    IvyLanguage ivyLanguage = new IvyLanguage();
    List<Locale> contentLocales = getContentLocales();
    List<Locale> formatLocales = getFormattingLocales();
    
    List<String> supportedLanguages = ListUtilities.transformList(contentLocales, Locale::toLanguageTag); 
    
    List<String> supportedFormatLanguages =  ListUtilities.transformList(formatLocales, Locale::toLanguageTag); 
    
    ivyLanguage.setUserLanguage(getUserLanguage());
    ivyLanguage.setSupportedLanguages(supportedLanguages);
    
    ivyLanguage.setUserFormattingLanguage(getUserFormatLanguage());
    ivyLanguage.setSupportedFormattingLanguages(supportedFormatLanguages);
    ivyLanguage.initItemFormattingLanguage();
    return ivyLanguage;
  }

  public String getUserLanguage() {
    String languageTag = loadLanguage(IUser::getLanguage);
    if (languageTag == StringUtils.EMPTY) {
    return getDefaultLanguage().toLanguageTag();
    }
    return languageTag;
  }
  
  public String getUserFormatLanguage() {
    return loadLanguage(IUser::getFormattingLanguage);
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

  public void saveUserLanguage(IvyLanguage language) {
    if (Ivy.session().isSessionUserUnknown()) {
      return;
    }
    Sudo.get(() -> {
      IUser currentUser = Ivy.session().getSessionUser();
      
      Locale userLanguage = StringUtils.isNotBlank(language.getUserLanguage()) 
          ? Locale.forLanguageTag(language.getUserLanguage()) 
          : null;
      currentUser.setLanguage(userLanguage);
      
      Locale userFormatLocale = language.getItemFormattingLanguage() != null 
          && language.getItemFormattingLanguage().getValue() != null 
          && StringUtils.isNotBlank(language.getItemFormattingLanguage().getValue().toString()) 
          ? Locale.forLanguageTag(language.getItemFormattingLanguage().getValue().toString()) 
          : null;
      currentUser.setFormattingLanguage(userFormatLocale);
      return Void.class;
    });
  }
  
  /**
   * From IVYPORTAL-16987
   * We use session cache to reduce loading time in new dashboard template
   * @return content locales
   * 
   */
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

  /**
   * From IVYPORTAL-16987
   * We use session cache to reduce loading time in new dashboard template
   * @return formatting locales
   * 
   */
  @SuppressWarnings("unchecked")
  public List<Locale> getFormattingLocales() {
    String sessionUserId = getSessionUserId();
    IvyCacheService cacheService = IvyCacheService.getInstance();
    Optional<Object> result = cacheService.getSessionCacheValue(IvyCacheIdentifier.PORTAL_FORMATTING_LOCALES,
        sessionUserId);
    if (result.isPresent()) {
      return (List<Locale>) result.get();
    }

    List<Locale> locales = locales(LanguageRepository::allFormatting)
        .stream()
        .sorted(Comparator.comparing(Locale::getDisplayName, String.CASE_INSENSITIVE_ORDER))
        .collect(Collectors.toList());
    cacheService.setSessionCache(IvyCacheIdentifier.PORTAL_FORMATTING_LOCALES, sessionUserId, locales);
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

  public Locale getDefaultFormattingLanguage() {
    return getLanguageConfigurator().formatting();
  }

  private LanguageConfigurator getLanguageConfigurator() {
    return new LanguageConfigurator(ISecurityContext.current());
  }
  
}
