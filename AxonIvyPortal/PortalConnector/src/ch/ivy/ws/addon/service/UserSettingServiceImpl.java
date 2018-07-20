package ch.ivy.ws.addon.service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
import ch.ivy.ws.addon.bo.AvailableAppsResult;
import ch.ivy.ws.addon.bo.UserSettingServiceResult;
import ch.ivy.ws.addon.types.IvyEmailSetting;
import ch.ivy.ws.addon.types.IvyUserSetting;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IEMailNotificationSettings;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserEMailNotificationSettings;
import ch.ivyteam.util.date.Weekday;

public class UserSettingServiceImpl extends AbstractService implements IUserSettingService {
  private static String ENABLE_CUSTOM_MAIL = "useCustomMails";
  private static String OLD_VAR_DISABLE_CUSTOM_MAIL = "DisableCustomMails";

  /**
   * 
   * @param user
   * @return IvyUserSetting
   * @throws WSException
   */
  private IvyUserSetting findUserSetting(IUser user) throws WSException {

    IvyUserSetting result = new IvyUserSetting();
    try {
      if (user != null) {
        IUserEMailNotificationSettings emailSettings = user.getEMailNotificationSettings();

        result.setEmailNotificationDisabled(emailSettings.isNotificationDisabled());

        if (emailSettings.getSendDailyTaskSummary().contains(Weekday.MONDAY)) {
          result.setEmailSendDailyTaskSummaryOnMonday(true);
        }

        result.setEmailSendDailyTaskSummaryOnMonday(emailSettings.getSendDailyTaskSummary().contains(Weekday.MONDAY));
        result.setEmailSendDailyTaskSummaryOnTuesday(emailSettings.getSendDailyTaskSummary().contains(Weekday.TUESDAY));
        result.setEmailSendDailyTaskSummaryOnWednesday(emailSettings.getSendDailyTaskSummary().contains(
            Weekday.WEDNESDAY));
        result.setEmailSendDailyTaskSummaryOnThursday(emailSettings.getSendDailyTaskSummary()
            .contains(Weekday.THURSDAY));
        result.setEmailSendDailyTaskSummaryOnFriday(emailSettings.getSendDailyTaskSummary().contains(Weekday.FRIDAY));
        result.setEmailSendDailyTaskSummaryOnSaturday(emailSettings.getSendDailyTaskSummary()
            .contains(Weekday.SATURDAY));
        result.setEmailSendDailyTaskSummaryOnSunday(emailSettings.getSendDailyTaskSummary().contains(Weekday.SUNDAY));

        result.setEmailSendOnNewWorkTasks(emailSettings.isSendOnNewWorkTasks());
        result.setUseUserEmailSettings(!emailSettings.isUseApplicationDefault());
        if (user.getEMailLanguage() != null) {
          result.setLanguage(user.getEMailLanguage().getLanguage());
        }
      }
    } catch (Exception e) {
      throw new WSException(10018, e);
    }
    return result;
  }

