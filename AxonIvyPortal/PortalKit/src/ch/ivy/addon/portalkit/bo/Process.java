package ch.ivy.addon.portalkit.bo;

import ch.ivy.addon.portalkit.enums.ProcessType;

public interface Process {
  public String getName();
  public String getStartLink();
  public String getDescription();
  public Object getProcess();
  public ProcessType getType();
  public String getTypeName();
  public String getId();

  default public String getIcon() {
    return "si si-cog-double-2";
  }
}
