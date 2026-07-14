package com.axonivy.portal.components.ivydata.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.ivydata.dto.IvySecurityResultDTO;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.user.NewUser;

@IvyTest
class TestSecurityService {

  private static final String USERNAME_1 = "test_security_service_user_1";
  private static final String USERNAME_2 = "test_security_service_user_2";

  SecurityService service = SecurityService.newInstance();

  @AfterEach
  void tearDown() {
    Sudo.get(() -> {
      deleteIfExists(USERNAME_1);
      deleteIfExists(USERNAME_2);
      return null;
    });
  }

  private static void deleteIfExists(String username) {
    if (ISecurityContext.current().users().find(username) != null) {
      ISecurityContext.current().users().delete(username);
    }
  }

  private static void createUser(String username, String fullName) {
    String password = UUID.randomUUID().toString();
    Sudo.get(() -> ISecurityContext.current().users()
        .create(NewUser.create(username).password(password).fullName(fullName).toNewUser()));
  }

  @Test
  void findUsers_matchesByFullNameOrUsername() {
    createUser(USERNAME_1, "Security Service Alpha");
    createUser(USERNAME_2, "Security Service Beta");

    IvySecurityResultDTO result = service.findUsers("Security Service", 0, 100, null, null);

    assertThat(result.getUsers()).extracting(UserDTO::getName)
        .contains(USERNAME_1, USERNAME_2);
  }

  @Test
  void findUsers_excludedUsername_isRemovedFromResult() {
    createUser(USERNAME_1, "Security Service Alpha");
    createUser(USERNAME_2, "Security Service Beta");

    IvySecurityResultDTO result = service.findUsers("Security Service", 0, 100, null, List.of(USERNAME_1));

    assertThat(result.getUsers()).extracting(UserDTO::getName)
        .doesNotContain(USERNAME_1)
        .contains(USERNAME_2);
  }

  @Test
  void findUsers_noMatch_returnsEmptyList() {
    IvySecurityResultDTO result = service.findUsers("no_such_user_should_ever_match_xyz", 0, 100, null, null);

    assertThat(result.getUsers()).isEmpty();
  }

  @Test
  void findUsers_unknownRole_isIgnoredAndStillMatchesByQuery() {
    createUser(USERNAME_1, "Security Service Alpha");

    IvySecurityResultDTO result = service.findUsers("Security Service Alpha", 0, 100,
        List.of("role_that_does_not_exist_xyz"), null);

    assertThat(result.getUsers()).extracting(UserDTO::getName).contains(USERNAME_1);
  }
}
