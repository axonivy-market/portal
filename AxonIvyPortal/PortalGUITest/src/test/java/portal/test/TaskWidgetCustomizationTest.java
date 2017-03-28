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
    assertFalse(taskWidgetPage.isTaskListColumnExist("State"));
    assertTrue(taskWidgetPage.isTaskListColumnExist("CustomVarcharField5"));
    assertTrue("Other Leave/Maternity".equals(taskWidgetPage.getTaskListCellValue(0, "custom-varchar-field5")));
  }
  
  @Test
  public void testShowHideCustomColumnsInTaskListPage() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertFalse(taskWidgetPage.isTaskListColumnExist("State"));
    assertTrue(taskWidgetPage.isTaskListColumnExist("CustomVarcharField5"));
    assertTrue("Other Leave/Maternity".equals(taskWidgetPage.getTaskListCellValue(0, "custom-varchar-field5")));
  }
  
  @Test
  public void testSortCustomColumnsInTaskWidget() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    taskWidgetPage.sortTaskListByColumn("CustomVarcharField5");
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Other Leave/Maternity".equals(taskWidgetPage.getTaskListCellValue(0, "custom-varchar-field5")));
    
    taskWidgetPage.sortTaskListByColumn("CustomVarcharField5");
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Other Leave/Sick/Long".equals(taskWidgetPage.getTaskListCellValue(0, "custom-varchar-field5")));
  }
  
  @Test
  public void testSortCustomColumnsInTaskListPage() {
  	MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.selectTaskMenu();
    taskWidgetPage.waitAjaxIndicatorDisappear();
    taskWidgetPage.sortTaskListByColumn("CustomVarcharField5");
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Annual Leave".equals(taskWidgetPage.getTaskListCellValue(0, "custom-varchar-field5")));
    
    taskWidgetPage.sortTaskListByColumn("CustomVarcharField5");
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue("Other Leave/Sick/Long".equals(taskWidgetPage.getTaskListCellValue(0, "custom-varchar-field5")));
  }
}