  /**
   * 
   * @param user
   * @param setting
   * @throws WSException
   */
  private void saveUserSetting(IUser user, IvyUserSetting setting) throws WSException {
    try {
      if (user != null) {
        IUserEMailNotificationSettings s = user.getEMailNotificationSettings();
        if (s != null) {
          if (setting.getEmailNotificationDisabled() != null)
            s.setNotificationDisabled(setting.getEmailNotificationDisabled());
          
          s.setSendDailyTaskSummaryOnDay(Weekday.MONDAY, BooleanUtils.toBoolean(setting.getEmailSendDailyTaskSummaryOnMonday()));
          s.setSendDailyTaskSummaryOnDay(Weekday.TUESDAY, BooleanUtils.toBoolean(setting.getEmailSendDailyTaskSummaryOnTuesday()));
          s.setSendDailyTaskSummaryOnDay(Weekday.WEDNESDAY, BooleanUtils.toBoolean(setting.getEmailSendDailyTaskSummaryOnWednesday()));
          s.setSendDailyTaskSummaryOnDay(Weekday.THURSDAY, BooleanUtils.toBoolean(setting.getEmailSendDailyTaskSummaryOnThursday()));
          s.setSendDailyTaskSummaryOnDay(Weekday.FRIDAY, BooleanUtils.toBoolean(setting.getEmailSendDailyTaskSummaryOnFriday()));
          s.setSendDailyTaskSummaryOnDay(Weekday.SATURDAY, BooleanUtils.toBoolean(setting.getEmailSendDailyTaskSummaryOnSaturday()));
          s.setSendDailyTaskSummaryOnDay(Weekday.SUNDAY, BooleanUtils.toBoolean(setting.getEmailSendDailyTaskSummaryOnSunday()));

          s.setUseApplicationDefault(!BooleanUtils.toBoolean(setting.getUseUserEmailSettings()));
          s.setSendOnNewWorkTasks(BooleanUtils.toBoolean(setting.getEmailSendOnNewWorkTasks()));

          if (setting.getLanguage() != null) {
            if (setting.getLanguage().equalsIgnoreCase("de")) {
              user.setEMailLanguage(Locale.GERMAN);
            } else {
              user.setEMailLanguage(Locale.ENGLISH);
            }
          }
          user.setEMailNotificationSettings(s);
        }
      }
    } catch (Exception e) {
      throw new WSException(10019, e);
    }
  }

  @Override
  public UserSettingServiceResult findUserSetting(String username, String appName) throws WSException {
    UserSettingServiceResult result = new UserSettingServiceResult();
    try {
      List<String> apps = new ArrayList<String>();
      apps.add(appName);

      List<IUser> users = findUsers(apps, username);
      if (users.size() > 0) {
        result.setUserSetting(findUserSetting(users.get(0)));
      }
    } catch (Exception e) {
      throw new WSException(10020, e);
    }
    return result;
  }

  @Override
  public void saveUserSetting(String username, IvyUserSetting setting, String appName) throws WSException {
    try {
      List<String> apps = new ArrayList<String>();
      apps.add(appName);

      List<IUser> users = findUsers(apps, username);
      if (users.size() > 0) {
        saveUserSetting(users.get(0), setting);
      }
    } catch (Exception e) {
      throw new WSException(10019, e);
    }

  }

  @Override
  public UserSettingServiceResult getEMailSettings(final List<String> applications, final String user) throws WSException{
    try {
      return executeAsSystem(new Callable<UserSettingServiceResult>() {
        @Override
        public UserSettingServiceResult call() throws WSException {
          UserSettingServiceResult result = new UserSettingServiceResult();
          List<IvyEmailSetting> settings = new ArrayList<IvyEmailSetting>();
          List<WSException> errors = new ArrayList<WSException>();
          List<String> availableApps = new ArrayList<String>();
          try {
            if (user != null && StringUtils.isNotBlank(user)) {
              AvailableAppsResult aaResult = findAvailableApplicationsForUser(applications, user);
              errors.addAll(aaResult.getErrors());
              availableApps.addAll(aaResult.getAvailableApps());
              List<IApplication> allApplication = getApplications();
              for (IApplication serverApp : allApplication) {
                IvyEmailSetting setting = new IvyEmailSetting();
                if (availableApps.contains(serverApp.getName())) {
                  if (serverApp.getSecurityContext().findUser(user) != null) {
                    IUser iuser = serverApp.getSecurityContext().findUser(user);
                    setting.setAppName(serverApp.getName());

                    // set value for useCustomMails base on property in IUser
                    boolean useNewCustomMailVariable = iuser.getProperty(ENABLE_CUSTOM_MAIL) != null
                        && Boolean.TRUE.toString().equalsIgnoreCase(iuser.getProperty(ENABLE_CUSTOM_MAIL));

                    // In old versions of Portal, we use property disableCustomMails instead of useCustomMails.
                    // To make Portal more compatible and easier to migrate, we decided not to ignore old property disableCustomMails.
                    boolean useOldCustomMailVariable = iuser.getProperty(OLD_VAR_DISABLE_CUSTOM_MAIL) != null
                        && Boolean.TRUE.toString().equalsIgnoreCase(iuser.getProperty(OLD_VAR_DISABLE_CUSTOM_MAIL));

                    if (useNewCustomMailVariable || useOldCustomMailVariable) {
                      setting.setCustomMailEnabled(true);
                    } else {
                      setting.setCustomMailEnabled(false);
                    }

                    IUserEMailNotificationSettings emailSettings = iuser.getEMailNotificationSettings();
                    // if user settings is set to default, return default settings of the application
                    if (emailSettings.isUseApplicationDefault()) {
                      IEMailNotificationSettings defaultSettings =
                          Ivy.wf().getApplication().getDefaultEMailNotifcationSettings();
                      if (defaultSettings != null) {
                        setEmailSettingFromEmail(setting, defaultSettings);
                      }
                    } else {
                      setEmailSettingFromUserEmail(setting, emailSettings);
                    }
                    settings.add(setting);
                  }
                }
                result.setEmailSettings(settings);
              }
            } else {
              // Username not given
              List<Object> userText = new ArrayList<Object>();
              userText.add(user);
              errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
            }
            result.setErrors(errors);

          } catch (Exception e) {
            throw new WSException(10032, e);
          }
          return result;
        }
      });
    } catch (Exception ex) {
      throw new WSException(10032, ex);
    }
  }
  
