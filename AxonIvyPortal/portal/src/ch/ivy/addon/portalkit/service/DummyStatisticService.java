package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.primefaces.model.charts.donut.DonutChartModel;

import ch.ivy.addon.portalkit.bo.PriorityStatistic;
import ch.ivy.addon.portalkit.constant.DummyStatistic;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;

public class DummyStatisticService {

  public static StatisticChart createDummyChartForGuide() {
    StatisticChart dummyChart = new StatisticChart();
    dummyChart.setId("Dummy");
    dummyChart.setNames(namesForDummyChart());
    dummyChart.setType(StatisticChartType.TASK_BY_PRIORITY);
    
    PriorityStatistic data = new PriorityStatistic();
    data.setNormal(1);
    StatisticService service = StatisticService.getInstance();
    DonutChartModel model = service.generateTaskByPriorityModel(data, true);
    dummyChart.setDonutChartModel(model);
    return dummyChart;
  }

  private static List<DisplayName> namesForDummyChart() {
    List<DisplayName> result = new ArrayList<>();
    List<String> supportedLanguages = LanguageService.newInstance().findUserLanguages().getIvyLanguage().getSupportedLanguages();
    for (String language : supportedLanguages) {
      DisplayName newChartName = new DisplayName();
      newChartName.setLocale(Locale.forLanguageTag(language));
      newChartName.setValue(DummyStatistic.CHART_NAME);
      result.add(newChartName);
    }
    return result;
  }
}
