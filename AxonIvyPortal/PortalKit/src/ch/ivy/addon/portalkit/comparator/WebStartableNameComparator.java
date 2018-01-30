package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.bo.RemoteWebStartable;


public class WebStartableNameComparator implements Comparator<RemoteWebStartable> {

  @Override
  public int compare(RemoteWebStartable first, RemoteWebStartable second) {
    return first.getDisplayName().toLowerCase().compareTo(second.getDisplayName().toLowerCase());
  }

}
