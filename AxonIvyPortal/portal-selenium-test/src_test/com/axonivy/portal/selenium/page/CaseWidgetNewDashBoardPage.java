package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class CaseWidgetNewDashBoardPage extends TemplatePage {

  private static final String YOUR_CASES_WIDGET = "Your Cases";
  private static final String FILTER_CASE_NAME = "Case name";
  private static final String FILTER_CASE_STATE = "State";

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

  private int getIndexWidgetByColumn(String columnName) {
    ElementsCollection elementsTH = $(caseWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$$("table thead tr th");
    for (int i = 0; i < elementsTH.size(); i++) {
      if (elementsTH.get(i).getText().equalsIgnoreCase(columnName)) {
        return i;
      }
    }
    return 0;
  }

  private ElementsCollection getColumnsOfTableWidget() {
    return $(caseWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$$("table tbody tr td");
  }

  private ElementsCollection getColumnOfTableWidget(int rowIndex) {
    return $(caseWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$$("table tbody tr ").get(rowIndex).$$("td");
  }

  public ElementsCollection expand() {
    return $$("div.widget__header").filter(text(caseWidgetName));
  }

  private ElementsCollection getCasesOfCaseWidgetHasName(String caseName) {
    return getColumnsOfTableWidget().filter(text(caseName));
  }

  private SelenideElement getCaseOfCaseWidgetHasIndex(int index) {
    return getColumnsOfTableWidget().get(index);
  }

  public CaseDetailsWidgetNewDashBoardPage openDetailsCase(String caseName) {
    getCasesOfCaseWidgetHasName(caseName).first().shouldBe(getClickableCondition()).click();
    return new CaseDetailsWidgetNewDashBoardPage();
  }

  public ElementsCollection countCases(String caseName) {
    return getCasesOfCaseWidgetHasName(caseName);
  }

  public CaseDetailsWidgetNewDashBoardPage openDetailsFirstCase() {
    getCaseOfCaseWidgetHasIndex(0).shouldBe(getClickableCondition()).click();
    return new CaseDetailsWidgetNewDashBoardPage();
  }

  private SelenideElement getColumnOfCaseHasIndex(int index, String columnName) {
    int startIndex = getIndexWidgetByColumn(columnName);
    return getColumnOfTableWidget(index).get(startIndex);
  }

  public SelenideElement stateOfFirstCase() {
    return getColumnOfCaseHasIndex(0, "state");
  }

  public void openFilterWidget() {
    $$("form.table-widget-form").filter(text(caseWidgetName)).first().waitUntil(appear, DEFAULT_TIMEOUT)
        .$("a.widget__filter-sidebar-link").shouldBe(getClickableCondition()).click();
  }

  public CaseEditWidgetNewDashBoardPage openEditWidget() {
    $$("form.table-widget-form div.widget__header").filter(text(caseWidgetName)).first().waitUntil(appear, DEFAULT_TIMEOUT)
        .$("a[id*='edit-widget']").shouldBe(getClickableCondition()).click();
    return new CaseEditWidgetNewDashBoardPage();
  }

  private SelenideElement getFilterInput(String inputField) {
    return $("div[id$='widget-filter-content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("div.widget-filter-panel div.ui-g").filter(text(inputField)).first().$("input.ui-inputfield");
  }

  public void filterCaseName(String input) {
    getFilterInput(FILTER_CASE_NAME).sendKeys(input);
  }

  public void clearFilterCaseName() {
    getFilterInput(FILTER_CASE_NAME).clear();
  }

  public void filterCaseState() {
    getFilterCheckBox(FILTER_CASE_STATE).shouldBe(getClickableCondition()).click();
  }

  public void selectStateAsDone() {
    getValueOfCheckBox("Done").shouldBe(getClickableCondition()).click();
    getCloseCheckBox().shouldBe(getClickableCondition()).click();
  }

  private SelenideElement getFilterCheckBox(String inputField) {
    return $("div[id$='widget-filter-content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("div.widget-filter-panel div.ui-g").filter(text(inputField)).first();
  }

  private SelenideElement getCloseCheckBox() {
    return $("div.ui-selectcheckboxmenu-panel").waitUntil(appear, DEFAULT_TIMEOUT).$("a.ui-selectcheckboxmenu-close");
  }

  private SelenideElement getValueOfCheckBox(String value) {
    return $("div.ui-selectcheckboxmenu-items-wrapper").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }

  public void applyFilter() {
    $("div.filter-overlay-panel__footer").waitUntil(appear, DEFAULT_TIMEOUT).$$("button[id$='apply-button']")
        .filter(text("Apply")).first().shouldBe(getClickableCondition()).click();
  }

  public void nextPageTable() {
    $$("form.table-widget-form").filter(text(caseWidgetName)).first().$("a.ui-paginator-next")
        .shouldBe(getClickableCondition()).click();
  }


}
