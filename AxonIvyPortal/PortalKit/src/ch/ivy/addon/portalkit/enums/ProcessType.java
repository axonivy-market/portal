package ch.ivy.addon.portalkit.enums;

public enum ProcessType {
  EXPRESS_PROCESS("ExpressProcess"), 
  IVY_PROCESS("IvyProcess");
  
  private final String type;
  
  private ProcessType(String type) {
    this.type = type;
  }
  
  public String getType() {
    return type;
  }
  
}
