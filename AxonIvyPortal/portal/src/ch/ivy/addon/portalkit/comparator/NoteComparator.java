package ch.ivy.addon.portalkit.comparator;

import java.util.Comparator;

import ch.ivyteam.ivy.workflow.note.Note;

public class NoteComparator implements Comparator<Note> {

  @Override
  public int compare(Note o1, Note o2) {
    return o2.createdAt().compareTo(o1.createdAt());
  }

}
