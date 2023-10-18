package com.axonivy.portal.migration.dashboardfilter.converter.v113;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardFilterJsonVersion;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterType;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.axonivy.portal.migration.common.search.JsonDashboardConfigurationSearch;
import com.axonivy.portal.migration.common.visitor.JsonDashboardVisitor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class DashboardCaseWidgetFilterConverter implements IJsonConverter {

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
          if (col.get("field").asText().contentEquals(DashboardStandardCaseColumn.CREATED.getField())) {
              convertCreatedDateFilters(initFilterNode(jsonNode), col.get("userFilterFrom"), col.get("userFilterTo"));
            }
            if (col.get("field").asText().contentEquals(DashboardStandardCaseColumn.NAME.getField())) {
              convertNameFilters(initFilterNode(jsonNode), col.get("userFilter"));
            }
          });
        columns.removeAll();
      });
  }

  public List<JsonNode> findWidgets(JsonNode dashboard) {
    List<JsonNode> matches = new ArrayList<>();
    new JsonDashboardVisitor(dashboard).visitWidgets(widget -> {
      matches.add(widget);
    });
    return matches;
  }

  private void convertCreatedDateFilters(ArrayNode filters, JsonNode filterFrom, JsonNode filterTo) {
    boolean isEmptyFilterFrom = filterFrom == null || StringUtils.isBlank(filterFrom.asText());
    boolean isEmptyFilterTo = filterTo == null || StringUtils.isBlank(filterTo.asText());

    if (isEmptyFilterFrom && isEmptyFilterTo) {
      return;
    }

    filters.elements().forEachRemaining(filter -> {
      if (filter.get("field").asText().contentEquals(DashboardStandardCaseColumn.CREATED.getField())) {
        return;
      }
    });

    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new TextNode(DashboardStandardCaseColumn.CREATED.getField()));
    newFilterNode.set("type", new TextNode(FilterType.DATE.name()));
    newFilterNode.set("operator", new TextNode(FilterOperator.BETWEEN.name()));

    if (!isEmptyFilterFrom) {
      newFilterNode.set("from", new TextNode(filterFrom.asText()));
    }

    if (!isEmptyFilterTo) {
      newFilterNode.set("to", new TextNode(filterTo.asText()));
    }
  }

  private void convertNameFilters(ArrayNode filters, JsonNode filterText) {
    if (filterText == null || StringUtils.isBlank(filterText.asText())) {
      return;
    }

    filters.elements().forEachRemaining(filter -> {
      if (filter.get("field").asText().contentEquals(DashboardStandardCaseColumn.NAME.getField())) {
        return;
      }
    });

    ObjectNode newFilterNode = filters.addObject();
    newFilterNode.set("field", new TextNode(DashboardStandardCaseColumn.NAME.getField()));
    newFilterNode.set("type", new TextNode(FilterType.TEXT.name()));
    newFilterNode.set("operator", new TextNode(FilterOperator.CONTAINS.name()));

    ArrayNode textsNode = newFilterNode.putArray("texts");
    textsNode.add(new TextNode(filterText.asText()));
  }

  private ArrayNode initFilterNode(JsonNode widget) {
    if (widget.get("userFilters") == null) {
      return ((ObjectNode)widget).putArray("userFilters");
    }
    return Optional.ofNullable(widget.get("userFilters")).filter(JsonNode::isArray).map(ArrayNode.class::cast).get();
  }
}
