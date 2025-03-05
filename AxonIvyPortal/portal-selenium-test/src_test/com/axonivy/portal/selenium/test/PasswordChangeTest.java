package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.ChangePasswordPage;
import com.axonivy.portal.selenium.page.LoginPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class PasswordChangeTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    setupWithAlternativeLinkAndAccount("portalKitTestHelper/153CACC26D0D4C3D/createTestUser.ivp",
        TestAccount.TEST_CHANGE_PASSWORD_USER);
  }

  @Test
  public void passwordChangeTest() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();

    String newPassword = "abc";

    ChangePasswordPage changePasswordPage = newDashboardPage.openChangePasswordPage();

    changePasswordPage.changePassword("random password", newPassword);
    changePasswordPage.isWrongCurrentPasswordError();

    changePasswordPage.changePassword(TestAccount.TEST_CHANGE_PASSWORD_USER.getPassword(), newPassword);
    changePasswordPage.isNewPasswordNotStrongEnough();

    newPassword = "a2C!";
    changePasswordPage.changePassword(TestAccount.TEST_CHANGE_PASSWORD_USER.getPassword(), newPassword);
    newDashboardPage.clickOnLogout();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.TEST_CHANGE_PASSWORD_USER);
    loginPage.login(TestAccount.TEST_CHANGE_PASSWORD_USER.getUsername(), newPassword, true);
    newDashboardPage.waitPageLoaded();

    assertTrue(newDashboardPage.isDisplayed());
  }

}
