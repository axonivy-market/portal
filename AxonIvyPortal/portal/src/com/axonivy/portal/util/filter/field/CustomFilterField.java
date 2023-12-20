package com.axonivy.portal.util.filter.field;

import com.axonivy.portal.enums.dashboard.filter.FilterFormat;

public abstract class CustomFilterField extends FilterField {

  private FilterFormat format;

  public CustomFilterField() {
    super();
  }

  public CustomFilterField(String name, FilterFormat format) {
    this.name = name;
    this.format = format;
  }

  public FilterFormat getFormat() {
    return format;
  }

  public void setFormat(FilterFormat format) {
    this.format = format;
  }
}
