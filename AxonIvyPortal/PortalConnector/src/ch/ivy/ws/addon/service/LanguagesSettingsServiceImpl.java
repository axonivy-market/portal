package ch.ivy.ws.addon.service;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.LanguagesSettingsServiceResult;
import ch.ivy.ws.addon.types.IvyLanguageSetting;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.cm.IContentManagementSystem;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserEMailNotificationSettings;
import ch.ivyteam.ivy.server.IServer;
import ch.ivyteam.ivy.server.ServerFactory;

/**
 * Get and set user setting for language
 * 
 *
 */
public class LanguagesSettingsServiceImpl extends AbstractService implements ILanguagesSettingsService {

  private static final String CMS_LANG_KEY = "/AppInfo/SupportedLanguages";

  @Override
  public LanguagesSettingsServiceResult getLanguagesSettings(String username, List<String> apps, Long serverId) {
    LanguagesSettingsServiceResult result = new LanguagesSettingsServiceResult();
    List<WSException> errors = new ArrayList<>();
    List<IvyLanguageSetting> settings = new ArrayList<>();
    if (apps != null && !apps.isEmpty()) {
      for (String appName : apps) {
        try {
          IvyLanguageSetting languageSetting = getUserLanguagesSettings(username, appName, serverId);
          if (languageSetting != null && languageSetting.getSupportedLanguages() != null
              && !languageSetting.getSupportedLanguages().isEmpty()) {
            settings.add(languageSetting);
          }
        } catch (WSException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error(ex);
        }
      }
    }
    result.setErrors(errors);
    result.setSettings(settings);
    return result;
  }

