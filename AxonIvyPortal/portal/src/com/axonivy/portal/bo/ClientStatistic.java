package com.axonivy.portal.bo;

import java.util.List;
import java.util.Map.Entry;

import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.enums.statistic.ChartTarget;
import com.axonivy.portal.enums.statistic.ChartType;
import com.axonivy.portal.util.DisplayNameUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;
import ch.ivyteam.ivy.environment.Ivy;

public class ClientStatistic extends AbstractConfiguration {
  private String aggregates;
  private String filter;
  private List<String> permissions;
  private ChartTarget chartTarget;
  private ChartType chartType;
  private Long refreshInterval; // in seconds
  @JsonInclude(value = Include.NON_NULL)
  private BarChartConfig barChartConfig;
  @JsonInclude(value = Include.NON_NULL)
  private LineChartConfig lineChartConfig;
  @JsonInclude(value = Include.NON_NULL)
  private NumberChartConfig numberChartConfig;
  private List<DisplayName> names;
  @JsonProperty(access = Access.READ_ONLY)
  private String name;
  private List<DisplayName> descriptions;
  @JsonProperty(access = Access.READ_ONLY)
  private String description;
  @JsonProperty(access = Access.READ_ONLY)
  private List<Entry<String, String>> additionalConfig;
  private String icon;
  @JsonIgnore
  private String locale = Ivy.session().getFormattingLocale().toString();
  @JsonInclude(value = Include.NON_NULL)
  private String manipulateValueBy;
  @JsonInclude(value = Include.NON_NULL)
  private List<String> backgroundColor;
  
  @JsonIgnore
  private List<SecurityMemberDTO> permissionDTOs;

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

  public Long getRefreshInterval() {
    return refreshInterval;
  }

  public void setRefreshInterval(Long refreshInterval) {
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

public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
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
    return LanguageUtils.getLocalizedName(names, name);
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
    return LanguageUtils.getLocalizedName(descriptions, description);
  }

  public void setDescription(String description) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(descriptions, description);
    this.descriptions = nameResult.names();
    this.description = nameResult.name();
  }

  public List<Entry<String, String>> getAdditionalConfig() {
    return additionalConfig;
  }

  public void setAdditionalConfig(List<Entry<String, String>> additionalConfig) {
    this.additionalConfig = additionalConfig;
  }
  
  public String getLocale() {
    return this.locale;
  }

  public String getManipulateValueBy() {
    return manipulateValueBy;
  }

  public void setManipulateValueBy(String manipulateValueBy) {
    this.manipulateValueBy = manipulateValueBy;
  }

  public List<String> getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(List<String> backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public List<SecurityMemberDTO> getPermissionDTOs() {
    return permissionDTOs;
  }

  public void setPermissionDTOs(List<SecurityMemberDTO> permissionDTOs) {
    this.permissionDTOs = permissionDTOs;
  }
}
