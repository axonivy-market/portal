package ch.ivy.addon.portal.chat;

import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.server.IServer;
import ch.ivyteam.ivy.server.IServerListener;
import ch.ivyteam.log.Logger;

/**
 * When a server is stopping, it will destroy sessions, then Portal resumes AsyncResponse to update list online users.
 * But at that time, REST service locator is shut down, so it throws exceptions. We use this server listener to remove
 * PortalSessionExtension when stopping a server.
 */
public class PortalServerListener implements IServerListener {
  private static PortalServerListener instance;

  private PortalServerListener() {}

  public static PortalServerListener getInstance() {
    if (instance == null) {
      instance = new PortalServerListener();
    }
    return instance;
  }

  public static void install() {
    DiCore.getGlobalInjector().getInstance(IServer.class).addServerListener(getInstance());
  }

  @Override
  public void serverStarted() {}

  @Override
  public void serverStopped() {}

  @Override
  public void serverStarting(int amountToStart) {}

  @Override
  public void serverStopping(int amountToStop) {
    try {
      PortalSessionExtension.uninstall();
    } catch (Exception e) {
      Logger.getLogger(PortalServerListener.class).error(e);
    }
  }

  @Override
  public void serverProgress(int amoutProgressed, String description) {}

}
