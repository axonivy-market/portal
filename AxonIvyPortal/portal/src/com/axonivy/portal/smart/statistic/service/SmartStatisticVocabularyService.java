package com.axonivy.portal.smart.statistic.service;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.AggregationInterval;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;

import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import jakarta.faces.model.SelectItem;

/**
 * What a chart is allowed to aggregate by, for a given target and chart type.
 *
 * The single source of truth for both consumers: the system prompt renders the agent's half
 * through {@code VocabularyBlock}, {@code SmartChartSpecMapper} enforces the same sets, and the
 * configuration form offers them in its dropdowns. Keeping one implementation is what prevents
 * the prompt from promising something the mapper then rejects - and what stops the form and the
 * agent from disagreeing about the same field.
 *
 * Everything is computed at runtime rather than hard-coded, because the legal sets genuinely
 * vary per installation: {@code isHideCaseCreator()} removes an aggregation field and custom
 * fields come from {@link ICustomFieldMeta}.
 *
 * Reads only. Nothing here touches the chart being configured - which is what makes it safe to
 * call from a getter the form evaluates on every render.
 *
 * Filter vocabulary - which fields may be filtered on and with which operators - lives in
 * {@link StatisticFilterFieldService}.
 */
public class SmartStatisticVocabularyService {

  /** Not numeric custom fields, so they are queried without the customFields.numbers. prefix. */
  public static final Set<String> BUILT_IN_KPI_FIELDS =
      Set.of(StatisticConstants.BUSINESS_RUNTIME, StatisticConstants.WORKING_TIME);

  public static final String COUNTING = "Counting";

  /** A field whose name says it holds a date, and so can be bucketed by interval. */
  public static final String TIMESTAMP = "timestamp";

  private static final String CUSTOM = "custom";

  private SmartStatisticVocabularyService() {}

  // ---------------------------------------------------------------- group by

  /**
   * The group-by fields the configuration form may offer. A number chart aggregates rather than
   * groups, so it gets the *_NUMBER_AGGREGATES set.
   *
   * This is the manual configurator's own dropdown; the agent is allowed slightly more - see
   * {@link #availableGroupByFields(ChartTarget, ChartType)}.
   */
  public static List<AggregationField> availableFormFields(ChartTarget target, ChartType chartType) {
    if (ChartTarget.CASE == target) {
      return ChartType.NUMBER == chartType
          ? AggregationField.CASE_NUMBER_AGGREGATES.stream().toList()
          : visibleCaseAggregates().stream().toList();
    }
    return ChartType.NUMBER == chartType
        ? AggregationField.TASK_NUMBER_AGGREGATES.stream().toList()
        : AggregationField.TASK_AGGREGATES.stream().toList();
  }

  /**
   * The group-by fields the agent may produce. Follows
   * {@link #availableFormFields(ChartTarget, ChartType)}, including the hide-case-creator global
   * setting, with one deliberate widening.
   *
   * The *_NUMBER_AGGREGATES sets describe what the manual configurator offers in its dropdown,
   * not what the query engine supports. Portal's own shipped defaults contradict them: charts
   * 8 ("Tasks that expire by the end of the week") and 11 ("Tasks Due Today") in
   * config/variables/Portal/Statistic.json are number charts grouped by expiryTimestamp with an
   * interval. Rejecting that shape would refuse configurations the product itself ships, so the
   * timestamp fields are allowed for number charts too.
   */
  public static Set<AggregationField> availableGroupByFields(ChartTarget target, ChartType type) {
    if (ChartTarget.CASE == target) {
      if (ChartType.NUMBER == type) {
        return withTimestamps(AggregationField.CASE_NUMBER_AGGREGATES);
      }
      return visibleCaseAggregates();
    }
    if (ChartType.NUMBER == type) {
      return withTimestamps(AggregationField.TASK_NUMBER_AGGREGATES);
    }
    return AggregationField.TASK_AGGREGATES;
  }

