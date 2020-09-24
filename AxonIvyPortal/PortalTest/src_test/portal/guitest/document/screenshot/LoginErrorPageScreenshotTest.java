package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;

public class LoginErrorPageScreenshotTest extends ScreenshotTest {
  
  private final String PORTAL_LOGIN_PAGE_DISPLAY = "PortalLoginPageDisplay";
  
  @Before
  @Override
  public void setup() {
    super.setup();
  }

  @Test
  public void testLoginErrorPage() throws IOException {
    updateGlobalVariable(PORTAL_LOGIN_PAGE_DISPLAY, "false");
    ScreenshotUtil.resizeBrowser(new Dimension(1024, 768));
    Sleeper.sleep(500);//wait for focus annimation finish to capture nice screenshot
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.LOGIN_FOLDER + "login-error-page");
    updateGlobalVariable(PORTAL_LOGIN_PAGE_DISPLAY, "true");
  }
}
