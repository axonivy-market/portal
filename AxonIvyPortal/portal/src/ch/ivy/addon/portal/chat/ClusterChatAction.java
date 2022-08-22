package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portal.chat.ChatReferencesContainer.getChatService;

import java.util.function.Consumer;

public enum ClusterChatAction implements Consumer<ClusterChatEventParameter> {

  // Action name must have prefix that defined by constant CLUSTER_CHAT_ACTION_PREFIX
  PORTAL_CHAT_SEND_PRIVATE_MESSAGE((param) -> {
    getChatService().performSendingPrivateMessage(param.getMessageText(), param.getReceiver(), param.getClientId(),
        param.getActor(), param.getNodeName());
  }), PORTAL_CHAT_SEND_GROUP_MESSAGE((param) -> {
    getChatService().performSendingGroupMessage(param.getMessageText(), param.getReceiver(), param.getClientId(),
        param.getActor(), param.getNodeName());
  }), PORTAL_CHAT_UPDATE_GROUP_LIST((param) -> {
    getChatService().performUpdatingGroupList(param.getGroupChat());
  }), PORTAL_CHAT_UPDATE_USER_STATUS((param) -> {
    getChatService().performUpdatingUserStatus(param.getActor(), param.isOnline());
  }), PORTAL_CHAT_HANDLE_USER_OFFLINE((param) -> {
    getChatService().performHandlingUserOffline((param.getActor()));
  }), PORTAL_CHAT_READ_MESSAGE((param) -> {
    getChatService().performReadingMessage(param.getParticipant(), param.getClientId(), param.getActor());
  }), PORTAL_CHAT_READ_GROUP_MESSAGE((param) -> {
    getChatService().performReadingGroupMessage(param.getParticipant(), param.getClientId(), param.getActor());
  });

  public static final String CLUSTER_CHAT_ACTION_PREFIX = "PORTAL_CHAT_";
  private Consumer<ClusterChatEventParameter> consumer;

  private ClusterChatAction(Consumer<ClusterChatEventParameter> consumer) {
    this.consumer = consumer;
  }

  @Override
  public void accept(ClusterChatEventParameter param) {
    consumer.accept(param);
  }
}
