package portal.guitest.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.NewDashboardPage2;

public class ClientSideTimeoutTest extends BaseTest {
  
  @Override
  @Before
  public void setup() {
    super.setup();
    setupWithAlternativeLinkAndAccount(NewDashboardPage2.PORTAL_HOME_PAGE_URL, TestAccount.ADMIN_USER);
  }
  
  @Test
  public void testShowWarningDialog(){
    AdminSettingsPage adminSettingPage = setupClientSideTimeout2Minutes();
    assertTrue(adminSettingPage.isWarningDialogShowWhenTimeoutIsLosing());
  }

  @Test
  public void testShowInformDialog(){
    AdminSettingsPage adminSettingPage = setupClientSideTimeout2Minutes();
    assertTrue(adminSettingPage.isInformDialogShowAfterTimeout());
  }
  
  private AdminSettingsPage setupClientSideTimeout2Minutes() {
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    AdminSettingsPage adminSettingPage = newDashboardPage2.openAdminSettings();
    adminSettingPage.setClientSideTimeout("2");
    refreshPage();
    return adminSettingPage;
  }
  
  @After
  public void cleanupSettingAfterTest() {
    redirectToRelativeLink(cleanupDataLink);
  }
  
}
