package com.axonivy.portal.smart.statistic.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.StatisticAggregation;
import com.axonivy.portal.components.service.DateTimeGlobalSettingService;
import com.axonivy.portal.dto.statistic.AggregationDTO;
import com.axonivy.portal.dto.statistic.AggregationResultDTO;
import com.axonivy.portal.dto.statistic.BucketDTO;
import com.axonivy.portal.enums.statistic.AggregationInterval;
import com.axonivy.portal.service.StatisticService;
import com.axonivy.portal.smart.statistic.prompt.PromptFormatter;
import com.axonivy.portal.util.AggregationResultMapper;

import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.statistics.StatisticResponse;
import ch.ivyteam.ivy.environment.Ivy;

public class SmartChartResultService {

  private static final int MAX_BUCKETS = 20;
  private static final DateTimeFormatter DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter MONTH = DateTimeFormatter.ofPattern("yyyy-MM");
  private static final DateTimeFormatter YEAR = DateTimeFormatter.ofPattern("yyyy");

  private static final String CANNOT_READ_RESULT_MESSAGE = "Smart statistic: cannot read the result of chart %s";

  /* What the agent actually reads. Kept as templates rather than appended piecemeal, so the shape
   * of the reading can be seen at a glance - it is prompt text, and it lands in InsightPrompt's
   * {{reading}} slot. The share is a separate fragment because a chart totalling zero has none,
   * the same way InsightPrompt renders its optional scope line. */
  private static final PromptFormatter VALUE_ONLY = PromptFormatter.from("Value: {{value}}");
  private static final PromptFormatter TOTAL_LINE = PromptFormatter.from("Total: {{total}}\n");
  private static final PromptFormatter BUCKET_LINE =
      PromptFormatter.from("- {{label}}: {{measure}}{{share}}\n");
  private static final PromptFormatter SHARE = PromptFormatter.from(" ({{percent}}%)");
  private static final PromptFormatter OVERFLOW_LINE =
      PromptFormatter.from("- {{count}} further groups: {{total}}\n");

  private SmartChartResultService() {}

  public static String previewJson(Statistic chart) {
    StatisticService statisticService = StatisticService.getInstance();
    AggregationResultDTO result = statisticService.resolveChartResult(chart);
    chart.setAdditionalConfigs(statisticService.getAdditionalConfig(chart));
    return BusinessEntityConverter.entityToJsonValue(new StatisticResponse(result, chart));
  }

  public static List<String> categoryValues(Statistic chart, boolean dateTimeSelected) {
    StatisticService statisticService = StatisticService.getInstance();
    AggregationResultDTO chartResult = AggregationResultMapper.toAggResultDTO(statisticService.getChartData(chart));
    statisticService.localizeChartKeys(chartResult, chart.getChartTarget(), chart.getStatisticAggregation());
    chart.setAdditionalConfigs(statisticService.getAdditionalConfig(chart));

    List<BucketDTO> buckets = chartResult.getAggs().get(0).getBuckets();
    if (dateTimeSelected) {
      return buckets.stream().map(bucket -> String.valueOf(((Number) bucket.getKey()).longValue()))
          .collect(Collectors.toList());
    }
    return buckets.stream().map(bucket -> (String) bucket.getKey()).filter(key -> !StringUtils.isBlank(key))
        .collect(Collectors.toList());
  }

  public static String formatCategoryKey(String value, boolean dateTimeSelected) {
    if (!dateTimeSelected) {
      return value;
    }
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern(DateTimeGlobalSettingService.getInstance().getDatePattern());
    Instant instant = new Date(Long.parseLong(value)).toInstant();
    return instant.atZone(ZoneId.systemDefault()).format(formatter);
  }

  public static String readResult(Statistic statistic) {
    AggregationResultDTO result;
    try {
      result = StatisticService.getInstance().resolveChartResult(statistic);
    } catch (Exception e) {
      Ivy.log().warn(String.format(CANNOT_READ_RESULT_MESSAGE, statistic.getId()), e);
      return null;
    }
    if (result == null || CollectionUtils.isEmpty(result.getAggs())) {
      return null;
    }
    String text = render(result.getAggs().get(0), bucketDateFormat(statistic));
    return StringUtils.isBlank(text) ? null : text;
  }

  private static DateTimeFormatter bucketDateFormat(Statistic statistic) {
    StatisticAggregation aggregation = statistic.getStatisticAggregation();
    if (aggregation == null) {
      return null;
    }
    AggregationInterval interval = aggregation.getInterval();
    if (interval == null) {
      return SmartStatisticVocabularyService.looksLikeTimestampField(aggregation.getField())
          ? DAY
          : null;
    }
    return switch (interval) {
      case YEAR -> YEAR;
      case MONTH -> MONTH;
      case WEEK, DAY -> DAY;
    };
  }

  private static String render(AggregationDTO agg, DateTimeFormatter dates) {
    if (CollectionUtils.isEmpty(agg.getBuckets())) {
      return agg.getValue() == null ? null
          : VALUE_ONLY.apply("value", agg.getValue());
    }

    List<BucketDTO> buckets = agg.getBuckets();
    long total = buckets.stream().mapToLong(BucketDTO::getCount).sum();
    StringBuilder out = new StringBuilder();
    out.append(TOTAL_LINE.apply("total", total));

    int shown = Math.min(buckets.size(), MAX_BUCKETS);
    for (BucketDTO bucket : buckets.subList(0, shown)) {
      out.append(BUCKET_LINE.apply(
          "label", label(bucket, dates),
          "measure", measure(bucket),
          "share", share(bucket, total)));
    }
    if (buckets.size() > shown) {
      long rest = buckets.subList(shown, buckets.size()).stream().mapToLong(BucketDTO::getCount).sum();
      out.append(OVERFLOW_LINE.apply(
          "count", buckets.size() - shown,
          "total", rest));
    }
    return out.toString();
  }

  /** Empty when nothing was counted, so a chart totalling zero does not read "(0%)" throughout. */
  private static String share(BucketDTO bucket, long total) {
    if (total <= 0) {
      return StringUtils.EMPTY;
    }
    return SHARE.apply("percent", Math.round(bucket.getCount() * 100.0 / total));
  }

  private static Object measure(BucketDTO bucket) {
    if (CollectionUtils.isNotEmpty(bucket.getAggs())) {
      Object value = bucket.getAggs().get(0).getValue();
      if (value != null) {
        return value;
      }
    }
    return bucket.getCount();
  }

  private static String label(BucketDTO bucket, DateTimeFormatter dates) {
    Object key = bucket.getDisplayKey() == null ? bucket.getKey() : bucket.getDisplayKey();
    if (key == null) {
      return "(none)";
    }
    if (dates != null && key instanceof Number number) {
      return dates.format(Instant.ofEpochMilli(number.longValue()).atZone(ZoneId.systemDefault()));
    }
    return String.valueOf(key);
  }
}
