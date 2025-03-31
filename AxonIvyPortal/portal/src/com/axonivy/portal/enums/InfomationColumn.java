package com.axonivy.portal.enums;

public enum InfomationColumn {
  ICON("icon"), NAME("name"), CONTENT("content");

  private String key;

  InfomationColumn(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
