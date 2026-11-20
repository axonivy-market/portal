package com.axonivy.portal.enums.statistic;

import java.util.Arrays;
import java.util.Optional;
import com.axonivy.portal.smart.statistic.provider.ProviderChartMapper;

/**
 * The chart shapes a provider-backed chart may take, together with the data shape each one needs
 * and the business question it answers.
 *
 * This is the catalogue the agent is taught from, so the guidance here is the whole contract: the
 * prompt is rendered from {@link #catalogue()} and {@code ProviderChartMapper} validates against
 * the same constants. Keeping one definition is what stops the prompt promising a shape the mapper
 * then rejects - the same reason {@code SmartStatisticVocabularyService} exists for task and case
 * charts.
 *
 * Deliberately limited to what {@code statistic.js} can already draw. A fifth entry here without a
 * matching renderer would produce a chart that configures cleanly and then fails to paint.
 */
public enum CustomChartType {

  BAR(ChartType.BAR, 2, 20, false,
      "One bar per group.",
      "Comparing a measure across categories that have no natural order, such as revenue per "
          + "customer or cost per department. The default choice when in doubt."),

  LINE(ChartType.LINE, 2, 60, true,
      "One point per time bucket, in chronological order.",
      "Showing how a measure moves over time, such as invoiced amount per month. Requires a date "
          + "field to group by and a date bucket."),

  PIE(ChartType.PIE, 2, 8, false,
      "One slice per group, each a share of the total.",
      "Showing composition when the parts add up to a meaningful whole and there are only a few "
          + "of them, such as the split of spending across four business units. Never use it for "
          + "values that can be negative, nor for more than eight groups."),

  NUMBER(ChartType.NUMBER, 0, 0, false,
      "A single figure, with no breakdown.",
      "Answering a question whose answer is one number, such as total outstanding receivables. "
          + "Leave the group-by field empty.");

  private final ChartType chartType;
  private final int minBuckets;
  private final int maxBuckets;
  private final boolean timeOrdered;
  private final String dataShape;
  private final String whenToUse;

  private CustomChartType(ChartType chartType, int minBuckets, int maxBuckets, boolean timeOrdered,
      String dataShape, String whenToUse) {
    this.chartType = chartType;
    this.minBuckets = minBuckets;
    this.maxBuckets = maxBuckets;
    this.timeOrdered = timeOrdered;
    this.dataShape = dataShape;
    this.whenToUse = whenToUse;
  }

  public ChartType getChartType() {
    return chartType;
  }

  public int getMinBuckets() {
    return minBuckets;
  }

  public int getMaxBuckets() {
    return maxBuckets;
  }

  /** True when the group-by field must be a date and a bucket is required. */
  public boolean isTimeOrdered() {
    return timeOrdered;
  }

  public boolean isSingleValue() {
    return this == NUMBER;
  }

  public static Optional<CustomChartType> find(String name) {
    return Arrays.stream(values())
        .filter(type -> type.name().equalsIgnoreCase(name))
        .findFirst();
  }

  public static Optional<CustomChartType> fromChartType(ChartType chartType) {
    return Arrays.stream(values())
        .filter(type -> type.chartType == chartType)
        .findFirst();
  }

  public String getDataShape() {
    return dataShape;
  }

  public String getWhenToUse() {
    return whenToUse;
  }
}
