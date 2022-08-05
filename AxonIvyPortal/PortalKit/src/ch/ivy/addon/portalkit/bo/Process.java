package ch.ivy.addon.portalkit.bo;

import ch.ivy.addon.portalkit.enums.ProcessType;

public interface Process {
  public static final String DEFAULT_PROCESS_ICON = "si si-hierarchy-6 si-rotate-270";
  public String getName();
  public String getStartLink();
  public String getDescription();
  public Object getProcess();
  public ProcessType getType();
  public String getTypeName();
  public String getId();
  public String getCategory();
  public String getImageUrl();
  public String getDefaultImageSrc();
  public String getApplication();

  default public String getIcon() {
    return DEFAULT_PROCESS_ICON;
  }
}
