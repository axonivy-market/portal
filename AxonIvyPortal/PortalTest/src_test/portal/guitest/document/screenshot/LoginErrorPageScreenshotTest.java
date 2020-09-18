package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.LoginUtils;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;

public class LoginErrorPageScreenshotTest extends ScreenshotTest {
  
  @Before
  @Override
  public void setup() {
    super.setup();
  }

  @Test
  public void testLoginErrorPage() throws IOException {
    updateGlobalVariable(LoginUtils.PORTAL_LOGIN_ERROR_MESSAGE_DISPLAY, "false");
    ScreenshotUtil.resizeBrowser(new Dimension(1024, 768));
    Sleeper.sleep(500);//wait for focus annimation finish to capture nice screenshot
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.LOGIN_FOLDER + "login-error-page");
    updateGlobalVariable(LoginUtils.PORTAL_LOGIN_ERROR_MESSAGE_DISPLAY, "true");
  }
}
