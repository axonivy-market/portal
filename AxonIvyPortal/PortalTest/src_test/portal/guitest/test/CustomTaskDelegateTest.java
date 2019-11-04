package portal.guitest.test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskWidgetPage;

public class CustomTaskDelegateTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
  }

  @Test
  public void testCustomTaskDelegateOnlyToGroup() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    taskWidgetPage.openTaskDelegateDialog(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue(taskWidgetPage.isDelegateTypeSelectAvailable());
    // Radio button "User" is enabled
    assertTrue(taskWidgetPage.isDelegateTypeDisabled(0,0));
    // Radio button "Group" is disabled
    assertFalse(taskWidgetPage.isDelegateTypeDisabled(0,1));
    assertTrue(taskWidgetPage.isDelegateListSelectionAvailable());
  }

  @Test
  public void testCustomTaskDelegateOnlyToUser() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    taskWidgetPage.openTaskDelegateDialog(1);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    assertTrue(taskWidgetPage.isDelegateTypeSelectAvailable());
    assertTrue(taskWidgetPage.isDelegateListSelectionAvailable());
  }

  @Test
  public void testCustomTaskDelegateNoDelegateOption() {
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    taskWidgetPage.openTaskDelegateDialog(0);
    taskWidgetPage.waitAjaxIndicatorDisappear();
    
    assertFalse(taskWidgetPage.isDelegateTypeSelectAvailable());
    assertFalse(taskWidgetPage.isDelegateListSelectionAvailable());
  }
}
