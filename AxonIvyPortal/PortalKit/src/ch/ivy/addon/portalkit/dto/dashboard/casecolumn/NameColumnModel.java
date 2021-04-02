package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.ICase;

public class NameColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -8593323636310504629L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/common/caseName");
    this.field = DashboardStandardCaseColumn.NAME.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-cases__name");
  }
  
  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return null;
    }
    return caze.getName();
  }
}
