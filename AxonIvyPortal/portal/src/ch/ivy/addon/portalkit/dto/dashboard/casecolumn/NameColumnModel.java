
package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.ICase;

public class NameColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -8593323636310504629L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.NAME.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.quickSearch = defaultIfEmpty(this.quickSearch, true);
    this.styleToDisplay = initDefaultStyle();
  }
  
  @Override
  protected int getDefaultColumnWidth() {
    return 200;
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/caseName";
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-cases__name";
  }

  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return null;
    }
    return caze.names().current();
  }

  @Override
  public boolean canQuickSearch() {
    return true;
  }
}
