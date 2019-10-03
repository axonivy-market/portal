package ch.ivy.addon.portal.chat;

public class SessionAttributeInfo {

  private int sessionId;
  private String uuid;

  public SessionAttributeInfo() {
    super();
  }

  public SessionAttributeInfo(int sessionId, String uuid) {
    super();
    this.sessionId = sessionId;
    this.uuid = uuid;
  }

  public int getSessionId() {
    return sessionId;
  }

  public void setSessionId(int sessionId) {
    this.sessionId = sessionId;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }



}
