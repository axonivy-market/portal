package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.StatisticWidgetPage;
import portal.guitest.page.TaskAnalysisWidgetPage;

public class TaskAnalysisWidgetTest extends BaseTest {

  private HomePage homePage;
  private StatisticWidgetPage statisticWidgetPage;
  private MainMenuPage mainMenuPage;

  @Override
  @Before
  public void setup() {
    super.setup();
    createTestingTasks();
    createTestData();
    login(TestAccount.ADMIN_USER);
    grantPermissionOfPortal();
    homePage = new HomePage();
    mainMenuPage = homePage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
  }

  private void grantPermissionOfPortal() {
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }

  @Test
  public void testNavigateToAnalysisPage() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    assertTrue(taskAnalysisWidgetPage.isDisplayed());
  }

  @Test
  public void testBackToStatisticPage() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    statisticWidgetPage = taskAnalysisWidgetPage.navigateToStatisticPage();
    assertTrue(statisticWidgetPage.isDisplayed());
  }

  @Test
  public void testAddColumns() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();

    WebElement toggler = taskAnalysisWidgetPage.findColumnToggler();
    taskAnalysisWidgetPage.click(toggler);

    WebElement columnContainer = taskAnalysisWidgetPage.getDriver().findElement(By.tagName("body"))
        .findElement(By.cssSelector(".ui-columntoggler"));
    int numberOfCheckboxes = columnContainer.findElements(By.cssSelector(".ui-chkbox-box")).size();
    List<WebElement> unselectedColumnCheckboxes =
        columnContainer.findElements(By.cssSelector(".ui-chkbox-box:not(.ui-state-active)"));

    unselectedColumnCheckboxes.forEach(elem -> taskAnalysisWidgetPage.click(elem));
    taskAnalysisWidgetPage.waitForPageLoaded();

    int numberOfColumns = taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_head")
        .findElements(By.cssSelector("th[scope='col']:not(.ui-helper-hidden)")).size();
    assertEquals(numberOfColumns, numberOfCheckboxes);
  }

  @Test
  public void testRemoveColumns() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();

    WebElement toggler = taskAnalysisWidgetPage.findColumnToggler();
    taskAnalysisWidgetPage.click(toggler);

    WebElement columnContainer = taskAnalysisWidgetPage.getDriver().findElement(By.tagName("body"))
        .findElement(By.cssSelector(".ui-columntoggler"));
    List<WebElement> selectedColumnCheckboxes =
        columnContainer.findElements(By.cssSelector(".ui-chkbox-box.ui-state-active"));

    taskAnalysisWidgetPage.click(selectedColumnCheckboxes.get(0));
    taskAnalysisWidgetPage.click(selectedColumnCheckboxes.get(1));
    selectedColumnCheckboxes.remove(0);
    selectedColumnCheckboxes.remove(1);

    taskAnalysisWidgetPage.waitForPageLoaded();

    int numberOfColumns = taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_head")
        .findElements(By.cssSelector("th[scope='col']:not(.ui-helper-hidden)")).size();

    assertEquals(numberOfColumns, selectedColumnCheckboxes.size());
  }

  @Test
  public void testApplyTaskFilters() {
    String nameKeyword = "request";
    String prioritySeletion = "NORMAL";

    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.openAdvancedTaskFilter("Name", "name");
    taskAnalysisWidgetPage.filterByTaskName(nameKeyword);

    taskAnalysisWidgetPage.openAdvancedTaskFilter("Priority", "priority");
    List<String> selectedPriorities = new ArrayList<>();
    selectedPriorities.add(prioritySeletion);
    taskAnalysisWidgetPage.filterByTaskPriority(selectedPriorities);
    taskAnalysisWidgetPage.clickApplyFilter();

    List<WebElement> results = taskAnalysisWidgetPage.getRowsInTaskTable();

    assertEquals(2, results.size());

    for (WebElement resultRow : results) {
      List<WebElement> resultCells = resultRow.findElements(By.cssSelector("td:not([class='ui-helper-hidden'])"));
      assertTrue(resultCells.get(2).getText().toLowerCase().contains(nameKeyword));
      assertTrue(resultCells.get(3).getText().equals(prioritySeletion));
    }
  }

  @Test
  public void testApplyCaseFilters() {
    String keyword = "request";
    String stateSeletion = "In progress";

    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.openAdvancedCaseFilter("State", "state");
    taskAnalysisWidgetPage.openAdvancedCaseFilter("Name", "case-name");
    taskAnalysisWidgetPage.filterByCaseName(keyword);

    List<String> selectedStates = new ArrayList<>();
    selectedStates.add(stateSeletion);
    taskAnalysisWidgetPage.filterByCaseState(selectedStates);
    taskAnalysisWidgetPage.clickApplyFilter();

    List<WebElement> results = taskAnalysisWidgetPage.getRowsInTaskTable();

    assertEquals(4, results.size());

    for (WebElement resultRow : results) {
      List<WebElement> resultCells = resultRow.findElements(By.cssSelector("td:not([class='ui-helper-hidden'])"));
      assertTrue(resultCells.get(0).getText().toLowerCase().contains(keyword));
      assertTrue(resultCells.get(1).getText().equals("RUNNING"));
    }
  }

  @Test
  public void testApplyTaskCategoryFilter() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.openAdvancedTaskFilter("Task category", "task-category");
    taskAnalysisWidgetPage.filterByTaskCategory("Other Leave");
    taskAnalysisWidgetPage.clickApplyFilter();
    List<WebElement> results = taskAnalysisWidgetPage.getRowsInTaskTable();

    assertEquals(2, results.size());
  }

  @Test
  public void testApplyCaseCategoryFilter() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.openAdvancedCaseFilter("Case category", "case-category");
    taskAnalysisWidgetPage.filterByCaseCategory("Leave Request");
    taskAnalysisWidgetPage.clickApplyFilter();

    List<WebElement> results = taskAnalysisWidgetPage.getRowsInTaskTable();
    assertEquals(4, results.size());
  }
  
  @Test
  public void testApplyCaseOwnerFilter() {
    updatePortalSetting("ENABLE_CASE_OWNER", "true");
    redirectToRelativeLink(userIsOwnerUrl);
    homePage = new HomePage();
    mainMenuPage = homePage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.filterByOwner("Demo");
    taskAnalysisWidgetPage.clickApplyFilter();

    List<WebElement> results = taskAnalysisWidgetPage.getRowsInTaskTable();
    assertEquals(2, results.size());
    updatePortalSetting("ENABLE_CASE_OWNER", "false");
  }
  
  @Test
  public void testAddCaseOwnerColumn() {
    updatePortalSetting("ENABLE_CASE_OWNER", "true");
    homePage = new HomePage();
    mainMenuPage = homePage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();

    WebElement toggler = taskAnalysisWidgetPage.findColumnToggler();
    taskAnalysisWidgetPage.click(toggler);
    taskAnalysisWidgetPage.waitAjaxIndicatorDisappear();

    WebElement columnContainer = taskAnalysisWidgetPage.getDriver().findElement(By.tagName("body"))
        .findElement(By.cssSelector(".ui-columntoggler"));
    List<WebElement> checkboxes = columnContainer.findElements(By.cssSelector("li.ui-columntoggler-item"));

    checkboxes.forEach(elem -> {
      if (StringUtils.equals(elem.getText(), "Case Owner")) {
        elem.findElement(By.className("ui-chkbox")).click();
      }
    });
    taskAnalysisWidgetPage.isElementDisplayed(By.id("task-widget:statistic-result-form:task-table:case-owner"));
    updatePortalSetting("ENABLE_CASE_OWNER", "false");
  }

  @Test
  public void testSavePublicFilterSet() {
    String filterSetName = "Filters for annual";

    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    addFilters(taskAnalysisWidgetPage);
    taskAnalysisWidgetPage.saveFilterSet(filterSetName, false);

    statisticWidgetPage = taskAnalysisWidgetPage.navigateToStatisticPage();
    final TaskAnalysisWidgetPage secondTaskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    secondTaskAnalysisWidgetPage.loadFilterSet(filterSetName, false);
    secondTaskAnalysisWidgetPage.waitForTaskDataChangeToSpecificSize(1);
    List<WebElement> resultCells = secondTaskAnalysisWidgetPage.getRowsInTaskTable().get(0).findElements(By.cssSelector("td:not([class='ui-helper-hidden'])"));
    assertTrue(resultCells.get(0).getText().toLowerCase().contains("request"));
    assertTrue(resultCells.get(1).getText().equals("RUNNING"));
    assertTrue(resultCells.get(2).getText().toLowerCase().contains("annual"));
  }

  @Test
  public void testSavePrivateFilterSet() {
    String filterSetName = "Filters for annual";

    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    addFilters(taskAnalysisWidgetPage);
    taskAnalysisWidgetPage.saveFilterSet(filterSetName, true);

    statisticWidgetPage = taskAnalysisWidgetPage.navigateToStatisticPage();
    TaskAnalysisWidgetPage secondTaskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    secondTaskAnalysisWidgetPage.loadFilterSet(filterSetName, true);
    secondTaskAnalysisWidgetPage.waitForTaskDataChangeToSpecificSize(1);
    
    List<WebElement> resultCells = secondTaskAnalysisWidgetPage.getRowsInTaskTable().get(0).findElements(By.cssSelector("td:not([class='ui-helper-hidden'])"));
    assertTrue(resultCells.get(0).getText().toLowerCase().contains("request"));
    assertTrue(resultCells.get(1).getText().equals("RUNNING"));
    assertTrue(resultCells.get(2).getText().toLowerCase().contains("annual"));
  }

  private void createTestData() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
  }

  private void addFilters(TaskAnalysisWidgetPage taskAnalysisWidgetPage) {
    String taskNameKeyword = "annual";
    String taskCategory = "Annual Leave";
    String caseNameKeyword = "request";
    String caseCategory = "Leave Request";
    String caseState = "In progress";

    taskAnalysisWidgetPage.openAdvancedCaseFilter("State", "state");
    taskAnalysisWidgetPage.openAdvancedTaskFilter("Name", "name");
    taskAnalysisWidgetPage.filterByTaskName(taskNameKeyword);

    taskAnalysisWidgetPage.openAdvancedTaskFilter("Task category", "task-category");
    taskAnalysisWidgetPage.filterByTaskCategory(taskCategory);

    taskAnalysisWidgetPage.openAdvancedCaseFilter("Name", "case-name");
    taskAnalysisWidgetPage.filterByCaseName(caseNameKeyword);

    taskAnalysisWidgetPage.openAdvancedCaseFilter("Case category", "case-category");
    taskAnalysisWidgetPage.filterByCaseCategory(caseCategory);

    List<String> selectedStates = new ArrayList<>();
    selectedStates.add(caseState);
    taskAnalysisWidgetPage.filterByCaseState(selectedStates);
  }
  
  @Test
  public void testDefaultAndResetFilter() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    assertTrue(taskAnalysisWidgetPage.getFilterName().contains("Default filter"));
    
    taskAnalysisWidgetPage.openAdvancedTaskFilter("Name", "name");
    taskAnalysisWidgetPage.filterByTaskName("request");
    assertTrue(taskAnalysisWidgetPage.isResetButtonShown());
    
    taskAnalysisWidgetPage.resetFilter();
    assertTrue(taskAnalysisWidgetPage.getFilterName().contains("Default filter"));
  }
  
  @Test
  public void testRemoveAndReloadFilter() {
    //  Task Responsible, Case Creator 
    String filterSetName = "saveUserFilter";
    
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.filterByResponsible("Demo","Responsible","responsible");
    taskAnalysisWidgetPage.filterUserInCase("Demo","Creator","creator");
    
    taskAnalysisWidgetPage.saveFilterSet(filterSetName, false);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.loadFilterSet(filterSetName, false);
    
    taskAnalysisWidgetPage.removeResponsible();
    taskAnalysisWidgetPage.removeUserInFilter();
    
    mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.loadFilterSet(filterSetName, false);
    
    assertTrue(taskAnalysisWidgetPage.getUser("responsible").contains("Demo"));
    assertTrue(taskAnalysisWidgetPage.getUser("creator").contains("Demo"));
  }
}
