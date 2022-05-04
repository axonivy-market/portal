package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserFilterCollection implements Serializable {

  private static final long serialVersionUID = -4806650420632442697L;

  private String widgetId;
  private DashboardWidgetType widgetType;
  private List<WidgetFilterModel> selectedWidgetFilters;
  private List<String> selectedWidgetFilterIds;
  private WidgetFilterModel latestFilterOption;

  @JsonIgnore
  private List<WidgetFilterModel> widgetFilterSelections;
  @JsonIgnore
  private Map<String, FilterColumnModel> selectedFilterOptionMap;

  public UserFilterCollection() {}

  public UserFilterCollection(String widgetId, DashboardWidgetType widgetType) {
    this.widgetId = widgetId;
    this.widgetType = widgetType;
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

  public List<WidgetFilterModel> getSelectedWidgetFilters() {
    if (selectedWidgetFilters == null) {
      selectedWidgetFilters = new ArrayList<>();
    }
    return selectedWidgetFilters;
  }

  public void setSelectedWidgetFilters(List<WidgetFilterModel> selectedWidgetFilters) {
    this.selectedWidgetFilters = selectedWidgetFilters;
  }

  public WidgetFilterModel getLatestFilterOption() {
    return latestFilterOption;
  }

  public void setLatestFilterOption(WidgetFilterModel latestFilterOption) {
    this.latestFilterOption = latestFilterOption;
  }

  public List<String> getSelectedWidgetFilterIds() {
    return selectedWidgetFilterIds;
  }

  public void setSelectedWidgetFilterIds(List<String> selectedWidgetFilterIds) {
    this.selectedWidgetFilterIds = selectedWidgetFilterIds;
  }

  public List<WidgetFilterModel> getWidgetFilterSelections() {
    if (widgetFilterSelections == null) {
      widgetFilterSelections = new ArrayList<>();
    }
    return widgetFilterSelections;
  }

  public void setWidgetFilterSelections(List<WidgetFilterModel> widgetFilterSelections) {
    this.widgetFilterSelections = widgetFilterSelections;
  }

  @JsonIgnore
  public void updateUserFilterOptionValue(DashboardWidget widget) {
    latestFilterOption = new WidgetFilterModel(widget.getName(), widget.getType());
    List<ColumnModel> columns = new ArrayList<>();
    if (DashboardWidgetType.TASK == widget.getType()) {
      columns = ((TaskDashboardWidget) widget).getFilterableColumns();
    }
    if (DashboardWidgetType.CASE == widget.getType()) {
      columns = ((CaseDashboardWidget) widget).getFilterableColumns();
    }
    if (DashboardWidgetType.PROCESS == widget.getType()) {
      columns = ((CompactProcessDashboardWidget) widget).getFilterableColumns();
    }
    latestFilterOption.addFilterableColumns(columns);
    setSelectedWidgetFilterIds(getSelectedWidgetFilters()
          .stream()
          .map(WidgetFilterModel::getId).collect(Collectors.toList()));
  }

  public Map<String, FilterColumnModel> getSelectedFilterOptionMap() {
    if (selectedFilterOptionMap == null) {
      selectedFilterOptionMap = new HashMap<>();
    }
    return selectedFilterOptionMap;
  }

  public void setSelectedFilterOptionMap(Map<String, FilterColumnModel> selectedFilterOptionMap) {
    this.selectedFilterOptionMap = selectedFilterOptionMap;
  }
}
