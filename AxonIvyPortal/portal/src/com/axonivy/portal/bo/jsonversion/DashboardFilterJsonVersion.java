package com.axonivy.portal.bo.jsonversion;

public class DashboardFilterJsonVersion extends AbstractJsonVersion {

  public static final DashboardFilterJsonVersion LATEST_VERSION = new DashboardFilterJsonVersion(LATEST);
  public static final DashboardFilterJsonVersion OLDEST_VERSION = new DashboardFilterJsonVersion(OLDEST);

  public DashboardFilterJsonVersion(String value) {
    super(value);
  }
}