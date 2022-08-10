package ch.ivy.addon.portalkit.casefilter.impl;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseNameFilter extends CaseFilter {
  private String name;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/name");
  }

  @Override
  public String value() {
    return StringUtils.isNotBlank(name) ? String.format(DOUBLE_QUOTES, name) : ALL;
  }

  @Override
  public CaseQuery buildQuery() {
      return null;
   
  }

  @Override
  public void resetValues() {
    name = StringUtils.EMPTY;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
