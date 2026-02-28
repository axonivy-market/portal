package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class DelegationColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.DELEGATION.getField();
    this.styleToDisplay = initDefaultStyle();
    this.format = getDefaultFormat();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.quickSearch = defaultIfEmpty(this.quickSearch, false);
    this.sortable = defaultIfEmpty(this.sortable, false);
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/DELEGATION";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
  }

  @Override
  protected int getDefaultColumnWidth() {
    return SMALL_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__delegation text-center widget-column";
  }

  @Override
  public Boolean getDefaultSortable() {
    return false;
  }

  @Override
  public boolean canQuickSearch() {
    return false;
  }
}
