package ch.ivy.addon.portal.chat;

import java.util.List;

public class Message {

  private String sender;
  private String content;
  private List<String> recipients;
  private String timestamp;

  public Message() {
    super();
  }

  public Message(String sender, String content, List<String> recipients, String timestamp) {
    super();
    this.sender = sender;
    this.content = content;
    this.recipients = recipients;
    this.timestamp = timestamp;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public List<String> getRecipients() {
    return recipients;
  }

  public void setRecipients(List<String> recipients) {
    this.recipients = recipients;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
}
