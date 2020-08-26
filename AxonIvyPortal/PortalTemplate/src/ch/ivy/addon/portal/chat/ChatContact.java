package ch.ivy.addon.portal.chat;

public class ChatContact {

  private String name;
  private boolean isOnline;

  public ChatContact(String name) {
    this.name = name;
  }

  public ChatContact(String name, boolean isOnline) {
    this.name = name;
    this.isOnline = isOnline;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isOnline() {
    return isOnline;
  }

  public void setOnline(boolean isOnline) {
    this.isOnline = isOnline;
  }
}
