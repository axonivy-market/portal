package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.ivydata.utils.DateTimeFormatter;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.statistics.StatisticChartConstants;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ElapsedTimeDetailsBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private String selectedCaseCategory;
  private String chartName;

  public void initialize(String caseCategory, StatisticChart statisticChart) {
    setSelectedCaseCategory(caseCategory);
    chartName = statisticChart.getName();
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
  
  public String calculateElapsedTime(Number secondsValue) {
    return DateTimeFormatter.formatDateTimeToString(secondsValue);
  }
}
