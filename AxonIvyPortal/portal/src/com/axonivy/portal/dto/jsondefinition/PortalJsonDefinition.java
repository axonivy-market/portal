package com.axonivy.portal.dto.jsondefinition;

import java.util.List;

import com.axonivy.portal.components.dto.JsonListWrapper;
import com.axonivy.portal.enums.PortalSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ch.ivy.addon.portalkit.util.PortalSchemaUtils;

@JsonPropertyOrder({"$schema", "version", "items"})
public abstract class PortalJsonDefinition<T> extends JsonListWrapper<T> {

  public PortalJsonDefinition() {}

  public PortalJsonDefinition(PortalSchema portalSchema, List<T> items) {
    super(JsonListWrapper.FORMAT_VERSION, items);
    setSchema(PortalSchemaUtils.schemaUrl(portalSchema.getFileName()));
  }

  public PortalJsonDefinition(String schemaFile, List<T> items) {
    super(JsonListWrapper.FORMAT_VERSION, items);
    setSchema(PortalSchemaUtils.schemaUrl(schemaFile));
  }

  @Override
  @JsonProperty(required = true, value = "$schema")
  public String getSchema() {
    return super.getSchema();
  }

  @Override
  @JsonProperty(required = true)
  public String getVersion() {
    return super.getVersion();
  }

  @Override
  @JsonProperty(required = true)
  public List<T> getItems() {
    return super.getItems();
  }
}
