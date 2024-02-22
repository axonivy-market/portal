package com.axonivy.portal.migration.dashboardfilter.converter.v113;

import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardFilterJsonVersion;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonDashboardConfigurationSearch;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class DashboardTaskWidgetFilterConverter implements IJsonConverter {

  private static final String NO_CATEGORY = "[No Category]";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardFilterJsonVersion("11.3.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    new JsonDashboardConfigurationSearch(jsonNode)
    .type(DashboardWidgetType.TASK.name())
    .findFilterableColumns().ifPresent(columns -> {
        columns.elements().forEachRemaining(col -> {
          DashboardStandardTaskColumn field = DashboardStandardTaskColumn.findBy(col.get("field").asText());
          if (field == null) {
            migrateCustomColumn(jsonNode, col);
          } else {
            migrateStandardColumn(jsonNode, col, field);
          }
        });
      columns.removeAll();
    });    
  }

  private void migrateCustomColumn(JsonNode jsonNode, JsonNode col) {
    /**
     * Will do in the next story
     */
  }

  private void migrateStandardColumn(JsonNode jsonNode, JsonNode col, DashboardStandardTaskColumn field) {
    switch (field) {
      case CREATED -> {
        convertDateFilters(initFilterNode(jsonNode),
            col.get("userFilterFrom"),
            col.get("userFilterTo"),
            DashboardStandardTaskColumn.CREATED.getField(),
            true);
      }
      case EXPIRY -> {
        convertDateFilters(initFilterNode(jsonNode),
            col.get("userFilterFrom"),
            col.get("userFilterTo"),
            DashboardStandardTaskColumn.EXPIRY.getField(),
            true);
      }
      case NAME -> {
        convertStringFilters(initFilterNode(jsonNode),
            col.get("userFilter"),
            DashboardStandardTaskColumn.NAME.getField(),
            true);
      }
      case DESCRIPTION -> {
        convertStringFilters(initFilterNode(jsonNode),
            col.get("userFilter"),
            DashboardStandardTaskColumn.DESCRIPTION.getField(),
            true);
      }
      case RESPONSIBLE -> {
          convertListFilter(initFilterNode(jsonNode),
              (ArrayNode)col.get("userFilterList"),
              DashboardStandardTaskColumn.RESPONSIBLE.getField(),
              true);
        }
        case STATE -> {
          convertListFilter(initFilterNode(jsonNode),
              (ArrayNode)col.get("userFilterList"),
              DashboardStandardTaskColumn.STATE.getField(),
              true);
        }
        case PRIORITY -> {
          convertListFilter(initFilterNode(jsonNode),
              (ArrayNode)col.get("userFilterList"),
              DashboardStandardTaskColumn.PRIORITY.getField(),
              true);
        }
        case CATEGORY -> {
          convertCategoryFilter(initFilterNode(jsonNode),
              (ArrayNode)col.get("userFilterList"),
              DashboardStandardTaskColumn.CATEGORY.getField());
        }
        case APPLICATION -> {
          convertListFilter(initFilterNode(jsonNode),
              (ArrayNode)col.get("userFilterList"),
              DashboardStandardTaskColumn.APPLICATION.getField(),
              true);
        }
      default -> {}
    }
  }

  private ArrayNode initFilterNode(JsonNode widget) {
    if (widget.get("userFilters") == null) {
      return ((ObjectNode)widget).putArray("userFilters");
    }
    return Optional.ofNullable(widget.get("userFilters")).filter(JsonNode::isArray).map(ArrayNode.class::cast).get();
  }

  private void convertDateFilters(ArrayNode filters, JsonNode filterFrom, JsonNode filterTo, String field, boolean isStandardField) {
    boolean isEmptyFilterFrom = filterFrom == null || StringUtils.isBlank(filterFrom.asText());
    boolean isEmptyFilterTo = filterTo == null || StringUtils.isBlank(filterTo.asText());

    if (isEmptyFilterFrom && isEmptyFilterTo) {
      return;
    }

    filters.elements().forEachRemaining(filter -> {
      if (filter.get("field").asText().contentEquals(field)) {
        return;
      }
    });

    DashboardColumnType type = isStandardField ? DashboardColumnType.STANDARD : DashboardColumnType.CUSTOM;
    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new TextNode(field));
    newFilterNode.set("type", new TextNode(type.getType()));
    newFilterNode.set("operator", new TextNode(FilterOperator.BETWEEN.getOperator()));

    if (!isEmptyFilterFrom) {
      newFilterNode.set("from", new TextNode(filterFrom.asText()));
    }

    if (!isEmptyFilterTo) {
      newFilterNode.set("to", new TextNode(filterTo.asText()));
    }
  }

  private void convertStringFilters(ArrayNode filters, JsonNode filterText, String field, boolean isStandardField) {
    if (filterText == null || StringUtils.isBlank(filterText.asText())) {
      return;
    }

    // If the new complex filters has filter for the same field, skip migrate
    filters.elements().forEachRemaining(filter -> {
      if (filter.get("field").asText().contentEquals(field)) {
        return;
      }
    });

    DashboardColumnType type = isStandardField ? DashboardColumnType.STANDARD : DashboardColumnType.CUSTOM;
    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new TextNode(field));
    newFilterNode.set("type", new TextNode(type.getType()));
    newFilterNode.set("operator", new TextNode(FilterOperator.CONTAINS.getOperator()));

    ArrayNode valuesNode = newFilterNode.putArray("values");
    valuesNode.add(new TextNode(filterText.asText()));
  }

  private void convertCategoryFilter(ArrayNode filters, ArrayNode filterList, String field) {
    if (filterList == null || filterList.size() == 0) {
      return;
    }

    // If the new complex filters has filter for the same field, skip migrate
    filters.elements().forEachRemaining(filter -> {
      if (filter.get("field").asText().contentEquals(field)) {
        return;
      }
    });

    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new TextNode(field));
    newFilterNode.set("type", new TextNode(DashboardColumnType.STANDARD.getType()));
    newFilterNode.set("operator", new TextNode(FilterOperator.IN.name()));

    ArrayNode valuesNode = newFilterNode.putArray("values");
    filterList.elements().forEachRemaining(node -> {
      if (node.asText().contentEquals(NO_CATEGORY) && CollectionUtils.size(filterList.elements()) == 1) {
        // If category filter only have one option: No category
        // Choose operator: No category
        newFilterNode.set("operator", new TextNode(FilterOperator.NO_CATEGORY.name()));
      } else if (!node.asText().contentEquals(NO_CATEGORY)) {
        // Otherwise add all selected categories beside "No category"
        valuesNode.add(new TextNode(node.asText()));
      }
    });
  }

  private void convertListFilter(ArrayNode filters, ArrayNode filterList, String field, boolean isStandardField) {
    if (filterList == null || filterList.size() == 0) {
      return;
    }

    // If the new complex filters has filter for the same field, skip migrate
    filters.elements().forEachRemaining(filter -> {
      if (filter.get("field").asText().contentEquals(field)) {
        return;
      }
    });

    DashboardColumnType type = isStandardField ? DashboardColumnType.STANDARD : DashboardColumnType.CUSTOM;
    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new TextNode(field));
    newFilterNode.set("type", new TextNode(type.getType()));
    newFilterNode.set("operator", new TextNode(FilterOperator.IN.name()));

    ArrayNode valuesNode = newFilterNode.putArray("values");
    filterList.elements().forEachRemaining(node -> {
      valuesNode.add(new TextNode(node.asText()));
    });
  }
  

}
