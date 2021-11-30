package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;

public class DescriptionColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/common/description");
    this.field = DashboardStandardTaskColumn.DESCRIPTION.getField();
    this.style = defaultIfEmpty(this.style, "width: 250px");
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__description");
    this.sortable = false;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getDescription();
  }
}
