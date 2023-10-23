package com.axonivy.portal.selenium.test;

import static com.axonivy.portal.selenium.common.Variable.DEFAULT_SORT_DIRECTION_OF_CASE_LIST;
import static com.axonivy.portal.selenium.common.Variable.DEFAULT_SORT_DIRECTION_OF_TASK_LIST;
import static com.axonivy.portal.selenium.common.Variable.DEFAULT_SORT_FIELD_OF_CASE_LIST;
import static com.axonivy.portal.selenium.common.Variable.DEFAULT_SORT_FIELD_OF_TASK_LIST;
import static com.axonivy.portal.selenium.common.Variable.GLOBAL_FOOTER_INFO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

import ch.ivy.addon.portalkit.enums.SortDirection;

@IvyWebTest
public class AdminSettingsTest extends BaseTest {

  @Test
  public void whenLoginAsAdminThenAdminMenuItemDisplayed() {
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    assertEquals(true, newDashboardPage.isAdminSettingsMenuItemPresent());
    AdminSettingsPage adminSettingsPage = newDashboardPage.openAdminSettings();
    assertEquals(true, adminSettingsPage.isDisplayed());
  }

  @Test
  public void whenLoginAsNonAdminThenAdminMenuItemNotDisplayed() {
    login(TestAccount.DEMO_USER);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    assertEquals(false, newDashboardPage.isAdminSettingsMenuItemPresent());
  }

  @Test
  public void testDefaultEnvironmentInfo() {
    login(TestAccount.ADMIN_USER);
    NewDashboardPage homePage = new NewDashboardPage();
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    adminSettingsPage.setGlobalFooterInfo();
    assertTrue(homePage.getGlobalFooterInfo().contains("Wawa"));
  }

  @Test
  public void testCustomizedEnvironmentInfo() {
    updatePortalSetting(GLOBAL_FOOTER_INFO.getKey(), "Dev Team: Wawa, Env: Dev");
    login(TestAccount.ADMIN_USER);
    NewDashboardPage homePage = new NewDashboardPage();
    // Customize environment info in portal example
    redirectToRelativeLinkWithEmbedInFrame(createTaskWithIframe);

    assertTrue(homePage.getGlobalFooterInfo().contains("Wawa"));
  }

  @Test
  public void testDefaultSortOptionsForTaskList() {
    updatePortalSetting(DEFAULT_SORT_FIELD_OF_TASK_LIST.getKey(), "PRIORITY");
    updatePortalSetting(DEFAULT_SORT_DIRECTION_OF_TASK_LIST.getKey(), SortDirection.ASC.name());
    redirectToRelativeLink(cleanSessionCacheUrl);

    createTestingTasks();
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    assertEquals("high", taskWidgetPage.getPriorityOfTask(0));
    assertNotEquals("high", taskWidgetPage.getPriorityOfTask(taskWidgetPage.countTasks().size() - 1));
  }

  @Test
  public void testDefaultSortOptionsForCaseList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    updatePortalSetting(DEFAULT_SORT_FIELD_OF_CASE_LIST.getKey(), "NAME");
    updatePortalSetting(DEFAULT_SORT_DIRECTION_OF_CASE_LIST.getKey(), SortDirection.DESC.name());
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();

    MainMenuPage mainMenuPage = taskWidgetPage.openMainMenu();
    CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
    assertEquals("TestCase", caseWidgetPage.getCaseNameAt(0));
  }

}
