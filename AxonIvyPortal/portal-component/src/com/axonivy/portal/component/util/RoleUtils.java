package com.axonivy.portal.component.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.component.dto.RoleDTO;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;

public final class RoleUtils {
  private static final String PROPERTY_HIDE = "HIDE";

  private RoleUtils() {}

  public static List<RoleDTO> findRoles(List<String> fromRoleNames, List<String> excludedRoleNames, String query) {
    List<RoleDTO> roles = findAllRolesFromRoles(fromRoleNames);

    if (CollectionUtils.isNotEmpty(roles) && CollectionUtils.isNotEmpty(excludedRoleNames)) {
      roles = excludeRoleNames(roles, excludedRoleNames);
    }

    if (CollectionUtils.isNotEmpty(roles) && StringUtils.isNotEmpty(query)) {
      roles = filterRoleDTO(roles, query);
    }

    roles.sort((first, second) -> StringUtils.compareIgnoreCase(first.getDisplayName(), second.getDisplayName()));

    return roles;
  }

  private static List<RoleDTO> findAllRolesFromRoles(List<String> roleNames) {
    if (CollectionUtils.isNotEmpty(roleNames)) {
      return findAllChildrenOfRoles(roleNames);
    }

    return findVisibleRoleDTOs();
  }

  private static List<RoleDTO> findAllChildrenOfRoles(List<String> fromRoles) {
    List<RoleDTO> roles = new ArrayList<>();
    for (String roleName : fromRoles) {
      IRole role = findRole(roleName);
      if (Objects.nonNull(role)) {
        roles = mergeTwoRoleList(roles, getAllChildrenOfRole(role));
      }
    }

    return roles;
  }

  private static IRole findRole(String name) {
    return IvyExecutor.executeAsSystem(() -> {
      return Ivy.wf().getSecurityContext().findRole(name);
    });
  }

  private static List<RoleDTO> mergeTwoRoleList(List<RoleDTO> firstList, List<RoleDTO> secondList) {
    return Stream.of(firstList, secondList).flatMap(x -> x.stream()).collect(Collectors.toList());
  }

  private static List<RoleDTO> getAllChildrenOfRole(IRole role) {
    return role.getChildRoles().stream().filter(predicateIsVisibleRole()).map(childRole -> new RoleDTO(childRole))
        .collect(Collectors.toList());
  }

  private static Predicate<IRole> predicateIsVisibleRole() {
    return role -> Objects.isNull(role.getProperty(PROPERTY_HIDE));
  }

  private static List<RoleDTO> findVisibleRoleDTOs() {
    return filterVisibleRoles(getAllRoles()).stream().map(role -> new RoleDTO(role)).collect(Collectors.toList());
  }

  private static List<IRole> filterVisibleRoles(List<IRole> roles) {
    return filterRole(roles, predicateIsVisibleRole());
  }

  private static List<IRole> filterRole(List<IRole> roles, Predicate<IRole> predicate) {
    return CollectionUtils.emptyIfNull(roles).stream().filter(predicate).collect(Collectors.toList());
  }

  private static List<IRole> getAllRoles() {
    return IvyExecutor.executeAsSystem(() -> {
      return Ivy.wf().getSecurityContext().getRoles();
    });
  }

  private static List<RoleDTO> excludeRoleNames(List<RoleDTO> roleDTOs, List<String> excludedRoleNames) {
    List<RoleDTO> roles = roleDTOs;
    for (String excludedRoleName : excludedRoleNames) {
      roles = excludeRoleDTOByName(roles, excludedRoleName);
    }
    return roles;
  }

  private static List<RoleDTO> excludeRoleDTOByName(List<RoleDTO> roleDTOs, String roleName) {
    List<RoleDTO> roles = new ArrayList<>();
    for (RoleDTO roleDTO : roleDTOs) {
      if (!isNameOfRole(roleDTO, roleName)) {
        roles.add(roleDTO);
      }
    }
    return roles;
  }

  private static boolean isNameOfRole(RoleDTO roleDTO, String roleName) {
    return roleDTO.getDisplayName().equalsIgnoreCase(roleName.trim())
        || roleDTO.getMemberName().equalsIgnoreCase(roleName.trim())
        || roleDTO.getName().equalsIgnoreCase(roleName.trim());
  }

  private static List<RoleDTO> filterRoleDTO(List<RoleDTO> roles, String query) {
    if (StringUtils.isEmpty(query)) {
      return roles;
    }

    List<RoleDTO> filterRoles = new ArrayList<>();
    for (RoleDTO role : roles) {
      if (StringUtils.containsIgnoreCase(role.getDisplayName(), query)
          || StringUtils.containsIgnoreCase(role.getMemberName(), query)) {
        filterRoles.add(role);
      }
    }

    return filterRoles;
  }
}
