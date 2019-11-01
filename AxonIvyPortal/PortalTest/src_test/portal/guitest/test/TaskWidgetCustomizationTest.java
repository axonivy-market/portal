package portal.guitest.test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskWidgetCustomizationTest extends BaseTest {

  private static final String CUSTOMER_NAME_COLUMN_ID = "customer-name";
  private static final String STATE_COLUMN_NAME = "State";
  private static final String CUSTOMER_NAME_COLUMN_HEADER = "Customer name";
  private static final String CREATE_TESTING_TASK_FOR_CUSTOMIZATION_URL = "portalExamples/162511D2577DBA88/createTasksForTaskListCustomization.ivp";

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(CREATE_TESTING_TASK_FOR_CUSTOMIZATION_URL);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
  }

  @Test
  public void testShowHideCustomColumnsInTaskWidget() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertFalse(taskWidgetPage.isTaskListColumnExist(STATE_COLUMN_NAME));
    assertTrue(taskWidgetPage.isTaskListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    assertTrue("Florian".equals(taskWidgetPage.getTaskListCustomCellValue(0, CUSTOMER_NAME_COLUMN_ID)));
  }

  @Test
  public void testShowHideCustomColumnsInTaskListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertFalse(taskWidgetPage.isTaskListColumnExist(STATE_COLUMN_NAME));
    assertTrue(taskWidgetPage.isTaskListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    assertTrue("Florian".equals(taskWidgetPage.getTaskListCustomCellValue(0, CUSTOMER_NAME_COLUMN_ID)));
  }

  @Test
  public void testSortCustomColumnsInTaskListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    taskWidgetPage.filterTasksInExpendedModeBy("Order");
    taskWidgetPage.sortTaskListByColumn(CUSTOMER_NAME_COLUMN_HEADER);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Florian".equals(taskWidgetPage.getTaskListCustomCellValue(0, CUSTOMER_NAME_COLUMN_ID)));

    taskWidgetPage.sortTaskListByColumn(CUSTOMER_NAME_COLUMN_HEADER);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Florian".equals(taskWidgetPage.getTaskListCustomCellValue(2, CUSTOMER_NAME_COLUMN_ID)));
  }
}
