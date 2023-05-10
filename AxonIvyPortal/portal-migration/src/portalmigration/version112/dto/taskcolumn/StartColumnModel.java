package portalmigration.version112.dto.taskcolumn;

import java.io.Serializable;

import portalmigration.version112.enums.DashboardStandardTaskColumn;

public class StartColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.START.getField();
    this.style = defaultIfEmpty(this.style, getDefaultStyle());
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
  public String getDefaultStyle() {
    return "width: 50px";
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__start u-text-align-center";
  }
}
