package com.axonivy.portal.smart.statistic.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.axonivy.portal.smart.ai.AgentEnums;

/**
 * A chart configuration in the slim, LLM-friendly shape.
 *
 * This is a full snapshot, never a diff: the agent is given the current spec each turn and is
 * expected to re-emit everything it wants to keep. That is what lets the mapper stay free of
 * merge logic, and it makes each turn independently verifiable.
 *
 * The nested enums exist instead of the Portal ones because {@code ChartType},
 * {@code ChartTarget} and {@code AggregationInterval} all carry {@code @JsonValue}, which would
 * put {@code BAR}/{@code CASE}/{@code DAY} in the generated schema while Jackson expects
 * {@code bar}/{@code case}/{@code day}. Translation happens in {@code SmartChartSpecMapper}.
 */
public class SmartChartSpec implements Serializable {

  private static final long serialVersionUID = 1L;

  public enum ChartTypeValue {
    BAR, LINE, PIE, NUMBER;

    @JsonCreator
    public static ChartTypeValue fromJson(String value) {
      return AgentEnums.parse(ChartTypeValue.class, value);
    }
  }

  public enum ChartTargetValue {
    TASK, CASE;

    @JsonCreator
    public static ChartTargetValue fromJson(String value) {
      return AgentEnums.parse(ChartTargetValue.class, value);
    }
  }

  public enum IntervalValue {
    DAY, WEEK, MONTH, YEAR;

    @JsonCreator
    public static IntervalValue fromJson(String value) {
      return AgentEnums.parse(IntervalValue.class, value);
    }
  }

  public enum AggregationMethodValue {
    SUM, AVG, MAX, MIN;

    @JsonCreator
    public static AggregationMethodValue fromJson(String value) {
      return AgentEnums.parse(AggregationMethodValue.class, value);
    }
  }

  private String name;

  /**
   * The chart explained for someone who did not configure it: what is counted and how it is
   * broken down, which records the filters let through, and the decision it supports. It is the
   * only explanation a grid viewer gets, so the prompt asks for several sentences of plain
   * business prose rather than a label.
   */
  private String description;

  private ChartTypeValue chartType;
  private ChartTargetValue target;

  /**
   * An aggregation field name such as {@code priority} or {@code startTimestamp}. Kept as a
   * String rather than an enum because the legal set is conditional on (target, chartType) -
   * a JSON-schema enum cannot express that, and an over-broad one would give false confidence.
   * The mapper enforces it; the prompt teaches it.
   */
  private String groupByField;

  /** Required when {@code groupByField} is the custom-field placeholder. */
  private String customFieldName;

  /** Required for timestamp group-by fields, forbidden otherwise. */
  private IntervalValue interval;

  /** Blank or "Counting" means a plain bucket count. */
  private String kpiField;
  private AggregationMethodValue kpiMethod;

  /** Number charts only. */
  private Boolean hideLabel;

  /** Bar and line charts only. */
  private String xAxisTitle;
  private String yAxisTitle;

  /** "#rrggbb" entries. Ignored by number charts. */
  private List<String> backgroundColors;

  /** Auto-refresh period in seconds; clamped to the range the manual configurator allows. */
  private Integer refreshInterval;

  private List<SmartChartFilterSpec> filters;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ChartTypeValue getChartType() {
    return chartType;
  }

  public void setChartType(ChartTypeValue chartType) {
    this.chartType = chartType;
  }

  public ChartTargetValue getTarget() {
    return target;
  }

  public void setTarget(ChartTargetValue target) {
    this.target = target;
  }

  public String getGroupByField() {
    return groupByField;
  }

  public void setGroupByField(String groupByField) {
    this.groupByField = groupByField;
  }

  public String getCustomFieldName() {
    return customFieldName;
  }

  public void setCustomFieldName(String customFieldName) {
    this.customFieldName = customFieldName;
  }

  public IntervalValue getInterval() {
    return interval;
  }

  public void setInterval(IntervalValue interval) {
    this.interval = interval;
  }

  public String getKpiField() {
    return kpiField;
  }

  public void setKpiField(String kpiField) {
    this.kpiField = kpiField;
  }

  public AggregationMethodValue getKpiMethod() {
    return kpiMethod;
  }

  public void setKpiMethod(AggregationMethodValue kpiMethod) {
    this.kpiMethod = kpiMethod;
  }

  public Boolean getHideLabel() {
    return hideLabel;
  }

  public void setHideLabel(Boolean hideLabel) {
    this.hideLabel = hideLabel;
  }

  public String getxAxisTitle() {
    return xAxisTitle;
  }

  public void setxAxisTitle(String xAxisTitle) {
    this.xAxisTitle = xAxisTitle;
  }

  public String getyAxisTitle() {
    return yAxisTitle;
  }

  public void setyAxisTitle(String yAxisTitle) {
    this.yAxisTitle = yAxisTitle;
  }

  public List<String> getBackgroundColors() {
    return backgroundColors;
  }

  public void setBackgroundColors(List<String> backgroundColors) {
    this.backgroundColors = backgroundColors;
  }

  public Integer getRefreshInterval() {
    return refreshInterval;
  }

  public void setRefreshInterval(Integer refreshInterval) {
    this.refreshInterval = refreshInterval;
  }

  public List<SmartChartFilterSpec> getFilters() {
    return filters;
  }

  public void setFilters(List<SmartChartFilterSpec> filters) {
    this.filters = filters;
  }
}
