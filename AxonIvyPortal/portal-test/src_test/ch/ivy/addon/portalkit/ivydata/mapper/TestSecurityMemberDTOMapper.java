package ch.ivy.addon.portalkit.ivydata.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.dto.UserDTO;

public class TestSecurityMemberDTOMapper {

  @Test
  void mapFromUserDTO_mapsAllFields() {
    var userDTO = new UserDTO();
    userDTO.setName("john.doe");
    userDTO.setDisplayName("John Doe");
    userDTO.setMemberName("#john.doe");
    userDTO.setEmail("john.doe@example.com");
    userDTO.setEnabled(true);
    userDTO.setSecurityMemberId("security-member-uuid-123");

    var result = SecurityMemberDTOMapper.mapFromUserDTO(userDTO);

    assertThat(result.getName()).isEqualTo("john.doe");
    assertThat(result.getDisplayName()).isEqualTo("John Doe");
    assertThat(result.getMemberName()).isEqualTo("#john.doe");
    assertThat(result.getEMailAddress()).isEqualTo("john.doe@example.com");
    assertThat(result.isEnabled()).isTrue();
    assertThat(result.isUser()).isTrue();
    assertThat(result.getSecurityMemberId()).isEqualTo("security-member-uuid-123");
  }

  @Test
  void mapFromUserDTO_disabledUser_mapsEnabledFalse() {
    var userDTO = new UserDTO();
    userDTO.setName("inactive.user");
    userDTO.setEnabled(false);

    var result = SecurityMemberDTOMapper.mapFromUserDTO(userDTO);

    assertThat(result.isEnabled()).isFalse();
    assertThat(result.isUser()).isTrue();
  }

  @Test
  void mapFromUserDTOs_withNullList_returnsEmpty() {
    var result = SecurityMemberDTOMapper.mapFromUserDTOs(null);

    assertThat(result).isEmpty();
  }

  @Test
  void mapFromUserDTOs_mapsAllEntries() {
    var user1 = new UserDTO();
    user1.setName("alice");
    user1.setSecurityMemberId("sid-alice");

    var user2 = new UserDTO();
    user2.setName("bob");
    user2.setSecurityMemberId("sid-bob");

    var result = SecurityMemberDTOMapper.mapFromUserDTOs(List.of(user1, user2));

    assertThat(result).hasSize(2)
        .extracting(dto -> dto.getName())
        .containsExactly("alice", "bob");
  }
}
