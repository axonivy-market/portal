package ch.ivy.addon.portalkit.bo;

import java.util.Date;

import ch.ivy.ws.addon.IvyNote;

public class RemoteNote {
  
  private IvyNote ivyNote;
  
  public RemoteNote(IvyNote ivyNote) {
    this.ivyNote = ivyNote;
  }
  public long getId() {
    return ivyNote.getId();
  }
  public String getCreatorUserName() {
    return ivyNote.getCreatorUserName();
  }
  public String getMessage() {
    return ivyNote.getMessage();
  }
  public Date getCreationTimestamp() {
    if(ivyNote.getCreationTimestamp() != null)
      return ivyNote.getCreationTimestamp().getTime();
    return null;
  } 

}
