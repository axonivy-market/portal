package ch.ivy.addon.portalkit.statistics;

public class ElapsedTimeComparison {
  private String category;
  private String elapsedTimeOfFirstRole;
  private String elapsedTimeOfSecondRole;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getElapsedTimeOfFirstRole() {
    return elapsedTimeOfFirstRole;
  }

  public void setElapsedTimeOfFirstRole(String elapsedTimeOfFirstRole) {
    this.elapsedTimeOfFirstRole = elapsedTimeOfFirstRole;
  }

  public String getElapsedTimeOfSecondRole() {
    return elapsedTimeOfSecondRole;
  }

  public void setElapsedTimeOfSecondRole(String elapsedTimeOfSecondRole) {
    this.elapsedTimeOfSecondRole = elapsedTimeOfSecondRole;
  }
}
