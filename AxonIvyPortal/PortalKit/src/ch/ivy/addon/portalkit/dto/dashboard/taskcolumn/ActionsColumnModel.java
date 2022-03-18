package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class ActionsColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/common/action");
    this.field = DashboardStandardTaskColumn.ACTIONS.getField();
    this.style = defaultIfEmpty(this.style, SMALL_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__actions");
    this.sortable = false;
    this.format = DashboardColumnFormat.CUSTOM;
  }
}
