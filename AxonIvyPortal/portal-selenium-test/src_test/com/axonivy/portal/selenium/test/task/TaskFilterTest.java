package com.axonivy.portal.selenium.test.task;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
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
    /**
     * Note: stabilize the expand() function
     */
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
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    assertEquals(3, taskWidgetPage.countTasks().size());

    /**
     * Note: filterTasksBy is unstable because when enter the 
     * text we don't have enough time to see it filtering the 
     * task. I see that it still keeps the filter option after
     * we go back to home and go back to full task list again
     * so I do it to buy some time for the rendering
     */
    taskWidgetPage.filterTasksBy("Maternity");
    homePage.clickOnLogo();
    new TaskWidgetPage().expand();
    assertEquals(1, taskWidgetPage.countTasks().size());

    taskWidgetPage.filterTasksBy("Sick Leave Request Description");
    homePage.clickOnLogo();
    new TaskWidgetPage().expand();
    assertEquals(1, taskWidgetPage.countTasks().size());
  }
   
  @Test
  public void testKeepSessionFilter() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
    MainMenuPage mainMenuPage = new MainMenuPage();
    final TaskWidgetPage taskWidgetPage = mainMenuPage.openDeveloperExamplesTaskList();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");

    homePage.clickOnLogo();
    TaskWidgetPage taskWidgetPage2 = new TaskWidgetPage();
    taskWidgetPage2.expand();
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
    redirectToRelativeLink(createTestingTasksUrl);
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

    taskWidgetPage = mainMenuPage.openDeveloperExamplesTaskList();
    taskWidgetPage.filterByResponsible("Everybody");
    taskWidgetPage.saveFilter(filterResponsible);
    // Switch filter and remove responsible
    taskWidgetPage.openSavedFilters(filterMaternity);
    taskWidgetPage.openSavedFilters(filterResponsible);
    taskWidgetPage.removeResponsibleFilter();

    homePage.clickOnLogo();
    new TaskWidgetPage().expand();
    taskWidgetPage.openSavedFilters(filterMaternity);
    taskWidgetPage.openSavedFilters(filterResponsible);

    assertTrue(taskWidgetPage.getResponsible().contains("Everybody"));
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
    redirectToRelativeLink(createTestingTasksUrl);
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

    taskWidgetPage = mainMenuPage.openDeveloperExamplesTaskList();
    taskWidgetPage.filterByResponsible("Demo");
    taskWidgetPage.saveFilter(filterResponsible);
    // Change filter and verify responsible changed
    taskWidgetPage.openSavedFilters(filterMaternity);

    assertTrue(taskWidgetPage.getResponsible().contains("Everybody"));
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
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    String filterName = "myFilter";

    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Sick");
    taskWidgetPage.saveFilter(filterName);

    login(TestAccount.DEMO_USER);
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
    assertEquals("State: Created, Suspended, In progress, Reserved, Ready for joining", stateFilterValue);

    taskWidgetPage.openStateFilter();
    assertTrue(taskWidgetPage.getListStateFilterSelection().contains("Done"));
  }
  
  
  @Test
  public void testShowSystemStatesFilterFokAdminUser() {
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();
    
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
