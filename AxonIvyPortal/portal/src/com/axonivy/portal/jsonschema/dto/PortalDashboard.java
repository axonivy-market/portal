package com.axonivy.portal.jsonschema.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;


public class PortalDashboard {
  private String version;
  private List<Dashboard> items;
  
  @JsonProperty(required = true)
  @JsonPropertyDescription("The version of Portal")
  public String getVersion() {
    return this.version;
  }

  @JsonProperty(required = true)
  @JsonPropertyDescription("List of dashboard")
  public List<Dashboard> getItems() {
    return this.items;
  }
}
