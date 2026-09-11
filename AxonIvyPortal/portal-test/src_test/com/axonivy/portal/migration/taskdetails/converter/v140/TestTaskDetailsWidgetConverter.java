package com.axonivy.portal.migration.taskdetails.converter.v140;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.jupiter.api.Test;

/**
 * Mirrors {@code TestCaseDetailsWidgetConverter} - see there for why this coverage exists.
 * {@link TaskDetailsWidgetConverter} is a byte-identical copy of
 * {@link com.axonivy.portal.migration.casedetails.converter.v140.CaseDetailsWidgetConverter} for the
 * task-details schema, and had the same unguarded, non-idempotent rescale/split logic.
 */
class TestTaskDetailsWidgetConverter {

  private final ObjectMapper mapper = new ObjectMapper();
  private final TaskDetailsWidgetConverter converter = new TaskDetailsWidgetConverter();

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
