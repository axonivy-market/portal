package com.axonivy.portal.bean.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.axonivy.portal.dto.menu.ExternalLinkMenuItemDefinition;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.dto.menu.StandardMenuItemDefinition;
import com.axonivy.portal.enums.StandardMenuItemDefinitionType;
import com.axonivy.portal.menu.management.enums.MenuSource;

import ch.ivy.addon.portal.generic.bean.DashboardConfigurationBean;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.AccessState;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityConstants;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.security.user.NewUser;

@IvyTest
@ResourceLock("Portal menu permissions")
class TestMenuManagementBean {

  private final MenuManagementBean bean = new MenuManagementBean();
  private final Map<IPermission, List<Boolean>> previousEverybodyAcl = new LinkedHashMap<>();
  private final String suffix = UUID.randomUUID().toString();
  private IUser previousUser;
  private ISecurityContext security;
  private ISecurityDescriptor descriptor;
  private IRole everybody;
  private IRole unrelatedRole;
  private IUser manager;
  private IUser unrelatedUser;
  private IPermission sidebarPermission;

  @BeforeEach
  void setUp(AppFixture fixture) {
    previousUser = Ivy.session().getSessionUser();
    Sudo.get(() -> {
      security = ISecurityContext.current();
      descriptor = security.securityDescriptor();
      everybody = security.roles().find(ISecurityConstants.TOP_LEVEL_ROLE_NAME);
      assertThat(everybody).isNotNull();
      sidebarPermission = permission(PortalPermission.PORTAL_SIDEBAR_CONFIGURATION);
      for (var portalPermission : List.of(PortalPermission.ACCESS_DASHBOARD, PortalPermission.ACCESS_FULL_PROCESS_LIST)) {
        var permission = permission(portalPermission);
        previousEverybodyAcl.put(permission, explicitAcl(permission, everybody));
      }
      manager = security.users().create(NewUser.create("menu_manager_" + suffix).toNewUser());
      unrelatedUser = security.users().create(NewUser.create("menu_user_" + suffix).toNewUser());
      unrelatedRole = security.roles().create("menu_role_" + suffix);
      descriptor.grantPermission(sidebarPermission, manager);
      for (var permission : previousEverybodyAcl.keySet()) {
        descriptor.undenyPermission(permission, everybody);
        descriptor.grantPermission(permission, everybody);
        descriptor.grantPermission(permission, unrelatedRole);
        descriptor.grantPermission(permission, unrelatedUser);
      }
      return null;
    });
    fixture.loginUser(manager);
    assertThat(PermissionUtils.hasPortalSidebarConfigurationPermission()).isTrue();
  }

  @AfterEach
  void tearDown(AppFixture fixture) {
    try {
      if (previousUser == null) {
        Ivy.session().logoutSessionUser();
      } else {
        fixture.loginUser(previousUser);
      }
    } finally {
      Sudo.get(() -> {
        for (var entry : previousEverybodyAcl.entrySet()) {
          restoreExplicitAcl(entry.getKey(), everybody, entry.getValue());
        }
        for (var member : new ISecurityMember[] {manager, unrelatedUser, unrelatedRole}) {
          if (member == null) {
            continue;
          }
          for (var permission : previousEverybodyAcl.keySet()) {
            restoreExplicitAcl(permission, member, List.of());
          }
          if (sidebarPermission != null) {
            restoreExplicitAcl(sidebarPermission, member, List.of());
          }
        }
        if (manager != null) {
          security.users().delete(manager.getName());
        }
        if (unrelatedUser != null) {
          security.users().delete(unrelatedUser.getName());
        }
        if (unrelatedRole != null) {
          security.roles().delete(unrelatedRole.getName());
        }
        return null;
      });
    }
  }

  @ParameterizedTest
  @CsvSource({
      "DASHBOARD, ACCESS_DASHBOARD, ACCESS_FULL_PROCESS_LIST",
      "PROCESS, ACCESS_FULL_PROCESS_LIST, ACCESS_DASHBOARD"
  })
  void togglingBuiltInOnlyChangesMappedEverybodyPermission(StandardMenuItemDefinitionType type,
      PortalPermission mappedPermission, PortalPermission otherPermission) {
    var target = permission(mappedPermission);
    var other = permission(otherPermission);
    var menu = standardMenu(type, MenuSource.STANDARD);
    var otherAcl = Sudo.get(() -> allExplicitAcl(other));
    var unrelatedAcl = Sudo.get(() -> explicitAclExceptEverybody(target));

    assertThat(bean.canToggleMenuPermission(menu)).isTrue();
    assertThat(bean.hasAction(menu)).isTrue();
    assertThat(bean.isStandardMenuEnabled(menu)).isTrue();
    for (boolean enabled : new boolean[] {false, false, true, true}) {
      bean.setStandardMenuEnabled(menu, enabled);
      assertThat(bean.isStandardMenuEnabled(menu)).isEqualTo(enabled);
      Sudo.get(() -> {
        assertThat(explicitAcl(target, everybody)).containsExactly(enabled);
        assertThat(allExplicitAcl(other)).containsExactlyInAnyOrderElementsOf(otherAcl);
        assertThat(explicitAclExceptEverybody(target)).containsExactlyInAnyOrderElementsOf(unrelatedAcl);
        return null;
      });
    }
  }

