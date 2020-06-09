package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;

public interface IEmailSettingService {

  /**
   * Gets email settings for the passed user
   * @param username
   * @return IvyEmailSettingResultDTO
   */
  IvyEmailSettingResultDTO findEmailSetting(String username);

  /**
   * Sets email setting for the passed user
   * @param username
   * @param emailSetting
   * @return IvyEmailSettingResultDTO
   */
  IvyEmailSettingResultDTO saveEmailSetting(String username, IvyEmailSetting emailSetting);

}
