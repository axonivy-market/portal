package com.axonivy.portal.selenium.test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;

@IvyWebTest(headless = false)
public class PortalSideStepTest extends BaseTest {
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }
  
  @Test
  public void testCreateSideStepTaskLevel() {
    login(TestAccount.DEMO_USER);
    ProcessWidgetPage processWidget = NavigationHelper.navigateToProcessList();
    processWidget.startProcessByName("Leave request with side step processes (task level)");
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.inputLeaveRequestInfo();
    
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.openTaskList();
    
    login(TestAccount.ADMIN_USER);
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.openTaskList();
    
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskByNameInIFrame("Approval for leave request of Portal Demo User");

    taskTemplatePage.clickActionButton();
    taskTemplatePage.startSideStep();
    taskTemplatePage.inputSideStepInfoTaskLevel();
    taskTemplatePage.openTaskList();
    
    // Admin finish side step
    login(TestAccount.DEMO_USER);
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskByNameInIFrame("Side step task for: Approval for leave request of Portal Demo User");
    taskWidget.finishSideStepForLeaveRequest();
  }
  
  @Test
  public void testCreateSideStepCaseLevel() {
    // number of config is fixed for both creation and approval task
    int numberOfConfig = 4; // 3 options + 1 empty option
    
    login(TestAccount.DEMO_USER);
    ProcessWidgetPage processWidget = NavigationHelper.navigateToProcessList();
    processWidget.startProcessByName("Leave request with side step processes (case level)");
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.inputLeaveRequestInfo();
    
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.openTaskList();
    
    login(TestAccount.ADMIN_USER);
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.openTaskList();
    
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskByNameInIFrame("Approval for leave request of Portal Demo User");

    taskTemplatePage.clickActionButton();
    taskTemplatePage.startSideStep();
    taskTemplatePage.inputSideStepInfoCaseLevel(numberOfConfig);
    taskTemplatePage.approveLeaveRequest();
    taskTemplatePage.openTaskList();
    
    login(TestAccount.DEMO_USER);
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.openTaskList();
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskByNameInIFrame("Your leave request is approved");
    
    taskTemplatePage.clickActionButton();
    taskTemplatePage.startSideStep();
    taskTemplatePage.inputSideStepInfoCaseLevel(numberOfConfig);
  }
}
