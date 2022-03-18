package ch.ivy.addon.portalkit.enums;

public enum DefaultImage {
  DEFAULT, ARROWRIGHT, BILLING, CHECK, CONTRACTING, DASHBOARD, DASHBOARD2,
  HELPDESK, HELPDESK2, HR, HR2, INNOVATION, INNOVATION2, INVESTMENT, LOAN,
  MUGBEGIN, NASASTART, PROJECTMANAGEMENT, PROJECTMANAGEMENT2, PURCHASING,
  PURCHASING2, SPORT, WARTUNG, WARTUNG2, PROCESSMODELING;

  public String getPath() {
    return "/images/process/" + name();
  }
  
  public String getLabel() {
    return name();
  }
}
