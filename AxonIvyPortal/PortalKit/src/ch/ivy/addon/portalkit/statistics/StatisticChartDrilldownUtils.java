package ch.ivy.addon.portalkit.statistics;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import org.primefaces.event.ItemSelectEvent;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class StatisticChartDrilldownUtils {
  public static final String CLIENT_ID = "clientId";
  public static final String DRILLDOWN_TASK_PRIORITY_METHOD = "#{logic.drilldownTaskByPriority}";
  public static final String DRILLDOWN_CASE_STATE_METHOD = "#{logic.drilldownCaseByState}";
  public static final String DRILLDOWN_ELAPSED_TIME_METHOD = "#{logic.drilldownElapsedTime}";
  public static final String ONSELECT_DRILLDOWN_TASK_EXPIRY_METHOD = "#{logic.onSelectDrilldownTaskByExpiry}";
  public static final String TOTASK_EXPIRY_TASK_LIST_METHOD = "#{logic.toTaskByExpiryTaskList}";
  public static final String DRILLDOWN_TASK_EXPIRY_METHOD = "#{logic.drilldownTaskByExpiry}";
  public static final String PREVIOUS_SELECTED_MONTH_FIELD = "#{data.previousSelectedMonth}";
  public static final String PREVIOUS_SELECTED_WEEK_FIELD = "#{data.previousSelectedWeek}";
  public static final String PREVIOUS_SELECTED_DAY_FIELD = "#{data.previousSelectedDay}";

  public static void drilldownTaskByPriority(ItemSelectEvent event, StatisticChart chart) {
    var taskQuery = StatisticChartQueryUtils.getQueryForSelectedItemOfTaskByPriorityChart(event, chart);
    var currentLanguage = LanguageService.newInstance().findUserLanguages().getIvyLanguage().getUserLanguage();
    var chartName = chart.getNames().stream()
            .filter(name -> StatisticService.equalsDisplayNameLocale(name, currentLanguage))
            .map(DisplayName::getValue).findFirst().orElse(EMPTY);
    callIvyComponentLogic(DRILLDOWN_TASK_PRIORITY_METHOD, new Object[] {chartName, taskQuery});
  }

  public static void drilldownCaseByState(ItemSelectEvent event, StatisticChart chart) {
    var caseQuery = StatisticChartQueryUtils.getQueryForSelectedItemByCaseByState(event, chart);
    callIvyComponentLogic(DRILLDOWN_CASE_STATE_METHOD, new Object[] {chart, caseQuery});
  }

  public static void drilldownElapsedTime(ItemSelectEvent event, StatisticChart chart) {
    var selectedCaseCategory = StatisticService.getSelectedValueOfBarChart(event);
    callIvyComponentLogic(DRILLDOWN_ELAPSED_TIME_METHOD, new Object[] {chart, selectedCaseCategory});
  }

  public static void onSelectDrilldownTaskByExpiry(ItemSelectEvent event, StatisticChart chart) {
    var selectedDrilldownItem = StatisticService.getSelectedValueOfBarChart(event);
    var selectedItemOfDrilldown = StatisticService.getSelectedValueOfBarChart(event);
    var isDrilldownToTaskList = StatisticService.selectHourOfDay(selectedDrilldownItem);
    var taskQuery = generateQueryForTaskByExpiry(event, chart);
    callIvyComponentLogic(ONSELECT_DRILLDOWN_TASK_EXPIRY_METHOD,
        new Object[] {isDrilldownToTaskList, selectedItemOfDrilldown, chart, taskQuery});
  }

  public static void toTaskByExpiryTaskList(ItemSelectEvent event, StatisticChart chart) {
    var selectedItemOfDrilldown = StatisticService.getSelectedValueOfBarChart(event);
    var taskQuery = generateQueryForTaskByExpiry(event, chart);
    callIvyComponentLogic(TOTASK_EXPIRY_TASK_LIST_METHOD, new Object[] {selectedItemOfDrilldown, chart, taskQuery});
  }

  public static void drilldownTaskByExpiry(ItemSelectEvent event, StatisticChart chart) {
    var selectedItemOfDrilldown = StatisticService.getSelectedValueOfBarChart(event);
    callIvyComponentLogic(DRILLDOWN_TASK_EXPIRY_METHOD, new Object[] {chart, selectedItemOfDrilldown});
  }

  private static TaskQuery generateQueryForTaskByExpiry(ItemSelectEvent event, StatisticChart statisticChart) {
    String previousSelectedMonth = Attrs.currentContext().getAttribute(PREVIOUS_SELECTED_MONTH_FIELD, String.class);
    String previousSelectedWeek = Attrs.currentContext().getAttribute(PREVIOUS_SELECTED_WEEK_FIELD, String.class);
    String previousSelectedDay = Attrs.currentContext().getAttribute(PREVIOUS_SELECTED_DAY_FIELD, String.class);
    return StatisticChartQueryUtils.getQueryForSelectedItemOfTaskByExpiryChart(event, statisticChart,
        previousSelectedMonth, previousSelectedWeek, previousSelectedDay);
  }

  private static void callIvyComponentLogic(String methodSignature, Object[] paramValues) {
    var ivyComponentLogicCaller = new IvyComponentLogicCaller<String>();
    ivyComponentLogicCaller.invokeComponentLogic(getComponentId(), methodSignature, paramValues);
  }

  private static String getComponentId() {
    return Attrs.currentContext().getBuildInAttribute(CLIENT_ID);
  }
}
