package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.page.GlobalSearchResultPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class GlobalSearchScreenshotTest extends ScreenshotBaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    showNewDashboard();
  }

  @Test
  public void screenshotForGlobalSearch() throws IOException {
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    GlobalSearchResultPage resultPage = homePage.inputGlobalSearchKeyword("process");
    
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    resultPage.waitUtilProcessWidgetDisplayed();
    ScreenshotUtil.captureHalfTopPageScreenShot(ScreenshotUtil.SEARCH_FOLDER + "global-search-result");
  }
}
