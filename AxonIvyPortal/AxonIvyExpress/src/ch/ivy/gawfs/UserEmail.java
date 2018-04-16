package ch.ivy.gawfs;

import java.util.List;

import ch.ivy.addon.portalkit.dto.AttachmentDTO;

public class UserEmail {
  private String recipients;
  private String responseTo;
  private String subject;
  private String content;
  private List<AttachmentDTO> attachments;
  
  public String getRecipients() {
    return recipients;
  }
  public void setRecipients(String recipients) {
    this.recipients = recipients;
  }
  public String getResponseTo() {
    return responseTo;
  }
  public void setResponseTo(String responseTo) {
    this.responseTo = responseTo;
  }
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public List<AttachmentDTO> getAttachments() {
    return attachments;
  }
  public void setAttachments(List<AttachmentDTO> attachments) {
    this.attachments = attachments;
  }
  
  
}
