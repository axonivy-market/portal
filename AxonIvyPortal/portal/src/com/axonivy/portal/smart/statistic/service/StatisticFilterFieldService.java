package com.axonivy.portal.smart.statistic.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.service.filter.operatorpolicy.service.GlobalOperatorPolicyService;
import com.axonivy.portal.util.filter.field.CustomFilterField;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.field.CaseFilterFieldFactory;
import com.axonivy.portal.util.statisticfilter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

/**
 * Which filters a chart may be given, with which operators, and how a stored filter finds its
 * field again.
 *
 * The single source of truth for both consumers, the same way
 * {@link SmartStatisticVocabularyService} is for aggregations: the form offers
 * {@link #catalogueFor(Statistic)} in its dropdown, the system prompt renders
 * {@link #filterFields(ChartTarget)} and {@link #statisticOperatorsFor(FilterField)}, and
 * {@code SmartChartSpecMapper} validates the agent against the very same sets.
 *
 * Everything picks its factory off the chart target, so all of it is empty for a
 * provider-backed chart - those carry their filters in the custom spec instead.
 */
public class StatisticFilterFieldService {

  private StatisticFilterFieldService() {}

  // ------------------------------------------------------------- catalogues

  /** Every filter the form can offer for this chart, default field first. */
  public static List<FilterField> catalogueFor(Statistic chart) {
    List<FilterField> filterFields = new ArrayList<>();
    if (chart.isProviderBacked()) {
      return filterFields;
    }
    if (ChartTarget.TASK == chart.getChartTarget()) {
      filterFields.add(TaskFilterFieldFactory.getDefaultFilterField());
      filterFields.addAll(TaskFilterFieldFactory.getStandardFilterableFields());
      filterFields.addAll(TaskFilterFieldFactory.getCustomFilterableFields());
    } else {
      filterFields.add(CaseFilterFieldFactory.getDefaultFilterField());
      filterFields.addAll(CaseFilterFieldFactory.getStandardFilterableFields());
      filterFields.addAll(CaseFilterFieldFactory.getCustomFilterableFields());
    }
    return filterFields;
  }

  /**
   * The same catalogue as {@link #catalogueFor(Statistic)} without the default field, sorted by
   * name: this one is read into the system prompt and matched against by the mapper, where the
   * default field is not something the agent may name and a stable order keeps the prompt from
   * churning between renders.
   */
  public static List<FilterField> filterFields(ChartTarget target) {
    List<FilterField> fields = new ArrayList<>();
    if (ChartTarget.TASK == target) {
      fields.addAll(TaskFilterFieldFactory.getStandardFilterableFields());
      fields.addAll(TaskFilterFieldFactory.getCustomFilterableFields());
    } else {
      fields.addAll(CaseFilterFieldFactory.getStandardFilterableFields());
      fields.addAll(CaseFilterFieldFactory.getCustomFilterableFields());
    }
    fields.sort(Comparator.comparing(FilterField::getName,
        Comparator.nullsLast(Comparator.naturalOrder())));
    return fields;
  }

  // ---------------------------------------------------------------- lookups

  public static FilterField findBy(ChartTarget target, String field) {
    return ChartTarget.TASK == target ? TaskFilterFieldFactory.findBy(field)
        : CaseFilterFieldFactory.findBy(field);
  }

  /** The stored filter's own column type decides which of the same-named fields matches. */
  public static FilterField findBy(ChartTarget target, String field, DashboardColumnType filterType) {
    return ChartTarget.TASK == target ? TaskFilterFieldFactory.findBy(field, filterType)
        : CaseFilterFieldFactory.findBy(field, filterType);
  }

  /**
   * The filter field's display name, or the raw field name when nothing matches.
   *
   * It has to come from the factory rather than from {@code filter.getFilterField()}: that
   * reference is {@code @JsonIgnore}, so it is null on every chart loaded from storage.
   */
  public static String labelOf(ChartTarget target, String field) {
    FilterField filterField = findBy(target, field);
    if (filterField == null) {
      return field;
    }
    return StringUtils.defaultIfBlank(filterField.getLabel(), field);
  }

  // ---------------------------------------------------------------- binding

  /**
   * Hands each stored filter to the field that renders it, so the form opens showing the values
   * the chart was saved with. A filter naming a field this chart no longer offers is skipped.
   */
  public static void bindExisting(Statistic chart, List<FilterField> catalogue) {
    if (CollectionUtils.isEmpty(chart.getFilters())) {
      return;
    }
    for (DashboardFilter filter : chart.getFilters()) {
      if (!isAvailable(filter, catalogue)) {
        continue;
      }
      FilterField filterField = findBy(chart.getChartTarget(),
          Optional.ofNullable(filter).map(DashboardFilter::getField).orElse(StringUtils.EMPTY),
          Optional.ofNullable(filter).map(DashboardFilter::getFilterType).orElse(null));
      if (filterField != null) {
        filterField.initFilter(filter);
      }
    }
  }

  public static boolean isAvailable(DashboardFilter filter, List<FilterField> catalogue) {
    return Optional.ofNullable(filter).map(DashboardFilter::getField).isPresent()
        && catalogue.stream().anyMatch(field -> filter.getField().equals(field.getName()));
  }

  // -------------------------------------------------------------- operators

  /**
   * The operators the manual configurator offers for this field, after the global operator
   * policy has been applied. The mapping mirrors the per-field widget beans in
   * {@code com.axonivy.portal.bean.dashboard} - see WidgetStateFilterBean, WidgetDateFilterBean
   * and friends, which are the authority on which STATISTIC_* set belongs to which field.
   */
  public static List<FilterOperator> statisticOperatorsFor(FilterField field) {
    return statisticOperatorsFor(field, new GlobalOperatorPolicyService());
  }

  /**
   * Overload for loops: the {@link GlobalOperatorPolicyService} constructor loads and parses
   * the policy, so it is worth building once and reusing across many fields.
   */
  public static List<FilterOperator> statisticOperatorsFor(FilterField field,
      GlobalOperatorPolicyService policy) {
    return policy.keepGloballyEnabledOperators(baseOperatorsFor(field).stream().toList());
  }

  private static Set<FilterOperator> baseOperatorsFor(FilterField field) {
    if (field instanceof CustomFilterField custom && custom.getFormat() != null) {
      return switch (custom.getFormat()) {
        case NUMBER -> FilterOperator.STATISTIC_NUMBER_OPERATORS;
        case DATE -> FilterOperator.STATISTIC_DATE_OPERATORS;
        default -> FilterOperator.STATISTIC_TEXT_OPERATORS;
      };
    }
    String name = field.getName();
    if (StatisticConstants.CAN_WORK_ON.equals(name)) {
      return FilterOperator.STATISTIC_CAN_WORK_ON_OPERATORS;
    }
    // Task responsible is "activator" as a filter, "responsibles.name" as an aggregation.
    if (DashboardStandardTaskColumn.RESPONSIBLE.getField().equals(name)) {
      return FilterOperator.STATISTIC_RESPONSIBLE_OPERATORS;
    }
    // Case creator is "creator" as a filter, "creator.name" as an aggregation.
    if (StatisticConstants.CREATOR.equals(name)) {
      return FilterOperator.STATISTIC_CREATOR_OPERATORS;
    }
    if (SmartStatisticVocabularyService.isTimestampAggregation(name)) {
      return FilterOperator.STATISTIC_DATE_OPERATORS;
    }
    return FilterOperator.STATISTIC_TEXT_OPERATORS;
  }
}
