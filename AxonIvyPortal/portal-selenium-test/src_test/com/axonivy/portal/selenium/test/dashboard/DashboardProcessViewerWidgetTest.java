package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.size;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessViewerWidgetNewDashBoardPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardProcessViewerWidgetTest extends BaseTest {
  private static final String TEST_PROCESS_VIEWER_PERMISSION = "Test Process Viewer Permission";
  private static final String THIRD_PARTY_APP = "Create third party app";
  private static final String SHOWCASE_APPLICATION = "Showcase Application";
  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testCreateProcessViewerWidget() {
    var configurationPage = newDashboardPage.openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    newDashboardDetailsEditPage.addWidget();
    ProcessViewerWidgetNewDashBoardPage processViewerWidgetConfiguration =
        newDashboardDetailsEditPage.addNewProcessViewerWidget();
    processViewerWidgetConfiguration.findProcess(TEST_PROCESS_VIEWER_PERMISSION);
    processViewerWidgetConfiguration.getSelectedProcessList().shouldHave(size(0));
    processViewerWidgetConfiguration.selectProcessAndSaveWidget(THIRD_PARTY_APP);
    newDashboardPage.getProcessRequestPathName().shouldHave(Condition.exactTextCaseSensitive(THIRD_PARTY_APP));

    testEditProcessViewerWidget();
    newDashboardPage.findProcessViewerWidget().shouldBe(Condition.exist);
    testDeleteProcessViewerWidget();
  }

  public void testEditProcessViewerWidget() {
    ProcessViewerWidgetNewDashBoardPage processViewerWidgetConfiguration =
        newDashboardPage.showEditProcessViewerWidgetConfiguration();
    processViewerWidgetConfiguration.selectProcessAndSaveWidget(SHOWCASE_APPLICATION);
    newDashboardPage.getProcessRequestPathName().shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_APPLICATION));
  }

  public void testDeleteProcessViewerWidget() {
    newDashboardPage.deleteProcessViewerWidget();
    newDashboardPage.findProcessViewerWidget().shouldNotBe(Condition.exist);
  }
}
