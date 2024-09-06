package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.size;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CustomWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardCustomAndProcessWigetTest extends BaseTest {

  private MainMenuPage mainMenuPage;

  private static final String PROCESS_NAME = "Investment List (Example for Custom Widget on Dashboard)";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    mainMenuPage = new MainMenuPage();
  }

  @Test
  public void testTheProcessParamDisplayOnAddCustomWidgetPage() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    CustomWidgetNewDashBoardPage customWidgetNewDashBoardPage = newDashboardDetailsEditPage.addNewCustomWidget();
    customWidgetNewDashBoardPage.selectCustomWidgetTypeProcess();
    customWidgetNewDashBoardPage.inputProcessName(PROCESS_NAME);
    customWidgetNewDashBoardPage.processParam().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  @Test
  public void testTheProcessDoesNotDispalyOnAddProcessesWidgetPage() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    ProcessEditWidgetNewDashBoardPage processEditWidgetNewDashBoardPage =
        newDashboardDetailsEditPage.addNewProcessWidget();
    processEditWidgetNewDashBoardPage.clickOnProcesses();
    assertFalse(processEditWidgetNewDashBoardPage.getProcessByName(PROCESS_NAME));
  }

  @Test
  public void testTheProcessDisplayonProcessesWidgetPage() {
    ProcessWidgetPage processWidgetPage = mainMenuPage.openProcessList();
    processWidgetPage.checkProcessNotExists(PROCESS_NAME);
  }

  private NewDashboardDetailsEditPage gotoEditPublicDashboardPage() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    return modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
  }
  
  @Test
  public void testTheProcessDisplayOnAddCustomWidgetPage() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = gotoEditPublicDashboardPage();
    newDashboardDetailsEditPage.addWidget();
    CustomWidgetNewDashBoardPage customWidgetNewDashBoardPage =
        newDashboardDetailsEditPage.addNewCustomrWidget();
    customWidgetNewDashBoardPage.selectWidgetType("Custom Dashboard Widget Process");
    customWidgetNewDashBoardPage.selectProcess(PROCESS_NAME);
    customWidgetNewDashBoardPage.getProcessList().shouldHave(size(1));
  }

}
