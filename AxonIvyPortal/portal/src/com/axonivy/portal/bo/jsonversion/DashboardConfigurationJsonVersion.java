package com.axonivy.portal.bo.jsonversion;

public class DashboardConfigurationJsonVersion extends AbstractJsonVersion {

  public static final DashboardConfigurationJsonVersion LATEST_VERSION = new DashboardConfigurationJsonVersion(LATEST);
  public static final DashboardConfigurationJsonVersion OLDEST_VERSION = new DashboardConfigurationJsonVersion(OLDEST);

  public DashboardConfigurationJsonVersion(String value) {
    super(value);
  }
}