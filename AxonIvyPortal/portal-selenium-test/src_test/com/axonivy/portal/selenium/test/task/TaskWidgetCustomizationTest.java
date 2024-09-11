package com.axonivy.portal.selenium.test.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class TaskWidgetCustomizationTest extends BaseTest {
  private static final String CUSTOMER_NAME_COLUMN_ID = "customer-name";
  private static final String STATE_COLUMN_NAME = "State";
  private static final String CUSTOMER_NAME_COLUMN_HEADER = "Customer name";
  private static final String CREATE_TESTING_TASK_FOR_CUSTOMIZATION_URL = "portal-developer-examples/162511D2577DBA88/createTasksForTaskListCustomization.ivp";


  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(CREATE_TESTING_TASK_FOR_CUSTOMIZATION_URL);
    updateLegacyUIConfiguration();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
  }

  @Test
  public void testShowHideCustomColumnsInTaskWidget() {
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertFalse(taskWidgetPage.isTaskListColumnExist(STATE_COLUMN_NAME));
    assertTrue(taskWidgetPage.isTaskListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    assertTrue("Florian".equals(taskWidgetPage.getTaskListCustomCellValue(0, CUSTOMER_NAME_COLUMN_ID)));
  }
  
  @Test
  public void testShowHideCustomColumnsInTaskListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectUserExampleTaskMenu();
    assertFalse(taskWidgetPage.isTaskListColumnExist(STATE_COLUMN_NAME));
    assertTrue(taskWidgetPage.isTaskListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    assertTrue("Florian".equals(taskWidgetPage.getTaskListCustomCellValue(0, CUSTOMER_NAME_COLUMN_ID))); 
  }
  
  
  @Test
  public void testSortCustomColumnsInTaskListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openDeveloperExamplesTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Order", 3);
    taskWidgetPage.sortTaskListByColumn(CUSTOMER_NAME_COLUMN_HEADER, 0, CUSTOMER_NAME_COLUMN_ID, "Florian");
    assertTrue("Florian".equals(taskWidgetPage.getTaskListCustomCellValue(0, CUSTOMER_NAME_COLUMN_ID)));

    taskWidgetPage.sortTaskListByColumn(CUSTOMER_NAME_COLUMN_HEADER, 0, CUSTOMER_NAME_COLUMN_ID, "Sarah");
    assertTrue("Florian".equals(taskWidgetPage.getTaskListCustomCellValue(2, CUSTOMER_NAME_COLUMN_ID)));
  }

}
