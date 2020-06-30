package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class GlobalSearchScreenshotTest extends BaseTest {
  private HomePage homePage;
  private GlobalSearch globalSearch;
  
  @Test
  public void screenshotForGlobalSearch() throws IOException {
    homePage = new HomePage();
    globalSearch = homePage.getGlobalSearch();
    globalSearch.inputSearchKeyword("process");
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.SEARCH_FOLDER + "global-search-result");
  }
}
