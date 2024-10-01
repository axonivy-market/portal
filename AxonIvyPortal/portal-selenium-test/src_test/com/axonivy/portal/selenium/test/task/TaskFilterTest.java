package com.axonivy.portal.selenium.test.task;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class TaskFilterTest extends BaseTest {

  private static final String EMPTY = "";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testFilterTasksByCreatedDate() {
    TaskWidgetPage taskWidgetPage = createTestData();
    taskWidgetPage.openAdvancedFilter("Created (from/to)", "created");
    String fromInputText = new SimpleDateFormat(DateTimePattern.DATE_PATTERN).format(new Date());
    taskWidgetPage.filterTasksByCreatedDate(fromInputText, EMPTY);
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
  }

  @Test
  public void testFilterTasksByApplication() {
    TaskWidgetPage taskWidgetPage = createTestData();
    int before = taskWidgetPage.countTasks().size();
    taskWidgetPage.openAdvancedFilter("Application", "application");
    taskWidgetPage.filterFirstApp();
    int after = taskWidgetPage.countTasks().size();
    Assertions.assertEquals(before, after);
  }

  private TaskWidgetPage createTestData() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.waitForGrowlMessageDisappear();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    return taskWidgetPage;
  }

  @Test
  public void testAdvancedFilterTask() { 
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(3, taskWidgetPage.countTasks().size());

    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");

    assertEquals(1, taskWidgetPage.countTasks().size());
  }
  
 
  @Test
  public void testCategory() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
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
  public void testDefaultFilter() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertTrue(taskWidgetPage.getFilterName().contains("Default filter"));
  }
 
  
  @Test
  public void testFilterTask() {
    updateLegacyUIConfiguration();
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(3, taskWidgetPage.countTasks().size());

    taskWidgetPage.filterTasksBy("Maternity");
    taskWidgetPage.waitTillOnlyOneTaskAppear();
    assertEquals(1, taskWidgetPage.countTasks().size());

    taskWidgetPage.filterTasksBy("Sick Leave Request Description");
    taskWidgetPage.waitTillOnlyOneTaskAppear();
    assertEquals(1, taskWidgetPage.countTasks().size());
  }
   
  // good
  @Test
  public void testKeepSessionFilter() {
    updateLegacyUIConfiguration();
    createTestData();
    
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openDeveloperExamplesTaskList();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");

    TaskWidgetPage taskWidgetPage2 = new TaskWidgetPage();
    assertEquals(1, taskWidgetPage2.countTasks().size());
    assertTrue(taskWidgetPage2.isAdvancedFilterDisplayed("description"));
  }
  
  @Test
  public void testNoSelectionWhenChangeFilter() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
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
  public void testNotShowTaskWithNotExistsedActivatorToPersonNotHaveTaskReadAllPermission() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(createTaskWithNotExistedActivatorUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(3, taskWidgetPage.countTasks().size());
  }

  
  @Test
  public void testRemoveResponsibleAndSwitchFilter() {
    updateLegacyUIConfiguration();
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
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
    taskWidgetPage.waitForDesiredResponsibleRendered("Responsible: \"Everybody (Everybody)\"");
    // Switch filter and remove responsible
    taskWidgetPage.openSavedFilters(filterMaternity);
    taskWidgetPage.openSavedFilters(filterResponsible);
    taskWidgetPage.removeResponsibleFilter();

//    homePage.clickOnLogo();
//    new TaskWidgetPage().expand();
    taskWidgetPage.openSavedFilters(filterMaternity);
    taskWidgetPage.openSavedFilters(filterResponsible);

    String responsible = taskWidgetPage.getResponsible();
    assertTrue(responsible.contains("Everybody"));
  }
  
  @Test
  public void testResetFilter() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
    String filterMaternity = "Maternity";
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription(filterMaternity);
    taskWidgetPage.resetFilter();

    assertTrue(taskWidgetPage.getFilterName().contains("Default filter"));
  }
  
  @Test
  public void testResponsibleWithChangeFilter() {
    updateLegacyUIConfiguration();
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
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
    taskWidgetPage.openSavedFilters(filterMaternity);
    taskWidgetPage.waitForDesiredResponsibleRendered("Responsible: \"Everybody (Everybody)\"");

    taskWidgetPage.filterByResponsible("Demo");
    taskWidgetPage.saveFilter(filterResponsible);
    taskWidgetPage.openSavedFilters(filterResponsible);
    taskWidgetPage.waitForDesiredResponsibleRendered("Responsible: \"Portal Demo User (demo)\"");

    // Change filter and verify responsible changed
    refreshPage();
    taskWidgetPage.openSavedFilters(filterMaternity);
    taskWidgetPage.waitForDesiredResponsibleRendered("Responsible: \"Everybody (Everybody)\"");

    String responsible = taskWidgetPage.getResponsible();
    assertTrue(responsible.contains("Everybody"));
  }

  @Test
  public void testSaveTaskFilter() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();

    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    String filterName = "Maternity";
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription(filterName);
    taskWidgetPage.saveFilter(filterName);
    assertEquals(filterName, taskWidgetPage.getFilterName());
  }

  @Test
  public void testSaveTaskFilterForAdmin() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openDeveloperExamplesTaskList();

    String filterName = "For admins";
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");

    taskWidgetPage.saveFilterForAllAdministrators("All administrators");
    taskWidgetPage.openSavedPublicFilters("All administrators");

    login(TestAccount.DEMO_USER);
    new TaskWidgetPage().expand();
    assertFalse(taskWidgetPage.isExistedFilter(filterName));
  }
  
  
  @Test
  public void testSaveTaskFilterOnDifferentTaskList() {
    updateLegacyUIConfiguration();
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    String filterName = "myFilter";

    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Sick");
    taskWidgetPage.saveFilter(filterName);

    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertTrue(taskWidgetPage.getFilterName().contains("Default filter"));

    taskWidgetPage.openAdvancedFilter("Customer name", "customer-name");
    taskWidgetPage.filterByCustomerName("Anh");
    taskWidgetPage.saveFilter(filterName);

    assertEquals(filterName, taskWidgetPage.getFilterName());
  }

  @Test
  public void testShowDoneStateFilterForNormalUser() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(3, taskWidgetPage.countTasks().size());

    String stateFilterValue = taskWidgetPage.getFilterValue("state-filter");
    assertEquals("State: Created, Suspended, In progress, Reserved, Ready for join", stateFilterValue);

    taskWidgetPage.openStateFilter();
    assertTrue(taskWidgetPage.getListStateFilterSelection().contains("Done"));
  }
  
  
  @Test
  public void testShowSystemStatesFilterFokAdminUser() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
    List<String> adminStates = Arrays.asList("Created", "Ready for join", "Suspended", "In progress", "Reserved",
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
  public void testShowTaskWithNotExistsedActivatorToPersonHaveTaskReadAllPermission() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTaskWithNotExistedActivatorUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(6, taskWidgetPage.countTasks().size());

    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Suspended"));
    assertEquals(4, taskWidgetPage.countTasks().size());
    assertEquals("Not exist user", taskWidgetPage.getResponsibleOfTaskAt(0));
  }
  
  @Test
  public void testTaskFilterForUnavailableActivator() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();

    String filterName = "For admins";
    taskWidgetPage.openNoActivatorFilter("Missing activator");
    assertEquals(4, taskWidgetPage.countTasks().size());
    taskWidgetPage.filterByUnavailableActivator(true);
    assertEquals(1, taskWidgetPage.countTasks().size());
    taskWidgetPage.saveFilterForAllAdministrators("admin filter");

    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    new TaskWidgetPage().expand();
    assertFalse(taskWidgetPage.isExistedFilter(filterName));
  }
}
