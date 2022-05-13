package ch.ivy.addon.portal.chat;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowContext;
import ch.ivyteam.log.ILogger;

/**
 * Holds instance of {@link ChatService}. Also holds IWorkflowContext Ivy.wf(), ILogger Ivy.log() because in cluster
 * mode, when handling system event, it does not have Ivy context, therefore we use these references instead of
 * Ivy.wf(), Ivy.log()
 *
 */
public final class ChatReferencesContainer {

  private static ChatService chatService;
  private static boolean isIvyExtentionRegistered;
  private static IWorkflowContext worflowContext;
  private static ILogger logger;

  private ChatReferencesContainer() {}

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
    return IApplication.current();
  }


  public static ILogger log() {
    if (logger == null) {
      logger = Ivy.log();
    }

    return logger;
  }

  public static void setChatService(ChatService chatService) {
    ChatReferencesContainer.chatService = chatService;
    if (worflowContext == null) {
      ChatReferencesContainer.worflowContext = Ivy.wf();
    }
    if (logger == null) {
      ChatReferencesContainer.logger = Ivy.log();
    }
  }

  public static void registerIvyExtension() {
    if (!isIvyExtentionRegistered) {
      isIvyExtentionRegistered = true;
      PortalSessionExtension.install();
      PortalServerListener.install();
    }
  }

}
