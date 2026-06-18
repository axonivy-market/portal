package com.axonivy.portal.components.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TestUserDTO {

  @Test
  void copyConstructor_copiesAllRelevantFields() {
    var source = new UserDTO();
    source.setName("jane.doe");
    source.setDisplayName("Jane Doe");
    source.setMemberName("#jane.doe");
    source.setEnabled(true);
    source.setSecurityMemberId("security-member-uuid-456");

    var copy = new UserDTO(source);

    assertThat(copy.getName()).isEqualTo("jane.doe");
    assertThat(copy.getDisplayName()).isEqualTo("Jane Doe");
    assertThat(copy.getMemberName()).isEqualTo("#jane.doe");
    assertThat(copy.isEnabled()).isTrue();
    assertThat(copy.getSecurityMemberId()).isEqualTo("security-member-uuid-456");
  }

  @Test
  void copyConstructor_disabledUser_preservesEnabledState() {
    var source = new UserDTO();
    source.setName("inactive.user");
    source.setEnabled(false);

    var copy = new UserDTO(source);

    assertThat(copy.isEnabled()).isFalse();
  }

  @Test
  void setters_updateFieldsCorrectly() {
    var user = new UserDTO();
    user.setName("test.user");
    user.setEmail("test@example.com");
    user.setSecurityMemberId("sid-xyz");

    assertThat(user.getName()).isEqualTo("test.user");
    assertThat(user.getEmail()).isEqualTo("test@example.com");
    assertThat(user.getSecurityMemberId()).isEqualTo("sid-xyz");
  }
}
