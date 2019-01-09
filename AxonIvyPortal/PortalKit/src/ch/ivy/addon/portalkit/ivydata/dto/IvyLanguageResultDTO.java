package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;

public class IvyLanguageResultDTO {

  private List<IvyLanguage> ivyLanguages = new ArrayList<>();
  private List<PortalIvyDataException> errors = new ArrayList<>();

  public List<IvyLanguage> getIvyLanguages() {
    return ivyLanguages;
  }

  public void setIvyLanguages(List<IvyLanguage> ivyLanguages) {
    this.ivyLanguages = ivyLanguages;
  }

  public List<PortalIvyDataException> getErrors() {
    return errors;
  }

  public void setErrors(List<PortalIvyDataException> errors) {
    this.errors = errors;
  }
}
