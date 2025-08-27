package com.axonivy.portal.components.configuration;

import java.util.List;

import com.axonivy.portal.components.dto.DisplayName;
import com.axonivy.portal.components.enums.MenuKind;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomSubMenuItem {
  private MenuKind menuKind;
  private String link;
  private String icon;
  private String label;
  private Integer index;

  private List<DisplayName> titles;

  @JsonIgnore
  private String id;

  @JsonProperty("isExternal")
  private Boolean isExternalLink; 

  private String version;

  public MenuKind getMenuKind() {
    return menuKind;
  }

  public void setMenuKind(MenuKind menuKind) {
    this.menuKind = menuKind;
  }

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

  public List<DisplayName> getTitles() {
    return titles;
  }

  public void setTitles(List<DisplayName> titles) {
    this.titles = titles;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}