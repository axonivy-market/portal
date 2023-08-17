package com.axonivy.portal.migration.dashboardtemplate.converter.v112;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.EnumUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonWidgetSearch;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class DashboardTemplateTaskWidgetConverter implements IJsonConverter {
  public static final String DASHBOARD_VERSION = "11.2.0";
  public static final String FILTER_LIST = "filterList";
  public static final String FIELD = "field";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardJsonVersion(DASHBOARD_VERSION);
  }

  @Override
  public void convert(JsonNode jsonNode) {
      new JsonWidgetSearch(jsonNode)
      .type(DashboardWidgetType.TASK.name())
      .findColumns().forEach(columns -> {
        columns.elements().forEachRemaining(col -> {
          if (col.get("field").asText().contentEquals(DashboardStandardTaskColumn.STATE.getField())) {
            convertTaskBusinessStates(col.get("filterList"));
          }
        });
      });
  }

  private void convertTaskBusinessStates(JsonNode statesNode) {
    if (Objects.isNull(statesNode)) {
      return;
    }
    List<TextNode> newStates = new ArrayList<>();
    statesNode.elements().forEachRemaining(node -> {
      TextNode newState = convertTaskBusinessState(node.asText());
      if (newState != null) {
        newStates.add(newState);
      }
    });
    ((ArrayNode) statesNode).removeAll().addAll(newStates.stream().distinct().toList());
  }

  /**
   * Adapt task business state for dashboards
   * IVYPORTAL-14903: Introduce TaskBusinessState
   * 
   */
  private TextNode convertTaskBusinessState(String oldTaskStateString) {
    TaskState oldTaskState = EnumUtils.getEnum(TaskState.class, oldTaskStateString);
    TextNode result = new TextNode(oldTaskStateString);

    if (Objects.nonNull(oldTaskState)) {
      result = switch (oldTaskState) {
        case PARKED, WAITING_FOR_INTERMEDIATE_EVENT, SUSPENDED 
          -> new TextNode(TaskBusinessState.OPEN.name());

        case CREATED, RESUMED 
          -> new TextNode(TaskBusinessState.IN_PROGRESS.name());

        case DONE, READY_FOR_JOIN, JOINING 
          -> new TextNode(TaskBusinessState.DONE.name());

        case DESTROYED 
          -> new TextNode(TaskBusinessState.DESTROYED.name());

        case DELAYED 
          -> new TextNode(TaskBusinessState.DELAYED.name());

        case JOIN_FAILED, FAILED 
          -> new TextNode(TaskBusinessState.ERROR.name());

        default -> null;
      };
    }
    return result;
  }

}
