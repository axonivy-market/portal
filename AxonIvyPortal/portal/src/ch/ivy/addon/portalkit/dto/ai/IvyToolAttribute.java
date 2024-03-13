package ch.ivy.addon.portalkit.dto.ai;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class IvyToolAttribute implements Serializable {

  private static final long serialVersionUID = -2291917200620175108L;

  private String name;
  private String value;
  private String description;
  private boolean isRequired;

  public IvyToolAttribute() {
  }

  public IvyToolAttribute(String name, String value, String description) {
    this.name = name;
    this.value = value;
    this.description = description;
  }

  public IvyToolAttribute(String name, String description, boolean isRequired) {
    this.name = name;
    this.description = description;
    this.isRequired = isRequired;
    this.value = "";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isRequired() {
    return isRequired;
  }

  public void setRequired(boolean isRequired) {
    this.isRequired = isRequired;
  }
}
