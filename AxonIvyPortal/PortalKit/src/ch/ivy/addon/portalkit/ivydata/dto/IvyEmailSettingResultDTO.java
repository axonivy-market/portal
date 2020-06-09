package ch.ivy.addon.portalkit.ivydata.dto;

import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;

public class IvyEmailSettingResultDTO extends AbstractResultDTO {

  private IvyEmailSetting ivyEmailSetting = new IvyEmailSetting();

  public IvyEmailSetting getIvyEmailSetting() {
    return ivyEmailSetting;
  }

  public void setIvyEmailSetting(IvyEmailSetting ivyEmailSetting) {
    this.ivyEmailSetting = ivyEmailSetting;
  }
}
