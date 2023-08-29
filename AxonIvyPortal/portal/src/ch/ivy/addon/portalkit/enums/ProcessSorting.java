package ch.ivy.addon.portalkit.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum ProcessSorting {
  BY_ALPHABETICALLY("Alphabetically"), BY_INDEX("SortingIndex");

  private final String value;

  private ProcessSorting(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public String getLabel() {
    String label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/ProcessSorting/" + name());
    return StringUtils.isBlank(label) ? name() : label;
  }
}
