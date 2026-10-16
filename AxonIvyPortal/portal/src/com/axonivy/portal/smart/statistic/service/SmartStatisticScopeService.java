package com.axonivy.portal.smart.statistic.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.ChartTarget;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Renders a chart's configuration as the one-line "what am I looking at" caption under each
 * card - what is counted, how it is broken down, and which records the filters let through.
 *
 * This is deliberately mechanical and derived from the stored configuration, not written by the
 * agent: it is therefore always accurate and always present, including for charts created before
 * the AI existed, and it never drifts out of sync with the chart it describes. For Smart
 * Statistic charts this text stands in for the chart's description everywhere the page shows
 * one - the card caption, the draft preview, and the context given to the agent when it reads a
 * chart's numbers.
 */
public class SmartStatisticScopeService {

  private static final String SEPARATOR = " · ";
  private static final String LIST_SEPARATOR = ", ";

  /** Two named filters then a count: enough to recognise the chart, short enough to scan. */
  private static final int DETAILED_FILTERS = 2;
  /** Beyond this a single filter's own value list would crowd out the next filter. */
  private static final int MAX_VALUES = 3;

  /**
   * Operators whose meaning is carried entirely by the values, so "State: Open" is exact.
   * Every other operator - especially the negating ones - must show its own label, or the
   * caption would claim the opposite of what the chart does.
   */
  private static final Set<FilterOperator> VALUE_SPEAKS_FOR_ITSELF =
      EnumSet.of(FilterOperator.IN, FilterOperator.IS, FilterOperator.EQUAL);

  /** Operators that describe a window rather than a set: "Expiry: next 3 days". */
  private static final Set<FilterOperator> PERIOD_OPERATORS =
      EnumSet.of(FilterOperator.LAST, FilterOperator.NEXT, FilterOperator.CURRENT);

  private SmartStatisticScopeService() {}

  public static String describe(Statistic statistic) {
    if (statistic == null) {
      return StringUtils.EMPTY;
    }
    List<String> parts = new ArrayList<>();
    if (statistic.getChartTarget() != null) {
      parts.add(statistic.getChartTarget().getCmsName());
    }
    String groupBy = groupByLabel(statistic.getStatisticAggregation());
    if (StringUtils.isNotBlank(groupBy)) {
      parts.add(text("by {0}", groupBy));
    }
    parts.add(filterSummary(statistic));
    return String.join(SEPARATOR, parts);
  }

  // ------------------------------------------------------------------ group by

  private static String groupByLabel(StatisticAggregation aggregation) {
    if (aggregation == null) {
      return StringUtils.EMPTY;
    }
    // A custom field carries the placeholder in `field` and the real name in `customFieldValue`,
    // and the real name is the only one a reader would recognise.
    String label = StringUtils.isNotBlank(aggregation.getCustomFieldValue())
        ? aggregation.getCustomFieldValue()
        : SmartStatisticVocabularyService.findAggregationField(aggregation.getField())
            .map(AggregationField::getCmsName)
            .orElse(StringUtils.defaultString(aggregation.getField()));
    if (StringUtils.isBlank(label)) {
      return StringUtils.EMPTY;
    }
    if (aggregation.getInterval() != null) {
      label = label + " (" + aggregation.getInterval().getCmsName() + ")";
    }
    String kpi = kpiLabel(aggregation);
    return StringUtils.isBlank(kpi) ? label : label + SEPARATOR + kpi;
  }

  /** A KPI changes the unit from "how many" to "how much", which is worth saying out loud. */
  private static String kpiLabel(StatisticAggregation aggregation) {
    String field = StringUtils.trimToNull(aggregation.getKpiField());
    if (field == null || SmartStatisticVocabularyService.COUNTING.equalsIgnoreCase(field)) {
      return StringUtils.EMPTY;
    }
    String readable = SmartStatisticVocabularyService.findAggregationField(field)
        .map(AggregationField::getCmsName).orElse(field);
    String method = StringUtils.trimToNull(aggregation.getAggregationMethod());
    return method == null ? readable : text("{0} of {1}", method, readable);
  }

  // ------------------------------------------------------------------- filters

