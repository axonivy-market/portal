package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.UserProfilePage;

@IvyWebTest
public class UserHomepageTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void testHomepageInUserProfile() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();
    newDashboardPage.waitForGrowlMessageDisappear();

    UserProfilePage profilePage = newDashboardPage.openMyProfilePage();
    profilePage.changeNewDashboardPageToCase();
    profilePage.saveWithoutWaitingNavigation();
    new CaseWidgetNewDashBoardPage();
  }
}
