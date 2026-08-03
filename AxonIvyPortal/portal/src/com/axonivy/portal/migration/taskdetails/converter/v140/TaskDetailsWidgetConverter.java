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

/**
 * Brings a Task Details configuration written before 14.0.0 up to the redesigned layout. Its
 * widgets are {@code summary}, {@code information}, {@code document}, {@code history} and
 * {@code custom} — the Case Details widgets {@code relatedTask}, {@code technicalCase} and
 * {@code businessDetails} never appear here. Two things changed:
 *
 * <ol>
 * <li><b>Grid unit.</b> GridStack {@code cellHeight} went from 100px to 20px in
 * {@code task-details.js} while {@code column} stayed at 12 — {@code gridstack.min.css} bakes in
 * the 1/12 geometry, so it could not be raised. Therefore {@code y} and {@code h} are multiplied by
 * {@value #CELL_HEIGHT_RATIO} and {@code x}/{@code w} are left alone:
 * {@code y_new * 20px == y_old * 100px}, i.e. every card renders on exactly the same pixels as
 * before.</li>
 * <li><b>Widget split.</b> The old {@code information} widget rendered the identity strip
 * (priority / state / due / workflow) and the field list in one card; those are now two widgets,
 * {@code summary} and {@code information}. The summary takes the top {@value #SUMMARY_HEIGHT} rows
 * of the area the information widget used to occupy and the information keeps the rest, so the
 * total footprint is unchanged and no other widget has to move.</li>
 * </ol>
 */
public class TaskDetailsWidgetConverter implements IJsonConverter {

  private static final String WIDGETS = "widgets";
  private static final String LAYOUT = "layout";
  private static final String TYPE = "type";
  private static final String ID = "id";
  private static final String AXIS_Y = "y";
  private static final String HEIGHT = "h";

  /** Old cellHeight (100px) divided by the new one (20px). */
  private static final int CELL_HEIGHT_RATIO = 5;

  /** Rows the extracted summary widget occupies: 12 * 20px = 240px. */
  private static final int SUMMARY_HEIGHT = 12;

  /**
   * Floor for what is left over for the information widget: 10 * 20px = 200px. Only kicks in for an
   * information widget that was already tiny (old {@code h <= 4}); the configuration then grows
   * taller than it was and GridStack pushes the widgets below it down on load.
   */
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

  /**
   * Carves a summary widget out of the top of the information widget. Configurations where the user
   * deliberately dropped the information widget stay without a summary.
   */
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

  /**
   * The plain {@code summary} id matches the shipped default. Fall back to a suffixed one on the off
   * chance that a hand-written configuration already uses it for something else.
   */
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
