package com.axonivy.portal.selenium.document.screenshot;

import static com.codeborne.selenide.Selenide.$;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.util.ConfigurationJsonUtils;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class QuickSearchScreenshotTest extends ScreenshotBaseTest {

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void screenshotForQuickSearchConfigurationOnTaskWidget() throws IOException {
    ConfigurationJsonUtils.updateJSONSetting("dashboard-task-widget-has-quicksearch.json", Variable.DASHBOARD);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForTaskWidgetLoaded();
    ScreenshotUtils.executeDecorateJs("highlightQuickSearchTextbox()");
    ScreenshotUtils.captureElementScreenshot($(".dashboard__widget").shouldBe(Condition.appear, DEFAULT_TIMEOUT),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "task-quick-search-textbox");

    }

  @Test
  public void screenshotForQuickSearchConfigurationOnCaseWidget() throws IOException {
    ConfigurationJsonUtils.updateJSONSetting("dashboard-case-widget-has-quicksearch.json", Variable.DASHBOARD);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();

    ScreenshotUtils.executeDecorateJs("highlightQuickSearchTextbox()");
    ScreenshotUtils.captureElementScreenshot($(".dashboard__widget").shouldBe(Condition.appear, DEFAULT_TIMEOUT),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "case-quick-search-textbox");
  }
  
  @Test
  public void screenshotForQuickSearchConfigurationOnProcessWidget() throws IOException {
    ConfigurationJsonUtils.updateJSONSetting("dashboard-process-widget-has-quicksearch.json", Variable.DASHBOARD);
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForProcessWidgetLoaded();

    ScreenshotUtils.executeDecorateJs("highlightQuickSearchTextbox()");
    ScreenshotUtils.captureElementScreenshot($(".dashboard__widget").shouldBe(Condition.appear, DEFAULT_TIMEOUT),
        ScreenshotUtils.NEW_DASHBOARD_FOLDER + "process-quick-search-textbox");
  }
}
