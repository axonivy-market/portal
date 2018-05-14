package ch.ivy.ws.addon.service;

import static ch.ivyteam.ivy.scripting.objects.List.create;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.SecurityServiceResult;
import ch.ivy.ws.addon.comparator.IvyRoleDisplayNameComparator;
import ch.ivy.ws.addon.comparator.IvyUserDisplayNameComparator;
import ch.ivy.ws.addon.transformer.IvyRoleTransformer;
import ch.ivy.ws.addon.transformer.IvyUserTransformer;
import ch.ivy.ws.addon.types.IvyRole;
import ch.ivy.ws.addon.types.IvyUser;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.IServer;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Default implementation for the security service
 * 
 * @author mde
 *
 */
public class SecurityServiceImpl extends AbstractService implements ISecurityService {

  private static final String SYSTEM_USER = "SYSTEM";
  private static final String HIDE = "HIDE";

  @Override
  public SecurityServiceResult findAllRoles(final List<String> apps) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<SecurityServiceResult>() {
        @Override
        public SecurityServiceResult call() throws Exception {

          IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();
          SecurityServiceResult result = result(create(IvyUser.class), create(IvyRole.class));
          IvyRoleTransformer transformer = new IvyRoleTransformer();
          for (IApplication i : server.getApplicationConfigurationManager().getApplications()) {
            if (apps.contains(i.getName())) {
              result.getIvyRoles().addAll(getMemberRolesOfApp(transformer, i));
            }
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10009, e);
    }
  }

  private ch.ivyteam.ivy.scripting.objects.List<IvyRole> getMemberRolesOfApp(IvyRoleTransformer transformer,
      IApplication app) {
    ch.ivyteam.ivy.scripting.objects.List<IvyRole> members = create(IvyRole.class);
    for (IRole r : app.getSecurityContext().getRoles()) {
      if (r.getProperty(HIDE) == null) {
        members.add(transformer.transform(r, app));
      }
    }
    return members;
  }

  @Override
  public SecurityServiceResult findAllUsers(final List<String> apps) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<SecurityServiceResult>() {
        @Override
        public SecurityServiceResult call() throws Exception {
          IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();
          SecurityServiceResult result = result(create(IvyUser.class), create(IvyRole.class));

          for (IApplication application : server.getApplicationConfigurationManager().getApplications()) {
            if (apps.contains(application.getName())) {
              result.getIvyUsers().addAll(getMemberUsersOfApp(application));
            }
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10010, e);
    }
  }

  private ch.ivyteam.ivy.scripting.objects.List<IvyUser> getMemberUsersOfApp(IApplication application) {
    ch.ivyteam.ivy.scripting.objects.List<IvyUser> members = create(IvyUser.class);
    for (IUser user : application.getSecurityContext().getUsers()) {
      if (!user.getName().equalsIgnoreCase(SYSTEM_USER)) {
        members.add(IvyUserTransformer.transform(user, application));
      }
    }
    return members;
  }

  @Override
  public SecurityServiceResult findUsersByRoleId(final String app, final Long roleId) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<SecurityServiceResult>() {
        @Override
        public SecurityServiceResult call() throws Exception {

          IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();
          SecurityServiceResult result = new SecurityServiceResult();
          ch.ivyteam.ivy.scripting.objects.List<IvyUser> members = create(IvyUser.class);

          for (IApplication i : server.getApplicationConfigurationManager().getApplications()) {
            if (i.getName().equals(app)) {
              findUsers(roleId, result, members, i);
            }
          }
          return result;
        }
      });
    } catch (Exception e) {
      throw new WSException(10011, e);
    }
  }

  private void findUsers(final Long roleId, SecurityServiceResult result,
      ch.ivyteam.ivy.scripting.objects.List<IvyUser> members, IApplication i) {
    IRole role = i.getSecurityContext().findRole(roleId);
    if (role != null) {
      List<IUser> users = role.getAllUsers();
      for (IUser r : users) {
        members.add(IvyUserTransformer.transform(r, i));
      }
      result.setIvyUsers(members);
    }
  }

  @Override
  public SecurityServiceResult findSecurityMembersToDelegate(ITask task) throws WSException {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<SecurityServiceResult>() {
        @Override
        public SecurityServiceResult call() throws Exception {

          ch.ivyteam.ivy.scripting.objects.List<IvyUser> ivyUsers =
              ch.ivyteam.ivy.scripting.objects.List.create(IvyUser.class);
          ch.ivyteam.ivy.scripting.objects.List<IvyRole> ivyRoles =
              ch.ivyteam.ivy.scripting.objects.List.create(IvyRole.class);

          ISecurityContext securityContext = task.getApplication().getSecurityContext();
          List<IUser> users = securityContext.getUsers();
          getIvyRoles(ivyRoles, securityContext);
          Collections.sort(ivyRoles, new IvyRoleDisplayNameComparator());

          getIvyUsers(ivyUsers, users);
          Collections.sort(ivyUsers, new IvyUserDisplayNameComparator());
          return result(ivyUsers, ivyRoles);
        }
      });
    } catch (Exception e) {
      throw new WSException(10010, e);
    }
  }

  private void getIvyUsers(ch.ivyteam.ivy.scripting.objects.List<IvyUser> ivyUsers, List<IUser> users) {
    for (IUser user : users) {
      if (!SYSTEM_USER.equals(user.getName())) {
        ivyUsers.add(IvyUserTransformer.transform(user, null));
      }
    }
  }

  private void getIvyRoles(ch.ivyteam.ivy.scripting.objects.List<IvyRole> ivyRoles, ISecurityContext securityContext) {
    IvyRoleTransformer roleTransformer = new IvyRoleTransformer();
    for (IRole role : securityContext.getRoles()) {
      if (role.getProperty(HIDE) != null) {
        continue;
      }
      ivyRoles.add(roleTransformer.transform(role, null));
    }
  }

  private SecurityServiceResult result(ch.ivyteam.ivy.scripting.objects.List<IvyUser> ivyUsers,
      ch.ivyteam.ivy.scripting.objects.List<IvyRole> ivyRoles) {
    SecurityServiceResult result = new SecurityServiceResult();
    result.setIvyUsers(ivyUsers);
    result.setIvyRoles(ivyRoles);
    return result;
  }
}
