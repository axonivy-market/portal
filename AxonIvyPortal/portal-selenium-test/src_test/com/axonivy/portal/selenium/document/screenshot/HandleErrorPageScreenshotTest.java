package com.axonivy.portal.selenium.document.screenshot;

import static com.axonivy.portal.selenium.common.ScreenshotUtil.ERROR_HANDLING_FOLDER;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.page.NewDashboardPage;


@IvyWebTest
public class HandleErrorPageScreenshotTest extends ScreenshotBaseTest {
  private String portalCustomErrorUrl = "InternalSupport/14B2FC03D2E87141/testPortalCustomErrorHandler.ivp";
  private String showIvyErrorPageUrl = "portal-developer-examples/169BDE2F368D6EC4/StartShowIvyErrorPage.ivp";
  private String portalCustom404ErrorUrl = "portal/1549F58C18A6C562/Error404Page.ivp";
  private String portalCustom500ErrorUrl = "portal/1549F58C18A6C562/Error500Page.ivp";

  @Test
  public void screenshotErrorPages() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));

    // WAIT ROBOT FIX SCREENSHOT ERROR
    /*
     * redirectToRelativeLink(portalCustomErrorUrl); $("[id$=':test-error-method']")
     * .shouldBe(exist, DEFAULT_TIMEOUT) .shouldBe(visible,
     * DEFAULT_TIMEOUT).click();
     * 
     * $(".notification-container").shouldBe(appear, DEFAULT_TIMEOUT);
     * $("a[class$='notification-content-action-more-details']") .shouldBe(exist,
     * DEFAULT_TIMEOUT) .shouldBe(visible, DEFAULT_TIMEOUT).click();
     * 
     * ScreenshotUtil.capturePageScreenshot(ERROR_HANDLING_FOLDER +
     * "portal-ajax-error-handler");
     */

    
    redirectToRelativeLink(showIvyErrorPageUrl);
    $(".exception-body").shouldBe(appear, DEFAULT_TIMEOUT);
    ScreenshotUtil.capturePageScreenshot(ERROR_HANDLING_FOLDER + "default-ivy-error");

    redirectToRelativeLink(portalCustom404ErrorUrl);
    $(".error-container").shouldBe(appear, DEFAULT_TIMEOUT);
    ScreenshotUtil.capturePageScreenshot(ERROR_HANDLING_FOLDER + "404");

    redirectToRelativeLink(portalCustom500ErrorUrl);
    $(".error-container").shouldBe(appear, DEFAULT_TIMEOUT);
    ScreenshotUtil.capturePageScreenshot(ERROR_HANDLING_FOLDER + "500");
  }
}
