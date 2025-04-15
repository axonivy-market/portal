package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

public class FavoriteColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -7132266991495711489L;

  @Override
  public void initDefaultValue() {
    this.setVisible(GlobalSettingService.getInstance().isEnableFavoriteTask());
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.FAVORITE.getField();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.sortable = getDefaultSortable();
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-task-__favorite text-center widget-column ui-sm-hidden";
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
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/FAVORITE";
  }

}
