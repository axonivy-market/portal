package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.FileHelper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AbsencePage;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.ExpressManagementPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskWidgetPage;

public class DisabledUserTest extends BaseTest {

  private static final String VISIBILITY_USER_FULL_NAME = "Visibility Test User";
  private static final String DISABLED_VISIBILITY_USER_FULL_DISPLAY_NAME =
      "(disabled) Visibility Test User (visibility_test_user)";
  private static final String DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME = "(disabled) Visibility Test User";
  private static final String DISABLE_VISIBILITY_USER_CREATION_LINK = "portalKitTestHelper/153CACC26D0D4C3D/disableVisibilityUser.ivp";
  private static final String TASK_CASE_CREATION_FOR_DISABLED_USER_LINK = "portalKitTestHelper/153CACC26D0D4C3D/createTaskAndCaseForDisabledUser.ivp";


  @Override
  @Before
  public void setup() {
    super.setupWithAlternativeLinkAndAccount(cleanupDataLink, TestAccount.ADMIN_USER);
    redirectToRelativeLink(DISABLE_VISIBILITY_USER_CREATION_LINK);
  }

  @Test
  public void testFilterByDisabledUserInTaskResponsibleFilter() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(TASK_CASE_CREATION_FOR_DISABLED_USER_LINK);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.filterByResponsible(VISIBILITY_USER_FULL_NAME);
    assertTrue(taskWidgetPage.getFilterValue("responsible-filter").contains(DISABLED_VISIBILITY_USER_FULL_DISPLAY_NAME));
    assertEquals(1, taskWidgetPage.countTasks());
    assertEquals(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME, taskWidgetPage.getResponsibleOfTaskAt(0));
  }

  @Test
  public void testFilterByDisabledUserInCaseCreatorFilter() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(TASK_CASE_CREATION_FOR_DISABLED_USER_LINK);
    HomePage homePage = new HomePage();
    CaseWidgetPage caseWidgetPage = homePage.openCaseList();
    caseWidgetPage.openAdvancedFilter("Creator", "creator");
    caseWidgetPage.filterByCreator(VISIBILITY_USER_FULL_NAME);
    assertTrue(caseWidgetPage.getFilterValue("creator-filter").contains(DISABLED_VISIBILITY_USER_FULL_DISPLAY_NAME));
    assertEquals(1, caseWidgetPage.countCases());
    assertEquals(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME, caseWidgetPage.getCreatorAt(0));
    
  }

  @Test
  public void testExpressWfWithDisabledUser() {
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ExpressManagementPage expressManagementPage = adminSettingsPage.openExpressManagementTab();
    expressManagementPage.openImportDialog();
    expressManagementPage.selectJSONFile(FileHelper.getAbsolutePathToTestFile("express-wf-with-disabled-user.json"));
    expressManagementPage.clickOnDeployExpress();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    ProcessWidgetPage processPage = homePage.getProcessWidget();
    processPage.expand();
    ExpressProcessPage expressPage = processPage.editExpressWF("Test disabled user");
    assertEquals("Test disabled user", expressPage.getProcessName());
    assertTrue(expressPage.getProcessOwnerNames().contains(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME));
    assertTrue(expressPage.getAbleToStartNames().contains(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME));
    assertTrue(expressPage.getResponsiblesOfTask(1).contains(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME));
  }

  @Test
  public void testAbsenceWithDisabledUser() {
    redirectToRelativeLink(cleanUpAbsencesAndSubstituesLink);
    AbsencePage absencePage = new HomePage().openAbsencePage();
    absencePage.setSubstitutedByAdmin(VISIBILITY_USER_FULL_NAME);
    absencePage.setDeputy(Arrays.asList(TestAccount.DEMO_USER.getFullName()), 0);
    absencePage.saveSubstitute();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    absencePage = new HomePage().openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    assertEquals(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME, absencePage.getSubstitutedByAdmin(0));
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    absencePage = new HomePage().openAbsencePage();
    absencePage.setSubstitutedByAdmin(VISIBILITY_USER_FULL_NAME);
    assertEquals(TestAccount.DEMO_USER.getFullName(), absencePage.getMyDeputy(0));
  }
}
