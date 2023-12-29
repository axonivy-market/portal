package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.page.LoginPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class ForgotPasswordScreenshotTest extends ScreenshotBaseTest {

  @Test
  public void testForgotPassword() throws IOException {
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    LoginPage loginPage = homePage.clickOnLogout();
    loginPage.forgotPassword();
    ScreenshotUtil.resizeBrowser(new Dimension(1024, 768));
    loginPage.waitForEmailAddressIsFocused();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.FORGOT_PASSWORD + "send-email-screen");
  }
}