  /**
   * Get IvyLanguageSetting of passed user in list of applications
   * 
   * @param username
   * @param appName
   * @return
   * @throws Exception
   */
  @SuppressWarnings("javadoc")
  public IvyLanguageSetting getUserLanguagesSettings(final String username, final String appName, final Long serverId)
      throws Exception { // NOSONAR
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<IvyLanguageSetting>() {
      @Override
      public IvyLanguageSetting call() throws Exception {
        IvyLanguageSetting result = ivyLanguageSetting(appName);
        IApplication app = getServer().getApplicationConfigurationManager().findApplication(appName);
        if (app != null && app.getActivityState() == ActivityState.ACTIVE && StringUtils.equals(appName, app.getName())) {
          setUserLaguagesSettings(username, result, app);
        } else {
          // app not found
          throw new WSException(WSErrorType.WARNING, 10030, Arrays.asList(appName), null);
        }
        if (CollectionUtils.isEmpty(result.getSupportedLanguages()) && serverId > 0) {
          // key not found
          throw new WSException(WSErrorType.WARNING, 10040, Arrays.asList(CMS_LANG_KEY, appName), null);
        }
        return result;
      }
    });
  }

  private void setUserLaguagesSettings(final String username, IvyLanguageSetting result, IApplication app)
      throws WSException {
    List<IProcessModelVersion> activeReleasedPmvs = getActiveReleasedPmvs(app);
    IUser user = app.getSecurityContext().findUser(username);
    if (user != null) {
      result.setUserLanguage(getUserLanguage(user).toLowerCase());
    } else {
      // user not found
      throw new WSException(WSErrorType.WARNING, 10029, Arrays.asList(username), null);
    }
    result.setSupportedLanguages(getSupportedLanguagesFromPmvs(activeReleasedPmvs, getServer()));
  }

  private IvyLanguageSetting ivyLanguageSetting(final String appName) {
    IvyLanguageSetting result = new IvyLanguageSetting();
    result.setAppName(appName);
    return result;
  }

  private String getUserLanguage(IUser user) {
    return user.getEMailLanguage() != null ? user.getEMailLanguage().toLanguageTag() : Locale.ENGLISH.toLanguageTag();
  }

  private List<String> getSupportedLanguagesFromPmvs(List<IProcessModelVersion> pmvs, IServer server) {
    Set<String> supportedLanguages = new HashSet<>();
    for (IProcessModelVersion pmv : pmvs) {
      String lang;
      IContentManagementSystem findCms = server.getContentManagement().findCms(pmv);
      if (findCms != null) {
        lang = findCms.co(CMS_LANG_KEY);

        if (!StringUtils.isEmpty(lang)) {
          String[] sp = lang.split(",");
          for (String spItem : sp) {
            String languageCode = spItem.trim();
            if (!supportedLanguages.contains(languageCode)) {
              supportedLanguages.add(languageCode);
            }
          }
        }
      }
    }
    return new ArrayList<>(supportedLanguages);
  }

  private List<IProcessModelVersion> getActiveReleasedPmvs(IApplication app) {
    return app.getProcessModels().stream().filter(pm -> pm.getActivityState() == ActivityState.ACTIVE)
        .map(IProcessModel::getReleasedProcessModelVersion)
        .filter(pmv -> pmv != null && pmv.getActivityState() == ActivityState.ACTIVE).collect(Collectors.toList());
  }

  @Override
  public LanguagesSettingsServiceResult setLanguagesSettings(String username, List<IvyLanguageSetting> settings) {
    LanguagesSettingsServiceResult rs = new LanguagesSettingsServiceResult();
    List<WSException> errors = new ArrayList<>();
    if (settings != null && !settings.isEmpty()) {
      for (IvyLanguageSetting ivyLanguageSetting : settings) {
        try {
          errors.addAll(saveLanguagesSettings(ivyLanguageSetting.getAppName(), username, ivyLanguageSetting));
        } catch (WSException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error(ex);
        }
      }
    }
    rs.setErrors(errors);
    return rs;
  }

  /**
   * Save languages settings for passed user in passed application
   * 
   * @param appName
   * @param username
   */
  private List<WSException> saveLanguagesSettings(final String appName, final String username,
      final IvyLanguageSetting setting) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<WSException>>() {
        @Override
        public List<WSException> call() throws Exception {
          IApplication application = getServer().getApplicationConfigurationManager().findApplication(appName);
          if (application == null) {
            return Arrays.asList(new WSException(WSErrorType.WARNING, 10030, Arrays.asList(username), null));
          }
          return saveLanguagesSettings(username, setting, application);
        }
      });
    } catch (Exception ex) {
      throw new WSException(WSErrorType.WARNING, 10048, ex, Arrays.asList(username), null);
    }
  }

  private List<WSException> saveLanguagesSettings(final String username, final IvyLanguageSetting setting,
      IApplication application) {
    List<WSException> errors = new ArrayList<>();
    List<IProcessModelVersion> activePmvs = getActiveReleasedPmvs(application);
    List<Locale> supportedEmailLanguages = getSupportedEmailLanguages(activePmvs);
    IUser user = application.getSecurityContext().findUser(username);
    if (user != null) {
      Locale emailLanguage = getDefaultUserSettings(setting, application, user);
      user.setEMailLanguage(emailLanguage);
      if (!supportedEmailLanguages.contains(emailLanguage)) {
        errors.add(new WSException(WSErrorType.WARNING, 10048, Arrays.asList(username), null));
      }
    } else {
      // user not found
      errors.add(new WSException(WSErrorType.WARNING, 10029, Arrays.asList(username), null));
    }
    return errors;
  }

  private List<Locale> getSupportedEmailLanguages(List<IProcessModelVersion> activePmvs) {
    List<Locale> supportedEmailLanguages = new ArrayList<>();
    activePmvs.forEach(pmv -> {
      IContentManagementSystem findCms = getServer().getContentManagement().findCms(pmv);
      if (findCms != null) {
        supportedEmailLanguages.addAll(findCms.getSupportedLanguages());
      }
    });
    return supportedEmailLanguages;
  }

  private Locale getDefaultUserSettings(final IvyLanguageSetting setting, IApplication application, IUser user) {
    // default user settings
    Locale emailLanguage = Locale.forLanguageTag(setting.getUserLanguage());
    if (user.getEMailNotificationSettings().isUseApplicationDefault()) {
      IUserEMailNotificationSettings userEmailSettings = user.getEMailNotificationSettings();
      userEmailSettings.setUseApplicationDefault(false);
      // copy default settings
      userEmailSettings.setSendDailyTaskSummary(application.getDefaultEMailNotifcationSettings()
          .getSendDailyTaskSummary());
      userEmailSettings.setNotificationDisabled(application.getDefaultEMailNotifcationSettings()
          .isNotificationDisabled());
      userEmailSettings.setSendOnNewWorkTasks(application.getDefaultEMailNotifcationSettings().isSendOnNewWorkTasks());
      user.setEMailNotificationSettings(userEmailSettings);
    }
    return emailLanguage;
  }
}
