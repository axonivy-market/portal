package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portal.chat.ChatReferencesContainer.getChatService;
import static ch.ivy.addon.portal.chat.ChatReferencesContainer.log;
import static ch.ivy.addon.portal.chat.ChatReferencesContainer.wf;
import static ch.ivy.addon.portal.chat.ClusterChatAction.CLUSTER_CHAT_ACTION_PREFIX;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.event.ISystemEventListener;
import ch.ivyteam.ivy.event.SystemEvent;
import ch.ivyteam.ivy.event.SystemEventCategory;

public class ClusterChatEventListener implements ISystemEventListener {
  private static List<ClusterChatEventListener> listeners = new ArrayList<>();

  private ClusterChatEventListener() {}

  @Override
  public void handleSystemEvent(SystemEvent<?> event) {
    try {
      IvyExecutor.executeAsSystem(() -> {
        String eventName = event.getName();
        if (StringUtils.isNotBlank(eventName) && eventName.contains(CLUSTER_CHAT_ACTION_PREFIX)
            && getChatService() != null) {
          ClusterChatEventParameter parameter = (ClusterChatEventParameter) event.getParameter();
          ClusterChatAction.valueOf(eventName).accept(parameter);
        }
        return "";
      });
    } catch (Exception e) {
      log().error(e);
      throw e;
    }
  }

  public static void register() {
    clearAllListeners();
    ClusterChatEventListener ClusterChatEventListener = new ClusterChatEventListener();
    ClusterChatEventListener.subscribeToSystemEvents();
  }

  private void subscribeToSystemEvents() {
    wf().getApplication().addSystemEventListener(EnumSet.of(SystemEventCategory.THIRD_PARTY), this);
    listeners.add(this);
  }

  private void unsubscribeToSystemEvents() {
    wf().getApplication().removeSystemEventListener(EnumSet.of(SystemEventCategory.THIRD_PARTY), this);
  }

  private static void clearAllListeners() {
    for (ClusterChatEventListener listener : listeners) {
      listener.unsubscribeToSystemEvents();
    }
    listeners.clear();
  }

}
