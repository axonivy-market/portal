package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.ws.addon.types.IvySubstitute;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUserSubstitute;

public class IvySubstituteTransformer {

  public static List<IvySubstitute> transform(List<IUserSubstitute> members, IApplication app) {
    List<IvySubstitute> result = new ArrayList<IvySubstitute>();

    for (IUserSubstitute m : members) {
      result.add(transform(m, app));
    }

    return result;
  }

  public static IvySubstitute transform(IUserSubstitute s, IApplication app) {
    IvySubstitute result = new IvySubstitute();
    setRoleForSubstitute(result, s.getSubstitutionRole());
    if (s.getSubstituteUser() != null) {
      result.setMySubstitute(s.getSubstituteUser().getName());
    }
    result.setDescription(s.getDescription());
    if (app != null) {
      result.setAppName(app.getName());
    }

    return result;
  }

  private static void setRoleForSubstitute(IvySubstitute substitute, IRole role) {
    if (role == null) {
      return;
    }
    substitute.setForThisRole(role.getName());
    substitute.setRoleDisplayName(role.getDisplayName());
  }

}
