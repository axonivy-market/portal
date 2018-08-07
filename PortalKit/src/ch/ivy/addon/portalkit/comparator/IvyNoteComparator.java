package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivy.ws.addon.IvyNote;

public class IvyNoteComparator implements Comparator<IvyNote> {

  @Override
  public int compare(IvyNote o1, IvyNote o2) {
    return o2.getCreationTimestamp().getTime().compareTo(o1.getCreationTimestamp().getTime());
  }

}
