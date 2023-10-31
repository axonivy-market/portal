package com.axonivy.portal.migration.dashboard.converter.v113;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterType;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonWidgetSearch;
import com.axonivy.portal.migration.common.visitor.JsonDashboardVisitor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class DashboardCaseWidgetConverter implements IJsonConverter {

  @Override
  public AbstractJsonVersion version() {
    return new DashboardJsonVersion("11.3.0");
  }

  @Override
  public void convert(JsonNode jsonNode) {
    List<JsonNode> caseWidgets = new JsonWidgetSearch(jsonNode)
        .type(DashboardWidgetType.CASE.name()).findWidgets();

    for (JsonNode caseWidget : caseWidgets) {
      ArrayNode columns = Optional.ofNullable(caseWidget.get("columns")).filter(JsonNode::isArray).map(ArrayNode.class::cast).get();

      columns.elements().forEachRemaining(col -> {
        DashboardStandardCaseColumn field = DashboardStandardCaseColumn.findBy(col.get("field").asText());
        switch (field) {
          case CREATED -> {
            convertDateFilters(initFilterNode(caseWidget),
                col.get("filterFrom"),
                col.get("filterTo"),
                DashboardStandardCaseColumn.CREATED.getField());
          }
          case FINISHED -> {
            convertDateFilters(initFilterNode(caseWidget),
                col.get("filterFrom"),
                col.get("filterTo"),
                DashboardStandardCaseColumn.FINISHED.getField());
          }
          case NAME -> {
            convertStringFilters(initFilterNode(caseWidget),
                col.get("filter"),
                DashboardStandardCaseColumn.NAME.getField());
          }
          case DESCRIPTION -> {
            convertStringFilters(initFilterNode(caseWidget),
                col.get("filter"),
                DashboardStandardCaseColumn.DESCRIPTION.getField());
          }
          default -> {}
        }
        removeOldFiltersFields(col);
      });
    }
  }

  public List<JsonNode> findWidgets(JsonNode dashboard) {
    List<JsonNode> matches = new ArrayList<>();
    new JsonDashboardVisitor(dashboard).visitWidgets(widget -> {
      matches.add(widget);
    });
    return matches;
  }

  private void convertDateFilters(ArrayNode filters, JsonNode filterFrom, JsonNode filterTo, String field) {
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

    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new TextNode(field));
    newFilterNode.set("type", new TextNode(FilterType.DATE.name()));
    newFilterNode.set("operator", new TextNode(FilterOperator.BETWEEN.name()));

    if (!isEmptyFilterFrom) {
      newFilterNode.set("from", new TextNode(filterFrom.asText()));
    }

    if (!isEmptyFilterTo) {
      newFilterNode.set("to", new TextNode(filterTo.asText()));
    }
  }

  private void convertStringFilters(ArrayNode filters, JsonNode filterText, String field) {
    if (filterText == null || StringUtils.isBlank(filterText.asText())) {
      return;
    }

    filters.elements().forEachRemaining(filter -> {
      if (filter.get("field").asText().contentEquals(field)) {
        return;
      }
    });

    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new TextNode(field));
    newFilterNode.set("type", new TextNode(FilterType.TEXT.name()));
    newFilterNode.set("operator", new TextNode(FilterOperator.CONTAINS.name()));

    ArrayNode textsNode = newFilterNode.putArray("texts");
    textsNode.add(new TextNode(filterText.asText()));
  }

  private ArrayNode initFilterNode(JsonNode widget) {
    if (widget.get("filters") == null) {
      return ((ObjectNode)widget).putArray("filters");
    }
    return Optional.ofNullable(widget.get("filters")).filter(JsonNode::isArray).map(ArrayNode.class::cast).get();
  }

  private void removeOldFiltersFields(JsonNode column) {
    ObjectNode columnObj = (ObjectNode) column;
    columnObj.remove("filter");
    columnObj.remove("filterForm");
    columnObj.remove("filterTo");
    columnObj.remove("filterList");
  }
}
