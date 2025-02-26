package ch.ivy.addon.portalkit.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum ProcessSorting {
  BY_ALPHABETICALLY, BY_INDEX, BY_CUSTOM_ORDER, BY_SMART_ORDER;

  public String getLabel() {
    String label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/ProcessSorting/" + name());
    return StringUtils.isBlank(label) ? name() : label;
  }
}