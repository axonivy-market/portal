package ch.addon.portal.generic.userprofile.homepage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.dto.menu.MenuOrderEntry;
import com.axonivy.portal.dto.menu.PortalMenuItemDefinition;
import com.axonivy.portal.menu.management.MenuLoader;
import com.axonivy.portal.menu.management.enums.MenuSource;
import com.axonivy.portal.service.MenuOrderService;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.security.IUser;

@IvyTest
class TestHomepageUtils {

  private static final String DASHBOARD = HomepageType.DASHBOARD.name();
  private static final String PROCESS = HomepageType.PROCESS.name();
  private static final String DEFAULT_HOMEPAGE_KEY = GlobalVariable.DEFAULT_HOMEPAGE.getKey();

  private final MenuOrderService menuOrderService = MenuOrderService.getInstance();
  private IUser user;

  @BeforeEach
  void setup(AppFixture fixture) {
    fixture.loginUser("demo");
    user = Ivy.session().getSessionUser();
    clearHomepageChoices();
    // Skip the first-login user guide so the global default applies when no personal choice is stored
    user.setProperty(UserProperty.PORTAL_SHOW_USER_GUIDE, "false");
  }

  @AfterEach
  void tearDown() {
    Ivy.var().set(PortalVariable.MENU_ORDER.key, "");
    clearHomepageChoices();
    user.removeProperty(UserProperty.PORTAL_SHOW_USER_GUIDE);
  }

  @Test
  void findHomepage_personalChoice_winsOverGlobalDefault() {
    assumeTrue(PermissionUtils.checkAccessFullProcessListPermission(), "demo user needs process list access");
    user.setProperty(UserProperty.HOMEPAGE, PROCESS);
    Ivy.var().set(DEFAULT_HOMEPAGE_KEY, DASHBOARD);

    assertThat(HomepageUtils.findHomepage().getName()).isEqualTo(PROCESS);
  }

  @Test
  void findHomepage_globalDefault_isUsedWithoutPersonalChoice() {
    assumeTrue(PermissionUtils.checkAccessFullProcessListPermission(), "demo user needs process list access");
    Ivy.var().set(DEFAULT_HOMEPAGE_KEY, PROCESS);

    assertThat(HomepageUtils.findHomepage().getName()).isEqualTo(PROCESS);
  }

  @Test
  void findHomepage_withoutChoice_takesFirstVisibleCandidateInSidebarOrder() {
    assumeTrue(PermissionUtils.checkAccessFullProcessListPermission(), "demo user needs process list access");
    saveStandardOrder(DASHBOARD, PROCESS);
    assertThat(HomepageUtils.findHomepage().getName()).isEqualTo(DASHBOARD);

    saveStandardOrder(PROCESS, DASHBOARD);
    assertThat(HomepageUtils.findHomepage().getName()).isEqualTo(PROCESS);
  }

  @Test
  void loadHomepages_hiddenDashboard_isExcludedAsCandidate() {
    user.setProperty(UserProperty.HOMEPAGE, DASHBOARD);
    saveDashboardVisibility(false);

    assertThat(HomepageUtils.loadHomepages()).extracting(Homepage::getName).doesNotContain(DASHBOARD);
    Homepage homepage = HomepageUtils.findHomepage();
    assertThat(homepage == null ? null : homepage.getName()).isNotEqualTo(DASHBOARD);
  }

  @Test
  void hiddenPersonalChoice_isKeptButIgnored_andReenablesAfterEnable() {
    user.setProperty(UserProperty.HOMEPAGE, DASHBOARD);
    saveDashboardVisibility(false);

    assertThat(HomepageUtils.findHomepageInMyProfile().getName()).isEmpty();
    assertThat(user.getProperty(UserProperty.HOMEPAGE)).isEqualTo(DASHBOARD);

    saveDashboardVisibility(true);

    assertThat(HomepageUtils.findHomepageInMyProfile().getName()).isEqualTo(DASHBOARD);
    assertThat(HomepageUtils.findHomepage().getName()).isEqualTo(DASHBOARD);
  }

  @Test
  void findHomepage_noVisibleCandidate_returnsNullWithoutFallback() {
    hideEverySidebarEntry();
    user.setProperty(UserProperty.HOMEPAGE, DASHBOARD);
    Ivy.var().set(DEFAULT_HOMEPAGE_KEY, PROCESS);

    assertThat(HomepageUtils.loadHomepages()).isEmpty();
    assertThat(HomepageUtils.findHomepage()).isNull();
    assertThat(HomepageUtils.findDefaultHomepage()).isNull();
    assertThat(HomepageUtils.findHomepageInMyProfile().getName()).isEmpty();
    assertThat(HomepageUtils.isNoHomepageAvailable(null, false, false)).isTrue();
  }

  private void clearHomepageChoices() {
    user.removeProperty(UserProperty.HOMEPAGE);
    Ivy.var().set(DEFAULT_HOMEPAGE_KEY, "");
  }

  private void saveStandardOrder(String... standardIds) {
    List<MenuOrderEntry> entries = Arrays.stream(standardIds)
        .map(id -> new MenuOrderEntry(id, MenuSource.STANDARD))
        .toList();
    menuOrderService.save(entries);
  }

  private void saveDashboardVisibility(boolean visible) {
    menuOrderService.save(List.of(new MenuOrderEntry(DASHBOARD, MenuSource.STANDARD, visible)));
  }

  private void hideEverySidebarEntry() {
    List<PortalMenuItemDefinition> definitions = MenuLoader.loadMenuDefinitions();
    definitions.forEach(definition -> definition.setVisible(false));
    MenuLoader.persistManifest(definitions);
  }
}
