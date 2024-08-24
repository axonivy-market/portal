package com.axonivy.portal.selenium.test.task;

import static org.junit.jupiter.api.Assertions.fail;

import java.text.SimpleDateFormat;
import java.util.Date;

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

@IvyWebTest(headless = false)
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
  @RepeatedTest(10)
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
    TaskWidgetPage taskWidgetPage2 = mainMenuPage.openDeveloperExamplesTaskList();
    assertEquals(1, taskWidgetPage2.countTasks().size());
    assertTrue(taskWidgetPage2.isAdvancedFilterDisplayed("description"));
  }
  
  
  
  
  
  
  
  
  /**
   * 
  
  testNoSelectionWhenChangeFilter()
  
  testNotShowTaskWithNotExistsedActivatorToPersonNotHaveTaskReadAllPermission()
  
  testRemoveResponsibleAndSwitchFilter()
  
  testResetFilter()
  
  testResponsibleWithChangeFilter()
  
  testSaveTaskFilter()
  
  testSaveTaskFilterForAdmin()
  
  testSaveTaskFilterOnDifferentTaskList()
  
  testShowDoneStateFilterForNormalUser()
  
  testShowSystemStatesFilterForAdminUser()
  
  testShowTaskWithNotExistsedActivatorToPersonHaveTaskReadAllPermission()
  
  testTaskFilterForUnavailableActivator()
   */
}
