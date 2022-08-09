package ch.ivy.addon.portalkit.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum ProcessWidgetMode {
  COMPACT_MODE,
  FULL_MODE,
  COMBINED_MODE,
  IMAGE_MODE;

  public String getLabel() {
    String label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/ProcessWidgetMode/" + name());
    return StringUtils.isBlank(label) ? name() : label;
  }
}
