package ch.ivy.addon.portalkit.bo;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.ProcessType;

public abstract class Process {
  
  public abstract String getName();
  public abstract String getStartLink();
  public abstract String getDescription();
  public abstract Object getProcess();
  public abstract ProcessType getType();
  public abstract String getTypeName();
  public String getId() {
    return StringUtils.EMPTY;
  }
  
}
