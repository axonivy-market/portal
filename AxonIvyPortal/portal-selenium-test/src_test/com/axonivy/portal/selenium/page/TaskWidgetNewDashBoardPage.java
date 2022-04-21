package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class TaskWidgetNewDashBoardPage extends TemplatePage {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private static final String FILTER_TASK_STATE = "State";

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

  private int getIndexWidgetByColumn(String columnName) {
    ElementsCollection elementsTH = $(taskWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$$("table thead tr th");
    for (int i = 0; i < elementsTH.size(); i++) {
      if (elementsTH.get(i).getText().equalsIgnoreCase(columnName)) {
        return i;
      }
    }
    return 0;
  }

  private ElementsCollection getColumnOfTableWidget(int rowIndex) {
    return $(taskWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$$("table tbody tr").get(rowIndex).$$("td");
  }

  public ElementsCollection expand() {
    return $$("div.widget__header").filter(text(taskWidgetName));
  }

  private SelenideElement getColumnOfTaskHasIndex(int index, String columnName) {
    int startIndex = getIndexWidgetByColumn(columnName);
    return getColumnOfTableWidget(index).get(startIndex);
  }

  public void startFirstTask() {
    $$("span.widget__filter-noti-number").first().waitUntil(appear, DEFAULT_TIMEOUT);
    getColumnOfTaskHasIndex(0, "Start").shouldBe(getClickableCondition()).click();
    $$("span.widget__filter-noti-number").first().waitUntil(disappears, DEFAULT_TIMEOUT);
  }

  public void startTask(int taskIndex) {
    $$("span.widget__filter-noti-number").first().waitUntil(appear, DEFAULT_TIMEOUT);
    getColumnOfTaskHasIndex(taskIndex, "Start").shouldBe(getClickableCondition()).click();
    $$("span.widget__filter-noti-number").first().waitUntil(disappears, DEFAULT_TIMEOUT);
  }

  public ElementsCollection countRelatedCases() {
    return $("div[id$='related-cases']").$$("td.name-column");
  }

  public void openFilterWidget() {
    $$("div.table-widget-panel").filter(text(taskWidgetName)).first().$("a.widget__filter-sidebar-link")
        .waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$=':widget-saved-filters-items").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public void filterTaskName(String input) {
    var taskNameFilter = $("div[id$='widget-filter-content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$(".ui-inputfield.text-field-input-name");
    taskNameFilter.sendKeys(input);
  }

  public String getTaskNameFilterValue() {
    return $("div[id$='widget-filter-content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$(".task-configuration__input-text.text-field-input-name").waitUntil(Condition.cssClass("ui-state-filled"), DEFAULT_TIMEOUT)
        .getValue();
  }
  
  public void applyFilter() {
    $("div.filter-overlay-panel__footer").waitUntil(appear, DEFAULT_TIMEOUT).$$("button[id$='apply-button']")
        .filter(text("Apply")).first().shouldBe(getClickableCondition()).click();
    $("div[id$='task-task_1:filter-overlay-panel-0']").waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void resetFilter() {
    $("div.filter-overlay-panel__footer").waitUntil(appear, DEFAULT_TIMEOUT).$$("button[id$='reset-button']")
        .filter(text("Reset")).first().shouldBe(getClickableCondition()).click();
  }

  public void filterPriority(String... priorities) {
    $("div[id$='widget-filter-content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("div[id$=':priorities']").$(".ui-selectcheckboxmenu-trigger.ui-corner-right")
        .shouldBe(getClickableCondition()).click();
    var priorityCheckboxOptions = $("[id$=':priorities_panel']").waitUntil(appear, DEFAULT_TIMEOUT)
      .$$(".ui-selectcheckboxmenu-item.ui-selectcheckboxmenu-list-item").shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1));
    for (var item : priorityCheckboxOptions) {
      for (var prio : priorities) {
        if (item.getAttribute("data-item-value").equalsIgnoreCase(prio)) {
          item.$(".ui-chkbox-box.ui-widget").shouldBe(getClickableCondition()).click();
          break;
        }
      }
    }
  }

  public void filterCategories(String... categories) {
    $("div[id$='widget-filter-content']").waitUntil(appear, DEFAULT_TIMEOUT)
      .$("[id$=':widget-filter-category']").shouldBe(getClickableCondition()).click();
  var categoriesPanel = $("[id$=':widget-filter-category-panel']").waitUntil(appear, DEFAULT_TIMEOUT);
  categoriesPanel.$("[id$=':widget-category-filter-tree']")
    .$$(".ui-chkbox").first().shouldBe(getClickableCondition()).click();
  
    categoriesPanel.$$(".ui-treenode").forEach(leaf -> {
       for (var category : categories) {
         var leafValue = leaf.$(".ui-treenode-label").getText();
         if (category.equalsIgnoreCase(leafValue)) {
           leaf.$(".ui-chkbox").shouldBe(getClickableCondition()).click();
           break;
         }
       }
    });
    
    categoriesPanel.$("button[id$=':update-command']").shouldBe(getClickableCondition()).click();
    categoriesPanel.waitUntil(disappears, DEFAULT_TIMEOUT);
  }

  public void clickOnSaveFilterButton() {
    $("div.filter-overlay-panel__footer").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("button[id$=':save-filter']").shouldBe(getClickableCondition()).click();
    $("div[id$='save-widget-filter-dialog']").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public void saveANewWidgetFilter(String filterName) {
    var saveFilterDialog = $("div[id$='save-widget-filter-dialog']").waitUntil(appear, DEFAULT_TIMEOUT);
    saveFilterDialog.$("input[id$='save-filter-form:save-filter-name']").sendKeys(filterName);
    saveFilterDialog.$("button[id$='save-filter-form:save-widget-filter-button']").shouldBe(getClickableCondition()).click();
    saveFilterDialog.waitUntil(disappears, DEFAULT_TIMEOUT);
    getSavedFilterContainer().$(".saved-filter__items").waitUntil(appear, DEFAULT_TIMEOUT)
      .$$(".saved-filter-node").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
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
     return $("[id$='" + selectSavedFilterId + "']").waitUntil(Condition.enabled, DEFAULT_TIMEOUT);
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
    $("[id$='manage-filter-dialog']").waitUntil(appear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getManageFilterDialog() {
    return $("[id$='manage-filter-dialog']").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public int getTotalSavedFilterInManageFilterDialog() {
    var deleteSavedFilterForm = $("#delete-saved-filter-form").waitUntil(appear, DEFAULT_TIMEOUT);
    return deleteSavedFilterForm.$(".ui-datatable-data").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$(".saved-filter-selection-column").size();
  }

  public void deleteFirstSavedFilter() {
    var deleteSavedFilterForm = $("#delete-saved-filter-form").waitUntil(appear, DEFAULT_TIMEOUT);
    $("tr[data-rk='Tasks Filterset 4']").waitUntil(appear, DEFAULT_TIMEOUT);
    var totalWidgetFilter = getDelelteSavedFilterRow().size();
    deleteSavedFilterForm.$(".ui-datatable-data")
        .$$(".saved-filter-selection-column").first()
        .shouldBe(getClickableCondition()).click();
    var removeButton = getDeleteWidgetFilterButton().waitUntil(Condition.enabled, DEFAULT_TIMEOUT);
    removeButton.shouldBe(getClickableCondition()).click();
    getDelelteSavedFilterRow().shouldHave(CollectionCondition.sizeLessThan(totalWidgetFilter));
  }

  private ElementsCollection getDelelteSavedFilterRow() {
    return $("[id$='delete-saved-filter-form:quick-filter-table']").waitUntil(appear, DEFAULT_TIMEOUT)
    .$$("tbody tr");
  }

  public void clickOnResetFilter() {
    $("[id$=':reset-button']").waitUntil(Condition.enabled, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$=':saved-filters-container']").waitUntil(disappears, DEFAULT_TIMEOUT);
  }

  public int getNumberOfFilterApplied() {
    var numberNoti = $("[id$='task-task_1:task-panel-group-0']").waitUntil(appear, DEFAULT_TIMEOUT)
          .$(".widget__filter-noti-number").waitUntil(appear, DEFAULT_TIMEOUT).getText();
    return Integer.valueOf(numberNoti);
  }

  public void searchWidgetFilter(String filterName) {
    var savedFilterPanel = getSavedFilterContainer();
    waitFirstWidgetFilterAppear(savedFilterPanel);
    var searchFilter = savedFilterPanel.$("[id$=':search-saved-filter-input']").waitUntil(Condition.visible, DEFAULT_TIMEOUT);
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
    savedFilterPanel.$(".saved-filter__content").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("[id$=':saved-filter-node']").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getSavedFilterContainer() {
    return $("[id$=':saved-filters-container']").waitUntil(appear, DEFAULT_TIMEOUT);
  }
  
  private SelenideElement getDeleteWidgetFilterButton() {
    return $("button[id$='delete-saved-filter-form:delete-widget-filter-btn']");
  }

  public void openTask(String taskName) {
    $("div[id$=':task-component:dashboard-tasks']").waitUntil(appear, DEFAULT_TIMEOUT)
      .$$("table tbody tr td span").filter(text(taskName)).first().click();
  }
  
  private SelenideElement getStateFilterCheckBox(String value) {
    return $("div[id$='states_panel']").$("div.ui-selectcheckboxmenu-items-wrapper").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }
  
  private SelenideElement getCloseStateFilter() {
    return $("div[id$='states_panel']").waitUntil(appear, DEFAULT_TIMEOUT).$("a.ui-selectcheckboxmenu-close");
  }
  
  private SelenideElement getFilterCheckBox(String inputField) {
    return $("div[id$='widget-filter-content']").waitUntil(appear, DEFAULT_TIMEOUT)
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
        .waitUntil(appear, DEFAULT_TIMEOUT).$("div.ui-overlaypanel-content").$$("a[class*='option-item']");
  }

  public void clickOnTaskActionLink(int taskIndex) {
    getColumnOfTaskHasIndex(taskIndex, "Actions").shouldBe(getClickableCondition()).click();
  }
  
  public void reserveTask(int taskIndex) {
    getActiveTaskActions(taskIndex).filter(text("Reserve")).first().shouldBe(getClickableCondition()).click();
  }
  
  public void clickCancelTask() {
    $("a[id$='button-cancel']").shouldBe(getClickableCondition()).click();
  }
  
  public void triggerEscalationTask(int taskIndex) {
    getActiveTaskActions(taskIndex).filter(text("Trigger Escalation")).first().shouldBe(getClickableCondition()).click();
    $("div[id='escalation-task-confirmation-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    $("button[id='confirm-escalation-dashboard-tasks']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }
}
