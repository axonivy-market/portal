package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.dto.RoleDTO;

class TestRoleUtils {

  @Test
  void distinctAndSortRoleList_nullInput_returnsNull() {
    assertThat(RoleUtils.distinctAndSortRoleList(null)).isNull();
  }

  @Test
  void distinctAndSortRoleList_emptyInput_returnsNull() {
    assertThat(RoleUtils.distinctAndSortRoleList(List.of())).isNull();
  }

  @Test
  void distinctAndSortRoleList_duplicateSecurityMemberIds_areDistinct() {
    RoleDTO first = roleDTO("Zebra", "zebra", "1");
    RoleDTO duplicate = roleDTO("Zebra Duplicate", "zebra-dup", "1");
    RoleDTO second = roleDTO("Alpha", "alpha", "2");

    List<RoleDTO> result = RoleUtils.distinctAndSortRoleList(Arrays.asList(first, duplicate, second));

    assertThat(result).hasSize(2);
  }

  @Test
  void distinctAndSortRoleList_sortsByDisplayName() {
    RoleDTO zebra = roleDTO("Zebra", "zebra", "1");
    RoleDTO alpha = roleDTO("Alpha", "alpha", "2");

    List<RoleDTO> result = RoleUtils.distinctAndSortRoleList(Arrays.asList(zebra, alpha));

    assertThat(result).extracting(RoleDTO::getDisplayName).containsExactly("Alpha", "Zebra");
  }

  @Test
  void isNameOfRole_matchesByDisplayNameIgnoringCaseAndWhitespace() {
    RoleDTO role = roleDTO("Manager", "manager-member", "1");
    role.setName("manager-name");

    assertThat(invokeIsNameOfRole(role, "  MANAGER  ")).isTrue();
  }

  @Test
  void isNameOfRole_matchesByMemberName() {
    RoleDTO role = roleDTO("Manager", "manager-member", "1");
    role.setName("manager-name");

    assertThat(invokeIsNameOfRole(role, "manager-member")).isTrue();
  }

  @Test
  void isNameOfRole_matchesByName() {
    RoleDTO role = roleDTO("Manager", "manager-member", "1");
    role.setName("manager-name");

    assertThat(invokeIsNameOfRole(role, "manager-name")).isTrue();
  }

  @Test
  void isNameOfRole_noMatch_returnsFalse() {
    RoleDTO role = roleDTO("Manager", "manager-member", "1");
    role.setName("manager-name");

    assertThat(invokeIsNameOfRole(role, "other")).isFalse();
  }

  @Test
  void filterRoleDTO_emptyQuery_returnsAllRoles() {
    RoleDTO role = roleDTO("Manager", "manager-member", "1");

    assertThat(invokeFilterRoleDTO(List.of(role), "")).containsExactly(role);
  }

  @Test
  void filterRoleDTO_matchesDisplayNameCaseInsensitive() {
    RoleDTO manager = roleDTO("Manager", "manager-member", "1");
    RoleDTO clerk = roleDTO("Clerk", "clerk-member", "2");

    assertThat(invokeFilterRoleDTO(Arrays.asList(manager, clerk), "man")).containsExactly(manager);
  }

  @Test
  void filterRoleDTO_matchesMemberName() {
    RoleDTO manager = roleDTO("Manager", "special-member", "1");
    RoleDTO clerk = roleDTO("Clerk", "clerk-member", "2");

    assertThat(invokeFilterRoleDTO(Arrays.asList(manager, clerk), "special")).containsExactly(manager);
  }

  @Test
  void filterRoleDTO_noMatch_returnsEmpty() {
    RoleDTO role = roleDTO("Manager", "manager-member", "1");

    assertThat(invokeFilterRoleDTO(List.of(role), "nope")).isEmpty();
  }

  private static RoleDTO roleDTO(String displayName, String memberName, String securityMemberId) {
    RoleDTO role = new RoleDTO();
    role.setDisplayName(displayName);
    role.setMemberName(memberName);
    role.setSecurityMemberId(securityMemberId);
    return role;
  }

  @SuppressWarnings("unchecked")
  private static List<RoleDTO> invokeFilterRoleDTO(List<RoleDTO> roles, String query) {
    try {
      Method method = RoleUtils.class.getDeclaredMethod("filterRoleDTO", List.class, String.class);
      method.setAccessible(true);
      return (List<RoleDTO>) method.invoke(null, roles, query);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }

  private static boolean invokeIsNameOfRole(RoleDTO role, String roleName) {
    try {
      Method method = RoleUtils.class.getDeclaredMethod("isNameOfRole", RoleDTO.class, String.class);
      method.setAccessible(true);
      return (boolean) method.invoke(null, role, roleName);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException(e);
    }
  }
}
