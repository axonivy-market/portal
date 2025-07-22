package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
@IvyWebTest
public class SideStepScreenshotTest extends ScreenshotBaseTest {

  @Test
  public void screenshotSideStepConfiguration() throws IOException {
    login(TestAccount.DEMO_USER);
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 1000));
    ProcessWidgetPage processWidget = NavigationHelper.navigateToProcessList();
    processWidget.startProcessByName("Leave request with side step processes (process level)");
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.clickActionButton();
    ScreenshotUtils.captureHalfTopRightPageScreenShot(ScreenshotUtils.SIDE_STEP_FOLDER + "side-step-menu");
    taskTemplatePage.startSideStep();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskTemplatePage.getSideStepConfigDialog(), ScreenshotUtils.SIDE_STEP_FOLDER + "side-step-config", new ScreenshotMargin(5, 5));
    
  }
}
