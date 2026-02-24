package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.ComplexFilterHelper;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class TaskEditWidgetNewDashBoardPage extends TemplatePage {

  private static final String FILTER_TASK_NAME = "Task name";
  private String taskEditWidgetId;
  private static final String CUSTOM_CASE_FIELD = "Custom case field";
  private static final String CUSTOM_FIELD = "Custom field";
  private static final String STATE = "State";

  public TaskEditWidgetNewDashBoardPage() {
    this("div[id='new-widget-configuration-dialog']");
  }

  public TaskEditWidgetNewDashBoardPage(String taskWidgetId) {
    this.taskEditWidgetId = taskWidgetId;
  }

  @Override
  protected String getLoadedLocator() {
    return ".widget-configuration__input-text";
  }

  private SelenideElement widgetTitle() {
    return $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("span[id$='widget-title-group']")
        .$("input[id$='widget-title']");
  }

  public void changeWidgetTitle(String name) {
    widgetTitle().clear();
    widgetTitle().click();
    widgetTitle().sendKeys(name);
  }

  private int getIndexFiltertByName(String name) {
    ElementsCollection elementsTH =
        $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='user-filter']").$$("div");
    for (int i = 0; i < elementsTH.size(); i++) {
      if (elementsTH.get(i).getText().equalsIgnoreCase(name)) {
        return i;
      }
    }
    return 0;
  }

  private SelenideElement getAvailableFilterInput(String filterName) {
    int index = getIndexFiltertByName(filterName);
    return $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='user-filter']").$$("div").get(index + 1)
        .$("input");
  }

  public void filterTaskName(String taskName) {
    getAvailableFilterInput(FILTER_TASK_NAME).sendKeys(taskName);
  }

  public void clickOnStateToShowDropdown() {
    int index = getIndexFiltertByName(STATE);
    $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='user-filter']").$$("div").get(index + 1).click();
  }

  private SelenideElement getValueOfCheckBox(String value) {
    return $("div[style*='display: block;'] div.ui-selectcheckboxmenu-items-wrapper").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }

  private SelenideElement getCloseCheckBox() {
    return $("div.ui-selectcheckboxmenu-panel[style*='display: block;']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("a.ui-selectcheckboxmenu-close");
  }

  public void selectState(String state) {
    getValueOfCheckBox(state).shouldBe(getClickableCondition()).click();
    getCloseCheckBox().shouldBe(getClickableCondition()).click();
  }

  public void preview() {
    $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='preview-button']")
        .shouldBe(getClickableCondition()).click();
  }

  private ElementsCollection getColumnsOfTableWidget() {
    return $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr td");
  }

  private ElementsCollection getAllTasksOfTaskWidget() {
    return getColumnsOfTableWidget().filter(Condition.cssClass("dashboard-tasks__name"));
  }

  public ElementsCollection countAllTasks() {
    return getAllTasksOfTaskWidget();
  }


  public void nextPageTable() {
    $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='widget-preview']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-paginator-next").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
  }

  public void waitPageSelected(int pageNumber) {
    $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='widget-preview']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("a.ui-paginator-page").get(pageNumber - 1)
        .shouldBe(Condition.attributeMatching("class", ".*ui-state-active.*"), DEFAULT_TIMEOUT);
  }

  public void save() {
    $("[id='widget-configuration-form:new-widget-configuration-component:task-widget-preview:dashboard-tasks_head']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='widget-configuration-save-button']")
        .shouldBe(getClickableCondition()).click();
    $("button[id$='widget-configuration-save-button']").shouldBe(disappear, DEFAULT_TIMEOUT);
    $("[id$='task-component:loading']").shouldBe(disappear, DEFAULT_TIMEOUT);
    $(taskEditWidgetId).shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void waitPreviewTableLoaded() {
    $(taskEditWidgetId).$(".task-dashboard-widget__loading-message").shouldHave(Condition.cssClass("hidden"), DEFAULT_TIMEOUT);
    $(taskEditWidgetId).$("div[id$=':dashboard-tasks-container']").shouldBe(appear, DEFAULT_TIMEOUT).shouldNotHave(Condition.cssClass("hidden"));
  }

  public SelenideElement getAddLanguageButton() {
    SelenideElement addLanguageButton = $("button[id$='add-language-button']");
    addLanguageButton.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    addLanguageButton.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    waitUntilElementToBeClickable(addLanguageButton);
    return addLanguageButton;
  }

  public SelenideElement getMultipleLanguageDialog() {
    SelenideElement addLanguageButton = $("div[id$='multiple-languages-dialog']");
    addLanguageButton.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return addLanguageButton;
  }

  public SelenideElement getTranslationOverlayPanel(int index) {
    SelenideElement translationOverlay = $(String.format("div[id$=':%s:overlay-panel-input']", index));
    waitUntilElementToBeClickable(translationOverlay);
    translationOverlay.shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    return translationOverlay;
  }

  public WebElement getConfigurationFilter() {
    return $("[id='widget-configuration-form:new-widget-configuration-component:widget-filter-content']").shouldBe(appear,
        DEFAULT_TIMEOUT);
  }

  public SelenideElement getColumnManagementDialog() {
    return $("div[id$='column-management-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void removeAddedField(String field) {
    SelenideElement removeLink = getAddedFieldRemoveLink(field);
    removeLink.click();
    removeLink.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getAddedFieldRemoveLink(String field) {
    return getColumnManagementDialog().$("tbody td.js-column-field-" + field + " a").shouldBe(getClickableCondition(),
        DEFAULT_TIMEOUT);
  }

  public void saveColumn() {
    getColumnManagementDialog().$("button[id$='column-management-save-btn']").click();
    $("div[id$=':column-management-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
    waitPreviewTableLoaded();
  }

  private SelenideElement getMultiLanguageDialogWhenAddWidget() {
    return $(
        "div[id='widget-configuration-form:new-widget-configuration-component:title-language-config:multiple-languages-dialog']");
  }

  public WebElement openMultiLanguageDialogWhenAddWidget() {
    getAddLanguageButton().click();
    getMultiLanguageDialogWhenAddWidget().shouldBe(Condition.appear, DEFAULT_TIMEOUT).$(".ui-dialog-title")
        .shouldBe(appear, DEFAULT_TIMEOUT).click();
    return getMultiLanguageDialogWhenAddWidget();
  }

  public void cancelMultiLanguageDialogWhenAddWidget() {
    $("a[id$=':multi-language-cancel-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getMultiLanguageDialogWhenAddWidget().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getConfigurationDialog() {
    $("div[id='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("[id='new-widget-configuration-dialog_title']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return $("div[id='new-widget-configuration-dialog']");
  }

  public void closeConfigurationDialog() {
    getConfigurationDialog().$(".ui-dialog-footer").$("a").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getAddingFieldColumnType() {
    return $(
        "[id='widget-configuration-form:new-widget-configuration-component:column-management-component:column-management-form:field-type-selection_label']")
            .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getStandardFieldSelection() {
    getColumnManagementDialog().$("div[id$='standard-field-selection'] .ui-selectonemenu-trigger")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    return getColumnManagementDialog().$("div[id$='standard-field-selection'] .ui-selectonemenu-trigger");
  }

  public void selectCustomCaseType() {
    selectFieldType("Custom case field");
    getCustomCaseFieldCategory().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectStandardType() {
    selectFieldType("Standard column");
    getCustomFieldCategory().shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public String addFirstStandardField() {
    getStandardFieldSelection().click();
    SelenideElement standardFieldPanel = $("div[id$='column-management-form:standard-field-selection_panel']");
    standardFieldPanel.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    SelenideElement firstFieldElement = standardFieldPanel.$("li").shouldBe(getClickableCondition());
    String field = firstFieldElement.getText();
    firstFieldElement.click();

    getFieldDisplayName().clear();
    getFieldDisplayName().sendKeys(field);

    getColumnManagementDialog().$("button[id$='field-add-btn']").click();

    getAddedFieldRemoveLink(field).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return field;
  }

  private SelenideElement getFieldDisplayName() {
    return getColumnManagementDialog().$("input[id$='field-display-name']");
  }

  public void selectFieldType(String type) {
    getColumnManagementDialog().$("div[id$='field-type-selection'] span.ui-icon-triangle-1-s")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(Condition.enabled, DEFAULT_TIMEOUT).click();
    waitForElementClickableThenClick(
        $("div[id$='column-management-form:field-type-selection_panel'] li[data-label='" + type + "']"));
  }

  public SelenideElement getCustomField(String field) {
    getCustomFieldSelection().click();
    SelenideElement customFieldPanel = $("span[id$='column-management-form:custom-field-selection_panel']");
    customFieldPanel.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return customFieldPanel.$("li[data-item-value='" + field + "']");
  }

  private SelenideElement getCustomFieldSelection() {
    return getColumnManagementDialog().$("span[id$='custom-field-selection'] button");
  }
  
  private SelenideElement getCustomCaseFieldSelection() {
    return getColumnManagementDialog().$("span[id$='custom-case-field-selection'] button");
  }

  public SelenideElement openColumnManagementDialog() {
    $("div#new-widget-configuration-dialog").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='new-widget-configuration-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("button[id$='manage-column']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return getColumnManagementDialog().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String addFirstCustomField() {
    selectCustomType();
    getCustomFieldSelection().click();
    SelenideElement customFieldPanel = $("span[id$='column-management-form:custom-field-selection_panel']");
    customFieldPanel.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    SelenideElement firstFieldElement = customFieldPanel.$("li").shouldBe(getClickableCondition());
    String field = firstFieldElement.getText();
    firstFieldElement.click();

    getColumnManagementDialog().$("button[id$='field-add-btn']").click();

    getAddedFieldRemoveLink(field).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return field;
  }

  private SelenideElement getCustomFieldCategory() {
    return getColumnManagementDialog()
        .$("input[id$=':column-management-form:custom-field-categories-selection_input']");
  }

  private SelenideElement getCustomCaseFieldCategory() {
    return getColumnManagementDialog()
        .$("input[id$=':column-management-form:custom-case-field-categories-selection_input']");
  }

  public void selectCustomType() {
    selectFieldType("Custom field");
    getCustomFieldCategory().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getStandardField(String field) {
    getStandardFieldSelection().click();
    SelenideElement standardFieldPanel = $("div[id$='column-management-form:standard-field-selection_panel']");
    standardFieldPanel.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return standardFieldPanel.$("li[data-label='" + field + "']");
  }
  
  public void addCustomFields(String fieldName) {
    selectCustomType();
    getCustomFieldSelection().click();
    SelenideElement customFieldPanel = $("span[id$='column-management-form:custom-field-selection_panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    SelenideElement fieldElement =
        customFieldPanel.$$("li").filter(text(fieldName)).first().shouldBe(getClickableCondition());
    fieldElement.getAttribute(FILTER_TASK_NAME);
    fieldElement.click();
    getColumnManagementDialog().$("button[id$='field-add-btn']").click();
  }
  
  public void addCustomCaseFields(String fieldName) {
    selectCustomCaseType();
    getCustomCaseFieldSelection().click();
    SelenideElement customCaseFieldPanel = $("span[id$='column-management-form:custom-case-field-selection_panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    SelenideElement fieldElement =
        customCaseFieldPanel.$$("li").filter(text(fieldName)).first().shouldBe(getClickableCondition());
    fieldElement.getAttribute(FILTER_TASK_NAME);
    fieldElement.click();
    getColumnManagementDialog().$("button[id$='field-add-btn']").click();
  }
  
  public void saveAfterAddingCustomField() {
    saveColumn();
    save();
  }
  
  public void openFilter() {
    $("div#new-widget-configuration-dialog").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("button[id$=':show-filter']").shouldBe(getClickableCondition()).click();
    $("div[id$='widget-filter-content']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    waitForElementDisplayed($("div[id$='widget-filter-content']"), isDisplayed());
  }
  
  public void addFilter(String columnName, FilterOperator operator) {
    ComplexFilterHelper.addFilter(columnName, operator);
  }
  
  public void inputValueOnLatestFilter(FilterValueType type, Object... values) {
    ComplexFilterHelper.inputValueOnLatestFilter(type, values);
  }
  
  public void applyFilter() {
    $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='preview-button']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(".filter-panel-header").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
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
  
  public void addCustomColumns(String... fieldNameList) {
    openColumnManagementDialog();
    selectCustomType();
    
    for(String fieldName : fieldNameList) {
      addCustomFields(fieldName);
    }
    $("button[id$='column-management-save-btn']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }
  
  public void closeFilter() {
    $("span[id$=':widget-title-group']").$("label").scrollIntoView(("{block: \"start\"}")).click();
    $("div[id$=':widget-filter-content']").shouldBe(disappear, DEFAULT_TIMEOUT);
    waitPreviewTableLoaded();
  }
  
  public void resetFilter() {
    $("button[id$=':reset-filter']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    countFilterSelect().shouldBe(CollectionCondition.size(0), DEFAULT_TIMEOUT);
  }

  public WebElement getQuickSearchCheckBox() {
    return $("div[id$='widget-preview']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("span[id$='quick-search-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("div[id$='quick-search']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
  }

  public void clickOnQuickSearchCheckBox() {
    getQuickSearchCheckBox().click();
  }
  
  public boolean isQuickSearchClicked(String fieldName) {
    return getColumnManagementDialog().$("div[id$='column-management-datatable']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("table tbody").$$("tr").filter(text(fieldName)).first()
        .$("div[id$='quick-search-checkbox-panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("a").$("span span")
        .getAttribute("class").contains("ui-chkbox-icon");
  }
  
  public void clickOnQuickSearchByField(String fieldName) {
    var quickSeatchChkbox = getColumnManagementDialog().$("div[id$='column-management-datatable']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("table tbody").$$("tr").filter(text(fieldName)).first().$("div[id$='quick-search-checkbox-panel']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    quickSeatchChkbox.click();
  }
  
  public void clickOnVisibilityCheckBoxByField(String fieldName) {
    var visibilityCheckBox = getColumnManagementDialog().$("div[id$='column-management-datatable']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("table tbody").$$("tr").filter(text(fieldName)).first().$("a[id$='toggle-visibility']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    visibilityCheckBox.click();
  }

  public void addCustomFieldByCustomTypeAndFieldName(String customType, String fieldName) {
    switch (customType) {
    case (CUSTOM_FIELD):
      customType = "custom-field";
      selectCustomType();
      getCustomFieldSelection().click();
      break;
    case (CUSTOM_CASE_FIELD):
      customType = "custom-case-field";
      selectCustomCaseType();
      getCustomCaseFieldSelection().click();
      break;
    default:
      break;
    }
    customType.toLowerCase();
    String spanId = String.format("span[id*='%s-selection_panel']", customType);
    $(spanId).$("ul").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$$("li")
        .filter(text(fieldName)).first().click();
    getColumnManagementDialog().$("button[id$='field-add-btn']").click();
  }
  
  public SelenideElement getWidgetInfoIconCheckbox() {
    return getWidgetConfigurationForm().$("span[id$='widget-info-icon-group']").shouldBe(Condition.appear,
        DEFAULT_TIMEOUT).$("div[class*='ui-inputgroup']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("div[id$='widget-info']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("div[class*='ui-chkbox-box']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("span");
  }

  public void clickOnWidgetInfoIconCheckbox() {
    getWidgetInfoIconCheckbox().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getWidgetConfigurationForm() {
    return $("div#new-widget-configuration-dialog").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("form#widget-configuration-form").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getExpandModeCheckbox() {
    return getWidgetConfigurationForm().$("span[id$='fullscreen-mode-group']").shouldBe(Condition.appear,
        DEFAULT_TIMEOUT).$("div[class*='ui-inputgroup']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("div[id$='fullscreen-mode']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void clickOnExpandModeCheckbox() {
    getExpandModeCheckbox().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  } 
  
  public void clickOnTaskNameColumn() {
    $("div[id$='task-widget-preview:dashboard-tasks']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("th[id$='dashboard-tasks-columns:3']").shouldBe(getClickableCondition()).click();
  }
  
  public void clickOnTaskPriorityColumn() {
    $("div[id$='task-widget-preview:dashboard-tasks']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("th[id$='dashboard-tasks-columns:1']").shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getFirstTaskOfTaskWidget() {
    $("div[id$='task-widget-preview:dashboard-tasks']").shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr").get(0).shouldBe(appear,
        DEFAULT_TIMEOUT);
    return $("div[id$='task-widget-preview:dashboard-tasks']").$$("table tbody tr").get(0);
  }

}