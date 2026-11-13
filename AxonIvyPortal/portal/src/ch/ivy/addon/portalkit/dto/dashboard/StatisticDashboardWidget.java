package ch.ivy.addon.portalkit.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.persistence.converter.JsonValueFilters.ExcludeTrue;
import io.github.axonivy.json.schema.annotations.RemoteRef;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatisticDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 1L;
  private String chartId;
  @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ExcludeTrue.class)
  private boolean showFullscreenMode;

  public StatisticDashboardWidget() {
    setShowFullscreenMode(true);
  }

  @JsonIgnore
  @Override
  public void resetWidgetFilters() {}

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.STATISTIC;
  }

  @RemoteRef("portal-custom-statistic-schema.json")
  @JsonPropertyDescription("ID of an existing statistic chart. Define the chart in the statistics JSON configuration first, then set this field to the chart's id.")
  public String getChartId() {
    return chartId;
  }

  public void setChartId(String chartId) {
    this.chartId = chartId;
  }
  
  @JsonIgnore
  @Override
  public void cancelUserFilter() {    
  }

  public boolean isShowFullscreenMode() {
    return showFullscreenMode;
  }

  public void setShowFullscreenMode(boolean showFullscreenMode) {
    this.showFullscreenMode = showFullscreenMode;
  }

}
