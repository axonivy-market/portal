package com.axonivy.portal.jsonschema.definition;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"$schema", "version", "items"})
public abstract class PortalDefinition<T> {
  private String schema;
  private String version;
  private List<T> items;

  @JsonProperty(required = true, value = "$schema")
  public String getSchema() {
    return schema;
  }

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
