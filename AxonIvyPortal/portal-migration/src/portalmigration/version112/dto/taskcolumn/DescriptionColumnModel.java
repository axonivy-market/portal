package portalmigration.version112.dto.taskcolumn;

import java.io.Serializable;

import ch.ivyteam.ivy.workflow.ITask;
import portalmigration.version112.enums.DashboardStandardTaskColumn;

public class DescriptionColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.DESCRIPTION.getField();
    this.style = defaultIfEmpty(this.style, getDefaultStyle());
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.sortable = defaultIfEmpty(this.sortable, getDefaultSortable());
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/description";
  }

  @Override
  public Boolean getDefaultSortable() {
    return false;
  }

  @Override
  public String getDefaultStyle() {
    return "width: 200px";
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__description";
  }

  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.descriptions().current();
  }
}
