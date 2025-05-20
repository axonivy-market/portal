package com.axonivy.portal.util.statisticfilter.operator.number;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class NumberCustomFieldGreaterOrEqualOperatorHandler {
  private static NumberCustomFieldGreaterOrEqualOperatorHandler instance;

  public static NumberCustomFieldGreaterOrEqualOperatorHandler getInstance() {
    if (instance == null) {
      instance = new NumberCustomFieldGreaterOrEqualOperatorHandler();
    }
    return instance;
  }
  
  public String buildFilter(DashboardFilter filter) {
    if (StringUtils.isEmpty(filter.getValue())) {
      return StringUtils.EMPTY;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(StatisticConstants.CUSTOM_NUMBER)
      .append(filter.getField())
      .append(PortalConstants.COLON)
      .append(">=")
      .append(filter.getValue());
    return sb.toString();
  }
}
