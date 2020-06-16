package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.dto.DashboardWidget;
import ch.ivy.addon.portalkit.dto.WidgetSample;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

@ViewScoped
@ManagedBean
public class DashboardBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040688L;
  private List<DashboardWidget> widgets;
  private List<WidgetSample> samples;

  @PostConstruct
  public void init() {
    widgets = List.of(task(), process(), addNew());
    samples = List.of(taskSample(), caseSample(), statisticSample(), processSample());
  }
  
  public List<DashboardWidget> getWidgets() {
    return widgets;
  }
  
  public List<WidgetSample> getSamples() {
    return samples;
  }
  
  private DashboardWidget task() {
    return new DashboardWidget("Your Tasks", DashboardWidgetType.TASK, 0, 0, 8, 11);
  }
  
  private DashboardWidget process() {
    return new DashboardWidget("Your Processes", DashboardWidgetType.PROCESS, 8, 0, 4, 4);
  }
  
  private DashboardWidget addNew() {
    return new DashboardWidget("Add new", DashboardWidgetType.NEW, 8, 4, 4, 1);
  }
  
  private WidgetSample taskSample() {
    return new WidgetSample("Task List", DashboardWidgetType.TASK, "task-widget-prototype.png");
  }
  
  private WidgetSample caseSample() {
    return new WidgetSample("Case List", DashboardWidgetType.CASE, "case-widget-prototype.png");
  }
  
  private WidgetSample statisticSample() {
    return new WidgetSample("Statistic Widget", DashboardWidgetType.STATISTIC, "statistic-widget-prototype.png");
  }
  
  private WidgetSample processSample() {
    return new WidgetSample("Process List", DashboardWidgetType.PROCESS, "process-widget-prototype.png");
  }
}
