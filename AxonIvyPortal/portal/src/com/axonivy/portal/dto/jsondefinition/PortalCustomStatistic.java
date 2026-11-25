package com.axonivy.portal.dto.jsondefinition;

import java.util.List;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.enums.PortalSchema;

public class PortalCustomStatistic extends PortalJsonDefinition<Statistic> {

  public PortalCustomStatistic() {}

  public PortalCustomStatistic(List<Statistic> items) {
    super(PortalSchema.CUSTOM_STATISTIC, items);
  }
}
