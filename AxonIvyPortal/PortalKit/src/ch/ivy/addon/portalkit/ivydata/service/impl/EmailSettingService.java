package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.service.IEmailSettingService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IEMailNotificationSettings;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserEMailNotificationSettings;

public class EmailSettingService implements IEmailSettingService {

  private static final String ENABLE_CUSTOM_MAIL = "useCustomMails";
  private static final String OLD_VAR_DISABLE_CUSTOM_MAIL = "DisableCustomMails";

  private EmailSettingService() {}

  public static EmailSettingService newInstance() {
    return new EmailSettingService();
  }

  private IvyEmailSetting getIvyEmailSetting(final String username, final String appName)
      throws PortalIvyDataException {
    IApplication app = ServiceUtilities.findApp(appName);
    IUser user = ServiceUtilities.findUser(username, app);

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
  public IvyEmailSettingResultDTO saveEmailSetting(String username, IvyEmailSetting emailSetting) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyEmailSettingResultDTO rs = new IvyEmailSettingResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();

      try {
        IApplication app = ServiceUtilities.findApp(emailSetting.getAppName());
        IUser user = ServiceUtilities.findUser(username, app);
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
      } catch (PortalIvyDataException e) {
        errors.add(e);
      } catch (Exception ex) {
        Ivy.log().error("Error in saving email settings of user {0} within app {1}", ex, username,
            emailSetting.getAppName());
        errors.add(new PortalIvyDataException(emailSetting.getAppName(),
            PortalIvyDataErrorType.FAIL_TO_SAVE_EMAIL_SETTING.toString()));
      }
      rs.setErrors(errors);
      return rs;
    });
  }

  public void displayDailySummary(IvyEmailSetting emailSetting) {
    if (emailSetting.getEmailSendDailyTaskSummary().size() > 0) {
      emailSetting.setEnableDailySummary(true);
    }
  }

  @Override
  public IvyEmailSettingResultDTO findEmailSetting(String username) {
    return IvyExecutor.executeAsSystem(() -> {
      IvyEmailSettingResultDTO result = new IvyEmailSettingResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      String appName = Ivy.wf().getApplication().getName();
      try {
        result.setIvyEmailSetting(getIvyEmailSetting(username, appName));
      } catch (PortalIvyDataException e) {
        errors.add(e);
      } catch (Exception ex) {
        Ivy.log().error("Error in getting email settings of user {0} within app {1}", ex, username, appName);
        errors.add(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_EMAIL_SETTING.toString()));
      }
      result.setErrors(errors);
      return result;
    });
  }
}
