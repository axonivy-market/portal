package ch.ivy.addon.portalkit.enums;

public enum IvyDefaultApplication {
  DESIGNER("designer"), SYSTEM("System");

  private String value;

  private IvyDefaultApplication(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
