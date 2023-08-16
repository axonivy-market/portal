package com.axonivy.portal.migration.dashboard.converter.v112;

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

public class DashboardTaskWidgetConverter implements IJsonConverter{
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
        columns.elements().forEachRemaining(column -> {
          if (column.get(FIELD).asText().contentEquals(DashboardStandardTaskColumn.STATE.getField())) {
            convertTaskBusinessStates(column.get(FILTER_LIST));
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
      switch (oldTaskState) {
        case PARKED:
        case WAITING_FOR_INTERMEDIATE_EVENT:
        case SUSPENDED: {
          result = new TextNode(TaskBusinessState.OPEN.name());
          break;
        }

        case CREATED:
        case RESUMED: {
          result = new TextNode(TaskBusinessState.IN_PROGRESS.name());
          break;
        }

        case DONE:
        case READY_FOR_JOIN:
        case JOINING: {
          result = new TextNode(TaskBusinessState.DONE.name());
          break;
        }

        case DESTROYED: {
          result = new TextNode(TaskBusinessState.DESTROYED.name());
          break;
        }

        case DELAYED: {
          result = new TextNode(TaskBusinessState.DELAYED.name());
          break;
        }

        case JOIN_FAILED:
        case FAILED: {
          result = new TextNode(TaskBusinessState.ERROR.name());
          break;
        }

        default: result = null;
      };
    }
    return result;
  }

}
