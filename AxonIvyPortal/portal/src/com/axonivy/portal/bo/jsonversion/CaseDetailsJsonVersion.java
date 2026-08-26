package com.axonivy.portal.bo.jsonversion;

public class CaseDetailsJsonVersion extends AbstractJsonVersion {

  public static final CaseDetailsJsonVersion LATEST_VERSION = new CaseDetailsJsonVersion(LATEST);
  public static final CaseDetailsJsonVersion OLDEST_VERSION = new CaseDetailsJsonVersion(OLDEST);

  public CaseDetailsJsonVersion(String value) {
    super(value);
  }
}
