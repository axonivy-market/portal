package ch.ivy.addon.portal.generic.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.List;

@ViewScoped
@ManagedBean
public class TaskListBean extends DashboardBean {

  private static final long serialVersionUID = -4224901891867040688L;

  @Override
  @PostConstruct
  public void init() {
    selectedDashboard = taskListDashBoard();
    this.widget = selectedDashboard.getWidgets().getFirst();
  }

  public static Dashboard taskListDashBoard() {
    String dashboardJson = Ivy.var().get(PortalVariable.TASK_LIST_DASHBOARD.key);
    Dashboard taskListDashBoard = new Dashboard();
    if (StringUtils.isEmpty(dashboardJson)) {
      List<DashboardWidget> widgets = new List<DashboardWidget>();
      DashboardWidget defaultTaskList = DashboardWidgetUtils.buildDefaultTaskWidget("ID", "Your Task");
      widgets.add(defaultTaskList);
      taskListDashBoard.setId(DashboardUtils.generateId());
      taskListDashBoard.setWidgets(widgets);
    }else {
      taskListDashBoard = BusinessEntityConverter.jsonValueToEntity(dashboardJson, Dashboard.class);

    }

    return taskListDashBoard;
  }

  public boolean hasAdminPermission() {
    return PermissionUtils.isSessionUserHasAdminRole();
  }

}
