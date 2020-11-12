package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.ITask;

public class NameColumnModel extends ColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, cms("/ch.ivy.addon.portalkit.ui.jsf/common/taskName"));
    this.field = DashboardStandardTaskColumn.NAME.getField();
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getName();
  }
}
