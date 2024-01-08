package com.axonivy.portal.developerexamples.dto;

public class DashboardFilterJsonVersion {
  public static final String LATEST = "11.2.0";
  public static final String OLDEST = "10.0.0";
  public static final DashboardFilterJsonVersion LATEST_VERSION = new DashboardFilterJsonVersion(LATEST);
  public static final DashboardFilterJsonVersion OLDEST_VERSION = new DashboardFilterJsonVersion(OLDEST);
  private String value;

  public DashboardFilterJsonVersion(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
