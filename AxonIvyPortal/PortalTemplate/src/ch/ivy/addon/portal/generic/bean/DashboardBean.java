package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import ch.ivy.addon.portalkit.dto.DashboardWidget;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;
  private DashboardModel model;
  private List<DashboardWidget> widgets;
  private DefaultDashboardColumn column1;
  private DefaultDashboardColumn column2;

  @PostConstruct
  public void init() {
    model = new DefaultDashboardModel();
    column1 = new DefaultDashboardColumn();
    column2 = new DefaultDashboardColumn();
    
    column2.addWidget("process-widget");
    column2.addWidget("actions");
    column1.addWidget("task-widget");
    
    column2.setStyle("flex: auto");
    column1.setStyle("flex: 70%");
    
    model.addColumn(column1);
    model.addColumn(column2);
    
    widgets = List.of(taskWidget(), caseWidget(), statisticWidget(), processWidget());
  }
  
  public void handleReorder(DashboardReorderEvent event) {
    if (column1.getWidgets().contains("task-widget")) {
      resizeByTaskWidget(column1, column2);
    }
    if (column2.getWidgets().contains("task-widget")) {
      resizeByTaskWidget(column2, column1);
    }
  }
  
  public void onClose(CloseEvent event) {
    String componentId = event.getComponent().getId();
    column1.removeWidget(componentId);
    column2.removeWidget(componentId);
  }
  
  private void resizeByTaskWidget(DefaultDashboardColumn column, DefaultDashboardColumn anotherColumn) {
    column.setStyle("flex: 70%");
    anotherColumn.setStyle("flex: auto");
  }

  public DashboardModel getModel() {
    return model;
  }
  
  public List<DashboardWidget> getWidgets() {
    return widgets;
  }
  
  private DashboardWidget taskWidget() {
    return new DashboardWidget("Task List", "fa fa-check-square-o");
  }
  
  private DashboardWidget caseWidget() {
    return new DashboardWidget("Case List", "fa fa-list-ul");
  }
  
  private DashboardWidget statisticWidget() {
    return new DashboardWidget("Statistic List", "fa fa-bar-chart");
  }
  
  private DashboardWidget processWidget() {
    return new DashboardWidget("Process List", "fa fa-cogs");
  }
}
