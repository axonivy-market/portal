package ch.ivy.ws.addon.service;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.IsAliveServiceResult;
import ch.ivy.ws.addon.types.IvyApplication;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.server.ServerFactory;

/**
 * Default implementation for the isAlive service
 * 
 * @deprecated This class is deprecated
 *
 */
@Deprecated
public class IsAliveServiceImpl extends AbstractService implements IIsAliveService {

  private static final String SYSTEM_APP_NAME = "System";

  @Override
  public IsAliveServiceResult isActive(final List<String> apps) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<IsAliveServiceResult>() {
        @Override
        public IsAliveServiceResult call() throws Exception {
          IsAliveServiceResult result = isAliveServiceResult(new ArrayList<>());
          if (apps == null) {
            return result;
          }
          if (apps.isEmpty()) {
            getServerAppNames(apps);
          }
          for (String name : apps) {
            IvyApplication app = isApplicationActive(name);
            result.getApps().add(app);
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10006, e);
    }
  }

  private void getServerAppNames(final List<String> apps) {
    List<IApplication> serverApp = getServer().getApplicationConfigurationManager().getApplications();
    apps.addAll(serverApp.stream().filter(app -> !app.getName().equals(SYSTEM_APP_NAME)).map(IApplication::getName)
        .collect(Collectors.toList()));
  }

  private IsAliveServiceResult isAliveServiceResult(List<IvyApplication> apps) {
    IsAliveServiceResult result = new IsAliveServiceResult();
    result.setApps(apps);
    return result;
  }
}
