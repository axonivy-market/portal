package ch.ivy.addon.portalkit.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum SortDirection {
  ASC, DESC;

  public String getLabel() {
    String label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/SortDirection/" + name());
    return StringUtils.isBlank(label) ? name() : label;
  }
}
