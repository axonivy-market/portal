package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;

public interface IEmailSettingService {

  /**
   * Gets email settings for the passed user
   * @param username
   * @param apps
   * @return IvyEmailSettingResultDTO
   */
  IvyEmailSettingResultDTO findEmailSetting(String username, List<String> apps);
  
  /**
   * Sets email setting for the passed user
   * @param username
   * @param emailSettings
   * @return IvyEmailSettingResultDTO
   */
  IvyEmailSettingResultDTO saveEmailSetting(String username, List<IvyEmailSetting> emailSettings);

  
  /**
   * Updates list of email settings from general email settings
   * @param generalEmailSetting
   * @param emailSettings
   */
  void updateIvyEmailSettings(IvyEmailSetting generalEmailSetting, List<IvyEmailSetting> emailSettings);
}
