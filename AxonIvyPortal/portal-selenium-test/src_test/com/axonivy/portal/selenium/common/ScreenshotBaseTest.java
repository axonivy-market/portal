package com.axonivy.portal.selenium.common;

import org.junit.jupiter.api.BeforeEach;

import com.axonivy.ivy.webtest.IvyWebTest;

@IvyWebTest
public class ScreenshotBaseTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.getKey(), "false");
  }

}
