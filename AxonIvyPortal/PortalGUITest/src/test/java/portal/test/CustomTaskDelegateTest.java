package portal.test;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.TaskWidgetPage;

public class CustomTaskDelegateTest extends BaseTest {

  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.INTERNAL_SUPPORT_HOME_PAGE_URL);
  }

  @Test
  public void testCustomTaskDelegateOnlyToGroup() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.waitAjaxIndicatorDisappear();

    taskWidgetPage.openTaskDelegateDialog(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertFalse(taskWidgetPage.isDelegateTypeSelectAvailable());
    assertTrue(taskWidgetPage.isDelegateListSelectionAvailable());
  }

  @Test
  public void testCustomTaskDelegateOnlyToUser() {
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.waitAjaxIndicatorDisappear();

    taskWidgetPage.openTaskDelegateDialog(1);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue(taskWidgetPage.isDelegateTypeSelectAvailable());
    assertTrue(taskWidgetPage.isDelegateListSelectionAvailable());
  }

  @Test
  public void testCustomTaskDelegateNoDelegateOption() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.waitAjaxIndicatorDisappear();

    taskWidgetPage.openTaskDelegateDialog(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    
    assertFalse(taskWidgetPage.isDelegateTypeSelectAvailable());
    assertFalse(taskWidgetPage.isDelegateListSelectionAvailable());
  }
}
