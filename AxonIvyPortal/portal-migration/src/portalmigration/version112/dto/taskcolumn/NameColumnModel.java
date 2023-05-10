package portalmigration.version112.dto.taskcolumn;

import java.io.Serializable;

import ch.ivyteam.ivy.workflow.ITask;
import portalmigration.version112.enums.DashboardStandardTaskColumn;

public class NameColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.NAME.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/taskName";
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__name";
  }

  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.names().current();
  }
}
