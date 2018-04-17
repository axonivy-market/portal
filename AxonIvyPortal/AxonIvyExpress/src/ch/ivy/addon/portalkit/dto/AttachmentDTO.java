package ch.ivy.addon.portalkit.dto;

import ch.ivy.gawfs.enums.EmailAttachmentStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AttachmentDTO {
  private String name;
  @JsonIgnore
  private byte[] content;
  private String size;
  private String path;
  private EmailAttachmentStatus status;

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

  public EmailAttachmentStatus getStatus() {
    return status;
  }

  public void setStatus(EmailAttachmentStatus status) {
    this.status = status;
  }

}
