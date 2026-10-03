package com.axonivy.portal.smart.statistic.prompt;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.axonivy.portal.enums.statistic.CustomChartType;
import com.axonivy.portal.enums.statistic.DynamicFieldType;
import com.axonivy.portal.enums.statistic.DynamicFieldType.JsonKind;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicField;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicFieldCatalogue;

/**
 * Renders the things a prompt talks about - a provider's fields, the chart types on offer.
 *
 * Two blocks rather than one: the field-naming call is given the fields without any chart
 * vocabulary, which is what keeps it answering with titles instead of a chart.
 */
public final class DataDescriber {

  // The block ends on {{fields}} because each line brings its own newline: joining with one would
  // leave a stray blank line behind an empty catalogue.
  private static final PromptFormatter FIELDS_BLOCK = PromptFormatter.from("""
      {{rowCount}} records, with these fields:
      {{fields}}""");
  private static final PromptFormatter FIELD_LINE = PromptFormatter.from(
      "{{indent}}- `{{path}}` \"{{title}}\" ({{type}}){{details}}; measures: {{measures}}");
  private static final PromptFormatter DISTINCT = PromptFormatter.from(", {{count}} distinct values");
  private static final PromptFormatter SAMPLES = PromptFormatter.from(", e.g. {{values}}");
  // No slots to fill, so these stay plain text - they are appended, not formatted.
  private static final String GROUP_BY = " - can group by this";
  private static final String DATE_BUCKET = "; grouping requires a date bucket";

  private static final PromptFormatter CHART_TYPES_BLOCK = PromptFormatter.from("""
      ### Chart types

      {{chartTypes}}""");
  private static final PromptFormatter CHART_TYPE_LINE =
      PromptFormatter.from("- `{{name}}` - {{dataShape}} Use when: {{whenToUse}}{{buckets}}");
  private static final PromptFormatter BUCKET_RANGE =
      PromptFormatter.from(" Works with {{min}} to {{max}} groups.");

  private static final PromptFormatter FIELD_TYPE_LINE = PromptFormatter.from("- `{{name}}` - {{meaning}}");
  private static final PromptFormatter KIND_CONSTRAINT =
      PromptFormatter.from("- A field whose raw kind is {{kind}} can only be {{types}}.");

  private DataDescriber() {}

  /** One line per field, with what may be done to it and what it holds. */
  public static String fields(DynamicFieldCatalogue catalogue) {
    return FIELDS_BLOCK.apply(
        "rowCount", catalogue.getRowCount(),
        "fields", fieldLines(catalogue, "", true));
  }

  /**
   * The same lines, indented to sit under a provider entry and without the distinct-value count
   * or the examples: this form lists every deployed provider at once, and values read out of real
   * business data belong in the calls that are about one dataset.
   */
  public static String fieldLines(DynamicFieldCatalogue catalogue, String indent) {
    return fieldLines(catalogue, indent, false);
  }

  private static String fieldLines(DynamicFieldCatalogue catalogue, String indent,
      boolean withValueDetail) {
    return catalogue.getFields().stream()
        .map(field -> describeField(field, indent, withValueDetail) + "\n")
        .collect(Collectors.joining());
  }

  public static String chartTypes() {
    return CHART_TYPES_BLOCK.apply(
        "chartTypes", Arrays.stream(CustomChartType.values()).map(DataDescriber::describeChartType)
            .map(line -> line + "\n").collect(Collectors.joining()));
  }

  /** The types a field may be classified as, and what each one means. */
  public static String fieldTypes() {
    return Arrays.stream(DynamicFieldType.values())
        .map(type -> FIELD_TYPE_LINE.apply("name", type.name(), "meaning", type.getMeaning()))
        .map(line -> line + "\n").collect(Collectors.joining());
  }

  /**
   * What the raw payload allows, one line per kind that has a choice to make. A kind with a single
   * legal type tells the agent nothing it cannot already see.
   */
  public static String fieldTypeConstraints() {
    return Arrays.stream(JsonKind.values())
        .map(kind -> Map.entry(kind, DynamicFieldType.compatibleWith(kind)))
        .filter(entry -> entry.getValue().size() > 1)
        .map(entry -> KIND_CONSTRAINT.apply(
            "kind", entry.getKey().name(),
            "types", orList(entry.getValue().stream().map(DynamicFieldType::name).toList())))
        .map(line -> line + "\n").collect(Collectors.joining());
  }

  /** "A, B or C" - the last separator is a word, because the agent reads this as a sentence. */
  private static String orList(List<String> names) {
    if (names.size() < 2) {
      return String.join("", names);
    }
    return String.join(", ", names.subList(0, names.size() - 1)) + " or " + names.get(names.size() - 1);
  }

  private static String describeField(DynamicField field, String indent, boolean withValueDetail) {
    return FIELD_LINE.apply(
        "indent", indent,
        "path", field.getFieldName(),
        "title", field.getTitle(),
        "type", field.getType(),
        "details", describeFieldDetails(field, withValueDetail),
        "measures", field.getMeasures().stream().map(Enum::name).collect(Collectors.joining(", ")));
  }

  private static String describeFieldDetails(DynamicField field, boolean withValueDetail) {
    StringBuilder details = new StringBuilder();
    if (withValueDetail && field.getCardinality() > 0) {
      details.append(DISTINCT.apply("count", field.getCardinality()));
    }
    if (withValueDetail && !field.getSamples().isEmpty()) {
      details.append(SAMPLES.apply("values", String.join(", ", field.getSamples())));
    }
    if (field.isGroupable()) {
      details.append(GROUP_BY);
    }
    if (field.isNeedsDateBucket()) {
      details.append(DATE_BUCKET);
    }
    return details.toString();
  }

  private static String describeChartType(CustomChartType type) {
    return CHART_TYPE_LINE.apply(
        "name", type.name(),
        "dataShape", type.getDataShape(),
        "whenToUse", type.getWhenToUse(),
        "buckets", type.isSingleValue() ? ""
            : BUCKET_RANGE.apply("min", type.getMinBuckets(), "max", type.getMaxBuckets()));
  }
}
