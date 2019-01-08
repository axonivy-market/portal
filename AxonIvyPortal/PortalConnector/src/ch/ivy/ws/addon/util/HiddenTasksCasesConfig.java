package ch.ivy.ws.addon.util;

import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.restricted.IGlobalVariable;
import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.server.ServerFactory;

public final class HiddenTasksCasesConfig {
  public static Boolean isHiddenTasksCasesExcluded(List<String> apps) {
    if (CollectionUtils.isEmpty(apps)) {
      return false;
    }
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        String globalVariableValue = apps.stream().findFirst()
                        .map(ServerFactory.getServer().getApplicationConfigurationManager()::findApplication)
                        .map(IApplication::getActualEnvironment)
                        .map(env -> env.findGlobalVariable("PortalHiddenTaskCaseExcluded"))
                        .map(IGlobalVariable::getValue).orElse("true");
                return Boolean.parseBoolean(globalVariableValue);
      });
    } catch (Exception exception) {
      throw BpmError.create("ivy:portalconnector:error:applicationservice").withCause(exception).build();
    }
  }
  
  public static String getHiddenTasksCasesField(List<String> apps) {
    if (CollectionUtils.isEmpty(apps)) {
      return "";
    }
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        String globalVariableValue = apps.stream().findFirst()
                        .map(ServerFactory.getServer().getApplicationConfigurationManager()::findApplication)
                        .map(IApplication::getActualEnvironment)
                        .map(env -> env.findGlobalVariable("PortalHiddenTaskCaseCustomField"))
                        .map(IGlobalVariable::getValue).orElse("");
                return globalVariableValue;
      });
    } catch (Exception exception) {
      throw BpmError.create("ivy:portalconnector:error:applicationservice").withCause(exception).build();
    }
  }
}
