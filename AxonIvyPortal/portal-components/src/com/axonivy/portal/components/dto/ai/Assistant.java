package com.axonivy.portal.components.dto.ai;

import java.util.List;

import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Assistant {
  private List<AbstractTool> toolkit;
  private String id;
  private String name;

  public void init(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public List<AbstractTool> getToolkit() {
    return toolkit;
  }
  public void setToolkit(List<AbstractTool> toolkit) {
    this.toolkit = toolkit;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String toJson() {
    return BusinessEntityConverter.entityToJsonValue(this);
  }
}
