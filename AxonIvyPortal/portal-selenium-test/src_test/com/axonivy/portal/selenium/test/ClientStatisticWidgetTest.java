package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.ClientStatisticWidgetNewDashboardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.codeborne.selenide.CollectionCondition;


@IvyWebTest( headless = false)
public class ClientStatisticWidgetTest extends BaseTest {
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
    ClientStatisticWidgetNewDashboardPage openTasksWidget = newDashboardPage.selectClientStatisticChartWidget("Open Tasks");
    ClientStatisticWidgetNewDashboardPage runningCasesWidget = newDashboardPage.selectClientStatisticChartWidget("Running Cases");
    ScreenshotUtils.maximizeBrowser();
    openTasksWidget.getAllChartNumbers().shouldHave(CollectionCondition.size(1));
    openTasksWidget.getAllChartLabels().shouldHave(CollectionCondition.size(1));
    openTasksWidget.getAllChartLabels().first().text().equals("Open");
    runningCasesWidget.getAllChartLabels().first().text().equals("Running");
  }
  
  @Test
  public void testEmptyMessageAppearWhenNoData() {
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    ScreenshotUtils.maximizeBrowser();
    ClientStatisticWidgetNewDashboardPage tasksByPriorityWidget = newDashboardPage.selectClientStatisticChartWidget("Tasks by Priority");
    tasksByPriorityWidget.clickOnButtonExpand();
    assertTrue(tasksByPriorityWidget.isEmptyMessageAppear());
  }
}
