/**
 * Update later on selenium, currently it's on GuiTest package
 */
//package com.axonivy.portal.selenium.test;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.axonivy.ivy.webtest.IvyWebTest;
//import com.axonivy.portal.selenium.common.BaseTest;
//import com.axonivy.portal.selenium.common.TestAccount;
//import com.axonivy.portal.selenium.page.AdminSettingsPage;
//import com.axonivy.portal.selenium.page.NewDashboardPage;
//
//@IvyWebTest
//public class ClientSideTimeoutTest extends BaseTest {
//
//  @Override
//  @BeforeEach
//  public void setup() {
//    super.setup();
//    setupWithAlternativeLinkAndAccount(NewDashboardPage.PORTAL_HOME_PAGE_URL, TestAccount.ADMIN_USER);
//  }
//
//  @Test
//  public void testShowWarningDialog() {
//    AdminSettingsPage adminSettingPage = setupClientSideTimeout2Minutes();
//    assertTrue(adminSettingPage.isWarningDialogShowWhenTimeoutIsLosing());
//  }
//
//  @Test
//  public void testShowInformDialog() {
//    AdminSettingsPage adminSettingPage = setupClientSideTimeout2Minutes();
//    assertTrue(adminSettingPage.isInformDialogShowAfterTimeout());
//  }
//
//  private AdminSettingsPage setupClientSideTimeout2Minutes() {
//    NewDashboardPage newDashboardPage = new NewDashboardPage();
//    newDashboardPage.waitPageLoaded();
//    AdminSettingsPage adminSettingPage = newDashboardPage.openAdminSettings();
//    adminSettingPage.setClientSideTimeout("2");
//    refreshPage();
//    return adminSettingPage;
//  }
//
//  @AfterEach
//  public void cleanupSettingAfterTest() {
//    redirectToRelativeLink(cleanupDataLink);
//  }
//
//}
