package com.axonivy.portal.smart.statistic.service;

import static com.axonivy.portal.enums.statistic.ChartType.BAR;
import static com.axonivy.portal.enums.statistic.ChartType.LINE;
import static com.axonivy.portal.enums.statistic.ChartType.NUMBER;
import static com.axonivy.portal.enums.statistic.ChartType.PIE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.BarChartConfig;
import com.axonivy.portal.bo.LineChartConfig;
import com.axonivy.portal.bo.NumberChartConfig;
import com.axonivy.portal.bo.PieChartConfig;
import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.bo.ThresholdStatisticChart;
import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.enums.statistic.AggregationField;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.enums.statistic.ConditionBasedColoringScope;
import com.axonivy.portal.service.StatisticService;

import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;

/**
 * A chart's round trip through the configuration form: what has to be filled in before the form
 * can bind to it, and what has to be taken back out before it is stored.
 *
 * The two halves are one rule read from opposite ends. The form binds every chart config, every
 * name list and the colouring fields whatever the chart currently is, so all of them must exist
 * even when only one is in use - and none of them but the one in use may reach storage. Keeping
 * {@link #ensureChartConfigs} and {@link #dropUnusedChartConfigs} in the same file is what stops
 * a new chart type from being added to one and forgotten in the other.
 *
 * A chart arrives at the opening half from three directions - created blank, loaded from the
 * store, or produced by an agent turn - and each of them used to fill those fields in its own
 * copy of the same block. The opening half deliberately only fills what is missing: an existing
 * chart's own values always win.
 */
public class StatisticLifecycleService {

  /** Shown behind a number chart until a threshold colour takes over. */
  public static final String DEFAULT_BACKGROUND_COLOR = "#8dc261";

  public static final int MIN_REFRESH_INTERVAL_IN_SECONDS = 60;
  public static final int MAX_REFRESH_INTERVAL_IN_SECONDS = 1000000;
  public static final int DEFAULT_REFRESH_INTERVAL_IN_SECONDS = 300;

  /** What a threshold covers when the colouring applies to the chart as a whole. */
  private static final String ALL_VALUES = "All values";

  private StatisticLifecycleService() {}

  // ======================================================================== opening

  /**
   * A brand new chart: a task bar chart grouped by priority, visible to everyone.
   *
   * Deliberately not {@link #ensureFormDefaults(Statistic)} on a blank Statistic - that would add
   * the colouring defaults to the stored JSON of every chart created from here, which is a
   * persistence change rather than a form one.
   */
  public static Statistic newDraft() {
    Statistic chart = new Statistic();
    chart.setStatisticAggregation(new StatisticAggregation());
    chart.getStatisticAggregation().setField(AggregationField.PRIORITY.getName());
    chart.setNames(new ArrayList<>());
    chart.setDescriptions(new ArrayList<>());
    chart.setChartTarget(ChartTarget.TASK);
    chart.setChartType(ChartType.BAR);
    ensureChartConfigs(chart);
    chart.setPermissions(new ArrayList<>(Arrays.asList(ISecurityConstants.TOP_LEVEL_ROLE_NAME)));
    chart.setConditionBasedColoringEnabled(false);
    return chart;
  }

  /**
   * Fills every field the form binds to but the chart may not carry: the name lists, all four
   * chart configs, and the colouring defaults.
   *
   * Shared by the "open an existing chart" and "an agent turn replaced the chart" paths, which
   * need exactly the same set.
   */
  public static void ensureFormDefaults(Statistic chart) {
    ensureNameLists(chart);
    ensureChartConfigs(chart);
    ensureColoringDefaults(chart);
  }

  /**
   * Gives a chart stored without any permission the top level role, so the form's autocomplete
   * has something to show. Only for stored charts: a new draft is built with it already.
   */
  public static void ensureDefaultPermission(Statistic chart) {
    if (CollectionUtils.isEmpty(chart.getPermissions())) {
      chart.setPermissions(new ArrayList<>(Arrays.asList(ISecurityConstants.TOP_LEVEL_ROLE_NAME)));
    }
    if (chart.getPermissionDTOs() == null) {
      chart.setPermissionDTOs(Arrays.asList(SecurityMemberDTOMapper.mapFromRoleDTO(
          new RoleDTO(ISecurityContext.current().roles().find(ISecurityConstants.TOP_LEVEL_ROLE_NAME)))));
    }
  }

  /** Condition-based colouring switched on: start from one colour and no thresholds. */
  public static void resetColoring(Statistic chart) {
    chart.setDefaultBackgroundColor(DEFAULT_BACKGROUND_COLOR);
    chart.setConditionBasedColoringScope(ConditionBasedColoringScope.ALL);
    chart.setThresholdStatisticCharts(new ArrayList<>());
  }

  /** The form binds every chart config, so all four must exist even though only one is used. */
  private static void ensureChartConfigs(Statistic chart) {
    if (chart.getNumberChartConfig() == null) {
      chart.setNumberChartConfig(new NumberChartConfig());
    }
    if (chart.getBarChartConfig() == null) {
      chart.setBarChartConfig(new BarChartConfig());
    }
    if (chart.getLineChartConfig() == null) {
      chart.setLineChartConfig(new LineChartConfig());
    }
    if (chart.getPieChartConfig() == null) {
      chart.setPieChartConfig(new PieChartConfig() {});
    }
  }

