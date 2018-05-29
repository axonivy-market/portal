package ch.ivy.addon.portal.chat;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/portalchat/{user}")
@Singleton
public class ChatResource {

  private Logger logger = Logger.getLogger(ChatResource.class);

  @PathParam("user")
  private String user;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public static final String PORTAL_CHAT_CHANNEL = "/portalchat";

  @OnOpen
  public void onOpen(RemoteEndpoint r, EventBus eventBus) {
    if (user == null) {
      user = r.pathSegments(2);
    }

    try {
      ChatContactManager.setContactOnline(user);
    } catch (IOException e) {
      logger.warn("Could not set user online.", e);
    }

    if (eventBus == null) {
      eventBus = EventBusFactory.getDefault().eventBus();
    }
    if (ChatContactManager.getNumberOfOnlineInstance(user) == 1) {
      eventBus.publish(PORTAL_CHAT_CHANNEL + "/*", encode(new Command(Command.ADD_CONTACT, user)));
    }
  }

  @OnClose
  public void onClose(RemoteEndpoint r, EventBus eventBus) {
    if (user == null) {
      user = r.pathSegments(2);
    }

    try {
      ChatContactManager.setContactOffline(user);
    } catch (IOException e) {
      logger.warn("Could not set user offline.", e);
    }

    if (eventBus == null) {
      eventBus = EventBusFactory.getDefault().eventBus();
    }
    if (ChatContactManager.getNumberOfOnlineInstance(user) == 0) {
      eventBus.publish(PORTAL_CHAT_CHANNEL + "/*", encode(new Command(Command.REMOVE_CONTACT, user)));
    }
  }

  @OnMessage
  public String onMessage(String message) {
    return message;
  }

  private String encode(Object object) {
    return new JSONEncoder().encode(object);
  }
}
