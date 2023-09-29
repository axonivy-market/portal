package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.UserProfilePage;

public class UserHomepageTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void testHomepageInUserProfile() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    UserProfilePage profilePage = newDashboardPage.openMyProfilePage();
    profilePage.changeNewDashboardPageToCase();
    profilePage.saveWithoutWaitingNavigation();
    new CaseWidgetPage();
  }
}
