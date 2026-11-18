package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import static com.axonivy.portal.selenium.common.Variable.GLOBAL_FOOTER_INFO;
import static com.axonivy.portal.selenium.common.Variable.TASK_HEADER_MODE;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class AdminSettingsTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(GLOBAL_FOOTER_INFO.getKey(), "");
  }

  @AfterEach
  public void restoreTaskHeaderMode() {
    updatePortalSetting(TASK_HEADER_MODE.getKey(), "DEFAULT");
  }

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
    resizeBrowserTo2kResolution();
    login(TestAccount.ADMIN_USER);
    createTestingTasks();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
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
    createTestingTasks();
    assertTrue(homePage.getGlobalFooterInfo().contains("Wawa"));
  }

  @Test
  public void testTaskHeaderModeSavedAndReopened() {
    updatePortalSetting(TASK_HEADER_MODE.getKey(), "DEFAULT");
    login(TestAccount.ADMIN_USER);
    AdminSettingsPage adminSettingsPage = new NewDashboardPage().openAdminSettings();
    adminSettingsPage.updateGlobalSingleSelectionVariable(TASK_HEADER_MODE.getKey(), "Legacy");
    assertEquals("Legacy", adminSettingsPage.getGlobalSingleSelectionLabel(TASK_HEADER_MODE.getKey()),
        "Task header mode should remain Legacy after reopening the setting dialog");
  }

  @Test
  public void testTaskHeaderModeChangedToDefaultAndReopened() {
    updatePortalSetting(TASK_HEADER_MODE.getKey(), "HIDDEN");
    login(TestAccount.ADMIN_USER);
    AdminSettingsPage adminSettingsPage = new NewDashboardPage().openAdminSettings();
    assertEquals("Hide", adminSettingsPage.getGlobalSingleSelectionLabel(TASK_HEADER_MODE.getKey()),
        "Task header mode should initially be Hide");
    adminSettingsPage.updateGlobalSingleSelectionVariable(TASK_HEADER_MODE.getKey(), "Default");
    assertEquals("Default", adminSettingsPage.getGlobalSingleSelectionLabel(TASK_HEADER_MODE.getKey()),
        "Task header mode should remain Default after reopening the setting dialog");
  }

}
