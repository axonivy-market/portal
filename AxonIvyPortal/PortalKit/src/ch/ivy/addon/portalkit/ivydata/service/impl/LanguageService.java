package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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
import ch.ivyteam.ivy.language.LanguageConfigurator;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;

public class LanguageService implements ILanguageService {

  private static final String CMS_LANG_KEY = "/AppInfo/SupportedLanguages";

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
    IUser currentUser = Ivy.session().getSessionUser();
    IApplication currentApp = Ivy.request().getApplication();
    List<IProcessModelVersion> activeReleasedPmvs = ServiceUtilities.getActiveReleasedPmvs(currentApp);
    List<String> supportedLanguages = getSupportedLanguagesFromPmvs(activeReleasedPmvs);
    if (CollectionUtils.isEmpty(supportedLanguages)) {
      Locale defaultLocale = new LanguageConfigurator(ISecurityContext.current()).content();
      supportedLanguages.add(defaultLocale.toLanguageTag());
    }

    ivyLanguage.setAppName(currentApp.getName());
    ivyLanguage.setUserLanguage(getUserLanguage(currentUser).toLowerCase());
    ivyLanguage.setSupportedLanguages(supportedLanguages);
    
    return ivyLanguage;
  }

  private String getUserLanguage(IUser user) {
    return user.getLanguage() != null ? user.getLanguage().toLanguageTag() : Locale.ENGLISH.toLanguageTag();
  }

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

  @Override
  public void saveUserLanguage(IvyLanguage language){
    IvyExecutor.executeAsSystem(() -> {
      IUser currentUser = Ivy.session().getSessionUser();
      Locale userLanguage = Locale.forLanguageTag(language.getUserLanguage());
      currentUser.setLanguage(userLanguage);
      return Void.class;
    });
  }

}
