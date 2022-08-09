package portal.guitest.document.screenshot;

import static ch.ivy.addon.portalkit.util.ScreenshotUtil.ERROR_HANDLING_FOLDER;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.page.HomePage;

public class HandleErrorPageScreenshotTest extends ScreenshotTest {
  
  private String portalCustomErrorUrl = "InternalSupport/14B2FC03D2E87141/testPortalCustomErrorHandler.ivp";
  private String showIvyErrorPageUrl = "portal-developer-examples/169BDE2F368D6EC4/StartShowIvyErrorPage.ivp";
  private String portalCustom404ErrorUrl = "portal/1549F58C18A6C562/Error404Page.ivp";
  private String portalCustom500ErrorUrl = "portal/1549F58C18A6C562/Error500Page.ivp";
  
  @Test
  public void screenshotErrorPages() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    HomePage homePage = new HomePage();
    redirectToRelativeLink(portalCustomErrorUrl);
    homePage.waitUntilLayoutWrapperDisplayed();
    homePage.click(By.cssSelector("[id$=':test-error-method']"));
    homePage.waitUntilErrorMessageShowUp();
    homePage.clickOnShowMoreLinkOfErrorMessages();
    ScreenshotUtil.capturePageScreenshot(ERROR_HANDLING_FOLDER + "portal-ajax-error-handler");
    
    redirectToRelativeLink(showIvyErrorPageUrl);
    homePage.waitForElementDisplayed(By.className("exception-body"), true);
    homePage.waitForLeftMenuActive();
    ScreenshotUtil.capturePageScreenshot(ERROR_HANDLING_FOLDER + "default-ivy-error");
    
    redirectToRelativeLink(portalCustom404ErrorUrl);
    homePage.waitUntilErrorContainerDisplayed();
    homePage.waitForLeftMenuActive();
    ScreenshotUtil.capturePageScreenshot(ERROR_HANDLING_FOLDER + "404");
    
    redirectToRelativeLink(portalCustom500ErrorUrl);
    homePage.waitUntilErrorContainerDisplayed();
    homePage.waitForLeftMenuActive();
    ScreenshotUtil.capturePageScreenshot(ERROR_HANDLING_FOLDER + "500");
  }

}
