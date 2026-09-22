package com.axonivy.portal.util.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.axonivy.portal.bean.dashboard.WidgetPriorityFilterBean;
import com.axonivy.portal.bean.dashboard.WidgetStateFilterBean;
import com.axonivy.portal.components.service.DateTimeGlobalSettingService;
import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.util.filter.field.FilterField;

import ch.ivy.addon.portalkit.bean.LocaleBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;

public final class DefaultFilterChipLabelBuilder {

  private DefaultFilterChipLabelBuilder() {
  }

  public static List<String> build(List<DashboardFilter> filters, String widgetType) {
    if (CollectionUtils.isEmpty(filters)) {
      return List.of();
    }
    return filters.stream().map(filter -> buildChipLabel(filter, widgetType))
        .filter(StringUtils::isNotBlank).collect(Collectors.toList());
  }

  private static String buildChipLabel(DashboardFilter filter, String widgetType) {
    String filterLabel = StringUtils.defaultIfBlank(filter.getLabel(),
        Optional.ofNullable(filter.getFilterField()).map(FilterField::getLabel).orElse(filter.getField()));
    String operatorLabel = Optional.ofNullable(filter.getOperator()).map(FilterOperator::getLabel)
        .map(StringUtils::lowerCase).orElse(StringUtils.EMPTY);
    String value = resolveValue(filter, widgetType);
    String valueLabel = StringUtils.isBlank(value) ? StringUtils.EMPTY : String.format("\"%s\"", value);

    return Stream.of(filterLabel, operatorLabel, valueLabel).filter(StringUtils::isNotBlank)
        .collect(Collectors.joining(" "));
  }

  private static String resolveValue(DashboardFilter filter, String widgetType) {
    if (filter.getOperator() == null) {
      return StringUtils.EMPTY;
    }
    return switch (filter.getOperator()) {
      case EMPTY, NOT_EMPTY, TODAY, YESTERDAY, CURRENT_USER, CURRENT_USER_CAN_WORK_ON, NO_CATEGORY -> StringUtils.EMPTY;
      case BETWEEN, NOT_BETWEEN -> String.format("%s - %s", toDisplayValue(filter, filter.getFrom(), widgetType),
          toDisplayValue(filter, filter.getTo(), widgetType));
      case CURRENT -> filter.getPeriodType().getLabel();
      case LAST, NEXT -> resolvePeriodValue(filter);
      default -> resolveListValue(filter, widgetType);
    };
  }

  private static String resolvePeriodValue(DashboardFilter filter) {
    FilterPeriodType type = filter.getPeriodType();
    Long periods = filter.getPeriods();
    return periods + " " + (periods == 1 ? type.getLabel() : type.getPluralLabel());
  }

  private static String resolveListValue(DashboardFilter filter, String widgetType) {
    List<String> values = CollectionUtils.isNotEmpty(filter.getValues())
        ? filter.getValues() : resolveSingleValue(filter);
    return values.stream().map(value -> toDisplayValue(filter, value, widgetType))
        .collect(Collectors.joining(", "));
  }

  private static List<String> resolveSingleValue(DashboardFilter filter) {
    return Stream.of(filter.getValue(), filter.getFrom(), filter.getTo())
        .filter(StringUtils::isNotBlank).findFirst().map(List::of).orElseGet(List::of);
  }

  private static String toDisplayValue(DashboardFilter filter, String value, String widgetType) {
    if (StringUtils.isBlank(value)) {
      return StringUtils.EMPTY;
    }
    if (filter.getFilterFormat() == FilterFormat.DATE) {
      return formatDate(value);
    }
    if (filter.isState()) {
      return ((WidgetStateFilterBean) ManagedBeans.get("widgetStateFilterBean"))
          .getUserFriendlyState(value, widgetType);
    }
    if (filter.isPriority()) {
      return ((WidgetPriorityFilterBean) ManagedBeans.get("widgetPriorityFilterBean"))
          .getUserFriendlyTaskPriority(value);
    }
    return value;
  }

  private static String formatDate(String value) {
    try {
      Date date = DateUtils.parseDate(value, BaseFilter.DATE_FORMAT, BaseFilter.DMY_DATE_FORMAT,
          BaseFilter.DATE_FORMAT_WITHOUT_TIME, BaseFilter.DMY_DATE_FORMAT_WITHOUT_TIME);
      String pattern = DateTimeGlobalSettingService.getInstance().getDateTimePatternForDatePicker(true);
      return new SimpleDateFormat(pattern, ((LocaleBean) ManagedBeans.get("localeBean")).getLocale()).format(date);
    } catch (ParseException e) {
      return value;
    }
  }
}
