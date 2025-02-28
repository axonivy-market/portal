package com.axonivy.portal.bo.jsonversion;

public class ClientStatisticJsonVersion extends AbstractJsonVersion{

  public static final ClientStatisticJsonVersion LATEST_VERSION = new ClientStatisticJsonVersion(LATEST);
  public static final ClientStatisticJsonVersion OLDEST_VERSION = new ClientStatisticJsonVersion(OLDEST);

  public ClientStatisticJsonVersion(String value) {
    super(value);
  }
}
