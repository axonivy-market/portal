package com.axonivy.portal.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivyteam.ivy.environment.Ivy;

public abstract class AbstractDrillDownService {

  private static final String DEFAULT_COLUMN_WIDTH = "120";
  private static final List<String> ALL_AGGREGATION_DATE_FIELDS =
      Arrays.asList("startTimestamp", "endTimestamp", "expiryTimestamp");

  public void createDrillDownDashboardInSession(Statistic statistic, String drillDownValue) {
    Dashboard drillDownDashboard = getDrillDownDashboard();
    DashboardWidget widget = drillDownDashboard.getWidgets().get(0);
    ensureAllRelatedColumnsIncluded(widget, statistic);
    addDrillDownValueToWidgetFilters(drillDownValue, statistic, widget);
    if (CollectionUtils.isNotEmpty(statistic.getFilters())) {
      addStatisticFiltersToWidgetFilters(widget, statistic.getFilters());
    }
    Ivy.session().setAttribute(SessionAttribute.DRILL_DOWN_DASHBOARD.name(), drillDownDashboard);
  }

  private void addDrillDownValueToWidgetFilters(String drillDownValue, Statistic chart, DashboardWidget widget) {
    DashboardFilter drillDownFilter = new DashboardFilter();
    drillDownFilter.setFilterType(DashboardColumnType.STANDARD);
    drillDownFilter.setField(getDashboardFilterFieldByAggregationField(chart.getStatisticAggregation().getField()));
    if (ALL_AGGREGATION_DATE_FIELDS.contains(chart.getStatisticAggregation().getField())
        || (DashboardColumnType.CUSTOM == chart.getStatisticAggregation().getType()
            && chart.getStatisticAggregation().getInterval() != null)) {
      drillDownFilter.setOperator(FilterOperator.IS);
      drillDownFilter.setFromDate(Date.from(Instant.parse(drillDownValue)));
    } else {
      drillDownFilter.setOperator(FilterOperator.IN);
      drillDownFilter.setValues(Arrays.asList(String.valueOf(drillDownValue)));
    }
    getWidgetFilters(widget).add(drillDownFilter);
  }

  private void ensureAllRelatedColumnsIncluded(DashboardWidget widget, Statistic chart) {
    List<? extends ColumnModel> columns = getWidgetColumns(widget);
    String aggregationField = getDashboardFilterFieldByAggregationField(chart.getStatisticAggregation().getField());
    DashboardColumnType aggregationFieldType = chart.getStatisticAggregation().getType();
    ensureColumnIncluded(columns, aggregationField, aggregationFieldType);

    if (StringUtils.isNotBlank(chart.getStatisticAggregation().getKpiField())) {
      ensureColumnIncluded(columns, chart.getStatisticAggregation().getKpiField(), DashboardColumnType.CUSTOM);
    }

    if (CollectionUtils.isNotEmpty(chart.getFilters())) {
      for (DashboardFilter filter : chart.getFilters()) {
        ensureColumnIncluded(columns, filter.getField(), filter.getFilterType());
      }
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private void ensureColumnIncluded(List columns, String name, DashboardColumnType type) {
    boolean fieldExisted = columns.stream().anyMatch(column -> name.equals(((ColumnModel) column).getField()));
    if (!fieldExisted) {
      ColumnModel newColumn = createWidgetColumn(type, name);
      newColumn.setField(name);
      newColumn.setType(type);
      newColumn.setWidth(DEFAULT_COLUMN_WIDTH);
      int insertPosition = Math.max(0, columns.size() - 1);
      columns.add(insertPosition, newColumn);
    }
  }

  protected abstract String getDashboardFilterFieldByAggregationField(String field);

  protected abstract Dashboard getDrillDownDashboard();

  protected abstract List<DashboardFilter> getWidgetFilters(DashboardWidget widget);

  protected abstract List<? extends ColumnModel> getWidgetColumns(DashboardWidget widget);

  protected abstract ColumnModel createWidgetColumn(DashboardColumnType fieldType, String field);

  protected abstract void addStatisticFiltersToWidgetFilters(DashboardWidget widget, List<DashboardFilter> filters);

}
