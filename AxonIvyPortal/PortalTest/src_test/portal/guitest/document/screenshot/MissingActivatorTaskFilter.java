package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskWidgetPage;

public class MissingActivatorTaskFilter extends ScreenshotTest {

  private static final int SCREENSHOT_WIDTH = 1500;
  private HomePage homePage;
  
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    homePage = new HomePage();
  }
  
  @Test
  public void screenshotMissingActivatorTaskFilter() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 1200));
    login(TestAccount.ADMIN_USER);
    TaskWidgetPage taskWidget = homePage.openTaskList();
    taskWidget.openNoActivatorFilter("Missing activator");
    taskWidget.filterByUnavailableActivator(false);
    ScreenshotUtil.captureHalfTopRightPageScreenShot(ScreenshotUtil.TASK_WIDGET_FOLDER + "task-filter-missing-activator");
  }

}
