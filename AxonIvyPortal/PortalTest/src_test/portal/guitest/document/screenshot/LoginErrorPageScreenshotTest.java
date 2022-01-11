package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import vn.wawa.guitest.base.page.AbstractPage;

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
    waitForPageLoad();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.LOGIN_FOLDER + "login-error-page");
    updateGlobalVariable(PORTAL_LOGIN_PAGE_DISPLAY, "true");
  }

  private void waitForPageLoad() {
    var loginErrorPage = new AbstractPage() {
      @Override
      protected String getLoadedLocator() {
        return "//*[contains(@class,'login-error-page')]";
      }
    };
    loginErrorPage.waitForPageLoaded();
    loginErrorPage.waitForElementDisplayed(By.className("login-panel-content"), true, 45);
  }
}
