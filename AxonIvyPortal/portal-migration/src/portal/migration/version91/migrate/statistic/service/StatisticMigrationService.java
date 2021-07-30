package portal.migration.version91.migrate.statistic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;
import portal.migration.dto.DisplayName;
import portal.migration.ivydata.service.impl.LanguageService;
import portal.migration.service.BusinessDataService;
import portal.migration.version91.statistics.StatisticChart;

public class StatisticMigrationService extends BusinessDataService<StatisticChart> {

  @Override
  public Class<StatisticChart> getType() {
    return StatisticChart.class;
  }

  private List<StatisticChart> findAll() {
    List<StatisticChart> result = new ArrayList<>();
    try {
      Result<StatisticChart> queryResult = repo().search(getType()).limit(10000).execute();
      return queryResult.getAll();
    }catch (Exception e) {
      Ivy.log().error(e);
      return result;
    }
  }

  public void migrateStatisticCharts() {
    List<StatisticChart> foundCharts = findAll();
    if (CollectionUtils.isEmpty(foundCharts)) {
      return;
    }

    List<Locale> supportedLocales = LanguageService.newInstance().findUserLanguages().getIvyLanguage().getSupportedLanguages().stream()
        .map(languageString -> Locale.forLanguageTag(languageString))
        .collect(Collectors.toList());

    for (StatisticChart chart : foundCharts) {
      if (StringUtils.isNotBlank(chart.getName()) && CollectionUtils.isEmpty(chart.getNames())) {
        List<DisplayName> names = new ArrayList<>();
        for (Locale locale : supportedLocales) {
          DisplayName displayName = new DisplayName();
          displayName.setLocale(locale);
          displayName.setValue(chart.getName());
          names.add(displayName);
        }
        chart.setName(null);
        chart.setNames(names);
        save(chart);
      }
    }
  }

}

