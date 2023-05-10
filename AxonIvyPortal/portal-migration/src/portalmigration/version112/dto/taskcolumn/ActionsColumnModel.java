package portalmigration.version112.dto.taskcolumn;

import java.io.Serializable;

import portalmigration.version112.enums.DashboardColumnFormat;
import portalmigration.version112.enums.DashboardStandardTaskColumn;

public class ActionsColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.ACTIONS.getField();
    this.style = defaultIfEmpty(this.style, getDefaultStyle());
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.sortable = defaultIfEmpty(this.sortable, getDefaultSortable());
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
    return "dashboard-tasks__actions u-text-align-center";
  }
}
