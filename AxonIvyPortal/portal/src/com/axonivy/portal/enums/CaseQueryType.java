package com.axonivy.portal.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum CaseQueryType implements HasCmsName {
  BUSINESS_CASE, SUB_CASE, ALL;

  @Override
  public String getCmsName() {
    return StringUtils.defaultIfBlank(
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/CaseQueryType/" + name()), name());
  }
}
