package com.axonivy.portal.selenium.test.task;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.UrlHelpers;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.WebDriverRunner;

@IvyWebTest
public class PortalInPortalGuardTest extends BaseTest {

  private static final String IFRAME_TASK_URL =
      "portal-developer-examples/16E5DB746865BCEC/CreateInvestment.ivp?embedInFrame";
  private static final String IFRAME_ID = "iFrame";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testGuardBreaksPageOutWhenPortalShellIsNestedInsideTaskIFrame() {
    redirectToRelativeLink(IFRAME_TASK_URL);
    $("#" + IFRAME_ID).shouldBe(visible, DEFAULT_TIMEOUT);

    String dashboardAbsoluteUrl = UrlHelpers.generateAbsoluteProcessStartLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    executeJavaScript("document.getElementById(arguments[0]).src = arguments[1];", IFRAME_ID, dashboardAbsoluteUrl);

    // The guard navigates window.top asynchronously relative to this call (the iframe first has to
    // load the dashboard and run its <head> script), so poll instead of asserting immediately.
    // Check DOM state (#iFrame gone) rather than the resolved URL: Ivy resolves the process-start
    // URL to its own internal Faces view path, which isn't reliably predictable from here.
    WaitHelper.assertTrueWithWait(() -> !$("#" + IFRAME_ID).exists());

    // The guard already moved us to window.top; make sure no stale frame selection is left behind
    // for subsequent tests.
    WebDriverRunner.getWebDriver().switchTo().defaultContent();
  }

  @Test
  public void testNormalTaskIFrameNavigationDoesNotTriggerTopLevelRedirect() {
    redirectToRelativeLink(IFRAME_TASK_URL);

    WaitHelper.waitForIFrameAvailable(WebDriverRunner.getWebDriver(), IFRAME_ID);
    $(By.id("form:invested-amount")).shouldBe(appear, DEFAULT_TIMEOUT);
    WebDriverRunner.getWebDriver().switchTo().defaultContent();

    // If the guard had over-fired, window.top would have navigated away to the task's own (raw
    // dialog) URL and #iFrame could no longer exist on the top-level page. This single check
    // strictly dominates a URL-equality check, which would be sensitive to any benign
    // history.pushState/replaceState the outer shell might perform during the task's AJAX load
    // sequence (e.g. breadcrumb/topbar updates) and add flake surface without added coverage.
    assertTrue($("#" + IFRAME_ID).is(exist), "Task iframe should still be nested on the top-level page");
  }
}
