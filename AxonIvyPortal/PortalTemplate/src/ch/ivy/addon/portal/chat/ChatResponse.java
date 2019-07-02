package ch.ivy.addon.portal.chat;

public class ChatResponse {
  private String action;
  private String status;
  private Object content;

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public Object getContent() {
    return content;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setContent(Object content) {
    this.content = content;
  }

}
