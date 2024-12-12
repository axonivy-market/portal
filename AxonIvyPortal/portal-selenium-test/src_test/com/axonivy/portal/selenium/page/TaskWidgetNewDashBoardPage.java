package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.ComplexFilterHelper;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class TaskWidgetNewDashBoardPage extends TemplatePage {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String FILTER_TASK_STATE = "State";
  private static final String DESCENDING = "descending";
  private static final String ASCENDING = "ascending";

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

  private int getIndexWidgetByColumn(String columnName) {
    ElementsCollection elementsTH = $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table thead tr th");
    for (int i = 0; i < elementsTH.size(); i++) {
      if (elementsTH.get(i).getText().equalsIgnoreCase(columnName)) {
        return i;
      }
    }
    return 0;
  }

  private int getIndexWidgetByColumnScrollable(String columnName) {
    ElementsCollection elementsTH =
        $(taskWidgetId).$(".ui-datatable-scrollable-header").shouldBe(appear, DEFAULT_TIMEOUT).$$("table thead tr th");
    for (int i = 0; i < elementsTH.size(); i++) {
      if (elementsTH.get(i).getAttribute("aria-label").equalsIgnoreCase(columnName)) {
        return i;
      }
    }
    return 0;
  }

  private SelenideElement getColumnOfCaseHasActionIndex(int index, String columnName) {
    int startIndex = getIndexWidgetByColumnScrollable(columnName);
    return getColumnOfTableWidget(index).get(startIndex).$("span a");
  }

  private ElementsCollection getColumnOfTableWidget(int rowIndex) {
    return $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr").get(rowIndex).$$("td");
  }

  public ElementsCollection expand() {
    $$("div.widget__header").filter(text(taskWidgetName)).first().shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("div.widget__header").filter(text(taskWidgetName));
  }

  protected SelenideElement getColumnOfTaskHasIndex(int index, String columnName) {
    int startIndex = getIndexWidgetByColumn(columnName);
    return getColumnOfTableWidget(index).get(startIndex);
  }

  public void startFirstTask() {
    $(".task-dashboard-widget__panel span.widget__filter-noti-number").shouldBe(appear, DEFAULT_TIMEOUT);
    WaitHelper.waitForNavigation(() -> getColumnOfTaskHasIndex(0, "Start").shouldBe(appear, DEFAULT_TIMEOUT).click());
  }

  public void startFirstTaskAndWaitShowHomePageButton() {
    $(".task-dashboard-widget__panel span.widget__filter-noti-number").shouldBe(appear, DEFAULT_TIMEOUT);
    getColumnOfTaskHasIndex(0, "Start").shouldBe(appear, DEFAULT_TIMEOUT).click();
    // $("a>span.si-house-chimney-2.portal-icon").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void startTask(int taskIndex) {
    $$("span.widget__filter-noti-number").first().shouldBe(appear, DEFAULT_TIMEOUT);
    getColumnOfTaskHasIndex(taskIndex, "Start").shouldBe(getClickableCondition()).click();
  }

  public ElementsCollection countRelatedCases() {
    return $("div[id$='related-cases']").$$("td.name-column");
  }

  public void openFilterWidget() {
    waitForGlobalGrowlDisappear();
    getTaskWidgetHeader().$(".widget__filter-sidebar-link").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(getTaskWidgetHeader().$(".widget__filter-sidebar-link"));
    $("[id$=':widget-saved-filters-items").shouldBe(appear, DEFAULT_TIMEOUT);
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
    $("div.filter-overlay-panel__footer").shouldBe(appear, DEFAULT_TIMEOUT).$$("button[id$='apply-button']")
        .filter(text("Apply")).first().shouldBe(getClickableCondition()).click();
    $("div[id$='task-task_1:filter-overlay-panel-0']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void resetFilter() {
    $("div.filter-overlay-panel__footer").shouldBe(appear, DEFAULT_TIMEOUT).$$("button[id$='reset-button']")
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
    $("div#manage-filter").shouldBe(appear, DEFAULT_TIMEOUT).$("button").shouldBe(getClickableCondition()).click();
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
    $("tr[data-rk='Tasks Filterset 4']").shouldBe(appear, DEFAULT_TIMEOUT);
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
    var numberNoti = $("[id$='task-task_1:task-panel-group-0']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$(".widget__filter-noti-number").shouldBe(appear, DEFAULT_TIMEOUT).getText();
    return Integer.valueOf(numberNoti);
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
    getColumnOfCaseHasActionIndex(taskIndex, "Actions").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void reserveTask(int taskIndex) {
    getActiveTaskActions(taskIndex).filter(text("Reserve")).first().shouldBe(getClickableCondition()).click();
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

  public SelenideElement stateOfFirstTask() {
    return getColumnOfTaskHasIndex(0, FILTER_TASK_STATE).shouldBe(appear, DEFAULT_TIMEOUT);
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
    getTaskWidgetHeader().$(".widget__info-sidebar-link").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
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
    getTaskWidgetHeader().$(".expand-link").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public ElementsCollection getExpandedTaskWidget() {
    return $("div.expand-fullscreen").$$("div.widget__header").filter(text(taskWidgetName));
  }

  public ElementsCollection getExpandedWidget() {
    return $$("div.expand-fullscreen");
  }

  public void clickOnButtonCollapseTaskWidget() {
    getTaskWidgetHeader().$(".collapse-link").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
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
    ElementsCollection elementsTH = $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table thead tr th");
    elementsTH.asDynamicIterable().forEach(headerElem -> {
      if (headerElem.getText().equalsIgnoreCase(columnName)) {
        waitForElementClickableThenClick(headerElem);

        // Sometimes browser click before JS of Primefaces loaded correctly.
        // -> header has state focus instead of active.
        // -> should check: after click, if header has state focus instead of active,
        // click again.
        try {
          headerElem.shouldHave(Condition.cssClass("ui-state-active"), DEFAULT_TIMEOUT);
        } catch (AssertionError e) {
          if (headerElem.has(Condition.cssClass("ui-state-focus"))) {
            waitForElementClickableThenClick(headerElem);
            headerElem.shouldHave(Condition.cssClass("ui-state-active"), DEFAULT_TIMEOUT);
          }
        }
      }
    });
  }

  public SelenideElement getTheFirstTaskWidgetByColumn(String columnName) {
    return getColumnOfTaskHasIndex(0, columnName);
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
    getQuickSearchForm().$("input").sendKeys(input);
    waitPageLoaded();
  }
  
  private SelenideElement getQuickSearchForm() {
    return getTaskWidgetHeader().$("div[class*='widget-header-quick-search']").shouldBe(appear, DEFAULT_TIMEOUT).$("form");
  }
  
  public void clearQuickSearchInput() {
    getQuickSearchForm().$("input").clear();
    waitPageLoaded();
  }

  public boolean isEmptyMessageAppear() {
    return $("div[id$='empty-message-container']").exists();
  }

  public void addFilter(String columnName, com.axonivy.portal.selenium.common.FilterOperator operator) {
    ComplexFilterHelper.addFilter(columnName, operator);
  }
  
  public void inputValueOnLatestFilter(FilterValueType type, Object... values) {
    ComplexFilterHelper.inputValueOnLatestFilter(type, values);
  }
  
  public void saveFilter(String widgetFilterName) {
    $("div.filter-overlay-panel__footer").shouldBe(appear, DEFAULT_TIMEOUT).$$("button[id$='save-filter']")
        .filter(text("Save filter")).first().shouldBe(getClickableCondition()).click();
    $("div#save-widget-filter-dialog").$("input[id='save-filter-form:save-filter-name']")
        .shouldBe(appear, DEFAULT_TIMEOUT).setValue(widgetFilterName);
    $("button[id$=':save-widget-filter-button']").click();
    $("div[id$=':widget-saved-filters-items']").$$("div.saved-filter__items").filter(text(widgetFilterName)).first()
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void openManageFiltersDialog() {
    $("div#manage-filter").shouldBe(appear, DEFAULT_TIMEOUT).$("button").shouldBe(getClickableCondition()).click();
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
    $("div[class*='saved-filter--search-container']").$("input[id$=':search-saved-filter-input']").setValue(input);
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
    String filterNotiNumber =
        $$("div.table-widget-panel").filter(text(taskWidgetName)).first().shouldBe(appear, DEFAULT_TIMEOUT)
            .$("div[id$=':widget-header-actions']").$("span[class*='widget__filter-noti-number']").getText();
    return Integer.parseInt(filterNotiNumber);
  }
  
  public void clickOnFilterOperator(Integer index) {
    $("div[class*='dashboard-widget-filter__main-panel']").shouldBe(getClickableCondition())
    .$$("div[class*='dashboard-widget-filter__filter-wrapper']").get(index).shouldBe(getClickableCondition())
    .$("div[id$='operator-selection']").shouldBe(getClickableCondition()).click();
  }
  
  public SelenideElement getConfigurationFilter() {
    return $("div[class*='filter-overlay-panel'][style*='display: block']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void removeFocusFilterDialog() {
    $("[id$=':widget-filter-content']").$("strong").click();
    $("[id$=':widget-filter-content']").scrollIntoView("{block: \"end\"}");
  }
  
  public WebElement getFilterOverlayPanel(Integer index) {
    String widgetIndex = String.format("div[id$='filter-overlay-panel-%d']", index);
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
    .$("div[class*='filter-overlay-panel__content']").shouldBe(appear, DEFAULT_TIMEOUT)
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
    return getTaskWidgetHeader().$(".expand-link").isDisplayed();
  }
  
  public boolean isWidgetInfomationIconAppear() {
    return getTaskWidgetHeader().$(".widget__info-sidebar-link").isDisplayed();
  }
  
  public void clickOnWidgetFilterHeader() {
    $$("strong").filter(Condition.text("Filter options")).first().click();
  }
}
