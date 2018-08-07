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

  private static final String CUSTOM_VARCHAR_FIELD5_COLUMN_ID = "custom-varchar-field5";
  private static final String STATE_COLUMN_NAME = "State";
  private static final String CUSTOM_VARCHAR_FIELD5_COLUMN_HEADER = "CustomVarcharField5";

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
    assertTrue(taskWidgetPage.isTaskListColumnExist(CUSTOM_VARCHAR_FIELD5_COLUMN_HEADER));
    assertTrue("Other Leave/Maternity".equals(taskWidgetPage.getTaskListCellValue(0, CUSTOM_VARCHAR_FIELD5_COLUMN_ID)));
  }

  @Test
  public void testShowHideCustomColumnsInTaskListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertFalse(taskWidgetPage.isTaskListColumnExist(STATE_COLUMN_NAME));
    assertTrue(taskWidgetPage.isTaskListColumnExist(CUSTOM_VARCHAR_FIELD5_COLUMN_HEADER));
    assertTrue("Other Leave/Maternity".equals(taskWidgetPage.getTaskListCellValue(0, CUSTOM_VARCHAR_FIELD5_COLUMN_ID)));
  }

  @Test
  public void testSortCustomColumnsInTaskWidget() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    taskWidgetPage.sortTaskListByColumn(CUSTOM_VARCHAR_FIELD5_COLUMN_HEADER);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Other Leave/Sick/Long".equals(taskWidgetPage.getTaskListCellValue(0, CUSTOM_VARCHAR_FIELD5_COLUMN_ID)));

    taskWidgetPage.sortTaskListByColumn(CUSTOM_VARCHAR_FIELD5_COLUMN_HEADER);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Other Leave/Maternity".equals(taskWidgetPage.getTaskListCellValue(0, CUSTOM_VARCHAR_FIELD5_COLUMN_ID)));
  }

  @Test
  public void testSortCustomColumnsInTaskListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    taskWidgetPage.sortTaskListByColumn(CUSTOM_VARCHAR_FIELD5_COLUMN_HEADER);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Annual Leave".equals(taskWidgetPage.getTaskListCellValue(0, CUSTOM_VARCHAR_FIELD5_COLUMN_ID)));

    taskWidgetPage.sortTaskListByColumn(CUSTOM_VARCHAR_FIELD5_COLUMN_HEADER);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Annual Leave".equals(taskWidgetPage.getTaskListCellValue(3, CUSTOM_VARCHAR_FIELD5_COLUMN_ID)));
  }
}
