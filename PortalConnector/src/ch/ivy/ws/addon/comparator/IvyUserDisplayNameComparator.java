package ch.ivy.ws.addon.comparator;

import java.util.Comparator;

import ch.ivy.ws.addon.types.IvyUser;

public class IvyUserDisplayNameComparator implements Comparator<IvyUser> {

  @Override
  public int compare(IvyUser firstUser, IvyUser secondUser) {
    return firstUser.getDisplayName().compareToIgnoreCase(secondUser.getDisplayName());
  }
}
