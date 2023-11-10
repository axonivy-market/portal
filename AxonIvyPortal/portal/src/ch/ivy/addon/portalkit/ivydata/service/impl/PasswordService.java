package ch.ivy.addon.portalkit.ivydata.service.impl;

import ch.ivy.addon.portalkit.ivydata.service.IPasswordService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.exec.Sudo;

public class PasswordService implements IPasswordService {

  private PasswordService() {}

  public static PasswordService newInstance() {
    return new PasswordService();
  }

  @Override
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
