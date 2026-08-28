package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;

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

  private ElementsCollection getCaseCollection() {
    return $(caseWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$(".ui-datatable-scrollable-header").shouldBe(appear, DEFAULT_TIMEOUT).$$("table thead tr th");
  }

  private int getIndexWidgetByColumnScrollable(String columnName) {
    ElementsCollection elementsTH = getCaseCollection();
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
    WaitHelper.waitForNavigation(
        () -> getCasesOfCaseWidgetHasName(caseName).first().shouldBe(getClickableCondition()).click());
    return new CaseDetailsPage();
  }

  public void clickOnCase(String caseName) {
    getCasesOfCaseWidgetHasName(caseName).first().shouldBe(getClickableCondition()).click();
  }

  public ElementsCollection countCases(String caseName) {
    return getCasesOfCaseWidgetHasName(caseName);
  }

  public CaseDetailsPage openDetailsFirstCase() {
    WaitHelper.waitForNavigation(() -> getCaseOfCaseWidgetHasIndex(0).shouldBe(getClickableCondition()).click());
    return new CaseDetailsPage();
  }

  private SelenideElement getColumnOfCaseHasIndex(int index, String columnName) {
    int startIndex = getIndexWidgetByColumn(columnName);
    return getColumnOfTableWidget(index).get(startIndex);
  }

  private SelenideElement getColumnOfCaseHasActionIndex(int index, String columnName) {
    int startIndex = getIndexWidgetByColumnScrollable(columnName);
    // The Actions cell no longer renders a plain "span a" - it's now an icon-only <button>
    // ("...dashboard-case-side-steps-menu", class "dashboard-side-steps-menu-button") that opens the
    // side-steps action panel (confirmed via the failure DOM snapshot).
    return getColumnOfTableWidget(index).get(startIndex).$("button[id$='dashboard-case-side-steps-menu']");
  }

  public SelenideElement stateOfFirstCase() {
    return getColumnOfCaseHasIndex(0, "state");
  }

  public void openFilterWidget() {
    // Same redesign as TaskWidgetNewDashBoardPage.openFilterWidget(): the filter icon is no longer a
    // standalone link in the widget header (".widget__filter-sidebar-link" is gone) - it now lives inside
    // the "..." actions menu as a "Filters" menu item. Open that menu first, then click "Filters" within
    // the specific menu panel that button opened.
    SelenideElement actionsMenuButton = getCaseWidgetHeader().$("button[id$=':actions-menu-button_button']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(actionsMenuButton);
    String menuId = actionsMenuButton.getAttribute("id").replace("_button", "_menu");
    SelenideElement actionsMenuPanel = $("[id='" + menuId + "']").shouldBe(appear, DEFAULT_TIMEOUT);
    actionsMenuPanel.$$("a.ui-menuitem-link").filter(text("Filters")).first()
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    WaitHelper.waitPageNoAnimation();
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
    // Redesign: the old ".filter-overlay-panel__footer" wrapper and "filter-overlay-panel-N" dialog id
    // are gone. The dialog is now "div.filter-dialog" (id "...:filter-dialog-N"), and its footer is
    // "div.footer-buttons-container" holding the "Reset all" link and "Apply filters" button (confirmed
    // via WidgetFilterConditions/TableWidget.xhtml source and the live failure DOM).
    SelenideElement filterDialog = getConfigurationFilter();
    filterDialog.$("div.footer-buttons-container").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("button[id$='apply-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    filterDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void nextPageTable() {
    $$("div.table-widget-panel").filter(text(caseWidgetName)).first().$("a.ui-paginator-next")
        .shouldBe(getClickableCondition()).click();
  }

  public void resetFilter() {
    // Same redesign as applyFilter(): footer now lives at "div.footer-buttons-container" inside the
    // "div.filter-dialog", and the reset trigger is an <a id="...:reset-button"> ("Reset all"). The old
    // ".widget__filter-sidebar-link" post-condition is gone too - the equivalent "back to normal" signal
    // is the widget's "..." actions menu button becoming clickable again.
    SelenideElement filterDialog = getConfigurationFilter();
    filterDialog.$("div.footer-buttons-container").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("a[id$='reset-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    filterDialog.shouldBe(disappear, DEFAULT_TIMEOUT);
    waitForElementClickable(getCaseWidgetHeader().$("button[id$=':actions-menu-button_button']"));
  }

  public void selectState(String state) {
    getValueOfCheckBox(state).shouldBe(getClickableCondition()).click();
    getCloseCheckBox().shouldBe(getClickableCondition()).click();
  }

  public void clickOnCaseActionLink(int caseIndex) {
    getColumnOfCaseHasActionIndex(caseIndex, "Actions").shouldBe(getClickableCondition()).click();
  }
  
  public SelenideElement getActionsPanelOfCase() {
    return $("div[id$=':action-steps-panel']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void clickOnCustomFieldsLink() {
    getActionsPanelOfCase().$$("a").filter(text("Custom Fields"))
      .first()
      .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
      .click();
    waitForElementDisplayed(getCaseCustomFieldsDialog(), true);
  }
  
  public SelenideElement getCaseCustomFieldsDialog() {
    return $("div[id$='case-custom-fields-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public List<String> getCaseCustomFieldNames() {
    return $$("span[id$='customFieldLabel']")
        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(0), DEFAULT_TIMEOUT)
        .asFixedIterable()
        .stream()
        .map(SelenideElement::getText)
        .collect(Collectors.toList());
  }

  public CaseDetailsPage openCaseDetailsViaAction(int index) {
    clickOnCaseActionLink(index);
    String openDetailsCommandButton = String.format("[id$=':case-item-open-detail-link']", index);
    waitForElementDisplayed(By.cssSelector(openDetailsCommandButton), true);
    WaitHelper.waitForNavigation(() -> findElementByCssSelector(openDetailsCommandButton).click());
    return new CaseDetailsPage();
  }

  public void turnOffActionsPanel(int caseIndex) {
    $$("div.widget__header").filter(text(caseWidgetName)).first().shouldBe(getClickableCondition()).click();
    $$(String.format("div.js-case-side-steps-panel-case_1-%d", caseIndex)).first().shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getActiveCaseActions(int caseIndex) {
    return getActiveCaseActions(caseIndex, "case_1");
  }
  public ElementsCollection getActiveCaseActionsInFullCaseListPage(int caseIndex) {
    return getActiveCaseActions(caseIndex, "default_case_list_dashboard_case_1");
  }
  private ElementsCollection getActiveCaseActions(int caseIndex, String widgetId) {
    clickOnCaseActionLink(caseIndex);
    return $$(String.format("div.js-case-side-steps-panel-" + widgetId
        + "-%d", caseIndex)).filter(appear).first()
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

  public void clickOnCustomActionButton() {
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
    WaitHelper.waitPageNoAnimation();

    $$("li").filter(text(operator.getValue())).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void saveFilter(String widgetFilterName) {
    // The separate "save-widget-filter-dialog" popup is gone. Saving a filter is now inline within the
    // Set Filter dialog itself: a "Save filter (optional)" section with input id "...:inline-save-filter-name"
    // and button id "...:inline-save-filter" (confirmed via TableWidget.xhtml source).
    SelenideElement filterDialog = getConfigurationFilter();
    filterDialog.$("input[id$=':inline-save-filter-name']").shouldBe(appear, DEFAULT_TIMEOUT).setValue(widgetFilterName);
    filterDialog.$("button[id$=':inline-save-filter']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$=':widget-saved-filters-items']").$$("span.saved-filter-node__text").filter(text(widgetFilterName)).first()
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void searchFilter(String input) {
    // The search field wrapper is a <span> ("saved-filter--search-container"), not a <div> - the old
    // selector's tag never matched.
    $("[class*='saved-filter--search-container']").$("input[id$=':search-saved-filter-input']").setValue(input);
  }

  public void removeAllFilterItems() {
    $("div[id='manage-filter-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[id$=':quick-filter-table_head_checkbox']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("button[id='delete-saved-filter-form:delete-widget-filter-btn']").click();
  }

  public void openManageFiltersDialog() {
    // "div#manage-filter" never matched anything in the current markup. The actual trigger is the
    // "Manage saved filters" link (class "saved-filter__manage-filter") inside the currently open filter
    // dialog's saved-filters panel (confirmed via WidgetFilter.xhtml source).
    getConfigurationFilter().$("a.saved-filter__manage-filter").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    $("div[id='manage-filter-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void closeManageFilterDialog() {
    $("div[id$='manage-filter-dialog']").shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-dialog-titlebar-close")
        .shouldBe(appear, DEFAULT_TIMEOUT).click();
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
    // The widget-header badge ("widget__filter-noti-number") no longer exists. The active-filter count is
    // now a p:tag rendered inside the "Filters" item of the "..." actions menu (rendered only when
    // count != 0, per TableWidget.xhtml source). Open the menu to read it, then close it again.
    SelenideElement actionsMenuButton = getCaseWidgetHeader().$("button[id$=':actions-menu-button_button']")
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
  
  public void removeFocusFilterDialog() {
    $("[id$=':widget-filter-content']").$("strong").click();
    $("[id$=':widget-filter-content']").scrollIntoView(ScrollIntoViewOptions.instant().block(Block.end));
  }

  public SelenideElement getConfigurationFilter() {
    // Renamed from "filter-overlay-panel" to "filter-dialog" in the redesign (PrimeFaces p:dialog,
    // appendTo="@(body)", so it's not nested under the widget - the currently visible one is found via
    // its inline "display: block" style, same as before).
    return $("div.filter-dialog[style*='display: block']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void clickOnFilterOperator() {
    $("div[id$='text-filter-operator-panel']").shouldBe(getClickableCondition()).click();
    Sleeper.sleep(300);
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
    WaitHelper.waitPageNoAjaxAndAnimation();
    $(getLoadedLocator()).shouldNotHave(Condition.cssClass("hidden"), DEFAULT_TIMEOUT);
  }
  
  public boolean isExpandButtonAppear() {
    WaitHelper.waitPageNoAjaxAndAnimation();
    return getCaseWidgetHeader().$(".expand-link").isDisplayed();
  }

  public boolean isWidgetInfomationIconAppear() {
    WaitHelper.waitPageNoAjaxAndAnimation();
    return getCaseWidgetHeader().$(".widget__info-sidebar-link").isDisplayed();
  }

  public int countSideStepItems(int caseIndex, String widgetId) {
    clickOnCaseActionLink(caseIndex);
    return $$(String.format("div.js-case-side-steps-panel-" + widgetId + "-%d", caseIndex)).filter(appear).first()
        .shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-overlaypanel-content")
        .$$("span[id$=':side-steps'] > a[class*='action-step-item']").size();
  }

  public void openProcessViewer(int caseIndex) {
    clickActionItem(caseIndex, "Process Viewer");
  }

  private void clickActionItem(int caseIndex, String actionName) {
    getActiveCaseActionsInFullCaseListPage(caseIndex).filter(text(actionName)).first().shouldBe(getClickableCondition())
        .click();
  }
  public String getCaseId(int caseIndex) {
    String elementIdSuffixForCaseId = caseIndex + ":dashboard-cases-columns:0:custom-column";
    return $("span[id$='" + elementIdSuffixForCaseId + "']").getText();
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
  
  public void scrollToCaseWidget() {
    $(byText(YOUR_CASES_WIDGET)).shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .scrollIntoView(ScrollIntoViewOptions.instant().block(Block.start));
  }
}
