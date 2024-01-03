package com.axonivy.portal.developerexamples.service;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.exec.Sudo;

public class PasswordService{

  private PasswordService() {}

  public static PasswordService newInstance() {
    return new PasswordService();
  }

  public boolean changePassword(String newPassword) {
    return Sudo.get(() -> {
      try {
        Ivy.session().getSessionUser().setPassword(newPassword);
      } catch (PersistencyException ex) {
        Ivy.log().error(ex);
        return false;
      }
      return true;
    });
  }
}
