package portal.migration.ivydata.dto;

import portal.migration.ivydata.bo.IvyLanguage;

public class IvyLanguageResultDTO extends AbstractResultDTO {

  private IvyLanguage ivyLanguage = new IvyLanguage();

  public IvyLanguage getIvyLanguage() {
    return ivyLanguage;
  }

  public void setIvyLanguage(IvyLanguage ivyLanguage) {
    this.ivyLanguage = ivyLanguage;
  }

}
