package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.axonivy.portal.selenium.common.ComplexFilterHelper;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class CaseEditWidgetNewDashBoardPage extends TemplatePage {

  private static final String FILTER_CASE_STATE = "State";
  private static final String FILTER_CASE_NAME = "Case name";

  private String caseEditWidgetId;

  public CaseEditWidgetNewDashBoardPage() {
    this("div[id='new-widget-configuration-dialog']");
  }

  public CaseEditWidgetNewDashBoardPage(String caseWidgetId) {
    this.caseEditWidgetId = caseWidgetId;
  }

  private SelenideElement getValueOfCheckBox(String value) {
    return $("div.ui-selectcheckboxmenu-items-wrapper").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }

  private SelenideElement getCloseCheckBox() {
    return $("div.ui-selectcheckboxmenu-panel").shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-selectcheckboxmenu-close");
  }

  public void selectStateAsOpen() {
    getValueOfCheckBox("Open").shouldBe(getClickableCondition()).click();
    getCloseCheckBox().shouldBe(getClickableCondition()).click();
  }

  private SelenideElement widgetTitle() {
    return $(caseEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("span[id$='widget-title-group']")
        .$("input[id$='widget-title']");
  }

  public void changeWidgetTitle(String name) {
    widgetTitle().clear();
    widgetTitle().click();
    widgetTitle().sendKeys(name);
  }

  public void applyFilter() {
    $(caseEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='preview-button']")
        .shouldBe(getClickableCondition()).click();
    $(caseEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='preview-button']").$("span[id*='ui-icon-loading]").exists();
    $(caseEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='preview-button']").shouldNotHave(Condition.attribute("disabled", "disabled"),DEFAULT_TIMEOUT);
  }

  public void openFilter() {
    $("button[id$=':show-filter']").shouldBe(getClickableCondition()).click();
    $(".filter-panel-header").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void filterCaseName(String caseName) {
    addFilter("Name", FilterOperator.IS);
    inputValueOnLatestFilter(FilterValueType.TEXT, caseName);
  }

  public void filterCaseState(String state) {
    addFilter(FILTER_CASE_STATE, null);
    inputValueOnLatestFilter(FilterValueType.STATE_TYPE, state);
  }

  private ElementsCollection getRowOfTableCasePreview() {
    return $(caseEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='case-widget-preview:dashboard-cases']")
        .$$("table tbody tr:not(.ui-datatable-empty-message)");
  }

  public ElementsCollection countCases() {
    return getRowOfTableCasePreview();
  }

  public void nextPageTable() {
    $(caseEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='case-widget-preview:dashboard-cases']")
        .$("a.ui-paginator-next").shouldBe(getClickableCondition()).click();
  }

  public void waitPreviewTableLoaded() {
    $(caseEditWidgetId).$("div[id$=':dashboard-cases-container']").shouldBe(appear, DEFAULT_TIMEOUT);
    $(caseEditWidgetId).$(".case-dashboard-widget__loading-message").shouldHave(Condition.cssClass("u-display-none"), DEFAULT_TIMEOUT);
    $(caseEditWidgetId).$("div[id$=':dashboard-cases-container']").shouldBe(appear, DEFAULT_TIMEOUT).shouldNotHave(Condition.cssClass("u-display-none"));
    $(caseEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='preview-button']").shouldNotHave(Condition.attribute("disabled", "disabled"),DEFAULT_TIMEOUT);
  }

  public void save() {
    $(caseEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id='widget-configuration-form:new-widget-configuration-component:case-widget-preview:dashboard-cases_head']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$='widget-configuration-save-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    clickByJavaScript($("button[id$='widget-configuration-save-button']"));
    $("button[id$='widget-configuration-save-button']").shouldBe(disappear, DEFAULT_TIMEOUT);
    $(caseEditWidgetId).shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement openColumnManagementDialog() {
    $("div[id$='case-widget-preview:dashboard-cases-container']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$=':manage-column']").shouldBe(getClickableCondition()).click();
    return getColumnManagementDialog().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getColumnManagementDialog() {
    return $("div[id$='column-management-dialog']");
  }

  public void selectCustomType() {
    selectFieldType("Custom field");
    getCustomFieldCategory().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectStandardType() {
    selectFieldType("Standard column");
    getCustomFieldCategory().shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getCustomFieldCategory() {
    return getColumnManagementDialog()
        .$("input[id$=':column-management-form:custom-field-categories-selection_input']");
  }

  public void selectFieldType(String type) {
    getColumnManagementDialog().$("div[id$='field-type-selection'] span.ui-icon-triangle-1-s")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(Condition.enabled, DEFAULT_TIMEOUT).click();
    waitForElementClickableThenClick(
        $("div[id$='column-management-form:field-type-selection_panel'] li[data-label='" + type + "']"));
  }

  public void removeAddedField(String field) {
    SelenideElement removeLink = getAddedFieldRemoveLink(field);
    removeLink.click();
    removeLink.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getAddedFieldRemoveLink(String field) {
    return getColumnManagementDialog().$("tbody td.js-column-field-" + field + " a");
  }

  public SelenideElement getCustomField(String field) {
    getCustomFieldSelection().click();
    SelenideElement customFieldPanel = $("span[id$='column-management-form:custom-field-selection_panel']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return customFieldPanel.$("li[data-item-value='" + field + "']");
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

  public String addCustomColumnByName(String columnName) {
    selectCustomType();
    getCustomFieldSelection().click();
    $("span[id$='column-management-form:custom-field-selection_panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    SelenideElement result = $("span[id$='column-management-form:custom-field-selection_panel']").$$("li")
        .filter(Condition.text(columnName)).first().shouldBe(getClickableCondition());
    String field = result.getText();
    result.click();
    $("input[id$=':field-display-name'").shouldNot(Condition.empty, DEFAULT_TIMEOUT);

    getColumnManagementDialog().$("button[id$='field-add-btn']").click();
    getAddedFieldRemoveLink(field).shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    return field;
  }

  private SelenideElement getCustomFieldSelection() {
    return getColumnManagementDialog().$("span[id$='custom-field-selection'] button").shouldBe(clickable(),
        DEFAULT_TIMEOUT);
  }

  public SelenideElement getStandardField(String field) {
    getStandardFieldSelection().click();
    SelenideElement standardFieldPanel = $("div[id$='column-management-form:standard-field-selection_panel']");
    standardFieldPanel.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return standardFieldPanel.$("li[data-label='" + field + "']");
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

  public void saveColumn() {
    getColumnManagementDialog().$("button[id$='column-management-save-btn']").click();
    $("div[id$=':column-management-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getStandardFieldSelection() {
    getColumnManagementDialog().$("div[id$='standard-field-selection'] .ui-selectonemenu-trigger")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    return getColumnManagementDialog().$("div[id$='standard-field-selection'] .ui-selectonemenu-trigger");
  }

  private SelenideElement getFieldDisplayName() {
    return getColumnManagementDialog().$("input[id$='field-display-name']");
  }

  @Override
  protected String getLoadedLocator() {
    return ".widget-configuration__input-text";
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

  public SelenideElement getTranslationOverlayPanel(int index) {
    SelenideElement translationOverlay = $(String.format("div[id$=':%s:overlay-panel-input']", index));
    waitUntilElementToBeClickable(translationOverlay);
    translationOverlay.shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    return translationOverlay;
  }

  public SelenideElement getConfigurationFilter() {
    $("[id$=':widget-filter-content']").$(".filter-panel-title").click();
    $("[id$=':widget-filter-content']").scrollIntoView("{block: \"end\"}");
    return $("[id$=':widget-filter-content']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getConfigurationDialog() {
    return $("div[id='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void closeConfigurationDialog() {
    getConfigurationDialog().$(".ui-dialog-footer").$("a").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void addFilter(String columnName, FilterOperator operator) {
    ComplexFilterHelper.addFilter(columnName, operator);
  }

  public void inputValueOnLatestFilter(FilterValueType type, Object... values) {
    ComplexFilterHelper.inputValueOnLatestFilter(type, values);
  }

  public void closeFilter() {
    $("span[id$=':widget-title-group']").$("label").scrollIntoView(("{block: \"start\"}")).click();
    $("div[id$=':widget-filter-content']").shouldBe(disappear, DEFAULT_TIMEOUT);
    waitPreviewTableLoaded();
  }

  public void removeFilter(int index) {
    int currentIndex = $$("div[id$=':filter-component:filter-selection-panel']").size();
    if (currentIndex > 0) {
      String removeBtn = String.format("button[id$=':%s:filter-component:remove-filter']", index);
      $(removeBtn).shouldBe(getClickableCondition()).click();
      countFilterSelect().shouldBe(CollectionCondition.size(currentIndex - 1), DEFAULT_TIMEOUT);
    }
  }
  
  public void resetFilter() {
    $("button[id$=':reset-filter']").shouldBe(getClickableCondition()).click();
    countFilterSelect().shouldBe(CollectionCondition.size(0), DEFAULT_TIMEOUT);
  }

  public ElementsCollection countFilterSelect() {
    return $$("[id$=':filter-component:field-selection_panel']");
  }

  public void addCustomField(String fieldName) {
    selectCustomType();
    getCustomFieldSelection().click();
    SelenideElement customFieldPanel = $("span[id$='column-management-form:custom-field-selection_panel']");
    customFieldPanel.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    SelenideElement fieldElement =
        customFieldPanel.$$("li").filter(text(fieldName)).first().shouldBe(getClickableCondition());
    fieldElement.getAttribute(FILTER_CASE_NAME);
    fieldElement.click();
    getColumnManagementDialog().$("button[id$='field-add-btn']").click();
  }

  public void saveAfterAddingCustomField() {
    saveColumn();
    save();
  }

  public void addCustomColumns(String... fieldNameList) {
    openColumnManagementDialog();
    selectCustomType();
    for (String fieldName : fieldNameList) {
      addCustomField(fieldName);
    }
    saveColumn();
  }
  

  public void clickOnQuickSearchCheckBox() {
    getQuickSearchCheckBox().click();
  }

  public WebElement getQuickSearchCheckBox() {
    return $("div[id$='widget-preview']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("span[id$='quick-search-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("div[id$='quick-search']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
  }

  public void clickOnQuickSearchByField(String fieldName) {
    var quickSearchChkbox = getColumnManagementDialog().$("div[id$='column-management-datatable']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("table tbody").$$("tr").filter(text(fieldName)).first().$("div[id$='quick-search-checkbox-panel']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    quickSearchChkbox.click();
  }

  public boolean isQuickSearchClicked(String fieldName) {
    return getColumnManagementDialog().$("div[id$='column-management-datatable']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("table tbody").$$("tr").filter(text(fieldName)).first()
        .$("div[id$='quick-search-checkbox-panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("a").$("span span")
        .getAttribute("class").contains("ui-chkbox-icon");
  }
  

  public boolean isQuickSearchInputShow(String widgetIndex) {
    String taskWidgetIndex = String.format("div[id*='case-case_%s']", widgetIndex);
    waitPageLoaded();
    return $(taskWidgetIndex).$("form").$("input").exists();
  }

  public String getQuickSearchInput() {
    return getQuickSearchForm().$("input").getValue();
  }

  public void setInputForQuickSearch(String input) {
    getQuickSearchForm().$("input").sendKeys(input);
    waitForPageLoad();
  }

  private SelenideElement getQuickSearchForm() {
    return $("div[class*='widget-header-quick-search']").shouldBe(appear, DEFAULT_TIMEOUT).$("form");
  }

  public void clearQuickSearchInput() {
    getQuickSearchForm().$("input").clear();
    waitForPageLoad();
  }

  public boolean isEmptyMessageAppear() {
    return $("div[id$='empty-message-container'][class='empty-message-container ']").shouldBe(appear, DEFAULT_TIMEOUT)
        .isDisplayed();
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
  
  public void resizeColumn() {
    ElementsCollection elements = $("[id=\"task-task_1:task-component:dashboard-tasks:dashboard-tasks-columns:1\"]")
    .$$(".ui-column-resizer.ui-draggable.ui-draggable-handle");
    WebElement element = elements.get(0);
    new Actions(driver)
    .clickAndHold(element)
    .perform();
  }
  
  public void clickOnCaseNameColumn() {
    $("div[id$='case-widget-preview:dashboard-cases']").shouldBe(appear, DEFAULT_TIMEOUT)
    .$("th[id$='dashboard-cases-columns:1']").shouldBe(getClickableCondition()).click();
  } 
  
  public SelenideElement getFirstCaseOfCaseWidget() {
    $("div[id$='case-widget-preview:dashboard-cases']").shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr").get(0).shouldBe(appear,
        DEFAULT_TIMEOUT);
    return $("div[id$='case-widget-preview:dashboard-cases']").$$("table tbody tr").get(0);
  }

}
