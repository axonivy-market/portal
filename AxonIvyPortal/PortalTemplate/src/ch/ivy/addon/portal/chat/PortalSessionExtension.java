package ch.ivy.addon.portal.chat;

import static ch.ivy.addon.portal.chat.ChatReferencesContainer.getChatService;
import static ch.ivy.addon.portal.chat.ChatReferencesContainer.wf;

import java.util.concurrent.Callable;

import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.application.loader.ClassLoaderService;
import ch.ivyteam.ivy.persistence.IPersistentTransaction;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.ISessionExtension;
import ch.ivyteam.log.Logger;
import ch.ivyteam.util.threadcontext.IvyAsyncRunner;

@SuppressWarnings("restriction")
public final class PortalSessionExtension implements ISessionExtension {
  private static PortalSessionExtension instance;
  private IvyAsyncRunner asyncRunner;

  private PortalSessionExtension() {
    asyncRunner = new IvyAsyncRunner();
  }

  public static PortalSessionExtension getInstance() {
    if (instance == null) {
      instance = new PortalSessionExtension();
    }
    return instance;
  }

  public static void install() {
    DiCore.getGlobalInjector().getInstance(ISecurityManager.class).addSessionExtension(getInstance());
  }

  public static void uninstall() {
    DiCore.getGlobalInjector().getInstance(ISecurityManager.class).removeSessionExtension(getInstance());
  }

  @Override
  public void createSession(ISession session, IPersistentTransaction transaction) throws PersistencyException {
    // this method is called when new session is created, e.g.
    // - After session is timed out, access ivy engine again.
    // - Open incognito browser window then access ivy engine
    // this is unrelated to login user, it is related to session of browser.
  }

  @Override
  public void destroySession(ISession session, IPersistentTransaction transaction) {
    // this method is called when session timed out
    if (isSessionExtensionObsolete()) {
      uninstall();
      return;
    }
    try {
      executeWithIvyContext(() -> {
        if (chatService() != null && !session.getHttpSessionIdentifiers().isEmpty()
            && isLastSessionBoundToUser(session)) {
          chatService().handleUserOffline(session.getSessionUserName());
          ConcurrentChatUtils.removePortalChatResponseHistory(session.getSessionUserName());
        }
        return null;
      });
    } catch (Exception e) {
      Logger.getLogger(PortalSessionExtension.class).error("PortalSessionExtension destroySession", e);
    }
  }

  @Override
  public void logoutSession(ISession session, IPersistentTransaction transaction, long currentTaskId) {
    // this method is called when user logged out
    if (isSessionExtensionObsolete()) {
      uninstall();
      return;
    }
    try {
      executeWithIvyContext(() -> {
        if (chatService() != null && isLastSessionBoundToUser(session)) {
          chatService().handleUserOffline(session.getSessionUserName());
          ConcurrentChatUtils.removePortalChatResponseHistory(session.getSessionUserName());
        }
        return null;
      });
    } catch (Exception e) {
      Logger.getLogger(PortalSessionExtension.class).error("PortalSessionExtension logoutSession", e);
    }
  }

  private boolean isSessionExtensionObsolete() {
    if (ClassLoaderService.hasDeprecatedClassLoader(this)) {
      return true;
    }
    return false;
  }

  private boolean isLastSessionBoundToUser(ISession session) {
    String username = session.getSessionUserName();
    return wf().getSecurityContext().sessions().all().stream()
        .noneMatch(s -> s.getSessionUserName().equals(username) && !s.equals(session));
  }

  private <T> T executeWithIvyContext(Callable<T> callable) throws Exception {
    return asyncRunner.run(callable);
  }

  private ChatService chatService() {
    return getChatService();
  }

}
