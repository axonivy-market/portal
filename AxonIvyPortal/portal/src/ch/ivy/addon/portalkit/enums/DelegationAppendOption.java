package ch.ivy.addon.portalkit.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum DelegationAppendOption {
  NONE, PARENT_NAME;
  
  public String getLabel() {
    return StringUtils
        .defaultIfBlank(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/DelegationAppendOption/" + name()), name());
  }
}
