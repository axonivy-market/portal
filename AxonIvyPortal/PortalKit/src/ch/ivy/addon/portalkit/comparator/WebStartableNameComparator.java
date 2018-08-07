package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class WebStartableNameComparator implements Comparator<IWebStartable> {

  @Override
  public int compare(IWebStartable first, IWebStartable second) {
    return first.getDisplayName().toLowerCase().compareTo(second.getDisplayName().toLowerCase());
  }

}
