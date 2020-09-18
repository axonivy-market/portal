package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.service.ILanguageService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.cm.IContentManagementSystem;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class LanguageService implements ILanguageService {

  private static final String CMS_LANG_KEY = "/AppInfo/SupportedLanguages";

  private LanguageService() {}

  public static LanguageService newInstance() {
    return new LanguageService();
  }

  @Override
  public IvyLanguageResultDTO findUserLanguages(String username, List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyLanguageResultDTO result = new IvyLanguageResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }

      List<PortalIvyDataException> errors = new ArrayList<>();
      List<IvyLanguage> ivyLanguages = new ArrayList<>();
      var applicationService = new RegisteredApplicationService();
      for (String appName : apps) {
        try {
          String appDisplayName = appName;
          Application savedApp = applicationService.findByName(appName);
          if (savedApp != null) {
            appDisplayName = ApplicationMultiLanguage.getDisplayNameInCurrentLocale(savedApp);
          }

          IvyLanguage ivyLanguage = getIvyLanguage(username, appName, appDisplayName);
          if (ivyLanguage != null) {
            ivyLanguages.add(ivyLanguage);
          }
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in getting user language of user {0} within app {1}", ex, username, appName);
          errors.add(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_LANGUAGE.toString()));
        }
      }
      result.setErrors(errors);
      result.setIvyLanguages(ivyLanguages);
      return result;
    });
  }

  private IvyLanguage getIvyLanguage(final String username, final String appName, final String appDisplayName) throws PortalIvyDataException {
    IApplication app = ServiceUtilities.findApp(appName);
    IUser user = ServiceUtilities.findUser(username, app);
    IvyLanguage ivyLanguage = new IvyLanguage();

    List<IProcessModelVersion> activeReleasedPmvs = ServiceUtilities.getActiveReleasedPmvs(app);
    List<String> supportedLanguages = getSupportedLanguagesFromPmvs(activeReleasedPmvs);
    if (CollectionUtils.isEmpty(supportedLanguages)) {
      throw new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.SUPPORTED_LANGUAGES_NOT_FOUND.toString());
    }

    ivyLanguage.setAppDisplayName(appDisplayName);
    ivyLanguage.setAppName(app.getName());
    ivyLanguage.setUserLanguage(getUserLanguage(user).toLowerCase());
    ivyLanguage.setSupportedLanguages(supportedLanguages);
    return ivyLanguage;
  }

  private String getUserLanguage(IUser user) {
    return user.getEMailLanguage() != null ? user.getEMailLanguage().toLanguageTag() : Locale.ENGLISH.toLanguageTag();
  }

  private List<String> getSupportedLanguagesFromPmvs(List<IProcessModelVersion> pmvs) {
    Set<String> supportedLanguages = new HashSet<>();
    for (IProcessModelVersion pmv : pmvs) {
      IContentManagementSystem findCms = getServer().getContentManagement().findCms(pmv);
      if (findCms == null) {
        continue;
      }
      String lang = findCms.co(CMS_LANG_KEY);

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
  public IvyLanguageResultDTO saveUserLanguages(String username, List<IvyLanguage> languages) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyLanguageResultDTO rs = new IvyLanguageResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      if (CollectionUtils.isNotEmpty(languages)) {
        for (IvyLanguage language : languages) {
          try {
            IApplication app = ServiceUtilities.findApp(language.getAppName());
            IUser user = ServiceUtilities.findUser(username, app);
            List<IProcessModelVersion> activePmvs = ServiceUtilities.getActiveReleasedPmvs(app);
            Locale userLanguage = Locale.forLanguageTag(language.getUserLanguage());
            if (!getSupportedEmailLanguages(activePmvs).contains(userLanguage)) { 
              errors.add(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.SUPPORTED_LANGUAGES_NOT_FOUND.toString()));
              continue;
            }

            user.setEMailLanguage(userLanguage);
          } catch (PortalIvyDataException e) {
            errors.add(e);
          } catch (Exception ex) {
            Ivy.log().error("Error in saving user language of user {0} within app {1}", ex, username, language.getAppName());
            errors.add(new PortalIvyDataException(language.getAppName(), PortalIvyDataErrorType.FAIL_TO_SAVE_LANGUAGE.toString()));
          }
        }
      }
      rs.setErrors(errors);
      return rs;
    });
  }

  private Set<Locale> getSupportedEmailLanguages(List<IProcessModelVersion> activePmvs) {
    Set<Locale> supportedEmailLanguages = new HashSet<>();
    activePmvs.forEach(pmv -> {
      IContentManagementSystem findCms = getServer().getContentManagement().findCms(pmv);
      if (findCms != null) {
        supportedEmailLanguages.addAll(findCms.getSupportedLanguages());
      }
    });

    return supportedEmailLanguages;
  }

  @Override
  public IvyLanguageResultDTO getSupportedLanguages(String appName) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyLanguageResultDTO result = new IvyLanguageResultDTO();
      
      List<PortalIvyDataException> errors = new ArrayList<>();
      List<IvyLanguage> ivyLanguages = new ArrayList<>();
      
      try {
        IApplication app = ServiceUtilities.findApp(appName);
        IvyLanguage ivyLanguage = new IvyLanguage();

        List<IProcessModelVersion> activeReleasedPmvs = ServiceUtilities.getActiveReleasedPmvs(app);
        List<String> supportedLanguages = getSupportedLanguagesFromPmvs(activeReleasedPmvs);
        if (CollectionUtils.isEmpty(supportedLanguages)) {
          errors.add(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.SUPPORTED_LANGUAGES_NOT_FOUND.toString()));
        }

        ivyLanguage.setAppName(app.getName());
        ivyLanguage.setSupportedLanguages(supportedLanguages);
        
        ivyLanguages.add(ivyLanguage);      
        result.setErrors(errors);
        result.setIvyLanguages(ivyLanguages);
      } catch (Exception e) {
        Ivy.log().error("Error load language for application {0}", e, appName);
        errors.add(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_LANGUAGE.toString()));
      }
      return result;
    });
  }
}
