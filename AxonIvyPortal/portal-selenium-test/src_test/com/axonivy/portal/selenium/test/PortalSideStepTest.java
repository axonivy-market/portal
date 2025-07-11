package com.axonivy.portal.selenium.test;
import org.junit.jupiter.api.Assertions;
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

@IvyWebTest
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
    taskTemplatePage.clickActionButton();
    taskTemplatePage.startSideStep();
    taskTemplatePage.inputSideStepInfoTaskLevel();
    
    
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.openTaskList();
    
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskByNameInIFrame("Approval for leave request of Portal Demo User");
    taskWidget.finishApprovalForLeaveRequest();

    // Admin finish side step
    login(TestAccount.ADMIN_USER);
    taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskByNameInIFrame("Side step task for: Create leave request for: Portal Demo User");
    taskWidget.finishSideStepForLeaveRequest();
  }
  
  @Test
  public void testCreateSideStepCaseLevel() {
    // number of config is fixed for both creation and approval task
    int numberOfConfig = 4; // 3 options + 1 empty option
    login(TestAccount.DEMO_USER);
    ProcessWidgetPage processWidget = NavigationHelper.navigateToProcessList();
    processWidget.startProcessByName("Leave request with side step processes (process level)");
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();
    taskTemplatePage.inputLeaveRequestInfo();
    taskTemplatePage.clickActionButton();
    taskTemplatePage.startSideStep();
    taskTemplatePage.inputSideStepInfoCaseLevel(numberOfConfig);
    
    
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.openTaskList();
    
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.startTaskByNameInIFrame("Approval for leave request of Portal Demo User");
    taskTemplatePage.clickActionButton();
    taskTemplatePage.startSideStep();
    Assertions.assertEquals(numberOfConfig, taskTemplatePage.getNumberOfConfigForSideStep());
  }
}
