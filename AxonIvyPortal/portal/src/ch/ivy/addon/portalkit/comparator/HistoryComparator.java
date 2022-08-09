package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.addon.portalkit.bo.History;

public class HistoryComparator implements Comparator<History> {

  @Override
  public int compare(History o1, History o2) {
    return o1.getTimestamp().compareTo(o2.getTimestamp());
  }

}
