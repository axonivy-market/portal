package com.axonivy.portal.util.statisticfilter.field;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.dto.statistic.StatisticFilter;

import ch.ivyteam.ivy.environment.Ivy;

public class FilterFieldDefault extends FilterField {

  public FilterFieldDefault() {
    super(BaseFilter.DEFAULT);
  }

  @Override
  public String getLabel() {
    return Ivy.cms()
        .co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/pleaseSelect");
  }

  @Override
  public void initFilter(StatisticFilter filter) {
    filter.setFilterField(null);
    filter.setField(null);
  }

  @Override
  public void addNewFilter(StatisticFilter filter) {
    initFilter(filter);
  }

  @Override
  public String generateStringFilter(StatisticFilter filter) {
    return null;
  }
}
