package com.axonivy.portal.service.multilanguage;

import java.util.List;

import com.axonivy.portal.bo.Statistic;

import ch.ivy.addon.portalkit.dto.DisplayName;

public class StatisticNameMultilanguageService extends AbstractMultilanguageService {
  private Statistic container;

  public StatisticNameMultilanguageService(Statistic container) {
    this.container = container;
  }

  @Override
  protected String getValue() {
    return container.getName();
  }

  @Override
  protected void setValue(String value) {
    container.setName(value);
  }

  @Override
  protected List<DisplayName> getValues() {
    return container.getNames();
  }

}
