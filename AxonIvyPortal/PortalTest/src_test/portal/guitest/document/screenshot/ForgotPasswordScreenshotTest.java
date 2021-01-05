package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;
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
    LoginPage loginPage = new LoginPage();
    loginPage.forgotPassword();
  }

  @Test
  public void testForgotPassword() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1024, 768));
    Sleeper.sleep(500);//wait for focus annimation finish to capture nice screenshot
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.FORGOT_PASSWORD + "send-email-screen");
  }
}
