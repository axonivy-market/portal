package ch.ivy.addon.portalkit.dto;


import ch.ivy.addon.portalkit.enums.ExpressEmailAttachmentStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExpressAttachment {
  private String name;
  @JsonIgnore
  private byte[] content;
  private String size;
  private String path;
  private ExpressEmailAttachmentStatus status;
  private String contentType;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public ExpressEmailAttachmentStatus getStatus() {
    return status;
  }

  public void setStatus(ExpressEmailAttachmentStatus status) {
    this.status = status;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
}
