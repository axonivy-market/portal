package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.ILanguageService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.language.LanguageManager;
import ch.ivyteam.ivy.language.LanguageRepository;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.ISessionInternal;
import ch.ivyteam.ivy.security.IUser;

public class LanguageService implements ILanguageService {

  //private static final String CMS_LANG_KEY = "/AppInfo/SupportedLanguages";

  private LanguageService() {}

  public static LanguageService newInstance() {
    return new LanguageService();
  }

  @Override
  public IvyLanguageResultDTO findUserLanguages() {
    return IvyExecutor.executeAsSystem(() -> {
      IvyLanguageResultDTO result = new IvyLanguageResultDTO();
      result.setIvyLanguage(getIvyLanguageOfCurrentApp());
      return result;
    });
  }

  private IvyLanguage getIvyLanguageOfCurrentApp() {
    IvyLanguage ivyLanguage = new IvyLanguage();
    //IUser currentUser = Ivy.session().getSessionUser();
    IApplication currentApp = Ivy.request().getApplication();
    //List<IProcessModelVersion> activeReleasedPmvs = ServiceUtilities.getActiveReleasedPmvs(currentApp);
    //List<String> supportedLanguages = getSupportedLanguagesFromPmvs(activeReleasedPmvs);
    List<String> supportedLanguages = new ArrayList<>();
    List<Locale> locales = getContentLanguages();
    List<String> nameLanguages = new ArrayList<>();
    for(Locale locale : locales) {
      nameLanguages.add(toDisplayName(locale));
      supportedLanguages.add(locale.toString());
    }
    //if (CollectionUtils.isEmpty(supportedLanguages)) {
    //  supportedLanguages.add(currentApp.getDefaultEMailLanguage().toString());
    //}
    
    //supportedLanguages.add("en");
    ivyLanguage.setAppName(currentApp.getName());
    //ivyLanguage.setUserLanguage(getUserLanguage(currentUser).toLowerCase());
    ivyLanguage.setUserLanguage(user().getLanguage().toString());
    
    ivyLanguage.setSupportedLanguages(supportedLanguages);
    
    return ivyLanguage;
  }

  private String getUserLanguage(IUser user) {
    return user.getLanguage() != null ? user.getLanguage().toLanguageTag() : Locale.ENGLISH.toLanguageTag();
  }

  /*
  private List<String> getSupportedLanguagesFromPmvs(List<IProcessModelVersion> pmvs) {
    Set<String> supportedLanguages = new HashSet<>();
    for (IProcessModelVersion pmv : pmvs) {
      ContentManagement contentManagement = ContentManagement.of(ContentManagement.cms(pmv));
      if (contentManagement == null) {
        continue;
      }
      String lang = contentManagement.co(CMS_LANG_KEY);

      if (!StringUtils.isEmpty(lang)) {
        String[] sp = lang.split(",");
        for (String spItem : sp) {
          supportedLanguages.add(spItem.trim());
        }
      }
    }
    return new ArrayList<>(supportedLanguages);
  }
*/

  @Override
  public void saveUserLanguage(IvyLanguage language){
    IvyExecutor.executeAsSystem(() -> {
      IUser currentUser = Ivy.session().getSessionUser();
      Locale userLanguage = Locale.forLanguageTag(language.getUserLanguage());
      currentUser.setLanguage(userLanguage);
      return Void.class;
    });
  }
  
  public IUser user() {
    return session().getSessionUser();
  }
  
  public ISessionInternal session() {
    return (ISessionInternal)ISession.current();
  }
  
  public List<Locale> getContentLanguages() {
    return locales(LanguageRepository::allContent);
  }
  
  public List<Locale> getFormattingLanguages() {
    return locales(LanguageRepository::allFormatting);
  }
  
  public String toDisplayName(Locale locale) {
    if (Locale.ROOT.equals(locale) || locale == null)
      return ""; 
    return String.valueOf(locale.getDisplayName(getCurrentContentLocale())) + " (" + locale.toString() + ")";
  }
  
  public Locale getCurrentContentLocale() {
    return session().getContentLocale();
  }
  
  private List<Locale> locales(Function<LanguageRepository, List<Locale>> loader) {
    Comparator<Locale> getDisplayName = Comparator.comparing(Locale::getDisplayName);
    List<Locale> locales = (List<Locale>)((List)loader.apply(LanguageManager.instance().languages(session().getSecurityContext()))).stream()
      .sorted(getDisplayName)
      .collect(Collectors.toList());
    ArrayList<Locale> l = new ArrayList<>();
    l.add(Locale.ROOT);
    l.addAll(locales);
    return l;
  }

}
