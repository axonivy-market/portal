package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.types.IvySecurityMember;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.ISecurityMember;

/**
 * Transform a ICase object to an IvyCase object
 * 
 * @author mde
 *
 */
public class IvySecurityMemberTransformer {

  public IvySecurityMember transform(ISecurityMember m, IApplication app) {
    IvySecurityMember result = new IvySecurityMember();

    result.setDisplayName(m.getDisplayName());
    result.setId(m.getId());
    result.setMemberName(m.getMemberName());
    if (app != null) {
      result.setApplication(app.getName());
    }

    return result;
  }

  public List<IvySecurityMember> transform(List<ISecurityMember> members, IApplication app) {
    List<IvySecurityMember> result = new ArrayList<>();

    for (ISecurityMember m : members) {
      result.add(transform(m, app));
    }

    return result;
  }
}
