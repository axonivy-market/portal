package com.axonivy.portal.util.statisticfilter.operator.number;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class NumberCustomFieldBetweenOperatorHandler {
  private static NumberCustomFieldBetweenOperatorHandler instance;

  public static NumberCustomFieldBetweenOperatorHandler getInstance() {
    if (instance == null) {
      instance = new NumberCustomFieldBetweenOperatorHandler();
    }
    return instance;
  }
  
  public String buildFilter(DashboardFilter filter) {
    if (StringUtils.isEmpty(filter.getFrom()) || StringUtils.isEmpty(filter.getTo())) {
      return StringUtils.EMPTY;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(StatisticConstants.CUSTOM_NUMBER)
      .append(filter.getField())
      .append(PortalConstants.COLON)
      .append(">=")
      .append(filter.getFrom())
      .append(PortalConstants.COMMA)
      .append(StatisticConstants.CUSTOM_NUMBER)
      .append(filter.getField())
      .append(PortalConstants.COLON)
      .append("<=")
      .append(filter.getTo());
    return sb.toString();
    
  }
}
