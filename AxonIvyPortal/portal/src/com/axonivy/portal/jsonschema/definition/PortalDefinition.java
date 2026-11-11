package com.axonivy.portal.jsonschema.definition;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.service.ProjectVersionService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"$schema", "version", "items"})
public abstract class PortalDefinition<T> {
  public static final String SCHEMA_BASE_URL = "https://axonivy-market.github.io/portal/schemas/";

  private String schema;
  private String version;
  private List<T> items;

  public PortalDefinition() {};

  public PortalDefinition(String schema, List<T> items) {
    if (this.version == null) {
      this.version = getPortalVersion();
    }
    this.schema = schemaUrl(schema);
    this.items = items;
  }

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

  private String getPortalVersion() {
    String portalVersion = ProjectVersionService.getInstance().getPortalVersion();
    if (StringUtils.isBlank(portalVersion)) {
      throw new IllegalStateException("Could not determine the current Portal version");
    }
    return portalVersion;
  }

  public String schemaUrl(String fileName) {
    return SCHEMA_BASE_URL + this.version + "/" + fileName;
  }
}
