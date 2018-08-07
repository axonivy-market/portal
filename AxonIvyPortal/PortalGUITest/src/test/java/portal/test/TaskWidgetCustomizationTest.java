package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.MainMenuPage;
import portal.page.TaskWidgetPage;

public class TaskWidgetCustomizationTest extends BaseTest {

  private static final String CUSTOMER_NAME_COLUMN_ID = "customer-name";
  private static final String STATE_COLUMN_NAME = "State";
  private static final String CUSTOMER_NAME_COLUMN_HEADER = "Customer name";

  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.INTERNAL_SUPPORT_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
  }

  @Test
  public void testShowHideCustomColumnsInTaskWidget() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertFalse(taskWidgetPage.isTaskListColumnExist(STATE_COLUMN_NAME));
    assertTrue(taskWidgetPage.isTaskListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    assertTrue("Anh Nguyen".equals(taskWidgetPage.getTaskListCellValue(0, CUSTOMER_NAME_COLUMN_ID)));
  }

  @Test
  public void testShowHideCustomColumnsInTaskListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertFalse(taskWidgetPage.isTaskListColumnExist(STATE_COLUMN_NAME));
    assertTrue(taskWidgetPage.isTaskListColumnExist(CUSTOMER_NAME_COLUMN_HEADER));
    assertTrue("Anh Nguyen".equals(taskWidgetPage.getTaskListCellValue(0, CUSTOMER_NAME_COLUMN_ID)));
  }

  @Test
  public void testSortCustomColumnsInTaskListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    taskWidgetPage.sortTaskListByColumn(CUSTOMER_NAME_COLUMN_HEADER);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Anh Nguyen".equals(taskWidgetPage.getTaskListCellValue(0, CUSTOMER_NAME_COLUMN_ID)));

    taskWidgetPage.sortTaskListByColumn(CUSTOMER_NAME_COLUMN_HEADER);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Anh Nguyen".equals(taskWidgetPage.getTaskListCellValue(2, CUSTOMER_NAME_COLUMN_ID)));
  }
}
