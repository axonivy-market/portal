package ch.ivy.addon.portalkit.enums;

public enum ProcessType {
  EXPRESS_PROCESS("ExpressProcess"),
  EXTERNAL_LINK("ExternalLink"),
  IVY_PROCESS("IvyProcess");
  
  private final String value;
  
  private ProcessType(String value) {
    this.value = value;
  }
  
  public String getValue() {
    return value;
  }
  
}
