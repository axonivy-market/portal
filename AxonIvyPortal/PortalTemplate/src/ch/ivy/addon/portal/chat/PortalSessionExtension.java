package ch.ivy.addon.portal.chat;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.IPersistentTransaction;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.request.RequestFactory;
import ch.ivyteam.ivy.request.metadata.MetaData;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.ISessionExtension;


public final class PortalSessionExtension implements ISessionExtension {
  private static PortalSessionExtension instance = new PortalSessionExtension();
  
  private PortalSessionExtension() {}

  public static PortalSessionExtension getInstance() {
    if (instance == null) {
      instance = new PortalSessionExtension();
    }
    return instance;
  }

  public static void install() {
    DiCore.getGlobalInjector().getInstance(ISecurityManager.class).addSessionExtension(getInstance());
  }

  @Override
  public void createSession(ISession session, IPersistentTransaction transaction) throws PersistencyException {
    // this method is called when new session is created, e.g.
    //    - After session is timed out, access ivy engine again.
    //    - Open incognito browser window then access ivy engine
    // this is unrelated to login user, it is related to session of browser.
  }

  @Override
  public void destroySession(ISession session, IPersistentTransaction transaction) throws PersistencyException {
    // this method is called when session timed out
    if (chatService() != null && StringUtils.isNotBlank(session.getHttpSessionIdentifier())
        && isLastSessionBoundToUser(session)) {
      chatService().handleUserOffline(session.getSessionUserName());
      removeChatMessageQueue(session);
    }
  }

  @Override
  public void logoutSession(ISession session, IPersistentTransaction transaction, long currentTaskId)
      throws PersistencyException {
    // this method is called when user logged out
    if (chatService() != null && isLastSessionBoundToUser(session)) {
      chatService().handleUserOffline(session.getSessionUserName());
      removeChatMessageQueue(session);
    }
  }

  private void removeChatMessageQueue(ISession session) {
    try {
      executeWithIvyContext(() -> {
        ConcurrentChatUtils.removePortalChatMessageQueue(session.getSessionUserName());
        return null;
      }, processModelVersion(), session);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  private boolean isLastSessionBoundToUser(ISession session) {
    String username = session.getSessionUserName();
    try {
      return executeWithIvyContext(
          () -> Ivy.wf().getSecurityContext().getSessions().stream()
              .noneMatch(s -> s.getSessionUserName().equals(username) && !s.equals(session)),
          processModelVersion(), session);
    } catch (Exception e) {
      Ivy.log().error(e);
      return false;
    }
  }

  private static <T> T executeWithIvyContext(Callable<T> callable, IProcessModelVersion pmv, ISession session) throws Exception {
    MetaData.pushRequest(RequestFactory.createRestRequest(pmv, session));
    try {
      return callable.call();
    } finally {
      MetaData.popRequest();
    }
  }

  private ChatService chatService() {
    return ChatServiceContainer.getChatService();
  }

  private IProcessModelVersion processModelVersion() {
    return ChatServiceContainer.getProcessModelVersion();
  }
}
