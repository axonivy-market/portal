package ch.ivy.addon.portalkit.ivydata.dto;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;

public class IvyLanguageResultDTO extends AbstractResultDTO {

  private IvyLanguage ivyLanguage = new IvyLanguage();

  public IvyLanguage getIvyLanguage() {
    return ivyLanguage;
  }

  public void setIvyLanguage(IvyLanguage ivyLanguage) {
    this.ivyLanguage = ivyLanguage;
  }

}
