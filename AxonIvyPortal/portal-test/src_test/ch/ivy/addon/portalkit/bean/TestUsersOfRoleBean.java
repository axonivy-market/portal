package ch.ivy.addon.portalkit.bean;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.user.NewUser;

@IvyTest
class TestUsersOfRoleBean {

  private final UsersOfRoleBean bean = new UsersOfRoleBean();
  private final List<String> usernames = new ArrayList<>();
  private final String suffix = UUID.randomUUID().toString();
  private final String roleAName = "overlay_a_" + suffix;
  private final String roleBName = "overlay_b_" + suffix;
  private IRole roleA;
  private IRole roleB;
  private String userAName;
  private String userBName;

  @BeforeEach
  void setUp() {
    Sudo.get(() -> {
      roleA = ISecurityContext.current().roles().create(roleAName);
      roleA.setDisplayName("Overlay role A");
      roleB = ISecurityContext.current().roles().create(roleBName);
      userAName = createUser("alpha", "Overlay Alpha", roleA);
      userBName = createUser("beta", "Overlay Alpha outside role A", roleB);
      return null;
    });
  }

  @AfterEach
  void tearDown() {
    Sudo.get(() -> {
      for (String username : usernames) {
        ISecurityContext.current().users().delete(username);
      }
      if (ISecurityContext.current().roles().find(roleAName) != null) {
        ISecurityContext.current().roles().delete(roleAName);
      }
      ISecurityContext.current().roles().delete(roleBName);
      return null;
    });
  }

  private String createUser(String name, String fullName, IRole role) {
    String username = "overlay_" + name + "_" + suffix;
    var user = ISecurityContext.current().users()
        .create(NewUser.create(username).fullName(fullName).toNewUser());
    user.addRole(role);
    usernames.add(username);
    return username;
  }

  @Test
  void openingLoadsOnlySelectedRoleAndItsDisplayName() {
    bean.loadUsersOfRole(roleAName);

    assertThat(bean.getSelectedRoleDisplayName()).isEqualTo("Overlay role A");
    assertThat(bean.getKeyword()).isEmpty();
    assertThat(bean.getMemberNames()).extracting(UserDTO::getName).containsExactly(userAName);
    assertThat(bean.isUserLimitReached()).isFalse();
  }

  @Test
  void filteringByFullNameOrUsernameStaysWithinSelectedRole() {
    bean.loadUsersOfRole(roleAName);
    bean.filterUserByName("Overlay Alpha");
    assertThat(bean.getMemberNames()).extracting(UserDTO::getName).containsExactly(userAName);

    bean.filterUserByName(userAName);
    assertThat(bean.getMemberNames()).extracting(UserDTO::getName).containsExactly(userAName);

    bean.filterUserByName(userBName);
    assertThat(bean.getMemberNames()).isEmpty();
  }

  @Test
  void switchingRolesResetsKeywordAndReplacesMembers() {
    bean.loadUsersOfRole(roleAName);
    bean.filterUserByName("no matching user");
    bean.loadUsersOfRole(roleBName);

    assertThat(bean.getKeyword()).isEmpty();
    assertThat(bean.getMemberNames()).extracting(UserDTO::getName).containsExactly(userBName);
  }

  @Test
  void filteringCanFindMembersBeyondInitialLimitAndUpdatesLimitHint() {
    String hiddenUsername = Sudo.get(() -> {
      for (int index = 0; index < 99; index++) {
        createUser("member_" + index, "Overlay member " + index, roleA);
      }
      return createUser("hidden", "ZZZ Overlay hidden", roleA);
    });
    bean.loadUsersOfRole(roleAName);

    assertThat(bean.getMemberNames()).hasSize(100).extracting(UserDTO::getName).doesNotContain(hiddenUsername);
    assertThat(bean.isUserLimitReached()).isTrue();

    bean.filterUserByName(hiddenUsername);
    assertThat(bean.getMemberNames()).extracting(UserDTO::getName).containsExactly(hiddenUsername);
    assertThat(bean.isUserLimitReached()).isFalse();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "Everybody", "overlay_role_that_does_not_exist"})
  void invalidRoleClearsPreviousStateWithoutQueryingOtherUsers(String roleName) {
    bean.loadUsersOfRole(roleAName);
    bean.loadUsersOfRole(roleName);
    bean.filterUserByName("Overlay Alpha");

    assertThat(bean.getSelectedRoleName()).isNull();
    assertThat(bean.getSelectedRoleDisplayName()).isEmpty();
    assertThat(bean.getMemberNames()).isEmpty();
  }

  @Test
  void deletedRoleDoesNotFallBackToUnrestrictedUserSearch() {
    bean.loadUsersOfRole(roleAName);
    Sudo.get(() -> {
      ISecurityContext.current().roles().delete(roleAName);
      return null;
    });
    bean.filterUserByName("Overlay Alpha");

    assertThat(bean.getMemberNames()).isEmpty();
  }

  @Test
  void roleTriggersRespectDisplaySettingAndMemberType(AppFixture fixture) {
    var displayBean = new SecurityMemberDisplayNameBean();
    fixture.var(GlobalVariable.DISPLAY_USERS_OF_ROLE.getKey(), "false");
    displayBean.init();
    assertThat(displayBean.isRenderUsersOverlaySearch(roleA, "false")).isFalse();

    fixture.var(GlobalVariable.DISPLAY_USERS_OF_ROLE.getKey(), "true");
    displayBean.init();
    assertThat(displayBean.isRenderUsersOverlaySearch(roleA, "false")).isTrue();
    assertThat(displayBean.isRenderUsersOverlaySearch(roleA, "true")).isFalse();
    assertThat(displayBean.isRenderUsersOverlaySearch(null, "false")).isFalse();
    assertThat(displayBean.isRenderUsersOverlaySearch(ISecurityContext.current().roles().topLevel(), "false")).isFalse();
    assertThat(displayBean.isRenderUsersOverlaySearch(ISecurityContext.current().users().find(userAName), "false")).isFalse();
  }
}
