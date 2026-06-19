// package com.axonivy.portal.selenium.a11y;

// import static org.assertj.core.api.Assertions.assertThat;

// import java.util.Arrays;
// import java.util.Collections;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.openqa.selenium.WebDriver;

// import com.axonivy.ivy.webtest.IvyWebTest;
// import com.axonivy.portal.selenium.common.BaseTest;
// import com.axonivy.portal.selenium.common.RecordLoginStatusCode;
// import com.axonivy.portal.selenium.common.SystemProperties;
// import com.axonivy.portal.selenium.common.TestAccount;
// import com.axonivy.portal.selenium.page.LoginPage;
// import com.axonivy.portal.selenium.page.MainMenuPage;
// import com.axonivy.portal.selenium.page.NewDashboardPage;
// import com.codeborne.selenide.Selenide;
// import com.codeborne.selenide.WebDriverRunner;
// import com.deque.html.axecore.results.Results;
// import com.deque.html.axecore.results.Rule;
// import com.deque.html.axecore.selenium.AxeBuilder;
// import com.deque.html.axecore.selenium.AxeReporter;

// @IvyWebTest
// public class A11yTest extends BaseTest {

//   private static final List<String> WCAG_TAGS = Arrays.asList("wcag2a", "wcag2aa", "wcag21aa", "best-practice");

//   @Override
//   @BeforeEach
//   public void setup() {
//     super.setup();
//     redirectToRelativeLink(createTestingTasksUrl);
//   }

//   @Test
//   public void loginPage_shouldHaveNoViolations() {
//     redirectToNewDashBoard();
//     RecordLoginStatusCode LoginStatus = new RecordLoginStatusCode();
//     Selenide.webdriver().driver().getProxy().addResponseFilter(LoginStatus.LOGIN, LoginStatus);
//     if (!SystemProperties.isInServerMode()) {
//       launchBrowserAndLogoutInDesigner();
//     }
//     new LoginPage().waitPageLoaded();
//     assertNoViolations();
//   }

//   @Test
//   public void dashboard_shouldHaveNoViolations() {
//     redirectToNewDashBoard();
//     var homePage = new NewDashboardPage();
//     homePage.waitForCaseWidgetLoaded();
//     homePage.waitForTaskWidgetLoaded();
//     assertNoViolations();
//   }

//   // @Test
//   // public void dashboardConfiguration_shouldHaveNoViolations() {
//   //   redirectToRelativeLink(DASHBOARD_CONFIG_URL);
//   //   $(".dashboard-configuration-container").shouldBe(Condition.visible);
//   //   assertNoViolations();
//   // }

//   // @Test
//   // public void taskDetails_shouldHaveNoViolations() {
//   //   redirectToRelativeLink(createTestingTasksUrl);
//   //   redirectToRelativeLink(TASK_LIST_URL);
//   //   $(".js-task-start-sidebar").shouldBe(Condition.visible);
//   //   $(".js-task-start-sidebar").click();
//   //   $(".task-details__container").shouldBe(Condition.visible);
//   //   assertNoViolations();
//   // }

//   // @Test
//   // public void caseDetails_shouldHaveNoViolations() {
//   //   redirectToRelativeLink(createTestingTasksUrl);
//   //   redirectToRelativeLink(CASE_LIST_URL);
//   //   $(".js-case-item-name").shouldBe(Condition.visible);
//   //   $(".js-case-item-name").click();
//   //   $(".case-details-title").shouldBe(Condition.visible);
//   //   assertNoViolations();
//   // }

//   @Test
//   public void processList_shouldHaveNoViolations() {
//     var newDashboardPage = new NewDashboardPage();
//     MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
//     mainMenuPage.selectProcessesMenu();
//     assertNoViolations();
//   }

//   @Test
//   public void userProfileDialog_shouldHaveNoViolations() {
//     login(TestAccount.ADMIN_USER);
//     showNewDashboard();
//     NewDashboardPage homePage = new NewDashboardPage();
//     homePage.openMyProfilePage();
//     assertNoViolations();
//   }

//   @Test
//   public void adminSettings_shouldHaveNoViolations() {
//     login(TestAccount.ADMIN_USER);
//     NewDashboardPage newDashboardPage = new NewDashboardPage();
//     assertEquals(true, newDashboardPage.isAdminSettingsMenuItemPresent());
//     newDashboardPage.openAdminSettings();
//     assertNoViolations();
//   }

//   // ---------------------------------------------------------------------------
//   // Helpers
//   // ---------------------------------------------------------------------------

//   /**
//    * Runs axe-core with WCAG 2.1 AA tags against the current page and asserts
//    * zero violations, printing a readable summary of each violation on failure.
//    */
//   private void assertNoViolations() {
//     WebDriver driver = WebDriverRunner.getWebDriver();
    
//     Results results = new AxeBuilder()
//         .withTags(WCAG_TAGS)
//         .analyze(driver);

//     // Strip passed and inapplicable results to prevent bloated JSON sizes
//     results.setPasses(Collections.emptyList());
//     results.setInapplicable(Collections.emptyList());

//     String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
//     AxeReporter.writeResultsToJsonFile("target/axe-results-" + methodName, results);

//     List<Rule> violations = results.getViolations();
//     if (!violations.isEmpty()) {
//       String summary = violations.stream()
//           .map(v -> String.format("[%s] impact=%s nodes=%d — %s (%s)",
//               v.getId(),
//               v.getImpact(),
//               v.getNodes().size(),
//               v.getHelp(),
//               v.getHelpUrl()))
//           .collect(Collectors.joining("\n  "));
//       assertThat(violations)
//           .as("axe-core violations on <%s>:\n  %s", driver.getCurrentUrl(), summary)
//           .isEmpty();
//     }
//   }
// }
