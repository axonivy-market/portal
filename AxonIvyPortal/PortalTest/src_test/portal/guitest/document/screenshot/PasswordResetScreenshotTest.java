package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.BaseTest;
import portal.guitest.common.SystemProperties;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.PasswordResetPage;
import vn.wawa.guitest.base.client.Browser;

public class PasswordResetScreenshotTest extends BaseTest {
  private PasswordResetPage passwordResetPage;

  @Before
  @Override
  public void setup() {
    setBrowser(Browser.getBrowser());
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    if (!SystemProperties.isInServerMode()) {
      logoutDesigner();
    }
    redirectToRelativeLink(portalKitTestHelperPasswordResetUrl);
  }

  @Test
  public void testResetPassword() throws IOException {
    redirectToRelativeLink(String.format(portalPasswordResetUrl, TestAccount.TEST_FORGOT_PASSWORD_USER.getPassword(), TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    ScreenshotUtil.resizeBrowser(new Dimension(1024, 768));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.FORGOT_PASSWORD + "reset-password-screen");
  }

  @Test
  public void testResetPasswordSuccess() throws IOException {
    redirectToRelativeLink(String.format(portalPasswordResetUrl, TestAccount.TEST_FORGOT_PASSWORD_USER.getPassword(), TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    passwordResetPage = new PasswordResetPage();
    String newPassword = "a2C!";
    passwordResetPage.resetPassword(newPassword, true);
    refreshPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1024, 768));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.FORGOT_PASSWORD + "reset-password-success-screen");
  }

}