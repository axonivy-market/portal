package ch.ivy.addon.portalkit.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.service.ProjectVersionService.ApplicationInfo;
import ch.ivyteam.ivy.application.app.Application;
import ch.ivyteam.ivy.application.app.state.ActivityState;
import ch.ivyteam.ivy.application.app.state.AppState;
import ch.ivyteam.ivy.application.app.state.ReleaseState;
import ch.ivyteam.ivy.application.project.AppProjects;
import ch.ivyteam.ivy.application.project.MavenCoordinates;
import ch.ivyteam.ivy.application.project.Project;
import ch.ivyteam.ivy.application.project.ProjectState;
import ch.ivyteam.ivy.application.project.ProjectState.ProjectMode;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestProjectVersionService {

  private final ProjectVersionService service = ProjectVersionService.getInstance();

  @Test
  void applicationInfo_sortsVersionsDescending() {
    ApplicationInfo appInfo = new ApplicationInfo("Developer-portal", List.of(
        fakeApplication(1, ReleaseState.RELEASED, ActivityState.ACTIVE),
        fakeApplication(3, ReleaseState.ARCHIVED, ActivityState.ACTIVE),
        fakeApplication(2, ReleaseState.CREATED, ActivityState.ACTIVE)));

    assertThat(appInfo.getVersions()).extracting(Application::version).containsExactly(3, 2, 1);
  }

  @Test
  void applicationInfo_defaultsToNewestReleasedVersion_notHighestVersionNumber() {
    Application released = fakeApplication(1, ReleaseState.RELEASED, ActivityState.ACTIVE);
    Application created = fakeApplication(2, ReleaseState.CREATED, ActivityState.ACTIVE);
    Application archived = fakeApplication(3, ReleaseState.ARCHIVED, ActivityState.ACTIVE);

    ApplicationInfo appInfo = new ApplicationInfo("Developer-portal", List.of(released, created, archived));

    assertThat(appInfo.getSelectedVersion()).isEqualTo(released);
  }

  @Test
  void applicationInfo_fallsBackToHighestVersion_whenNoneReleased() {
    Application created = fakeApplication(1, ReleaseState.CREATED, ActivityState.ACTIVE);
    Application archived = fakeApplication(2, ReleaseState.ARCHIVED, ActivityState.ACTIVE);

    ApplicationInfo appInfo = new ApplicationInfo("Developer-portal", List.of(created, archived));

    assertThat(appInfo.getSelectedVersion()).isEqualTo(archived);
  }

  @Test
  void applicationInfo_hasNoSelectedVersionOrProjects_whenNoVersionsExist() {
    ApplicationInfo appInfo = new ApplicationInfo("Empty-app", List.of());

    assertThat(appInfo.getSelectedVersion()).isNull();
    assertThat(appInfo.getProjects()).isEmpty();
  }

  @Test
  void applicationInfo_getProjects_excludesPortalLibraryAndSortsByNameCaseInsensitively() {
    Project zeta = fakeProject("zeta", "com.axonivy.portal:zeta", "1.0", ProjectMode.OK);
    Project alpha = fakeProject("Alpha", "com.axonivy.portal:alpha", "1.0", ProjectMode.OK);
    Project portalLib = fakeProject("portal", PortalConstants.PORTAL_LIBRARY_ID, "1.0", ProjectMode.OK);

    Application version = fakeApplicationWithProjects(1, ReleaseState.RELEASED, ActivityState.ACTIVE,
        List.of(zeta, alpha, portalLib));
    ApplicationInfo appInfo = new ApplicationInfo("Developer-portal", List.of(version));

    assertThat(appInfo.getProjects()).extracting(Project::name).containsExactly("Alpha", "zeta");
  }

  @Test
  void applicationInfo_selectingAnotherVersion_switchesProjects() {
    Project v1Project = fakeProject("v1-project", "id1", "1.0", ProjectMode.OK);
    Project v2Project = fakeProject("v2-project", "id2", "2.0", ProjectMode.OK);
    Application v1 = fakeApplicationWithProjects(1, ReleaseState.RELEASED, ActivityState.ACTIVE, List.of(v1Project));
    Application v2 = fakeApplicationWithProjects(2, ReleaseState.CREATED, ActivityState.ACTIVE, List.of(v2Project));

    ApplicationInfo appInfo = new ApplicationInfo("Developer-portal", List.of(v1, v2));
    assertThat(appInfo.getProjects()).extracting(Project::name).containsExactly("v1-project");

    appInfo.setSelectedVersion(v2);
    assertThat(appInfo.getProjects()).extracting(Project::name).containsExactly("v2-project");
  }

  @Test
  void releaseState_mapsToExpectedStyleAndIcon() {
    assertThat(service.getReleaseStateStyle(fakeApplication(1, ReleaseState.RELEASED, ActivityState.ACTIVE))).isEqualTo("released");
    assertThat(service.getReleaseStateIcon(fakeApplication(1, ReleaseState.RELEASED, ActivityState.ACTIVE))).isEqualTo("pi-check-circle");

    assertThat(service.getReleaseStateStyle(fakeApplication(1, ReleaseState.DEPRECATED, ActivityState.ACTIVE))).isEqualTo("deprecated");
    assertThat(service.getReleaseStateIcon(fakeApplication(1, ReleaseState.DEPRECATED, ActivityState.ACTIVE))).isEqualTo("pi-times-circle");

    assertThat(service.getReleaseStateStyle(fakeApplication(1, ReleaseState.CREATED, ActivityState.ACTIVE))).isEqualTo("created");
    assertThat(service.getReleaseStateIcon(fakeApplication(1, ReleaseState.CREATED, ActivityState.ACTIVE))).isEqualTo("pi-clock");

    assertThat(service.getReleaseStateStyle(fakeApplication(1, ReleaseState.PREPARED, ActivityState.ACTIVE))).isEqualTo("prepared");
    assertThat(service.getReleaseStateIcon(fakeApplication(1, ReleaseState.PREPARED, ActivityState.ACTIVE))).isEqualTo("pi-clock");

    assertThat(service.getReleaseStateStyle(fakeApplication(1, ReleaseState.ARCHIVED, ActivityState.ACTIVE))).isEqualTo("archived");
    assertThat(service.getReleaseStateIcon(fakeApplication(1, ReleaseState.ARCHIVED, ActivityState.ACTIVE))).isEqualTo("pi-question-circle");
  }

  @Test
  void activityState_mapsToExpectedStyleAndIcon() {
    assertThat(service.getActivityStateStyle(fakeApplication(1, ReleaseState.RELEASED, ActivityState.ACTIVE))).isEqualTo("active");
    assertThat(service.getActivityStateIcon(fakeApplication(1, ReleaseState.RELEASED, ActivityState.ACTIVE))).isEqualTo("pi-play");

    assertThat(service.getActivityStateStyle(fakeApplication(1, ReleaseState.RELEASED, ActivityState.INACTIVE))).isEqualTo("inactive");
    assertThat(service.getActivityStateIcon(fakeApplication(1, ReleaseState.RELEASED, ActivityState.INACTIVE))).isEqualTo("pi-exclamation-triangle");
  }

  @Test
  void projectMode_mapsToExpectedIcon() {
    assertThat(service.getProjectModeIcon(fakeProject("p", "id", "1.0", ProjectMode.OK))).isEqualTo("pi-check-circle");
    assertThat(service.getProjectModeIcon(fakeProject("p", "id", "1.0", ProjectMode.MISSING))).isEqualTo("pi-exclamation-triangle");
    assertThat(service.getProjectModeIcon(fakeProject("p", "id", "1.0", ProjectMode.OUTDATED))).isEqualTo("pi-exclamation-triangle");
    assertThat(service.getProjectModeIcon(fakeProject("p", "id", "1.0", ProjectMode.TOO_OLD))).isEqualTo("pi-exclamation-triangle");
    assertThat(service.getProjectModeIcon(fakeProject("p", "id", "1.0", ProjectMode.TOO_NEW))).isEqualTo("pi-exclamation-triangle");
    assertThat(service.getProjectModeIcon(fakeProject("p", "id", "1.0", ProjectMode.UNKNOWN))).isEqualTo("pi-question-circle");
  }

  @Test
  void translate_resolvesRealCmsKeys() {
    assertThat(service.translateReleaseState(ReleaseState.RELEASED)).isEqualTo("Released");
    assertThat(service.translateActivityState(ActivityState.ACTIVE)).isEqualTo("Active");
    assertThat(service.translateProjectMode(ProjectMode.OK)).isEqualTo("OK");
  }

  private static Application fakeApplication(int version, ReleaseState releaseState, ActivityState activityState) {
    return fakeApplicationWithProjects(version, releaseState, activityState, List.of());
  }

  private static Application fakeApplicationWithProjects(int version, ReleaseState releaseState,
      ActivityState activityState, List<Project> projects) {
    AppState state = fake(AppState.class, Map.of("releaseState", releaseState, "activityState", activityState));
    AppProjects appProjects = fake(AppProjects.class, Map.of("all", (Supplier<Stream<Project>>) projects::stream));
    Map<String, Object> stubs = new HashMap<>();
    stubs.put("version", version);
    stubs.put("state", state);
    stubs.put("projects", appProjects);
    return fake(Application.class, stubs);
  }

  private static Project fakeProject(String name, String mavenId, String mavenVersion, ProjectMode mode) {
    MavenCoordinates coordinates = fake(MavenCoordinates.class, Map.of("id", mavenId, "version", mavenVersion));
    ProjectState state = fake(ProjectState.class, Map.of("mode", mode));
    Map<String, Object> stubs = new HashMap<>();
    stubs.put("name", name);
    stubs.put("mavenCoordinates", coordinates);
    stubs.put("state", state);
    return fake(Project.class, stubs);
  }

  @SuppressWarnings("unchecked")
  private static <T> T fake(Class<T> type, Map<String, Object> stubs) {
    return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] {type}, (proxy, method, args) -> {
      switch (method.getName()) {
        case "equals":
          return proxy == args[0];
        case "hashCode":
          return System.identityHashCode(proxy);
        case "toString":
          return type.getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(proxy));
        default:
          break;
      }
      Object stub = stubs.get(method.getName());
      if (stub instanceof Supplier<?> supplier) {
        return supplier.get();
      }
      if (stubs.containsKey(method.getName())) {
        return stub;
      }
      throw new UnsupportedOperationException(type.getSimpleName() + "#" + method.getName());
    });
  }
}
