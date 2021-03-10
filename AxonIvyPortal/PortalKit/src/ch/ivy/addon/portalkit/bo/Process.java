package ch.ivy.addon.portalkit.bo;

import ch.ivy.addon.portalkit.enums.ProcessType;

public interface Process {
  public static final String DEFAULT_ICON = "si si-cog-double-2";
  public String getName();
  public String getStartLink();
  public String getDescription();
  public Object getProcess();
  public ProcessType getType();
  public String getTypeName();
  public String getId();

  default public String getIcon() {
    return DEFAULT_ICON;
  }
}
