package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.LoginPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class LoginScreenshotTest extends ScreenshotBaseTest {

  @Test
  public void testLogin() throws IOException {
    login(TestAccount.ADMIN_USER);
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.clickOnLogout();
    ScreenshotUtils.resizeBrowser(new Dimension(1024, 768));
    new LoginPage().waitForUsernameInputIsFocused();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.LOGIN_FOLDER + "login-form");
  }
}
