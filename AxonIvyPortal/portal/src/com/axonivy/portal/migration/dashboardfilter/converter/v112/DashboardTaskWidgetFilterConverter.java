package com.axonivy.portal.migration.dashboardfilter.converter.v112;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.EnumUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardFilterJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JCondition;
import com.axonivy.portal.migration.common.search.JsonDashboardConfigurationSearch;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.StringNode;

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

public class DashboardTaskWidgetFilterConverter implements IJsonConverter{
  public static final String DASHBOARD_VERSION = "11.2.0";
  public static final String USER_FILTER_LIST = "userFilterList";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardFilterJsonVersion(DASHBOARD_VERSION);
  }

  @Override
  public void convert(JsonNode jsonNode) {
    new JsonDashboardConfigurationSearch(jsonNode)
      .type(DashboardWidgetType.TASK.name())
      .findFilterableColumns()
        .ifPresent(columns -> columns.values().forEach(column -> {
        if(JCondition.isField(DashboardStandardTaskColumn.STATE.getField()).test(column)) {
          convertToTaskBusinessState(column.get(USER_FILTER_LIST));
        }
      }));
  }

  private void convertToTaskBusinessState(JsonNode statesNode) {
    if (Objects.isNull(statesNode)) {
      return;
    }
    List<StringNode> newStates = new ArrayList<>();
    
    statesNode.values().forEach(node -> {
      StringNode newState = convertTaskBusinessState(node.asString());
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
  private StringNode convertTaskBusinessState(String oldTaskStateString) {
    TaskState oldTaskState = EnumUtils.getEnum(TaskState.class, oldTaskStateString);
    StringNode result = new StringNode(oldTaskStateString);
        
    if (Objects.nonNull(oldTaskState)) {
      result = switch (oldTaskState) {
        case PARKED, WAITING_FOR_INTERMEDIATE_EVENT, SUSPENDED 
            -> new StringNode(TaskBusinessState.OPEN.name());

        case CREATED, RESUMED 
            -> new StringNode(TaskBusinessState.IN_PROGRESS.name());

        case DONE, READY_FOR_JOIN, JOINING 
            -> new StringNode(TaskBusinessState.DONE.name());

        case DESTROYED 
            -> new StringNode(TaskBusinessState.DESTROYED.name());

        case DELAYED 
            -> new StringNode(TaskBusinessState.DELAYED.name());

        case JOIN_FAILED, FAILED 
            -> new StringNode(TaskBusinessState.ERROR.name());

        default -> null;
      } ;
    }
    return result;
  }
}