  @Test
  void refreshingPermissionsUpdatesPrivateDashboardAccessWithoutLeavingSidebar() {
    var ownDashboardPermission = permission(PortalPermission.DASHBOARD_WRITE_OWN);
    Sudo.get(() -> {
      descriptor.grantPermission(ownDashboardPermission, manager);
      return null;
    });
    try {
      var configuration = new DashboardConfigurationBean();
      configuration.setMenuManagement(true);
      configuration.refreshPermissions();
      assertThat(configuration.isCanEditPrivateDashboard()).isTrue();

      var menu = standardMenu(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.STANDARD);
      bean.setStandardMenuEnabled(menu, false);
      configuration.refreshPermissions();
      assertThat(configuration.isCanEditPrivateDashboard()).isFalse();
      assertThat(configuration.isMenuManagement()).isTrue();

      bean.setStandardMenuEnabled(menu, true);
      configuration.refreshPermissions();
      assertThat(configuration.isCanEditPrivateDashboard()).isTrue();
      assertThat(configuration.isMenuManagement()).isTrue();
    } finally {
      Sudo.get(() -> {
        descriptor.ungrantPermission(ownDashboardPermission, manager);
        return null;
      });
    }
  }

  @ParameterizedTest
  @EnumSource(value = StandardMenuItemDefinitionType.class, names = {"DASHBOARD", "PROCESS"})
  void togglingRequiresSidebarManagementPermission(StandardMenuItemDefinitionType type) {
    denySidebarPermission();
    var before = Sudo.get(this::bothPermissionAcls);

    for (boolean enabled : new boolean[] {false, true}) {
      assertThrows(PortalException.class,
          () -> bean.setStandardMenuEnabled(standardMenu(type, MenuSource.STANDARD), enabled));
    }

    assertThat(Sudo.get(this::bothPermissionAcls)).isEqualTo(before);
  }

  @ParameterizedTest
  @MethodSource("unsupportedMenus")
  void unsupportedTargetsAreRejectedWithoutChangingPermissions(PortalMenuItemDefinition menu) {
    var before = Sudo.get(this::bothPermissionAcls);

    assertThat(bean.canToggleMenuPermission(menu)).isFalse();
    assertThat(bean.isStandardMenuEnabled(menu)).isFalse();
    for (boolean enabled : new boolean[] {false, true}) {
      assertThrows(PortalException.class, () -> bean.setStandardMenuEnabled(menu, enabled));
    }

    assertThat(Sudo.get(this::bothPermissionAcls)).isEqualTo(before);
  }

  @Test
  void authorizationIsCheckedBeforeTargetValidation() {
    denySidebarPermission();
    var before = Sudo.get(this::bothPermissionAcls);

    for (boolean enabled : new boolean[] {false, true}) {
      var exception = assertThrows(PortalException.class, () -> bean.setStandardMenuEnabled(null, enabled));
      assertThat(exception).hasMessageContaining("Sidebar menu management requires");
    }
    assertThat(Sudo.get(this::bothPermissionAcls)).isEqualTo(before);
  }

  @Test
  void unsupportedStandardAndCallableMenusKeepTheirActionExclusions() {
    assertThat(bean.hasAction(standardMenu(StandardMenuItemDefinitionType.TASK, MenuSource.STANDARD))).isFalse();
    assertThat(bean.hasAction(standardMenu(StandardMenuItemDefinitionType.CASE, MenuSource.STANDARD))).isFalse();
    var external = new ExternalLinkMenuItemDefinition();
    external.setSource(MenuSource.CALLABLE);
    assertThat(bean.hasAction(external)).isFalse();
    external.setSource(MenuSource.CUSTOM_MENU_CONFIGURATION);
    assertThat(bean.hasAction(external)).isTrue();
  }

