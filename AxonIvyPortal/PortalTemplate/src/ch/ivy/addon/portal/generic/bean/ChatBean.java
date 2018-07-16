package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ch.ivy.addon.portal.chat.ChatContact;
import ch.ivy.addon.portal.chat.ChatContactManager;
import ch.ivy.addon.portal.chat.ChatMessageManager;
import ch.ivy.addon.portal.chat.Message;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ChatBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private List<ChatContact> contacts;
  private List<Message> messages;

  @PostConstruct
  public void init() {
    username = Ivy.session().getSessionUserName();
    messages = new ArrayList<>();
    contacts = ChatContactManager.loadOnlineContacts();
  }

  public void loadPreviousConversation() {
    List<String> participants = new ArrayList<>();
    participants.add(username);
    Map<String, String> params = getRequestParameterMap();
    participants.addAll(toList(params.get("recipients")));
    messages = ChatMessageManager.loadMessages(participants);
  }

  private Map<String, String> getRequestParameterMap() {
    return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
  }

  private List<String> toList(String string) {
    List<String> recipients = new ArrayList<>();
    recipients.add(string);
    return recipients;
  }

  public List<ChatContact> getContacts() {
    return contacts;
  }

  public String getUsername() {
    return username;
  }

  public List<Message> getMessages() {
    return messages;
  }
}
