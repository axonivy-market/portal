package ch.ivy.addon.portalkit.statistics;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.donut.DonutChartModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.constant.PortalPrefix;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivyteam.ivy.environment.Ivy;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatisticChart extends AbstractConfiguration {
  private String chartName;
  private List<DisplayName> names;
  private StatisticChartType type;
  private StatisticFilter filter;
  private long position;
  @JsonIgnore
  private DonutChartModel donutChartModel;
  @JsonIgnore
  private BarChartModel barChartModel;
  @JsonIgnore
  private String name;

  public List<DisplayName> getNames() {
    return names;
  }

  public void setNames(List<DisplayName> names) {
    this.names = names;
  }

  public StatisticChartType getType() {
    return type;
  }

  public void setType(StatisticChartType type) {
    this.type = type;
  }

  public long getPosition() {
    return position;
  }

  public void setPosition(long position) {
    this.position = position;
  }

  public DonutChartModel getDonutChartModel() {
    return donutChartModel;
  }

  public void setDonutChartModel(DonutChartModel donutChartModel) {
    this.donutChartModel = donutChartModel;
  }

  public BarChartModel getBarChartModel() {
    return barChartModel;
  }

  public void setBarChartModel(BarChartModel barChartModel) {
    this.barChartModel = barChartModel;
  }

  public StatisticFilter getFilter() {
    return filter;
  }

  public void setFilter(StatisticFilter filter) {
    this.filter = filter;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    StatisticChart chart = (StatisticChart) object;
    EqualsBuilder builder = new EqualsBuilder();
    builder.append(getId(), chart.getId());
    return builder.isEquals();
  }
  
  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    builder.append(getId());
    return builder.hashCode();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getChartName() {
    if (StringUtils.startsWithIgnoreCase(chartName, PortalPrefix.CMS)) {
      return Ivy.cms().co(StringUtils.removeStart(chartName, PortalPrefix.CMS));
    }

    return chartName;
  }

  public void setChartName(String chartName) {
    this.chartName = chartName;
  }
}
