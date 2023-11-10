package com.axonivy.portal.bo.jsonversion;

public class DashboardTemplateJsonVersion extends AbstractJsonVersion {

  public static final DashboardTemplateJsonVersion LATEST_VERSION = new DashboardTemplateJsonVersion(LATEST);
  public static final DashboardTemplateJsonVersion OLDEST_VERSION = new DashboardTemplateJsonVersion(OLDEST);

  public DashboardTemplateJsonVersion(String value) {
    super(value);
  }
}