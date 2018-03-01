package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

import ch.ivy.addon.portalkit.bo.RemoteRole;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.ElapsedTimeComparison;
import ch.ivy.ws.addon.ElapsedTimeStatistic;
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

  @SuppressWarnings("unchecked")
  public void initialize(String caseCategory) {
    setSelectedCaseCategory(caseCategory);

    Map<String, Object> response = IvyAdapterService.startSubProcess("findAllRoles()", null,
        Arrays.asList(PortalLibrary.PORTAL_TEMPLATE.getValue()));
    setRolesForCompareElapsedTime((List<RemoteRole>)response.get("roles"));

    defaultRole = rolesForCompareElapsedTime.stream().findFirst().filter((role) -> ROLE_EVERYBODY.equals(role.getName())).get();
    compare(defaultRole, defaultRole);
  }

  public void compare(RemoteRole firstRoleToCompare, RemoteRole secondRoleToCompare) {
    comparisonDataModel = new ArrayList<>();
    String caseCategory = selectedCaseCategory;
    if (selectedCaseCategory.equals(StatisticService.NO_CATEGORY_CMS)) {
      caseCategory = StringUtils.EMPTY;
    }
    TaskQuery taskQuery = statisticService.getQueryForSelectedItemElapsedTime(caseCategory);
    HorizontalBarChartModel result = new HorizontalBarChartModel();

    ChartSeries chartSeriesOfFirstRole = generateChartDataForCompareRole(taskQuery, firstRoleToCompare);
    ChartSeries chartSeriesOfSecondRole = generateChartDataForCompareRole(taskQuery, secondRoleToCompare);
    populateDataForChartSeries(chartSeriesOfFirstRole, chartSeriesOfSecondRole);

    result.addSeries(chartSeriesOfFirstRole);
    result.addSeries(chartSeriesOfSecondRole);
    result.setLegendPosition("e");
    result.setAnimate(true);
    result.setDatatipFormat("%2$.0f");
    result.getAxis(AxisType.X).setLabel("Elapsed time in seconds");
    result.setExtender("chartExtender");

    setElapsedTimeOfTasksModel(result);

    Map<Object, Number> resultOfFirstRole = chartSeriesOfFirstRole.getData();
    for (Map.Entry<Object, Number> entryOfFirstRole : resultOfFirstRole.entrySet()) {
      ElapsedTimeComparison comparisonRow = new ElapsedTimeComparison();
      comparisonRow.setCategory((String) entryOfFirstRole.getKey());
      comparisonRow.setElapsedTimeOfFirstRole(entryOfFirstRole.getValue());
      comparisonDataModel.add(comparisonRow);
    }

    List<String> categoryList = comparisonDataModel.stream().map(ElapsedTimeComparison::getCategory).collect(Collectors.toList());
    Map<Object, Number> resultOfSecondRole = chartSeriesOfSecondRole.getData();
    for (Map.Entry<Object, Number> entryOfSecondRole : resultOfSecondRole.entrySet()) {
      String category = entryOfSecondRole.getKey().toString();
      if (categoryList.contains(category)) {
        comparisonDataModel.stream()
          .filter(comparison -> comparison.getCategory().equals(category))
          .findFirst().get()
          .setElapsedTimeOfSecondRole(entryOfSecondRole.getValue());
      }
    }
  }

  private ChartSeries generateChartDataForCompareRole(TaskQuery taskQuery, RemoteRole roleToCompare) {
    Map<Object, Number> result = new HashMap<>();
    TaskQuery updatedTaskQueryForCompare = TaskQuery.fromJson(taskQuery.asJson());

    updatedTaskQueryForCompare.where().and().activatorName().isEqual(roleToCompare.getMemberName());

    List<ElapsedTimeStatistic> statisticData = statisticService.getElapsedTimeOfTasksStatisticData(updatedTaskQueryForCompare.asJson());
    Map<String, Number> elapsedTimeData = statisticService.generateDataForElapsedTimeChart(statisticData);

    for (Map.Entry<String, Number> entry : elapsedTimeData.entrySet()) {
      result.put((Object)entry.getKey(), entry.getValue());
    }

    if (result.size() == 0) {
      result.put((Object) StringUtils.EMPTY, 0);
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
}
