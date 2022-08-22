package ch.ivy.addon.portalkit.bo;

import java.util.Map;

public class TaskCategoryStatistic {
  private Map<String, Long> numberOfTasksByCategory;

  public Map<String, Long> getNumberOfTasksByCategory() {
    return numberOfTasksByCategory;
  }

  public void setNumberOfTasksByCategory(Map<String, Long> numberOfTasksByCategory) {
    this.numberOfTasksByCategory = numberOfTasksByCategory;
  }
  
}
