package com.axonivy.portal.smart.statistic.provider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Aggregation;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.DateBucket;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Filter;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.SortOrder;
import com.axonivy.portal.dto.statistic.AggregationDTO;
import com.axonivy.portal.dto.statistic.AggregationResultDTO;
import com.axonivy.portal.dto.statistic.BucketDTO;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Turns a provider's rows into the bucket shape {@code statistic.js} already draws.
 *
 * Pure and deterministic: same rows plus same spec always give the same chart. That is the point of
 * storing the spec - the agent shapes a chart once, and every refresh afterwards runs through here
 * with no model involved.
 *
 * The output mirrors what Elasticsearch produces for task and case charts, because the renderer
 * reads them the same way: a counted chart puts its figure in {@code count}, while a measured one
 * puts it in a nested agg and leaves {@code count} as the number of records behind it.
 */
public class ProviderChartProjector {

  /** Buckets past this are noise on a card-sized chart, and a long tail of them hurts the payload. */
  public static final int MAX_BUCKETS = 50;

  private static final String EMPTY_LABEL = "-";
  private static final DateTimeFormatter MONTH = DateTimeFormatter.ofPattern("yyyy-MM");
  private static final DateTimeFormatter DAY = DateTimeFormatter.ISO_LOCAL_DATE;

  private ProviderChartProjector() {}

  private static class Accumulator {
    private String displayKey;
    private long count;
    private double total;
    private double min = Double.MAX_VALUE;
    private double max = -Double.MAX_VALUE;
    private long measured;
  }

  public static AggregationResultDTO project(JsonNode data, ProviderChartSpec spec) {
    List<JsonNode> rows = ProviderRunner.rowsOf(data);
    Map<String, Accumulator> groups = new LinkedHashMap<>();

    for (JsonNode row : rows) {
      if (!matches(row, spec.getFilters())) {
        continue;
      }
      String key = groupKey(row, spec);
      Accumulator acc = groups.computeIfAbsent(key, ignored -> new Accumulator());
      acc.displayKey = displayKey(key, spec);
      acc.count++;
      accumulate(acc, row, spec);
    }

    List<BucketDTO> buckets = toBuckets(groups, spec);
    sort(buckets, spec);
    int limit = Math.min(spec.getLimit() > 0 ? spec.getLimit() : MAX_BUCKETS, MAX_BUCKETS);
    if (buckets.size() > limit) {
      buckets = new ArrayList<>(buckets.subList(0, limit));
    }

    AggregationDTO aggregation = new AggregationDTO();
    aggregation.setName(StringUtils.defaultIfBlank(spec.getLabelPath(), "total"));
    aggregation.setBuckets(buckets);
    return new AggregationResultDTO(List.of(aggregation));
  }

  // ------------------------------------------------------------------ groups

  /** A NUMBER chart has no breakdown, so every row lands in one bucket. */
  private static String groupKey(JsonNode row, ProviderChartSpec spec) {
    if (StringUtils.isBlank(spec.getLabelPath())) {
      return StringUtils.EMPTY;
    }
    JsonNode value = at(row, spec.getLabelPath());
    if (value == null || value.isNull() || StringUtils.isBlank(value.asText())) {
      return EMPTY_LABEL;
    }
    return spec.getDateBucket() == null ? value.asText() : bucketDate(value.asText(), spec.getDateBucket());
  }

  private static String displayKey(String key, ProviderChartSpec spec) {
    return StringUtils.isBlank(spec.getLabelPath()) ? StringUtils.EMPTY : key;
  }

