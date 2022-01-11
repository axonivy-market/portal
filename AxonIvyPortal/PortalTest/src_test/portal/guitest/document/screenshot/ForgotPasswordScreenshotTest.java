package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.SystemProperties;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import vn.wawa.guitest.base.client.Browser;

public class ForgotPasswordScreenshotTest extends ScreenshotTest {
  
  @Before
  @Override
  public void setup() {
    setBrowser(Browser.getBrowser());
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    if (!SystemProperties.isInServerMode()) {
      logoutDesigner();
    }
  }

  @Test
  public void testForgotPassword() throws IOException {
    LoginPage loginPage = new LoginPage();
    loginPage.forgotPassword();
    ScreenshotUtil.resizeBrowser(new Dimension(1024, 768));
    loginPage.waitForEmailAddressIsFocused();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.FORGOT_PASSWORD + "send-email-screen");
  }
}
