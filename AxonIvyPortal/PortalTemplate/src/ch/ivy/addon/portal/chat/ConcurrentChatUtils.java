package ch.ivy.addon.portal.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISession;

public final class ConcurrentChatUtils {
  public static final String PORTAL_CHAT_MESSAGE_QUEUE = "PortalChatMessageQueue_%s";

  private ConcurrentChatUtils() {}

  @SuppressWarnings("unchecked")
  public static Queue<ChatMessage> getPortalChatMessageQueue(String username) {
    return (Queue<ChatMessage>) Ivy.wf().getApplication().getAttribute(String.format(PORTAL_CHAT_MESSAGE_QUEUE, username));
  }

  public static Queue<ChatMessage> getPortalChatMessageQueueOrInitIfNull(String username) {
    Queue<ChatMessage> queue = getPortalChatMessageQueue(username);
    if (queue == null) {
      queue = new ConcurrentLinkedQueue<>();
      setPortalChatMessageQueue(username, queue);
    }
    return queue;
  }

  public static void setPortalChatMessageQueue(String username,Queue<ChatMessage> queue) {
    Ivy.wf().getApplication().setAttribute(String.format(PORTAL_CHAT_MESSAGE_QUEUE, username), queue);
  }
  
  public static void removePortalChatMessageQueue(String username) {
    Ivy.wf().getApplication().removeAttribute(String.format(PORTAL_CHAT_MESSAGE_QUEUE, username));
  }
  
  public static boolean isUserOnline(String username) {
    return Ivy.wf().getSecurityContext().getSessions().stream().anyMatch(session -> session.getSessionUserName().equals(username));
  }

  /**
   * Gets map [username, ISession] of all active sessions 
   * @return map [username, ISession] of all active sessions
   */
  public static Map<String, ISession> getUserNameToSession() {
    Map<String, ISession> map = new HashMap<>();
    for (ISession session : Ivy.wf().getSecurityContext().getSessions()) {
      map.put(session.getSessionUserName(), session);
    }
    return map;
  }


}
