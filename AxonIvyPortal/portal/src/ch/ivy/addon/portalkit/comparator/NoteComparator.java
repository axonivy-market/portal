package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivyteam.ivy.workflow.INote;

public class NoteComparator implements Comparator<INote> {

  @Override
  public int compare(INote o1, INote o2) {
    return o2.getCreationTimestamp().compareTo(o1.getCreationTimestamp());
  }

}
