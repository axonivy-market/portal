package com.axonivy.portal.dto.definition;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.JsonListWrapper;
import com.axonivy.portal.service.ProjectVersionService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"$schema", "version", "items"})
public abstract class PortalDefinition<T> extends JsonListWrapper<T> {
  public static final String SCHEMA_BASE_URL = "https://axonivy-market.github.io/portal/schema/";

  @JsonProperty(required = true, value = "$schema")
  private String schema;

  public PortalDefinition() {};
  
  public PortalDefinition(String schemaFile, List<T> items) {
    super(JsonListWrapper.FORMAT_VERSION, items);
    schema = schemaFile;
  }

  public String getSchema() {
    return schema;
  }

  @Override
  @JsonProperty(required = true)
  public String getVersion() {
    return super.getVersion();
  };

  @Override
  @JsonProperty(required = true)
  public List<T> getItems() {
    return super.getItems();
  }

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
