package ch.ivy.ws.addon.service;

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
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserEMailNotificationSettings;
import ch.ivyteam.ivy.server.IServer;
import ch.ivyteam.ivy.server.ServerFactory;

/**
 * @author lptchi
 *
 */
public class LanguagesSettingsServiceImpl extends AbstractService implements ILanguagesSettingsService {

  private final String CMS_LANG_KEY = "/AppInfo/SupportedLanguages";

  @Override
  public LanguagesSettingsServiceResult getLanguagesSettings(String username, List<String> apps, Long serverId) {
    LanguagesSettingsServiceResult result = new LanguagesSettingsServiceResult();
    List<WSException> errors = new ArrayList<WSException>();
    List<IvyLanguageSetting> settings = new ArrayList<IvyLanguageSetting>();
    if (apps != null && apps.size() > 0) {
      for (String appName : apps) {
        try {
          IvyLanguageSetting languageSetting = getUserLanguagesSettings(username, appName, serverId);
          if (languageSetting != null && languageSetting.getSupportedLanguages() != null
              && !languageSetting.getSupportedLanguages().isEmpty()) {
            settings.add(languageSetting);
          }
        } catch (Exception e) {
          if (e instanceof WSException) {
            errors.add((WSException) e);
          } else {
            Ivy.log().error(e);
          }
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
      throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<IvyLanguageSetting>() {
      @Override
      public IvyLanguageSetting call() throws Exception {
        boolean appFound = false;
        String lang = "";
        IvyLanguageSetting result = new IvyLanguageSetting();
        result.setAppName(appName);
        Set<String> supportedLanguages = new HashSet<String>();
        IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();
        IApplication app = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(appName);
        if (app != null && app.getActivityState() == ActivityState.ACTIVE && appName.equals(app.getName())) {
          // get processmodel of app
          List<IProcessModel> pms = app.getProcessModels();
          if (pms != null && pms.size() > 0) {
            for (IProcessModel pm : pms) {
              // get process model active
              if (pm.getActivityState() == ActivityState.ACTIVE) {
                // get processmodel version
                List<IProcessModelVersion> pmvs = pm.getProcessModelVersions();
                if (pmvs != null && pmvs.size() > 0) {
                  for (IProcessModelVersion pmv : pmvs) {
                    // get processmodel version active

                    if (pmv.getActivityState() == ActivityState.ACTIVE) {
                      // app found
                      appFound = true;
                      // get default settings
                      IUser user = app.getSecurityContext().findUser(username);
                      if (user != null) {
                        // default user settings???
                        if (user.getEMailNotificationSettings().isUseApplicationDefault()) {
                          Ivy.log().debug("default languages");
                          result.setUserLanguage(app.getDefaultEMailLanguage().toLanguageTag().toLowerCase());
                        } else {
                          if (user.getEMailLanguage() != null) {
                            result.setUserLanguage(user.getEMailLanguage().toLanguageTag().toLowerCase());
                          } else {
                            result.setUserLanguage(Locale.ENGLISH.toLanguageTag().toLowerCase());
                          }
                        }
                      } else {
                        // user not found
                        throw new WSException(WSErrorType.WARNING, 10029, Arrays.asList(username), null);
                      }

                      lang = server.getContentManagement().findCms(pmv).co(CMS_LANG_KEY);
                      
                      if (!StringUtils.isEmpty(lang)) {
                        String[] sp = lang.split(",");
                        for (String spItem : sp) {
                          supportedLanguages.add(spItem);
                        }
                        result.setSupportedLanguages(new ArrayList<>(supportedLanguages));
                      }
                    }
                  }
                }
              }
            }
          }
        }
        if (!appFound) {
          // app not found
          List<Object> userText = new ArrayList<Object>();
          userText.add(appName);
          throw new WSException(WSErrorType.WARNING, 10030, userText, null);
        }
        if (CollectionUtils.isEmpty(result.getSupportedLanguages()) && serverId > 0) {
          // key not found
          List<Object> userText = new ArrayList<Object>();
          userText.add(CMS_LANG_KEY);
          userText.add(appName);
          throw new WSException(WSErrorType.WARNING, 10040, userText, null);
        }
        return result;
      }
    });
  }

  @Override
  public LanguagesSettingsServiceResult setLanguagesSettings(String username, List<IvyLanguageSetting> settings) {
    LanguagesSettingsServiceResult rs = new LanguagesSettingsServiceResult();
    List<WSException> errors = new ArrayList<WSException>();
    if (settings != null && settings.size() > 0) {
      for (IvyLanguageSetting ivyLanguageSetting : settings) {
        try {
          errors.addAll(saveLanguagesSettings(ivyLanguageSetting.getAppName(), username, ivyLanguageSetting));
        } catch (Exception e) {
          if (e instanceof WSException) {
            errors.add((WSException) e);
          } else {
            Ivy.log().error(e);
          }
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
   * @param absences : list of absences to save
   * @throws Exception
   */
  private List<WSException> saveLanguagesSettings(final String appName, final String username,
      final IvyLanguageSetting setting) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<WSException>>() {
      @Override
      public List<WSException> call() throws Exception {
        List<WSException> errors = new ArrayList<WSException>();
        IServer server = ServerFactory.getServer();
        IApplication application =
            server.getApplicationConfigurationManager().findApplication(appName);

        if (application != null) {
          List<IProcessModelVersion> activePmvs = application.getProcessModels().stream().
              filter(pm -> pm.getActivityState() == ActivityState.ACTIVE).
              map(IProcessModel::getProcessModelVersions).flatMap(List::stream).
              filter(pmv -> pmv.getActivityState() == ActivityState.ACTIVE).collect(Collectors.toList());
          
          IUser user = application.getSecurityContext().findUser(username);
          if (user != null) {
            // default user settings
            Locale emailLanguage = Locale.forLanguageTag(setting.getUserLanguage());
            if (user.getEMailNotificationSettings().isUseApplicationDefault()) {
              IUserEMailNotificationSettings userEmailSettings = user.getEMailNotificationSettings();
              userEmailSettings.setUseApplicationDefault(false);
              // copy default settings
              user.setEMailLanguage(emailLanguage);
              userEmailSettings.setSendDailyTaskSummary(application.getDefaultEMailNotifcationSettings()
                  .getSendDailyTaskSummary());
              userEmailSettings.setNotificationDisabled(application.getDefaultEMailNotifcationSettings()
                  .isNotificationDisabled());
              userEmailSettings.setSendOnNewWorkTasks(application.getDefaultEMailNotifcationSettings()
                  .isSendOnNewWorkTasks());
              user.setEMailNotificationSettings(userEmailSettings);
            } else {
              List<Locale> supportedEmailLanguages = new ArrayList<>();
              activePmvs.forEach(pmv ->{                
                supportedEmailLanguages.addAll(server.getContentManagement().findCms(pmv).getSupportedLanguages());
              });
              
              user.setEMailLanguage(emailLanguage);
              if (!supportedEmailLanguages.contains(emailLanguage)){
                errors.add(new WSException(WSErrorType.WARNING, 10048, Arrays.asList(username), null));
              }
            }
          } else {
            // user not found
            errors.add(new WSException(WSErrorType.WARNING, 10029, Arrays.asList(username), null));
          }

        } else {
          // app not found
          errors.add(new WSException(WSErrorType.WARNING, 10030, Arrays.asList(username), null));
        }
        return errors;
      }
    });
  }
}
