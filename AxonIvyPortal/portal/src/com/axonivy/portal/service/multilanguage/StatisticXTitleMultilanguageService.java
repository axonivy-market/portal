package com.axonivy.portal.service.multilanguage;

import java.util.List;

import com.axonivy.portal.bean.StatisticConfigurationBean;

import ch.ivy.addon.portalkit.dto.DisplayName;

public class StatisticXTitleMultilanguageService extends AbstractMultilanguageService {
  private StatisticConfigurationBean container;

  public StatisticXTitleMultilanguageService(StatisticConfigurationBean container) {
    this.container = container;
  }

  @Override
  protected String getValue() {
    return container.getxTitle();
  }

  @Override
  protected void setValue(String value) {
    container.setxTitle(value);
  }

  @Override
  protected List<DisplayName> getValues() {
    return container.getConfigxTitles();
  }

}
