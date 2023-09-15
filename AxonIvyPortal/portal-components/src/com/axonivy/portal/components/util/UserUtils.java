package com.axonivy.portal.components.util;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TreeSet;
import java.util.function.Function;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.constant.PortalComponentConstants;
import com.axonivy.portal.components.dto.UserDTO;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IUser;
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
   * Finds the users who have the given roles in current application.
   * @param query
   * @param startIndex index of the first record is 0
   * @param count use -1 to return all beginning from the startIndex
   * @param fromRoles role list filter
   * @param excludedUsernames user name list exclude
   * @return user list
   */
  @SuppressWarnings("unchecked")
  public static List<UserDTO> findUsers(String query, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    return IvyExecutor.executeAsSystem(() -> {
      return SubProcessCall.withPath(PortalComponentConstants.SECURITY_SERVICE_CALLABLE)
          .withStartName("findUsers")
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
    return users.stream().filter(user -> !StringUtils.equals(user.getName(), excludedUser.getName())).collect(toList());
  }

  private static String loadLanguage(Function<IUser, Locale> userLocaleLoader) {
    var languageTag = "";
    if (!Ivy.session().isSessionUserUnknown()) {
      Locale apply = userLocaleLoader.apply(Ivy.session().getSessionUser());
      languageTag = Objects.nonNull(apply) ? apply.toLanguageTag() : languageTag;
    }
    return languageTag;
  }
  public static String getUserLanguage() {
    return loadLanguage(IUser::getLanguage);
  }

  /**
   * Distinct a list of UserDTO by id then sort by display name.
   * 
   * @param userList original list of users
   * @return distinct and sorted list of users
   */
  public static List<UserDTO> distinctAndSortUserList(List<UserDTO> userList) {
    if (CollectionUtils.isEmpty(userList)) {
      return null;
    }

    List<UserDTO> result = userList.stream()
        .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(UserDTO::getId))), ArrayList::new));

    result.sort((u1, u2) -> u1.getDisplayName().compareTo(u2.getDisplayName()));
    return result;
  }
}
