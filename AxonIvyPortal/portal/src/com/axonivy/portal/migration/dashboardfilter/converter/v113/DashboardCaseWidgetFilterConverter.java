package com.axonivy.portal.migration.dashboardfilter.converter.v113;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardFilterJsonVersion;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonDashboardConfigurationSearch;
import com.axonivy.portal.migration.common.visitor.JsonDashboardVisitor;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class DashboardCaseWidgetFilterConverter implements IJsonConverter {

  private static final String NO_CATEGORY = "[No Category]";

  @Override
  public AbstractJsonVersion version() {
    return new DashboardFilterJsonVersion("11.3.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    new JsonDashboardConfigurationSearch(jsonNode)
      .type(DashboardWidgetType.CASE.name())
      .findFilterableColumns().ifPresent(columns -> {
          columns.elements().forEachRemaining(col -> {
            DashboardStandardCaseColumn field = DashboardStandardCaseColumn.findBy(col.get("field").asText());
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
    FilterField filterField = FilterFieldFactory.findBy(col.get("field").asText());
    if (filterField != null) {
      DashboardFilter filter = new DashboardFilter();
      filterField.initFilter(filter);
      switch (filter.getFilterFormat()) {
        case STRING -> {
          convertStringFilters(initFilterNode(jsonNode),
              col.get("userFilter"),
              filter.getField(),
              false);
        }
        case TEXT -> {
          convertStringFilters(initFilterNode(jsonNode),
              col.get("userFilter"),
              filter.getField(),
              false);
        }
        case DATE -> {
          convertDateFilters(initFilterNode(jsonNode),
              col.get("userFilterFrom"),
              col.get("userFilterTo"),
              filter.getField(),
              false);
        }
        case NUMBER -> {
          convertNumberFilters(initFilterNode(jsonNode),
              col.get("userFilterFrom"),
              col.get("userFilterTo"),
              filter.getField(),
              false);
        }
        default -> {}
      }
    }
  }

  private void migrateStandardColumn(JsonNode jsonNode, JsonNode col, DashboardStandardCaseColumn field) {
    switch (field) {
      case CREATED -> {
        convertDateFilters(initFilterNode(jsonNode),
            col.get("userFilterFrom"),
            col.get("userFilterTo"),
            DashboardStandardCaseColumn.CREATED.getField(),
            true);
      }
      case FINISHED -> {
        convertDateFilters(initFilterNode(jsonNode),
            col.get("userFilterFrom"),
            col.get("userFilterTo"),
            DashboardStandardCaseColumn.FINISHED.getField(),
            true);
      }
      case NAME -> {
        convertStringFilters(initFilterNode(jsonNode),
            col.get("userFilter"),
            DashboardStandardCaseColumn.NAME.getField(),
            true);
      }
      case DESCRIPTION -> {
        convertStringFilters(initFilterNode(jsonNode),
            col.get("userFilter"),
            DashboardStandardCaseColumn.DESCRIPTION.getField(),
            true);
      }
      case CREATOR -> {
          convertListFilter(initFilterNode(jsonNode),
              (ArrayNode)col.get("userFilterList"),
              DashboardStandardCaseColumn.CREATOR.getField(),
              true);
        }
        case STATE -> {
          convertListFilter(initFilterNode(jsonNode),
              (ArrayNode)col.get("userFilterList"),
              DashboardStandardCaseColumn.STATE.getField(),
              true);
        }
        case CATEGORY -> {
          convertCategoryFilter(initFilterNode(jsonNode),
              (ArrayNode)col.get("userFilterList"),
              DashboardStandardCaseColumn.CATEGORY.getField());
        }
        case APPLICATION -> {
          convertListFilter(initFilterNode(jsonNode),
              (ArrayNode)col.get("userFilterList"),
              DashboardStandardCaseColumn.APPLICATION.getField(),
              true);
        }
      default -> {}
    }
  }

  public List<JsonNode> findWidgets(JsonNode dashboard) {
    List<JsonNode> matches = new ArrayList<>();
    new JsonDashboardVisitor(dashboard).visitWidgets(widget -> {
      matches.add(widget);
    });
    return matches;
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
      newFilterNode.set("from", new TextNode(filterFrom == null ? "" : filterFrom.asText()));
    }

    if (!isEmptyFilterTo) {
      newFilterNode.set("to", new TextNode(filterTo == null ? "" : filterTo.asText()));
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
      if (node.asText().contentEquals(NO_CATEGORY)) {
        newFilterNode.set("operator", new TextNode(FilterOperator.NO_CATEGORY.name()));
      } else {
        valuesNode.add(new TextNode(node.asText()));
      }
    });
  }

  private void convertNumberFilters(ArrayNode filters, JsonNode filterFrom, JsonNode filterTo, String field, boolean isStandardField) {
    // Currently convert number filters same as convert date filters
    convertDateFilters(filters, filterFrom, filterTo, field, isStandardField);
  }

  private ArrayNode initFilterNode(JsonNode widget) {
    if (widget.get("userFilters") == null) {
      return ((ObjectNode)widget).putArray("userFilters");
    }
    return Optional.ofNullable(widget.get("userFilters")).filter(JsonNode::isArray).map(ArrayNode.class::cast).get();
  }
}
