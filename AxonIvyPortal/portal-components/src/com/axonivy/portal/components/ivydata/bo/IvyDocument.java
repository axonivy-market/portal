package com.axonivy.portal.components.ivydata.bo;

import com.axonivy.portal.components.enums.DocumentType;

import ch.ivyteam.ivy.workflow.document.ModificationInfo;

public class IvyDocument {
  private String uuid;
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

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public static Builder builder() {
    return new Builder();
  }
  public static class Builder{
    private IvyDocument ivyDocument;
    private Builder() {
      ivyDocument = new IvyDocument();
    }

    public Builder uuid(String uuid) {
      ivyDocument.uuid = uuid;
      return this;
    }
    
    public Builder id(String id) {
      ivyDocument.id = id;
      return this;
    }
    
    public Builder name(String name) {
      ivyDocument.name = name;
      return this;
    }
    
    public Builder path(String path) {
      ivyDocument.path = path;
      return this;
    }
    
    public Builder size(Long size) {
      ivyDocument.size = size;
      return this;
    }
    
    public Builder contentType(String contentType) {
      ivyDocument.contentType = contentType;
      return this;
    }
    
    public Builder creation(ModificationInfo creation) {
      ivyDocument.creation = creation;
      return this;
    }
    
    public Builder lastModification(ModificationInfo lastModification) {
      ivyDocument.lastModification = lastModification;
      return this;
    }
    
    public Builder type(DocumentType type) {
      ivyDocument.type = type;
      return this;
    }
    
    public IvyDocument create() {
      return ivyDocument;
    }
  }
}
