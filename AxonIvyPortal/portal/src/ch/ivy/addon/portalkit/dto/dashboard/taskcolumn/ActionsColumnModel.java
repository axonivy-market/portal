package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class ActionsColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.ACTIONS.getField();
    this.styleToDisplay = initDefaultStyle();
    this.sortable = defaultIfEmpty(this.sortable, getDefaultSortable());
    this.format = getDefaultFormat();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
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
  public int getDefaultColumnWidth() {
    return TINY_WIDTH;
  }

  @Override
  public Boolean getDefaultSortable() {
    return false;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__actions u-text-align-center widget-column";
  }
}
