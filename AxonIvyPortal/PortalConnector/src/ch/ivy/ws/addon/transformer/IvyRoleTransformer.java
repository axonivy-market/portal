package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.types.IvyRole;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.ISecurityMember;

/**
 * Transform a ICase object to an IvyCase object
 * 
 * @author mde
 *
 */
public class IvyRoleTransformer {

  public IvyRole transform(ISecurityMember m, IApplication app) {
    IvyRole result = new IvyRole();

    result.setDisplayName(m.getDisplayName());
    result.setId(m.getId());
    result.setMemberName(m.getMemberName());
    if (app != null) {
      result.setApplication(app.getName());
    }

    return result;
  }

  public List<IvyRole> transform(List<ISecurityMember> members, IApplication app) {
    List<IvyRole> result = new ArrayList<IvyRole>();

    for (ISecurityMember m : members) {
      result.add(transform(m, app));
    }

    return result;
  }
}
