package com.axonivy.portal.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum CaseQueryType {
  BUSINESS_CASE, SUB_CASE, ALL;

  public String getLabel() {
    return StringUtils.defaultIfBlank(Ivy.cms().co("/Labels/Enums/CaseQueryType/" + name()), name());
  }
}
