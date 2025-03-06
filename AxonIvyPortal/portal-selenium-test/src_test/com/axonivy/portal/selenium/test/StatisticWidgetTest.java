package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.StatisticWidgetNewDashboardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.CollectionCondition;


@IvyWebTest
public class StatisticWidgetTest extends BaseTest {
  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    this.newDashboardPage = new NewDashboardPage();
  }
  
  @Test
  public void testNumberChart() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToNewDashBoard();
    StatisticWidgetNewDashboardPage openTasksWidget = newDashboardPage.selectStatisticChartWidget("Open Tasks");
    StatisticWidgetNewDashboardPage runningCasesWidget = newDashboardPage.selectStatisticChartWidget("Running Cases");
    ScreenshotUtils.maximizeBrowser();
    openTasksWidget.getAllChartNumbers().shouldHave(CollectionCondition.size(1));
    openTasksWidget.getAllChartLabels().shouldHave(CollectionCondition.size(1));
    openTasksWidget.getAllChartLabels().first().text().equals("Open");
    runningCasesWidget.getAllChartLabels().first().text().equals("Running");
  }
  
  @Test
  public void testAddNewStatisticWidget() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    redirectToNewDashBoard();
    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    ScreenshotUtils.maximizeBrowser();
    configurationPage.clickOnAddWidgetButton();
    StatisticWidgetNewDashboardPage runningCasesWidget = configurationPage.addNewStatisticWidget("Running Cases");
    runningCasesWidget.getAllChartLabels().first().text().equals("Running");
  }
}
