package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.types.IvyAbsence;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IUserAbsence;

/**
 * Transform a IUserAbsence object to an IvyAbsence object
 * 
 * @author ptanh
 *
 */
public class IvyAbsenceTransformer {

  public IvyAbsence transform(IUserAbsence a, IApplication app) {
    IvyAbsence result = new IvyAbsence();

    result.setStartDateInclusive(a.getStartTimestamp());
    result.setStopDateInclusive(a.getStopTimestamp());
    result.setDescription(a.getDescription());
    if (app != null) {
      result.setAppName(app.getName());
    }

    return result;
  }

  public List<IvyAbsence> transform(List<IUserAbsence> members, IApplication app) {
    List<IvyAbsence> result = new ArrayList<>();

    for (IUserAbsence m : members) {
      result.add(transform(m, app));
    }

    return result;
  }
}
