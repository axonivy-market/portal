package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.ICase;

public class FinishedDateColumnModel  extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -1044297302783214986L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/FINISHED_TIME");
    this.field = DashboardStandardCaseColumn.FINISHED.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-cases__finished-date u-text-align-center");
    this.format = DashboardColumnFormat.TIMESTAMP;
  }
  
  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return null;
    }
    return caze.getEndTimestamp();
  }
}