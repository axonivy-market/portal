package com.axonivy.portal.component.util;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivyteam.ivy.application.restricted.IGlobalVariable;
import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.server.ServerFactory;

public class HiddenTasksCasesConfig {
  public static final String PORTAL_HIDDEN_TASK_CASE_EXCLUDED = "PortalHiddenTaskCaseExcluded";

  private HiddenTasksCasesConfig() {
    
  }

  public static boolean isHiddenTasksCasesExcluded(List<String> apps) {
    return getGlobalVariable(apps, PORTAL_HIDDEN_TASK_CASE_EXCLUDED);
  }
  
  private static boolean getGlobalVariable(List<String> apps, String variableName) {
    if (CollectionUtils.isEmpty(apps)) {
      return false;
    }
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        String globalVariableValue = apps.stream().findFirst()
                        .map(ServerFactory.getServer().getApplicationConfigurationManager()::findApplication)
                        .map(app -> app.findGlobalVariable(variableName))
                        .map(IGlobalVariable::getValue).orElse("true");
        return Boolean.parseBoolean(globalVariableValue);
      });
    } catch (Exception exception) {
      throw BpmError.create("ivy:portalconnector:error:applicationservice").withCause(exception).build();
    }
  }
  
}
