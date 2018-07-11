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
import ch.ivy.addon.portal.chat.ChatResource;
import ch.ivy.addon.portal.chat.Message;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ChatBean implements Serializable {

  private static final long serialVersionUID = 1L;
//  private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

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

  public void handleChatMessageSending() {
    Message message = constructMessageFromRequestParameter();
    publish(message);
    saveToPersistence(message);
  }

  private Map<String, String> getRequestParameterMap() {
    return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
  }

  private List<String> toList(String string) {
    List<String> recipients = new ArrayList<>();
    recipients.add(string);
    return recipients;
  }

  private void publish(Message message) {
    for (String recipient : message.getRecipients()) {
      String chanel = ChatResource.PORTAL_CHAT_CHANNEL + "/" + recipient;
//      eventBus.publish(chanel, message);
    }
  }

  private Message constructMessageFromRequestParameter() {
    Map<String, String> params = getRequestParameterMap();
    Message message = new Message();
    message.setSender(params.get("sender"));
    message.setContent(params.get("content"));
    List<String> recipients = toList(params.get("recipients"));
    message.setRecipients(recipients);
    message.setTimestamp(params.get("timestamp"));
    return message;
  }

  private void saveToPersistence(Message message) {
    ChatMessageManager.saveMessage(message);
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
