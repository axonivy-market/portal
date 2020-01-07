package ch.ivy.addon.portalkit.casefilter;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.util.CaseUtils;
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
    if (StringUtils.isBlank(name)) {
      return null;
    }

    String containingKeyword = String.format("%%%s%%", name.trim());
    return CaseUtils.createBusinessCaseQuery().where().name().isLikeIgnoreCase(containingKeyword);
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
