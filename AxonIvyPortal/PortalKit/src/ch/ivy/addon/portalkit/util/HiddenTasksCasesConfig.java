package ch.ivy.addon.portalkit.util;

import java.util.Optional;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.restricted.IGlobalVariable;
import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.server.ServerFactory;

public final class HiddenTasksCasesConfig {
  private HiddenTasksCasesConfig(){};
  
  public static String getHiddenTasksCasesField() throws PersistencyException, EnvironmentNotAvailableException, Exception {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        String globalVariableValue = Optional.of(Ivy.request().getApplication())
                        .map(IApplication::getActualEnvironment)
                        .map(env -> env.findGlobalVariable("PortalHiddenTaskCaseCustomField"))
                        .map(IGlobalVariable::getValue).orElse("");
          return globalVariableValue;
      });
  }
}
