package ch.ivy.addon.portalkit.ivydata.service.impl;

import static ch.ivy.addon.portalkit.constant.UserProperty.ENABLE_CUSTOM_MAIL;

import java.util.EnumSet;

import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.IEmailSettingService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IEMailNotificationSettings;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserEMailNotificationSettings;

public class EmailSettingService implements IEmailSettingService {

  private static final String OLD_VAR_DISABLE_CUSTOM_MAIL = "DisableCustomMails";

  private EmailSettingService() {}

  public static EmailSettingService newInstance() {
    return new EmailSettingService();
  }

  private IvyEmailSetting getIvyEmailSetting() {
    IApplication app = Ivy.request().getApplication();
    IUser user = Ivy.session().getSessionUser();

    IUserEMailNotificationSettings emailSettings = user.getEMailNotificationSettings();
    IvyEmailSetting ivyEmailSetting = convertToIvyEmailSetting(
        emailSettings.isUseApplicationDefault() ? app.getDefaultEMailNotifcationSettings() : emailSettings);

    // set value for useCustomMails base on property in IUser
    // In old versions of Portal, we use property disableCustomMails instead of useCustomMails.
    // To make Portal more compatible and easier to migrate, we decided not to ignore old property
    // disableCustomMails.
    boolean useCustomMailVariable = isUsingNewCustomVariable(user) || isUsingOldCustomVariable(user);
    ivyEmailSetting.setCustomMailEnabled(useCustomMailVariable);
    ivyEmailSetting.setAppName(app.getName());
    return ivyEmailSetting;
  }

  private boolean isUsingOldCustomVariable(IUser iuser) {
    return iuser.getProperty(OLD_VAR_DISABLE_CUSTOM_MAIL) != null
        && Boolean.TRUE.toString().equalsIgnoreCase(iuser.getProperty(OLD_VAR_DISABLE_CUSTOM_MAIL));
  }

  private boolean isUsingNewCustomVariable(IUser iuser) {
    return iuser.getProperty(ENABLE_CUSTOM_MAIL) != null
        && Boolean.TRUE.toString().equalsIgnoreCase(iuser.getProperty(ENABLE_CUSTOM_MAIL));
  }

  private IvyEmailSetting convertToIvyEmailSetting(IEMailNotificationSettings emailSettings) {
    IvyEmailSetting ivyEmailSetting = new IvyEmailSetting();
    ivyEmailSetting.setEmailSendOnNewWorkTasks(emailSettings.isSendOnNewWorkTasks());
    ivyEmailSetting.setEmailSendDailyTaskSummary(emailSettings.getSendDailyTaskSummary());
    return ivyEmailSetting;
  }

  @Override
  public IvyEmailSettingResultDTO saveEmailSetting(IvyEmailSetting emailSetting) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyEmailSettingResultDTO rs = new IvyEmailSettingResultDTO();

      IUser user = Ivy.session().getSessionUser();
      IUserEMailNotificationSettings userEmailSettings = user.getEMailNotificationSettings();

      userEmailSettings.setNotificationDisabled(false);
      userEmailSettings.setSendDailyTaskSummary(EnumSet.copyOf(emailSetting.getEmailSendDailyTaskSummary()));
      userEmailSettings.setSendOnNewWorkTasks(emailSetting.isEmailSendOnNewWorkTasks());
      if (emailSetting.isCustomMailEnabled()) {
        user.setProperty(ENABLE_CUSTOM_MAIL, String.valueOf(emailSetting.isCustomMailEnabled()));
      } else {
        user.removeProperty(ENABLE_CUSTOM_MAIL);
        user.removeProperty(OLD_VAR_DISABLE_CUSTOM_MAIL);
      }
      userEmailSettings.setUseApplicationDefault(false);
      user.setEMailNotificationSettings(userEmailSettings);
      return rs;
    });
  }

  public void displayDailySummary(IvyEmailSetting emailSetting) {
    if (emailSetting.getEmailSendDailyTaskSummary().size() > 0) {
      emailSetting.setEnableDailySummary(true);
    }
  }

  @Override
  public IvyEmailSettingResultDTO findEmailSetting() {
    return IvyExecutor.executeAsSystem(() -> {
      IvyEmailSettingResultDTO result = new IvyEmailSettingResultDTO();
      result.setIvyEmailSetting(getIvyEmailSetting());
      return result;
    });
  }
}
