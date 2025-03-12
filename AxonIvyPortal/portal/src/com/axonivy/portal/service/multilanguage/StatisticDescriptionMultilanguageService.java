package com.axonivy.portal.service.multilanguage;

import java.util.List;

import com.axonivy.portal.bo.Statistic;

import ch.ivy.addon.portalkit.dto.DisplayName;

public class StatisticDescriptionMultilanguageService extends AbstractMultilanguageService {
  private Statistic container;

  public StatisticDescriptionMultilanguageService(Statistic container) {
    this.container = container;
  }

  @Override
  protected String getValue() {
    return container.getDescription();
  }

  @Override
  protected void setValue(String value) {
    container.setDescription(value);
  }

  @Override
  protected List<DisplayName> getValues() {
    return container.getDescriptions();
  }

}
