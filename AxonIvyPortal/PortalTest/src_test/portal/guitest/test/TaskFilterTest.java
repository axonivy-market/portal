package portal.guitest.test;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskFilterTest extends BaseTest {

  @Before
  @Override
  public void setup() {
    super.setup();
    navigateToUrl(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testFilterTask() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks());
    taskWidgetPage.filterTasksBy("Maternity");
    assertEquals(1, taskWidgetPage.countTasks());
    taskWidgetPage.filterTasksBy("Sick Leave Request Description");
    assertEquals(1, taskWidgetPage.countTasks());
  }

  @Test
  public void testAdvancedFilterTask() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(3, taskWidgetPage.countTasks());

    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");

    assertEquals(1, taskWidgetPage.countTasks());
  }
  
  @Test
  public void testShowDoneStateFilterForNormalUser() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(3, taskWidgetPage.countTasks());

    String stateFilterValue = taskWidgetPage.getFilterValue("state-filter");
    assertEquals("State: Suspended, In progress, Reserved", stateFilterValue);
    
    taskWidgetPage.openStateFilter();
    assertEquals("Done", taskWidgetPage.getStateFilterSelection(3));
  }
  
  @Test
  public void testKeepSessionFilter() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");
    assertEquals(1, taskWidgetPage.countTasks());
    
    mainMenuPage.openCaseList();
    taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(1, taskWidgetPage.countTasks());
    assertTrue(taskWidgetPage.isAdvancedFilterDisplayed("description"));
  }

  @Test
  public void testSaveTaskFilter() {
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

    String filterName = "Maternity";
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription(filterName);
    taskWidgetPage.saveFilter(filterName);

    mainMenuPage.selectCaseMenu();
    taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(filterName, taskWidgetPage.getFilterName());
  }

  @Test
  public void testSaveTaskFilterOnDifferentTaskList() {  //TODO FEATURE BROKEN
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

    String filterName = "MyFilter";

    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Sick");
    taskWidgetPage.saveFilter(filterName);

    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    taskWidgetPage = mainMenuPage.openTaskList();

    assertFalse(taskWidgetPage.isFilterSelectionVisible());

    taskWidgetPage.openAdvancedFilter("Customer name", "customer-name");
    taskWidgetPage.filterByCustomerName("Anh");
    taskWidgetPage.saveFilter(filterName);

    mainMenuPage.selectCaseMenu();
    taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(filterName, taskWidgetPage.getFilterName());
  }
}
