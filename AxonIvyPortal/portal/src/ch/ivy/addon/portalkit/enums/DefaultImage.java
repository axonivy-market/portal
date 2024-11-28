package ch.ivy.addon.portalkit.enums;

public enum DefaultImage {
  DEFAULT, ARROWRIGHT, BILLING, CHECK, CONTRACTING, DASHBOARD, DASHBOARD2,
  HELPDESK, HELPDESK2, HR, HR2, INNOVATION, INNOVATION2, INVESTMENT, LOAN,
  MUGBEGIN, NASASTART, PROJECTMANAGEMENT, PROJECTMANAGEMENT2, PURCHASING,
  PURCHASING2, SPORT, WARTUNG, WARTUNG2, PROCESSMODELING, PROCESSMODELINGDARK;

  public static final String IMAGE_PATH = "/Images/process/";

  public String getPath() {
    return IMAGE_PATH + name();
  }
  
  public String getLabel() {
    return name();
  }
}
