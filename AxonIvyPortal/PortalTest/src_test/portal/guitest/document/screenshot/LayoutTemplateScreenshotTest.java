package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.page.TaskTemplateIFramePage;

public class LayoutTemplateScreenshotTest extends ScreenshotTest {

  @Test
  public void screenshotLayoutTemplate() throws IOException {
    redirectToRelativeLink(templateInFrameExampleInFrameUrl);

    TaskTemplateIFramePage templatePage = new TaskTemplateIFramePage();
    templatePage.waitForIFrameContentVisible();
    templatePage.switchOutIFrame();

    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    executeDecorateJs("highlightAndNumberingTaskTemplate();");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.LAYOUT_FOLDER + "task-template");
  }
}