package ch.ivy.addon.portal.chat;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;

public final class ConcurrentChatUtils {
  private static final String PORTAL_CHAT_RESPONSE_HISTORY_GROUP = "PortalChatResponseHistory";
  private static final int RECENT_HISTORY_SIZE = 20;

  private ConcurrentChatUtils() {}

  @SuppressWarnings("unchecked")
  public static Deque<ChatResponse> getRecentChatResponseHistory(String username) {
    return Sudo.get(() -> {
      IDataCache appCache = Ivy.datacache().getAppCache();
      IDataCacheEntry cacheEntry = appCache.getEntry(PORTAL_CHAT_RESPONSE_HISTORY_GROUP, username);
      Deque<ChatResponse> history = null;
      if (cacheEntry != null && cacheEntry.isValid()) {
        history = (Deque<ChatResponse>) cacheEntry.getValue();
      }
      if (history == null) {
        history = new ConcurrentLinkedDeque<>();
        appCache.setEntry(PORTAL_CHAT_RESPONSE_HISTORY_GROUP, username,
            com.axonivy.portal.components.service.IvyCacheService.MAX_TIMEOUT, history);
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
      IDataCache appCache = Ivy.datacache().getAppCache();
      IDataCacheGroup group = appCache.getGroup(PORTAL_CHAT_RESPONSE_HISTORY_GROUP);
      if (group != null) {
        IDataCacheEntry entry = appCache.getEntry(PORTAL_CHAT_RESPONSE_HISTORY_GROUP, username);
        if (entry != null) {
          appCache.invalidateEntry(group, entry);
        }
      }
      return null;
    });
  }

}
