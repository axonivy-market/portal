package ch.ivy.addon.portalkit.casefilter.impl;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseDescriptionFilter extends CaseFilter {
  private String description;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/description");
  }

  @Override
  public String value() {
    return StringUtils.isNotBlank(description) ? String.format(DOUBLE_QUOTES, description) : ALL;
  }

  @Override
  public CaseQuery buildQuery() {
    if (StringUtils.isBlank(description)) {
      return null;
    }

    String containingKeyword = String.format("%%%s%%", description.trim());
    return CaseUtils.createBusinessCaseQuery().where().description().isLikeIgnoreCase(containingKeyword);
  }

  @Override
  public void resetValues() {
    description = StringUtils.EMPTY;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
