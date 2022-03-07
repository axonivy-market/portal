package portalmigration.ivydata.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import portalmigration.ivydata.bo.IvyLanguage;
import portalmigration.ivydata.dto.IvyLanguageResultDTO;
import portalmigration.ivydata.service.ILanguageService;
import portalmigration.ivydata.utils.ServiceUtilities;
import portalmigration.util.IvyExecutor;

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
    IApplication currentApp = Ivy.request().getApplication();
    List<IProcessModelVersion> activeReleasedPmvs = ServiceUtilities.getActiveReleasedPmvs(currentApp);
    List<String> supportedLanguages = getSupportedLanguagesFromPmvs(activeReleasedPmvs);
    if (CollectionUtils.isEmpty(supportedLanguages)) {
      supportedLanguages.add(currentApp.getDefaultEMailLanguage().toString());
    }

    ivyLanguage.setAppName(currentApp.getName());
    ivyLanguage.setSupportedLanguages(supportedLanguages);
    
    return ivyLanguage;
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
