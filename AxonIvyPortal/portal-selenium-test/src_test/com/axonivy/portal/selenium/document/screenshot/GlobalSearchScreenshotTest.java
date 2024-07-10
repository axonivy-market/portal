package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
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

    ScreenshotUtils.resizeBrowser(new Dimension(1500, 800));
    resultPage.waitUtilProcessWidgetDisplayed();
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.SEARCH_FOLDER + "global-search-result");
  }

  @Test
  public void screenshotForConfigGlobalSearchScope() throws IOException {
    login(TestAccount.ADMIN_USER);

    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.waitForCaseWidgetLoaded();
    ScreenshotUtils.resizeBrowser(new Dimension(1366, 800));
    AdminSettingsPage adminSettingsPage = homePage.openAdminSettings();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(
        adminSettingsPage.getEditSettingDialogOfGlobalSearchScope(),
        ScreenshotUtils.SEARCH_FOLDER + "global-search-scope-settings", new ScreenshotMargin(5, 20));
  }
}
