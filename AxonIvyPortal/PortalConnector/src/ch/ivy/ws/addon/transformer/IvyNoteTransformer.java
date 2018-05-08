package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.types.IvyNote;
import ch.ivyteam.ivy.workflow.INote;

/**
 * Transform a INote object to a IvyNote object
 * 
 * @author mde
 *
 */
public class IvyNoteTransformer {

  public IvyNote transform(INote note) {
    IvyNote result = new IvyNote();
    result.setCreationTimestamp(note.getCreationTimestamp());
    if (note.getWritter() != null) {
      result.setCreatorFullName(note.getWritter().getFullName());
    }
    result.setCreatorUserName(note.getWritterName());
    result.setId(note.getId());
    result.setMessage(note.getMessage());
    return result;
  }


  public List<IvyNote> transform(List<INote> notes) {
    List<IvyNote> result = new ArrayList<>();
    for (INote note : notes) {
      result.add(transform(note));
    }

    return result;
  }
}
