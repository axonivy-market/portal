package portal.guitest.common;

import static portal.guitest.common.Variable.DISPLAY_MESSAGE_AFTER_FINISH_TASK;
import static portal.guitest.common.Variable.SHOW_LEGACY_UI;

import org.junit.Before;

public class ScreenshotTest extends BaseTest {
  
  @Before
  @Override
  public void setup() {
    killBrowsers();
    super.setup();
    updatePortalSetting(DISPLAY_MESSAGE_AFTER_FINISH_TASK.getKey(), "false");
    updatePortalSetting(SHOW_LEGACY_UI .getKey(), "true");
  }

}
