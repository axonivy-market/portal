package com.axonivy.portal.dto.definition;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.service.ProjectVersionService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"$schema", "version", "items"})
public abstract class PortalDefinition<T> {
  public static final String SCHEMA_BASE_URL = "https://axonivy-market.github.io/portal/schemas/";

  @JsonProperty(required = true, value = "$schema")
  private String schema;
  @JsonProperty(required = true)
  private String version = "14.0.0";
  @JsonProperty(required = true)
  private List<T> items;

  public PortalDefinition() {};

  public PortalDefinition(String schema, List<T> items) {
    this.schema = schemaUrl(schema);
    this.items = items;
  }

  public String getSchema() {
    return schema;
  }

  public String getVersion() {
    return version;
  }


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
    return SCHEMA_BASE_URL + getPortalVersion() + "/" + fileName;
  }
}
