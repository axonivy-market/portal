package com.axonivy.portal.components.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TestDisplayNameDTO {

  @Test
  void isCmsUri_localeValuePair_returnsFalse() {
    DisplayNameDTO dto = new DisplayNameDTO("en", "My Title");
    assertThat(dto.isCmsUri()).isFalse();
  }

  @Test
  void isCmsUri_cmsUriReference_returnsTrue() {
    DisplayNameDTO dto = DisplayNameDTO.fromCms("/Processes/SideStep/ProcessName", "portal-developer-examples");
    assertThat(dto.isCmsUri()).isTrue();
  }

  @Test
  void fromCms_withProjectName_setsValueAndProjectNameAndNullLocale() {
    DisplayNameDTO dto = DisplayNameDTO.fromCms("/Processes/SideStep/ProcessName", "portal-developer-examples");
    assertThat(dto.getLocale()).isNull();
    assertThat(dto.getValue()).isEqualTo("/Processes/SideStep/ProcessName");
    assertThat(dto.getProjectName()).isEqualTo("portal-developer-examples");
  }

  @Test
  void equalsAndHashCode_sameFields_areEqual() {
    DisplayNameDTO dto1 = new DisplayNameDTO("en", "My Title");
    DisplayNameDTO dto2 = new DisplayNameDTO("en", "My Title");
    assertThat(dto1).isEqualTo(dto2);
    assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
  }

  @Test
  void equals_differentValue_areNotEqual() {
    DisplayNameDTO dto1 = new DisplayNameDTO("en", "Title A");
    DisplayNameDTO dto2 = new DisplayNameDTO("en", "Title B");
    assertThat(dto1).isNotEqualTo(dto2);
  }

  @Test
  void equals_differentProjectName_areNotEqual() {
    DisplayNameDTO dto1 = DisplayNameDTO.fromCms("/uri", "projectA");
    DisplayNameDTO dto2 = DisplayNameDTO.fromCms("/uri", "projectB");
    assertThat(dto1).isNotEqualTo(dto2);
  }

  @Test
  void equals_null_returnsFalse() {
    DisplayNameDTO dto = new DisplayNameDTO("en", "My Title");
    assertThat(dto).isNotEqualTo(null);
  }

  @Test
  void equals_sameInstance_returnsTrue() {
    DisplayNameDTO dto = new DisplayNameDTO("en", "My Title");
    assertThat(dto).isEqualTo(dto);
  }

  @Test
  void equals_differentClass_returnsFalse() {
    DisplayNameDTO dto = new DisplayNameDTO("en", "My Title");
    assertThat(dto).isNotEqualTo("not a dto");
  }
}
