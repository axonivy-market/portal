package com.axonivy.portal.component.ivydata.bo;

import com.axonivy.portal.component.enums.DocumentType;
import ch.ivyteam.ivy.workflow.document.ModificationInfo;

public class IvyDocument {
  private String id;
  private String name;
  private String path;
  private Long size;
  private String contentType;
  private ModificationInfo creation;
  private ModificationInfo lastModification;
  private DocumentType type;

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

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Long getSize() {
    return size;
  }
  
  public void setSize(Long size) {
    this.size = size;
  }
  
  public String getUserFriendlySize() {
    return getSize() < 1024 ? "1KB" : getSize() / 1024 + "KB";
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public ModificationInfo getLastModification() {
    return lastModification;
  }

  public void setLastModification(ModificationInfo lastModification) {
    this.lastModification = lastModification;
  }

  public ModificationInfo getCreation() {
    return creation;
  }

  public void setCreation(ModificationInfo creation) {
    this.creation = creation;
  }

  public DocumentType getType() {
    return type;
  }

  public void setType(DocumentType type) {
    this.type = type;
  }

}
