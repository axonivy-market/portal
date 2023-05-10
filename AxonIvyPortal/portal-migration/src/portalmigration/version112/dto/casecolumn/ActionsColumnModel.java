package portalmigration.version112.dto.casecolumn;

import java.io.Serializable;

import ch.ivyteam.ivy.workflow.ICase;
import portalmigration.version112.enums.DashboardColumnFormat;
import portalmigration.version112.enums.DashboardStandardCaseColumn;

public class ActionsColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = 5096010835140006329L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.ACTIONS.getField();
    this.style = defaultIfEmpty(this.style, getDefaultStyle());
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.sortable = getDefaultSortable();
    this.format = getDefaultFormat();
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/action";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }

  @Override
  public Boolean getDefaultSortable() {
    return false;
  }

  @Override
  public String getDefaultStyle() {
    return SMALL_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-cases__actions u-text-align-center";
  }

  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return "";
    }
    return caze.getCategory().getName();
  }
}
