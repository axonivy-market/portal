package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TaskAnalysisWidgetPage extends TemplatePage {

  private static final String TASK_FILTER_SELECTION = "task-widget:task-filter-add-form:task-filter-selection";

  @Override
  protected String getLoadedLocator() {
    return "div[id='task-widget']";
  }

  public void openAdvancedTaskFilter(String filterName, String filterIdName) {
    findTaskFilterButton().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='" + TASK_FILTER_SELECTION + "']").shouldBe(appear, DEFAULT_TIMEOUT).$$("label").asFixedIterable().stream()
        .filter(filter -> filter.getText().contentEquals(filterName)).findFirst().get()
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='task-widget:task-filter-add-form:update-task-filter-selected-command']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']").shouldBe(appear,
        DEFAULT_TIMEOUT);
  }

  public void filterByTaskPriority(List<String> selectedPriorities) {
    $("[id$='priority-filter:filter-open-form:advanced-filter-command']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='priority-filter:filter-input-form:advanced-filter-panel-content']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("label").asFixedIterable().stream().filter(filter -> !selectedPriorities.contains(filter.getText()))
        .forEach(filter -> filter.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());

    $("[id$='priority-filter:filter-input-form:update-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
  }

  public void clickApplyFilter() {
    $("button[id$='task-widget:apply-filter']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  private SelenideElement findTaskFilterButton() {
    return $("[id='task-widget:task-filter-add-action']");
  }

  public void enterDataToSaveFilterSet(String filterSetName, boolean isPersonalFilter) {
    $("[id='task-widget:filter-save-action']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='task-widget:filter-save-form:save-filter-set-name-input']").shouldBe(appear, DEFAULT_TIMEOUT)
        .sendKeys(filterSetName);

    SelenideElement filterVisibilityContainer = findElementById("task-widget:filter-save-form:save-filter-type-radio");
    if (isPersonalFilter) {
      filterVisibilityContainer.$$(By.tagName("LABEL")).get(0).shouldBe(clickable(), DEFAULT_TIMEOUT).click();
    } else {
      filterVisibilityContainer.$$(By.tagName("LABEL")).get(1).shouldBe(clickable(), DEFAULT_TIMEOUT).click();
    }
  }

  public WebElement waitAndGetSavingFilterDialog() {
    return $("div[id$='save-filter-set-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public StatisticWidgetPage navigateToStatisticPage() {
    String backButtonId = "task-widget:back-button";
    waitForElementDisplayed(By.id(backButtonId), true);
    SelenideElement backButton = findElementById(backButtonId);

    clickByJavaScript(backButton);
    waitForElementDisplayed(By.id("statistics-widget"), true);
    return new StatisticWidgetPage();
  }

  public void clickOnColumnToggler() {
    String togglerId = "task-widget:statistic-result-form:toggler";
    waitForElementDisplayed(By.id(togglerId), true);
    clickByJavaScript(findElementById(togglerId));
  }

  public void filterByTaskName(String text) {
    waitForElementClickableThenClick($("[id$='task-name-filter:filter-open-form:advanced-filter-command']"));
    $("input[id$='task-name-filter:filter-input-form:name']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(text);
    waitForElementClickableThenClick($("[id$='task-name-filter:filter-input-form:update-command']"));
  }
  
  public void waitForRowDisplayed() {
    waitForElementDisplayed(By.cssSelector("tr[role='row']"), true);
  }

  public ElementsCollection getRowsInTaskTable() {
    return findElementById("task-widget:statistic-result-form:task-table_data").$$(By.cssSelector("tr[role='row']"));
  }
  
  public int getNumberOfRowsInTaskTable() {
    List<SelenideElement> elements = findElementById("task-widget:statistic-result-form:task-table_data").$$(By.cssSelector("tr[role='row']"));
    return elements.size();
  }


  private SelenideElement findCaseFilterButton() {
    String caseFilterButtonId = "task-widget:case-filter-add-action";
    waitForElementDisplayed(By.id(caseFilterButtonId), true);
    return findElementById(caseFilterButtonId);
  }

  public void openAdvancedCaseFilter(String filterName, String filterIdName) {
    waitForElementClickableThenClick(findCaseFilterButton());
    waitForElementDisplayed(findElementById("task-widget:case-filter-add-form:case-filter-selection"), true);
    SelenideElement filterSelectionElement = findElementById("task-widget:case-filter-add-form:case-filter-selection");
    for (SelenideElement filterElement : filterSelectionElement.$$(By.tagName("LABEL"))) {
      if (filterName.equals(filterElement.getText())) {
        waitForElementClickableThenClick(filterElement);
        waitForElementClickableThenClick(
            $("button[id$='task-widget:case-filter-add-form:update-task-filter-selected-command']"));
        break;
      }
    }
    waitForElementDisplayed(
        By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"),
        true);
  }

  public void filterByCaseName(String text) {
    waitForElementClickableThenClick($("[id$='case-name-filter:filter-open-form:advanced-filter-command']"));
    findElementByCssSelector("input[id$='case-name-filter:filter-input-form:name']").shouldBe(appear, DEFAULT_TIMEOUT)
        .sendKeys(text);
    waitForElementClickableThenClick($("[id$='case-name-filter:filter-input-form:update-command']"));
  }

  public void filterByCaseState(List<String> selectedPriorities) {
    waitForElementClickableThenClick($(
        "div[id$='task-widget:case-filters-container'] button[id$='state-filter:filter-open-form:advanced-filter-command']"));
    SelenideElement selectionElement = findElementByCssSelector(
        "div[id$='task-widget:case-filters-container'] div[id$='state-filter:filter-input-form:advanced-filter-panel-content']");
    for (SelenideElement labelElement : selectionElement.$$(By.tagName("LABEL"))) {
      if (!selectedPriorities.contains(labelElement.getText())) {
        waitForElementClickableThenClick(labelElement);
      }
    }
    waitForElementClickableThenClick(
        $("div[id$='task-widget:case-filters-container'] button[id$='state-filter:filter-input-form:update-command']"));
  }

  public void filterByTaskCategory(String selectedCategory) {
    waitForElementClickableThenClick($("[id$='task-category-filter:filter-open-form:advanced-filter-command']"));
    waitForElementDisplayed($("div[id$=':task-category-filter-tree']"), true);
    SelenideElement selectionElement =
        findElementByCssSelector("[id$='task-category-filter:filter-input-form:advanced-filter-panel-content']");
    List<SelenideElement> categoryTreeLabels = selectionElement.$$(By.className("ui-treenode-label"));
    // Find parent node of tree first and uncheck it
    SelenideElement parentNodeOfTree = categoryTreeLabels.stream().findFirst().get();
    waitForElementDisplayed(parentNodeOfTree, true);
    waitForElementClickableThenClick(parentNodeOfTree);
    for (SelenideElement labelElement : categoryTreeLabels) {
      if (selectedCategory.contains(labelElement.getText())) {
        waitForElementClickableThenClick(labelElement);
        break;
      }
    }
    waitForElementClickableThenClick($("[id$='task-category-filter:filter-input-form:update-command']"));
  }

  public void filterByCaseCategory(String selectedCategory) {
    waitForElementClickableThenClick($("[id$='case-category-filter:filter-open-form:advanced-filter-command']"));
    waitForElementDisplayed($("div[id$=':case-category-filter-tree']"), true);
    SelenideElement selectionElement =
        findElementByCssSelector("[id$='case-category-filter:filter-input-form:advanced-filter-panel-content']");
    List<SelenideElement> categoryTreeLabels = selectionElement.$$(By.className("ui-treenode-label"));
    // Find parent node of tree first and uncheck it
    SelenideElement parentNodeOfTree = categoryTreeLabels.stream().findFirst().get();
    waitForElementDisplayed(parentNodeOfTree, true);
    waitForElementClickableThenClick(parentNodeOfTree);
    for (SelenideElement labelElement : categoryTreeLabels) {
      if (selectedCategory.contains(labelElement.getText())) {
        waitForElementClickableThenClick(labelElement);
        break;
      }
    }
    waitForElementClickableThenClick($("button[id$='case-category-filter:filter-input-form:update-command']"));
  }

  public void filterByOwner(String user) {
    openAdvancedCaseFilter("Owner", "owner");
    waitForElementClickableThenClick($("button[id$='owner-filter:filter-open-form:advanced-filter-command']"));
    findElementByCssSelector("input[id$='owner-filter:filter-input-form:owner_input']").sendKeys(user);
    waitForElementDisplayed($("span[id$='owner-filter:filter-input-form:owner_panel']"), true);
    waitForElementDisplayed($("div[id*='owner-filter'] .ui-avatar-text"), true);
    waitForElementClickableThenClick($("[id$=':owner-filter:filter-input-form:owner_panel']"));
    waitForElementClickableThenClick($("button[id$='owner-filter:filter-input-form:update-command']"));
  }

  public void saveFilterSet(String filterSetName, boolean isPersonalFilter) {
    enterDataToSaveFilterSet(filterSetName, isPersonalFilter);
    SelenideElement saveButton =
        $("button[id$='task-widget:filter-save-form:filter-save-command']").shouldBe(appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(saveButton);
    $("[id='task-widget:save-filter-set-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    saveButton.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void waitForTaskDataChangeToSpecificSize(int size) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until((driver) -> {
      return getRowsInTaskTable().size() == size;
    });
  }

  public void loadFilterSet(String filterSetName, boolean isPersonalFilter) {
    waitForElementDisplayed($("a[id$='task-widget:filter-selection-form:filter-name']"), true);
    waitForElementClickableThenClick("a[id$='task-widget:filter-selection-form:filter-name']");
    waitForElementDisplayed(findElementById("task-widget:filter-selection-form:filter-name-overlay-panel"), true);
    SelenideElement filterContainer = null;
    if (isPersonalFilter) {
      filterContainer = findElementById("task-widget:filter-selection-form:private-filters");
    } else {
      filterContainer = findElementById("task-widget:filter-selection-form:public-filters");
    }
    clickByJavaScript(filterContainer.$(By.linkText(filterSetName)).shouldBe(appear, DEFAULT_TIMEOUT));
  }

  public String getFilterName() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until((driver) ->

    $("a[id$='task-widget:filter-selection-form:filter-name'] > span:nth-child(2)").getText().length() > 1);
    WebElement filterName =
        findElementByCssSelector("a[id$='task-widget:filter-selection-form:filter-name'] > span:nth-child(2)");
    return filterName.getText();
  }

  private void filterByUserName(String user, String filterIdName) {
    waitForElementClickableThenClick(
        $("button[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-command']"));
    $("input[id*='" + filterIdName + "-filter:filter-input-form:']").sendKeys(user);
    waitForElementExisted(By.cssSelector("div[id*='" + filterIdName + "-filter'] .ui-avatar-text"), true);
    waitForElementClickableThenClick($("tr[class$='ui-state-highlight']"));
    waitForElementClickableThenClick($("button[id$='" + filterIdName + "-filter:filter-input-form:update-command']"));
  }

  public void filterByResponsible(String user, String filterName, String filterIdName) {
    openAdvancedTaskFilter(filterName, filterIdName);
    waitForElementClickableThenClick($("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"));
    $("input[id$='responsible-filter:filter-input-form:responsible_input']").sendKeys(user);
    waitForElementDisplayed($("span[id$='responsible-filter:filter-input-form:responsible_panel']"), true);
    waitForElementDisplayed($("div[id*='responsible-filter'] .ui-avatar-text"), true);
    waitForElementClickableThenClick($("[id$=':responsible-filter:filter-input-form:responsible_panel']"));
    waitForElementClickableThenClick($("button[id$='responsible-filter:filter-input-form:update-command']"));
  }

  public void removeUserInFilter() {
    waitForElementDisplayed(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"),
        true);
    waitForElementClickableThenClick($("button[id$='creator-filter:filter-open-form:advanced-filter-command']"));
    waitForElementDisplayed(
        By.cssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']"), true);
    findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']")
        .click();
    findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']")
        .clear();
    waitForElementClickableThenClick($("button[id$='creator-filter:filter-input-form:update-command']"));
  }

  public void filterUserInCase(String user, String filterName, String filterIdName) {
    openAdvancedCaseFilter(filterName, filterIdName);
    filterByUserName(user, filterIdName);
  }

  public void removeResponsible() {
    waitForElementDisplayed(By.cssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"),
        true);
    waitForElementClickableThenClick($("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"));
    waitForElementDisplayed(By.cssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']"),
        true);
    findElementByCssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']").click();
    findElementByCssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']").clear();
    waitForElementClickableThenClick($("button[id$='responsible-filter:filter-input-form:update-command']"));
  }

  public void openNoActivatorFilter(String filterName) {
    findTaskFilterButton().click();
    SelenideElement filterSelectionElement = findElementById(TASK_FILTER_SELECTION);
    for (SelenideElement filterElement : filterSelectionElement.$$(By.tagName("LABEL"))) {
      if (filterName.equals(filterElement.getText())) {
        filterElement.click();
        waitForElementClickableThenClick(
            $("button[id$='task-widget:task-filter-add-form:update-task-filter-selected-command']"));
        break;
      }
    }
  }

  public void filterByUnavailableActivator() {
    waitForElementDisplayed(
        By.cssSelector("button[id$='available-activator-filter:filter-open-form:advanced-filter-command']"), true);
    waitForElementClickableThenClick(
        $("button[id$='available-activator-filter:filter-open-form:advanced-filter-command']"));

    waitForElementDisplayed(By.cssSelector("[id$='available-activator-filter:filter-input-form:available-activator']"),
        true);
    WebElement displayOnlyUnavailableTaskCheckbox =
        findElementByCssSelector("[id$='available-activator-filter:filter-input-form:available-activator']");
    displayOnlyUnavailableTaskCheckbox.click();
    waitForElementClickableThenClick($("button[id$='available-activator-filter:filter-input-form:update-command']"));
  }

  public boolean isResetButtonShown() {
    waitForElementDisplayed(By.cssSelector("[id$='task-widget:filter-reset-action']"), true);
    return isElementDisplayed(By.cssSelector("[id$='task-widget:filter-reset-action']"));
  }

  public String getUser(String filterName) {
    waitForElementDisplayed($("button[id$='" + filterName + "-filter:filter-open-form:advanced-filter-command']"),
        true);
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until((driver) -> findElementByCssSelector(
        "button[id$='" + filterName + "-filter:filter-open-form:advanced-filter-command'] > span").getText()
            .length() > 1);
    return findElementByCssSelector(
        "button[id$='" + filterName + "-filter:filter-open-form:advanced-filter-command'] > span").getText();
  }

  public void resetFilter() {
    waitForElementClickableThenClick($("[id$='task-widget:filter-reset-action']"));
    waitForElementDisplayed($("[id$='task-widget:filter-reset-command']"), true);
    waitForElementClickableThenClick($("[id$='task-widget:filter-reset-command']"));
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((driver) -> $("a[id$='task-widget:filter-selection-form:filter-name'] > span:nth-child(2)").getText()
            .contains("Default"));
  }

  public SelenideElement findColumnContainer() {
    return $(By.tagName("body")).$(By.cssSelector(".ui-columntoggler"));
  }

  public void countSavedFilter(int size) {
    waitForElementDisplayed($("a[id$='task-widget:filter-selection-form:filter-name']"), true);
    waitForElementClickableThenClick("a[id$='task-widget:filter-selection-form:filter-name']");
    $$(".user-defined-filter-container").shouldBe(CollectionCondition.sizeGreaterThanOrEqual(size), DEFAULT_TIMEOUT);
  }
}
