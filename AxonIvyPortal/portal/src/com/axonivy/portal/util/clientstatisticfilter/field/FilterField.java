package com.axonivy.portal.util.clientstatisticfilter.field;

import com.axonivy.portal.dto.statistic.StatisticFilter;

public abstract class FilterField {

  protected String name;

  public FilterField() {
    super();
  }

  public FilterField(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract String getLabel();

  public abstract void initFilter(StatisticFilter filter);

  public abstract void addNewFilter(StatisticFilter filter);
}
