package com.axonivy.portal.migration.dashboard.converter.v120;

import java.util.Iterator;

import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.migration.common.IJsonConverter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class DashboardStatisticWidgetConverter implements IJsonConverter {

  @Override
  public AbstractJsonVersion version() {
    return new DashboardJsonVersion("12.0.0");
  }

  // widget type "statistic" is deprecated and replaced by "client-statistic"
  // the Converter will find and remove all "statistic widgets" in the dashboards.
  @Override
  public void convert(JsonNode jsonNode) {
    ArrayNode widgetsArray = (ArrayNode) jsonNode.get("widgets");

    if (widgetsArray == null) {
      return;
    }

    // Remove widgets of type "statistic"
    Iterator<JsonNode> iterator = widgetsArray.iterator();
    while (iterator.hasNext()) {
      JsonNode widget = iterator.next();
      if (widget.has("type") && "statistic".equals(widget.get("type").asText())) {
        iterator.remove(); // Remove widget of type "statistic"
      }
    }

  }

}
