package ch.internalsupport;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

@ManagedBean
@ViewScoped
public class ChartView implements Serializable {

  private static final long serialVersionUID = 1980559732301424620L;
  private BarChartModel barModel;
  private TaskQuery today;
  private TaskQuery tomorrow;
  private TaskQuery in2Days;
  private TaskQuery in3Days;

  @PostConstruct
  public void init() {
    createExpiryTasksBarChart();
  }

  public BarChartModel getBarModel() {
    return barModel;
  }
  
  public TaskQuery getToday() {
    return today;
  }

  public TaskQuery getTomorrow() {
    return tomorrow;
  }

  public TaskQuery getIn2Days() {
    return in2Days;
  }

  public TaskQuery getIn3Days() {
    return in3Days;
  }

  private void createExpiryTasksBarChart() {
    Optional<ChartSeries> chartSeries = createExpiryChartSeries();
    BarChartModel barChartModel = new BarChartModel();
    if (chartSeries.isPresent()) {
      barChartModel.setTitle(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskExpiry"));
      barChartModel.setShadow(false);
      String datatipFormat = "%2$.0f " + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/tasks");
      barChartModel.setDatatipFormat(datatipFormat);
      Axis xAxis = barChartModel.getAxis(AxisType.X);
      xAxis.setLabel(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/expiryPeriod"));
      Axis yAxis = barChartModel.getAxis(AxisType.Y);
      yAxis.setLabel(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/tasks"));
      barChartModel.addSeries(chartSeries.get());
    }
    barModel = barChartModel;
  }

  private Optional<ChartSeries> createExpiryChartSeries() {
    ChartSeries chartSeries = new ChartSeries();
    List<TaskState> states = Arrays.asList(TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED);

    today = TaskQuery.create();
    today.where().and(queryForStates(states)).and(queryForExpiry(today()));
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/today"), Ivy.wf()
        .getTaskQueryExecutor().getCount(today));

    tomorrow = TaskQuery.create();
    tomorrow.where().and(queryForStates(states)).and(queryForExpiry(plusDays(today(), 1)));
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/tomorrow"), Ivy.wf()
        .getTaskQueryExecutor().getCount(tomorrow));

    in2Days = TaskQuery.create();
    in2Days.where().and(queryForStates(states)).and(queryForExpiry(plusDays(today(), 2)));
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/in2Days"), Ivy.wf()
        .getTaskQueryExecutor().getCount(in2Days));

    in3Days = TaskQuery.create();
    in3Days.where().and(queryForStates(states)).and(queryForExpiry(plusDays(today(), 3)));
    chartSeries.set(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/barchart/in3Days"), Ivy.wf()
        .getTaskQueryExecutor().getCount(in3Days));

    Optional<ChartSeries> optionalCharSeries = Optional.ofNullable(chartSeries);
    return optionalCharSeries;
  }

  private TaskQuery queryForStates(List<TaskState> states) {
    TaskQuery stateFieldQuery = TaskQuery.create();

    if (states == null || states.isEmpty()) {
      stateFieldQuery.where().state().isNotEqual(TaskState.DONE).and().state().isNotEqual(TaskState.ZOMBIE).and()
          .state().isNotEqual(TaskState.DESTROYED);
    } else {
      IFilterQuery filterQuery = stateFieldQuery.where();
      for (TaskState state : states) {
        filterQuery.or().state().isEqual(state);
      }
    }
    return stateFieldQuery;
  }

  private TaskQuery queryForExpiry(Date date) {
    Date dateAfter1Day = plusDays(date, 1);
    TaskQuery priorityQuery =
        TaskQuery.create().where().expiryTimestamp().isGreaterOrEqualThan(date).and().expiryTimestamp()
            .isLowerThan(dateAfter1Day).and().isExpired().isEqual(false);
    return priorityQuery;
  }

  private static Date today() {
    LocalDate now = LocalDate.now();
    return Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  private Date plusDays(Date date, long daysToAdd) {
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return Date.from(localDate.plusDays(daysToAdd).atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
}
