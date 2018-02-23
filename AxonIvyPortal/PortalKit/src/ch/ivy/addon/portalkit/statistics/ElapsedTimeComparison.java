package ch.ivy.addon.portalkit.statistics;

public class ElapsedTimeComparison {
  private String category;
  private Number elapsedTimeOfFirstRole;
  private Number elapsedTimeOfSecondRole;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Number getElapsedTimeOfFirstRole() {
    return elapsedTimeOfFirstRole;
  }

  public void setElapsedTimeOfFirstRole(Number elapsedTimeOfFirstRole) {
    this.elapsedTimeOfFirstRole = elapsedTimeOfFirstRole;
  }

  public Number getElapsedTimeOfSecondRole() {
    return elapsedTimeOfSecondRole;
  }

  public void setElapsedTimeOfSecondRole(Number elapsedTimeOfSecondRole) {
    this.elapsedTimeOfSecondRole = elapsedTimeOfSecondRole;
  }
}
