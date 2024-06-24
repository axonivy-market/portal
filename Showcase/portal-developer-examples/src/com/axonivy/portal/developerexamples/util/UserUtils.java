package com.axonivy.portal.developerexamples.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.UserDTO;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.query.IPagedResult;
import ch.ivyteam.ivy.security.IUser;

public class UserUtils {

  public static List<UserDTO> getAllUsers() {
    List<UserDTO> users = new ArrayList<>();
    IPagedResult<IUser> pageUser = Ivy.wf().getSecurityContext().users().paged(100);
    pageUser.forEach(iuser -> {
      users.add(UserDTO.newUserWithRoles(iuser));
    });

    return users;
  }

  public static List<UserDTO> filterUsers(List<UserDTO> users, List<RoleDTO> roles) {
    Set<String> roleNames = roles.stream().map(RoleDTO::getName).collect(Collectors.toSet());

    return users.stream()
        .filter(userDto -> userDto.getRoles().stream().map(RoleDTO::getName).anyMatch(roleNames::contains))
        .collect(Collectors.toList());
  }

}
