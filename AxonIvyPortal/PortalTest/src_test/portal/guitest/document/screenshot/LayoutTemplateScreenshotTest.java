package portal.guitest.document.screenshot;

import static ch.ivy.addon.portalkit.util.ScreenshotUtil.LAYOUT_FOLDER;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.page.HomePage;

public class LayoutTemplateScreenshotTest extends ScreenshotTest {
  
  private String applicationShowcase = "portal-developer-examples/169BDE2F368D6EC4/ApplicationShowcase.ivp";

  @Test
  public void screenshotLayoutTemplate() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 700));
    HomePage homePage = new HomePage();
    
    redirectToRelativeLink(defaultProcessImageSelectionExampleUrl);
    homePage.waitUntilLayoutWrapperDisplayed();
    homePage.waitForLeftMenuActive();
    ScreenshotUtil.capturePageScreenshot(LAYOUT_FOLDER + "basic-template");
    
    redirectToRelativeLink(applicationShowcase);
    homePage.waitUntilLayoutWrapperDisplayed();
    homePage.waitForLeftMenuActive();
    ScreenshotUtil.capturePageScreenshot(LAYOUT_FOLDER + "task-template"); 
  }
}
