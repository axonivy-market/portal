package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.dto.UserDTO;

class TestUserUtils {

  @Test
  void getDisplayedName_emptyFullname_returnsUsername() {
    assertThat(UserUtils.getDisplayedName("", "jdoe")).isEqualTo("jdoe");
  }

  @Test
  void getDisplayedName_nullFullname_returnsUsername() {
    assertThat(UserUtils.getDisplayedName(null, "jdoe")).isEqualTo("jdoe");
  }

  @Test
  void getDisplayedName_withFullname_returnsFormatted() {
    assertThat(UserUtils.getDisplayedName("John Doe", "jdoe")).isEqualTo("John Doe (jdoe)");
  }

  @Test
  void filterOut_removesMatchingUser() {
    UserDTO john = user("John", "1");
    UserDTO jane = user("Jane", "2");

    List<UserDTO> result = UserUtils.filterOut(List.of(john, jane), john);

    assertThat(result).containsExactly(jane);
  }

  @Test
  void filterOut_noMatch_keepsAll() {
    UserDTO john = user("John", "1");
    UserDTO jane = user("Jane", "2");
    UserDTO other = user("Other", "3");

    List<UserDTO> result = UserUtils.filterOut(List.of(john, jane), other);

    assertThat(result).containsExactly(john, jane);
  }

  @Test
  void distinctAndSortUserList_emptyInput_returnsNull() {
    assertThat(UserUtils.distinctAndSortUserList(List.of())).isNull();
  }

  @Test
  void distinctAndSortUserList_nullInput_returnsNull() {
    assertThat(UserUtils.distinctAndSortUserList(null)).isNull();
  }

  @Test
  void distinctAndSortUserList_duplicateSecurityMemberIds_areDistinctAndSortedByDisplayName() {
    UserDTO zebraFirst = displayUser("Zebra", "1");
    UserDTO zebraDuplicate = displayUser("Zebra Duplicate", "1");
    UserDTO alpha = displayUser("Alpha", "2");

    List<UserDTO> result = UserUtils.distinctAndSortUserList(List.of(zebraFirst, zebraDuplicate, alpha));

    assertThat(result).extracting(UserDTO::getDisplayName).containsExactly("Alpha", "Zebra");
  }

  private UserDTO user(String name, String securityMemberId) {
    UserDTO user = new UserDTO();
    user.setName(name);
    user.setSecurityMemberId(securityMemberId);
    return user;
  }

  private UserDTO displayUser(String displayName, String securityMemberId) {
    UserDTO user = new UserDTO();
    user.setDisplayName(displayName);
    user.setSecurityMemberId(securityMemberId);
    return user;
  }
}
