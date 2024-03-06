package com.axonivy.portal.selenium.common;

public enum FilterOperator {
  AFTER("Is After"),
  BEFORE("Is Before"),
  BETWEEN("Between"),
  CONTAINS("Contains"),
  CURRENT("Current"),
  CURRENT_USER("Current user"),
  CURRENT_USER_CAN_WORK_ON("Current user can work on"),
  EMPTY("Is Empty"),
  END_WITH("Ends With"),
  EQUAL("Equal"),
  GREATER("Greater"),
  GREATER_OR_EQUAL("Greater or equal"),
  IN("In"),
  IS("Is"),
  IS_NOT("Is Not"),
  LAST("Within the last"),
  LESS("Less"),
  LESS_OR_EQUAL("Less or equal"),
  NEXT("Within the next"),
  NOT_BETWEEN("Not Between"),
  NOT_CONTAINS("Doesn’t Contain"),
  NOT_EMPTY("Is Not Empty"),
  NOT_END_WITH("Doesn’t end with"),
  NOT_EQUAL("Not equal"),
  NOT_IN("Not In"),
  NOT_START_WITH("Doesn’t start with"),
  NO_CATEGORY("No Category"),
  START_WITH("Starts With"),
  TODAY("Today"),
  YESTERDAY("Yesterday"),
  WITHIN_NEXT("Within the next"),
  WITHIN_LAST("Within the last");

  private String value;

  private FilterOperator(String value) {
    this.setValue(value);
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
