package com.axonivy.portal.service;

import java.time.DayOfWeek;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.AggregationInterval;

import ch.ivy.addon.portalkit.dto.dashboard.AbstractColumn;
import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivyteam.ivy.environment.Ivy;

public abstract class AbstractDrillDownService {

  private static final String DEFAULT_COLUMN_WIDTH = String.valueOf(AbstractColumn.NORMAL_WIDTH);
  
  public void createDrillDownDashboardInSession(Statistic statistic, String drillDownValue) {
    Dashboard drillDownDashboard = getDrillDownDashboard();
    DashboardWidget widget = drillDownDashboard.getWidgets().get(0);
    ensureAllRelatedColumnsIncluded(widget, statistic);
    DashboardFilter drillDownFilter = createDrillDownFilter(drillDownValue, statistic.getStatisticAggregation());

    getWidgetFilters(widget).add(drillDownFilter);
    if (CollectionUtils.isNotEmpty(statistic.getFilters())) {
      if (isDrillDownFilterDuplicatedWithFilters(drillDownFilter, statistic.getFilters())) {
        statistic.getFilters().removeIf(filter -> filter.getField().equals(drillDownFilter.getField()));
      }
      addStatisticFiltersToWidgetFilters(widget, statistic.getFilters());
    }
    buildWidgetName(statistic, widget);
    Ivy.session().setAttribute(SessionAttribute.DRILL_DOWN_DASHBOARD.name(), drillDownDashboard);
  }
  
  private boolean isDrillDownFilterDuplicatedWithFilters(DashboardFilter drillDownFilter, List<DashboardFilter> filterList) {
    return filterList.stream().map(item -> item.getField()).anyMatch(field -> field.equals(drillDownFilter.getField()));
  }

  private DashboardFilter createDrillDownFilter(String drillDownValue, StatisticAggregation aggregation) {
    DashboardFilter filter = new DashboardFilter();
    filter.setFilterType(aggregation.getType());
    filter.setField(getDashboardFilterFieldByAggregationField(aggregation.getField()));
    
    if (isTimestampAggregation(aggregation)) {
      setFilterForTimestampAggregation(drillDownValue, aggregation.getInterval(), filter);
    } else if (isCategoryAggregation(aggregation) && StringUtils.isBlank(drillDownValue)) {
      filter.setOperator(FilterOperator.NO_CATEGORY);
    } else {
      filter.setOperator(shouldUseInOperator(aggregation.getField()) ? FilterOperator.IN : FilterOperator.IS);
      filter.setValues(List.of(drillDownValue));
    }
    
    return filter;
  }

  private boolean isCategoryAggregation(StatisticAggregation statisticAgg) {
    return DashboardStandardCaseColumn.CATEGORY.getField().equals(statisticAgg.getField());
  }

  private boolean isTimestampAggregation(StatisticAggregation aggregation) {
    boolean isStandardTimestampField = AggregationField.TIMESTAMP_AGGREGATES.contains(aggregation.getField());
    boolean isCustomTimestampField =
        DashboardColumnType.CUSTOM == aggregation.getType() && aggregation.getInterval() != null;
    return isStandardTimestampField || isCustomTimestampField;
  }

  private void setFilterForTimestampAggregation(String drillDownValue, AggregationInterval interval,
      DashboardFilter drillDownFilter) {
    LocalDateTime startDateTime = LocalDateTime.ofInstant(Instant.parse(drillDownValue), ZoneId.systemDefault());
    drillDownFilter.setFromDate(Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant()));
    switch (interval) {
      case WEEK, MONTH, YEAR -> {
        drillDownFilter.setOperator(FilterOperator.BETWEEN);
        LocalDateTime endDateTime = switch (interval) {
          case WEEK -> startDateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
          case MONTH -> startDateTime.with(TemporalAdjusters.lastDayOfMonth());
          default -> startDateTime.with(TemporalAdjusters.lastDayOfYear()); // YEAR as default
        };
        drillDownFilter
            .setToDate(Date.from(endDateTime.with(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant()));
      }
      default -> drillDownFilter.setOperator(FilterOperator.IS); // DAY as default
    }
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

  protected abstract void buildWidgetName(Statistic statistic, DashboardWidget widget);
  
  protected abstract boolean shouldUseInOperator(String field);
  
  public abstract void removeWidgetFilterFromSession();
}
