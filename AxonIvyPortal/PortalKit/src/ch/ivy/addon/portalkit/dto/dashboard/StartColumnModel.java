package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class StartColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, cms("/ch.ivy.addon.portalkit.ui.jsf/common/start"));
    this.field = DashboardStandardTaskColumn.START.getField();
    this.style = defaultIfEmpty(this.style, "width: 50px");
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__start");
    this.sortable = false;
  }
}
