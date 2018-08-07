package ch.ivy.ws.addon.service.callable;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;

import ch.ivy.ws.addon.bo.SubstituteServiceResult;
import ch.ivy.ws.addon.transformer.IvySubstituteTransformer;
import ch.ivy.ws.addon.transformer.IvyUserTransformer;
import ch.ivy.ws.addon.types.IvySubstitute;
import ch.ivy.ws.addon.types.IvyUser;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.IServer;

public final class GetSubstituteWebserviceCallable implements Callable<SubstituteServiceResult> {

  private static final String ROLE_EVERYBODY = "Everybody";
  private static final String SYSTEM_USERNAME = "SYSTEM";
  private static final String HIDE = "HIDE";
  private final String username;
  private final List<String> availableApps;

  public GetSubstituteWebserviceCallable(String username, List<String> availableApps) {
    this.username = username;
    this.availableApps = availableApps;
  }

  @Override
  public SubstituteServiceResult call() throws Exception {
    List<IvySubstitute> substitutesOfAllApplication = new ArrayList<>();
    List<IvyUser> userOfAllApplication = new ArrayList<>();

    List<IApplication> iApplications = getAvailableIApplicationsAndContainsUser();

    iApplications.forEach(application -> {
      substitutesOfAllApplication.addAll(getSubstituteOfUserOnApp(application));
      userOfAllApplication.addAll(getAllUserOnApp(application));
    });

    return newWebServiceResultInstance(substitutesOfAllApplication, userOfAllApplication);
  }

  private List<IApplication> getAvailableIApplicationsAndContainsUser() {
    return getAllApplication()
        .stream()
        .filter(
            iApplication -> availableApps.contains(iApplication.getName())
                && iApplication.getSecurityContext().findUser(username) != null).collect(toList());
  }

  private List<IApplication> getAllApplication() {
    return getServer().getApplicationConfigurationManager().getApplications();
  }

  private IServer getServer() {
    return ch.ivyteam.ivy.server.ServerFactory.getServer();
  }

  private SubstituteServiceResult newWebServiceResultInstance(List<IvySubstitute> substitutesOfAllApplication,
      List<IvyUser> userOfAllApplication) {
    SubstituteServiceResult serviceResult = new SubstituteServiceResult();
    serviceResult.setSubstitutes(substitutesOfAllApplication);
    serviceResult.setApplicationUsers(userOfAllApplication);
    return serviceResult;
  }

  private List<IvySubstitute> getSubstituteOfUserOnApp(IApplication application) {
    IUser user = application.getSecurityContext().findUser(username);

    List<IvySubstitute> ivySubstitutes = IvySubstituteTransformer.transform(user.getSubstitutes(), application);

    Set<String> existRoles = ivySubstitutes.stream().map(IvySubstitute::getForThisRole).collect(toSet());
    List<IRole> iRoles = getAllRoles(user).stream().filter((role) -> !existRoles.contains(role.getName()) && Objects.isNull(role.getProperty(HIDE)))
        .collect(toList());
    

    ivySubstitutes.add(createIvySubstituteForUserItself(application.getName()));

    ivySubstitutes.addAll(iRoles.stream().map(iRole -> toIvySubStitute(iRole, application.getName())).collect(toList()));
    return ivySubstitutes;
  }

  private List<IRole> getAllRoles(IUser user) {
    if (user == null) {
      return new ArrayList<>();
    }
    return user.getAllRoles().stream().filter((role) -> !ROLE_EVERYBODY.equals(role.getName())).collect(toList());
  }

  private IvySubstitute createIvySubstituteForUserItself(String applicationName) {
    return newIvySubtitute(null, "", applicationName);
  }

  private IvySubstitute toIvySubStitute(IRole iRole, String applicationName) {
    return newIvySubtitute(iRole.getName(), iRole.getDisplayName(), applicationName);
  }

  private IvySubstitute newIvySubtitute(String roleName, String displayRoleName, String applicationName) {
    IvySubstitute substitute = new IvySubstitute();
    substitute.setAppName(applicationName);
    substitute.setForThisRole(roleName);
    substitute.setRoleDisplayName(displayRoleName);
    return substitute;
  }

  private List<IvyUser> getAllUserOnApp(IApplication application) {
    List<IUser> users = application.getSecurityContext().getUsers();
    List<IvyUser> userlist = users.stream().filter(u -> !u.getName().equalsIgnoreCase(SYSTEM_USERNAME))
        .map(u -> IvyUserTransformer.transform(u, application)).collect(toList());
    return userlist;
  }

}
