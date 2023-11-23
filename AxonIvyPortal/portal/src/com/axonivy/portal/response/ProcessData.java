package com.axonivy.portal.response;

import org.apache.commons.lang.StringUtils;

import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessData {
  private String id;
  private String name;
  private String description;
  private String icon;
  private String link;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProcessData() {
  }

  public ProcessData(IWebStartable process) {
    this.id = process.getId();
    this.name = process.getName();
    this.description = process.getDescription();
    this.link = process.getLink().getAbsolute();
    this.icon = StringUtils.defaultIfBlank(process.customFields().value("cssIcon"), "si si si-hierarchy-6 si-rotate-270");
  }
}