  private static void ensureNameLists(Statistic chart) {
    if (chart.getNames() == null) {
      chart.setNames(new ArrayList<>());
    }
    if (chart.getDescriptions() == null) {
      chart.setDescriptions(new ArrayList<>());
    }
  }

  private static void ensureColoringDefaults(Statistic chart) {
    if (chart.getDefaultBackgroundColor() == null) {
      chart.setDefaultBackgroundColor(DEFAULT_BACKGROUND_COLOR);
    }
    if (chart.getThresholdStatisticCharts() == null) {
      chart.setThresholdStatisticCharts(new ArrayList<>());
    }
  }

  // ========================================================================= storing

  /**
   * The reason the chart cannot be saved yet, if there is one.
   *
   * Only the refresh interval is checked here - everything else on the form is either a choice
   * from a fixed list or free text.
   */
  public static Optional<String> refreshIntervalError(Statistic chart, boolean refreshIntervalEnabled) {
    Integer interval = chart.getRefreshInterval();
    boolean invalid = refreshIntervalEnabled && (interval == null || interval < MIN_REFRESH_INTERVAL_IN_SECONDS
        || interval > MAX_REFRESH_INTERVAL_IN_SECONDS);
    if (!invalid) {
      return Optional.empty();
    }
    return Optional.of(Ivy.cms().co(
        "/Dialogs/com/axonivy/portal/page/StatisticConfiguration/RefreshIntervalInSecondsValidationMessage",
        Arrays.asList(MIN_REFRESH_INTERVAL_IN_SECONDS, MAX_REFRESH_INTERVAL_IN_SECONDS)));
  }

  /** Whether the chart still carries the query text that replaced the structured aggregation. */
  public static boolean isOutdated(Statistic chart) {
    return chart != null
        && (StringUtils.isNotBlank(chart.getAggregates()) || StringUtils.isNotBlank(chart.getFilter()));
  }

  /**
   * Everything the chart needs before it is written: drops the configs it does not use, the
   * interval it is not refreshing on, the filters that were never given a field, and the
   * aggregation text a structured aggregation has replaced.
   */
  public static void prepare(Statistic chart, boolean refreshIntervalEnabled) {
    dropUnusedChartConfigs(chart, chart.getChartType());
    cleanUpConfiguration(chart, refreshIntervalEnabled);
    cleanUpFilter(chart);
    cleanUpAggregations(chart);
  }

  /**
   * Colouring the chart as a whole means every threshold covers every value, so they are stored
   * saying so rather than keeping whichever value they were built against.
   */
  public static void applyColoringScope(Statistic chart) {
    if (chart.getConditionBasedColoringEnabled()
        && chart.getConditionBasedColoringScope().equals(ConditionBasedColoringScope.ALL)) {
      for (ThresholdStatisticChart item : chart.getThresholdStatisticCharts()) {
        item.setTargetValue(ALL_VALUES);
      }
    }
  }

  /**
   * Drops filters the user added but never gave a field.
   *
   * Also used by the preview paths, which must not draw a chart through a half-built filter.
   */
  public static void cleanUpFilter(Statistic chart) {
    if (CollectionUtils.isNotEmpty(chart.getFilters())) {
      chart.getFilters().removeIf(filter -> filter.getField() == null);
    } else {
      chart.setFilter(null);
    }
  }

  /**
   * Stores one chart in Portal.CustomStatistic: replaces the entry with the same id, or appends
   * it when the chart is new.
   */
  public static void store(Statistic chart) {
    List<Statistic> statistics = StatisticService.getInstance().getCustomStatistic();
    Statistic oldStatistic = null;
    for (int i = 0; i < statistics.size(); i++) {
      if (chart.getId().equals(statistics.get(i).getId())) {
        oldStatistic = statistics.set(i, chart);
      }
    }
    if (oldStatistic == null) {
      // Versioning now lives on the export wrapper container, not on individual entities.
      statistics.add(chart);
    }
    StatisticService.getInstance().saveJsonToVariable(statistics);
  }

  private static void cleanUpConfiguration(Statistic chart, boolean refreshIntervalEnabled) {
    if (!refreshIntervalEnabled || chart.getRefreshInterval() == null
        || (chart.getRefreshInterval() < MIN_REFRESH_INTERVAL_IN_SECONDS)) {
      chart.setRefreshInterval(null);
    }
    chart.setAdditionalConfigs(null);
  }

  private static void cleanUpAggregations(Statistic chart) {
    if (StringUtils.isNotBlank(chart.getAggregates()) && chart.getStatisticAggregation() != null
        && StringUtils.isNotBlank(chart.getStatisticAggregation().getField())) {
      chart.setAggregates(null);
    }
  }

  /** The mirror of {@link #ensureChartConfigs}: only the config in use is stored. */
  private static void dropUnusedChartConfigs(Statistic chart, ChartType chartType) {
    if (BAR != chartType) {
      chart.setBarChartConfig(null);
    }
    if (LINE != chartType) {
      chart.setLineChartConfig(null);
    }
    if (PIE != chartType) {
      chart.setPieChartConfig(null);
    }
    if (NUMBER != chartType) {
      chart.setNumberChartConfig(null);
    }
  }
}
