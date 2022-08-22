package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WidgetFilterModel extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 5781537661765302380L;
  private String name;
  private String widgetId;
  private String widgetName;
  private DashboardWidgetType widgetType;
  private List<FilterColumnModel> filterableColumns;

  public WidgetFilterModel() {}

  public WidgetFilterModel(String name, DashboardWidgetType widgetType) {
    this.name = name;
    this.widgetType = widgetType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getWidgetId() {
    return widgetId;
  }

  public void setWidgetId(String widgetId) {
    this.widgetId = widgetId;
  }

  public DashboardWidgetType getWidgetType() {
    return widgetType;
  }

  public void setWidgetType(DashboardWidgetType widgetType) {
    this.widgetType = widgetType;
  }

  public List<FilterColumnModel> getFilterableColumns() {
    return filterableColumns;
  }

  public void setFilterableColumns(List<FilterColumnModel> filterableColumns) {
    this.filterableColumns = filterableColumns;
  }
  

  public void addFilterableColumns(List<ColumnModel> columns) {
    if (this.filterableColumns == null) {
      this.filterableColumns = new ArrayList<>();
    }
    CollectionUtils.emptyIfNull(columns).forEach(column -> {
      var filterColumn = new FilterColumnModel(column.getField(), column.getFormat(), column.getType());
      filterColumn.setUserFilterFrom(column.getUserFilterFrom());
      filterColumn.setUserFilterTo(column.getUserFilterTo());
      filterColumn.setUserFilterList(column.getUserFilterList());
      filterColumn.setUserFilter(column.getUserFilter());
      this.filterableColumns.add(filterColumn);
    });
  }

  public String getWidgetName() {
    return widgetName;
  }

  public void setWidgetName(String widgetName) {
    this.widgetName = widgetName;
  }

  @JsonIgnore
  public static Predicate<? super WidgetFilterModel> isEqualFilter(WidgetFilterModel filter) {
    return selectedFilter -> selectedFilter.getId().equals(filter.getId());
  }
}
