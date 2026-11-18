package com.axonivy.portal.dto.jsondefinition;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.JsonListWrapper;
import com.axonivy.portal.service.ProjectVersionService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

@JsonPropertyOrder({"$schema", "version", "items"})
public abstract class PortalJsonDefinition<T> extends JsonListWrapper<T> {

  @JsonProperty(required = true, value = "$schema")
  private String schema;

  public PortalJsonDefinition() {};
  
  public PortalJsonDefinition(String schemaFile, List<T> items) {
    super(JsonListWrapper.FORMAT_VERSION, items);
    schema = schemaUrl(schemaFile);
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
    return schemaBaseUrl() + getPortalVersion() + "/" + fileName;
  }

  private String schemaBaseUrl() {
    String baseUrl = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.SCHEMA_BASE_URL);
    if (StringUtils.isBlank(baseUrl)) {
      baseUrl = GlobalVariable.SCHEMA_BASE_URL.getDefaultValue();
    }
    return baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
  }
}
