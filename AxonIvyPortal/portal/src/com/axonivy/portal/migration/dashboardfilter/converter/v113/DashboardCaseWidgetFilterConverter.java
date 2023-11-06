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
            switch (field) {
            case CREATED -> {
              convertDateFilters(initFilterNode(jsonNode),
                  col.get("userFilterFrom"),
                  col.get("userFilterTo"),
                  DashboardStandardCaseColumn.CREATED.getField());
            }
            case FINISHED -> {
              convertDateFilters(initFilterNode(jsonNode),
                  col.get("userFilterFrom"),
                  col.get("userFilterTo"),
                  DashboardStandardCaseColumn.FINISHED.getField());
            }
            case NAME -> {
              convertStringFilters(initFilterNode(jsonNode),
                  col.get("userFilter"),
                  DashboardStandardCaseColumn.NAME.getField());
            }
            case DESCRIPTION -> {
              convertStringFilters(initFilterNode(jsonNode),
                  col.get("userFilter"),
                  DashboardStandardCaseColumn.DESCRIPTION.getField());
            }
            case CREATOR -> {
              convertListFilter(initFilterNode(jsonNode),
                  (ArrayNode)col.get("userFilterList"),
                  DashboardStandardCaseColumn.CREATOR.getField(),
                  FilterType.CREATOR.name());
            }
            case STATE -> {
              convertListFilter(initFilterNode(jsonNode),
                  (ArrayNode)col.get("userFilterList"),
                  DashboardStandardCaseColumn.STATE.getField(),
                  FilterType.STATE.name());
            }
            case CATEGORY -> {
              convertCategoryFilter(initFilterNode(jsonNode),
                  (ArrayNode)col.get("userFilterList"),
                  DashboardStandardCaseColumn.CATEGORY.getField(),
                  FilterType.CATEGORY.name());
            }
            case APPLICATION -> {
              convertListFilter(initFilterNode(jsonNode),
                  (ArrayNode)col.get("userFilterList"),
                  DashboardStandardCaseColumn.APPLICATION.getField(),
                  FilterType.APPLICATION.name());
            }
            default -> {}
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

    // If the new complex filters has filter for the same field, skip migrate
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

  private void convertListFilter(ArrayNode filters, ArrayNode filterList, String field, String filterType) {
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
    newFilterNode.set("type", new TextNode(filterType));
    newFilterNode.set("operator", new TextNode(FilterOperator.IN.name()));

    ArrayNode textsNode = newFilterNode.putArray("texts");
    filterList.elements().forEachRemaining(node -> {
      textsNode.add(new TextNode(node.asText()));
    });
  }

  private void convertCategoryFilter(ArrayNode filters, ArrayNode filterList, String field, String filterType) {
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
    newFilterNode.set("type", new TextNode(filterType));
    newFilterNode.set("operator", new TextNode(FilterOperator.IN.name()));

    ArrayNode textsNode = newFilterNode.putArray("texts");
    filterList.elements().forEachRemaining(node -> {
      if (node.asText().contentEquals(NO_CATEGORY)) {
        newFilterNode.set("operator", new TextNode(FilterOperator.NO_CATEGORY.name()));
      } else {
        textsNode.add(new TextNode(node.asText()));
      }
    });
  }

  private ArrayNode initFilterNode(JsonNode widget) {
    if (widget.get("userFilters") == null) {
      return ((ObjectNode)widget).putArray("userFilters");
    }
    return Optional.ofNullable(widget.get("userFilters")).filter(JsonNode::isArray).map(ArrayNode.class::cast).get();
  }
}
