package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.size;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.AbsencePage;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.ExpressManagementPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DisabledUserTest extends BaseTest {

  private static final String VISIBILITY_USER_FULL_NAME = "Visibility Test User";
  private static final String DISABLED_VISIBILITY_USER_FULL_DISPLAY_NAME =
      "(disabled) Visibility Test User (visibility_test_user)";
  private static final String DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME = "(disabled) Visibility Test User";
  private static final String DISABLE_VISIBILITY_USER_CREATION_LINK =
      "portalKitTestHelper/153CACC26D0D4C3D/disableVisibilityUser.ivp";
  private static final String TASK_CASE_CREATION_FOR_DISABLED_USER_LINK =
      "portalKitTestHelper/153CACC26D0D4C3D/createTaskAndCaseForDisabledUser.ivp";


  @Override
  @BeforeEach
  public void setup() {
    super.setupWithAlternativeLinkAndAccount(cleanupDataLink, TestAccount.ADMIN_USER);
    redirectToRelativeLink(DISABLE_VISIBILITY_USER_CREATION_LINK);
  }

  @Test
  public void testFilterByDisabledUserInTaskResponsibleFilter() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(TASK_CASE_CREATION_FOR_DISABLED_USER_LINK);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterByResponsible(VISIBILITY_USER_FULL_NAME);
    assertTrue(
        taskWidgetPage.getFilterValue("responsible-filter").contains(DISABLED_VISIBILITY_USER_FULL_DISPLAY_NAME));
    assertEquals(1, taskWidgetPage.countTasks().size());
    taskWidgetPage.countTasks().shouldHave(size(1));
    assertEquals(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME, taskWidgetPage.getResponsibleOfTaskAt(0));
  }

  @Test
  public void testFilterByDisabledUserInCaseCreatorFilter() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(TASK_CASE_CREATION_FOR_DISABLED_USER_LINK);
    redirectToNewDashBoard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    CaseWidgetPage caseWidgetPage = newDashboardPage.openCaseList();
    caseWidgetPage.openAdvancedFilter("Creator", "creator");
    caseWidgetPage.filterByCreator(VISIBILITY_USER_FULL_NAME);
    WaitHelper.assertTrueWithWait(
        () -> caseWidgetPage.getFilterValue("creator-filter").contains(DISABLED_VISIBILITY_USER_FULL_DISPLAY_NAME));
    caseWidgetPage.countCases().shouldHave(size(1));
    assertEquals(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME, caseWidgetPage.getCreatorAt(0));

  }

  @Test
  public void testExpressWfWithDisabledUser() {
    redirectToNewDashBoard();
    AdminSettingsPage adminSettingsPage = new NewDashboardPage().openAdminSettings();
    ExpressManagementPage expressManagementPage = adminSettingsPage.openExpressManagementTab();
    expressManagementPage.openImportDialog();
    expressManagementPage.selectJSONFile(FileHelper.getAbsolutePathToTestFile("express-wf-with-disabled-user.json"));
    expressManagementPage.clickOnDeployExpress();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    new NewDashboardPage().waitPageLoaded();
    ProcessWidgetPage processPage = NavigationHelper.navigateToProcessList();
    ExpressProcessPage expressPage = processPage.editExpressWF("Test disabled user");
    assertEquals("Test disabled user", expressPage.getProcessName());
    assertTrue(expressPage.getProcessOwnerNames().contains(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME));
    assertTrue(expressPage.getAbleToStartNames().contains(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME));
    assertTrue(expressPage.getResponsiblesOfTask(1).contains(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME));
  }

  @Test
  public void testAbsenceWithDisabledUser() {
    redirectToRelativeLink(cleanUpAbsencesAndSubstituesLink);
    AbsencePage absencePage = new NewDashboardPage().openAbsencePage();
    absencePage.setSubstitutedByAdmin(VISIBILITY_USER_FULL_NAME);
    absencePage.setDeputy(Arrays.asList(TestAccount.DEMO_USER.getFullName()), 0);
    absencePage.saveSubstitute();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    absencePage = new NewDashboardPage().openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    assertEquals(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME, absencePage.getSubstitutedByAdmin(0));
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    absencePage = new NewDashboardPage().openAbsencePage();
    absencePage.setSubstitutedByAdmin(VISIBILITY_USER_FULL_NAME);
    absencePage.getMyDeputy(0).shouldBe(Condition.text(TestAccount.DEMO_USER.getFullName()), DEFAULT_TIMEOUT);
  }
}
