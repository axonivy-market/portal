package ch.ivy.ws.addon.comparator;

import java.util.Comparator;

import ch.ivy.ws.addon.types.IvyRole;

public class IvyRoleDisplayNameComparator implements Comparator<IvyRole>{

  @Override
  public int compare(IvyRole firstRole, IvyRole secondRole) {
    return firstRole.getDisplayName().compareToIgnoreCase(secondRole.getDisplayName());
  }

}
