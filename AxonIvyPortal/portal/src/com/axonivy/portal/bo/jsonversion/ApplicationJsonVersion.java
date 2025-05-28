package com.axonivy.portal.bo.jsonversion;

public class ApplicationJsonVersion extends AbstractJsonVersion {

  public static final ApplicationJsonVersion LATEST_VERSION = new ApplicationJsonVersion(LATEST);
  public static final ApplicationJsonVersion OLDEST_VERSION = new ApplicationJsonVersion(OLDEST);

  public ApplicationJsonVersion(String value) {
    super(value);
  }
}
