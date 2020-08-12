package ch.ivy.addon.portal.chat;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowContext;
import ch.ivyteam.log.ILogger;

/**
 * Holds instance of {@link ChatService}. Also holds IWorkflowContext Ivy.wf(), ILogger Ivy.log() because in cluster
 * mode, when handling system event, it does not have Ivy context.
 *
 */
public final class ChatServiceContainer {

  private static ChatService chatService;
  private static boolean isSessionExtentionRegistered;
  private static IWorkflowContext worflowContext;
  private static ILogger logger;

  private ChatServiceContainer() {}

  public static ChatService getChatService() {
    return chatService;
  }

  public static IWorkflowContext wf() {
    if (worflowContext == null) {
      worflowContext = Ivy.wf();
    }
    return worflowContext;
  }

  public static IApplication getApplication() {
    return wf().getApplication();
  }


  public static ILogger log() {
    if (logger == null) {
      logger = Ivy.log();
    }

    return logger;
  }

  public static void setChatService(ChatService chatService) {
    ChatServiceContainer.chatService = chatService;
    if (worflowContext == null) {
      ChatServiceContainer.worflowContext = Ivy.wf();
    }
    if (logger == null) {
      ChatServiceContainer.logger = Ivy.log();
    }
  }

  public static void registerSessionExtension() {
    if (!isSessionExtentionRegistered) {
      isSessionExtentionRegistered = true;
      PortalSessionExtension.install();
      PortalServerListener.install();
    }
  }

}
