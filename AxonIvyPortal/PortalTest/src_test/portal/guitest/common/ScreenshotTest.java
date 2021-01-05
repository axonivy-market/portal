package portal.guitest.common;

import org.junit.After;
import org.junit.Before;

public class ScreenshotTest extends BaseTest {
  
  @Before
  @Override
  public void setup() {
    killBrowsers();
    super.setup();
    updatePortalSetting("DISPLAY_MESSAGE_AFTER_FINISH_TASK", "false");
  }
  
  @After
  public void cleanUp() {
    killBrowsers();
  }
}
