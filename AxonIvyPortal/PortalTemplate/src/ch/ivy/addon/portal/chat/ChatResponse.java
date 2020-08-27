package ch.ivy.addon.portal.chat;

import java.util.Date;

public class ChatResponse {
  private String id;
  private String action;
  private String status;
  private Object content;
  transient private String clientId;

  private ChatResponse() {
    id = String.valueOf(new Date().getTime());
    status = "CHAT_LONG_POLLING_REQUEST";
  }

  public ChatResponse(String action, Object content) {
    this();
    this.action = action;
    this.content = content;
  }

  public ChatResponse(String action, Object content, String clientId) {
    this();
    this.action = action;
    this.content = content;
    this.clientId = clientId;
  }

  public ChatResponse(String status) {
    this();
    this.status = status;
  }

  public String getId() {
    return id;
  }

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

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

}
