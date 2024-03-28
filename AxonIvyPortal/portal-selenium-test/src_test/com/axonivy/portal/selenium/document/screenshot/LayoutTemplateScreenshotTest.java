package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.page.TaskTemplatePage;

@IvyWebTest
public class LayoutTemplateScreenshotTest extends ScreenshotBaseTest {
  private String applicationShowcase = "portal-developer-examples/169BDE2F368D6EC4/ApplicationShowcase.ivp";
  
  @Test
  public void screenshotLayoutTemplate() throws IOException {
    ScreenshotUtils.resizeBrowser(new Dimension(1200, 700));
    redirectToRelativeLink(defaultProcessImageSelectionExampleUrl);

    TaskTemplatePage templatePage = new TaskTemplatePage();
    templatePage.waitUntilLayoutWrapperDisplayed();
    templatePage.switchBackToParent();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.LAYOUT_FOLDER + "basic-template");

    redirectToRelativeLink(applicationShowcase);
    templatePage.waitUntilLayoutWrapperDisplayed();
    templatePage.waitForMainAreaPanelRendered();
    ScreenshotUtils.resizeBrowser(new Dimension(1200, 800));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.LAYOUT_FOLDER + "task-template");
  }
}
