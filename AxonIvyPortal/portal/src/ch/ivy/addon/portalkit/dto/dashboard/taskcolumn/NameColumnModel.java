package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;

public class NameColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.NAME.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.quickSearch = defaultIfEmpty(this.quickSearch, true);
    this.styleToDisplay = initDefaultStyle();
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/taskName";
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__name widget-column";
  }
  
  @Override
  protected int getDefaultColumnWidth() {
    return 200;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.names().current();
  }

  @Override
  public boolean canQuickSearch() {
    return true;
  }
}
