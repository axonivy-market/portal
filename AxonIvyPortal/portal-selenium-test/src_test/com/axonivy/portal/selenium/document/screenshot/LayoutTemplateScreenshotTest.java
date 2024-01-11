package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class LayoutTemplateScreenshotTest extends ScreenshotTest{
  @Test
  public void screenshotLayoutTemplate() throws IOException {
    redirectToRelativeLink(defaultProcessImageSelectionExampleUrl);

    TaskTemplatePage templatePage = new TaskTemplatePage();
    templatePage.getElementInPortalIFramTask("[id='form:save-btn']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    templatePage.switchBackToParent();
    ScreenshotUtils.resizeBrowser(new Dimension(1200, 800));
    ScreenshotUtils.executeDecorateJs("highlightAndNumberingTaskTemplate()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.LAYOUT_FOLDER + "task-template");
  }
}
