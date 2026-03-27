package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portalkit.constant.IvyCacheIdentifier.PORTAL_CHAT_RESPONSE_HISTORY_CACHE_GROUP_NAME;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

import ch.ivy.addon.portalkit.service.IvyCacheService;
import ch.ivyteam.ivy.security.exec.Sudo;

public final class ConcurrentChatUtils {
  private static final int RECENT_HISTORY_SIZE = 20;

  private ConcurrentChatUtils() {}

  @SuppressWarnings("unchecked")
  public static Deque<ChatResponse> getRecentChatResponseHistory(String username) {
    return Sudo.get(() -> {
      IvyCacheService cacheService = IvyCacheService.getInstance();
      Deque<ChatResponse> history = (Deque<ChatResponse>) cacheService
          .getApplicationCache(PORTAL_CHAT_RESPONSE_HISTORY_CACHE_GROUP_NAME, username);
      if (history == null) {
        history = new ConcurrentLinkedDeque<>();
        cacheService.setApplicationCache(PORTAL_CHAT_RESPONSE_HISTORY_CACHE_GROUP_NAME, username, history);
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
    Sudo.get(() -> {
      IvyCacheService.getInstance()
          .invalidateApplicationCacheEntry(PORTAL_CHAT_RESPONSE_HISTORY_CACHE_GROUP_NAME, username);
      return null;
    });
  }

}
