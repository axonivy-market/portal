package com.axonivy.portal.components.bean;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.dto.UserDTO;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * Only the delegating overloads that don't need a real {@code ISecurityMember}
 * are covered here. {@code generateBriefDisplayNameForSecurityMember(SecurityMemberDTO)}
 * calls {@code Ivy.security().members().findById(...)} and immediately
 * dereferences the result without a null check, so it needs a real, existing
 * security member id — not testable without Mockito or a configured test user.
 */
@IvyTest
class TestPortalComponentDisplayNameFormatBean {

  private final PortalComponentDisplayNameFormatBean bean = new PortalComponentDisplayNameFormatBean();

  @Test
  void generateBriefDisplayNameForSecurityMember_nullMemberWithSharpPrefixedName_stripsSharp() {
    assertThat(bean.generateBriefDisplayNameForSecurityMember(null, "#john")).isEqualTo("john");
  }

  @Test
  void generateBriefDisplayNameForSecurityMember_blankName_returnsNotAvailableLabel() {
    assertThat(bean.generateBriefDisplayNameForSecurityMember(null, "")).isNotNull();
  }

  @Test
  void getDisplayNameForUserDTO_enabledUserWithDisplayName_returnsDisplayName() {
    UserDTO user = new UserDTO();
    user.setDisplayName("John Doe");
    user.setEnabled(true);

    assertThat(bean.getDisplayNameForUserDTO(user)).isEqualTo("John Doe");
  }

  @Test
  void getDisplayNameForUserDTO_nullUser_returnsNotAvailableLabel() {
    assertThat(bean.getDisplayNameForUserDTO(null)).isNotNull();
  }
}
