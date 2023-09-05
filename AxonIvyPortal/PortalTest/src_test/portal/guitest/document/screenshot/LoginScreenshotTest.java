package portal.guitest.document.screenshot;

import org.junit.Before;

import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.page.HomePage;
import vn.wawa.guitest.base.client.Browser;

public class LoginScreenshotTest extends ScreenshotTest {
  
  @Before
  @Override
  public void setup() {
    setBrowser(Browser.getBrowser());
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    if (SecurityServiceUtils.isDesigner()) {
      logoutDesigner();
    }
  }
}
