package ch.ivy.addon.portal.chat;

public class Command {

  public static final String REMOVE_CONTACT = "REMOVE_CONTACT";
  public static final String ADD_CONTACT = "ADD_CONTACT";

  private String action;
  private String data;

  public Command(String action, String data) {
    this.action = action;
    this.data = data;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
