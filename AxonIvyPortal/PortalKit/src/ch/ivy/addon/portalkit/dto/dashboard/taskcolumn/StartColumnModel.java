package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class StartColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/common/start");
    this.field = DashboardStandardTaskColumn.START.getField();
    this.style = defaultIfEmpty(this.style, TINY_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__start u-text-align-center");
    this.sortable = false;
  }
}
