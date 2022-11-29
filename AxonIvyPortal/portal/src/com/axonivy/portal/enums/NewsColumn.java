package com.axonivy.portal.enums;

public enum NewsColumn {
  ICON("icon"), NAME("name"), DESCRIPTION("description"), CREATED_DATE("createdDate");

  private String key;

  NewsColumn(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
