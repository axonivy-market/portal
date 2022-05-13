package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticChartConstants;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ElapsedTimeDetailsBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private String selectedCaseCategory;
  private String chartName;
  private static final String DAYS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/elapsedTimeChart/days";
  private static final String HOURS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/elapsedTimeChart/hours";
  private static final String MINUTES_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/elapsedTimeChart/minutes";
  private static final String SECONDS_CMS = "/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/elapsedTimeChart/seconds";

  public void initialize(String caseCategory, StatisticChart statisticChart) {
    setSelectedCaseCategory(caseCategory);
    String currentLanguage =
        LanguageService.newInstance().findUserLanguages(Ivy.session().getSessionUserName(), Arrays.asList(IApplication.current().getName())).getIvyLanguages().get(0).getUserLanguage();
    chartName = CollectionUtils.emptyIfNull(statisticChart.getNames()).stream()
        .filter(name -> StatisticService.equalsDisplayNameLocale(name, currentLanguage))
        .map(DisplayName::getValue)
        .findFirst().orElse("");
  }

  public String getSelectedCaseCategory() {
    return selectedCaseCategory;
  }

  public void setSelectedCaseCategory(String selectedCaseCategory) {
    this.selectedCaseCategory = selectedCaseCategory;
  }

  public String getExcelFileName() {
    String fileName = chartName + "_" + selectedCaseCategory;
    fileName = fileName.replace(" ", "_");
    if (Ivy.cms().co(StatisticChartConstants.NO_CATEGORY_CMS).equals(selectedCaseCategory)) {
      fileName = fileName.replace("[", "").replace("]", "");
    }
    return fileName;
  }
  
  public   String calculateElapsedTime(Number value) {
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
}
