package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portal.chat.ChatReferencesContainer.wf;
import static ch.ivy.addon.portal.chat.ClusterChatAction.PORTAL_CHAT_HANDLE_USER_OFFLINE;
import static ch.ivy.addon.portal.chat.ClusterChatAction.PORTAL_CHAT_READ_GROUP_MESSAGE;
import static ch.ivy.addon.portal.chat.ClusterChatAction.PORTAL_CHAT_READ_MESSAGE;
import static ch.ivy.addon.portal.chat.ClusterChatAction.PORTAL_CHAT_SEND_PRIVATE_MESSAGE;
import static ch.ivy.addon.portal.chat.ClusterChatAction.PORTAL_CHAT_UPDATE_GROUP_LIST;
import static ch.ivy.addon.portal.chat.ClusterChatAction.PORTAL_CHAT_UPDATE_USER_STATUS;
import static ch.ivy.addon.portal.chat.ClusterChatEventParameter.forReadMessage;
import static ch.ivy.addon.portal.chat.ClusterChatEventParameter.forSendMessage;
import static ch.ivy.addon.portal.chat.ClusterChatEventParameter.forUpdateGroupList;
import static ch.ivy.addon.portal.chat.ClusterChatEventParameter.forUpdateUserStatus;
import static ch.ivyteam.ivy.event.SystemEventCategory.THIRD_PARTY;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.event.SystemEvent;

public class ClusterChatEventSender {


  public static void sendPrivateMessage(String messageText, String receiver, String clientId, String nodeName) {
    ClusterChatEventParameter param = forSendMessage(messageText, receiver, clientId, sessionUserName(), nodeName);
    broadcastClusterChat(PORTAL_CHAT_SEND_PRIVATE_MESSAGE, param);
  }

  public static void sendGroupMessage(String messageText, String caseId, String clientId, String nodeName) {
    ClusterChatEventParameter param = forSendMessage(messageText, caseId, clientId, sessionUserName(), nodeName);
    broadcastClusterChat(ClusterChatAction.PORTAL_CHAT_SEND_GROUP_MESSAGE, param);
  }

  public static void updateGroupList(GroupChat groupChat) {
    ClusterChatEventParameter param = forUpdateGroupList(groupChat);
    broadcastClusterChat(PORTAL_CHAT_UPDATE_GROUP_LIST, param);
  }

  public static void handleUserOnline(String username) {
    ClusterChatEventParameter param = forUpdateUserStatus(username, true);
    broadcastClusterChat(PORTAL_CHAT_UPDATE_USER_STATUS, param);
  }

  public static void handleUserOffline(String username) {
    ClusterChatEventParameter param = forUpdateUserStatus(username, false);
    broadcastClusterChat(PORTAL_CHAT_HANDLE_USER_OFFLINE, param);
  }

  public static void readMessage(String participant, String clientId, String actor) {
    ClusterChatEventParameter param = forReadMessage(participant, clientId, actor);
    broadcastClusterChat(PORTAL_CHAT_READ_MESSAGE, param);
  }

  public static void readGroupMessage(String caseId, String clientId, String sender) {
    ClusterChatEventParameter param = forReadMessage(caseId, clientId, sender);
    broadcastClusterChat(PORTAL_CHAT_READ_GROUP_MESSAGE, param);
  }

  private static void broadcastClusterChat(ClusterChatAction action, ClusterChatEventParameter param) {
    wf().getApplication().sendSystemEvent(new SystemEvent<>(THIRD_PARTY, action.name(), param));
  }

  private static String sessionUserName() {
    return Ivy.session().getSessionUserName();
  }


}
