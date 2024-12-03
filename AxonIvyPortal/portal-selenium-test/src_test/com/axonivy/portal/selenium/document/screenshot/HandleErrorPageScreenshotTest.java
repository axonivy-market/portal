package com.axonivy.portal.selenium.document.screenshot;

import static com.axonivy.portal.selenium.common.ScreenshotUtils.ERROR_HANDLING_FOLDER;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;


@IvyWebTest
public class HandleErrorPageScreenshotTest extends ScreenshotBaseTest {
  private String portalCustomErrorUrl = "PortalKitTestHelper/18D596926C35B1DB/testPortalCustomErrorHandler.ivp";
  private String showIvyErrorPageUrl = "portal-developer-examples/169BDE2F368D6EC4/StartShowIvyErrorPage.ivp";
  private String portalCustom404ErrorUrl = "portal/1549F58C18A6C562/Error404Page.ivp";
  private String portalCustom500ErrorUrl = "portal/1549F58C18A6C562/Error500Page.ivp";

  @Test
  public void screenshotErrorPages() throws IOException {
    ScreenshotUtils.resizeBrowser(new Dimension(1280, 720));
    redirectToRelativeLink(portalCustomErrorUrl);
    $("[id$=':test-error-method']").shouldBe(exist, DEFAULT_TIMEOUT).shouldBe(visible, DEFAULT_TIMEOUT).click();
    $(".notification-container").shouldBe(appear, DEFAULT_TIMEOUT);

    $("a[class$='notification-content-action-more-details']").shouldBe(exist, DEFAULT_TIMEOUT)
        .shouldBe(visible, DEFAULT_TIMEOUT).click();
    $("[id='exception-dialog:exception-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    ScreenshotUtils.capturePageScreenshot(ERROR_HANDLING_FOLDER + "portal-ajax-error-handler");

    redirectToRelativeLink(showIvyErrorPageUrl);
    $(".exception-body").shouldBe(appear, DEFAULT_TIMEOUT);
    ScreenshotUtils.capturePageScreenshot(ERROR_HANDLING_FOLDER + "default-ivy-error");

    redirectToRelativeLink(portalCustom404ErrorUrl);
    $(".error-container").shouldBe(appear, DEFAULT_TIMEOUT);
    ScreenshotUtils.capturePageScreenshot(ERROR_HANDLING_FOLDER + "404");

    redirectToRelativeLink(portalCustom500ErrorUrl);
    $(".error-container").shouldBe(appear, DEFAULT_TIMEOUT);
    ScreenshotUtils.capturePageScreenshot(ERROR_HANDLING_FOLDER + "500");
  }
}
