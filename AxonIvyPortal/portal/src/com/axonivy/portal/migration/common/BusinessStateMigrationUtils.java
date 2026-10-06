package com.axonivy.portal.migration.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.apache.commons.lang3.EnumUtils;

import com.axonivy.portal.migration.common.search.JsonWidgetSearch;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.StringNode;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.task.TaskBusinessState;

/**
 * TaskState/CaseState -&gt; TaskBusinessState/CaseBusinessState value conversion, shared by the
 * plain-dashboard and dashboard-template converter chains. Idempotent: an already-current value has no
 * matching legacy enum constant, so it passes through unchanged - safe to re-apply.
 */
public final class BusinessStateMigrationUtils {

  private static final String FILTERS = "filters";
  private static final String FILTER_LIST = "filterList";
  private static final String FIELD = "field";
  private static final String VALUES = "values";

  private BusinessStateMigrationUtils() {
  }

  public static StringNode convertTaskBusinessState(String oldTaskStateString) {
    TaskState oldTaskState = EnumUtils.getEnum(TaskState.class, oldTaskStateString);
    StringNode result = new StringNode(oldTaskStateString);

    if (Objects.nonNull(oldTaskState)) {
      result = switch (oldTaskState) {
        case PARKED, WAITING_FOR_INTERMEDIATE_EVENT, SUSPENDED -> new StringNode(TaskBusinessState.OPEN.name());
        case CREATED, RESUMED -> new StringNode(TaskBusinessState.IN_PROGRESS.name());
        case DONE, READY_FOR_JOIN, JOINING -> new StringNode(TaskBusinessState.DONE.name());
        case DESTROYED -> new StringNode(TaskBusinessState.DESTROYED.name());
        case DELAYED -> new StringNode(TaskBusinessState.DELAYED.name());
        case JOIN_FAILED, FAILED -> new StringNode(TaskBusinessState.ERROR.name());
        default -> null;
      };
    }
    return result;
  }

  public static StringNode convertCaseBusinessState(String oldStateString) {
    CaseState oldState = EnumUtils.getEnum(CaseState.class, oldStateString);
    StringNode result = new StringNode(oldStateString);

    if (Objects.nonNull(oldState)) {
      result = switch (oldState) {
        case RUNNING -> new StringNode(CaseBusinessState.OPEN.name());
        case CREATED -> new StringNode(CaseBusinessState.OPEN.name());
        case DONE -> new StringNode(CaseBusinessState.DONE.name());
        case DESTROYED -> new StringNode(CaseBusinessState.DESTROYED.name());
        default -> null;
      };
    }
    return result;
  }

  /** Rewrites a raw JSON array of state-name strings in place - used for both the legacy per-column
   * {@code filterList} and the current widget-level {@code filters[].values}, which share the same shape. */
  public static void convertStatesArrayInPlace(JsonNode statesNode, Function<String, StringNode> converter) {
    if (Objects.isNull(statesNode) || !statesNode.isArray()) {
      return;
    }
    List<StringNode> newStates = new ArrayList<>();
    statesNode.values().forEach(node -> {
      StringNode newState = converter.apply(node.asString());
      if (newState != null) {
        newStates.add(newState);
      }
    });
    ((ArrayNode) statesNode).removeAll().addAll(newStates.stream().distinct().toList());
  }

  /**
   * Unconditional safety net: normalizes Task/Case widget State-column values on {@code
   * dashboardLikeNode}, regardless of any version stamp on the surrounding data. A version stamp is not
   * proof every value was actually converted (e.g. a wrapped collection stamped current without its
   * items ever running through the version-gated converters), so call this on every read, not just when
   * that chain decides to run.
   *
   * @param dashboardLikeNode any node with a direct {@code widgets} array.
   */
  public static void ensureTaskAndCaseStateFiltersCurrent(JsonNode dashboardLikeNode) {
    if (dashboardLikeNode == null) {
      return;
    }
    normalizeStateFilters(dashboardLikeNode, DashboardWidgetType.TASK.name(),
        DashboardStandardTaskColumn.STATE.getField(), BusinessStateMigrationUtils::convertTaskBusinessState);
    normalizeStateFilters(dashboardLikeNode, DashboardWidgetType.CASE.name(),
        DashboardStandardCaseColumn.STATE.getField(), BusinessStateMigrationUtils::convertCaseBusinessState);
  }

  private static void normalizeStateFilters(JsonNode dashboardLikeNode, String widgetType, String stateField,
      Function<String, StringNode> converter) {
    for (JsonNode widget : new JsonWidgetSearch(dashboardLikeNode).type(widgetType).findWidgets()) {
      normalizeColumnFilterList(widget, stateField, converter);
      normalizeWidgetFilters(widget, stateField, converter);
    }
  }

  private static void normalizeColumnFilterList(JsonNode widget, String stateField, Function<String, StringNode> converter) {
    JsonNode columns = widget.get("columns");
    if (columns == null || !columns.isArray()) {
      return;
    }
    columns.forEach(column -> {
      JsonNode fieldNode = column.get(FIELD);
      if (fieldNode != null && stateField.equals(fieldNode.asString())) {
        convertStatesArrayInPlace(column.get(FILTER_LIST), converter);
      }
    });
  }

  private static void normalizeWidgetFilters(JsonNode widget, String stateField, Function<String, StringNode> converter) {
    JsonNode filters = widget.get(FILTERS);
    if (filters == null || !filters.isArray()) {
      return;
    }
    filters.forEach(filter -> {
      JsonNode fieldNode = filter.get(FIELD);
      if (fieldNode != null && stateField.equals(fieldNode.asString())) {
        convertStatesArrayInPlace(filter.get(VALUES), converter);
      }
    });
  }
}
