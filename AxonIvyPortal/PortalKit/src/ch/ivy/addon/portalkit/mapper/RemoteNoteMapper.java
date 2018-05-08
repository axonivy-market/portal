package ch.ivy.addon.portalkit.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.bo.RemoteNote;
import ch.ivy.ws.addon.IvyNote;

public class RemoteNoteMapper {
  
  private RemoteNoteMapper() {}
  private static RemoteNote mapNote(IvyNote ivyNote) {
    return new RemoteNote(ivyNote);
  }

  private static List<RemoteNote> mapNotes(List<IvyNote> ivyNotes) {
    List<RemoteNote> remoteNotes = new ArrayList<>();
    if (ivyNotes != null) {
      for (IvyNote ivyNote : ivyNotes) {
        remoteNotes.add(mapNote(ivyNote));
      }
    }
    return remoteNotes;
  }

  public static List<RemoteNote> mapNoteArray(IvyNote[] ivyNoteArray) {
    if (ivyNoteArray != null) {
      List<IvyNote> ivyNotes = Arrays.asList(ivyNoteArray);
      return mapNotes(ivyNotes);
    } else {
      return new ArrayList<>();
    }
  }
}