  /** The case aggregates less the creator, when the installation hides it. */
  private static Set<AggregationField> visibleCaseAggregates() {
    boolean hidingCaseCreator = GlobalSettingService.getInstance().isHideCaseCreator();
    return AggregationField.CASE_AGGREGATES.stream()
        .filter(field -> !hidingCaseCreator || field != AggregationField.CREATOR_NAME)
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  private static Set<AggregationField> withTimestamps(Set<AggregationField> base) {
    Set<AggregationField> widened = new LinkedHashSet<>(base);
    Arrays.stream(AggregationField.values())
        .filter(field -> AggregationField.TIMESTAMP_AGGREGATES.contains(field.getName()))
        .forEach(widened::add);
    return widened;
  }

  public static Optional<AggregationField> findAggregationField(String name) {
    return Arrays.stream(AggregationField.values())
        .filter(field -> field.getName().equals(name))
        .findFirst();
  }

  public static List<AggregationInterval> intervals() {
    return AggregationInterval.DATE_TIME_INTERVALS.stream().toList();
  }

  public static List<SelectItem> aggregationMethods() {
    return Arrays.asList(new SelectItem("sum", "Sum"), new SelectItem("avg", "Average"),
        new SelectItem("max", "Max"), new SelectItem("min", "Min"));
  }

  // -------------------------------------------------------------- date fields

  /**
   * Whether this is one of the standard timestamp aggregations.
   *
   * Authoritative, and deliberately narrow: it is what the mapper validates the agent against,
   * so it must not accept a name the query engine has no interval support for. Compare
   * {@link #looksLikeTimestampField(String)}, which is the lenient test the form uses - the two
   * differ on purpose and the difference is the reason they are named apart.
   */
  public static boolean isTimestampAggregation(String fieldName) {
    return AggregationField.TIMESTAMP_AGGREGATES.contains(fieldName);
  }

  /**
   * Whether the field's name reads as a date, which is what decides if the form shows its
   * interval control.
   *
   * Lenient on purpose: it has to catch a *custom* timestamp field too, which is named by the
   * installation and so is in no set this code can enumerate. Not a validation test - use
   * {@link #isTimestampAggregation(String)} for that.
   */
  public static boolean looksLikeTimestampField(String fieldName) {
    return fieldName != null && fieldName.toLowerCase().contains(TIMESTAMP);
  }

  // ------------------------------------------------------------ custom fields

  /** Every custom field declared for tasks or for cases, depending on what the chart counts. */
  public static Set<ICustomFieldMeta> customFieldMetas(ChartTarget target) {
    return ChartTarget.TASK == target ? ICustomFieldMeta.tasks() : ICustomFieldMeta.cases();
  }

  public static Optional<ICustomFieldMeta> findCustomField(ChartTarget target, String name) {
    if (StringUtils.isBlank(name)) {
      return Optional.empty();
    }
    return customFieldMetas(target).stream().filter(meta -> name.equals(meta.name())).findFirst();
  }

  /**
   * String and timestamp custom fields can be grouped by; numeric ones cannot.
   *
   * Sorted, and the form's dropdown is filled from exactly this list: {@link ICustomFieldMeta}
   * hands back a Set whose iteration order is not defined, so anything that pairs a list with a
   * position in it - the form seeds its selection with the first entry - has to sort first or it
   * is picking arbitrarily.
   */
  public static List<String> groupableCustomFieldNames(ChartTarget target) {
    return customFieldMetas(target).stream()
        .filter(meta -> meta.type() != CustomFieldType.NUMBER)
        .map(ICustomFieldMeta::name)
        .sorted()
        .toList();
  }

  /** Numeric custom fields are the KPI candidates, and the only ones a chart can measure. */
  public static List<String> numericCustomFieldNames(ChartTarget target) {
    return customFieldMetas(target).stream()
        .filter(meta -> meta.type() == CustomFieldType.NUMBER)
        .map(ICustomFieldMeta::name)
        .sorted()
        .toList();
  }

  /** Whether the chart groups by a custom field rather than a standard one. */
  public static boolean isCustomField(String fieldName) {
    return fieldName != null && fieldName.toLowerCase().contains(CUSTOM);
  }
}
