package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;
import ch.ivy.addon.portalkit.ivydata.dto.IvyEmailSettingResultDTO;

public interface IEmailSettingService {

  /**
   * Gets email settings for current user
   * @return IvyEmailSettingResultDTO
   */
  IvyEmailSettingResultDTO findEmailSetting();

  /**
   * Sets email setting for current user
   * @param emailSetting
   * @return IvyEmailSettingResultDTO
   */
  IvyEmailSettingResultDTO saveEmailSetting(IvyEmailSetting emailSetting);

}
