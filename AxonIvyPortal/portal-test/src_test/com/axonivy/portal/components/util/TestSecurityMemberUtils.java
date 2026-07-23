package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class TestSecurityMemberUtils {

  @ParameterizedTest
  @NullSource
  void getNameInitials_nullInput_returnsNull(String value) {
    assertThat(SecurityMemberUtils.getNameInitials(value)).isNull();
  }

  @ParameterizedTest
  @ValueSource(strings = { "", "   " })
  void getNameInitials_emptyOrBlankInput_returnsEmpty(String value) {
    assertThat(SecurityMemberUtils.getNameInitials(value)).isEmpty();
  }

  @Test
  void getNameInitials_singleName_returnsFirstLetter() {
    assertThat(SecurityMemberUtils.getNameInitials("John")).isEqualTo("J");
  }

  @Test
  void getNameInitials_twoNames_returnsBothInitials() {
    assertThat(SecurityMemberUtils.getNameInitials("John Doe")).isEqualTo("JD");
  }

  @Test
  void getNameInitials_moreThanTwoNames_isTruncatedToTwoChars() {
    assertThat(SecurityMemberUtils.getNameInitials("John Adam Doe")).isEqualTo("JA");
  }

  @Test
  void getNameInitials_lowerCaseName_preservesCase() {
    assertThat(SecurityMemberUtils.getNameInitials("john doe")).isEqualTo("jd");
  }
}