  /**
   * Buckets to a sortable label. The label doubles as the sort key, so the formats are chosen to
   * order correctly as plain text - that is what keeps a line chart chronological without carrying
   * a separate timestamp through the renderer.
   */
  private static String bucketDate(String raw, DateBucket bucket) {
    LocalDate date = parseDate(raw);
    if (date == null) {
      return raw;
    }
    return switch (bucket) {
      case DAY -> date.format(DAY);
      case WEEK -> date.get(IsoFields.WEEK_BASED_YEAR) + "-W"
          + StringUtils.leftPad(String.valueOf(date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)), 2, '0');
      case MONTH -> date.format(MONTH);
      case YEAR -> String.valueOf(date.getYear());
    };
  }

  private static LocalDate parseDate(String raw) {
    try {
      // Providers emit either a plain date or a full timestamp; the date part is all we bucket on.
      return LocalDate.parse(StringUtils.substring(raw, 0, 10));
    } catch (Exception e) {
      return null;
    }
  }

  // ------------------------------------------------------------- aggregation

  private static void accumulate(Accumulator acc, JsonNode row, ProviderChartSpec spec) {
    if (Aggregation.COUNT == spec.getAggregation() || StringUtils.isBlank(spec.getValuePath())) {
      return;
    }
    JsonNode value = at(row, spec.getValuePath());
    if (value == null || !value.isNumber() && !NumberUtils.isCreatable(value.asText())) {
      return;
    }
    double number = value.isNumber() ? value.asDouble() : NumberUtils.toDouble(value.asText());
    acc.total += number;
    acc.min = Math.min(acc.min, number);
    acc.max = Math.max(acc.max, number);
    acc.measured++;
  }

  private static List<BucketDTO> toBuckets(Map<String, Accumulator> groups, ProviderChartSpec spec) {
    boolean counted = Aggregation.COUNT == spec.getAggregation() || StringUtils.isBlank(spec.getValuePath());
    List<BucketDTO> buckets = new ArrayList<>();
    groups.forEach((key, acc) -> {
      BucketDTO bucket = new BucketDTO();
      bucket.setKey(key);
      bucket.setDisplayKey(acc.displayKey);
      bucket.setCount(acc.count);
      if (!counted) {
        AggregationDTO measure = new AggregationDTO();
        measure.setName(spec.getAggregation().name().toLowerCase() + "-" + spec.getValuePath());
        measure.setValue(measure(acc, spec.getAggregation()));
        bucket.setAggs(List.of(measure));
      } else {
        bucket.setAggs(List.of());
      }
      buckets.add(bucket);
    });
    return buckets;
  }

  private static double measure(Accumulator acc, Aggregation aggregation) {
    if (acc.measured == 0) {
      return 0d;
    }
    return switch (aggregation) {
      case SUM -> acc.total;
      case AVG -> acc.total / acc.measured;
      case MIN -> acc.min;
      case MAX -> acc.max;
      case COUNT -> acc.count;
    };
  }

  private static double valueOf(BucketDTO bucket) {
    if (bucket.getAggs() != null && !bucket.getAggs().isEmpty()) {
      Object value = bucket.getAggs().get(0).getValue();
      return value instanceof Number number ? number.doubleValue() : 0d;
    }
    return bucket.getCount();
  }

  private static void sort(List<BucketDTO> buckets, ProviderChartSpec spec) {
    SortOrder order = spec.getSort() == null ? SortOrder.VALUE_DESC : spec.getSort();
    Comparator<BucketDTO> comparator = switch (order) {
      case VALUE_DESC -> Comparator.comparingDouble(ProviderChartProjector::valueOf).reversed();
      case VALUE_ASC -> Comparator.comparingDouble(ProviderChartProjector::valueOf);
      case LABEL_ASC -> Comparator.comparing(bucket -> String.valueOf(bucket.getKey()));
      case LABEL_DESC -> Comparator.comparing((BucketDTO bucket) -> String.valueOf(bucket.getKey())).reversed();
    };
    buckets.sort(comparator);
  }

  // ----------------------------------------------------------------- filters

  private static boolean matches(JsonNode row, List<Filter> filters) {
    if (filters == null || filters.isEmpty()) {
      return true;
    }
    return filters.stream().allMatch(filter -> matches(row, filter));
  }

  private static boolean matches(JsonNode row, Filter filter) {
    if (filter == null || StringUtils.isBlank(filter.getPath()) || filter.getOperator() == null) {
      return true;
    }
    JsonNode node = at(row, filter.getPath());
    String actual = node == null || node.isNull() ? StringUtils.EMPTY : node.asText();
    String expected = StringUtils.defaultString(filter.getValue());

    return switch (filter.getOperator()) {
      case EQUALS -> actual.equalsIgnoreCase(expected);
      case NOT_EQUALS -> !actual.equalsIgnoreCase(expected);
      case CONTAINS -> StringUtils.containsIgnoreCase(actual, expected);
      case IS_EMPTY -> StringUtils.isBlank(actual);
      case NOT_EMPTY -> StringUtils.isNotBlank(actual);
      case GREATER -> compare(actual, expected) > 0;
      case GREATER_OR_EQUAL -> compare(actual, expected) >= 0;
      case LESS -> compare(actual, expected) < 0;
      case LESS_OR_EQUAL -> compare(actual, expected) <= 0;
    };
  }

  /** Numbers compare numerically, everything else - dates included - compares as text. */
  private static int compare(String actual, String expected) {
    if (NumberUtils.isCreatable(actual) && NumberUtils.isCreatable(expected)) {
      return Double.compare(NumberUtils.toDouble(actual), NumberUtils.toDouble(expected));
    }
    return actual.compareToIgnoreCase(expected);
  }

  // -------------------------------------------------------------------- path

  /** Resolves a dotted path, the same notation {@code FieldCatalogueService} derives. */
  private static JsonNode at(JsonNode row, String path) {
    JsonNode current = row;
    for (String part : StringUtils.split(path, '.')) {
      if (current == null) {
        return null;
      }
      current = current.get(part);
    }
    return current;
  }
}
