package ch.ivy.addon.portalkit.bo;

import java.util.Date;
import java.util.Map;

public class ExpiryStatistic {

  private Map<Date, Long> numberOfTasksByExpiryTime;

  public Map<Date, Long> getNumberOfTasksByExpiryTime() {
    return numberOfTasksByExpiryTime;
  }

  public void setNumberOfTasksByExpiryTime(Map<Date, Long> numberOfTasksByExpiryTime) {
    this.numberOfTasksByExpiryTime = numberOfTasksByExpiryTime;
  }
  
}
