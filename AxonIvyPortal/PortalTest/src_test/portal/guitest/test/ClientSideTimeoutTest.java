package portal.guitest.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
public class ClientSideTimeoutTest extends BaseTest {
  
  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
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
    new LoginPage(TestAccount.ADMIN_USER).login();
    HomePage homePage = new HomePage();
    AdminSettingsPage adminSettingPage = homePage.openAdminSettings();
    adminSettingPage.setClientSideTimeout("2");
    refreshPage();
    return adminSettingPage;
  }
  
}
