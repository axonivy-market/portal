package com.axonivy.portal.component.util;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.component.dto.UserDTO;

import com.axonivy.portal.component.constant.PortalComponentConstants;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class UserUtils {

  private UserUtils() {
  }

  public static String getSessionUserName() {
    return getIvySession().getSessionUserName();
  }

  private static IWorkflowSession getIvySession() {
    return Ivy.session();
  }

  public static String getDisplayedName(String fullname, String username) {
    if (StringUtils.isEmpty(fullname)) {
      return username;
    }
    return String.format("%s (%s)", fullname, username);
  }

  /**
   * Finds users who have given roles. If current application is Portal, find all users over all applications, otherwise in current application
   * @param query
   * @param startIndex index of the first record is 0
   * @param count use -1 to return all beginning from the startIndex
   * @param fromRoles role list filter
   * @param excludedUsernames user name list exclude to query 
   * @return user list
   */
  @SuppressWarnings("unchecked")
  public static List<UserDTO> findUsers(String query, int startIndex, int  count, List<String> fromRoles, List<String> excludedUsernames) {
    return IvyExecutor.executeAsSystem(() -> {
      if (Ivy.request().getApplication().getName().equals(PortalComponentConstants.PORTAL_APPLICATION_NAME)) {
        return SubProcessCall.withPath(PortalComponentConstants.SECURITY_SERVICE_CALLABLE)
            .withStartName("findUsersOverAllApplications")
            .withParam("username", getSessionUserName())
            .withParam("query", query)
            .withParam("startIndex", startIndex)
            .withParam("count", count)
            .withParam("fromRoles", fromRoles)
            .withParam("excludedUsernames", excludedUsernames)
            .call()
            .get("users", List.class);
      }
      
      return SubProcessCall.withPath(PortalComponentConstants.SECURITY_SERVICE_CALLABLE)
          .withStartName("findUsers")
          .withParam("application", Ivy.request().getApplication())
          .withParam("query", query)
          .withParam("startIndex", startIndex)
          .withParam("count", count)
          .withParam("fromRoles", fromRoles)
          .withParam("excludedUsernames", excludedUsernames)
          .call()
          .get("users", List.class);
    });
  }
  
  public static List<UserDTO> filterOut(List<UserDTO> users, UserDTO excludedUser) {
    return users.stream().filter(user -> !StringUtils.equals(user.getName(), excludedUser.getName())).collect(Collectors.toList());
  }
}
