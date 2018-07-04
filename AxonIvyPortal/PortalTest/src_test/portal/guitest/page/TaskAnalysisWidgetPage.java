package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;


public class TaskAnalysisWidgetPage extends TemplatePage {

  private static final String WIDGET_ID = "task-widget";

  private String taskAnalysisWidgetId;

  public TaskAnalysisWidgetPage() {
    this(WIDGET_ID);
  }
  
  public TaskAnalysisWidgetPage(String taskAnalysisWidgetId) {
    this.setTaskAnalysisWidgetId(taskAnalysisWidgetId);
  }
  
  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'" + WIDGET_ID + ":statistic-result-list')]";
  }

  public String getTaskAnalysisWidgetId() {
    return taskAnalysisWidgetId;
  }

  public void setTaskAnalysisWidgetId(String taskAnalysisWidgetId) {
    this.taskAnalysisWidgetId = taskAnalysisWidgetId;
  }

  public StatisticWidgetPage navigateToStatisticPage() {
    String backButtonId = "task-widget:back-button";
    waitForElementDisplayed(By.id(backButtonId), true, DEFAULT_TIMEOUT);
    WebElement backButton = findElementById(backButtonId);

    backButton.click();
    waitForElementDisplayed(By.id("statistics-widget"), true, DEFAULT_TIMEOUT);
    return new StatisticWidgetPage();
  }

  public WebElement findColumnToggler() {
    String togglerId = "task-widget:statistic-result-form:toggler";
    waitForElementDisplayed(By.id(togglerId), true);
    return findElementById(togglerId);
  }

  public WebElement findTaskFilterButton() {
    String taskFilterButtonId = "task-widget:task-filter-add-action";
    waitForElementDisplayed(By.id(taskFilterButtonId), true);
    return findElementById(taskFilterButtonId);
  }

  public WebElement findCaseFilterButton() {
    String caseFilterButtonId = "task-widget:case-filter-add-action";
    waitForElementDisplayed(By.id(caseFilterButtonId), true);
    return findElementById(caseFilterButtonId);
  }

  public WebElement findApplyFilterButton() {
    String applyFilterButtonId = "task-widget:apply-filter";
    waitForElementDisplayed(By.id(applyFilterButtonId), true);
    return findElementById(applyFilterButtonId);
  }

  public void openAdvancedTaskFilter(String filterName, String filterIdName) {
    findTaskFilterButton().click();
    WebElement filterSelectionElement = findElementById("task-widget:task-filter-add-form:task-filter-selection");
    for (WebElement filterElement : findChildElementsByTagName(filterSelectionElement, "LABEL")) {
      if (filterName.equals(filterElement.getText())) {
        filterElement.click();
        break;
      }
    }

    waitForElementDisplayed(
        By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"), true);
  }

  public void openAdvancedCaseFilter(String filterName, String filterIdName) {
    findCaseFilterButton().click();
    WebElement filterSelectionElement = findElementById("task-widget:case-filter-add-form:case-filter-selection");
    for (WebElement filterElement : findChildElementsByTagName(filterSelectionElement, "LABEL")) {
      if (filterName.equals(filterElement.getText())) {
        filterElement.click();
        break;
      }
    }

    waitForElementDisplayed(
        By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"), true);
  }

  public void filterByTaskName(String text) {
    click(By.cssSelector("button[id$='task-name-filter:filter-open-form:advanced-filter-command']"));
    WebElement nameInput =
        findElementByCssSelector("input[id$='task-name-filter:filter-input-form:name']");
    enterKeys(nameInput, text);
    click(By.cssSelector("button[id$='task-name-filter:filter-input-form:update-command']"));
    waitForPageLoaded();
  }

  public void filterByTaskPriority(List<String> selectedPriorities) {
    click(By.cssSelector("button[id$='priority-filter:filter-open-form:advanced-filter-command']"));
    WebElement selectionElement = findElementByCssSelector("[id$='priority-filter:filter-input-form:advanced-filter-panel-content']");
    for (WebElement labelElement : findChildElementsByTagName(selectionElement, "LABEL")) {
      if (!selectedPriorities.contains(labelElement.getText())) {
        labelElement.click();
      }
    }
    click(By.cssSelector("button[id$='priority-filter:filter-input-form:update-command']"));
    waitForPageLoaded();
  }

  public void filterByTaskCategory(String selectedCategory) {
    click(By.cssSelector("button[id$='task-category-filter:filter-open-form:advanced-filter-command']"));
    WebElement selectionElement = findElementByCssSelector("[id$='task-category-filter:filter-input-form:advanced-filter-panel-content']");
    for (WebElement labelElement : findChildElementsByClassName(selectionElement, "ui-treenode-label")) {
      if (selectedCategory.contains(labelElement.getText())) {
        labelElement.click();
        break;
      }
    }
    click(By.cssSelector("button[id$='task-category-filter:filter-input-form:update-command']"));
    waitForPageLoaded();
  }

  public void filterByCaseName(String text) {
    click(By.cssSelector("button[id$='case-name-filter:filter-open-form:advanced-filter-command']"));
    WebElement nameInput =
        findElementByCssSelector("input[id$='case-name-filter:filter-input-form:name']");
    enterKeys(nameInput, text);
    click(By.cssSelector("button[id$='case-name-filter:filter-input-form:update-command']"));
    waitForPageLoaded();
  }

  public void filterByCaseState(List<String> selectedPriorities) {
    click(By.cssSelector("button[id$='state-filter:filter-open-form:advanced-filter-command']"));
    WebElement selectionElement = findElementByCssSelector("[id$='state-filter:filter-input-form:advanced-filter-panel-content']");
    for (WebElement labelElement : findChildElementsByTagName(selectionElement, "LABEL")) {
      if (!selectedPriorities.contains(labelElement.getText())) {
        labelElement.click();
      }
    }
    click(By.cssSelector("button[id$='state-filter:filter-input-form:update-command']"));
    waitForPageLoaded();
  }

  public void filterByCaseCategory(String selectedCategory) {
    click(By.cssSelector("button[id$='case-category-filter:filter-open-form:advanced-filter-command']"));
    WebElement selectionElement = findElementByCssSelector("[id$='case-category-filter:filter-input-form:advanced-filter-panel-content']");
    for (WebElement labelElement : findChildElementsByClassName(selectionElement, "ui-treenode-label")) {
      if (selectedCategory.contains(labelElement.getText())) {
        labelElement.click();
        break;
      }
    }
    click(By.cssSelector("button[id$='case-category-filter:filter-input-form:update-command']"));
    waitForPageLoaded();
  }

  public void saveFilterSet(String filterSetName, boolean isPersonalFilter) {
    click(By.id("task-widget:filter-save-action"));
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.id("task-widget:filter-save-form:save-filter-set-name-input"), true);
    enterKeys(findElementById("task-widget:filter-save-form:save-filter-set-name-input"), filterSetName);
    Sleeper.sleepTight(1000);

    WebElement filterVisibilityContainer = findElementById("task-widget:filter-save-form:save-filter-type-radio");
    if (isPersonalFilter) {
      filterVisibilityContainer.findElements(By.tagName("LABEL")).get(0).click();
    } else {
      filterVisibilityContainer.findElements(By.tagName("LABEL")).get(1).click();
    }

    click(By.id("task-widget:filter-save-form:filter-save-command"));
    waitAjaxIndicatorDisappear();
  }

  public void loadFilterSet(String filterSetName, boolean isPersonalFilter) {
    waitForElementDisplayed(By.id("task-widget:filter-selection-form:filter-name"), true);
    click(By.id("task-widget:filter-selection-form:filter-name"));
    WebElement filterContainer = null;
    if (isPersonalFilter) {
      filterContainer = findElementById("task-widget:filter-selection-form:private-filters");
    } else {
      filterContainer = findElementById("task-widget:filter-selection-form:public-filters");
    }
    filterContainer.findElement(By.linkText(filterSetName)).click();
    Sleeper.sleepTight(1000);
  }
}