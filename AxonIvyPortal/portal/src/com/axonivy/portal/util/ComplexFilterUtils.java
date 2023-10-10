package com.axonivy.portal.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivyteam.ivy.environment.Ivy;

public class ComplexFilterUtils {

  private static final String DATE_PATTERN = "dd/MM/yyyy";
  private static final String DATE_PATTERN_ALT = "dd-MM-yyyy";

  public static void convertFilterQueryToColumnModelFilters(DashboardWidget widget) {
    
  }


  private static List<String> getFilterTokens(String filterStr) {
    List<String> result = new ArrayList<String>();

    if (StringUtils.isBlank(filterStr)) {
      return result;
    }

    return Arrays.asList(filterStr.split("\\s+"));
  }

  private static void applyFilterConditionForCreatedDate(ColumnModel column, List<String> tokens) {
    /*
     * FilterOperator dateFilterType =
     * FilterOperator.valueOf(tokens.get(1).toUpperCase()); if (dateFilterType !=
     * null) { column.setDateFilterType(dateFilterType); }
     * 
     * switch (dateFilterType) { case BEFORE: applyDateFilterTypeBefore(column,
     * tokens); break; case BETWEEN: applyDateFilterTypeBetween(column, tokens);
     * break; case CURRENT: throw new
     * UnsupportedOperationException("Unimplemented case: " + dateFilterType); case
     * LAST: throw new UnsupportedOperationException("Unimplemented case: " +
     * dateFilterType); case NEXT: break; default: break; }
     */
  }

  private static void applyDateFilterTypeBefore(ColumnModel column, List<String> tokens) {
    Date toDate = getDate(tokens.get(2));
    column.setFilterTo(PortalDateUtils.getStartOfDate(toDate).toString());
  }

  private static void applyDateFilterTypeBetween(ColumnModel column, List<String> tokens) {
    Date fromDate = getDate(tokens.get(2));
    Date toDate = getDate(tokens.get(3));

    column.setFilterFrom(PortalDateUtils.getStartOfDate(fromDate).toString());
    column.setFilterTo(PortalDateUtils.getEndOfDate(toDate).toString());
  }

  private static String removeBeginningAndEndingQuotes(String words) {
    return Optional.ofNullable(words).orElse("").replaceAll("^\"|\"$", "");
  }

  private static Date getDate(String dateStr) {
    try {
      return DateUtils.parseDate(removeBeginningAndEndingQuotes(dateStr), DATE_PATTERN, DATE_PATTERN_ALT);
    } catch (ParseException e) {
      Ivy.log().error(e);
      return null;
    }
  }
}
