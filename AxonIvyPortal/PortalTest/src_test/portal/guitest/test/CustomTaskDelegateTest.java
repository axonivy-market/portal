package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
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
    WaitHelper.assertTrueWithWait(() -> taskWidgetPage.isDelegateTypeSelectAvailable());
    // Radio button "User" is enabled
    assertTrue(taskWidgetPage.isDelegateTypeDisabled(0));
    // Radio button "Group" is disabled
    assertFalse(taskWidgetPage.isDelegateTypeDisabled(1));
    assertTrue(taskWidgetPage.isDelegateListSelectionAvailable());
  }

  @Test
  public void testCustomTaskDelegateOnlyToUser() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    taskWidgetPage.openTaskDelegateDialog(1);
    WaitHelper.assertTrueWithWait(() -> taskWidgetPage.isDelegateTypeSelectAvailable());
    assertTrue(taskWidgetPage.isDelegateListSelectionAvailable());
  }

  @Test
  public void testCustomTaskDelegateNoDelegateOption() {
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    taskWidgetPage.openTaskDelegateDialog(0);
    
    assertFalse(taskWidgetPage.isDelegateTypeSelectAvailable());
    assertFalse(taskWidgetPage.isDelegateListSelectionAvailable());
  }
}
