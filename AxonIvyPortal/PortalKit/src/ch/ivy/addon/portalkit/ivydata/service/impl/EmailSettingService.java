package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.service.IEmailSettingService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.persistence.dao.ApplicationDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IEMailNotificationSettings;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserEMailNotificationSettings;

public class EmailSettingService implements IEmailSettingService {

  private static final String ENABLE_CUSTOM_MAIL = "useCustomMails";
  private static final String OLD_VAR_DISABLE_CUSTOM_MAIL = "DisableCustomMails";
  
  private EmailSettingService() {
  }
  
  public static EmailSettingService newInstance() {
    return new EmailSettingService();
  }
  
  @Override
  public IvyEmailSettingResultDTO findEmailSetting(String username, List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyEmailSettingResultDTO result = new IvyEmailSettingResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }
      
      List<PortalIvyDataException> errors = new ArrayList<>();
      List<IvyEmailSetting> ivyEmailSettings = new ArrayList<>();
      List<Application> applications = new ApplicationDao().findByNames(apps);
      for (String appName : apps) {
        try {
          String appDisplayName = applications.stream()
              .filter(app -> StringUtils.equals(app.getName(), appName))
              .map(ApplicationMultiLanguage::getDisplayNameInCurrentLocale)
              .findFirst()
              .orElse(appName);
          ivyEmailSettings.add(getIvyEmailSetting(username, appName, appDisplayName));
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in getting email settings of user {0} within app {1}", ex, username, appName);
          errors.add(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_EMAIL_SETTING.toString()));
        }
      }
      result.setErrors(errors);
      result.setIvyEmailSettings(ivyEmailSettings);
      return result;
    });
  }
  
  private IvyEmailSetting getIvyEmailSetting(final String username, final String appName, final String appDislayName) throws PortalIvyDataException {
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
    ivyEmailSetting.setAppDisplayName(appDislayName);
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
  public IvyEmailSettingResultDTO saveEmailSetting(String username, List<IvyEmailSetting> emailSettings) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyEmailSettingResultDTO rs = new IvyEmailSettingResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      if (CollectionUtils.isNotEmpty(emailSettings)) {
        for (IvyEmailSetting emailSetting : emailSettings) {
          try {
            IApplication app = ServiceUtilities.findApp(emailSetting.getAppName());
            IUser user = ServiceUtilities.findUser(username, app);
            IUserEMailNotificationSettings userEmailSettings = user.getEMailNotificationSettings();
            
            userEmailSettings.setNotificationDisabled(false);
            if (emailSetting.isEnableDailySummary()) {
              userEmailSettings.setSendDailyTaskSummary(EnumSet.copyOf(emailSetting.getEmailSendDailyTaskSummary()));
            }
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
            Ivy.log().error("Error in saving email settings of user {0} within app {1}", ex, username, emailSetting.getAppName());
            errors.add(new PortalIvyDataException(emailSetting.getAppName(), PortalIvyDataErrorType.FAIL_TO_SAVE_EMAIL_SETTING.toString()));
          }
        }
      }
      rs.setErrors(errors);
      return rs;
    });
  }

  @Override
  public void updateIvyEmailSettings(IvyEmailSetting generalEmailSetting, List<IvyEmailSetting> emailSettings) {
    if (generalEmailSetting != null) {
      for (IvyEmailSetting item: emailSettings) {
        item.setCustomMailEnabled(generalEmailSetting.isCustomMailEnabled());
        item.setEmailSendOnNewWorkTasks(generalEmailSetting.isEmailSendOnNewWorkTasks());
        item.setEmailSendDailyTaskSummary(generalEmailSetting.getEmailSendDailyTaskSummary());
      }
    }
  }

  public void clearSelectedDailySummary(List<IvyEmailSetting> emailSettings) {
    for( IvyEmailSetting ivyEmailSetting : emailSettings) {
        if(!ivyEmailSetting.isEnableDailySummary()) {
          ivyEmailSetting.getEmailSendDailyTaskSummary().clear();
        }
    }
  }

  public void displayDailySummary(List<IvyEmailSetting> emailSettings) {
    for( IvyEmailSetting ivyEmailSetting : emailSettings) {
        if(ivyEmailSetting.getEmailSendDailyTaskSummary().size() > 0) {
          ivyEmailSetting.setEnableDailySummary(true);
        }
    }
  }
}
