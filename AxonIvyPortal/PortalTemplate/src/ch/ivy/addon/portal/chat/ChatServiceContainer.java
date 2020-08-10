package ch.ivy.addon.portal.chat;

/**
 * Holds instance of {@link ChatService}
 *
 */
public final class ChatServiceContainer {

  private static ChatService chatService;
  private static boolean isSessionExtentionRegistered;

  private ChatServiceContainer() {}

  public static ChatService getChatService() {
    return chatService;
  }

  public static void setChatService(ChatService chatService) {
    ChatServiceContainer.chatService = chatService;
  }

  public static void registerSessionExtension() {
    if (!isSessionExtentionRegistered) {
      isSessionExtentionRegistered = true;
      PortalSessionExtension.install();
    }
  }

}
