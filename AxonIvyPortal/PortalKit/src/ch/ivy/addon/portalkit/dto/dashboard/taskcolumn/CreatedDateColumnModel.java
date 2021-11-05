package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;
import java.text.ParseException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class CreatedDateColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    this.header = defaultIfEmpty(this.header, "cms:/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CREATION_TIME");
    this.field = DashboardStandardTaskColumn.CREATED.getField();
    this.style = defaultIfEmpty(this.style, SMALL_WIDTH);
    this.styleClass = defaultIfEmpty(this.styleClass, "dashboard-tasks__created-date u-text-align-center");
    this.format = DashboardColumnFormat.TIMESTAMP;
  }
  
  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getStartTimestamp();
  }

  @Override
  @JsonIgnore
  public void validate() throws ParseException {
    Ivy.log().info("validate date from string {0}", userFilterFrom);
    Ivy.log().info("validate date from {0}", userDateFilterFrom);
    Ivy.log().info("validate date to string {0}", userFilterTo);
    Ivy.log().info("validate date to {0}", userDateFilterTo);
  }
}
