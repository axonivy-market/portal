package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portal.chat.ChatReferencesContainer.getApplication;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

import ch.ivy.addon.portalkit.util.IvyExecutor;

public final class ConcurrentChatUtils {
  public static final String PORTAL_CHAT_RESPONSE_HISTORY = "PortalChatResponseHistory_%s";
  private static final int RECENT_HISTORY_SIZE = 20;

  private ConcurrentChatUtils() {}

  @SuppressWarnings("unchecked")
  public static Deque<ChatResponse> getRecentChatResponseHistory(String username) {
    return IvyExecutor.executeAsSystem(() -> {
      Deque<ChatResponse> history =
          (Deque<ChatResponse>) getApplication().getAttribute(String.format(PORTAL_CHAT_RESPONSE_HISTORY, username));
      if (history == null) {
        history = new ConcurrentLinkedDeque<>();
        getApplication().setAttribute(String.format(PORTAL_CHAT_RESPONSE_HISTORY, username), history);
      }
      if (history.size() > RECENT_HISTORY_SIZE) {
        int numberOfEntriesToRemove = history.size() - RECENT_HISTORY_SIZE;
        for (int i = 0; i < numberOfEntriesToRemove; i++) {
          history.pollFirst();
        }
      }
      return history;
    });
  }

  public static void removePortalChatResponseHistory(String username) {
    IvyExecutor.executeAsSystem(() -> {
      getApplication().removeAttribute(String.format(PORTAL_CHAT_RESPONSE_HISTORY, username));
      return null;
    });
  }

}
