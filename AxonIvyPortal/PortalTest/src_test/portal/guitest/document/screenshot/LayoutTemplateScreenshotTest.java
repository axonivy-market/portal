package portal.guitest.document.screenshot;

import static ch.ivy.addon.portalkit.util.ScreenshotUtil.LAYOUT_FOLDER;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.page.HomePage;

public class LayoutTemplateScreenshotTest extends BaseTest {
  
  private String twoColumnsStartUrl = "portal-developer-examples/169BDE2F368D6EC4/StartTwoColumnTemplateShowcase.ivp";
  private String applicationShowcase = "portal-developer-examples/169BDE2F368D6EC4/ApplicationShowcase.ivp";

  @Test
  public void screenshotLayoutTemplate() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 700));
    HomePage homePage = new HomePage();
    redirectToRelativeLink(twoColumnsStartUrl);
    homePage.waitUntilLayoutWrapperDisplayed();
    Sleeper.sleep(500); // wait for Layout.js renders left menu
    ScreenshotUtil.capturePageScreenshot(LAYOUT_FOLDER + "two-column-template");
    
    redirectToRelativeLink(viewBetaCompanyProcessHistoryUrl);
    homePage.waitUntilLayoutWrapperDisplayed();
    Sleeper.sleep(500); // wait for Layout.js renders left menu
    ScreenshotUtil.capturePageScreenshot(LAYOUT_FOLDER + "basic-template");
    
    redirectToRelativeLink(applicationShowcase);
    homePage.waitUntilLayoutWrapperDisplayed();
    Sleeper.sleep(500); // wait for Layout.js renders left menu
    ScreenshotUtil.capturePageScreenshot(LAYOUT_FOLDER + "task-template"); 
  }
}
