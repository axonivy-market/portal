package ch.ivy.addon.portal.chat;

import ch.ivyteam.ivy.application.app.Application;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowContext;
import ch.ivyteam.log.ILogger;

/**
 * Holds instance of {@link ChatService}. Also holds IWorkflowContext Ivy.wf(), ILogger Ivy.log(), Application
 * Application.current() because in cluster mode, when handling system event, it does not have Ivy context, therefore we
 * use these references instead of Ivy.wf(), Ivy.log(), Application.current()
 *
 */
public final class ChatReferencesContainer {

  private static ChatService chatService;
  private static boolean isIvyExtentionRegistered;
  private static IWorkflowContext worflowContext;
  private static ILogger logger;
  private static Application application;

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

  public static Application app() {
    if (application == null) {
      application = Application.current();
    }
    return application;
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
    if (application == null) {
      ChatReferencesContainer.application = Application.current();
    }
  }

  public static void registerIvyExtension() {
    if (!isIvyExtentionRegistered) {
      isIvyExtentionRegistered = true;
      PortalSessionExtension.install();
    }
  }

}
