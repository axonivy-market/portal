package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;

public class IvyEmailSettingResultDTO extends AbstractResultDTO {

  private List<IvyEmailSetting> ivyEmailSettings = new ArrayList<>();

  public List<IvyEmailSetting> getIvyEmailSettings() {
    return ivyEmailSettings;
  }

  public void setIvyEmailSettings(List<IvyEmailSetting> ivyEmailSettings) {
    this.ivyEmailSettings = ivyEmailSettings;
  }

}
