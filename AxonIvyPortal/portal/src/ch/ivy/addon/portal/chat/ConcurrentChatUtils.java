package ch.ivy.addon.portal.chat;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Manages per-user chat response history for long-polling deduplication and reconnection replay.
 *
 * Previously stored in application-level attributes via IApplication.getAttribute/setAttribute.
 * Now uses a static ConcurrentHashMap — this data is ephemeral (lost on engine restart) and
 * does not need to be tied to any container. A static map is simpler and avoids the deprecated
 * IApplication API while keeping the same thread-safety guarantees.
 */
public final class ConcurrentChatUtils {
  public static final String PORTAL_CHAT_RESPONSE_HISTORY = "PortalChatResponseHistory_%s";
  private static final int RECENT_HISTORY_SIZE = 20;

  // Replaced getApplication().getAttribute/setAttribute with a static ConcurrentHashMap.
  // This is security-system-scoped in-memory storage — ISecurityContext has no attribute API,
  // and application-level storage was unnecessary for this ephemeral reconnection data.
  private static final Map<String, Deque<ChatResponse>> RESPONSE_HISTORY_MAP = new ConcurrentHashMap<>();

  private ConcurrentChatUtils() {}

  public static Deque<ChatResponse> getRecentChatResponseHistory(String username) {
    String key = String.format(PORTAL_CHAT_RESPONSE_HISTORY, username);
    Deque<ChatResponse> history = RESPONSE_HISTORY_MAP.computeIfAbsent(key, k -> new ConcurrentLinkedDeque<>());
    if (history.size() > RECENT_HISTORY_SIZE) {
      int numberOfEntriesToRemove = history.size() - RECENT_HISTORY_SIZE;
      for (int i = 0; i < numberOfEntriesToRemove; i++) {
        history.pollFirst();
      }
    }
    return history;
  }

  public static void removePortalChatResponseHistory(String username) {
    String key = String.format(PORTAL_CHAT_RESPONSE_HISTORY, username);
    RESPONSE_HISTORY_MAP.remove(key);
  }

}
