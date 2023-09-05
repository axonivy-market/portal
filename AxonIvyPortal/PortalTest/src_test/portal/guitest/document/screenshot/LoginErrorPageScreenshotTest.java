package portal.guitest.document.screenshot;

import org.junit.Before;
import org.openqa.selenium.By;

import portal.guitest.common.ScreenshotTest;
import vn.wawa.guitest.base.page.AbstractPage;

public class LoginErrorPageScreenshotTest extends ScreenshotTest {
  
  private final String PORTAL_LOGIN_PAGE_DISPLAY = "PortalLoginPageDisplay";
  
  @Before
  @Override
  public void setup() {
    super.setup();
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
