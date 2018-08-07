package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.bo.RemoteRole;

public class RemoteRoleComparator implements Comparator<RemoteRole> {

  @Override
  public int compare(RemoteRole first, RemoteRole second) {
    return first.getDisplayName().compareToIgnoreCase(second.getDisplayName());
  }
}
