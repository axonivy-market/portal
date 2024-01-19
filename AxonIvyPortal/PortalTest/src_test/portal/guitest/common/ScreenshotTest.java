package portal.guitest.common;

import static portal.guitest.common.Variable.DISPLAY_MESSAGE_AFTER_FINISH_TASK;

import org.junit.Before;

public class ScreenshotTest extends BaseTest {
  
  @Before
  @Override
  public void setup() {
    killBrowsers();
    super.setup();
    updatePortalSetting(DISPLAY_MESSAGE_AFTER_FINISH_TASK.getKey(), "false");
  }

}