  private void setEmailSettingFromEmail(IvyEmailSetting setting, IEMailNotificationSettings defaultSettings) {
    setting.setEmailNotificationDisabled(defaultSettings.isNotificationDisabled());
    setting.setEmailSendOnNewWorkTasks(defaultSettings.isSendOnNewWorkTasks());
    setting.setEmailSendDailyTaskSummaryOnMonday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.MONDAY));
    setting.setEmailSendDailyTaskSummaryOnTuesday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.TUESDAY));
    setting.setEmailSendDailyTaskSummaryOnWednesday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.WEDNESDAY));
    setting.setEmailSendDailyTaskSummaryOnThursday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.THURSDAY));
    setting.setEmailSendDailyTaskSummaryOnFriday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.FRIDAY));
    setting.setEmailSendDailyTaskSummaryOnSaturday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.SATURDAY));
    setting.setEmailSendDailyTaskSummaryOnSunday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.SUNDAY));
  }
  
  private void setEmailSettingFromUserEmail(IvyEmailSetting setting, IUserEMailNotificationSettings emailSettings) {
    setting.setEmailNotificationDisabled(emailSettings.isNotificationDisabled());
    setting.setEmailSendOnNewWorkTasks(emailSettings.isSendOnNewWorkTasks());
    setting.setEmailSendDailyTaskSummaryOnMonday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.MONDAY));
    setting.setEmailSendDailyTaskSummaryOnTuesday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.TUESDAY));
    setting.setEmailSendDailyTaskSummaryOnWednesday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.WEDNESDAY));
    setting.setEmailSendDailyTaskSummaryOnThursday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.THURSDAY));
    setting.setEmailSendDailyTaskSummaryOnFriday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.FRIDAY));
    setting.setEmailSendDailyTaskSummaryOnSaturday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.SATURDAY));
    setting.setEmailSendDailyTaskSummaryOnSunday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.SUNDAY));
  }

  @Override
  public List<WSException> setEMailSettings(final List<IvyEmailSetting> settings, final String user) throws WSException {
    try {
      return executeAsSystem(new Callable<List<WSException>>() {
        @Override
        public List<WSException> call() throws WSException {
          List<WSException> errors = new ArrayList<WSException>();
          try {
            for (IvyEmailSetting setting : settings) {
              if (setting != null) {
                IApplication serverApp = findApplication(setting.getAppName());
                if (serverApp != null) {
                  IUser iuser = serverApp.getSecurityContext().findUser(user);
                  if (iuser != null) {
                    /*
                     * IUserEMailNotificationSettings emailSettings = iuser.getEMailNotificationSettings(); // If change
                     * "use user settings" from true to false, copy default language setting from application's default if
                     * (emailSettings != null && !emailSettings.isUseApplicationDefault()) {
                     * iuser.setEMailLanguage(serverApp.getDefaultEMailLanguage()); }
                     */
                    // set value for email settings on the server
                    iuser.setEMailNotificationSettings(convertFromIvyEmailSettingToIUserEMailNotificationSettings(iuser,
                        setting));
                    if (setting.getCustomMailEnabled() != null) {
                      iuser.setProperty(ENABLE_CUSTOM_MAIL, setting.getCustomMailEnabled().toString());
                    }
                  } else {
                    List<Object> userText = new ArrayList<Object>();
                    userText.add(user);
                    errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
                  }
                } else {
                  List<Object> userText = new ArrayList<Object>();
                  userText.add(setting.getAppName());
                  errors.add(new WSException(WSErrorType.WARNING, 10030, userText, null));
                }
              }
            }
            return errors;
          } catch (Exception e) {
            throw new WSException(10033, e);
          }
        }
      });
    } catch (Exception ex) {
      throw new WSException(10033, ex);
    }
  }


  @Override
  public List<WSException> changePassword(final List<String> apps, final String username, final String password)
      throws WSException {
    try {
      return executeAsSystem(new Callable<List<WSException>>() {
        @Override
        public List<WSException> call() throws Exception {

          List<WSException> errors = new ArrayList<WSException>();
          List<IApplication> allApplications = getApplications();
          for (IApplication app : allApplications) {
            if (apps.contains(app.getName())) {
              IUser user = app.getSecurityContext().findUser(username);
              if (user != null) {
                user.setPassword(password);
              } else {
                errors.add(new WSException(WSErrorType.WARNING, 10029, Stream.of(username).collect(toList()), null));
              }
            }
          }
          return errors;
        }
      });
    } catch (Exception e) {
      throw new WSException(10047, e);
    }
  }

  /**
   * convert from IvyEmailSetting to IUserEMailNotificationSettings
   * 
   * @param user
   * @param setting
   */
  private IUserEMailNotificationSettings convertFromIvyEmailSettingToIUserEMailNotificationSettings(IUser user,
      IvyEmailSetting setting) {
    IUserEMailNotificationSettings out = null;
    if (user != null) {
      out = user.getEMailNotificationSettings();
      if (setting != null) {
        // Always set false in case of user setting.
        out.setNotificationDisabled(false);
        if (setting.getEmailSendOnNewWorkTasks() != null) {
          out.setSendOnNewWorkTasks(setting.getEmailSendOnNewWorkTasks());
        }
        
        setUserEmailDailyTaskSettingValue(Weekday.MONDAY, setting.getEmailSendDailyTaskSummaryOnMonday(), out);
        setUserEmailDailyTaskSettingValue(Weekday.TUESDAY, setting.getEmailSendDailyTaskSummaryOnTuesday(), out);
        setUserEmailDailyTaskSettingValue(Weekday.WEDNESDAY, setting.getEmailSendDailyTaskSummaryOnWednesday(), out);
        setUserEmailDailyTaskSettingValue(Weekday.THURSDAY, setting.getEmailSendDailyTaskSummaryOnThursday(), out);
        setUserEmailDailyTaskSettingValue(Weekday.FRIDAY, setting.getEmailSendDailyTaskSummaryOnFriday(), out);
        setUserEmailDailyTaskSettingValue(Weekday.SATURDAY, setting.getEmailSendDailyTaskSummaryOnSaturday(), out);
        setUserEmailDailyTaskSettingValue(Weekday.SUNDAY, setting.getEmailSendDailyTaskSummaryOnSunday(), out);
        out.setUseApplicationDefault(false);
      }
    }
    return out;
  }
  
  private void setUserEmailDailyTaskSettingValue(Weekday weekday, Boolean sendTaskSummary,
      IUserEMailNotificationSettings userSetting) {
    if (sendTaskSummary != null) {
      userSetting.setSendDailyTaskSummaryOnDay(weekday, sendTaskSummary);
    }
  }
}
