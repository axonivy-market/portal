package ch.ivy.addon.portalkit.bo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.ExpressAttachment;

public class ExpressUserEmail {
  private String recipients;
  private String responseTo;
  private String subject;
  private String content;
  private List<ExpressAttachment> attachments;

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

  public List<ExpressAttachment> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<ExpressAttachment> attachments) {
    this.attachments = attachments;
  }
  
  public boolean isEmpty() {
    return StringUtils.isEmpty(this.subject) || StringUtils.isEmpty(this.content);
  }

}
