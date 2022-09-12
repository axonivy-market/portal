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
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.language.LanguageManager;
import ch.ivyteam.ivy.language.LanguageRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;

public class LanguageService implements ILanguageService {
  private LanguageService() {}

  public static LanguageService newInstance() {
    return new LanguageService();
  }

  @Override
  public IvyLanguageResultDTO findUserLanguages() {
    return IvyExecutor.executeAsSystem(() -> {
      IvyLanguageResultDTO result = new IvyLanguageResultDTO();
      result.setIvyLanguage(getIvyLanguageOfUser());
      return result;
    });
  }

  private IvyLanguage getIvyLanguageOfUser() {
    IvyLanguage ivyLanguage = new IvyLanguage();
    List<Locale> contentLocales = getContentLocales();
    List<Locale> formatLocales = getFormattingLocales();
    
    List<String> supportedLanguages = contentLocales.stream()
                                      .map(Locale::toString)
                                      .collect(Collectors.toList());
    
    List<String> supportedFormatLanguages = formatLocales.stream()
                                      .map(Locale::toLanguageTag)
                                      .collect(Collectors.toList());
    
    ivyLanguage.setUserLanguage(getUserLanguage());
    ivyLanguage.setSupportedLanguages(supportedLanguages);
    
    ivyLanguage.setUserFormattingLanguage(getUserFormatLanguage());
    ivyLanguage.setSupportedFormattingLanguages(supportedFormatLanguages);
    ivyLanguage.initItemFormattingLanguage();
    return ivyLanguage;
  }

  private String getUserLanguage() {
    return loadLanguage(IUser::getLanguage);
  }
  
  private String getUserFormatLanguage() {
    return loadLanguage(IUser::getFormattingLanguage);
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

  @Override
  public void saveUserLanguage(IvyLanguage language) {
    if (Ivy.session().isSessionUserUnknown()) {
      return;
    }
    IvyExecutor.executeAsSystem(() -> {
      IUser currentUser = Ivy.session().getSessionUser();
      
      Locale userLanguage = null;
      if (StringUtils.isNotBlank(language.getUserLanguage())) {
        userLanguage = Locale.forLanguageTag(language.getUserLanguage());
      } 
      currentUser.setLanguage(userLanguage);
      
      Locale userFormatLocale = null;
      if (language.getItemFormattingLanguage() != null && language.getItemFormattingLanguage().getValue() != null) {
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
                          .sorted(Comparator.comparing(Locale::getDisplayName))
                          .collect(Collectors.toList());
  }
}
