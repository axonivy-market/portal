package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.ICase;

public class DescriptionColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, cms("/ch.ivy.addon.portalkit.ui.jsf/common/description"));
    this.field = DashboardStandardCaseColumn.DESCRIPTION.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__description u-text-align-center");
    this.sortable = false;
  }
  
  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return null;
    }
    return caze.getDescription();
  }
}
