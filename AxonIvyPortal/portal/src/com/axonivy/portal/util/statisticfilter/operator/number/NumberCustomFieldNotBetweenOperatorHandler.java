package com.axonivy.portal.util.statisticfilter.operator.number;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.constant.PortalConstants;

public class NumberCustomFieldNotBetweenOperatorHandler {
  private static NumberCustomFieldNotBetweenOperatorHandler instance;

  public static NumberCustomFieldNotBetweenOperatorHandler getInstance() {
    if (instance == null) {
      instance = new NumberCustomFieldNotBetweenOperatorHandler();
    }
    return instance;
  }
  
  public String buildFilter(DashboardFilter filter) {
    if (StringUtils.isEmpty(filter.getFrom()) && StringUtils.isEmpty(filter.getTo())) {
      return StringUtils.EMPTY;
    }

    if (StringUtils.isNotEmpty(filter.getFrom()) && StringUtils.isNotEmpty(filter.getTo())) {
      StringBuilder sb = new StringBuilder();
      sb.append(StatisticConstants.CUSTOM_NUMBER)
        .append(filter.getField())
        .append(PortalConstants.COLON)
        .append("<")
        .append(filter.getFrom())
        .append(" ")
        .append(">")
        .append(filter.getTo());
      return sb.toString();
    }
    
    if (StringUtils.isNotEmpty(filter.getFrom())){
      StringBuilder sb = new StringBuilder();
      sb.append(StatisticConstants.CUSTOM_NUMBER)
        .append(filter.getField())
        .append(PortalConstants.COLON)
        .append("<")
        .append(filter.getFrom());
      return sb.toString();
    }
    
    if (StringUtils.isNotEmpty(filter.getTo())){
      StringBuilder sb = new StringBuilder();
      sb.append(StatisticConstants.CUSTOM_NUMBER)
        .append(filter.getField())
        .append(PortalConstants.COLON)
        .append(">")
        .append(filter.getTo());
      return sb.toString();
    }
    return StringUtils.EMPTY;
  }
}
