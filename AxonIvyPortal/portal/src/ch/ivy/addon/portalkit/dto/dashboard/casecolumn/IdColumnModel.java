package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.ICase;

public class IdColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -8721107805389913589L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.ID.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
  }

  @Override
  public String getHeaderText() {
    return translateHeader(defaultIfEmpty(this.header, DashboardConfigurationPrefix.CMS + getDefaultHeaderCMS()));
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/ID";
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-cases__id u-text-align-center u-padding-0";
  }

  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return null;
    }
    return caze.getId();
  }
}
