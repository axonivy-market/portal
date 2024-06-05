package com.axonivy.portal.components.util;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.server.ServerFactory;

public class PermissionUtils {

  private PermissionUtils() {}

  
  /**
   * Check if current user has read all cases permission
   * 
   * @return True : has read all cases permission, False : do not have this permission
   */
  public static boolean checkReadAllCasesPermission() {
    return Ivy.session().hasPermission(Ivy.request().getApplication().getSecurityDescriptor(),
        ch.ivyteam.ivy.security.IPermission.CASE_READ_ALL);
  }

  /**
   * Check if current user has the permission to read all cases of an
   * application which his roles involved.
   *
   * @param app Application name
   *
   * @return True : has the permission to read all cases which his roles
   *         involved.
   */
  public static boolean checkCaseReadAllOwnRoleInvolvedPermission(String app) {
    if (StringUtils.isBlank(app)) {
      return false;
    }

    IApplication application = IvyExecutor.executeAsSystem(() -> {
      return ServerFactory.getServer().getApplicationConfigurationManager()
          .findApplication(app);
    });

    return Ivy.session().hasPermission(application.getSecurityDescriptor(),
        IPermission.CASE_READ_ALL_OWN_ROLE_INVOLVED);
  }
}
