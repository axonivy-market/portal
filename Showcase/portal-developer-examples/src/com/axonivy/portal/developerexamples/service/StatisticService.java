package com.axonivy.portal.developerexamples.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.axonivy.portal.components.enums.PortalVariable;
import com.axonivy.portal.developerexamples.dto.StatisticChart;

public class StatisticService extends JsonConfigurationService<StatisticChart> {

  private int numberOfDefaultCharts;

  private static StatisticService instance;

  private StatisticService() {}

  public static StatisticService getInstance() {
    if (instance == null) {
      instance = new StatisticService();
    }
    return instance;
  }

  public int getNumberOfDefaultCharts() {
    return numberOfDefaultCharts;
  }

  public List<StatisticChart> findStatisticCharts() {
    List<StatisticChart> charts = new ArrayList<>(getPublicConfig());
    numberOfDefaultCharts = charts.size();
    charts.addAll(getPrivateConfig().stream().sorted(Comparator.comparingLong(StatisticChart::getPosition))
        .collect(Collectors.toList()));
    return charts;
  }


  @Override
  public Class<StatisticChart> getType() {
    return StatisticChart.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.STATISTIC_CHART.key;
  }
}
