package ch.ivy.addon.portalkit.converter;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.bo.RemoteWebStartable;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

/**
 * 
 * <p>
 * {@link UserProcess}'s helper class provides methods which help to convert object to
 * {@link UserProcess} object.
 * </p>
 *
 */
public class UserProcessConverter {

  /**
   * Converts list of {@link RemoteWebStartable} to list of {@link UserProcess}.
   * @param webStartables 
   * 
   * @return list of {@link UserProcess}
   */
  public List<UserProcess> convert(List<RemoteWebStartable> webStartables) {
    List<UserProcess> userProcesses = new ArrayList<>();

    for (RemoteWebStartable webStartable : webStartables) {
      userProcesses.add(convert(webStartable));
    }

    return userProcesses;
  }

  private UserProcess convert(RemoteWebStartable webStartable) {
    UserProcess p = new UserProcess();
    p.setProcessName(webStartable.getDisplayName());
    p.setLink(webStartable.getStartLink());
    return p;
  }
}
