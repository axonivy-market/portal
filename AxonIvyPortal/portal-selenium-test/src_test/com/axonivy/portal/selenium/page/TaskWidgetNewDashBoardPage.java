package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.ComplexFilterHelper;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.Sleeper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ScrollIntoViewOptions;
import com.codeborne.selenide.ScrollIntoViewOptions.Block;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class TaskWidgetNewDashBoardPage extends TemplatePage {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String FILTER_TASK_STATE = "State";
  private static final String DESCENDING = "descending";
  private static final String ASCENDING = "ascending";
  private static final String CUSTOM_BUSINESS_CASE_FIELD = "Custom business case field";
  private static final String CUSTOMER_NAME = "CustomerName";

  private String taskWidgetId;
  private String taskWidgetName;

  public TaskWidgetNewDashBoardPage() {
    this("div[id$='dashboard-tasks']", YOUR_TASKS_WIDGET);
  }

  public TaskWidgetNewDashBoardPage(String taskWidgetName) {
    this("div[id$='dashboard-tasks']", taskWidgetName);
  }

  public TaskWidgetNewDashBoardPage(String taskWidgetId, String taskWidgetName) {
    this.taskWidgetId = taskWidgetId;
    this.taskWidgetName = taskWidgetName;
  }

  @Override
  protected String getLoadedLocator() {
    return "[id$='dashboard-tasks-container']";
  }

  private SelenideElement getColumnOfTaskHasActionIndex(int index, String columnName) {
    return getCellByRowAndColumnName(index, columnName).$("button.dashboard-side-steps-menu-button");
  }

  public ElementsCollection expand() {
    $$("div.widget__header").filter(text(taskWidgetName)).first().shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("div.widget__header").filter(text(taskWidgetName));
  }

  public SelenideElement getCellByRowAndColumnName(int rowIndex, String columnName) {
    int columnIndex = getColumnIndexByName(columnName);
    return $(taskWidgetId).$("tbody[id$='dashboard-tasks_data']").$("tr[data-ri='" + rowIndex + "']")
        .$$("td").get(columnIndex);
  }

  private int getColumnIndexByName(String columnName) {
    StaleElementReferenceException lastException = null;
    for (int attempt = 0; attempt < 3; attempt++) {
      try {
        return $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("thead th").texts().indexOf(columnName);
      } catch (StaleElementReferenceException e) {
        lastException = e;
      }
    }
    throw lastException;
  }

  public void startFirstTask() {
    WaitHelper.waitForNavigation(() -> getCellByRowAndColumnName(0, "Start").shouldBe(appear, DEFAULT_TIMEOUT).click());
  }

  public void startFirstTaskAndWaitShowHomePageButton() {
    getCellByRowAndColumnName(0, "Start").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  public void startTask(int taskIndex) {
    getCellByRowAndColumnName(taskIndex, "Start").shouldBe(getClickableCondition()).click();
  }

  public void startTask(String taskName) {
    int taskIndex =
        getAllTasksOfTaskWidget().asFixedIterable().stream().map(WebElement::getText).collect(Collectors.toList())
        .indexOf(taskName);
    getCellByRowAndColumnName(taskIndex, "Start").shouldBe(getClickableCondition()).click();
  }

  public ElementsCollection countRelatedCases() {
    return $("div[id$='related-cases']").$$("td.name-column");
  }

  public void openFilterWidget() {
    SelenideElement actionsMenuPanel = openWidgetActionsMenu();
    actionsMenuPanel.$$("a.ui-menuitem-link").filter(text("Filters")).first()
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    WaitHelper.waitPageNoAnimation();
    $("[id$=':widget-saved-filters-items").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement openWidgetActionsMenu() {
    waitForGlobalGrowlDisappear();
    SelenideElement actionsMenuButton = getTaskWidgetHeader().$("button[id$=':actions-menu-button_button']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(actionsMenuButton);
    String menuId = actionsMenuButton.getAttribute("id").replace("_button", "_menu");
    return $("[id='" + menuId + "']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void filterTaskName(String input, FilterOperator operator) {
    addFilter("Name", operator);
    inputValueOnLatestFilter(FilterValueType.TEXT, input);
  }

  public String getTaskNameFilterValue() {
    return $("div[id$='widget-filter-content']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$(".task-configuration__input-text.text-field-input-name")
        .shouldBe(Condition.cssClass("ui-state-filled"), DEFAULT_TIMEOUT).getValue();
  }

  public void applyFilter() {
    $("div.footer-buttons-container").shouldBe(appear, DEFAULT_TIMEOUT).$$("button[id$='apply-button']")
        .filter(text("Apply")).first().shouldBe(getClickableCondition()).click();
    $("div[id$='task-task_1:filter-dialog-0']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void resetFilter() {
    $("div.footer-buttons-container").shouldBe(appear, DEFAULT_TIMEOUT).$$("a[id$='reset-button']")
        .filter(text("Reset")).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickOnSaveFilterButton() {
    $("div.filter-overlay-panel__footer").shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$=':save-filter']")
        .shouldBe(getClickableCondition()).click();
    $("div[id$='save-widget-filter-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void saveANewWidgetFilter(String filterName) {
    var saveFilterDialog = $("div[id$='save-widget-filter-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    saveFilterDialog.$("input[id$='save-filter-form:save-filter-name']").sendKeys(filterName);
    saveFilterDialog.$("button[id$='save-filter-form:save-widget-filter-button']").shouldBe(getClickableCondition())
        .click();
    saveFilterDialog.shouldBe(disappear, DEFAULT_TIMEOUT);
    getSavedFilterContainer().$(".saved-filter__items").shouldBe(appear, DEFAULT_TIMEOUT).$$(".saved-filter-node")
        .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
  }

  public String clickOnASavedFilterItem(String filterName) {
    var savedFilterPanel = getSavedFilterContainer().$(".saved-filter__content");
    var savedFilterItems = savedFilterPanel.$$(".saved-filter-node");
    var selectSavedFilterId = "";
    for (var item : savedFilterItems) {
      if (filterName.equalsIgnoreCase(item.getText())) {
        selectSavedFilterId = item.getAttribute("id");
        item.shouldBe(getClickableCondition()).click();
        break;
      }
    }
    getSelectedFilter(selectSavedFilterId).shouldHave(Condition.cssClass("selected"));
    return selectSavedFilterId;
  }

  public SelenideElement getSelectedFilter(String selectSavedFilterId) {
    return $("[id$='" + selectSavedFilterId + "']").shouldBe(Condition.enabled, DEFAULT_TIMEOUT);
  }

  public boolean hasSavedFilterItem(String filterName) {
    var savedFilterPanel = getSavedFilterContainer().$(".saved-filter__content");
    var savedFilterItems = savedFilterPanel.$$(".saved-filter-node");
    for (var item : savedFilterItems) {
      if (filterName.equalsIgnoreCase(item.getText())) {
        return true;
      }
    }
    return false;
  }

  public void clickOnManageFilterLink() {
    $("a[class*='saved-filter__manage-filter']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='manage-filter-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getManageFilterDialog() {
    return $("[id$='manage-filter-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public int getTotalSavedFilterInManageFilterDialog() {
    var deleteSavedFilterForm = $("#delete-saved-filter-form").shouldBe(appear, DEFAULT_TIMEOUT);
    return deleteSavedFilterForm.$(".ui-datatable-data").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$(".saved-filter-selection-column").size();
  }

  public void deleteFirstSavedFilter() {
    var deleteSavedFilterForm = $("#delete-saved-filter-form").shouldBe(appear, DEFAULT_TIMEOUT);
    deleteSavedFilterForm.$(".ui-datatable-data").$$("tr td:nth-child(2)")
        .findBy(Condition.exactText("Tasks Filterset 4")).shouldBe(appear, DEFAULT_TIMEOUT);
    var totalWidgetFilter = getDelelteSavedFilterRow().size();
    deleteSavedFilterForm.$(".ui-datatable-data").$$(".saved-filter-selection-column").first()
        .shouldBe(getClickableCondition()).click();
    var removeButton = getDeleteWidgetFilterButton().shouldBe(Condition.enabled, DEFAULT_TIMEOUT);
    removeButton.shouldBe(getClickableCondition()).click();
    getDelelteSavedFilterRow().shouldHave(CollectionCondition.sizeLessThan(totalWidgetFilter));
  }

  private ElementsCollection getDelelteSavedFilterRow() {
    return $("[id$='delete-saved-filter-form:quick-filter-table']").shouldBe(appear, DEFAULT_TIMEOUT).$$("tbody tr");
  }

  public void clickOnResetFilter() {
    $("[id$=':reset-button']").shouldBe(Condition.enabled, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$=':saved-filters-container']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public int getNumberOfFilterApplied() {
    return getFilterNotiNumber();
  }

  public void searchWidgetFilter(String filterName) {
    var savedFilterPanel = getSavedFilterContainer();
    waitFirstWidgetFilterAppear(savedFilterPanel);
    var searchFilter =
        savedFilterPanel.$("[id$=':search-saved-filter-input']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    searchFilter.sendKeys(filterName);
  }

  public ElementsCollection getSavedFilterItems() {
    return getSavedFilterContainer().$$(".saved-filter-node");
  }

  public int getTotalSavedFilters() {
    var savedFilterPanel = getSavedFilterContainer();
    waitFirstWidgetFilterAppear(savedFilterPanel);
    return getSavedFilterItems().size();
  }

  private void waitFirstWidgetFilterAppear(SelenideElement savedFilterPanel) {
    savedFilterPanel.$(".saved-filter__content").shouldBe(appear, DEFAULT_TIMEOUT).$("[id$=':saved-filter-node']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getSavedFilterContainer() {
    return $("[id$=':saved-filters-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getDeleteWidgetFilterButton() {
    return $("button[id$='delete-saved-filter-form:delete-widget-filter-btn']");
  }

  public void openTask(String taskName) {
    $("div[id$=':task-component:dashboard-tasks']").shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr td span")
        .filter(text(taskName)).first().click();
  }

  private SelenideElement getStateFilterCheckBox(String value) {
    return $("div[id$='states_panel']").$("div.ui-selectcheckboxmenu-items-wrapper").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }

  private SelenideElement getCloseStateFilter() {
    return $("div[id$='states_panel']").shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-selectcheckboxmenu-close");
  }

  public void selectState(String state) {
    getStateFilterCheckBox(state).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getCloseStateFilter().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void filterTaskState(Object... states) {
    addFilter("State", null);
    inputValueOnLatestFilter(FilterValueType.STATE_TYPE, states);
  }

  public ElementsCollection getActiveTaskActions(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    return $$(String.format("div.js-task-side-steps-panel-task_1-%d", taskIndex)).filter(appear).first()
        .shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-overlaypanel-content")
        .$$("a[class*='option-item']")
        .filter(Condition.not(Condition.cssClass("ui-state-disabled")));
  }

  public void clickOnTaskActionLink(int taskIndex) {
    waitForGrowlMessageDisappear();
    getColumnOfTaskHasActionIndex(taskIndex, "Actions").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getSelectedTaskAction(int taskIndex) {
    return $$(String.format(
        "div.js-task-side-steps-panel-task_1-%d, div.js-task-side-steps-panel-default_task_list_dashboard_task_1-%d",
        taskIndex, taskIndex)).filter(appear).first().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public boolean isTaskAdditionActionDisplay(String taskName) {
    return !$("div[id$=':side-steps-panel'] div.task-additional-actions-panel").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$(" a > span").filter(Condition.text(taskName)).isEmpty();
  }

  public void reserveTask(int taskIndex) {
    getActiveTaskActions(taskIndex).filter(text("Reserve")).first().shouldBe(getClickableCondition()).click();
  }

  public List<String> getCustomFieldValuesOnTaskCustomFieldsDialog() {
    return $$("span[id$='number-value']")
        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(0), DEFAULT_TIMEOUT)
        .asFixedIterable()
        .stream()
        .map(SelenideElement::getText)
        .collect(Collectors.toList());
  }

  public SelenideElement getCustomFieldsPanelOfTask() {
    return $("div[id$='task-custom-fields-dialog']");
  }

  public boolean isCustomFieldsDialogDisplayed() {
    return getCustomFieldsPanelOfTask().isDisplayed();

  }

  public void clickCancelTask() {
    switchToIFrameOfTask();
    TaskIFrameTemplatePage taskIFrameTemplatePage = new TaskIFrameTemplatePage();
    taskIFrameTemplatePage.clickCancelButton();
  }

  public void triggerEscalationTask(int taskIndex) {
    getActiveTaskActions(taskIndex).filter(text("Trigger Escalation")).first().shouldBe(getClickableCondition())
        .click();
    $("div[id='escalation-task-confirmation-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("button[id='confirm-escalation-dashboard-tasks']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  protected ElementsCollection getColumnsOfTableWidget() {
    return $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr td");
  }

  private ElementsCollection getTasksOfTaskWidgetHasName(String taskName) {
    return getAllTasksOfTaskWidget().filter(text(taskName));
  }

  public void clickOnTaskName(String taskName) {
    getAllTasksOfTaskWidget().filter(text(taskName)).first().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }

  protected ElementsCollection getAllTasksOfTaskWidget() {
    return getColumnsOfTableWidget().filter(Condition.cssClass("dashboard-tasks__name"));
  }

  public ElementsCollection countTasks(String taskName) {
    return getTasksOfTaskWidgetHasName(taskName);
  }

  public ElementsCollection countAllTasks() {
    return getAllTasksOfTaskWidget();
  }

  private void confirmDestroy() {
    $("div[id$='destroy-task-confirmation-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("button[id$='confirm-destruction-dashboard-tasks']").shouldBe(getClickableCondition()).click();
    $("button[id$='confirm-destruction-dashboard-tasks']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement destroyTaskLink() {
    return $("a[id$='task-destroy-command']");
  }

  public void destroy() {
    destroyTaskLink().shouldBe(getClickableCondition()).click();
    confirmDestroy();
  }

  public SelenideElement getDestroyDialog() {
    return $("div[id$='destroy-task-confirmation-dialog']");
  }

  public SelenideElement stateOfFirstTask() {
    return getCellByRowAndColumnName(0, FILTER_TASK_STATE).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement responsibleOfFirstTask() {
    return getCellByRowAndColumnName(0, "Responsible").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public TaskEditWidgetNewDashBoardPage openEditTaskWidget() {
    $$("div.table-widget-panel div.widget__header").filter(text(taskWidgetName)).first()
        .shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='widget-header-actions']").$("[id*='edit-widget']")
        .shouldBe(getClickableCondition()).click();
    return new TaskEditWidgetNewDashBoardPage();
  }

  public void deleteTaskWidget() {
    $$("div.table-widget-panel div.widget__header").filter(text(taskWidgetName)).first()
        .shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='widget-header-actions']").$("[id*='delete-widget']")
        .shouldBe(getClickableCondition()).click();
  }

  private SelenideElement getTaskWidgetHeader() {
    return $$("div.table-widget-panel").filter(text(taskWidgetName)).first();
  }

  public void clickOnButtonWidgetInformation() {
    SelenideElement actionsMenuButton = getTaskWidgetHeader().$("button[id$=':actions-menu-button_button']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(actionsMenuButton);
    String menuId = actionsMenuButton.getAttribute("id").replace("_button", "_menu");
    SelenideElement actionsMenuPanel = $("[id='" + menuId + "']").shouldBe(appear, DEFAULT_TIMEOUT);
    actionsMenuPanel.$$("a.ui-menuitem-link").filter(text("Widget information")).first()
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getExpiryTodayLabelInWidgetInfo() {
    return $("[id$='expiry-tab']").shouldBe(appear, DEFAULT_TIMEOUT).$("div div").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickToExpandNumberOfTaskByState() {
    SelenideElement element = $("[id$='state-tab_header']").shouldBe(appear, DEFAULT_TIMEOUT);
    if ("false".equalsIgnoreCase(element.getAttribute("aria-expanded"))) {
      element.click();
    }
  }

  public SelenideElement getFirstStateLabelInWidgetInfo() {
    return $("[id$='state-tab']").shouldBe(appear, DEFAULT_TIMEOUT).$("div div").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickToExpandNumberOfTaskByCategory() {
    SelenideElement element = $("[id$='category-tab_header']").shouldBe(appear, DEFAULT_TIMEOUT);
    if ("false".equalsIgnoreCase(element.getAttribute("aria-expanded"))) {
      element.click();
    }
  }

  public void clickToExpandPredefinedFilters() {
    SelenideElement element = $("[id$='filter-tab_header']").shouldBe(appear, DEFAULT_TIMEOUT);
    if ("false".equalsIgnoreCase(element.getAttribute("aria-expanded"))) {
      element.click();
    }
  }

  public void closeWidgetInformationDialog() {
    $("div.info-overlay-panel__footer").$("a[onclick*='hide']").click();
  }

  public void clickOnButtonExpandTaskWidget() {
    clickOnToggleFullscreenMenuItem();
  }

  public ElementsCollection getExpandedTaskWidget() {
    return $("div.expand-fullscreen").$$("div.widget__header").filter(text(taskWidgetName));
  }

  public ElementsCollection getExpandedWidget() {
    return $$("div.expand-fullscreen");
  }

  public void clickOnButtonCollapseTaskWidget() {
    clickOnToggleFullscreenMenuItem();
  }

  public void pressEscapeKey() {
    Selenide.actions().sendKeys(Keys.ESCAPE).perform();
    WaitHelper.waitPageNoAnimation();
  }

  public void waitForFilterDialogDisappear() {
    $("div.filter-dialog[style*='display: block']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  private void clickOnToggleFullscreenMenuItem() {
    SelenideElement actionsMenuButton = getTaskWidgetHeader().$("button[id$=':actions-menu-button_button']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(actionsMenuButton);
    String menuId = actionsMenuButton.getAttribute("id").replace("_button", "_menu");
    SelenideElement actionsMenuPanel = $("[id='" + menuId + "']").shouldBe(appear, DEFAULT_TIMEOUT);
    actionsMenuPanel.$(".toggle-fullscreen-item").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  /*
   * return descending or ascending
   */
  public SelenideElement getTaskWidgetHeaderSorted() {
    return $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("th.ui-state-active");
  }

  /*
   * sortType descending or ascending
   */
  public void waitForSortingFinished(String sortType) {
    if (DESCENDING.equalsIgnoreCase(sortType)) {
      $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("th.ui-state-active")
          .shouldBe(Condition.attribute("aria-sort", DESCENDING), DEFAULT_TIMEOUT);
    } else {
      $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("th.ui-state-active")
          .shouldBe(Condition.attribute("aria-sort", ASCENDING), DEFAULT_TIMEOUT);
    }
  }

  public void clickOnHeaderTaskByColumn(String columnName) {
    $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table thead tr th").shouldHave(CollectionCondition.containExactTextsCaseSensitive(columnName)).filter(Condition.text(columnName)).first()
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table thead tr th").shouldHave(CollectionCondition.containExactTextsCaseSensitive(columnName)).filter(Condition.text(columnName)).first()
        .shouldHave(Condition.cssClass("ui-state-active"), DEFAULT_TIMEOUT);
  }

  public SelenideElement getTheFirstTaskWidgetByColumn(String columnName) {
    return getCellByRowAndColumnName(0, columnName);
  }

  public SelenideElement getTaskEmptyMessage() {
    return $("[id$='dashboard-tasks-container'] [id$='empty-message-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getGrowlTitle() {
    return $(".ui-growl-title").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getResponsibleAvatar() {
    return $(".dashboard-tasks__responsible > .has-avatar > .ui-avatar").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickExportExcel() {
    clickOnButtonWidgetInformation();
    $("div.info-overlay-panel__footer").$(".dashboard-excel-export-form").$("a").shouldBe(getClickableCondition())
        .click();
  }

  public boolean isQuickSearchInputShow() {
    return getTaskWidgetHeader().$("div.widget__header").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("div[class*='widget-header-quick-search']").isDisplayed();
  }

  public void setInputForQuickSearch(String input) {
    openQuickSearchInputIfHidden();
    getQuickSearchForm().$("input").sendKeys(input);
    waitPageLoaded();
  }

  public void openQuickSearchInputIfHidden() {
    SelenideElement quickSearchPanel = getTaskWidgetHeader().$("div[class*='widget-header-quick-search']");
    if (!quickSearchPanel.isDisplayed()) {
      getTaskWidgetHeader().$("button[id*='quick-search-icon']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
          .click();
      quickSearchPanel.shouldBe(appear, DEFAULT_TIMEOUT);
    }
  }

  private SelenideElement getQuickSearchForm() {
    return getTaskWidgetHeader().$("div[class*='widget-header-quick-search']").shouldBe(appear, DEFAULT_TIMEOUT).$("form");
  }

  public void clearQuickSearchInput() {
    openQuickSearchInputIfHidden();
    getQuickSearchForm().$("input").clear();
    waitPageLoaded();
  }

  public boolean isEmptyMessageAppear() {
    return $("div[id$='empty-message-container']").exists();
  }

  public void addFilter(String columnName, com.axonivy.portal.selenium.common.FilterOperator operator) {
    ComplexFilterHelper.addFilter(columnName, operator);
  }

  public boolean isOperatorOptionAvailableForFilterField(String fieldName, String operatorLabel) {
    var filterRow = $("div[class*='dashboard-widget-filter__main-panel']")
        .$$("div[class*='dashboard-widget-filter__filter-wrapper']").findBy(text(fieldName))
        .shouldBe(appear, DEFAULT_TIMEOUT);

    var operatorOptions = filterRow.$$("select[id$=':operator-selection_input'] option");
    return operatorOptions.texts().stream()
        .map(String::trim)
        .anyMatch(option -> option.equalsIgnoreCase(operatorLabel.trim()));
  }

  public void inputValueOnLatestFilter(FilterValueType type, Object... values) {
    ComplexFilterHelper.inputValueOnLatestFilter(type, values);
  }

  public void saveFilter(String widgetFilterName) {
    SelenideElement filterDialog = getConfigurationFilter();
    filterDialog.$("input[id$=':inline-save-filter-name']").shouldBe(appear, DEFAULT_TIMEOUT).setValue(widgetFilterName);
    filterDialog.$("button[id$=':inline-save-filter']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$=':widget-saved-filters-items']").$$("span.saved-filter-node__text").filter(text(widgetFilterName)).first()
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openManageFiltersDialog() {
    $("a[class*='saved-filter__manage-filter']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  public void removeAllFilterItems() {
    $("div[id='manage-filter-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[id$=':quick-filter-table_head_checkbox']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("button[id='delete-saved-filter-form:delete-widget-filter-btn']").click();
  }

  public void closeManageFilterDialog() {
    $("a[id*='delete-saved-filter-form']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  public void selectSavedFilter(String filterName) {
    getSavedFilterItems().filter(text(filterName)).first().shouldBe(getClickableCondition()).click();
  }

  public void searchSavedFilters(String input) {
    var savedFilterPanel = getSavedFilterContainer();
    savedFilterPanel.$("[id$=':search-saved-filter-input']").shouldBe(Condition.visible, DEFAULT_TIMEOUT)
        .setValue(input);
  }

  public void inputValueOnColumnWidgetHeader(String columnName, String value) {
    columnName = columnName + ": activate to sort column ascending";
    $("div[id='manage-filter-dialog']").$("div[id$=':quick-filter-table']")
        .$("div.ui-datatable-scrollable-header-box table thead tr")
        .$$("th[id*='delete-saved-filter-form:quick-filter-table']")
        .filter(Condition.attribute("aria-label", columnName)).first().$("input").setValue(value);
  }

  public ElementsCollection getSavedFilterItemsByFilterNameOnWidgetManagement() {
    ElementsCollection elements = $("div[id='manage-filter-dialog']").$("div.ui-datatable-scrollable-body table tbody")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("tr").filter(Condition.attribute("data-rk"));
    return elements;
  }

  public Integer getFilterNotiNumber() {
    SelenideElement actionsMenuButton = getTaskWidgetHeader().$("button[id$=':actions-menu-button_button']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(actionsMenuButton);
    String menuId = actionsMenuButton.getAttribute("id").replace("_button", "_menu");
    SelenideElement actionsMenuPanel = $("[id='" + menuId + "']").shouldBe(appear, DEFAULT_TIMEOUT);
    SelenideElement filtersMenuItem = actionsMenuPanel.$$("a.ui-menuitem-link").filter(text("Filters")).first();
    String filterNotiNumber = filtersMenuItem.$("span.ui-tag").shouldBe(appear, DEFAULT_TIMEOUT).getText();
    actionsMenuButton.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    actionsMenuPanel.shouldBe(disappear, DEFAULT_TIMEOUT);
    return Integer.parseInt(filterNotiNumber);
  }

  public void clickOnFilterOperator(Integer index) {
    $("div[class*='dashboard-widget-filter__main-panel']").shouldBe(getClickableCondition())
        .$$("div[class*='dashboard-widget-filter__filter-wrapper']").get(index).shouldBe(getClickableCondition())
        .$("div[id$='operator-selection']").shouldBe(getClickableCondition()).click();
    Sleeper.sleep(300); // Wait for drop-down menu clearly appear before screenshot
  }

  public SelenideElement getConfigurationFilter() {
    return $("div.filter-dialog[style*='display: block']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void removeFocusFilterDialog() {
    $("[id$=':widget-filter-content']").$("strong").click();
    $("[id$=':widget-filter-content']").scrollIntoView(ScrollIntoViewOptions.instant().block(Block.end));
  }

  public SelenideElement getFilterOverlayPanel(Integer index) {
    String widgetIndex = String.format("div[id$='filter-dialog-%d']", index);
    return $(widgetIndex).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void removeFilter(int index) {
    int currentIndex = $$("div[id$=':filter-component:filter-selection-panel']").size();
    if (currentIndex > 0) {
      String removeBtn = String.format("button[id$=':%s:filter-component:remove-filter']", index);
      $(removeBtn).shouldBe(getClickableCondition()).click();
      countFilterSelect().shouldBe(CollectionCondition.size(currentIndex - 1), DEFAULT_TIMEOUT);
    }
  }

  public ElementsCollection countFilterSelect() {
    return $$("[id$=':filter-component:field-selection_panel']");
  }

  public int getNumberOfFilter() {
    return $("div[id$='widget-filter-content']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("div[class*='filter-dialog__content']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("div[id$='filter-container']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("div[class*='dashboard-widget-filter__filter-wrapper']").size();
  }

  public void clickOnBackToHomeButtonOnAjaxErrorDialog() {
    $("div[id*='ajax-indicator:ajax-indicator-error-ajax-dialog']").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id*='ajax-indicator:ajax-indicator-error-ajax-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT).$("button").click();
  }

  public boolean isAjaxErrorDialogDisplayed() {
    return $("div[id*='ajax-indicator:ajax-indicator-error-ajax-dialog']").exists();
  }

  public void copyAndPasteOnQuickSearchInput() {
    SelenideElement searchInput = getQuickSearchForm().$("input");
    searchInput.click();
    searchInput.sendKeys(Keys.HOME);
    searchInput.sendKeys(Keys.LEFT_SHIFT, Keys.END);
    searchInput.sendKeys(Keys.CONTROL, "C");
    searchInput.sendKeys(Keys.DELETE);
    searchInput.sendKeys(Keys.CONTROL, "V");
    searchInput.sendKeys(Keys.ENTER);
    waitForPageLoad();
  }

  public void shiftAndArrowKeyOnQuickSearchInput() {
    SelenideElement searchInput = getQuickSearchForm().$("input");
    searchInput.click();
    searchInput.sendKeys(Keys.HOME);
    searchInput.sendKeys(Keys.LEFT_SHIFT, Keys.RIGHT, Keys.RIGHT, Keys.RIGHT, Keys.RIGHT);
    searchInput.sendKeys(Keys.ENTER);
    waitForPageLoad();
  }

  public boolean isExpandButtonAppear() {
    WaitHelper.waitPageNoAjaxAndAnimation();
    SelenideElement actionsMenuButton = getTaskWidgetHeader().$("button[id$=':actions-menu-button_button']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(actionsMenuButton);
    String menuId = actionsMenuButton.getAttribute("id").replace("_button", "_menu");
    SelenideElement actionsMenuPanel = $("[id='" + menuId + "']").shouldBe(appear, DEFAULT_TIMEOUT);
    boolean isDisplayed = actionsMenuPanel.$(".toggle-fullscreen-item").exists();
    actionsMenuButton.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    actionsMenuPanel.shouldBe(disappear, DEFAULT_TIMEOUT);
    return isDisplayed;
  }

  public boolean isWidgetInfomationIconAppear() {
    WaitHelper.waitPageNoAjaxAndAnimation();
    SelenideElement actionsMenuButton = getTaskWidgetHeader().$("button[id$=':actions-menu-button_button']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(actionsMenuButton);
    String menuId = actionsMenuButton.getAttribute("id").replace("_button", "_menu");
    SelenideElement actionsMenuPanel = $("[id='" + menuId + "']").shouldBe(appear, DEFAULT_TIMEOUT);
    boolean isDisplayed = actionsMenuPanel.$$("a.ui-menuitem-link").filter(text("Widget information")).first().exists();
    actionsMenuButton.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    actionsMenuPanel.shouldBe(disappear, DEFAULT_TIMEOUT);
    return isDisplayed;
  }

  public void clickOnWidgetFilterHeader() {
    $$("strong").filter(Condition.text("Set Filter")).first().click();
  }

  public void clickOnManageColumns() {
    $("button[id$='manage-column']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("div[id$=':column-management-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectCustomBusinessCaseFieldType() {
    $("span[id$='column-management-form:field-type-selection_label']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    WaitHelper.waitPageNoAnimation();
    $("li[data-label='" + CUSTOM_BUSINESS_CASE_FIELD + "']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    waitForPageLoad();
  }

  public void selectCustomerNameField() {
    $("input[id$='custom-business-case-field-selection_input']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("button[id$='custom-business-case-field-selection_button']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    WaitHelper.waitPageNoAnimation();
    $("li[data-item-label='" + CUSTOMER_NAME + "']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("input[value='" + "Customer name column for example" + "']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickAddButton() {
    $("button[id$='field-add-btn']").shouldBe(appear).click();
  }

  public void clickSaveButton() {
    $("button[id$='column-management-save-btn']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  public void saveWidgetConfiguration() {
    $("button[id$='widget-configuration-save-button']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  public String getCustomBusinessCaseFieldValueFromRowIndex(int dataRowIndex) {
    SelenideElement tableRow = $("tbody[id$='dashboard-tasks_data']").$("tr[data-ri='" + dataRowIndex + "']").shouldBe(appear, DEFAULT_TIMEOUT);
    String rowCustomBusinessCaseFieldValue = tableRow.$$("td").last().$("span[id$='custom-column']").getText();
    return rowCustomBusinessCaseFieldValue;
  }

  public void clickCustomFieldsButtonOnActions(int taskIndex) {
    getActiveTaskActions(taskIndex).filter(text("Custom Fields")).first().shouldBe(getClickableCondition()).click();
    getCustomFieldsPanelOfTask().shouldBe(appear);
  }

  public List<String> getCustomFieldNamesOnTaskCustomFieldsDialog() {
    return $$("span[id$='customFieldLabel']")
        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(0), DEFAULT_TIMEOUT)
        .asFixedIterable()
        .stream()
        .map(SelenideElement::getText)
        .collect(Collectors.toList());
  }

  public void selectTaskToDelegateAtRow(int rowIndex, int widgetIndex) {
    $(taskWidgetId).$("tbody[id$='dashboard-tasks_data']")
        .$("tr[data-ri='" + rowIndex + "']")
        .$("td.dashboard-tasks__selection-column .ui-chkbox-box")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("button[id$='delegate-tasks-btn-" + widgetIndex + "']")
        .shouldNotHave(Condition.cssClass("ui-state-disabled"), DEFAULT_TIMEOUT);
  }

  public void selectTaskByName(String taskName) {
    int rowIndex = getAllTasksOfTaskWidget().asFixedIterable().stream()
        .map(WebElement::getText).collect(Collectors.toList()).indexOf(taskName);
    $(taskWidgetId).$("tbody[id$='dashboard-tasks_data']")
        .$("tr[data-ri='" + rowIndex + "']")
        .$("td.dashboard-tasks__selection-column .ui-chkbox-box")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(".delegation-action-bar").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickBulkDelegateToggleButton(int widgetIndex) {
    $("button[id$='bulk-delegate-toggle-button-" + widgetIndex + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(".dashboard-tasks__selection-column").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickDelegateTasksButton(int widgetIndex) {
    $("button[id$='delegate-tasks-btn-" + widgetIndex + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div.task-delegate-dialog").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection openBulkDelegateUserDropdownAndGetItems() {
    $("button[id$='user-activator-select_button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    WaitHelper.waitPageNoAnimation();
    return $$("span[id$='user-activator-select_panel'] .ui-autocomplete-item");
  }

  public void selectUserFromBulkDelegateDropdown(ElementsCollection items, String userName) {
    items.filter(Condition.text(userName)).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickRoleRadioButtonInBulkDelegate() {
    $$("label[for$='activator-type-select:1']").first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("input[id$='group-activator-select_input']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection openBulkDelegateRoleDropdownAndGetItems() {
    $("button[id$='group-activator-select_button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    WaitHelper.waitPageNoAnimation();
    return $$("span[id$='group-activator-select_panel'] .ui-autocomplete-item");
  }

  public void selectRoleFromBulkDelegateDropdown(ElementsCollection items, String roleName) {
    items.filter(Condition.text(roleName)).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickBulkDelegateProceedButton() {
    $("button[id$='proceed-multiple-task-delegate-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div.task-delegate-dialog").shouldBe(disappear, DEFAULT_TIMEOUT);
    waitForPageLoad();
  }

  public void checkLimitTaskSelection(int widgetIndex, String limit) {
    $("span[id$='selected-count-limit-" + widgetIndex + "']").shouldHave(Condition.partialText(limit), DEFAULT_TIMEOUT);
  }

  public SelenideElement getResponsibleCellByTaskName(String taskName) {
    int rowIndex = getAllTasksOfTaskWidget().asFixedIterable().stream()
        .map(WebElement::getText).collect(Collectors.toList()).indexOf(taskName);
    return getCellByRowAndColumnName(rowIndex, "Responsible");
  }

  public SelenideElement getDelegateDialog() {
    return $("div.task-delegate-dialog");
  }

  public void closeSavedFilterDialog() {
    $("a[id*='delete-saved-filter-form']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  public void selectBulkDelegation(int widgetIndex) {
    $("button[id$=':actions-menu-button_button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$=':bulk-delegate-toggle-button-0']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(".dashboard-tasks__selection-column").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}