  /**
   * Names the first few filters and counts the rest: "Expiry: today, Can work on: me, and 4
   * other filters". A bare count told the reader a chart was filtered without telling them how,
   * which is the one thing they needed to know.
   */
  private static String filterSummary(Statistic statistic) {
    List<DashboardFilter> filters = statistic.getFilters();
    if (CollectionUtils.isEmpty(filters)) {
      return "no filter";
    }
    List<String> named = filters.stream()
        .limit(DETAILED_FILTERS)
        .map(filter -> describeFilter(filter, statistic.getChartTarget()))
        .filter(StringUtils::isNotBlank)
        .collect(Collectors.toList());
    if (named.isEmpty()) {
      return "no filter";
    }
    String head = String.join(LIST_SEPARATOR, named);
    int rest = filters.size() - named.size();
    if (rest <= 0) {
      return head;
    }
    return rest == 1 ? text("{0}, and 1 other filter", head) : text("{0}, and {1} other filters", head, rest);
  }

  private static String describeFilter(DashboardFilter filter, ChartTarget target) {
    if (filter == null || StringUtils.isBlank(filter.getField())) {
      return StringUtils.EMPTY;
    }
    String label = StatisticFilterFieldService.labelOf(target, filter.getField());
    String detail = filterDetail(filter, target);
    return StringUtils.isBlank(detail) ? label : text("{0}: {1}", label, detail);
  }

  private static String filterDetail(DashboardFilter filter, ChartTarget target) {
    FilterOperator operator = filter.getOperator();
    List<String> values = localizedValues(filter, target);

    if (operator == null) {
      return String.join(LIST_SEPARATOR, values);
    }
    if (PERIOD_OPERATORS.contains(operator)) {
      String window = periodWindow(filter);
      return StringUtils.isBlank(window)
          ? operator.getLabel()
          : operator.getLabel() + StringUtils.SPACE + window;
    }
    if (FilterOperator.BETWEEN == operator || FilterOperator.NOT_BETWEEN == operator) {
      String range = StringUtils.trimToEmpty(filter.getFrom()) + " - "
          + StringUtils.trimToEmpty(filter.getTo());
      return "-".equals(range.trim()) ? operator.getLabel()
          : operator.getLabel() + StringUtils.SPACE + range;
    }
    if (values.isEmpty()) {
      // TODAY, YESTERDAY, EMPTY, CURRENT_USER, NO_CATEGORY and friends say it all themselves.
      return operator.getLabel();
    }
    String joined = String.join(LIST_SEPARATOR, values);
    return VALUE_SPEAKS_FOR_ITSELF.contains(operator)
        ? joined
        : operator.getLabel() + StringUtils.SPACE + joined;
  }

  private static String periodWindow(DashboardFilter filter) {
    if (filter.getPeriods() == null || filter.getPeriodType() == null) {
      return StringUtils.EMPTY;
    }
    return filter.getPeriods() + StringUtils.SPACE + filter.getPeriodType().getLabel();
  }

  /**
   * States and priorities are stored as enum names. They are localised here through the very
   * same CMS entries {@code StatisticService} uses for the chart's own bucket labels, so the
   * caption and the bars underneath it never disagree about what a state is called.
   */
  private static List<String> localizedValues(DashboardFilter filter, ChartTarget target) {
    List<String> values = filter.getValues();
    if (CollectionUtils.isEmpty(values)) {
      return List.of();
    }
    String field = filter.getField();
    List<String> readable = values.stream()
        .filter(StringUtils::isNotBlank)
        .limit(MAX_VALUES)
        .map(value -> localizeValue(field, value, target))
        .collect(Collectors.toList());
    int rest = values.size() - readable.size();
    if (rest > 0) {
      readable.set(readable.size() - 1,
          text("{0} +{1}", readable.get(readable.size() - 1), rest));
    }
    return readable;
  }

  private static String localizeValue(String field, String value, ChartTarget target) {
    if (StatisticConstants.STATE.equals(field)) {
      String path = ChartTarget.CASE == target
          ? "/ch.ivy.addon.portalkit.ui.jsf/businessCaseState/"
          : "/ch.ivy.addon.portalkit.ui.jsf/taskBusinessState/";
      return StringUtils.defaultIfBlank(Ivy.cms().co(path + value), value);
    }
    if (StatisticConstants.PRIORITY.equals(field)) {
      return StringUtils.defaultIfBlank(
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + value), value);
    }
    return value;
  }

  private static String text(String template, Object... params) {
    return MessageFormat.format(template, Arrays.stream(params).map(String::valueOf).toArray());
  }
}
