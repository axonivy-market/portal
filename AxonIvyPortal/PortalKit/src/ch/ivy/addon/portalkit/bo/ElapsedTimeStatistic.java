package ch.ivy.addon.portalkit.bo;

import java.util.Map;

public class ElapsedTimeStatistic {

  private Map<String, Long> averageElapsedTimeByCategory;

  public Map<String, Long> getAverageElapsedTimeByCategory() {
    return averageElapsedTimeByCategory;
  }

  public void setAverageElapsedTimeByCategory(Map<String, Long> averageElapsedTimeByCategory) {
    this.averageElapsedTimeByCategory = averageElapsedTimeByCategory;
  }
}
