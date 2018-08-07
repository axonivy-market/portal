package ch.ivy.addon.portalkit.enums;

public enum WSAuthenticationType {
  NONE("None"), HTTP_BASIC("HTTP Basic"), NTLM("NTLM");


  private String value;

  private WSAuthenticationType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
