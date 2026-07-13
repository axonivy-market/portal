package com.axonivy.portal.components.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.dto.DisplayNameDTO;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestDisplayNameUtils {

  @Test
  void isDisplayNameBasedOnCms_nullList_returnsFalse() {
    assertThat(DisplayNameUtils.isDisplayNameBasedOnCms(null)).isFalse();
  }

  @Test
  void isDisplayNameBasedOnCms_emptyList_returnsFalse() {
    assertThat(DisplayNameUtils.isDisplayNameBasedOnCms(List.of())).isFalse();
  }

  @Test
  void isDisplayNameBasedOnCms_multipleLocaleEntries_returnsFalse() {
    List<DisplayNameDTO> displayNames = List.of(
        new DisplayNameDTO("en", "Hello"),
        new DisplayNameDTO("de", "Hallo"));
    assertThat(DisplayNameUtils.isDisplayNameBasedOnCms(displayNames)).isFalse();
  }

  @Test
  void isDisplayNameBasedOnCms_singleLocaleValueEntry_returnsFalse() {
    List<DisplayNameDTO> displayNames = List.of(new DisplayNameDTO("en", "Hello"));
    assertThat(DisplayNameUtils.isDisplayNameBasedOnCms(displayNames)).isFalse();
  }

  @Test
  void isDisplayNameBasedOnCms_singleCmsUriEntry_returnsTrue() {
    List<DisplayNameDTO> displayNames = DisplayNameUtils.createCmsDisplayName("/Labels/Foo", "my-project");
    assertThat(DisplayNameUtils.isDisplayNameBasedOnCms(displayNames)).isTrue();
  }

  @Test
  void getDisplayText_emptyList_returnsEmpty() {
    assertThat(DisplayNameUtils.getDisplayText(List.of(), "en")).isEmpty();
  }

  @Test
  void getDisplayText_nullList_returnsEmpty() {
    assertThat(DisplayNameUtils.getDisplayText(null, "en")).isEmpty();
  }

  @Test
  void getDisplayText_matchingLocale_returnsValue() {
    List<DisplayNameDTO> displayNames = List.of(
        new DisplayNameDTO("en", "Hello"),
        new DisplayNameDTO("de", "Hallo"));

    assertThat(DisplayNameUtils.getDisplayText(displayNames, "de")).isEqualTo("Hallo");
  }

  @Test
  void getDisplayText_noMatchingLocale_returnsEmpty() {
    List<DisplayNameDTO> displayNames = List.of(new DisplayNameDTO("en", "Hello"));

    assertThat(DisplayNameUtils.getDisplayText(displayNames, "fr")).isEmpty();
  }

  @Test
  void createCmsDisplayName_withProjectName_createsCmsUriEntry() {
    List<DisplayNameDTO> displayNames = DisplayNameUtils.createCmsDisplayName("/Labels/Foo", "my-project");

    assertThat(displayNames).hasSize(1);
    DisplayNameDTO dto = displayNames.get(0);
    assertThat(dto.isCmsUri()).isTrue();
    assertThat(dto.getValue()).isEqualTo("/Labels/Foo");
    assertThat(dto.getProjectName()).isEqualTo("my-project");
  }

  @Test
  void getDisplayText_cmsUriWithUnknownProject_returnsEmpty() {
    List<DisplayNameDTO> displayNames = DisplayNameUtils.createCmsDisplayName("/Labels/Foo",
        "project-that-does-not-exist-xyz");

    assertThat(DisplayNameUtils.getDisplayText(displayNames, "en")).isEmpty();
  }
}
