package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class LayoutTemplateScreenshotTest extends ScreenshotBaseTest {

  @Test
  public void screenshotLayoutTemplate() throws IOException {
    redirectToRelativeLink(templateInFrameExampleInFrameUrl);

    TaskTemplatePage templatePage = new TaskTemplatePage();
    templatePage.getElementInPortalIFramTask("[id='form:save-btn']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    templatePage.switchBackToParent();
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    ScreenshotUtil.executeDecorateJs("highlightAndNumberingTaskTemplate()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.LAYOUT_FOLDER + "task-template");
  }
}