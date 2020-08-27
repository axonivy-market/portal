package ch.ivy.addon.portal.chat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The message being sent over the wire by the {@link ChatService}.
 * 
 * @since 7.3.0
 */
public class ChatMessage {
  private String sender;
  private List<String> recipients;
  private Date sentDate;
  private String message;

  public ChatMessage() { // for decode
  }

  // For case group chat
  public ChatMessage(String sender, String message, String caseId) {
    this.sender = sender;
    this.sentDate = new Date();
    this.message = message;
    this.recipients = Arrays.asList("Case-" + caseId);
  }

  public ChatMessage(String sender, List<String> recipients, String message) {
    this.sender = sender;
    this.recipients = recipients;
    this.sentDate = new Date();
    this.message = message;
  }

  public ChatMessage(String sender, List<String> recipients) {
    this.sender = sender;
    this.recipients = recipients;
  }

  public ChatMessage copy() {
    ChatMessage clonedMessage = new ChatMessage();
    clonedMessage.setSender(sender);
    clonedMessage.setSentDate(sentDate);
    clonedMessage.setMessage(message);
    clonedMessage.setRecipients(recipients);
    return clonedMessage;
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

  public Date getSentDate() {
    return sentDate;
  }

  public void setSentDate(Date sentDate) {
    this.sentDate = sentDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "@" + sender + ">@{" + String.join(",", recipients) + "}: " + message;
  }
}
