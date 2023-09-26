package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.NewDashboardPage2;
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
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    UserProfilePage profilePage = newDashboardPage2.openMyProfilePage();
    profilePage.changeNewDashboardPage2ToCase();
    profilePage.saveWithoutWaitingNavigation();
    new CaseWidgetPage();
  }
}
