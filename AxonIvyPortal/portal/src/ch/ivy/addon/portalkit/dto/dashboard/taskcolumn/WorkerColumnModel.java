package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class WorkerColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.WORKER.getField();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.quickSearch = defaultIfEmpty(this.quickSearch, true);
    this.styleToDisplay = initDefaultStyle();
    this.format = getDefaultFormat();
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/common/worker";
  }
  
  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.CUSTOM;
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
    Ivy.log().info("loc ne");
    if (task == null) {
      return null;
    }
    return task.getWorkerUserName();
  }

  @Override
  public boolean canQuickSearch() {
    return true;
  }
}
