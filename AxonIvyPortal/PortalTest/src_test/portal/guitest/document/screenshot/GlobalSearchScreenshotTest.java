package portal.guitest.document.screenshot;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.TemplatePage.GlobalSearch;

public class GlobalSearchScreenshotTest extends ScreenshotTest {
  private HomePage homePage;
  
  @Test
  public void screenshotForGlobalSearch() throws IOException {
    homePage = new HomePage();
    homePage.waitForStatisticRendered(HomePage.PORTAL_HOME_PAGE_URL);
    GlobalSearch globalSearch = homePage.getGlobalSearch();
    globalSearch.inputSearchKeyword("process");
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.SEARCH_FOLDER + "global-search-result");
  }
}
