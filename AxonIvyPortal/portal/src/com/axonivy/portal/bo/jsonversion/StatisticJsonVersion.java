package com.axonivy.portal.bo.jsonversion;

public class StatisticJsonVersion extends AbstractJsonVersion{

  public static final StatisticJsonVersion LATEST_VERSION = new StatisticJsonVersion(LATEST);
  public static final StatisticJsonVersion OLDEST_VERSION = new StatisticJsonVersion("11.3");

  public StatisticJsonVersion(String value) {
    super(value);
  }
}
