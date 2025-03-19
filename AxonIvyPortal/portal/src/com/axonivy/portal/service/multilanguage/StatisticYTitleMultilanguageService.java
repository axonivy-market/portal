package com.axonivy.portal.service.multilanguage;

import java.util.List;

import com.axonivy.portal.bean.StatisticConfigurationBean;

import ch.ivy.addon.portalkit.dto.DisplayName;

public class StatisticYTitleMultilanguageService extends AbstractMultilanguageService {
  private StatisticConfigurationBean container;

  public StatisticYTitleMultilanguageService(StatisticConfigurationBean container) {
    this.container = container;
  }

  @Override
  protected String getValue() {
    return container.getyTitle();
  }

  @Override
  protected void setValue(String value) {
    container.setyTitle(value);
  }

  @Override
  protected List<DisplayName> getValues() {
    return container.getConfigyTitles();
  }

}
