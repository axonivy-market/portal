package ch.ivy.addon.portalkit.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClientStatisticDashboardWidget extends DashboardWidget {

  private static final long serialVersionUID = 1L;
  private String chartId;
  private long refreshInterval; // in seconds

  public ClientStatisticDashboardWidget() {}

  @JsonIgnore
  @Override
  public void resetWidgetFilters() {}

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.CLIENT_STATISTIC;
  }

  public String getChartId() {
    return chartId;
  }

  public void setChartId(String chartId) {
    this.chartId = chartId;
  }

  public long getRefreshInterval() {
    return refreshInterval;
  }

  public void setRefreshInterval(long refreshInterval) {
    this.refreshInterval = refreshInterval;
  }

}
