package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.ILanguageService;
import ch.ivy.addon.portalkit.util.ListUtilities;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.language.LanguageConfigurator;
import ch.ivyteam.ivy.language.LanguageManager;
import ch.ivyteam.ivy.language.LanguageRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;

public class LanguageService implements ILanguageService {

  private static final Comparator<Locale> LOCALE_COMPARATOR = Comparator.comparing(Locale::getDisplayName, String.CASE_INSENSITIVE_ORDER);

  private LanguageService() {}

  public static LanguageService newInstance() {
    return new LanguageService();
  }

  @Override
  public IvyLanguageResultDTO findUserLanguages() {
    return Sudo.get(() -> {
      IvyLanguageResultDTO result = new IvyLanguageResultDTO();
      result.setIvyLanguage(getIvyLanguageOfUser());
      return result;
    });
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

  @Override
  public void saveUserLanguage(IvyLanguage language) {
    if (Ivy.session().isSessionUserUnknown()) {
      return;
    }
    Sudo.get(() -> {
      IUser currentUser = Ivy.session().getSessionUser();
      
      Locale userLanguage = null;
      if (StringUtils.isNotBlank(language.getUserLanguage())) {
        userLanguage = Locale.forLanguageTag(language.getUserLanguage());
      } 
      currentUser.setLanguage(userLanguage);
      
      Locale userFormatLocale = null;
      if (language.getItemFormattingLanguage() != null && 
          language.getItemFormattingLanguage().getValue() != null && 
          StringUtils.isNotBlank(language.getItemFormattingLanguage().getValue().toString())) {
        userFormatLocale = Locale.forLanguageTag(language.getItemFormattingLanguage().getValue().toString());
      }
      currentUser.setFormattingLanguage(userFormatLocale);
      return Void.class;
    });
  }
  
  public List<Locale> getContentLocales() {
    return locales(LanguageRepository::allContent);
  }
  
  public List<Locale> getFormattingLocales() {
    return locales(LanguageRepository::allFormatting);
  }

  private List<Locale> locales(Function<LanguageRepository, List<Locale>> loader) {
    return loader.apply(LanguageManager.instance().languages(ISecurityContext.current()))
              .stream()
              .distinct()
              .sorted(LOCALE_COMPARATOR)
              .collect(Collectors.toList());
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
