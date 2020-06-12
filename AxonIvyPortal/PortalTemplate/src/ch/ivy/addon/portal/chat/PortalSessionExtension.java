package ch.ivy.addon.portal.chat;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.IPersistentTransaction;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.request.IProcessModelVersionRequest;
import ch.ivyteam.ivy.request.RequestFactory;
import ch.ivyteam.ivy.request.impl.RequestContext;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.ivy.security.ISessionExtension;
import ch.ivyteam.util.callable.AbstractExecutionContext;


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
    // - After session is timed out, access ivy engine again.
    // - Open incognito browser window then access ivy engine
    // this is unrelated to login user, it is related to session of browser.
  }

  @Override
  public void destroySession(ISession session, IPersistentTransaction transaction) {
    // this method is called when session timed out
    try {
      executeWithIvyContext(() -> {
        if (chatService() != null && StringUtils.isNotBlank(session.getHttpSessionIdentifier())
            && isLastSessionBoundToUser(session)) {
          chatService().handleUserOffline(session.getSessionUserName());
          ConcurrentChatUtils.removePortalChatResponseHistory(session.getSessionUserName());
        }
        return null;
      }, processModelVersion(), session);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  @Override
  public void logoutSession(ISession session, IPersistentTransaction transaction, long currentTaskId) {
    // this method is called when user logged out
    try {
      executeWithIvyContext(() -> {
        if (chatService() != null && isLastSessionBoundToUser(session)) {
          chatService().handleUserOffline(session.getSessionUserName());
          ConcurrentChatUtils.removePortalChatResponseHistory(session.getSessionUserName());
        }
        return null;
      }, processModelVersion(), session);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  private boolean isLastSessionBoundToUser(ISession session) {
    String username = session.getSessionUserName();
    return Ivy.wf().getSecurityContext().getSessions().stream()
        .noneMatch(s -> s.getSessionUserName().equals(username) && !s.equals(session));
  }

  private static AbstractExecutionContext createRequestContext(IProcessModelVersion pmv, ISession session) {
    IProcessModelVersionRequest request = RequestFactory.createRestRequest(pmv, session);
    return new RequestContext(request);
  }

  private static <T> T executeWithIvyContext(Callable<T> callable, IProcessModelVersion pmv, ISession session)
      throws Exception {
    return createRequestContext(pmv, session).callInContext(callable);
  }

  private ChatService chatService() {
    return ChatServiceContainer.getChatService();
  }

  private IProcessModelVersion processModelVersion() {
    return ChatServiceContainer.getProcessModelVersion();
  }
}
