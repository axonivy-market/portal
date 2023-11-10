package com.axonivy.portal.bo.jsonversion;

public class DashboardJsonVersion extends AbstractJsonVersion {

  public static final DashboardJsonVersion LATEST_VERSION = new DashboardJsonVersion(LATEST);
  public static final DashboardJsonVersion OLDEST_VERSION = new DashboardJsonVersion(OLDEST);

  public DashboardJsonVersion(String value) {
    super(value);
  }
}
