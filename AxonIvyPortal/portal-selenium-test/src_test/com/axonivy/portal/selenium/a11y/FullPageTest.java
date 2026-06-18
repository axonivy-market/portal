package com.axonivy.portal.selenium.a11y;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;

/**
 * Accessibility audit tests using axe-core (WCAG 2.1 AA).
 *
 * Each test method navigates to a specific Portal page or opens a dialog, then
 * runs axe-core and asserts zero violations. Violations are reported with their
 * rule ID, impact level, and affected node count so failures are immediately
 * actionable in CI.
 *
 * To add a new page: create a new //@Test method, navigate/interact as you would
 * in any Selenium test, then call assertNoViolations(getDriver()).
 */
@IvyWebTest
public class FullPageTest extends BaseTest {

  private static final List<String> WCAG_TAGS = Arrays.asList("wcag2a", "wcag2aa", "wcag21aa", "best-practice");

  private static final String PORTAL_APP = "portal/1549F58C18A6C562";
  private static final String DASHBOARD_URL = PORTAL_APP + "/DashboardPage.ivp?dashboardId=1";
  private static final String TASK_LIST_URL = PORTAL_APP + "/DefaultTaskListPage.ivp";
  private static final String CASE_LIST_URL = PORTAL_APP + "/CaseListPage.ivp";
  private static final String PROCESS_LIST_URL = PORTAL_APP + "/DefaultProcessStartListPage.ivp";
  private static final String ABSENCE_URL = PORTAL_APP + "/AbsencePage.ivp";
  private static final String DASHBOARD_CONFIG_URL = PORTAL_APP + "/PortalDashboardConfiguration.ivp";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
  }

  // ---------------------------------------------------------------------------
  // Auth
  // ---------------------------------------------------------------------------

  //@Test
  public void loginPage_shouldHaveNoViolations() {
    launchBrowserAndGotoRelativeLink("PortalKitTestHelper/1636734E13CEC872/login.ivp");
    assertNoViolations();
  }

  // ---------------------------------------------------------------------------
  // Dashboard
  // ---------------------------------------------------------------------------

  @Test
  public void dashboard_shouldHaveNoViolations() {
    redirectToRelativeLink(DASHBOARD_URL);
    $(".js-dashboard__wrapper").shouldBe(Condition.visible);
    assertNoViolations();
  }

  //@Test
  public void dashboardConfiguration_shouldHaveNoViolations() {
    redirectToRelativeLink(DASHBOARD_CONFIG_URL);
    $(".dashboard-configuration-container").shouldBe(Condition.visible);
    assertNoViolations();
  }

  // ---------------------------------------------------------------------------
  // Task
  // ---------------------------------------------------------------------------

  //@Test
  public void taskList_shouldHaveNoViolations() {
    redirectToRelativeLink(TASK_LIST_URL);
    $(".js-task-widget-panel").shouldBe(Condition.visible);
    assertNoViolations();
  }

  //@Test
  public void taskDetails_shouldHaveNoViolations() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(TASK_LIST_URL);
    $(".js-task-start-sidebar").shouldBe(Condition.visible);
    $(".js-task-start-sidebar").click();
    $(".task-details__container").shouldBe(Condition.visible);
    assertNoViolations();
  }

  // ---------------------------------------------------------------------------
  // Case
  // ---------------------------------------------------------------------------

  //@Test
  public void caseList_shouldHaveNoViolations() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(CASE_LIST_URL);
    $(".js-case-widget-panel").shouldBe(Condition.visible);
    assertNoViolations();
  }

  //@Test
  public void caseDetails_shouldHaveNoViolations() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(CASE_LIST_URL);
    $(".js-case-item-name").shouldBe(Condition.visible);
    $(".js-case-item-name").click();
    $(".case-details-title").shouldBe(Condition.visible);
    assertNoViolations();
  }

  // ---------------------------------------------------------------------------
  // Process
  // ---------------------------------------------------------------------------

  //@Test
  public void processList_shouldHaveNoViolations() {
    redirectToRelativeLink(PROCESS_LIST_URL);
    $(".js-process-start-list-item").shouldBe(Condition.visible);
    assertNoViolations();
  }

  // ---------------------------------------------------------------------------
  // User
  // ---------------------------------------------------------------------------

  //@Test
  public void absencePage_shouldHaveNoViolations() {
    redirectToRelativeLink(ABSENCE_URL);
    $(".absence-container").shouldBe(Condition.visible);
    assertNoViolations();
  }

  //@Test
  public void userProfileDialog_shouldHaveNoViolations() {
    redirectToRelativeLink(DASHBOARD_URL);
    $(".js-dashboard__wrapper").shouldBe(Condition.visible);
    $(".js-user-avatar-button").shouldBe(Condition.visible).click();
    $(".user-profile-menu").shouldBe(Condition.visible);
    // Audit the open user menu
    assertNoViolations();
  }

  // ---------------------------------------------------------------------------
  // Leave request form (iframe dialog triggered from process start)
  // ---------------------------------------------------------------------------

  //@Test
  public void leaveRequestForm_shouldHaveNoViolations() {
    redirectToRelativeLink(createTestingTasksUrl + "?embedInFrame=");
    // WaitHelper.waitForPresent($("form"));
    assertNoViolations();
  }

  // ---------------------------------------------------------------------------
  // Admin
  // ---------------------------------------------------------------------------

  //@Test
  public void adminSettings_shouldHaveNoViolations() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(PORTAL_APP + "/AdminSettingPage.ivp");
    $(".admin-setting-container").shouldBe(Condition.visible);
    assertNoViolations();
  }

  // ---------------------------------------------------------------------------
  // Helpers
  // ---------------------------------------------------------------------------

  /**
   * Runs axe-core with WCAG 2.1 AA tags against the current page and asserts
   * zero violations, printing a readable summary of each violation on failure.
   */
  private void assertNoViolations() {
    WebDriver driver = WebDriverRunner.getWebDriver();
    Results results = new AxeBuilder()
        .withTags(WCAG_TAGS)
        .analyze(driver);

    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
    AxeReporter.writeResultsToJsonFile("target/axe-results-" + methodName + ".json", results);

    List<Rule> violations = results.getViolations();
    if (!violations.isEmpty()) {
      String summary = violations.stream()
          .map(v -> String.format("[%s] impact=%s nodes=%d — %s (%s)",
              v.getId(),
              v.getImpact(),
              v.getNodes().size(),
              v.getHelp(),
              v.getHelpUrl()))
          .collect(Collectors.joining("\n  "));
      assertThat(violations)
          .as("axe-core violations on <%s>:\n  %s", driver.getCurrentUrl(), summary)
          .isEmpty();
    }
  }
}
