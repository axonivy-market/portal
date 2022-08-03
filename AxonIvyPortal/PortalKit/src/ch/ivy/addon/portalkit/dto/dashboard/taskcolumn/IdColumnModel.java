package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;

public class IdColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.ID.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
  }

  @Override
  public String getHeaderText() {
    return translateHeader(defaultIfEmpty(this.header, DashboardConfigurationPrefix.CMS + getDefaultHeaderCMS()));
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/ID";
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__id u-text-align-center u-padding-0";
  }

  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getId();
  }
}
