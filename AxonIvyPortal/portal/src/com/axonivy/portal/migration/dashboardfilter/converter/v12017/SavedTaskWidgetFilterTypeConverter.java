package com.axonivy.portal.migration.dashboardfilter.converter.v12017;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardFilterJsonVersion;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.taskcolumn.TaskColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;

public class SavedTaskWidgetFilterTypeConverter implements IJsonConverter {

  private static final String WIDGET_TYPE = "widgetType";
  private static final String WIDGET_ID = "widgetId";
  private static final String USER_FILTERS = "userFilters";
  private static final String FIELD = "field";
  private static final String TYPE = "type";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardFilterJsonVersion("12.0.17");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    if (!(jsonNode instanceof ObjectNode) || !isTaskWidget(jsonNode)) {
      return;
    }
    JsonNode userFilters = jsonNode.get(USER_FILTERS);
    if (userFilters == null || !userFilters.isArray() || userFilters.size() == 0) {
      return;
    }
    List<TaskColumnModel> columns = findTaskWidgetColumns(textValue(jsonNode, WIDGET_ID));
    if (CollectionUtils.isEmpty(columns)) {
      return;
    }
    for (JsonNode userFilter : userFilters) {
      if (userFilter instanceof ObjectNode filterNode) {
        retypeFilter(filterNode, columns);
      }
    }
  }

  private void retypeFilter(ObjectNode filterNode, List<TaskColumnModel> columns) {
    String field = textValue(filterNode, FIELD);
    if (field == null) {
      return;
    }
    DashboardFilter probe = new DashboardFilter();
    probe.setField(field);
    probe.setFilterType(parseType(textValue(filterNode, TYPE)));
    DashboardColumnType resolved = DashboardWidgetUtils.resolveCaseCustomColumnType(columns, probe);
    if (resolved != null && resolved != probe.getFilterType()) {
      filterNode.set(TYPE, new TextNode(resolved.getType()));
    }
  }

  private List<TaskColumnModel> findTaskWidgetColumns(String widgetId) {
    if (StringUtils.isBlank(widgetId)) {
      return Collections.emptyList();
    }
    for (Dashboard dashboard : CollectionUtils.emptyIfNull(DashboardUtils.getSessionDashboards())) {
      for (DashboardWidget widget : CollectionUtils.emptyIfNull(dashboard.getWidgets())) {
        if (widget instanceof TaskDashboardWidget taskWidget && widgetId.equals(taskWidget.getId())) {
          return taskWidget.getColumns();
        }
      }
    }
    return Collections.emptyList();
  }

  private boolean isTaskWidget(JsonNode node) {
    return DashboardWidgetType.TASK.name().equals(textValue(node, WIDGET_TYPE));
  }

  private static DashboardColumnType parseType(String type) {
    if (StringUtils.isBlank(type)) {
      return null;
    }
    try {
      return DashboardColumnType.valueOf(type.toUpperCase());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  private static String textValue(JsonNode node, String field) {
    JsonNode value = node.get(field);
    return value != null && value.isTextual() ? value.asText() : null;
  }
}
