package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.TaskWidgetPage;

public class TaskCategoryMenuTest extends BaseTest {

  private NewDashboardPage2 newDashboardPage2;

  @Before
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void testSelectTaskCategoryMenuAsNormalUser() {
    newDashboardPage2 = new NewDashboardPage2();
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTasList();
    assertEquals(3, taskWidgetPage.countTasks());
  }

  @Test
  public void testSelectTaskCategoryMenuAsAdminRole() {
    login(TestAccount.ADMIN_USER);
    newDashboardPage2 = new NewDashboardPage2();
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTasList();
    assertEquals(4, taskWidgetPage.countTasks());
  }
}
