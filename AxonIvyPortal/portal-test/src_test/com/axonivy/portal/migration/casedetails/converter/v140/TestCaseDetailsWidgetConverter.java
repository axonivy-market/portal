package com.axonivy.portal.migration.casedetails.converter.v140;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.jupiter.api.Test;

/**
 * Regression coverage for a real corruption bug: neither {@code rescaleLayout()} nor
 * {@code splitInformationWidget()} has any natural signal that it has already run, so calling
 * {@code convert()} a second time on already-converted data used to multiply every widget's
 * layout by 5 again and insert another "summary" widget - unbounded, on every call. This matters
 * because {@link JsonCaseDetailsMigrator} no longer relies on per-item version to avoid re-running
 * converters: once a collection is wrapped, per-item version is stripped for good, so any caller
 * that invokes this converter more than once on the same data must not corrupt it.
 */
class TestCaseDetailsWidgetConverter {

  private final ObjectMapper mapper = new ObjectMapper();
  private final CaseDetailsWidgetConverter converter = new CaseDetailsWidgetConverter();

  private ObjectNode widget(ArrayNode widgets, String type, String id, int x, int y, int w, int h) {
    ObjectNode widget = widgets.addObject();
    widget.put("type", type);
    widget.put("id", id);
    ObjectNode layout = widget.putObject("layout");
    layout.put("x", x);
    layout.put("y", y);
    layout.put("w", w);
    layout.put("h", h);
    return widget;
  }

  @Test
  void convert_calledTwice_isIdempotent() {
    ObjectNode configuration = mapper.createObjectNode();
    ArrayNode widgets = configuration.putArray("widgets");
    widget(widgets, "information", "information", 0, 0, 6, 10);
    widget(widgets, "document", "document", 6, 0, 6, 10);

    converter.convert(configuration);
    String afterFirstRun = configuration.toString();
    converter.convert(configuration);
    String afterSecondRun = configuration.toString();

    assertThat(afterSecondRun).isEqualTo(afterFirstRun);
  }

  @Test
  void convert_calledTwice_doesNotDuplicateSummaryWidgetOrRescaleAgain() {
    ObjectNode configuration = mapper.createObjectNode();
    ArrayNode widgets = configuration.putArray("widgets");
    widget(widgets, "information", "information", 0, 0, 6, 10);

    converter.convert(configuration);
    converter.convert(configuration);

    long summaryWidgetCount = 0;
    for (JsonNode widget : widgets) {
      if ("summary".equals(widget.path("type").asText())) {
        summaryWidgetCount++;
      }
    }
    assertThat(summaryWidgetCount).isEqualTo(1);
  }

  @Test
  void convert_legacyConfiguration_rescalesAndSplitsInformationWidget() {
    ObjectNode configuration = mapper.createObjectNode();
    ArrayNode widgets = configuration.putArray("widgets");
    widget(widgets, "information", "information", 0, 2, 6, 10);

    converter.convert(configuration);

    JsonNode summary = widgets.get(0);
    JsonNode information = widgets.get(1);
    assertThat(summary.get("type").asText()).isEqualTo("summary");
    assertThat(information.get("layout").get("y").asInt()).isEqualTo(2 * 5 + 12);
  }
}
