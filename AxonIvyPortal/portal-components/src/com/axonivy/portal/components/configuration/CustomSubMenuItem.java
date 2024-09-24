package com.axonivy.portal.components.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomSubMenuItem {
  private String link;
  private String icon;
  private String label;
  private Integer index;

  @JsonProperty("isExternal")
  private Boolean isExternalLink; 

  private String version;

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public Boolean getIsExternalLink() {
    return isExternalLink;
  }

  public void setIsExternalLink(Boolean isExternalLink) {
    this.isExternalLink = isExternalLink;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

}