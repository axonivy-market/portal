package com.axonivy.portal.smart.statistic.provider;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.enums.statistic.DynamicFieldType;
import com.axonivy.portal.enums.statistic.DynamicFieldType.JsonKind;
import com.axonivy.portal.service.StatisticService;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicField;
import com.axonivy.portal.smart.statistic.provider.dto.DynamicFieldCatalogue;
import com.axonivy.portal.smart.statistic.provider.dto.FieldCatalogueResult;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec;
import com.axonivy.portal.smart.statistic.service.SmartStatisticAiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCallStartEvent;

public class FieldCatalogueService {

  private static final int SAMPLE_ROWS = 5;
  private static final int MAX_SCANNED_ROWS = 500;
  private static final int MAX_DISTINCT_TRACKED = 200;
  private static final int MAX_SAMPLES = 5;
  private static final int MAX_SAMPLE_LENGTH = 60;
  private static final int UNKNOWN_CARDINALITY = 0;
  private static final int MAX_DEPTH = 2;

  private static final Pattern ISO_DATE = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}([T ].*)?$");

  private static final String NAMING_FIELDS_LOG_TEMPLATE = "Smart statistic: naming the fields of %s";
  private static final String CATALOGUE_FALLBACK_WARNING =
      "I could not name the fields of this data source, so I used their technical names.";

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private FieldCatalogueService() {}

  public static DynamicFieldCatalogue catalogueFor(SubProcessCallStartEvent provider, List<String> warnings) {
    JsonNode data = ProviderRunner.fetchData(provider).orElse(null);
    if (data == null) {
      return null;
    }
    DynamicFieldCatalogue catalogue = toCatalogue(data);
    if (catalogue.isEmpty()) {
      return null;
    }

    if (SmartStatisticAiService.isAvailable()) {
      Ivy.log().info(String.format(NAMING_FIELDS_LOG_TEMPLATE, ProviderRunner.signatureOf(provider)));
      enrich(provider, catalogue, data, warnings);
    }
    return catalogue;
  }

  private static void enrich(SubProcessCallStartEvent provider, DynamicFieldCatalogue catalogue,
      JsonNode data, List<String> warnings) {
    List<String> languages = LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
    FieldCatalogueResult result =
        SmartStatisticAiService.nameFields(provider, catalogue, sampleRows(data), languages);
    if (result == null) {
      warnings.add(CATALOGUE_FALLBACK_WARNING);
      return;
    }
    FieldCatalogueMapper.merge(catalogue, result, languages, warnings);
  }

  private static String sampleRows(JsonNode data) {
    List<JsonNode> rows = ProviderRunner.rowsOf(data);
    if (rows.isEmpty()) {
      return StringUtils.EMPTY;
    }
    try {
      return MAPPER.writerWithDefaultPrettyPrinter()
          .writeValueAsString(rows.subList(0, Math.min(rows.size(), SAMPLE_ROWS)));
    } catch (JsonProcessingException e) {
      return StringUtils.EMPTY;
    }
  }

  public static DynamicFieldCatalogue toCatalogue(JsonNode data) {
    List<JsonNode> rows = ProviderRunner.rowsOf(data);
    DynamicFieldCatalogue catalogue = new DynamicFieldCatalogue();
    catalogue.setRowCount(rows.size());
    if (rows.isEmpty()) {
      return catalogue;
    }

    Map<String, JsonKind> kinds = new LinkedHashMap<>();
    Map<String, Set<String>> distinct = new LinkedHashMap<>();
    int scanned = Math.min(rows.size(), MAX_SCANNED_ROWS);
    for (int i = 0; i < scanned; i++) {
      collect(rows.get(i), StringUtils.EMPTY, 0, kinds, distinct);
    }

    List<String> languages = LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
    List<DynamicField> fields = new ArrayList<>(kinds.size());
    kinds.forEach((path, kind) ->
        fields.add(toField(path, kind, distinct.getOrDefault(path, Set.of()), languages)));

    catalogue.setFields(fields);
    return catalogue;
  }

  private static DynamicField toField(String path, JsonKind kind, Set<String> values,
      List<String> languages) {
    int cardinality = values.size() >= MAX_DISTINCT_TRACKED ? UNKNOWN_CARDINALITY : values.size();
    List<String> samples =
        values.stream().limit(MAX_SAMPLES).collect(Collectors.toCollection(ArrayList::new));

    DynamicField field =
        new DynamicField(path, DynamicFieldType.defaultFor(kind), cardinality, samples);
    field.setTitles(placeholderTitles(toTitle(path), languages));
    return field;
  }

  private static void collect(JsonNode node, String prefix, int depth,
      Map<String, JsonKind> kinds, Map<String, Set<String>> distinct) {
    if (node == null || !node.isObject() || depth > MAX_DEPTH) {
      return;
    }
    for (var property : node.properties()) {
      JsonNode value = property.getValue();
      if (value == null || value.isNull() || value.isArray()) {
        continue;
      }
      String path = prefix.isEmpty() ? property.getKey() : prefix + "." + property.getKey();
      if (value.isObject()) {
        collect(value, path, depth + 1, kinds, distinct);
        continue;
      }
      kinds.merge(path, kindOf(value),
          (existing, found) -> existing == found ? existing : JsonKind.STRING);

      Set<String> values = distinct.computeIfAbsent(path, key -> new LinkedHashSet<>());
      if (values.size() < MAX_DISTINCT_TRACKED) {
        values.add(StringUtils.abbreviate(value.asText(), MAX_SAMPLE_LENGTH));
      }
    }
  }

  private static JsonKind kindOf(JsonNode value) {
    if (value.isNumber()) {
      return JsonKind.NUMBER;
    }
    if (value.isBoolean()) {
      return JsonKind.BOOLEAN;
    }
    return ISO_DATE.matcher(value.asText()).matches() ? JsonKind.DATE : JsonKind.STRING;
  }

  private static List<DisplayName> placeholderTitles(String title, List<String> languages) {
    List<DisplayName> titles = new ArrayList<>();
    languages.stream().forEach(lang -> titles.add(new DisplayName(Locale.forLanguageTag(lang), title)));
    return titles;
  }

  private static String toTitle(String path) {
    if (StringUtils.isBlank(path)) {
      return StringUtils.EMPTY;
    }
    String last = StringUtils.substringAfterLast(path, ".");
    String words = StringUtils.defaultIfBlank(last, path).replaceAll("[_-]+", " ")
        .replaceAll("(?<=[a-z0-9])(?=[A-Z])", " ").trim();
    if (words.isEmpty()) {
      return path;
    }
    return words.substring(0, 1).toUpperCase(Locale.ROOT) + words.substring(1).toLowerCase(Locale.ROOT);
  }

  public static Map<String, DynamicFieldCatalogue> byProviderName(List<SubProcessCallStartEvent> providers) {
    Set<String> deployed = providers.stream()
        .map(provider -> provider.description().name())
        .collect(Collectors.toSet());

    Map<String, DynamicFieldCatalogue> byName = new LinkedHashMap<>();
    for (Statistic chart : StatisticService.getInstance().getCustomStatistic()) {
      if (!chart.isProviderBacked()) {
        continue;
      }
      ProviderChartSpec spec = chart.getCustomChart();
      DynamicFieldCatalogue catalogue = spec.getFieldCatalogue();
      if (catalogue == null || catalogue.isEmpty() || !deployed.contains(spec.getProviderName())) {
        continue;
      }

      byName.merge(spec.getProviderName(), catalogue,
          (kept, found) -> found.getFields().size() > kept.getFields().size() ? found : kept);
    }
    return byName;
  }
}
