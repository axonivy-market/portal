package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

import ch.ivy.addon.portalkit.bo.RemoteRole;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.ElapsedTimeComparison;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticChartConstants;
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivy.ws.addon.ElapsedTimeStatistic;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

@ManagedBean
@ViewScoped
public class ElapsedTimeDetailsBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String ROLE_EVERYBODY = "Everybody";

  private StatisticService statisticService = new StatisticService();
  private String selectedCaseCategory;
  private HorizontalBarChartModel elapsedTimeOfTasksModel;
  private List<RemoteRole> rolesForCompareElapsedTime;
  private List<ElapsedTimeComparison> comparisonDataModel;
  private RemoteRole defaultRole;
  private String caseQueryOfSelectedChart;
  private String chartName;
  private boolean dataEmpty;

  private static final String DAYS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/elapsedTimeChart/days";
  private static final String HOURS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/elapsedTimeChart/hours";
  private static final String MINUTES_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/elapsedTimeChart/minutes";
  private static final String SECONDS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/elapsedTimeChart/seconds";

  @SuppressWarnings("unchecked")
  public void initialize(String caseCategory, StatisticChart statisticChart) {
    setSelectedCaseCategory(caseCategory);
    setCaseQueryOfSelectedChart(StatisticChartQueryUtils.generateCaseQuery(statisticChart.getFilter(), true).asJson());

    Map<String, Object> response = IvyAdapterService.startSubProcess("findAllRoles()", null,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    setRolesForCompareElapsedTime((List<RemoteRole>)response.get("roles"));

    defaultRole = rolesForCompareElapsedTime.stream().findFirst().filter((role) -> ROLE_EVERYBODY.equals(role.getName())).get();
    compare(defaultRole, defaultRole);
    chartName = statisticChart.getName();
    dataEmpty = false;
  }

  public void compare(RemoteRole firstRoleToCompare, RemoteRole secondRoleToCompare) {
    comparisonDataModel = new ArrayList<>();
    String caseCategory = selectedCaseCategory;
    TaskQuery taskQuery = TaskQuery.create();

    if (selectedCaseCategory.equals(Ivy.cms().co((StatisticChartConstants.NO_CATEGORY_CMS)))) {
      caseCategory = StringUtils.EMPTY;
    }

    if (selectedCaseCategory.equals(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/other"))) {
      List<ElapsedTimeStatistic> result = statisticService.getElapsedTimeStatisticData(getCaseQueryOfSelectedChart());
      Map<String, Number> chartData = statisticService.generateDataForElapsedTimeChart(result);

      float totalValue = 0;
      for (Number number : chartData.values()) {
        totalValue += number.floatValue();
      }

      CaseQuery caseQueryForCaseCategory = CaseQuery.create();
      for (Iterator<Entry<String, Number>> iterator = chartData.entrySet().iterator(); iterator.hasNext();) {
        Entry<String, Number> chartDataEntry = iterator.next();
        float floatValueOfChartData = chartDataEntry.getValue().floatValue();
        if (floatValueOfChartData < totalValue * 0.02) {
          caseQueryForCaseCategory.where().or().category().isEqual(chartDataEntry.getKey());
        }
      }
      taskQuery.where().cases(caseQueryForCaseCategory);
    } else {
      taskQuery = StatisticChartQueryUtils.getQueryForSelectedItemElapsedTime(caseCategory);
    }

    HorizontalBarChartModel result = new HorizontalBarChartModel();

    ChartSeries chartSeriesOfFirstRole = generateChartDataForCompareRole(taskQuery, firstRoleToCompare);
    ChartSeries chartSeriesOfSecondRole = generateChartDataForCompareRole(taskQuery, secondRoleToCompare);
    if (chartSeriesOfFirstRole.getData().size() == 0 && chartSeriesOfSecondRole.getData().size() == 0) {
      dataEmpty = true;
      return ;
    } else {
      dataEmpty = false;
    }
    populateDataForChartSeries(chartSeriesOfFirstRole, chartSeriesOfSecondRole);

    result.addSeries(chartSeriesOfFirstRole);
    result.addSeries(chartSeriesOfSecondRole);
    result.setLegendPosition("e");
    result.setAnimate(true);
    String datatipFormat = StringUtils.join("%1$.0f ", Ivy.cms().co(StatisticChartConstants.SECOND_DATATIP_CMS));
    result.setDatatipFormat(datatipFormat);
    Axis axis = result.getAxis(AxisType.X);
    axis.setLabel(Ivy.cms().co(StatisticChartConstants.ELAPSED_TIME_DETAIL_CHART_NAME_CMS));
    axis.setTickAngle(-45);
    result.setExtender("chartExtender");

    setElapsedTimeOfTasksModel(result);

    Map<Object, Number> resultOfFirstRole = chartSeriesOfFirstRole.getData();
    calculateElapsedTimeOfFirstRole(resultOfFirstRole);

    List<String> categoryList = comparisonDataModel.stream().map(ElapsedTimeComparison::getCategory).collect(Collectors.toList());
    Map<Object, Number> resultOfSecondRole = chartSeriesOfSecondRole.getData();
    calculateElapsedTimeOfSecondRole(categoryList, resultOfSecondRole);
  }

  private void calculateElapsedTimeOfFirstRole(Map<Object, Number> resultOfFirstRole) {
    for (Map.Entry<Object, Number> entryOfFirstRole : resultOfFirstRole.entrySet()) {
      String category = (String) entryOfFirstRole.getKey();
      if (StringUtils.EMPTY.equals(category)) {
        continue ;
      }
      ElapsedTimeComparison comparisonRow = new ElapsedTimeComparison();
      comparisonRow.setCategory(category);
      comparisonRow.setElapsedTimeOfFirstRole(calculateElapsedTime(entryOfFirstRole.getValue()));
      comparisonDataModel.add(comparisonRow);
    }
  }

  private void calculateElapsedTimeOfSecondRole(List<String> categoryList, Map<Object, Number> resultOfSecondRole) {
    for (Map.Entry<Object, Number> entryOfSecondRole : resultOfSecondRole.entrySet()) {
      String category = entryOfSecondRole.getKey().toString();
      if (categoryList.contains(category)) {
        comparisonDataModel.stream()
          .filter(comparison -> comparison.getCategory().equals(category))
          .findFirst().get()
          .setElapsedTimeOfSecondRole(calculateElapsedTime(entryOfSecondRole.getValue()));
      }
    }
  }

  String calculateElapsedTime(Number value) {
    long timeInSeconds = value.longValue();
    int days = (int)TimeUnit.SECONDS.toDays(timeInSeconds);
    long hours = TimeUnit.SECONDS.toHours(timeInSeconds) - (days *24);
    long minutes = TimeUnit.SECONDS.toMinutes(timeInSeconds) - (TimeUnit.SECONDS.toHours(timeInSeconds)* 60);
    long seconds = TimeUnit.SECONDS.toSeconds(timeInSeconds) - (TimeUnit.SECONDS.toMinutes(timeInSeconds) *60);
    StringBuilder elapsedTime = new StringBuilder();
    if (days > 0) {
      elapsedTime.append(days + " " + Ivy.cms().co(DAYS_CMS) + " - ");
    }
    if (hours > 0) {
      elapsedTime.append(hours + " " + Ivy.cms().co(HOURS_CMS) + " - ");
    }
    if (minutes > 0) {
      elapsedTime.append(minutes + " " + Ivy.cms().co(MINUTES_CMS) + " - ");
    }
    elapsedTime.append(seconds + " " + Ivy.cms().co(SECONDS_CMS));
    return elapsedTime.toString();
  }

  private ChartSeries generateChartDataForCompareRole(TaskQuery taskQuery, RemoteRole roleToCompare) {
    Map<Object, Number> result = new HashMap<>();
    TaskQuery updatedTaskQueryForCompare = TaskQuery.fromJson(taskQuery.asJson());

    updatedTaskQueryForCompare.where().and().activatorName().isEqual(roleToCompare.getMemberName());

    List<ElapsedTimeStatistic> statisticData = statisticService.getElapsedTimeOfTasksStatisticData(updatedTaskQueryForCompare.asJson());
    Map<String, Number> elapsedTimeData = statisticService.generateDataForElapsedTimeChart(statisticData);

    for (Map.Entry<String, Number> entry : elapsedTimeData.entrySet()) {
      result.put(entry.getKey(), entry.getValue());
    }

    ChartSeries series = new ChartSeries();
    series.setData(result);
    series.setLabel(roleToCompare.getDisplayName());

    return series;
  }

  private void populateDataForChartSeries(ChartSeries chartSeriesOfFirstRole, ChartSeries chartSeriesOfSecondRole) {
    Map<Object, Number> resultOfFirstRole = chartSeriesOfFirstRole.getData();
    Map<Object, Number> resultOfSecondRole = chartSeriesOfSecondRole.getData();

    List<Object> categoryList = new ArrayList<>();
    categoryList.addAll(resultOfFirstRole.entrySet().stream()
        .filter(entry -> StringUtils.isNotBlank(entry.getKey().toString()))
        .map(Entry::getKey).collect(Collectors.toList()));

    categoryList.addAll(resultOfSecondRole.entrySet().stream()
        .filter(entry -> StringUtils.isNotBlank(entry.getKey().toString()) && !categoryList.contains(entry.getKey().toString()))
        .map(Entry::getKey).collect(Collectors.toList()));

    for (Object category : categoryList) {
      if (!resultOfFirstRole.containsKey(category)) {
        resultOfFirstRole.put(category, 0);
      }
      if (!resultOfSecondRole.containsKey(category)) {
        resultOfSecondRole.put(category, 0);
      }
    }
    chartSeriesOfFirstRole.setData(resultOfFirstRole);
    chartSeriesOfSecondRole.setData(resultOfSecondRole);
  }

  public String getSelectedCaseCategory() {
    return selectedCaseCategory;
  }

  public void setSelectedCaseCategory(String selectedCaseCategory) {
    this.selectedCaseCategory = selectedCaseCategory;
  }

  public HorizontalBarChartModel getElapsedTimeOfTasksModel() {
    return elapsedTimeOfTasksModel;
  }

  public void setElapsedTimeOfTasksModel(HorizontalBarChartModel elapsedTimeOfTasksModel) {
    this.elapsedTimeOfTasksModel = elapsedTimeOfTasksModel;
  }

  public List<RemoteRole> getRolesForCompareElapsedTime() {
    return rolesForCompareElapsedTime;
  }

  public void setRolesForCompareElapsedTime(
      List<RemoteRole> rolesForCompareElapsedTime) {
    this.rolesForCompareElapsedTime = rolesForCompareElapsedTime;
  }

  public List<ElapsedTimeComparison> getComparisonDataModel() {
    return comparisonDataModel;
  }

  public void setComparisonDataModel(List<ElapsedTimeComparison> comparisonDataModel) {
    this.comparisonDataModel = comparisonDataModel;
  }

  public RemoteRole getDefaultRole() {
    return defaultRole;
  }

  public void setDefaultRole(RemoteRole defaultRole) {
    this.defaultRole = defaultRole;
  }

  public String getCaseQueryOfSelectedChart() {
    return caseQueryOfSelectedChart;
  }

  public void setCaseQueryOfSelectedChart(String caseQueryOfSelectedChart) {
    this.caseQueryOfSelectedChart = caseQueryOfSelectedChart;
  }

  public String getExcelFileName() {
    String fileName = chartName + "_" + selectedCaseCategory;
    fileName = fileName.replace(" ", "_");
    if (Ivy.cms().co(StatisticChartConstants.NO_CATEGORY_CMS).equals(selectedCaseCategory)) {
      fileName = fileName.replace("[", "").replace("]", "");
    }
    return fileName;
  }

  public boolean getDataEmpty() {
    return dataEmpty;
  }
}
