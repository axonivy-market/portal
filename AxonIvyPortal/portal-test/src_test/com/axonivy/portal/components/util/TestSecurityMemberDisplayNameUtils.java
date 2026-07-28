package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class TestSecurityMemberDisplayNameUtils {

  @ParameterizedTest
  @NullSource
  void stripSharpCharacterFromSecurityMemberName_nullInput_returnsEmpty(String input) {
    assertThat(SecurityMemberDisplayNameUtils.stripSharpCharacterFromSecurityMemberName(input)).isEmpty();
  }

  @Test
  void stripSharpCharacterFromSecurityMemberName_startsWithSharp_isStripped() {
    assertThat(SecurityMemberDisplayNameUtils.stripSharpCharacterFromSecurityMemberName("#john")).isEqualTo("john");
  }

  @Test
  void stripSharpCharacterFromSecurityMemberName_withoutSharp_isUnchanged() {
    assertThat(SecurityMemberDisplayNameUtils.stripSharpCharacterFromSecurityMemberName("john")).isEqualTo("john");
  }

  @Test
  void generateBriefDisplayNameForRole_blankDisplayName_returnsName() {
    assertThat(SecurityMemberDisplayNameUtils.generateBriefDisplayNameForRole("", "roleName")).isEqualTo("roleName");
    assertThat(SecurityMemberDisplayNameUtils.generateBriefDisplayNameForRole(null, "roleName")).isEqualTo("roleName");
  }

  @Test
  void generateBriefDisplayNameForRole_withDisplayName_returnsDisplayName() {
    assertThat(SecurityMemberDisplayNameUtils.generateBriefDisplayNameForRole("Role Display", "roleName"))
        .isEqualTo("Role Display");
  }

  @Test
  void generateBriefDisplayNameForSecurityMember_nullSecurityMember_stripsSharpCharacter() {
    assertThat(SecurityMemberDisplayNameUtils.generateBriefDisplayNameForSecurityMember(null, "#john"))
        .isEqualTo("john");
  }
}
