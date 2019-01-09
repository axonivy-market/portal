package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;

public class IvyEmailSettingResultDTO {

  private List<IvyEmailSetting> ivyEmailSettings = new ArrayList<>();
  private List<PortalIvyDataException> errors = new ArrayList<>();

  public List<IvyEmailSetting> getIvyEmailSettings() {
    return ivyEmailSettings;
  }

  public void setIvyEmailSettings(List<IvyEmailSetting> ivyEmailSettings) {
    this.ivyEmailSettings = ivyEmailSettings;
  }

  public List<PortalIvyDataException> getErrors() {
    return errors;
  }

  public void setErrors(List<PortalIvyDataException> errors) {
    this.errors = errors;
  }
}
