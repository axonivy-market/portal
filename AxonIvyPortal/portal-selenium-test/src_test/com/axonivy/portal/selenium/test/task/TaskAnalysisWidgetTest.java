package com.axonivy.portal.selenium.test.task;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.StatisticWidgetPage;
import com.axonivy.portal.selenium.page.TaskAnalysisWidgetPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest
public class TaskAnalysisWidgetTest extends BaseTest {

  private static final String ENABLE_CASE_OWNER_SETTING = Variable.ENABLE_CASE_OWNER.getKey();
  private NewDashboardPage newDashboardPage;
  private StatisticWidgetPage statisticWidgetPage;
  private MainMenuPage mainMenuPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestingTasks();
    createTestData();
    login(TestAccount.ADMIN_USER);
    grantPermissionOfPortal();
    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
  }

  private void grantPermissionOfPortal() {
    redirectToRelativeLink(grantPortalPermission);
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

    taskAnalysisWidgetPage.clickOnColumnToggler();

    SelenideElement columnContainer = taskAnalysisWidgetPage.findColumnContainer();
    int numberOfCheckboxes = columnContainer.findElements(By.cssSelector(".ui-chkbox-box")).size();
    List<SelenideElement> unselectedColumnCheckboxes =
        columnContainer.$$(By.cssSelector(".ui-chkbox-box:not(.ui-state-active)"));

    unselectedColumnCheckboxes.forEach(elem -> elem.click());
    taskAnalysisWidgetPage.waitPageLoaded();

    int numberOfColumns = taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_head")
        .findElements(By.cssSelector("th[scope='col']:not(.ui-helper-hidden)")).size();
    assertEquals(numberOfColumns, numberOfCheckboxes);
  }

  @Test
  public void testRemoveColumns() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();

    taskAnalysisWidgetPage.clickOnColumnToggler();

    SelenideElement columnContainer = taskAnalysisWidgetPage.findColumnContainer();
    List<SelenideElement> selectedColumnCheckboxes =
        columnContainer.$$(By.cssSelector(".ui-chkbox-box.ui-state-active"));
    selectedColumnCheckboxes.get(0).shouldBe(Condition.and("should be clickable", visible, exist), DEFAULT_TIMEOUT)
        .click();
    selectedColumnCheckboxes.get(1).shouldBe(Condition.and("should be clickable", visible, exist), DEFAULT_TIMEOUT)
        .click();

    taskAnalysisWidgetPage.waitPageLoaded();

    int numberOfColumns = taskAnalysisWidgetPage.findElementById("task-widget:statistic-result-form:task-table_head")
        .$$(By.cssSelector("th[scope='col']:not(.ui-helper-hidden)"))
        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(0)).size();

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

    ElementsCollection results = taskAnalysisWidgetPage.getRowsInTaskTable();
    results.shouldHave(CollectionCondition.size(2), DEFAULT_TIMEOUT);

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

    ElementsCollection results = taskAnalysisWidgetPage.getRowsInTaskTable();
    results.shouldHave(CollectionCondition.size(4), DEFAULT_TIMEOUT);

    for (WebElement resultRow : results) {
      List<WebElement> resultCells = resultRow.findElements(By.cssSelector("td:not([class='ui-helper-hidden'])"));
      assertTrue(resultCells.get(0).getText().toLowerCase().contains(keyword));
      assertTrue(resultCells.get(1).getText().equals("RUNNING"));
    }
  }

  @Test
  public void testApplyTaskCategoryFilter() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.openAdvancedTaskFilter("Category", "task-category");
    taskAnalysisWidgetPage.filterByTaskCategory("Other Leave");
    taskAnalysisWidgetPage.clickApplyFilter();
    ElementsCollection results = taskAnalysisWidgetPage.getRowsInTaskTable();
    results.shouldHave(CollectionCondition.size(2), DEFAULT_TIMEOUT);
  }

  @Test
  public void testApplyCaseCategoryFilter() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.openAdvancedCaseFilter("Case category", "case-category");
    taskAnalysisWidgetPage.filterByCaseCategory("Leave Request");
    taskAnalysisWidgetPage.clickApplyFilter();

    ElementsCollection results = taskAnalysisWidgetPage.getRowsInTaskTable();
    results.shouldHave(CollectionCondition.size(4), DEFAULT_TIMEOUT);
  }

  @Test
  public void testApplyCaseOwnerFilter() {
    updatePortalSetting(ENABLE_CASE_OWNER_SETTING, "true");
    redirectToRelativeLink(userIsOwnerUrl);
    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.filterByOwner("Demo");
    taskAnalysisWidgetPage.clickApplyFilter();

    assertEquals(2, taskAnalysisWidgetPage.getNumberOfRowsInTaskTable());
    updatePortalSetting(ENABLE_CASE_OWNER_SETTING, "false");
  }

  @Test
  public void testAddCaseOwnerColumn() {
    updatePortalSetting(ENABLE_CASE_OWNER_SETTING, "true");
    newDashboardPage = new NewDashboardPage();
    mainMenuPage = newDashboardPage.openMainMenu();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();

    taskAnalysisWidgetPage.clickOnColumnToggler();

    WebElement columnContainer = taskAnalysisWidgetPage.getDriver().findElement(By.tagName("body"))
        .findElement(By.cssSelector(".ui-columntoggler"));
    List<WebElement> checkboxes = columnContainer.findElements(By.cssSelector("li.ui-columntoggler-item"));

    checkboxes.forEach(elem -> {
      if (StringUtils.equals(elem.getText(), "Case Owner")) {
        elem.findElement(By.className("ui-chkbox")).click();
      }
    });
    updatePortalSetting(ENABLE_CASE_OWNER_SETTING, "false");
  }

  @Test
  public void testSavePublicFilterSet() {
    String filterSetName = "Filters for annual";

    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    resizeBrowserTo2kResolution();
    addFilters(taskAnalysisWidgetPage);
    taskAnalysisWidgetPage.saveFilterSet(filterSetName, false);
    taskAnalysisWidgetPage.countSavedFilter(2);
    refreshPage();
    taskAnalysisWidgetPage.loadFilterSet(filterSetName, false);
    taskAnalysisWidgetPage.waitForTaskDataChangeToSpecificSize(1);
    List<WebElement> resultCells = taskAnalysisWidgetPage.getRowsInTaskTable().get(0)
        .findElements(By.cssSelector("td:not([class='ui-helper-hidden'])"));
    assertTrue(resultCells.get(0).getText().toLowerCase().contains("request"));
    assertTrue(resultCells.get(1).getText().equals("RUNNING"));
    assertTrue(resultCells.get(2).getText().toLowerCase().contains("annual"));
  }

  @Test
  public void testSavePrivateFilterSet() {
    String filterSetName = "Filters for annual";
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    resizeBrowserTo2kResolution();
    addFilters(taskAnalysisWidgetPage);
    taskAnalysisWidgetPage.saveFilterSet(filterSetName, true);
    taskAnalysisWidgetPage.countSavedFilter(2);
    refreshPage();
    taskAnalysisWidgetPage.loadFilterSet(filterSetName, true);
    taskAnalysisWidgetPage.waitForTaskDataChangeToSpecificSize(1);
    List<WebElement> resultCells = taskAnalysisWidgetPage.getRowsInTaskTable().get(0)
        .findElements(By.cssSelector("td:not([class='ui-helper-hidden'])"));
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

    taskAnalysisWidgetPage.openAdvancedTaskFilter("Category", "task-category");
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
    // Task Responsible, Case Creator
    String filterSetName = "saveUserFilter";

    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.filterByResponsible("Demo", "Responsible", "responsible");
    taskAnalysisWidgetPage.filterUserInCase("Demo", "Creator", "creator");
    resizeBrowserTo2kResolution();
    taskAnalysisWidgetPage.saveFilterSet(filterSetName, false);
    taskAnalysisWidgetPage.countSavedFilter(2);

    MainMenuPage mainMenuPage = new MainMenuPage();
    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.loadFilterSet(filterSetName, false);

    taskAnalysisWidgetPage.removeResponsible();
    taskAnalysisWidgetPage.removeUserInFilter();

    statisticWidgetPage = mainMenuPage.selectStatisticDashboard();
    statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.loadFilterSet(filterSetName, false);

    assertTrue(taskAnalysisWidgetPage.getUser("responsible").contains("Demo"));
    assertTrue(taskAnalysisWidgetPage.getUser("creator").contains("Demo"));
  }

  @Test
  public void testTaskFilterForUnavailableActivator() {
    TaskAnalysisWidgetPage taskAnalysisWidgetPage = statisticWidgetPage.navigateToTaskAnalysisPage();
    taskAnalysisWidgetPage.openNoActivatorFilter("Missing activator");
    taskAnalysisWidgetPage.filterByUnavailableActivator();
    taskAnalysisWidgetPage.clickApplyFilter();
    ElementsCollection results = taskAnalysisWidgetPage.getRowsInTaskTable();
    results.shouldHave(CollectionCondition.size(2), DEFAULT_TIMEOUT);
  }
}
