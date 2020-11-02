package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class StartColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  public StartColumnModel() {
    this.header = cms("/ch.ivy.addon.portalkit.ui.jsf/common/start");
    this.width = "50";
    this.styleClass = "dashboard-tasks__start";
    this.toggleable = false;
    this.sortable = false;
    this.property = DashboardStandardTaskColumn.START.getProperty();
    this.type = DashboardColumnType.START;
  }
}
