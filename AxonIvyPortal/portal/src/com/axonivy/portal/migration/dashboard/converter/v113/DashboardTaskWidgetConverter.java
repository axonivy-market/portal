package com.axonivy.portal.migration.dashboard.converter.v113;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonWidgetSearch;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.TaskFilterFieldFactory;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;
import tools.jackson.databind.node.StringNode;

public class DashboardTaskWidgetConverter implements IJsonConverter {

  private static final String NO_CATEGORY = "[No Category]";
  private static final String CUSTOM_CASE = "CUSTOM_CASE";
  private static final String TYPE = "type";
  private static final String FILTER = "filter";
  private static final String FILTER_FROM = "filterFrom";
  private static final String FILTER_TO = "filterTo";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardJsonVersion("11.3.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    List<JsonNode> taskWidgets = new JsonWidgetSearch(jsonNode).type(DashboardWidgetType.TASK.name()).findWidgets();

    for (JsonNode taskWidget : taskWidgets) {
      ArrayNode columns = Optional.ofNullable(taskWidget.get("columns")).filter(JsonNode::isArray)
          .map(ArrayNode.class::cast).orElse(null);
      if (columns == null) {
        // A widget with no configured columns omits the field entirely (see
        // @JsonInclude(NON_EMPTY) on DashboardWidget) - nothing to migrate.
        continue;
      }

      columns.values().forEach(col -> {
        DashboardStandardTaskColumn field = DashboardStandardTaskColumn.findBy(col.get("field").asString());
        if (field == null) {
          migrateCustomColumn(taskWidget, col);
        } else {
          migrateStandardColumn(taskWidget, col, field);
        }
        removeOldFiltersFields(col);
      });
    }
  }

  /**
   * @param taskWidget
   * @param col
   */
  private void migrateCustomColumn(JsonNode taskWidget, JsonNode col) {
    DashboardColumnType dashboardColumnType = null;
    if (col.get(TYPE) != null && CUSTOM_CASE.equals(col.get(TYPE).asString())) {
      dashboardColumnType = DashboardColumnType.CUSTOM_CASE;
    } else {
      dashboardColumnType = DashboardColumnType.CUSTOM;
    }

    FilterField filterField = TaskFilterFieldFactory.findBy(col.get("field").asString(), dashboardColumnType);

    if (filterField != null) {
      DashboardFilter filter = new DashboardFilter();
      filterField.initFilter(filter);

      switch (filter.getFilterFormat()) {
      case STRING -> {
        convertStringFilters(initFilterNode(taskWidget), col.get(FILTER), filter.getField(),
            dashboardColumnType);
      }
      case TEXT -> {
        convertStringFilters(initFilterNode(taskWidget), col.get(FILTER), filter.getField(),
            dashboardColumnType);
      }
      case DATE -> {
        convertDateFilters(initFilterNode(taskWidget), col.get(FILTER_FROM), col.get(FILTER_TO), filter.getField(),
            dashboardColumnType);
      }
      case NUMBER -> {
        convertNumberFilters(initFilterNode(taskWidget), col.get(FILTER_FROM), col.get(FILTER_TO), filter.getField(),
            dashboardColumnType);
      }
      default -> {
      }
      }
    }
  }

  private void migrateStandardColumn(JsonNode taskWidget, JsonNode col, DashboardStandardTaskColumn field) {
    switch (field) {
    case CREATED -> {
      convertDateFilters(initFilterNode(taskWidget), col.get("filterFrom"), col.get("filterTo"),
          DashboardStandardTaskColumn.CREATED.getField(), DashboardColumnType.STANDARD);
    }
    case EXPIRY -> {
      convertDateFilters(initFilterNode(taskWidget), col.get("filterFrom"), col.get("filterTo"),
          DashboardStandardTaskColumn.EXPIRY.getField(), DashboardColumnType.STANDARD);
    }
    case NAME -> {
      convertStringFilters(initFilterNode(taskWidget), col.get("filter"), DashboardStandardTaskColumn.NAME.getField(),
          DashboardColumnType.STANDARD);
    }
    case DESCRIPTION -> {
      convertStringFilters(initFilterNode(taskWidget), col.get("filter"),
          DashboardStandardTaskColumn.DESCRIPTION.getField(), DashboardColumnType.STANDARD);
    }
    case RESPONSIBLE -> {
      convertListFilter(initFilterNode(taskWidget), (ArrayNode) col.get("filterList"),
          DashboardStandardTaskColumn.RESPONSIBLE.getField(), DashboardColumnType.STANDARD);
    }
    case STATE -> {
      convertListFilter(initFilterNode(taskWidget), (ArrayNode) col.get("filterList"),
          DashboardStandardTaskColumn.STATE.getField(), DashboardColumnType.STANDARD);
    }
    case PRIORITY -> {
      convertListFilter(initFilterNode(taskWidget), (ArrayNode) col.get("filterList"),
          DashboardStandardTaskColumn.PRIORITY.getField(), DashboardColumnType.STANDARD);
    }
    case CATEGORY -> {
      convertCategoryFilter(initFilterNode(taskWidget), (ArrayNode) col.get("filterList"),
          DashboardStandardTaskColumn.CATEGORY.getField());
    }
    case APPLICATION -> {
      convertListFilter(initFilterNode(taskWidget), (ArrayNode) col.get("filterList"),
          DashboardStandardTaskColumn.APPLICATION.getField(), DashboardColumnType.STANDARD);
    }
    default -> {
    }
    }
  }

