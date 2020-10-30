package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;

public class StartColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  public StartColumnModel() {
    this.field = "start";
    this.header = cms("/ch.ivy.addon.portalkit.ui.jsf/common/start");
    this.width = "50";
    this.styleClass = "dashboard-tasks__start";
    this.toggleable = false;
    this.sortable = false;
    this.type = DashboardColumnType.START;
  }
}
