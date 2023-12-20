package com.axonivy.portal.migration.dashboardtemplate.converter.v113;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardTemplateJsonVersion;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonWidgetSearch;
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

public class DashboardTemplateCaseWidgetConverter implements IJsonConverter {

  @Override
  public AbstractJsonVersion version() {
    return new DashboardTemplateJsonVersion("11.3.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    List<JsonNode> caseWidgets = new JsonWidgetSearch(jsonNode)
        .type(DashboardWidgetType.CASE.name()).findWidgets();

    for (JsonNode caseWidget : caseWidgets) {
      ArrayNode columns = Optional.ofNullable(caseWidget.get("columns")).filter(JsonNode::isArray).map(ArrayNode.class::cast).get();

      columns.elements().forEachRemaining(col -> {
        DashboardStandardCaseColumn field = DashboardStandardCaseColumn.findBy(col.get("field").asText());
        if (field == null) {
          migrateCustomColumn(caseWidget, col);
        } else {
          migrateStandardColumn(caseWidget, col, field);
        }
        removeOldFiltersFields(col);
      });
    }
  }

  private void migrateCustomColumn(JsonNode caseWidget, JsonNode col) {
    FilterField filterField = FilterFieldFactory.findBy(col.get("field").asText());
    if (filterField != null) {
      DashboardFilter filter = new DashboardFilter();
      filterField.initFilter(filter);
      switch (filter.getFilterFormat()) {
        case STRING -> {
          convertStringFilters(initFilterNode(caseWidget),
              col.get("filter"),
              filter.getField(),
              false);
        }
        case TEXT -> {
          convertStringFilters(initFilterNode(caseWidget),
              col.get("filter"),
              filter.getField(),
              false);
        }
        case DATE -> {
          convertDateFilters(initFilterNode(caseWidget),
              col.get("filterFrom"),
              col.get("filterTo"),
              filter.getField(),
              false);
        }
        case NUMBER -> {
          convertNumberFilters(initFilterNode(caseWidget),
              col.get("filterFrom"),
              col.get("filterTo"),
              filter.getField(),
              false);
        }
        default -> {}
      }
    }
  }

  private void migrateStandardColumn(JsonNode caseWidget, JsonNode col, DashboardStandardCaseColumn field) {
    switch (field) {
      case CREATED -> {
        convertDateFilters(initFilterNode(caseWidget),
            col.get("filterFrom"),
            col.get("filterTo"),
            DashboardStandardCaseColumn.CREATED.getField(),
            true);
      }
      case FINISHED -> {
        convertDateFilters(initFilterNode(caseWidget),
            col.get("filterFrom"),
            col.get("filterTo"),
            DashboardStandardCaseColumn.FINISHED.getField(),
            true);
      }
      case NAME -> {
        convertStringFilters(initFilterNode(caseWidget),
            col.get("filter"),
            DashboardStandardCaseColumn.NAME.getField(),
            true);
      }
      case DESCRIPTION -> {
        convertStringFilters(initFilterNode(caseWidget),
            col.get("filter"),
            DashboardStandardCaseColumn.DESCRIPTION.getField(),
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

    ArrayNode textsNode = newFilterNode.putArray("values");
    textsNode.add(new TextNode(filterText.asText()));
  }

  private ArrayNode initFilterNode(JsonNode widget) {
    if (widget.get("filters") == null) {
      return ((ObjectNode)widget).putArray("filters");
    }
    return Optional.ofNullable(widget.get("filters")).filter(JsonNode::isArray).map(ArrayNode.class::cast).get();
  }

  private void convertNumberFilters(ArrayNode filters, JsonNode filterFrom, JsonNode filterTo, String field, boolean isStandardField) {
    // Currently convert number filters same as convert date filters
    convertDateFilters(filters, filterFrom, filterTo, field, isStandardField);
  }

  private void removeOldFiltersFields(JsonNode column) {
    ObjectNode columnObj = (ObjectNode) column;
    columnObj.remove("filter");
    columnObj.remove("filterFrom");
    columnObj.remove("filterTo");
    columnObj.remove("filterList");
  }
}
