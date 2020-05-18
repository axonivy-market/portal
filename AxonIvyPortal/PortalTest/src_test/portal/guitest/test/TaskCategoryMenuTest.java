package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskWidgetPage;

public class TaskCategoryMenuTest extends BaseTest {

  private HomePage homePage;

  @Before
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void testSelectTaskCategoryMenuAsNormalUser() {
    homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    assertEquals(3, taskWidgetPage.countTasks());
  }

  @Test
  public void testSelectTaskCategoryMenuAsAdminRole() {
    login(TestAccount.ADMIN_USER);
    homePage = new HomePage();
    TaskWidgetPage taskWidgetPage = homePage.getTaskWidget();
    taskWidgetPage.expand();
    assertEquals(4, taskWidgetPage.countTasks());
  }
}
