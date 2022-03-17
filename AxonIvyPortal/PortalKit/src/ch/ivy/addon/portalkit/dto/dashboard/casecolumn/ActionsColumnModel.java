package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.ICase;

public class ActionsColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = 5096010835140006329L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/common/action");
    this.field = DashboardStandardCaseColumn.ACTIONS.getField();
    this.style = defaultIfEmpty(this.style, SMALL_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-cases__actions");
    this.sortable = false;
    this.format = DashboardColumnFormat.CUSTOM;
  }
  
  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return "";
    }
    return caze.getCategory().getName();
  }
}
