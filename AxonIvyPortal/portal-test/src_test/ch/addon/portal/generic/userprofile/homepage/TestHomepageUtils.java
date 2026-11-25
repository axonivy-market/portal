package ch.addon.portal.generic.userprofile.homepage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class TestHomepageUtils {

  private static final String DASHBOARD = "DASHBOARD";
  private static final String PROCESSES = "PROCESS_default-process-list";
  private static final String TASKS = "MAIN_DASHBOARD_default-task-list-dashboard";

  private static Homepage homepage(String name) {
    Homepage homepage = new Homepage();
    homepage.setName(name);
    return homepage;
  }

  @Test
  void resolveHomepage_personalHomepageAccessible_returnsPersonal() {
    List<Homepage> homepages = List.of(homepage(PROCESSES), homepage(TASKS));

    Homepage selected = HomepageUtils.resolveHomepage(homepages, TASKS, PROCESSES);

    assertThat(selected.getName()).isEqualTo(TASKS);
  }

  @Test
  void resolveHomepage_personalNotAccessible_fallsBackToGlobal() {
    List<Homepage> homepages = List.of(homepage(PROCESSES), homepage(TASKS));

    Homepage selected = HomepageUtils.resolveHomepage(homepages, DASHBOARD, TASKS);

    assertThat(selected.getName()).isEqualTo(TASKS);
  }

  @Test
  void resolveHomepage_blankPersonal_usesGlobal() {
    List<Homepage> homepages = List.of(homepage(PROCESSES), homepage(TASKS));

    Homepage selected = HomepageUtils.resolveHomepage(homepages, "", TASKS);

    assertThat(selected.getName()).isEqualTo(TASKS);
  }

  @Test
  void resolveHomepage_neitherPersonalNorGlobalAccessible_returnsFirstAccessible() {
    List<Homepage> homepages = List.of(homepage(PROCESSES), homepage(TASKS));

    Homepage selected = HomepageUtils.resolveHomepage(homepages, DASHBOARD, DASHBOARD);

    assertThat(selected.getName()).isEqualTo(PROCESSES);
  }

  @Test
  void resolveHomepage_noAccessibleHomepage_returnsNull() {
    Homepage selected = HomepageUtils.resolveHomepage(List.of(), DASHBOARD, TASKS);

    assertThat(selected).isNull();
  }
}
