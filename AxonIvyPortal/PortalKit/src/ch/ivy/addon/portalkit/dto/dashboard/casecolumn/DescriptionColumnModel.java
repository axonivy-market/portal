package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.ICase;

public class DescriptionColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = 1111740263997339274L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/common/description");
    this.field = DashboardStandardCaseColumn.DESCRIPTION.getField();
    this.style = defaultIfEmpty(this.style, "width: 250px");
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-cases__description");
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
