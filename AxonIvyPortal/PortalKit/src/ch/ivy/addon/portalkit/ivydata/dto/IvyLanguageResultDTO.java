package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;

public class IvyLanguageResultDTO extends AbstractResultDTO {

  private List<IvyLanguage> ivyLanguages = new ArrayList<>();

  public List<IvyLanguage> getIvyLanguages() {
    return ivyLanguages;
  }

  public void setIvyLanguages(List<IvyLanguage> ivyLanguages) {
    this.ivyLanguages = ivyLanguages;
  }

}
