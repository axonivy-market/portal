package ch.ivy.addon.portal.chat;

import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Holds instance of {@link ChatService}
 *
 */
public final class ChatServiceContainer {

  private static ChatService chatService;
  private static IProcessModelVersion processModelVersion;
  private static boolean isSessionExtentionRegistered;

  private ChatServiceContainer() {}

  public static ChatService getChatService() {
    return chatService;
  }

  public static IProcessModelVersion getProcessModelVersion() {
    return processModelVersion;
  }

  public static void setChatService(ChatService chatService) {
    ChatServiceContainer.chatService = chatService;
    processModelVersion = Ivy.request().getProcessModelVersion();
  }

  public static void registerSessionExtension() {
    if (!isSessionExtentionRegistered) {
      isSessionExtentionRegistered = true;
      PortalSessionExtension.install();
    }
  }

}
