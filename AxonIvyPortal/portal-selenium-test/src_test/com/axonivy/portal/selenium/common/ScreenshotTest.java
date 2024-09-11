package com.axonivy.portal.selenium.common;

import com.axonivy.ivy.webtest.IvyWebTest;
import org.junit.jupiter.api.BeforeEach;

@IvyWebTest
public class ScreenshotTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.getKey(), Boolean.FALSE.toString());
  }
}
