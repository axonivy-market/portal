package com.axonivy.portal.migration.taskdetails.converter.v140;

import java.util.Optional;

import org.apache.commons.lang3.Strings;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.TaskDetailsJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.ivy.addon.portalkit.constant.WidgetType;

public class TaskDetailsWidgetConverter implements IJsonConverter {

  private static final String WIDGETS = "widgets";
  private static final String LAYOUT = "layout";
  private static final String TYPE = "type";
  private static final String ID = "id";
  private static final String AXIS_Y = "y";
  private static final String HEIGHT = "h";

  private static final int CELL_HEIGHT_RATIO = 5;
  private static final int SUMMARY_HEIGHT = 12;
  private static final int MIN_INFORMATION_HEIGHT = 10;

  @Override
  public AbstractJsonVersion version() {
    return new TaskDetailsJsonVersion("14.0.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    ArrayNode widgets = widgetsOf(jsonNode).orElse(null);
    if (widgets == null) {
      return;
    }
    widgets.forEach(TaskDetailsWidgetConverter::rescaleLayout);
    splitInformationWidget(widgets);
  }

  private static Optional<ArrayNode> widgetsOf(JsonNode configuration) {
    return Optional.ofNullable(configuration)
        .filter(JsonNode::isObject)
        .map(node -> node.get(WIDGETS))
        .filter(JsonNode::isArray)
        .map(ArrayNode.class::cast);
  }

  private static void rescaleLayout(JsonNode widget) {
    layoutOf(widget).ifPresent(layout -> {
      layout.put(AXIS_Y, layout.path(AXIS_Y).asInt(0) * CELL_HEIGHT_RATIO);
      layout.put(HEIGHT, layout.path(HEIGHT).asInt(0) * CELL_HEIGHT_RATIO);
    });
  }

  private static Optional<ObjectNode> layoutOf(JsonNode widget) {
    return Optional.ofNullable(widget)
        .filter(JsonNode::isObject)
        .map(node -> node.get(LAYOUT))
        .filter(JsonNode::isObject)
        .map(ObjectNode.class::cast);
  }

  private static void splitInformationWidget(ArrayNode widgets) {
    int informationIndex = indexOfType(widgets, WidgetType.INFORMATION);
    if (informationIndex < 0) {
      return;
    }

    ObjectNode informationLayout = layoutOf(widgets.get(informationIndex)).orElse(null);
    if (informationLayout == null) {
      return;
    }

    int totalHeight = informationLayout.path(HEIGHT).asInt(0);
    int informationHeight = Math.max(totalHeight - SUMMARY_HEIGHT, MIN_INFORMATION_HEIGHT);
    int informationAxisY = informationLayout.path(AXIS_Y).asInt(0);

    ObjectNode summary = widgets.objectNode();
    summary.put(TYPE, WidgetType.SUMMARY);
    summary.put(ID, summaryId(widgets));
    ObjectNode summaryLayout = informationLayout.deepCopy();
    summaryLayout.put(AXIS_Y, informationAxisY);
    summaryLayout.put(HEIGHT, SUMMARY_HEIGHT);
    summary.set(LAYOUT, summaryLayout);

    informationLayout.put(AXIS_Y, informationAxisY + SUMMARY_HEIGHT);
    informationLayout.put(HEIGHT, informationHeight);

    widgets.insert(informationIndex, summary);
  }

  private static int indexOfType(ArrayNode widgets, String type) {
    for (int i = 0; i < widgets.size(); i++) {
      if (Strings.CS.equals(widgets.get(i).path(TYPE).asText(), type)) {
        return i;
      }
    }
    return -1;
  }

  private static String summaryId(ArrayNode widgets) {
    String id = WidgetType.SUMMARY;
    for (int suffix = 2; hasId(widgets, id); suffix++) {
      id = WidgetType.SUMMARY + "-" + suffix;
    }
    return id;
  }

  private static boolean hasId(ArrayNode widgets, String id) {
    for (JsonNode widget : widgets) {
      if (Strings.CS.equals(widget.path(ID).asText(), id)) {
        return true;
      }
    }
    return false;
  }
}
