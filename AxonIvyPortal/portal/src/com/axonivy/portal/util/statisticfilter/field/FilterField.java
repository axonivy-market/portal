package com.axonivy.portal.util.statisticfilter.field;

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
  
  public abstract String generateStringFilter(StatisticFilter filter);
}
