package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.UserProfilePage;

public class UserHomepageTest extends BaseTest {
  
  @Override
  @Before
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
  }

  @SuppressWarnings("unused")
  @Test
  public void testHomepageInUserProfile() {
    HomePage homePage = new HomePage();
    UserProfilePage profilePage = homePage.openMyProfilePage();
    profilePage.changeHomePageToCase();
    profilePage = profilePage.save();
    profilePage.clickOnLogo();
    new CaseWidgetPage();
  }
}
