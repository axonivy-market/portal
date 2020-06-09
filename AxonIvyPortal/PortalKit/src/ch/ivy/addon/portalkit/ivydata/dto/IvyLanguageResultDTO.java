package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;

public class IvyLanguageResultDTO extends AbstractResultDTO {

  private List<IvyLanguage> ivyLanguages = new ArrayList<>();
  
  //It's used for new concept, no multi-app portal
  private IvyLanguage ivyLanguage = new IvyLanguage();

  public List<IvyLanguage> getIvyLanguages() {
    return ivyLanguages;
  }

  public void setIvyLanguages(List<IvyLanguage> ivyLanguages) {
    this.ivyLanguages = ivyLanguages;
  }

  public IvyLanguage getIvyLanguage() {
    return ivyLanguage;
  }

  public void setIvyLanguage(IvyLanguage ivyLanguage) {
    this.ivyLanguage = ivyLanguage;
  }

}
