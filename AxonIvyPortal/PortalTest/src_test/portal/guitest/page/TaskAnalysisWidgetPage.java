package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;


public class TaskAnalysisWidgetPage extends TemplatePage {

  private static final String WIDGET_ID = "task-widget";

  public TaskAnalysisWidgetPage() {
  }
  
  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'" + WIDGET_ID + ":statistic-result-list')]";
  }

  public StatisticWidgetPage navigateToStatisticPage() {
    String backButtonId = "task-widget:back-button";
    waitForElementDisplayed(By.id(backButtonId), true, DEFAULT_TIMEOUT);
    WebElement backButton = findElementById(backButtonId);

    click(backButton);
    waitForElementDisplayed(By.id("statistics-widget"), true, DEFAULT_TIMEOUT);
    return new StatisticWidgetPage();
  }

  public WebElement findColumnToggler() {
    String togglerId = "task-widget:statistic-result-form:toggler";
    waitForElementDisplayed(By.id(togglerId), true);
    return findElementById(togglerId);
  }

  private WebElement findTaskFilterButton() {
    refreshAndWaitElement("button[id$='task-widget:task-filter-add-action']");
    String taskFilterButtonId = "task-widget:task-filter-add-action";
    waitForElementDisplayed(By.id(taskFilterButtonId), true);
    return findElementById(taskFilterButtonId);
  }

  private WebElement findCaseFilterButton() {
    refreshAndWaitElement("button[id$='task-widget:case-filter-add-action']");
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
    click(findTaskFilterButton());
    WebElement filterSelectionElement = findElementById("task-widget:task-filter-add-form:task-filter-selection");
    for (WebElement filterElement : findChildElementsByTagName(filterSelectionElement, "LABEL")) {
      if (filterName.equals(filterElement.getText())) {
        click(filterElement);
        click(By.cssSelector("button[id$='task-widget:task-filter-add-form:update-task-filter-selected-command']"));
        waitAjaxIndicatorDisappear();
        ensureNoBackgroundRequest();
        break;
      }
    }
    refreshAndWaitElement("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']");
    waitForElementDisplayed(
        By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"), true);
  }

  public void openAdvancedCaseFilter(String filterName, String filterIdName) {
    click(findCaseFilterButton());
    WebElement filterSelectionElement = findElementById("task-widget:case-filter-add-form:case-filter-selection");
    for (WebElement filterElement : findChildElementsByTagName(filterSelectionElement, "LABEL")) {
      if (filterName.equals(filterElement.getText())) {
        click(filterElement);
        click(By.cssSelector("button[id$='task-widget:case-filter-add-form:update-task-filter-selected-command']"));
        waitAjaxIndicatorDisappear();
        ensureNoBackgroundRequest();
        break;
      }
    }
    refreshAndWaitElement("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']");
    waitForElementDisplayed(
        By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"), true);
  }

  public void filterByTaskName(String text) {
    click(By.cssSelector("button[id$='task-name-filter:filter-open-form:advanced-filter-command']"));
    WebElement nameInput =
        findElementByCssSelector("input[id$='task-name-filter:filter-input-form:name']");
    enterKeys(nameInput, text);
    click(By.cssSelector("button[id$='task-name-filter:filter-input-form:update-command']"));
    waitAjaxIndicatorDisappear();
  }

  public void filterByTaskPriority(List<String> selectedPriorities) {
    click(By.cssSelector("button[id$='priority-filter:filter-open-form:advanced-filter-command']"));
    WebElement selectionElement = findElementByCssSelector("[id$='priority-filter:filter-input-form:advanced-filter-panel-content']");
    for (WebElement labelElement : findChildElementsByTagName(selectionElement, "LABEL")) {
      if (!selectedPriorities.contains(labelElement.getText())) {
        click(labelElement);
      }
    }
    click(By.cssSelector("button[id$='priority-filter:filter-input-form:update-command']"));
    waitForPageLoaded();
    waitAjaxIndicatorDisappear();
  }

  public void filterByTaskCategory(String selectedCategory) {
    click(By.cssSelector("button[id$='task-category-filter:filter-open-form:advanced-filter-command']"));
    WebElement selectionElement = findElementByCssSelector("[id$='task-category-filter:filter-input-form:advanced-filter-panel-content']");
    List<WebElement> categoryTreeLabels = findChildElementsByClassName(selectionElement, "ui-treenode-label");
    //Find parent node of tree first and uncheck it
    WebElement parentNodeOfTree = categoryTreeLabels.stream().findFirst().get();
    click(parentNodeOfTree);
    for (WebElement labelElement : categoryTreeLabels) {
      if (selectedCategory.contains(labelElement.getText())) {
        click(labelElement);
        break;
      }
    }
    click(By.cssSelector("button[id$='task-category-filter:filter-input-form:update-command']"));
    waitAjaxIndicatorDisappear();
  }

  public void filterByCaseName(String text) {
    click(By.cssSelector("button[id$='case-name-filter:filter-open-form:advanced-filter-command']"));
    WebElement nameInput =
        findElementByCssSelector("input[id$='case-name-filter:filter-input-form:name']");
    enterKeys(nameInput, text);
    click(By.cssSelector("button[id$='case-name-filter:filter-input-form:update-command']"));
    waitAjaxIndicatorDisappear();
  }

  public void filterByCaseState(List<String> selectedPriorities) {
    click(By.cssSelector("div[id$='task-widget:case-filters-container'] button[id$='state-filter:filter-open-form:advanced-filter-command']"));
    WebElement selectionElement = findElementByCssSelector("div[id$='task-widget:case-filters-container'] div[id$='state-filter:filter-input-form:advanced-filter-panel-content']");
    for (WebElement labelElement : findChildElementsByTagName(selectionElement, "LABEL")) {
      if (!selectedPriorities.contains(labelElement.getText())) {
        click(labelElement);
        waitAjaxIndicatorDisappear();
      }
    }
    click(By.cssSelector("div[id$='task-widget:case-filters-container'] button[id$='state-filter:filter-input-form:update-command']"));
    waitAjaxIndicatorDisappear();
  }

  public void filterByCaseCategory(String selectedCategory) {
    click(By.cssSelector("button[id$='case-category-filter:filter-open-form:advanced-filter-command']"));
    WebElement selectionElement = findElementByCssSelector("[id$='case-category-filter:filter-input-form:advanced-filter-panel-content']");
    List<WebElement> categoryTreeLabels = findChildElementsByClassName(selectionElement, "ui-treenode-label");
    //Find parent node of tree first and uncheck it
    WebElement parentNodeOfTree = categoryTreeLabels.stream().findFirst().get();
    click(parentNodeOfTree);  
    for (WebElement labelElement : categoryTreeLabels) {
      if (selectedCategory.contains(labelElement.getText())) {
        click(labelElement);
        break;
      }
    }
    click(By.cssSelector("button[id$='case-category-filter:filter-input-form:update-command']"));
    waitAjaxIndicatorDisappear();
  }

  public void saveFilterSet(String filterSetName, boolean isPersonalFilter) {
    click(By.id("task-widget:filter-save-action"));
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.id("task-widget:filter-save-form:save-filter-set-name-input"), true);
    enterKeys(findElementById("task-widget:filter-save-form:save-filter-set-name-input"), filterSetName);
    Sleeper.sleep(1000);

    WebElement filterVisibilityContainer = findElementById("task-widget:filter-save-form:save-filter-type-radio");
    if (isPersonalFilter) {
      click(filterVisibilityContainer.findElements(By.tagName("LABEL")).get(0));
    } else {
      click(filterVisibilityContainer.findElements(By.tagName("LABEL")).get(1));
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
    click(filterContainer.findElement(By.linkText(filterSetName)));
    waitAjaxIndicatorDisappear();
    ensureNoBackgroundRequest();
  }
  
  public String getFilterName() {
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(
        () -> findElementByCssSelector("a[id$='task-widget:filter-selection-form:filter-name'] > span:nth-child(2)")
            .getText().length()>1);
    WebElement filterName = findElementByCssSelector("a[id$='task-widget:filter-selection-form:filter-name'] > span:nth-child(2)");
    return filterName.getText();
  }

  public boolean isResetButtonShown() {
    waitForElementDisplayed(By.cssSelector("button[id$='task-widget:filter-reset-action']"), true);
    return  isElementDisplayed(By.cssSelector("button[id$='task-widget:filter-reset-action']"));
  }

  public void resetFilter() {
    click(By.cssSelector("button[id$='task-widget:filter-reset-action']"));
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.cssSelector("button[id$='task-widget:filter-reset-command']"), true);
    click(By.cssSelector("button[id$='task-widget:filter-reset-command']"));
    waitAjaxIndicatorDisappear();
    refreshAndWaitElement("a[id$='task-widget:filter-selection-form:filter-name'] > span:nth-child(2)");
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(
        () -> findElementByCssSelector("a[id$='task-widget:filter-selection-form:filter-name'] > span:nth-child(2)")
            .getText().contains("Default"));  
  }

  public void filterUserInCase(String user, String filterName,String filterIdName) {
    openAdvancedCaseFilter(filterName, filterIdName);
    filterByUserName(user, filterIdName);
  }
  
  private void filterByUserName(String user, String filterIdName) {
    click(By.cssSelector("button[id$='"+filterIdName+"-filter:filter-open-form:advanced-filter-command']"));
    WebElement responsible = findElementByCssSelector("input[id*='"+filterIdName+"-filter:filter-input-form:']");
    type(responsible, user);
    waitAjaxIndicatorDisappear();
    waitForElementExisted("i[class*='fa-user']", true, 5);
    click(By.cssSelector("tr[class$='ui-state-highlight']"));
    waitAjaxIndicatorDisappear();
    click(By.cssSelector("button[id$='"+filterIdName+"-filter:filter-input-form:update-command']"));
    waitAjaxIndicatorDisappear();
    Sleeper.sleep(2000);
  }
  
  public void removeUserInFilter() {
    waitForElementDisplayed(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"),
        true);
    click(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"));
    waitForElementDisplayed(By.cssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']"),
        true);
    findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']").click();
    findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']").clear();
    click(By.cssSelector("button[id$='creator-filter:filter-input-form:update-command']"));
    waitAjaxIndicatorDisappear();
    Sleeper.sleep(2000);
  }
  
  public void removeResponsible() {
    waitForElementDisplayed(By.cssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"),
        true);
    click(By.cssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"));
    waitForElementDisplayed(By.cssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']"),
        true);
    findElementByCssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']").click();
    findElementByCssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']").clear();
    click(By.cssSelector("button[id$='responsible-filter:filter-input-form:update-command']"));
    waitAjaxIndicatorDisappear();
    Sleeper.sleep(2000);
  }
  
  public void filterByResponsible(String user, String filterName,String filterIdName) {
    openAdvancedTaskFilter(filterName, filterIdName);
    click(By.cssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"));
    WebElement responsible =
        findElementByCssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']");
    //enterKeys(responsible, text);
    type(responsible,user);
    waitForElementDisplayedByCssSelector("span[id$='responsible-filter:filter-input-form:responsible_panel']", 10);
    Sleeper.sleep(2000);
    waitForElementDisplayedByCssSelector("i[class*='fa-user']",5);
    click(By.cssSelector("i[class*='fa-user']"));
    waitAjaxIndicatorDisappear();
    click(By.cssSelector("button[id$='responsible-filter:filter-input-form:update-command']"));
    waitAjaxIndicatorDisappear();
    Sleeper.sleep(2000);
  }
  public String getUser(String filterName) {
    waitForElementDisplayed(By.cssSelector("button[id$='" + filterName + "-filter:filter-open-form:advanced-filter-command']"),true,DEFAULT_TIMEOUT);
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
        .until(() -> findElementByCssSelector(
            "button[id$='" + filterName + "-filter:filter-open-form:advanced-filter-command'] > span").getText()
                .length() > 1);
    return findElementByCssSelector(
        "button[id$='" + filterName + "-filter:filter-open-form:advanced-filter-command'] > span").getText();
  }
  
  public List<WebElement> getRowsInTaskTable() {
    return findElementById("task-widget:statistic-result-form:task-table_data").findElements(By.cssSelector("tr[role='row']"));
  }
  
  public void clickApplyFilter() {
    click(By.cssSelector("button[id$='task-widget:apply-filter']"));
    waitAjaxIndicatorDisappear();
    ensureNoBackgroundRequest();
  }
  
  public void waitForTaskDataChangeToSpecificSize(int size) {
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> {
      return getRowsInTaskTable().size() == size;
    });
  }
}