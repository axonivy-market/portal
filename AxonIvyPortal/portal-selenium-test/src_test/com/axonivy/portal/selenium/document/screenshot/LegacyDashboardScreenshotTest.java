package com.axonivy.portal.selenium.document.screenshot;

import static com.axonivy.portal.selenium.common.Variable.SHOW_USER_GUIDE;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.legacy.LegacyDashboardPage;
import com.axonivy.portal.selenium.page.legacy.LegacyTaskWidgetPage;

public class LegacyDashboardScreenshotTest extends ScreenshotBaseTest{
  private LegacyDashboardPage homePage;
  private static final int SCREENSHOT_WIDTH = 1500;
  private static final int SCREENSHOT_HD_WIDTH = 1920;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.ENABLE_GROUP_CHAT.getKey(), "true");
    updatePortalSetting(Variable.SHOW_LEGACY_UI.getKey(), "true");
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createUserFavoriteProcess);
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new LegacyDashboardPage();
  }

  @Test
  public void takeScreenshotOverlayGuide() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    updatePortalSetting(SHOW_USER_GUIDE.getKey(), "true");
    homePage = new LegacyDashboardPage();
    homePage.waitForGrowlMessageDisappear();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_FOLDER + "overlay-guide");
  }

  @Test
  public void screenshotDashboard() throws IOException {
    ScreenshotUtil.maximizeBrowser();
    homePage.waitForStatisticFinishAnimation();
    ScreenshotUtil.captureElementScreenshot(homePage.getProcessWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "process-widget");
    ScreenshotUtil.captureElementScreenshot(homePage.getStatisticWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "statistic-widget");

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_HD_WIDTH, 800));
    homePage.waitForStatisticFinishAnimation();
    ScreenshotUtil.captureElementScreenshot(homePage.getTaskWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "task-widget");
  }

  @Test
  public void screenshotDashboardWithAnnotation() throws IOException {
    ScreenshotUtil.maximizeBrowser();
    homePage.waitForStatisticFinishAnimation();
    ScreenshotUtil.executeDecorateJs("numberingStatisticWidget();");
    homePage = new LegacyDashboardPage();
    ScreenshotUtil.captureElementScreenshot(homePage.getStatisticWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "statistics-key-information");

    ScreenshotUtil.resizeBrowser(new Dimension(1100, 800));
    homePage.clickGlobalSearch();
    ScreenshotUtil.executeDecorateJs("numberingTopBar()");
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(homePage.getTopBar(), ScreenshotUtil.DASHBOARD_FOLDER + "portal-header-with-numbering-annotation", new ScreenshotMargin(20, 20, 20, 120));
    ScreenshotUtil.resizeBrowser(new Dimension(1400, 800));

    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new LegacyDashboardPage();
    homePage.waitForStatisticFinishAnimation();
    ScreenshotUtil.executeDecorateJs("numberingTaskItem();");
    ScreenshotUtil.captureElementScreenshot(homePage.getTaskWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "personal-tasks-key-information");

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_WIDTH, 800));
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    homePage = new LegacyDashboardPage();
    homePage.waitForStatisticFinishAnimation();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_FOLDER + "legacy-dashboard");
    ScreenshotUtil.executeDecorateJs("highlightAndNumberingDashboardSections();");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.DASHBOARD_FOLDER + "dashboard-3-sections");

    ScreenshotUtil.resizeBrowser(new Dimension(SCREENSHOT_HD_WIDTH, 800));
    LegacyTaskWidgetPage taskWidgetPage = new LegacyTaskWidgetPage();
    taskWidgetPage.openCompactSortMenu();
    ScreenshotUtil.executeDecorateJs("numberingTaskFilterAndSort();");
    ScreenshotUtil.captureElementScreenshot(homePage.getTaskWidgetElement(), ScreenshotUtil.DASHBOARD_FOLDER + "personal-tasks-sort-and-search-features");
  }

  @Test
  public void screenshotHeaderFooter() throws IOException {
    redirectToRelativeLink(LegacyDashboardPage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    MainMenuPage menuPage = new MainMenuPage();
    menuPage.openTaskList();
    ScreenshotUtil.resizeBrowserAndCaptureWholeScreen(ScreenshotUtil.DASHBOARD_FOLDER + "page-header-footer", new Dimension(SCREENSHOT_WIDTH, 900));
  }

  @Test
  public void screenshotWithEnvironmentInfo() throws IOException {
    updatePortalSetting(Variable.SHOW_ENVIRONMENT_INFO.getKey(), "true");
    homePage = new LegacyDashboardPage();
    homePage.waitForGrowlMessageDisappear();
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 500));
    ScreenshotUtil.executeDecorateJs("highlightServerInfo()");
    ScreenshotUtil.captureHalfRightPageScreenShot(ScreenshotUtil.DASHBOARD_FOLDER + "environment-info");
  }
}
