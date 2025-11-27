package com.axonivy.portal.selenium.document.screenshot;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class SampleDashboardScreenshotTest extends ScreenshotBaseTest {
  
  private final String SAMPLE_STATISTIC_DASHBOARD_MENU = "Sample: KPI Procurement Overview";
  private final String PROCUREMENT_PROCESS_NAME = "Procurement";
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void screenshotSampleStatisticDashboard() throws IOException {
    showNewDashboard();
    MainMenuPage mainMenuPage = new MainMenuPage();

    ProcessWidgetPage processWidgetPage = mainMenuPage.openProcessList();
    processWidgetPage.startProcessByName(PROCUREMENT_PROCESS_NAME);

    mainMenuPage.clickMainMenuItem(SAMPLE_STATISTIC_DASHBOARD_MENU);
    waitForDashboardLoaded();

    ScreenshotUtils.resizeBrowserAndCaptureWholeScreen(ScreenshotUtils.SAMPLE_DASHBOARD_FOLDER + "statistic-sample-dashboard",
        new Dimension(1800, 2400));
  }

  @Test
  public void screenshotSampleStatisticChartConfig() throws IOException {
    showNewDashboard();
    NewDashboardPage dashboardPage = new NewDashboardPage();
    DashboardConfigurationPage dashboardConfigurationPage = dashboardPage.openDashboardConfigurationPage();
    dashboardConfigurationPage.openEditPublicDashboardsPage();
    dashboardConfigurationPage.clickButtonOnDashboardConfigurationActionMenu("Configuration", 2);
    NewDashboardDetailsEditPage dashboardConfigPage = new NewDashboardDetailsEditPage();
    dashboardConfigPage.addWidget().click();
    scrollToExampleStatisticInAddWidgetDialog();
    ScreenshotUtils.resizeBrowserAndCaptureWholeScreen(ScreenshotUtils.SAMPLE_DASHBOARD_FOLDER + "statistic-config",
        new Dimension(1500, 1500));
  }
  
  private void scrollToExampleStatisticInAddWidgetDialog() {
    $$("div[id$='example-chip']").shouldHave(CollectionCondition.size(9), DEFAULT_TIMEOUT).first().scrollIntoView(true);
  }

  private void waitForDashboardLoaded() {
    $("span[id$='welcome-text']").shouldHave(Condition.text("KPI Procurement Overview"), DEFAULT_TIMEOUT);
  }
}
