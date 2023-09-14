package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.Variable;


@IvyWebTest(headless = false)
public class AdditionalComponentsScreenshotTest extends ScreenshotBaseTest{
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.getKey(), "false");
  }
  
  @Test
  public void captureScreenshotForProcessChainComponent() throws IOException {
    redirectToRelativeLink(processChainShowcaseUrl);
    ScreenshotUtil.resizeBrowser(new Dimension(1300, 1150));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.COMPONENTS_FOLDER + "process-chain");
  }

}
