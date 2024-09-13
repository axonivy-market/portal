package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.Selenide.$;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.StatisticWidgetPage;

@IvyWebTest
public class DefaultChartTest extends BaseTest {

  private static final String DEFAULT_CHART = "Tasks by Priority";
  private static final String RESTORE_DEFAULT = "Restore default";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    grantPermissionToCreateChart();
  }

  @AfterEach
  public void clear() {
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testCreateDefaultChart() {
    redirectToNewDashBoard();
    MainMenuPage mainMenuPage = new MainMenuPage();
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    Set<String> chartNames = statisticWidgetPage.getAllChartNames();
    assertTrue(chartNames.contains(DEFAULT_CHART));
    assertEquals(RESTORE_DEFAULT, statisticWidgetPage.getRestoreDefaultButtonName());
  }

  @Test
  public void testRestoreDefaultChart() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    StatisticWidgetPage statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    resizeBrowserTo2kResolution();
    statisticWidgetPage.waitForElementDisplayed(By.id("statistics-widget:widget-container"), true);
    statisticWidgetPage.switchCreateMode();
    statisticWidgetPage.createCaseByFinishedTask();
    statisticWidgetPage.backToDashboard();
    statisticWidgetPage.restoreDefaultCharts();

    WebElement taskByExpiryChartName3 = null;
    try {
      taskByExpiryChartName3 =
          $("[id='statistics-widget:statistic-dashboard-widget:statistic-chart-repeater:2:chart-name']");
    } catch (Exception ex) {
    }

    assertEquals(DEFAULT_CHART, statisticWidgetPage.getChartName(0));
    assertEquals(false, taskByExpiryChartName3.isDisplayed());
  }

  private void grantPermissionToCreateChart() {
    redirectToRelativeLink(grantPortalPermission);
  }
}
