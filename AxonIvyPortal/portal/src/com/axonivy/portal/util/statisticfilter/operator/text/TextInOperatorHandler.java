package com.axonivy.portal.util.statisticfilter.operator.text;

import java.util.ArrayList;

import java.util.Arrays;

import org.apache.commons.collections4.CollectionUtils;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;

public class TextInOperatorHandler {
  private static final String DOUBLE_QUOTE_WITH_SPACE = "\" ";
  private static final String DOUBLE_QUOTE = "\"";
  private static final String HASH_PREFIX = "#";
  private static TextInOperatorHandler instance;

  public static TextInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new TextInOperatorHandler();
    }
    return instance;
  }

  public String buildFilter(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return StringUtils.EMPTY;
    }

    StringBuilder sb = new StringBuilder();
    String field = changeFilterField(filter.getField());
    sb.append(field).append(PortalConstants.COLON);

    String prefix, suffix;

    if (filter.isCreator()) {
        prefix = HASH_PREFIX;
        suffix = StringUtils.SPACE;
    } else if (filter.isCategory()) {
        prefix = DOUBLE_QUOTE;
        suffix = DOUBLE_QUOTE_WITH_SPACE;
    } else {
        prefix = StringUtils.EMPTY;
        suffix = StringUtils.SPACE;
    }

    for (String value : filter.getValues()) {
        sb.append(prefix).append(value).append(suffix);
    }

    return sb.toString();
  }
  
  public String buildFilterForCurrentUserOperator(DashboardFilter filter) {
    String currentUserMemberName = StringUtils.EMPTY;
    if (filter.isCreator()) {
      currentUserMemberName = SecurityMemberUtils.getCurrentSessionUserAsSecurityMemberDTO().getName();
    } else {
      currentUserMemberName = SecurityMemberUtils.getCurrentSessionUserAsSecurityMemberDTO().getMemberName();
    }
    filter.setValues(new ArrayList<>(Arrays.asList(currentUserMemberName)));
    return buildFilter(filter);
  }

  public String buildFilterWithoutValue(DashboardFilter filter) {
    return changeFilterField(filter.getField());
  }

  private String changeFilterField(String field) {
    return switch (field) {
    case StatisticConstants.STATE -> StatisticConstants.BUSSINESS_STATE;
    case StatisticConstants.ACTIVATOR -> StatisticConstants.RESPONSIBLE_NAME;
    case StatisticConstants.CREATOR -> StatisticConstants.CREATOR_NAME;
    default -> field;
    };
  }

}
