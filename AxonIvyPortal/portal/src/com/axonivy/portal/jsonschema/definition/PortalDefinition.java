package com.axonivy.portal.jsonschema.definition;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public abstract class PortalDefinition<T> {
  private String version;
  private List<T> items;

  @JsonProperty(required = true)
  @JsonPropertyDescription("The Portal version")
  public String getVersion() {
    return version;
  }

  @JsonProperty(required = true)
  public List<T> getItems() {
    return items;
  };
}
