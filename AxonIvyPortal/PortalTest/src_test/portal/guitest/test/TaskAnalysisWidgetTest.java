package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
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
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    LoginPage loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    grantPermissionOfPortal();
    resetLanguageOfCurrentUser();
    homePage = new HomePage();
    mainMenuPage = homePage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.waitForPageLoaded();
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
    toggler.click();

    WebElement columnContainer = taskAnalysisWidgetPage.getDriver().findElement(By.tagName("body"))
        .findElement(By.cssSelector(".ui-columntoggler"));
    int numberOfCheckboxes = columnContainer.findElements(By.cssSelector(".ui-chkbox-box")).size();
    List<WebElement> unselectedColumnCheckboxes =
        columnContainer.findElements(By.cssSelector(".ui-chkbox-box:not(.ui-state-active)"));

    unselectedColumnCheckboxes.forEach(elem -> elem.click());
    taskAnalysisWidgetPage.waitForPageLoaded();

    int numberOfColumns = taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_head")
        .findElements(By.cssSelector("th[scope='col']:not(.ui-helper-hidden)")).size();
    assertEquals(numberOfColumns, numberOfCheckboxes);
  }

  @Test
  public void testRemoveColumns() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();

    WebElement toggler = taskAnalysisWidgetPage.findColumnToggler();
    toggler.click();

    WebElement columnContainer = taskAnalysisWidgetPage.getDriver().findElement(By.tagName("body"))
        .findElement(By.cssSelector(".ui-columntoggler"));
    List<WebElement> selectedColumnCheckboxes =
        columnContainer.findElements(By.cssSelector(".ui-chkbox-box.ui-state-active"));

    selectedColumnCheckboxes.get(0).click();
    selectedColumnCheckboxes.get(1).click();
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

    WebElement applyFilterButton = taskAnalysisWidgetPage.findApplyFilterButton();
    applyFilterButton.click();
    taskAnalysisWidgetPage.waitAjaxIndicatorDisappear();

    List<WebElement> results =
        taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_data")
            .findElements(By.cssSelector("tr[role='row']"));

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
    taskAnalysisWidgetPage.openAdvancedCaseFilter("Name", "case-name");
    taskAnalysisWidgetPage.filterByCaseName(keyword);

    taskAnalysisWidgetPage.openAdvancedCaseFilter("State", "state");
    List<String> selectedStates = new ArrayList<>();
    selectedStates.add(stateSeletion);
    taskAnalysisWidgetPage.filterByCaseState(selectedStates);

    WebElement applyFilterButton = taskAnalysisWidgetPage.findApplyFilterButton();
    applyFilterButton.click();
    taskAnalysisWidgetPage.waitAjaxIndicatorDisappear();

    List<WebElement> results =
        taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_data")
            .findElements(By.cssSelector("tr[role='row']"));

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

    WebElement applyFilterButton = taskAnalysisWidgetPage.findApplyFilterButton();
    applyFilterButton.click();
    taskAnalysisWidgetPage.waitAjaxIndicatorDisappear();

    List<WebElement> results =
        taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_data")
            .findElements(By.cssSelector("tr[role='row']"));

    assertEquals(2, results.size());
  }

  @Test
  public void testApplyCaseCategoryFilter() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.openAdvancedCaseFilter("Case category", "case-category");
    taskAnalysisWidgetPage.filterByCaseCategory("Leave Request");

    WebElement applyFilterButton = taskAnalysisWidgetPage.findApplyFilterButton();
    applyFilterButton.click();
    taskAnalysisWidgetPage.waitAjaxIndicatorDisappear();

    List<WebElement> results =
        taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_data")
            .findElements(By.cssSelector("tr[role='row']"));

    assertEquals(4, results.size());
  }

  @Test
  public void testSavePublicFilterSet() {
    String filterSetName = "Filters for annual leaves";

    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    addFilters(taskAnalysisWidgetPage);
    taskAnalysisWidgetPage.saveFilterSet(filterSetName, false);

    statisticWidgetPage = taskAnalysisWidgetPage.navigateToStatisticPage();
    taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.loadFilterSet(filterSetName, false);

    List<WebElement> results =
        taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_data")
            .findElements(By.cssSelector("tr[role='row']"));

    assertEquals(1, results.size());

    List<WebElement> resultCells = results.get(0).findElements(By.cssSelector("td:not([class='ui-helper-hidden'])"));
    assertTrue(resultCells.get(0).getText().toLowerCase().contains("request"));
    assertTrue(resultCells.get(1).getText().equals("RUNNING"));
    assertTrue(resultCells.get(2).getText().toLowerCase().contains("annual"));
  }

  @Test
  public void testSavePrivateFilterSet() {
    String filterSetName = "Filters for annual leaves";

    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    addFilters(taskAnalysisWidgetPage);
    taskAnalysisWidgetPage.saveFilterSet(filterSetName, true);

    statisticWidgetPage = taskAnalysisWidgetPage.navigateToStatisticPage();
    taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.loadFilterSet(filterSetName, true);

    List<WebElement> results =
        taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_data")
            .findElements(By.cssSelector("tr[role='row']"));

    assertEquals(1, results.size());

    List<WebElement> resultCells = results.get(0).findElements(By.cssSelector("td:not([class='ui-helper-hidden'])"));
    assertTrue(resultCells.get(0).getText().toLowerCase().contains("request"));
    assertTrue(resultCells.get(1).getText().equals("RUNNING"));
    assertTrue(resultCells.get(2).getText().toLowerCase().contains("annual"));
  }

  private void createTestData() {
    redirectToRelativeLink("internalSupport/15C7B30FB93C827E/create12CasesWithCategory.ivp");
  }

  private void addFilters(TaskAnalysisWidgetPage taskAnalysisWidgetPage) {
    String taskNameKeyword = "annual";
    String taskCategory = "Annual Leave";
    String caseNameKeyword = "request";
    String caseCategory = "LeaveRequest";
    String caseState = "In progress";

    taskAnalysisWidgetPage.openAdvancedTaskFilter("Name", "name");
    taskAnalysisWidgetPage.filterByTaskName(taskNameKeyword);

    taskAnalysisWidgetPage.openAdvancedTaskFilter("Task category", "task-category");
    taskAnalysisWidgetPage.filterByTaskCategory(taskCategory);

    taskAnalysisWidgetPage.openAdvancedCaseFilter("Name", "case-name");
    taskAnalysisWidgetPage.filterByCaseName(caseNameKeyword);

    taskAnalysisWidgetPage.openAdvancedCaseFilter("Case category", "case-category");
    taskAnalysisWidgetPage.filterByCaseCategory(caseCategory);

    taskAnalysisWidgetPage.openAdvancedCaseFilter("State", "state");
    List<String> selectedStates = new ArrayList<>();
    selectedStates.add(caseState);
    taskAnalysisWidgetPage.filterByCaseState(selectedStates);
  }
}