  private ArrayNode initFilterNode(JsonNode widget) {
    if (widget.get("filters") == null) {
      return ((ObjectNode) widget).putArray("filters");
    }
    return Optional.ofNullable(widget.get("filters")).filter(JsonNode::isArray).map(ArrayNode.class::cast).get();
  }

  private void convertListFilter(ArrayNode filters, ArrayNode filterList, String field, DashboardColumnType type) {
    if (filterList == null || filterList.size() == 0) {
      return;
    }

    // If the new complex filters has filter for the same field, skip migrate
    filters.values().forEach(filter -> {
      if (filter.get("field").asString().contentEquals(field)) {
        return;
      }
    });

    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new StringNode(field));
    newFilterNode.set("type", new StringNode(type.getType()));
    newFilterNode.set("operator", new StringNode(FilterOperator.IN.name()));

    ArrayNode valuesNode = newFilterNode.putArray("values");
    filterList.values().forEach(node -> {
      valuesNode.add(new StringNode(node.asString()));
    });
  }

  private void convertDateFilters(ArrayNode filters, JsonNode filterFrom, JsonNode filterTo, String field,
      DashboardColumnType type) {
    boolean isEmptyFilterFrom = filterFrom == null || StringUtils.isBlank(filterFrom.asString());
    boolean isEmptyFilterTo = filterTo == null || StringUtils.isBlank(filterTo.asString());

    if (isEmptyFilterFrom && isEmptyFilterTo) {
      return;
    }

    filters.values().forEach(filter -> {
      if (filter.get("field").asString().contentEquals(field)) {
        return;
      }
    });

    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new StringNode(field));
    newFilterNode.set("type", new StringNode(type.getType()));
    newFilterNode.set("operator", new StringNode(FilterOperator.BETWEEN.getOperator()));

    if (!isEmptyFilterFrom) {
      newFilterNode.set("from", new StringNode(filterFrom == null ? "" : filterFrom.asString()));
    }

    if (!isEmptyFilterTo) {
      newFilterNode.set("to", new StringNode(filterTo == null ? "" : filterTo.asString()));
    }
  }

  private void convertStringFilters(ArrayNode filters, JsonNode filterText, String field, DashboardColumnType type) {
    if (filterText == null || StringUtils.isBlank(filterText.asString())) {
      return;
    }

    // If the new complex filters has filter for the same field, skip migrate
    filters.values().forEach(filter -> {
      if (filter.get("field").asString().contentEquals(field)) {
        return;
      }
    });

    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new StringNode(field));
    newFilterNode.set("type", new StringNode(type.getType()));
    newFilterNode.set("operator", new StringNode(FilterOperator.CONTAINS.getOperator()));

    ArrayNode valuesNode = newFilterNode.putArray("values");
    valuesNode.add(new StringNode(filterText.asString()));
  }

  private void convertCategoryFilter(ArrayNode filters, ArrayNode filterList, String field) {
    if (filterList == null || filterList.size() == 0) {
      return;
    }

    // If the new complex filters has filter for the same field, skip migrate
    filters.values().forEach(filter -> {
      if (filter.get("field").asString().contentEquals(field)) {
        return;
      }
    });

    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new StringNode(field));
    newFilterNode.set("type", new StringNode(DashboardColumnType.STANDARD.getType()));
    newFilterNode.set("operator", new StringNode(FilterOperator.IN.name()));

    ArrayNode valuesNode = newFilterNode.putArray("values");
    filterList.values().forEach(node -> {
      if (node.asString().contentEquals(NO_CATEGORY) && filterList.values().size() == 1) {
        // If category filter only have one option: No category
        // Choose operator: No category
        newFilterNode.set("operator", new StringNode(FilterOperator.NO_CATEGORY.name()));
      } else if (!node.asString().contentEquals(NO_CATEGORY)) {
        // Otherwise add all selected categories beside "No category"
        valuesNode.add(new StringNode(node.asString()));
      }
    });
  }

  private void convertNumberFilters(ArrayNode filters, JsonNode filterFrom, JsonNode filterTo, String field,
      DashboardColumnType type) {
    // Currently convert number filters same as convert date filters
    convertDateFilters(filters, filterFrom, filterTo, field, type);
  }

  private void removeOldFiltersFields(JsonNode column) {
    ObjectNode columnObj = (ObjectNode) column;
    columnObj.remove("filter");
    columnObj.remove("filterFrom");
    columnObj.remove("filterTo");
    columnObj.remove("filterList");
  }

}
