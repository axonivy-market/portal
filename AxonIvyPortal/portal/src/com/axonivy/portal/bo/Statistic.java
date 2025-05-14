package com.axonivy.portal.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.dto.statistic.StatisticFilter;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.util.DisplayNameUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Statistic extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = -8416553636564399910L;
  public static final String DEFAULT_ICON = "si-pie-line-graph";
  private String filter;
  private List<StatisticFilter> filters;
  private List<String> permissions;
  private ChartTarget chartTarget;
  private ChartType chartType;
  private StatisticAggregation statisticAggregation;
  private String icon;
  private Integer refreshInterval; // in seconds
  private List<DisplayName> names;
  private List<DisplayName> descriptions;
  private BarChartConfig barChartConfig;
  private LineChartConfig lineChartConfig;
  private PieChartConfig pieChartConfig;
  private NumberChartConfig numberChartConfig;
  private String aggregates;
  @JsonProperty(access = Access.READ_ONLY)
  private List<Entry<String, String>> additionalConfigs;
  private String manipulateValueBy;
  @JsonIgnore
  private String name;
  @JsonIgnore
  private String description;
  @JsonIgnore
  private Boolean isCustom;
  @JsonIgnore
  private List<SecurityMemberDTO> permissionDTOs;

  public Statistic() {
    icon = DEFAULT_ICON;
    isCustom = true;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public NumberChartConfig getNumberChartConfig() {
    return numberChartConfig;
  }

  public void setNumberChartConfig(NumberChartConfig numberChartConfig) {
    this.numberChartConfig = numberChartConfig;
  }

  public ChartType getChartType() {
    return chartType;
  }

  public void setChartType(ChartType chartType) {
    this.chartType = chartType;
  }
  
  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  public Integer getRefreshInterval() {
    return refreshInterval;
  }

  public void setRefreshInterval(Integer refreshInterval) {
    this.refreshInterval = refreshInterval;
  }

  public BarChartConfig getBarChartConfig() {
    return barChartConfig;
  }

  public void setBarChartConfig(BarChartConfig barChartConfig) {
    this.barChartConfig = barChartConfig;
  }

  public LineChartConfig getLineChartConfig() {
    return lineChartConfig;
  }

  public void setLineChartConfig(LineChartConfig lineChartConfig) {
    this.lineChartConfig = lineChartConfig;
  }

  public PieChartConfig getPieChartConfig() {
    return pieChartConfig;
  }

  public void setPieChartConfig(PieChartConfig pieChartConfig) {
    this.pieChartConfig = pieChartConfig;
  }

  public List<StatisticFilter> getFilters() {
    return filters;
  }

  public void setFilters(List<StatisticFilter> filters) {
    this.filters = filters;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  public String getAggregates() {
    return aggregates;
  }

  public void setAggregates(String aggregates) {
    this.aggregates = aggregates;
  }

  public ChartTarget getChartTarget() {
    return chartTarget;
  }

  public void setChartTarget(ChartTarget chartTarget) {
    this.chartTarget = chartTarget;
  }

  public List<DisplayName> getNames() {
    return names;
  }

  public void setNames(List<DisplayName> names) {
    this.names = names;
    name = DisplayNameUtils.findDisplayNameOfUserLanguage(names);
  }

  public String getName() {
    if (name == null) {
      return LanguageUtils.getLocalizedName(names, name);
    }
    return name;
  }

  public void setName(String name) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(names, name);
    this.names = nameResult.names();
    this.name = nameResult.name();
  }

  public List<DisplayName> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<DisplayName> descriptions) {
    this.descriptions = descriptions;
    description = DisplayNameUtils.findDisplayNameOfUserLanguage(descriptions);
  }

  public String getDescription() {
    if (description == null) {
      return LanguageUtils.getLocalizedName(descriptions, description);
    }
    return description;
  }

  public void setDescription(String description) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(descriptions, description);
    this.descriptions = nameResult.names();
    this.description = nameResult.name();
  }

  public List<Entry<String, String>> getAdditionalConfigs() {
    return additionalConfigs;
  }

  public void setAdditionalConfigs(List<Entry<String, String>> additionalConfigs) {
    this.additionalConfigs = additionalConfigs;
  }

  public String getManipulateValueBy() {
    return manipulateValueBy;
  }

  public void setManipulateValueBy(String manipulateValueBy) {
    this.manipulateValueBy = manipulateValueBy;
  }

  public List<SecurityMemberDTO> getPermissionDTOs() {
    return permissionDTOs;
  }

  public void setPermissionDTOs(List<SecurityMemberDTO> permissionDTOs) {
    this.permissionDTOs = permissionDTOs;
  }

  public Boolean getIsCustom() {
    return isCustom;
  }

  public void setIsCustom(Boolean isCustom) {
    this.isCustom = isCustom;
  }

  public StatisticAggregation getStatisticAggregation() {
    return statisticAggregation;
  }

  public void setStatisticAggregation(StatisticAggregation statisticAggregation) {
    this.statisticAggregation = statisticAggregation;
  }
}
