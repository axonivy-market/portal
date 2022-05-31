package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskFilterTest extends BaseTest {

  @Before
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testFilterTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks());
    taskWidgetPage.filterTasksBy("Maternity");
    assertEquals(1, taskWidgetPage.countTasks());
    taskWidgetPage.filterTasksBy("Sick Leave Request Description");
    assertEquals(1, taskWidgetPage.countTasks());
  }

  @Test
  public void testAdvancedFilterTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(3, taskWidgetPage.countTasks());

    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");

    assertEquals(1, taskWidgetPage.countTasks());
  }

  @Test
  public void testShowDoneStateFilterForNormalUser() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(3, taskWidgetPage.countTasks());

    String stateFilterValue = taskWidgetPage.getFilterValue("state-filter");
    assertEquals("State: Created, Suspended, In progress, Reserved, Ready for joining", stateFilterValue);

    taskWidgetPage.openStateFilter();
    assertTrue(taskWidgetPage.getListStateFilterSelection().contains("Done"));
  }

  @Test
  public void testKeepSessionFilter() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    final TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");

    mainMenuPage.openCaseList();
    TaskWidgetPage taskWidgetPage2 = mainMenuPage.openTaskList();
    assertEquals(1, taskWidgetPage2.countTasks());
    assertTrue(taskWidgetPage2.isAdvancedFilterDisplayed("description"));
  }

  @Test
  public void testSaveTaskFilter() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    String filterName = "Maternity";
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription(filterName);
    taskWidgetPage.saveFilter(filterName);
    assertEquals(filterName, taskWidgetPage.getFilterName());
  }

  @SuppressWarnings("deprecation")
  @Test
  public void testSaveTaskFilterForAdmin() {
    List<String> adminStates = Arrays.asList("Ready for joining", "Suspended", "In progress", "Reserved", "Delayed",
        "Done", "Destroyed", "Failed", "Join failed", "Waiting for event");
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

    String filterName = "For admins";
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");

    WebElement saveFilterDialog = taskWidgetPage.openSaveFilterDialog();
    assertEquals("All users", saveFilterDialog
        .findElement(By.cssSelector("label[for='task-widget:filter-save-form:save-filter-type-radio:1']")).getText());
    taskWidgetPage.click(saveFilterDialog.findElement(By.cssSelector("a")));
    taskWidgetPage.waitAjaxIndicatorDisappear();

    taskWidgetPage.filterByStates(adminStates);

    saveFilterDialog = taskWidgetPage.openSaveFilterDialog();
    assertEquals("All administrators", saveFilterDialog
        .findElement(By.cssSelector("label[for='task-widget:filter-save-form:save-filter-type-radio:1']")).getText());
    taskWidgetPage.click(saveFilterDialog.findElement(By.cssSelector("a")));
    taskWidgetPage.waitAjaxIndicatorDisappear();

    taskWidgetPage.saveAdminFilter(filterName);

    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    mainMenuPage = homePage.openMainMenu();
    taskWidgetPage = mainMenuPage.openTaskList();
    assertFalse(taskWidgetPage.isExistedFilter(filterName));
  }

  @Test
  public void testSaveTaskFilterOnDifferentTaskList() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    String filterName = "myFilter";

    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Sick");
    taskWidgetPage.saveFilter(filterName);

    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    taskWidgetPage.expand();

    assertTrue(taskWidgetPage.getFilterName().contains("Default filter"));

    taskWidgetPage.openAdvancedFilter("Customer name", "customer-name");
    taskWidgetPage.filterByCustomerName("Anh");
    taskWidgetPage.saveFilter(filterName);

    mainMenuPage.selectCaseMenu();
    taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(filterName, taskWidgetPage.getFilterName());
  }

  @Test
  public void testShowTaskWithNotExistsedActivatorToPersonHaveTaskReadAllPermission() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTaskWithNotExistedActivatorUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(6, taskWidgetPage.countTasks());

    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    assertEquals(4, taskWidgetPage.countTasks());
    assertEquals("Not exist user", taskWidgetPage.getResponsibleOfTaskAt(0));
  }

  @Test
  public void testShowSystemStatesFilterForAdminUser() {
    List<String> adminStates = Arrays.asList("Created", "Ready for joining", "Suspended", "In progress", "Reserved",
        "Delayed", "Done", "Destroyed", "Failed", "Join failed", "Waiting for event");
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

    String stateFilterValue = taskWidgetPage.getFilterValue("state-filter");
    assertEquals("State: All", stateFilterValue);

    taskWidgetPage.openStateFilter();
    List<String> states = taskWidgetPage.getListStateFilterSelection();
    assertTrue(states.size() == adminStates.size());
    assertTrue(states.containsAll(adminStates));
  }

  @Test
  public void testNotShowTaskWithNotExistsedActivatorToPersonNotHaveTaskReadAllPermission() {
    redirectToRelativeLink(createTaskWithNotExistedActivatorUrl);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    assertEquals(3, taskWidgetPage.countTasks());
  }

  @Test
  public void testCategory() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    String taskCategoryId = "task-category";
    taskWidgetPage.openAdvancedFilter("Category", taskCategoryId);
    assertEquals("Category: All", taskWidgetPage.getFilterValue(taskCategoryId + "-filter"));
    taskWidgetPage.openCategoryFilter();
    assertTrue(taskWidgetPage.isAllCategoriesSelected());

    taskWidgetPage.toggleNoCategory();

    assertTrue(taskWidgetPage.isAllCategoriesUnselected());

    taskWidgetPage.applyCategoryFilter();

    assertNotEquals("Category: All", taskWidgetPage.getFilterValue(taskCategoryId + "-filter"));
  }

  @Test
  public void testRemoveResponsibleAndSwitchFilter() {
    // Prepare 2 filter
    String filterResponsible = "Responsible";
    String filterMaternity = "Maternity";

    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription(filterMaternity);
    taskWidgetPage.saveFilter(filterMaternity);

    taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.filterByResponsible("Everybody");
    taskWidgetPage.saveFilter(filterResponsible);
    // Switch filter and remove responsible
    taskWidgetPage.openSavedFilters(filterMaternity);
    taskWidgetPage.openSavedFilters(filterResponsible);
    taskWidgetPage.removeResponsibleFilter();
    taskWidgetPage.openSavedFilters(filterMaternity);
    taskWidgetPage.openSavedFilters(filterResponsible);

    assertTrue(taskWidgetPage.getResponsible().contains("Everybody"));
  }

  @Test
  public void testResponsibleWithChangeFilter() {
    // Prepare 2 filter
    String filterResponsible = "Responsible";
    String filterMaternity = "Maternity";

    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByResponsible("Everybody");
    taskWidgetPage.filterByDescription(filterMaternity);
    taskWidgetPage.saveFilter(filterMaternity);

    taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.filterByResponsible("Demo");
    taskWidgetPage.saveFilter(filterResponsible);
    // Change filter and verify responsible changed
    taskWidgetPage.openSavedFilters(filterMaternity);

    assertTrue(taskWidgetPage.getResponsible().contains("Everybody"));
  }

  @Test
  public void testDefaultFilter() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertTrue(taskWidgetPage.getFilterName().contains("Default filter"));
  }

  @Test
  public void testNoSelectionWhenChangeFilter() {
    String filterMaternity = "Maternity";
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription(filterMaternity);
    taskWidgetPage.saveFilter(filterMaternity);
    taskWidgetPage.filterByResponsible("Demo");

    assertTrue(taskWidgetPage.getFilterName().contains("No Selection"));
  }

  @Test
  public void testResetFilter() {
    String filterMaternity = "Maternity";
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription(filterMaternity);
    taskWidgetPage.resetFilter();

    assertTrue(taskWidgetPage.getFilterName().contains("Default filter"));
  }

  @Test
  public void testTaskFilterForUnavailableActivator() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

    String filterName = "For admins";
    taskWidgetPage.openNoActivatorFilter("Missing activator");
    assertEquals(4, taskWidgetPage.countTasks());
    taskWidgetPage.filterByUnavailableActivator(true);
    assertEquals(1, taskWidgetPage.countTasks());

    WebElement saveFilterDialog = taskWidgetPage.openSaveFilterDialog();
    assertEquals("All users", saveFilterDialog
        .findElement(By.cssSelector("label[for='task-widget:filter-save-form:save-filter-type-radio:1']")).getText());
    taskWidgetPage.saveAdminFilter("admin filter");

    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    mainMenuPage = homePage.openMainMenu();
    taskWidgetPage = mainMenuPage.openTaskList();
    assertFalse(taskWidgetPage.isExistedFilter(filterName));
  }
}
