package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class TaskEditWidgetNewDashBoardPage extends TemplatePage {

  private String taskEditWidgetId;
  private static final String TASK_NAME = "Task name";
  private static final String STATE = "State";
  private static final String CUSTOM_CASE_FIELD = "Custom case field";
  private static final String CUSTOM_FIELD = "Custom field";

  public TaskEditWidgetNewDashBoardPage() {
    this("div[id='new-widget-configuration-dialog']");
  }

  public TaskEditWidgetNewDashBoardPage(String taskWidgetId) {
    this.taskEditWidgetId = taskWidgetId;
  }

  @Override
  protected String getLoadedLocator() {
    return ".task-configuration__responsibles";
  }

  private SelenideElement widgetTitle() {
    return $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("span[id$='widget-title-group']")
        .$("input[id$='widget-title']");
  }

  public void changeWidgetTitle(String name) {
    widgetTitle().clear();
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
    getAvailableFilterInput(TASK_NAME).sendKeys(taskName);
  }
  
  public void clickOnStateToShowDropdown() {
    int index = getIndexFiltertByName(STATE);
    $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='user-filter']").$$("div").get(index + 1)
        .click();
  }
  
  private SelenideElement getValueOfCheckBox(String value) {
    return $("div[style*='display: block;'] div.ui-selectcheckboxmenu-items-wrapper")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }
  
  private SelenideElement getCloseCheckBox() {
    return $("div.ui-selectcheckboxmenu-panel[style*='display: block;']").shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-selectcheckboxmenu-close");
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
    .shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-paginator-next").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }
  
  public void waitPageSelected(int pageNumber) {
    $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='widget-preview']")
    .shouldBe(appear, DEFAULT_TIMEOUT).$$("a.ui-paginator-page").get(pageNumber-1).shouldBe(Condition.attributeMatching("class", ".*ui-state-active.*"), DEFAULT_TIMEOUT);
  }
  
  public void save() {
    $(taskEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='widget-configuration-save-button']")
        .shouldBe(getClickableCondition()).click();
    $("[id$='task-component:loading']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void waitPreviewTableLoaded() {
    $(taskEditWidgetId).$("div[id$=':dashboard-tasks-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getAddLanguageButton() {
    SelenideElement addLanguageButton = $("button[id$='add-language-button']");
    addLanguageButton.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    addLanguageButton.shouldBe(getClickableCondition());
    waitUntilElementToBeClickable(addLanguageButton);
    return addLanguageButton;
  }

  public SelenideElement getMultipleLanguageDialog() {
    SelenideElement addLanguageButton = $("div[id$='multiple-languages-dialog']");
    addLanguageButton.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return addLanguageButton;
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
    $("div[id$='column-management-form:field-type-selection_panel'] li[data-label='" + type + "']").click();
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

  public SelenideElement openColumnManagementDialog() {
    $("div[id$='task-widget-preview:dashboard-tasks-container']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("a[id$='column-toggler']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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

  public SelenideElement getColumnManagementDialog() {
    return $("div[id$='column-management-dialog']");
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
  
  public SelenideElement getQuickSearchCheckBox() {
    return $("span[id$='quick-search-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("div[id$='quick-search']")
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
  
  private SelenideElement getCustomCaseFieldSelection() {
    return getColumnManagementDialog().$("span[id$='custom-case-field-selection'] button");
  }
  
  public void saveColumn() {
    getColumnManagementDialog().$("button[id$='column-management-save-btn']").click();
    $("div[id$=':column-management-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
    waitPreviewTableLoaded();
  }
}
