package ch.ivy.addon.portalkit.bo;

public class ExpiryStatistic {

  private long numberOfTasksExpiredToday;
  private long numberOfTasksExpiredThisWeek;

  public long getNumberOfTasksExpiredToday() {
    return numberOfTasksExpiredToday;
  }

  public void setNumberOfTasksExpiredToday(long numberOfTasksExpiredToday) {
    this.numberOfTasksExpiredToday = numberOfTasksExpiredToday;
  }

  public long getNumberOfTasksExpiredThisWeek() {
    return numberOfTasksExpiredThisWeek;
  }

  public void setNumberOfTasksExpiredThisWeek(long numberOfTasksExpiredThisWeek) {
    this.numberOfTasksExpiredThisWeek = numberOfTasksExpiredThisWeek;
  }

}
