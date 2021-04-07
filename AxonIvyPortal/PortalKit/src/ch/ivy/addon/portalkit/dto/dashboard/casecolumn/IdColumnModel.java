package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.ICase;

public class IdColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -8721107805389913589L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/ID");
    this.field = DashboardStandardCaseColumn.ID.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-cases__id u-text-align-center u-padding-0");
  }
  
  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return null;
    }
    return caze.getId();
  }
}