  @ParameterizedTest
  @CsvSource({"DASHBOARD, ACCESS_DASHBOARD", "PROCESS, ACCESS_FULL_PROCESS_LIST"})
  void statusTracksEverybodyAclRatherThanUserOrUnrelatedGrants(StandardMenuItemDefinitionType type,
      PortalPermission mappedPermission) {
    var target = permission(mappedPermission);
    var menu = standardMenu(type, MenuSource.STANDARD);
    Sudo.get(() -> {
      descriptor.denyPermission(target, manager);
      return null;
    });
    assertThat(bean.isStandardMenuEnabled(menu)).isTrue();

    for (boolean explicitDenial : new boolean[] {true, false}) {
      Sudo.get(() -> {
        restoreExplicitAcl(target, everybody, explicitDenial ? List.of(false) : List.of());
        descriptor.ungrantPermission(target, manager);
        descriptor.undenyPermission(target, manager);
        descriptor.grantPermission(target, manager);
        assertThat(descriptor.getPermissionAccess(target, everybody).getAccessState())
            .isEqualTo(explicitDenial ? AccessState.EXPLICIT_DENIED : AccessState.NOT_GRANTED_OR_DENIED);
        return null;
      });
      assertThat(bean.isStandardMenuEnabled(menu)).isFalse();
      var unrelatedAcl = Sudo.get(() -> explicitAclExceptEverybody(target));
      var otherAcls = Sudo.get(this::bothPermissionAcls);
      otherAcls.remove(target);
      bean.setStandardMenuEnabled(menu, true);
      assertThat(bean.isStandardMenuEnabled(menu)).isTrue();
      Sudo.get(() -> {
        assertThat(explicitAcl(target, everybody)).containsExactly(true);
        assertThat(explicitAclExceptEverybody(target)).containsExactlyInAnyOrderElementsOf(unrelatedAcl);
        otherAcls.forEach((permission, acl) ->
            assertThat(allExplicitAcl(permission)).containsExactlyInAnyOrderElementsOf(acl));
        return null;
      });
    }
  }

  private void denySidebarPermission() {
    Sudo.get(() -> {
      descriptor.denyPermission(sidebarPermission, manager);
      return null;
    });
    assertThat(PermissionUtils.hasPortalSidebarConfigurationPermission()).isFalse();
  }

  private IPermission permission(PortalPermission portalPermission) {
    var permission = IPermissionRepository.instance().findByName(portalPermission.getValue());
    assertThat(permission).as("Registered permission %s", portalPermission.getValue()).isNotNull();
    return permission;
  }

  private List<Boolean> explicitAcl(IPermission permission, ISecurityMember member) {
    return descriptor.getAccessControlsForPermission(permission).stream()
        .filter(acl -> acl.getSecurityMember().getSecurityMemberId().equals(member.getSecurityMemberId()))
        .map(acl -> acl.isGranted()).toList();
  }

  private List<ExplicitAcl> allExplicitAcl(IPermission permission) {
    return descriptor.getAccessControlsForPermission(permission).stream()
        .map(acl -> new ExplicitAcl(acl.getSecurityMember().getSecurityMemberId(), acl.isGranted())).toList();
  }

  private List<ExplicitAcl> explicitAclExceptEverybody(IPermission permission) {
    return allExplicitAcl(permission).stream()
        .filter(acl -> !acl.memberId().equals(everybody.getSecurityMemberId())).toList();
  }

  private Map<IPermission, List<ExplicitAcl>> bothPermissionAcls() {
    var result = new LinkedHashMap<IPermission, List<ExplicitAcl>>();
    previousEverybodyAcl.keySet().forEach(permission -> result.put(permission, allExplicitAcl(permission)));
    return result;
  }

  private void restoreExplicitAcl(IPermission permission, ISecurityMember member, List<Boolean> previous) {
    descriptor.ungrantPermission(permission, member);
    descriptor.undenyPermission(permission, member);
    for (boolean granted : previous) {
      if (granted) {
        descriptor.grantPermission(permission, member);
      } else {
        descriptor.denyPermission(permission, member);
      }
    }
  }

  private static Stream<Arguments> unsupportedMenus() {
    var external = new ExternalLinkMenuItemDefinition();
    external.setSource(MenuSource.STANDARD);
    return Stream.of(
        Arguments.of((Object) null),
        Arguments.of(standardMenu(StandardMenuItemDefinitionType.TASK, MenuSource.STANDARD)),
        Arguments.of(standardMenu(StandardMenuItemDefinitionType.CASE, MenuSource.STANDARD)),
        Arguments.of(standardMenu(null, MenuSource.STANDARD)),
        Arguments.of(standardMenu(StandardMenuItemDefinitionType.DASHBOARD, MenuSource.CALLABLE)),
        Arguments.of(standardMenu(StandardMenuItemDefinitionType.PROCESS, MenuSource.CUSTOM_MENU_CONFIGURATION)),
        Arguments.of(standardMenu(StandardMenuItemDefinitionType.DASHBOARD, null)),
        Arguments.of(external));
  }

  private static StandardMenuItemDefinition standardMenu(StandardMenuItemDefinitionType type, MenuSource source) {
    var menu = new StandardMenuItemDefinition();
    menu.setStandardType(type);
    menu.setSource(source);
    return menu;
  }

  private record ExplicitAcl(String memberId, boolean granted) {}

}
