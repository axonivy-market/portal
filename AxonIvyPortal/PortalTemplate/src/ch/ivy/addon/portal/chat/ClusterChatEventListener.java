package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portal.chat.ClusterChatAction.CLUSTER_CHAT_ACTION_PREFIX;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.event.ISystemEventListener;
import ch.ivyteam.ivy.event.SystemEvent;
import ch.ivyteam.ivy.event.SystemEventCategory;
import ch.ivyteam.util.threadcontext.IvyAsyncRunner;

public class ClusterChatEventListener implements ISystemEventListener {
  private final IvyAsyncRunner asyncRunner;
  private static List<ClusterChatEventListener> listeners = new ArrayList<>();

  private ClusterChatEventListener() {
    asyncRunner = new IvyAsyncRunner();
  }

  @Override
  public void handleSystemEvent(SystemEvent<?> event) {
    asyncRunner.run(() -> {
      IvyExecutor.executeAsSystem(() -> {
        String eventName = event.getName();
        if (StringUtils.isNotBlank(eventName) && eventName.contains(CLUSTER_CHAT_ACTION_PREFIX)
            && ChatServiceContainer.getChatService() != null) {
          Ivy.log().warn("handleSystemEvent Portal Chat {0}", eventName);
          ClusterChatEventParameter parameter = (ClusterChatEventParameter) event.getParameter();
          ClusterChatAction.valueOf(eventName).accept(parameter);
        }
        return "";
      });

    });
  }

  public static void register() {
    clearAllListeners();
    ClusterChatEventListener ClusterChatEventListener = new ClusterChatEventListener();
    ClusterChatEventListener.subscribeToSystemEvents();
  }

  private void subscribeToSystemEvents() {
    Ivy.wf().getApplication().addSystemEventListener(EnumSet.of(SystemEventCategory.THIRD_PARTY), this);
    listeners.add(this);
  }

  private void unsubscribeToSystemEvents() {
    Ivy.wf().getApplication().removeSystemEventListener(EnumSet.of(SystemEventCategory.THIRD_PARTY), this);
  }

  private static void clearAllListeners() {
    for (ClusterChatEventListener listener : listeners) {
      listener.unsubscribeToSystemEvents();
    }
    listeners.clear();
  }

}
