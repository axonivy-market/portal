package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class StartColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.START.getField();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.sortable = getDefaultSortable();
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/start";
  }

  @Override
  public Boolean getDefaultSortable() {
    return false;
  }

  @Override
  protected int getDefaultColumnWidth() {
    return TINY_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__start text-center widget-column ui-sm-hidden";
  }
}
