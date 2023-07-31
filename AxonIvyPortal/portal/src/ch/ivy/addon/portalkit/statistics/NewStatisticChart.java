package ch.ivy.addon.portalkit.statistics;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.dto.DisplayName;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NewStatisticChart extends AbstractConfiguration {
  private List<DisplayName> names;
  private String aggregates;
  private String filter;
  private List<String> permissions;
  private Boolean isCaseFilter;
  @JsonIgnore
  private String name;

  public List<DisplayName> getNames() {
    return names;
  }

  public void setNames(List<DisplayName> names) {
    this.names = names;
  }

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public Boolean getIsCaseFilter() {
    return isCaseFilter;
  }

  public void setIsCaseFilter(Boolean isCaseFilter) {
    this.isCaseFilter = isCaseFilter;
  }

}
