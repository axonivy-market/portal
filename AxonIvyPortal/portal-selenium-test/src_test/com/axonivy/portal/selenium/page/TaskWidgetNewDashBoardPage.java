package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.Keys;

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
    ElementsCollection elementsTH = $(taskWidgetId).$(".ui-datatable-scrollable-header")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("table thead tr th");
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

  private SelenideElement getColumnOfTaskHasIndex(int index, String columnName) {
    int startIndex = getIndexWidgetByColumn(columnName);
    return getColumnOfTableWidget(index).get(startIndex);
  }

  public void waitForFilterNotificationAppear() {
    $(".task-dashboard-widget__panel span.widget__filter-noti-number").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void startFirstTask() {
    WaitHelper.waitForNavigation(() -> getColumnOfTaskHasIndex(0, "Start").shouldBe(appear, DEFAULT_TIMEOUT).click());
  }

  public void startFirstTaskAndWaitShowHomePageButton() {
    $(".task-dashboard-widget__panel span.widget__filter-noti-number").shouldBe(appear, DEFAULT_TIMEOUT);
    getColumnOfTaskHasIndex(0, "Start").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("a>span.si-house-chimney-2.portal-icon").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void startTask(int taskIndex) {
    $$("span.widget__filter-noti-number").first().shouldBe(appear, DEFAULT_TIMEOUT);
    getColumnOfTaskHasIndex(taskIndex, "Start").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public ElementsCollection countRelatedCases() {
    return $("div[id$='related-cases']").$$("td.name-column");
  }

  public void openFilterWidget() {
    getTaskWidgetHeader().$(".widget__filter-sidebar-link").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    clickByJavaScript(getTaskWidgetHeader().$(".widget__filter-sidebar-link"));
    $("[id$=':widget-saved-filters-items").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void filterTaskName(String input) {
    var taskNameFilter = $("div[id$='widget-filter-content']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$(".ui-inputfield.text-field-input-name");
    taskNameFilter.clear();
    taskNameFilter.sendKeys(input);
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
        .filter(text("Reset")).first().shouldBe(getClickableCondition()).click();
  }

  public void filterPriority(String... priorities) {
    $("div[id$='widget-filter-content']").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$=':priorities']")
        .$(".ui-selectcheckboxmenu-trigger.ui-corner-right").shouldBe(getClickableCondition()).click();
    var priorityCheckboxOptions = $("[id$=':priorities_panel']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$(".ui-selectcheckboxmenu-item.ui-selectcheckboxmenu-list-item")
        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1));
    for (var item : priorityCheckboxOptions) {
      for (var prio : priorities) {
        if (item.getAttribute("data-item-value").equalsIgnoreCase(prio)) {
          item.$(".ui-chkbox-box.ui-widget").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
          break;
        }
      }
    }
  }

  public void filterCategories(String... categories) {
    $("div[id$='widget-filter-content']").shouldBe(appear, DEFAULT_TIMEOUT).$("[id$=':widget-filter-category']")
        .shouldBe(getClickableCondition()).click();
    var categoriesPanel = $("[id$=':widget-filter-category-panel']").shouldBe(appear, DEFAULT_TIMEOUT);
    categoriesPanel.$("[id$=':widget-category-filter-tree']").$$(".ui-chkbox").first().shouldBe(getClickableCondition())
        .click();

    categoriesPanel.$$(".ui-treenode").asDynamicIterable().forEach(leaf -> {
      for (var category : categories) {
        var leafValue = leaf.$(".ui-treenode-label").getText();
        if (category.equalsIgnoreCase(leafValue)) {
          leaf.$(".ui-chkbox").shouldBe(getClickableCondition()).click();
          break;
        }
      }
    });

    categoriesPanel.$("button[id$=':update-command']").shouldBe(getClickableCondition()).click();
    categoriesPanel.shouldBe(disappear, DEFAULT_TIMEOUT);
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
    var savedFilterContainer = getSavedFilterContainer();
    savedFilterContainer.$(".ui-commandlink.saved-filter__manage-filter").shouldBe(getClickableCondition()).click();
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
    var searchFilter = savedFilterPanel.$("[id$=':search-saved-filter-input']").shouldBe(Condition.visible,
        DEFAULT_TIMEOUT);
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

  private SelenideElement getFilterCheckBox(String inputField) {
    return $("div[id$='widget-filter-content']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("div.widget-filter-panel div.ui-g").filter(text(inputField)).first();
  }

  public void selectState(String state) {
    getStateFilterCheckBox(state).shouldBe(getClickableCondition()).click();
    getCloseStateFilter().shouldBe(getClickableCondition()).click();
  }

  public void filterTaskState() {
    getFilterCheckBox(FILTER_TASK_STATE).shouldBe(getClickableCondition()).click();
  }

  public ElementsCollection getActiveTaskActions(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    return $$(String.format("div.js-task-side-steps-panel-task_1-%d", taskIndex)).filter(appear).first()
        .shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-overlaypanel-content").$$("a[class*='option-item']");
  }

  public void clickOnTaskActionLink(int taskIndex) {
    getColumnOfCaseHasActionIndex(taskIndex, "Actions").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void reserveTask(int taskIndex) {
    getActiveTaskActions(taskIndex).filter(text("Reserve")).first().shouldBe(getClickableCondition()).click();
  }

  public void clickCancelTask() {
    $("a[id$='button-cancel']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void triggerEscalationTask(int taskIndex) {
    getActiveTaskActions(taskIndex).filter(text("Trigger Escalation")).first().shouldBe(getClickableCondition())
        .click();
    $("div[id='escalation-task-confirmation-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("button[id='confirm-escalation-dashboard-tasks']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  private ElementsCollection getColumnsOfTableWidget() {
    return $(taskWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr td");
  }

  private ElementsCollection getTasksOfTaskWidgetHasName(String taskName) {
    return getAllTasksOfTaskWidget().filter(text(taskName));
  }

  private ElementsCollection getAllTasksOfTaskWidget() {
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
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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

  public boolean isWidgetInfomationIconAppear() {
    return getTaskWidgetHeader().$(".widget__info-sidebar-link").isDisplayed();
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

  public boolean isExpandButtonAppear() {
    return getTaskWidgetHeader().$(".expand-link").isDisplayed();
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

  public boolean isTableResizable() {
    return $(taskWidgetId).has(Condition.cssClass("ui-datatable-resizable"));
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
    for (int i = 0; i < elementsTH.size(); i++) {
      if (elementsTH.get(i).getText().equalsIgnoreCase(columnName)) {
        elementsTH.get(i).click();
        elementsTH.get(i).shouldBe(Condition.cssClass("ui-state-active"), DEFAULT_TIMEOUT);
      }
    }
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

  public boolean isQuickSearchInputShow(String widgetIndex) {
    String taskWidgetIndex = String.format("div[id*='task-task_%s']", widgetIndex);
    waitPageLoaded();
    return $(taskWidgetIndex).$("form").$("input").exists();
  }

  public ElementsCollection getCaseList() {
    return $("div[id$='dashboard-tasks']").shouldBe(appear, DEFAULT_TIMEOUT).$(".ui-datatable-scrollable-body")
        .shouldBe(appear, DEFAULT_TIMEOUT).$("table tbody").$$("tr");
  }

  public void setInputForQuickSearch(String input) {
    getQuickSearchForm().$("input").sendKeys(input);
    waitForPageLoad();
  }

  private SelenideElement getQuickSearchForm() {
    return getTaskWidgetHeader().$("div[class*='widget-header-quick-search']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("form");
  }

  public void clearQuickSearchInput() {
    getQuickSearchForm().$("input").clear();
    waitForPageLoad();
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

  public boolean isEmptyMessageAppear() {
    return $("div[id$='empty-message-container']").exists();
  }

  public void clickOnManageColumns() {
    $("a[id$='column-toggler']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("div[id$=':column-management-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectCustomBusinessCaseFieldType() {
    $("label[id$='column-management-form:field-type-selection_label']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("li[data-label='" + CUSTOM_BUSINESS_CASE_FIELD + "']").shouldBe(appear).click();
    waitForPageLoad();
  }
  
  public void selectCustomerNameField() {
    $("input[id$='custom-business-case-field-selection_input']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("button[id$='custom-business-case-field-selection_button']").shouldBe(appear).click();
    $("li[data-item-label='" + CUSTOMER_NAME + "']").shouldBe(appear).click();
    $("input[value='" + "Customer name column for example" + "']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void clickAddButton() {
    $("button[id$='field-add-btn']").shouldBe(appear).click();
  }

  public void clickSaveButton() {
    $("button[id$='column-management-save-btn']").shouldBe(appear).click();
  }

  public void saveWidgetConfiguration() {
    $("button[id$='widget-configuration-save-button']").shouldBe(appear).click();
  }
  
  public String getCustomBusinessCaseFieldValueFromRowIndex(int dataRowIndex) {
    SelenideElement tableRow = $("tbody[id$='dashboard-tasks_data']").$("tr[data-ri='" + dataRowIndex + "']").shouldBe(appear);
    String rowCustomBusinessCaseFieldValue = tableRow.$$("td").last().$("span[id$='custom-column']").getText();
    return rowCustomBusinessCaseFieldValue ;
  }
}
