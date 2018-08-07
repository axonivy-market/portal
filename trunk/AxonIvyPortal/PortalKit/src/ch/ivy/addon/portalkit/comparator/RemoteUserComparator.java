package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.bo.RemoteUser;

public class RemoteUserComparator implements Comparator<RemoteUser> {

  @Override
  public int compare(RemoteUser first, RemoteUser second) {
    return first.getName().compareToIgnoreCase(second.getName());
  }
}
