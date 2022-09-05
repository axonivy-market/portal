package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessViewerWidgetNewDashBoardPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardProcessViewerWidgetTest extends BaseTest {
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
    ProcessViewerWidgetNewDashBoardPage processViewerWidgetConfiguration = newDashboardPage
        .showProcessViewerWidgetConfiguration();
    processViewerWidgetConfiguration.selectProcessAndSaveWidget(THIRD_PARTY_APP);
    newDashboardPage.getProcessRequestPathName().shouldHave(Condition.exactTextCaseSensitive(THIRD_PARTY_APP));

    testEditProcessViewerWidget();
    newDashboardPage.findProcessViewerWidget().shouldBe(Condition.exist);
    testDeleteProcessViewerWidget();

  }

  public void testEditProcessViewerWidget() {
    ProcessViewerWidgetNewDashBoardPage processViewerWidgetConfiguration = newDashboardPage
        .showEditProcessViewerWidgetConfiguration();
    processViewerWidgetConfiguration.selectProcessAndSaveWidget(SHOWCASE_APPLICATION);
    newDashboardPage.getProcessRequestPathName().shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_APPLICATION));
  }

  public void testDeleteProcessViewerWidget() {
    newDashboardPage.deleteProcessViewerWidget();
    newDashboardPage.findProcessViewerWidget().shouldNotBe(Condition.exist);
  }
}
