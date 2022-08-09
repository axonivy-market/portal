package ch.ivy.addon.portalkit.bo;

import java.util.Map;

public class TaskStateStatistic {
  private Map<Integer, Long> numberOfTasksByState;

  public Map<Integer, Long> getNumberOfTasksByState() {
    return numberOfTasksByState;
  }

  public void setNumberOfTasksByState(Map<Integer, Long> numberOfTasksByState) {
    this.numberOfTasksByState = numberOfTasksByState;
  }
}
