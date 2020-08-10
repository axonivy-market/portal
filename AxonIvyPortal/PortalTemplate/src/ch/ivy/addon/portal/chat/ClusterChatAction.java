package ch.ivy.addon.portal.chat;

import java.util.function.Consumer;

public enum ClusterChatAction implements Consumer<ClusterChatEventParameter> {

  // Action name must have prefix that defined by constant CLUSTER_CHAT_ACTION_PREFIX
  PORTAL_CHAT_SEND_PRIVATE_MESSAGE((param) -> {
    ChatServiceContainer.getChatService().performSendingPrivateMessage(param.getMessageText(), param.getReceiver(),
        param.getClientId(), param.getActor(), param.getNodeId());
  }), PORTAL_CHAT_SEND_GROUP_MESSAGE((param) -> {
    ChatServiceContainer.getChatService().performSendingGroupMessage(param.getMessageText(), param.getReceiver(),
        param.getClientId(), param.getActor(), param.getNodeId());
  }), PORTAL_CHAT_UPDATE_GROUP_LIST((param) -> {
    ChatServiceContainer.getChatService().performUpdatingGroupList(param.getGroupChat());
  }), PORTAL_CHAT_UPDATE_USER_STATUS((param) -> {
    ChatServiceContainer.getChatService().performUpdatingUserStatus(param.getUsername(), param.isOnline());
  }), PORTAL_CHAT_HANDLE_USER_OFFLINE((param) -> {
    ChatServiceContainer.getChatService().performHandlingUserOffline((param.getUsername()));
  }), PORTAL_CHAT_READ_MESSAGE((param) -> {
    ChatServiceContainer.getChatService().performReadingMessage(param.getParticipant(), param.getClientId(),
        param.getActor());
  }), PORTAL_CHAT_READ_GROUP_MESSAGE((param) -> {
    ChatServiceContainer.getChatService().performReadingGroupMessage(param.getParticipant(), param.getClientId(),
        param.getActor());
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
