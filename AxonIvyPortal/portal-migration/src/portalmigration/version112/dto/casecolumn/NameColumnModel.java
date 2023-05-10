package portalmigration.version112.dto.casecolumn;

import java.io.Serializable;

import ch.ivyteam.ivy.workflow.ICase;
import portalmigration.version112.enums.DashboardStandardCaseColumn;

public class NameColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = -8593323636310504629L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.NAME.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
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
}
