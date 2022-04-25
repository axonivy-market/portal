package com.axonivy.portal.selenium.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class StatisticDashboardWidgetTest extends BaseTest {

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testShowChartInfo() {
    login(TestAccount.ADMIN_USER);
    createPublicDashboardByJSonFile("dashboard-has-one-chart-example.json");
    redirectToRelativeLink(createTestingTasksUrl);
    var chartWidget = newDashboardPage.selectStatisticWidget();
    var chartName = chartWidget.getChartName(0);
    assertTrue(StringUtils.equalsIgnoreCase("Tasks by Priority", chartName));
    var infoIcon = chartWidget.getStatisticInfoIconOfChart(0);
    infoIcon.should(Condition.appear);
    chartWidget.openStatisticInfoPanel(0);
    var chartFilter = chartWidget.countFilterOfStatistic(0);
    chartFilter.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(4));
  }

  @Test
  public void testEditStatisticChart() {
    login(TestAccount.ADMIN_USER);
    createPublicDashboardByJSonFile("dashboard-has-one-chart-example.json");
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    var chartWidget = newDashboardDetailsEditPage.selectStatisticWidget();
    chartWidget.getEditIconOfChart(0).shouldBe(Condition.visible, Condition.enabled);
    var editChartDialog = chartWidget.openEditStatisticWidgetDialog(0);
    assertTrue(StringUtils.equalsIgnoreCase(editChartDialog, "Edit Widget Configuration"));
    chartWidget.clickOnCancelConfiguration();
  }

  private void createPublicDashboardByJSonFile(String dashboardJSon) {
    var path = System.getProperty("user.dir") + "\\resources\\testFile\\" + dashboardJSon;
    String filepath = "";
    try {
      filepath = URLEncoder.encode(path, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    redirectToRelativeLink(createPublicDashboadByJSonFileUrl + filepath);
  }

  private NewDashboardDetailsEditPage gotoEditPublicDashboardPage() {
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    return configPage.navigateToEditDashboardDetailsByName("Dashboard");
  }
}
