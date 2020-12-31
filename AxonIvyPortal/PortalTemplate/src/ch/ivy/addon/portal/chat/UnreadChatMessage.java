package ch.ivy.addon.portal.chat;

public class UnreadChatMessage {
  private String senderId;

  public UnreadChatMessage() { // for decode
  }

  public UnreadChatMessage(String senderId) {
    this.senderId = senderId;
  }

  public String getSenderId() {
    return senderId;
  }

  public void setSenderId(String senderId) {
    this.senderId = senderId;
  }

  @Override
  public String toString() {
    return "From " + senderId;
  }
}
