package ch.ivy.addon.portalkit.ivydata.service.impl;

import ch.ivy.addon.portalkit.ivydata.service.IPasswordService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;

public class PasswordService implements IPasswordService {

  private PasswordService() {}

  public static PasswordService newInstance() {
    return new PasswordService();
  }

  @Override
  public boolean changePassword(String newPassword) {
    return IvyExecutor.executeAsSystem(() -> {
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
