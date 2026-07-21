package com.axonivy.portal.components.bean;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.enums.GlobalVariable;

import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.IvyTest;

/**
 * The {@code ISecurityMember}/{@code IUser}-based overload of getEmailAddress
 * isn't covered here: it needs a real {@code ISecurityMember} instance, which
 * needs Mockito (not used in this project) or a configured test user.
 */
@IvyTest
class TestPortalComponentAvatarBean {

  private final PortalComponentAvatarBean bean = new PortalComponentAvatarBean();

  @Test
  void getPortalShowAvatarSettingOrDefault_empty_returnsDefault(AppFixture fixture) {
    fixture.var(GlobalVariable.SHOW_AVATAR.getKey(), "");
    assertThat(bean.getPortalShowAvatarSettingOrDefault(true)).isTrue();
    assertThat(bean.getPortalShowAvatarSettingOrDefault(false)).isFalse();
  }

  @Test
  void getPortalShowAvatarSettingOrDefault_explicitValue_ignoresDefault(AppFixture fixture) {
    fixture.var(GlobalVariable.SHOW_AVATAR.getKey(), "false");
    assertThat(bean.getPortalShowAvatarSettingOrDefault(true)).isFalse();
  }

  @Test
  void getPortalShowTechnicalTooltipOrDefault_empty_returnsDefault(AppFixture fixture) {
    fixture.var(GlobalVariable.SHOW_TOOLTIP_TECHNICAL_NAME.getKey(), "");
    assertThat(bean.getPortalShowTechnicalTooltipOrDefault(true)).isTrue();
    assertThat(bean.getPortalShowTechnicalTooltipOrDefault(false)).isFalse();
  }

  @Test
  void getPortalShowTechnicalTooltipOrDefault_explicitValue_ignoresDefault(AppFixture fixture) {
    fixture.var(GlobalVariable.SHOW_TOOLTIP_TECHNICAL_NAME.getKey(), "true");
    assertThat(bean.getPortalShowTechnicalTooltipOrDefault(false)).isTrue();
  }

  @Test
  void getNameInitials_delegatesToSecurityMemberUtils() {
    assertThat(bean.getNameInitials("John Doe")).isEqualTo("JD");
  }

  @Test
  void getEmailAddress_securityMemberDTO_null_returnsEmpty() {
    assertThat(bean.getEmailAddress((SecurityMemberDTO) null, true)).isEmpty();
  }

  @Test
  void getEmailAddress_securityMemberDTO_notUser_returnsEmpty() {
    SecurityMemberDTO role = new SecurityMemberDTO();
    role.setUser(false);
    assertThat(bean.getEmailAddress(role, true)).isEmpty();
  }

  @Test
  void getEmailAddress_securityMemberDTO_user_returnsLowercasedEmailWhenRequested() {
    SecurityMemberDTO user = new SecurityMemberDTO();
    user.setUser(true);
    user.setEMailAddress("John.Doe@Example.com");

    assertThat(bean.getEmailAddress(user, true)).isEqualTo("john.doe@example.com");
    assertThat(bean.getEmailAddress(user, false)).isEqualTo("John.Doe@Example.com");
  }

  @Test
  void getEmailAddress_userDTO_null_returnsEmpty() {
    assertThat(bean.getEmailAddress((UserDTO) null, true)).isEmpty();
  }

  @Test
  void getEmailAddress_userDTO_returnsEmail() {
    UserDTO user = new UserDTO();
    user.setEmail("Jane.Doe@Example.com");

    assertThat(bean.getEmailAddress(user, true)).isEqualTo("jane.doe@example.com");
  }

  @Test
  void getEmailAddress_roleDTO_alwaysReturnsEmpty() {
    assertThat(bean.getEmailAddress(new RoleDTO(), true)).isEmpty();
  }

  @Test
  void tooltipTechnicalDisplayName_nullMember_returnsEmpty() {
    assertThat(bean.tooltipTechnicalDisplayName(null)).isEmpty();
  }

  @Test
  void tooltipTechnicalDisplayName_unsupportedType_returnsEmpty() {
    assertThat(bean.tooltipTechnicalDisplayName("a plain string")).isEmpty();
  }

  @Test
  void tooltipTechnicalDisplayName_userDTOWithBlankName_returnsEmpty() {
    UserDTO user = new UserDTO();
    user.setDisplayName("John Doe");
    user.setName(null);

    assertThat(bean.tooltipTechnicalDisplayName(user)).isEmpty();
  }

  @Test
  void tooltipTechnicalDisplayName_userDTOWithValidData_returnsFormattedTooltip() {
    UserDTO user = new UserDTO();
    user.setDisplayName("John Doe");
    user.setName("jdoe");
    user.setMemberName("#jdoe");

    assertThat(bean.tooltipTechnicalDisplayName(user)).isNotBlank();
  }

  @Test
  void tooltipTechnicalDisplayName_roleDTOWithBlankName_returnsEmpty() {
    RoleDTO role = new RoleDTO();
    role.setDisplayName("Managers");
    role.setName(null);

    assertThat(bean.tooltipTechnicalDisplayName(role)).isEmpty();
  }
}
