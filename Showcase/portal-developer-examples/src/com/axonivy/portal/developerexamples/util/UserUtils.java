package com.axonivy.portal.developerexamples.util;

import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;

public class UserUtils {
  public static IUser findUserByUsername(String username) {
    return Sudo.get(() -> {
      return ISecurityContext.current().users().find(username);
    });
  }

}
