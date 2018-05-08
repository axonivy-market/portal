package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.ws.addon.types.IvyUser;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.ISecurityMember;

public class IvyUserTransformer {

  private IvyUserTransformer() {}

  public static IvyUser transform(ISecurityMember m, IApplication app) {
    IvyUser result = new IvyUser();

    result.setDisplayName(m.getDisplayName());
    result.setId(m.getId());
    result.setMemberName(m.getMemberName().replace("#", StringUtils.EMPTY));
    if (app != null) {
      result.setApplication(app.getName());
    }

    return result;
  }

  public static List<IvyUser> transform(List<ISecurityMember> members, IApplication app) {
    List<IvyUser> result = new ArrayList<>();

    for (ISecurityMember m : members) {
      result.add(transform(m, app));
    }

    return result;
  }
}
