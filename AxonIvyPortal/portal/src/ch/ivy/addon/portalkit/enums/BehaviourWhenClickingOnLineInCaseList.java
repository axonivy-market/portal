package ch.ivy.addon.portalkit.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum BehaviourWhenClickingOnLineInCaseList {
  ACCESS_CASE_DETAILS, ACCESS_BUSINESS_DETAILS;

  public String getLabel() {
    String label = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/BehaviourWhenClickingOnLineInCaseList/" + name());
    return StringUtils.isBlank(label) ? name() : label;
  }
}
