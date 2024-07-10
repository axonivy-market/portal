package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.axonivy.portal.selenium.common.ComplexFilterHelper;
import com.axonivy.portal.selenium.common.FilterOperator;
import com.axonivy.portal.selenium.common.FilterValueType;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class CaseWidgetNewDashBoardPage extends TemplatePage {

  private static final String YOUR_CASES_WIDGET = "Your Cases";

  private String caseWidgetId;
  private String caseWidgetName;

  public CaseWidgetNewDashBoardPage() {
    this("div[id$='dashboard-cases']", YOUR_CASES_WIDGET);
  }

  public CaseWidgetNewDashBoardPage(String caseWidgetName) {
    this("div[id$='dashboard-cases']", caseWidgetName);
  }

  public CaseWidgetNewDashBoardPage(String caseWidgetId, String caseWidgetName) {
    this.caseWidgetId = caseWidgetId;
    this.caseWidgetName = caseWidgetName;
  }

  @Override
  protected String getLoadedLocator() {
    return "[id$='dashboard-cases-container']";
  }

  private int getIndexWidgetByColumn(String columnName) {
    ElementsCollection elementsTH = $(caseWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table thead tr th");
    for (int i = 0; i < elementsTH.size(); i++) {
      if (elementsTH.get(i).getText().equalsIgnoreCase(columnName)) {
        return i;
      }
    }
    return 0;
  }

  private int getIndexWidgetByColumnScrollable(String columnName) {
    ElementsCollection elementsTH =
        $(caseWidgetId).$(".ui-datatable-scrollable-header").shouldBe(appear, DEFAULT_TIMEOUT).$$("table thead tr th");
    for (int i = 0; i < elementsTH.size(); i++) {
      if (elementsTH.get(i).getAttribute("aria-label").equalsIgnoreCase(columnName)) {
        return i;
      }
    }
    return 0;
  }

  private ElementsCollection getColumnsOfTableWidget() {
    return $(caseWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr td");
  }

  private ElementsCollection getColumnOfTableWidget(int rowIndex) {
    return $(caseWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr ").get(rowIndex).$$("td");
  }

  public ElementsCollection expand() {
    return $$("div.widget__header").filter(text(caseWidgetName));
  }

  private ElementsCollection getCasesOfCaseWidgetHasName(String caseName) {
    return getColumnsOfTableWidget().filter(Condition.cssClass("dashboard-cases__name")).filter(text(caseName));
  }

  private SelenideElement getCaseOfCaseWidgetHasIndex(int index) {
    return getColumnsOfTableWidget().get(index);
  }

  public CaseDetailsPage openDetailsCase(String caseName) {
    getCasesOfCaseWidgetHasName(caseName).first().shouldBe(getClickableCondition()).click();
    return new CaseDetailsPage();
  }

  public ElementsCollection countCases(String caseName) {
    return getCasesOfCaseWidgetHasName(caseName);
  }

  public CaseDetailsPage openDetailsFirstCase() {
    getCaseOfCaseWidgetHasIndex(0).shouldBe(getClickableCondition()).click();
    return new CaseDetailsPage();
  }

  private SelenideElement getColumnOfCaseHasIndex(int index, String columnName) {
    int startIndex = getIndexWidgetByColumn(columnName);
    return getColumnOfTableWidget(index).get(startIndex);
  }

  private SelenideElement getColumnOfCaseHasActionIndex(int index, String columnName) {
    int startIndex = getIndexWidgetByColumnScrollable(columnName);
    return getColumnOfTableWidget(index).get(startIndex).$("span a");
  }

  public SelenideElement stateOfFirstCase() {
    return getColumnOfCaseHasIndex(0, "state");
  }

  public void openFilterWidget() {
    $$("div.table-widget-panel").filter(text(caseWidgetName)).first().shouldBe(appear, DEFAULT_TIMEOUT)
        .$(".widget__filter-sidebar-link")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$=':widget-saved-filters-items").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getEditWidgetLink() {
    return $$("div.table-widget-panel div.widget__header")
        .filter(text(caseWidgetName)).first().shouldBe(appear, DEFAULT_TIMEOUT)
        .$("div[id$='widget-header-actions']").$("[id*='edit-widget']");
  }

  public CaseEditWidgetNewDashBoardPage openEditWidget() {
    getEditWidgetLink().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(getEditWidgetLink());
    return new CaseEditWidgetNewDashBoardPage();
  }

  public void filterCaseName(String input) {
    addFilter("Name", FilterOperator.IS);
    inputValueOnLatestFilter(FilterValueType.TEXT, input);
  }

  public void selectStateAsDone() {
    getValueOfCheckBox("Done").shouldBe(getClickableCondition()).click();
    getCloseCheckBox().shouldBe(getClickableCondition()).click();
  }

  public void selectStateAsOpen() {
    getValueOfCheckBox("Open").shouldBe(getClickableCondition()).click();
    getCloseCheckBox().shouldBe(getClickableCondition()).click();
  }

  private SelenideElement getCloseCheckBox() {
    return $("div.ui-selectcheckboxmenu-panel").shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-selectcheckboxmenu-close");
  }

  private SelenideElement getValueOfCheckBox(String value) {
    return $("div.ui-selectcheckboxmenu-items-wrapper").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }

  public void applyFilter() {
    $("div.filter-overlay-panel__footer").shouldBe(appear, DEFAULT_TIMEOUT).$$("button[id$='apply-button']")
        .filter(text("Apply")).first().shouldBe(getClickableCondition()).click();
    $("[id$='case-case_1:filter-overlay-panel-1']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void nextPageTable() {
    $$("div.table-widget-panel").filter(text(caseWidgetName)).first().$("a.ui-paginator-next")
        .shouldBe(getClickableCondition()).click();
  }

  public void resetFilter() {
    $("div.filter-overlay-panel__footer").shouldBe(appear, DEFAULT_TIMEOUT).$$("button[id$='reset-button']")
        .filter(text("Reset")).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div.filter-overlay-panel__footer").shouldBe(disappear, DEFAULT_TIMEOUT);
    waitForElementClickable($$("div.table-widget-panel")
        .filter(text(caseWidgetName)).first().shouldBe(appear, DEFAULT_TIMEOUT)
        .$(".widget__filter-sidebar-link"));
    
  }

  public void selectState(String state) {
    getValueOfCheckBox(state).shouldBe(getClickableCondition()).click();
    getCloseCheckBox().shouldBe(getClickableCondition()).click();
  }

  public void clickOnCaseActionLink(int caseIndex) {
    getColumnOfCaseHasActionIndex(caseIndex, "Actions").shouldBe(getClickableCondition()).click();
  }

  public void turnOffActionsPanel(int caseIndex) {
    $$("div.widget__header").filter(text(caseWidgetName)).first().shouldBe(getClickableCondition()).click();
    $$(String.format("div.js-case-side-steps-panel-case_1-%d", caseIndex)).first().shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getActiveCaseActions(int caseIndex) {
    clickOnCaseActionLink(caseIndex);
    return $$(String.format("div.js-case-side-steps-panel-case_1-%d", caseIndex)).filter(appear).first()
        .shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-overlaypanel-content").$$("a[class*='action-step-item']");
  }

  public void destroyCase(int caseIndex) {
    getActiveCaseActions(caseIndex).filter(text("Destroy")).first().shouldBe(getClickableCondition()).click();
    confirmDestroy();
  }

  private void confirmDestroy() {
    $("div[id$='destroy-case-confirmation-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("button[id$='confirm-destruction-dashboard-cases']").shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getCreatorAvatar() {
    return $(".dashboard-cases__creator > .has-avatar > .ui-avatar").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void deleteCaseWidget() {
    $$("div.table-widget-panel div.widget__header").filter(text(caseWidgetName)).first()
        .shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='widget-header-actions']").$("[id*='delete-widget']")
        .shouldBe(getClickableCondition()).click();
  }

  public void clickExportExcel() {
    expand().first().$(".widget__info-sidebar-link").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
    $("div.info-overlay-panel__footer").$(".dashboard-excel-export-form").$("a").shouldBe(getClickableCondition())
        .click();
  }

  public void clickOnCustomActionButton(int rowIndex, String columnName) {
    SelenideElement custom = $("a[id$=':custom-description']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    custom.shouldBe(getClickableCondition()).click();
  }

  public void addFilter(String columnName, FilterOperator operator) {
    ComplexFilterHelper.addFilter(columnName, operator);
  }

  public void inputValueOnLatestFilter(FilterValueType type, Object... values) {
    ComplexFilterHelper.inputValueOnLatestFilter(type, values);
  }

  public void changeOperator(String filterLabel, FilterOperator operator, String type) {
    String typeInput = String.format("div[id$=':%s-filter-operator-panel']", type);
    $("div[id$='widget-filter-content']").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$=':filter-container']")
        .$$("span[id$=':field-selection_label']").filter(text(filterLabel)).first().shouldBe(appear, DEFAULT_TIMEOUT);

    $(typeInput).shouldBe(getClickableCondition()).$("span[id$=':operator-selection_label']").click();

    $$("li").filter(text(operator.getValue())).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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

  public void searchFilter(String input) {
    $("div[class*='saved-filter--search-container']").$("input[id$=':search-saved-filter-input']").setValue(input);
  }

  public void removeAllFilterItems() {
    $("div[id='manage-filter-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[id$=':quick-filter-table_head_checkbox']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("button[id='delete-saved-filter-form:delete-widget-filter-btn']").click();
  }

  public void openManageFiltersDialog() {
    $("a[id*='case-case_1:filter-form-1']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  public void closeManageFilterDialog() {
    $("a[id*='delete-saved-filter-form']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  public ElementsCollection getSavedFilterItemsByFilterNameOnWidgetManagement() {
    ElementsCollection elements = $("div[id='manage-filter-dialog']").$("div.ui-datatable-scrollable-body table tbody")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("tr").filter(Condition.attribute("data-rk"));
    return elements;
  }

  public ElementsCollection getSavedFilterItems() {
    return $("div[id$=':saved-filters-container']").$("div[id$=':widget-saved-filters-items']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("span.saved-filter-node__text");
  }

  public void selectSavedFilter(String filterName) {
    getSavedFilterItems().filter(text(filterName)).first().shouldBe(getClickableCondition()).click();
  }

  public void inputValueOnColumnWidgetHeader(String columnName, String value) {
    columnName = columnName + ": activate to sort column ascending";
    $("div[id='manage-filter-dialog']").$("div[id$=':quick-filter-table']")
        .$("div.ui-datatable-scrollable-header-box table thead tr")
        .$$("th[id*='delete-saved-filter-form:quick-filter-table']")
        .filter(Condition.attribute("aria-label", columnName)).first().$("input").setValue(value);
  }

  public Integer getFilterNotiNumber() {
    String filterNotiNumber =
        $$("div.table-widget-panel").filter(text(caseWidgetName)).first().shouldBe(appear, DEFAULT_TIMEOUT)
            .$("div[id$=':widget-header-actions']").$("span[class*='widget__filter-noti-number']").getText();
    return Integer.parseInt(filterNotiNumber);
  }
  
  public void removeFocusFilterDialog() {
    $("[id$=':widget-filter-content']").$("strong").click();
    $("[id$=':widget-filter-content']").scrollIntoView("{block: \"end\"}");
  }

  public SelenideElement getConfigurationFilter() {
    return $("div[class*='filter-overlay-panel'][style*='display: block']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void clickOnFilterOperator() {
    $("div[id$='text-filter-operator-panel']").shouldBe(getClickableCondition()).click();
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
    return getCaseWidgetHeader().$("div[class*='widget-header-quick-search']").shouldBe(appear, DEFAULT_TIMEOUT).$("form");
  }

  public void clearQuickSearchInput() {
    getQuickSearchForm().$("input").clear();
    waitForPageLoad();
  }

  private SelenideElement getCaseWidgetHeader() {
    return $$("div.table-widget-panel").filter(text(caseWidgetName)).first();
  }

  public void clickOnButtonExpandCaseWidget() {
    getCaseWidgetHeader().$(".expand-link").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickOnButtonCollapseCaseWidget() {
    getCaseWidgetHeader().$(".collapse-link").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public ElementsCollection countAllCases() {
    return getAllCasesOfCaseWidget();
  }

  private ElementsCollection getAllCasesOfCaseWidget() {
    return getColumnsOfTableWidget().filter(Condition.cssClass("dashboard-cases__name"));
  }

  public boolean isEmptyMessageAppear() {
    return $("div[id$='empty-message-container'][class='empty-message-container ']").shouldBe(appear, DEFAULT_TIMEOUT)
        .isDisplayed();
  }

  public void waitTableLoaded() {
    $(getLoadedLocator()).shouldHave(Condition.cssClass("u-display-none"), DEFAULT_TIMEOUT);
    $(getLoadedLocator()).shouldNotHave(Condition.cssClass("u-display-none"), DEFAULT_TIMEOUT);
  }
  
  public boolean isExpandButtonAppear() {
    return getCaseWidgetHeader().$(".expand-link").isDisplayed();
  }
  
  public boolean isWidgetInfomationIconAppear() {
    return getCaseWidgetHeader().$(".widget__info-sidebar-link").isDisplayed();
  }
}
