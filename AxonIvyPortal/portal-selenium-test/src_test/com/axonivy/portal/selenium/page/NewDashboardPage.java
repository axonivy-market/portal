package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NewDashboardPage extends TemplatePage {
  private static final String IVY_PROCESS = "IVY_PROCESS";
  public static final String SRC_ATTRIBUTE = "src";
  public static final String IMAGE_URI_PATTERN = ".*PROCESSMODELING.*";
  private static final String SHOWCASE_DATA_TABLE = "Showcase Data Table";
  private static final String SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME = "Showcase Data Table filter";
  private static final String MY_FILTER = "My filter";
  private static final String DATA_TABLE = "DataTable";

  public CaseWidgetNewDashBoardPage selectCaseWidget(String caseWidgetName) {
    return new CaseWidgetNewDashBoardPage(caseWidgetName);
  }

  public TaskWidgetNewDashBoardPage selectTaskWidget(String taskWidgetName) {
    return new TaskWidgetNewDashBoardPage(taskWidgetName);
  }
  
  public ProcessWidgetNewDashBoardPage selectProcessWidget(String processWidgetName) {
    return new ProcessWidgetNewDashBoardPage(processWidgetName);
  }

  public void waitForAbsencesGrowlMessageDisplay() {
    $("div[id='portal-global-growl_container']").waitUntil(appear, DEFAULT_TIMEOUT).$("div.ui-growl-message")
        .waitUntil(disappears, DEFAULT_TIMEOUT);
  }

  public ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration() {
    openDashboardConfigurationDialog();
    NewDashboardConfigurationPage configPage = navigateToEditPublicDashboardPage();
    configPage.navigateToEditDashboardDetailsByName("Dashboard");

    $("a[id$=':edit-widget-2']").waitUntil(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div#new-widget-configuration-dialog").waitUntil(appear, DEFAULT_TIMEOUT);
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public void checkStartButtonAndImageShown() {
    getStartButton().shouldBe(Condition.disabled);
    getDisabledMoreInformationLink().shouldBe(Condition.appear);
    getProcessImage().shouldHave(Condition.attributeMatching(SRC_ATTRIBUTE, IMAGE_URI_PATTERN));
  }

  public SelenideElement getStartButton() {
    return $("button[id$=':start-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getProcessImage() {
    return $("img.image-process-item-image").waitUntil(Condition.exist, DEFAULT_TIMEOUT);
  }

  public ProcessEditWidgetNewDashBoardPage editImageModeProcess() {
    $("button[id$=':process-action-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("span.si-graphic-tablet-drawing-pen").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public SelenideElement getProcessItemName() {
    return $("span[id$=':process-item-name']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getImageContainer() {
    return $("div.image-process-container");
  }

  public void startProcess() {
    getStartButton().click();
  }

  public void startMoreInfoLink() {
    getMoreInformationLink().click();
  }

  public SelenideElement getDisabledMoreInformationLink() {
    return $("span[id$=':more-information']");
  }

  public SelenideElement getMoreInformationLink() {
    return $("a[id$=':more-information']");
  }

  public SelenideElement getFullModeProcessName() {
    return $("span[id$=':process-item:process-name']").shouldBe(Condition.appear);
  }

  public SelenideElement getFullModeProcessContainer() {
    return $(".process-widget.dashboard-widget-panel-container .process-widget--full");
  }

  public ProcessEditWidgetNewDashBoardPage editCombinedModeProcess() {
    try {
      openEditCombinedModeProcessDialog();
    } catch (Exception e) {
      openEditCombinedModeProcessDialog();
    }
    return new ProcessEditWidgetNewDashBoardPage();
  }

  private void openEditCombinedModeProcessDialog() {
    $(".process-grid-item__action--combined .si-pencil").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").shouldHave(Condition.appear);
  }

  public void resizeCombinedModeProcess() {
    SelenideElement gridStackItem = getStartButton().closest(".grid-stack-item");
    updateElementAttribute(gridStackItem, "gs-w", "7");
    updateElementAttribute(gridStackItem, "gs-x", "0");
    gridStackItem.waitUntil(Condition.attribute("gs-x", "0"), DEFAULT_TIMEOUT)
        .waitUntil(Condition.attribute("gs-w", "7"), DEFAULT_TIMEOUT);
  }

  private void updateElementAttribute(SelenideElement element, String attribute, String value) {
    WebDriver driver = element.getWrappedDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element.getWrappedElement(), attribute,
        value);
  }

  public SelenideElement getCombinedModeProcessName() {
    return $(".process-grid-view__name--combined").shouldBe(Condition.appear);
  }

  public SelenideElement getCombinedModeProcessContainer() {
    return $(".process-widget.dashboard-widget-panel-container .dashboard-processes-container--combined");
  }

  public void expandCombindedModeProcess() {
    getCombinedModeProcessCollapseLink().shouldBe(Condition.disappear);
    getCombinedModeProcessExpandLink().shouldBe(Condition.appear).click();
    getCombinedModeProcessCollapseLink().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    getCombinedModeProcessExpandLink().shouldBe(Condition.disappear);
  }

  public void collapseCombinedModeProcess() {
    getCombinedModeProcessCollapseLink().click();
    getCombinedModeProcessExpandLink().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCombinedModeProcessExpandLink() {
    return $(".dashboard-processes-container--combined a[id$=':expand-link']");
  }

  public SelenideElement getCombinedModeProcessCollapseLink() {
    return $(".dashboard-processes-container--combined a[id$=':collapse-link']");
  }

  public void checkTasksTabDisplayedDataContainer() {
    $("div[id$=':dashboard-process-tasks-container']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCombinedModeProcessFirstTaskStartAction() {
    return $("div[id$=':dashboard-process-tasks-container'] a.start-task-action");
  }

  public SelenideElement getCombinedModeProcessFirstTaskName() {
    return $("div[id$=':dashboard-process-tasks-container'] td.revelent-tasks__name");
  }

  public SelenideElement getCasesTab() {
    return $(".combined-process-widget__button-tabs.last-tab-button");
  }

  public SelenideElement getCombinedModeProcessFirstCaseName() {
    return $("div[id$=':dashboard-process-cases-container'] td.revelent-cases__name");
  }

  public void startCombinedModeProcessFirstTask() {
    getCombinedModeProcessFirstTaskStartAction().shouldBe(Condition.appear).click();
  }

  public void openCombinedModeProcessFirstTask() {
    getCombinedModeProcessFirstTaskName().shouldBe(Condition.appear).click();
  }

  public void openCombinedModeProcessFirstCase() {
    getCasesTab().shouldBe(Condition.appear).click();
    getCombinedModeProcessFirstCaseName().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getWidgetByName(String widgetName) {
    return getAllWidgetHeaders().find(Condition.textCaseSensitive(widgetName));
  }

  public ElementsCollection getAllWidgetHeaders() {
    return $$("span[id$=':widget__header']");
  }

  public SelenideElement getCompactModeProcessDisplayedDisabledFirstProcessItemName() {
    return $(".compact-processes-container span.ui-commandlink.process-item span[id$=':process-name-process-item']")
        .waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void startCompactModeProcessByProcessName(String processName) {
    getCompactModeProcessDisplayedProcessByName(processName).click();
  }

  public SelenideElement getCompactModeProcessDisplayedProcessByName(String processName) {
    return getCompactModeProcessProcessItemName(processName).waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void checkCompactModeProcessDisplayedProcessItem(String processName) {
    getCompactModeProcessDisplayedProcessByName(processName);
  }

  public void checkDisplayedCompactModeProcessContainer() {
    getCompactModeProcessContainer().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessDisplayedFirstProcessItemName() {
    return getCompactModeProcessFirstProcessItemName().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessFirstProcessItemName() {
    return $(".compact-processes-container a.process-item span[id$=':process-name-process-item']");
  }

  public SelenideElement getCompactModeProcessContainer() {
    return $(".process-widget.dashboard-widget-panel-container .compact-processes-container");
  }

  public SelenideElement getCompactModeProcessProcessItemName(String processName) {
    return $$(".compact-processes-container a.process-item span[id$=':process-name-process-item']")
        .filter(Condition.exactTextCaseSensitive(processName)).first();
  }

  public void openCompactModeProcessFilterPanel(boolean isExpanded) {
    getCompactModeProcessFilterLink(isExpanded).shouldBe(Condition.appear).click();
    getCompactModeProcessFilterPanelSaveFilters(isExpanded).waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void applyCompactModeProcessFilterPanel(boolean isExpanded) {
    getCompactModeProcessFilterPanelApplyButton(isExpanded).shouldBe(Condition.enabled).click();
    getCompactModeProcessFilterPanel(isExpanded).waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void applyFilterCompactModeProcessProcessName(String processName) {
    applyFilterCompactModeProcessProcessName(false, processName);
  }

  public void applyFilterCompactModeProcessProcessNameWhenExpanded(String processName) {
    applyFilterCompactModeProcessProcessName(true, processName);
  }

  private void applyFilterCompactModeProcessProcessName(boolean isExpanded, String processName) {
    openCompactModeProcessFilterPanel(isExpanded);
    filterCompactModeProcessProcessName(isExpanded, processName);
    applyCompactModeProcessFilterPanel(isExpanded);
  }

  public void filterCompactModeProcessProcessName(boolean isExpanded, String processName) {
    SelenideElement filterName = getCompactModeProcessFilterPanelProcessName(isExpanded);
    filterName.shouldBe(Condition.appear).clear();
    filterName.sendKeys(processName);
  }

  public SelenideElement getCompactModeProcessFilterLink(boolean isExpanded) {
    String selector = String.format("a[id$=':%sfilter-sidebar-link-2']", isExpanded ? "expanded-" : "");
    return $(selector);
  }

  public SelenideElement getCompactModeProcessInfoLink(boolean isExpanded) {
    String selector = String.format("a[id$=':%sinfo-sidebar-link-2']", isExpanded ? "expanded-" : "");
    return $(selector);
  }

  public SelenideElement getCompactModeProcessFilterPanel(boolean isExpanded) {
    String selector = String.format("div[id$=':%sfilter-overlay-panel-2']", isExpanded ? "expanded-" : "");
    return $(selector);
  }

  public SelenideElement getCompactModeProcessInfoPanel(boolean isExpanded) {
    String selector = String.format("div[id$=':%sinfo-overlay-panel-2']", isExpanded ? "expanded-" : "");
    return $(selector);
  }

  public SelenideElement getCompactModeProcessFilterPanelSaveFilters(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("div[id$=':widget-saved-filters-items");
  }

  public SelenideElement getCompactModeProcessFilterPanelProcessName(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("input[id$=':text-field-input']");
  }

  public SelenideElement getCompactModeProcessFilterPanelApplyButton(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("button[id$=':apply-button']");
  }

  public void applyFilterCompactModeProcessProcessType(String processType) {
    applyFilterCompactModeProcessProcessType(false, processType);
  }

  public void applyFilterCompactModeProcessProcessTypeWhenExpanded(String processType) {
    applyFilterCompactModeProcessProcessType(true, processType);
  }

  public void applyFilterCompactModeProcessProcessType(boolean isExpanded, String processType) {
    openCompactModeProcessFilterPanel(isExpanded);
    filterCompactModeProcessProcessType(isExpanded, processType);
    applyCompactModeProcessFilterPanel(isExpanded);
  }

  public void checkCompactModeProcessDisappearedProcessItem(String processName) {
    getCompactModeProcessProcessItemName(processName).waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void filterCompactModeProcessProcessType(boolean isExpanded, String processType) {
    getCompactModeProcessFilterPanelProcessTypes(isExpanded).shouldBe(Condition.appear).click();
    getCompactModeProcessProcessTypesPanel().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .$("li[data-item-value='" + processType + "']").shouldBe(Condition.appear).click();
    getCompactModeProcessProcessTypesPanel().$(".ui-selectcheckboxmenu-close").click();
    getCompactModeProcessProcessTypesPanel().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessFilterPanelProcessTypes(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("div[id$=':process-types']");
  }

  public SelenideElement getCompactModeProcessProcessTypesPanel() {
    return $("div[id$=':process-types_panel']");
  }

  public void applyFilterCompactModeProcessCategory(String category) {
    applyFilterCompactModeProcessCategory(false, category);
  }

  public void applyFilterCompactModeProcessCategoryWhenExpanded(String category) {
    applyFilterCompactModeProcessCategory(true, category);
  }

  public void applyFilterCompactModeProcessCategory(boolean isExpanded, String category) {
    openCompactModeProcessFilterPanel(isExpanded);
    filterCompactModeProcessCategory(isExpanded, category);
    applyCompactModeProcessFilterPanel(isExpanded);
  }

  public void filterCompactModeProcessCategory(boolean isExpanded, String category) {
    getCompactModeProcessFilterPanelCategory(isExpanded).shouldBe(Condition.appear).click();
    ElementsCollection categories = getCompactModeProcessCategoryFilterPanel(isExpanded)
        .waitUntil(Condition.appear, DEFAULT_TIMEOUT).$$(".ui-treenode-label");
    categories.filter(Condition.exactTextCaseSensitive("All Categories")).first().click();
    categories.filter(Condition.exactTextCaseSensitive(category)).first()
        .waitUntil(Condition.not(Condition.cssClass("ui-state-highlight")), DEFAULT_TIMEOUT).click();
    getCompactModeProcessCategoryFilterPanelApplyButton(isExpanded).click();
    getCompactModeProcessCategoryFilterPanel(isExpanded).waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessFilterPanelCategory(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("input[id$=':widget-filter-category']");
  }

  public SelenideElement getCompactModeProcessCategoryFilterPanel(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("div[id$=':widget-filter-category-panel']");
  }

  public SelenideElement getCompactModeProcessCategoryFilterPanelApplyButton(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("button[id$=':update-command']");
  }

  public void expandCompactModeProcess() {
    SelenideElement filterLink = getCompactModeProcessFilterLink(false);
    filterLink.shouldBe(Condition.appear);

    SelenideElement infoLink = getCompactModeProcessInfoLink(false);
    infoLink.shouldBe(Condition.appear);

    SelenideElement expandedFilterLink = getCompactModeProcessFilterLink(true);
    expandedFilterLink.shouldBe(Condition.disappear);

    SelenideElement expandedInfoLink = getCompactModeProcessFilterLink(true);
    expandedInfoLink.shouldBe(Condition.disappear);

    SelenideElement collapseLink = getCompactModeProcessCollapseLink();
    collapseLink.shouldBe(Condition.disappear);

    SelenideElement expandLink = getCompactModeProcessExpandLink();
    expandLink.shouldBe(Condition.appear).click();
    expandLink.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);

    collapseLink.waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    expandedFilterLink.waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    expandedInfoLink.waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    filterLink.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
    infoLink.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void collapseCompactModeProcess() {
    getCompactModeProcessCollapseLink().click();
    getCompactModeProcessExpandLink().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    getCompactModeProcessFilterLink(true).waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
    getCompactModeProcessFilterLink(false).waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    getCompactModeProcessInfoLink(true).waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
    getCompactModeProcessInfoLink(false).waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessExpandLink() {
    return $("a[id$=':expand-link-2']");
  }

  public SelenideElement getCompactModeProcessCollapseLink() {
    return $("a[id$=':collapse-link-2']");
  }

  public SelenideElement getCompactModeProcessFilterPanelSaveButton(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("button[id$=':save-filter']");
  }

  public SelenideElement getCompactModeProcessFilterPanelResetButton(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("button[id$=':reset-button']");
  }

  public void resetCompactModeProcessFilterPanel(boolean isExpaned) {
    getCompactModeProcessFilterPanelResetButton(isExpaned).shouldBe(Condition.enabled).click();
    getCompactModeProcessFilterPanel(isExpaned).waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void selectCompactModeProcessFilter(boolean isExpanded, String processName, String processType,
      String category) {
    filterCompactModeProcessProcessName(isExpanded, processName);
    filterCompactModeProcessProcessType(isExpanded, processType);
    filterCompactModeProcessCategory(isExpanded, category);
  }

  public void testSaveResetApplyCompactModeProcessFilter() {
    testSaveResetApplyCompactModeProcessFilter(false);
  }

  public void testSaveResetApplyCompactModeProcessFilterWhenExpanded() {
    testSaveResetApplyCompactModeProcessFilter(true);
  }

  private void testSaveResetApplyCompactModeProcessFilter(boolean isExpanded) {
    // Save filter
    openCompactModeProcessFilterPanel(isExpanded);
    selectCompactModeProcessFilter(isExpanded, SHOWCASE_DATA_TABLE, IVY_PROCESS, DATA_TABLE);
    saveCompactModeProcessFilter(isExpanded, SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0).waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME));
    // Apply filter
    applyCompactModeProcessFilterPanel(isExpanded);
    getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    // Reset filter
    openCompactModeProcessFilterPanel(isExpanded);
    resetCompactModeProcessFilterPanel(isExpanded);
    getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldNotHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
    // Apply saved filter
    openCompactModeProcessFilterPanel(isExpanded);
    selectCompactModeProcessSavedFilter(isExpanded, SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    applyCompactModeProcessFilterPanel(isExpanded);
    getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE));
  }

  public void saveCompactModeProcessFilter(boolean isExpanded, String savedFilterName) {
    getCompactModeProcessFilterPanelSaveButton(isExpanded).shouldBe(Condition.enabled).click();
    SelenideElement saveWidgetFilterDialog = getSaveWidgetFilterDialog();
    saveWidgetFilterDialog.waitUntil(Condition.appear, DEFAULT_TIMEOUT);

    SelenideElement filterName = getSaveWidgetFilterDialogFilterName();
    filterName.shouldBe(Condition.appear).clear();
    filterName.sendKeys(savedFilterName);

    getSaveWidgetFilterDialogSaveButton().shouldBe(Condition.enabled).click();
    saveWidgetFilterDialog.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getSaveWidgetFilterDialog() {
    return $("div[id='save-widget-filter-dialog']");
  }

  public SelenideElement getSaveWidgetFilterDialogFilterName() {
    return getSaveWidgetFilterDialog().$("input[id$=':save-filter-name']");
  }

  public SelenideElement getSaveWidgetFilterDialogSaveButton() {
    return getSaveWidgetFilterDialog().$("button[id$=':save-widget-filter-button']");
  }

  public SelenideElement getCompactModeProcessFilterPanelSavedFilter(boolean isExpanded, int index) {
    return getCompactModeProcessFilterPanelSaveFilters(isExpanded).$$("span.saved-filter-node__text").get(index);
  }

  public void selectCompactModeProcessSavedFilter(boolean isExpanded, String savedFilterName) {
    SelenideElement savedFilter = getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0);
    savedFilter.waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(savedFilterName)).click();
    savedFilter.closest(".saved-filter-node").waitUntil(Condition.cssClass("selected"), DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessFilterPanelSearchInput(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("input[id$=':search-saved-filter-input']");
  }

  public SelenideElement getCompactModeProcessFilterPanelManageFiltersLink(boolean isExpanded) {
    return getCompactModeProcessFilterPanel(isExpanded).$("a.saved-filter__manage-filter");
  }

  public SelenideElement getManageWidgetFilterDialog() {
    return $("div[id='manage-filter-dialog']");
  }

  public SelenideElement getManageWidgetFilterDialogFirstSavedFilter() {
    return getManageWidgetFilterDialog().$("tr.ui-datatable-selectable");
  }

  public SelenideElement getManageWidgetFilterDialogRemoveButton() {
    return getManageWidgetFilterDialog().$("button[id$=':delete-widget-filter-btn']");
  }

  public SelenideElement getManageWidgetFilterDialogCloseLink() {
    return getManageWidgetFilterDialog().$("div[id$=':manage-filter-action'] a");
  }

  public void openCompactModeProcessManageFilters(boolean isExpanded) {
    getCompactModeProcessFilterPanelManageFiltersLink(isExpanded).shouldBe(Condition.appear).click();
    getManageWidgetFilterDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void closeCompactModeProcessManagerFilters() {
    getManageWidgetFilterDialogCloseLink().shouldBe(Condition.appear).click();
    getManageWidgetFilterDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void testSearchRemoveCompactModeProcessFilter() {
    testSearchRemoveCompactModeProcessFilter(false);
  }

  public void testSearchRemoveCompactModeProcessFilterWhenExpanded() {
    testSearchRemoveCompactModeProcessFilter(true);
  }

  private void testSearchRemoveCompactModeProcessFilter(boolean isExpanded) {
    checkDisplayedCompactModeProcessContainer();
    if (isExpanded) {
      expandCompactModeProcess();
    }

    openCompactModeProcessFilterPanel(isExpanded);
    testCreateCompactModeProcessFilters(isExpanded);
    testSearchCompactModeProcessFilters(isExpanded);

    openCompactModeProcessManageFilters(isExpanded);
    testRemoveCompactModeProcessFilter();
    closeCompactModeProcessManagerFilters();

    openCompactModeProcessFilterPanel(isExpanded);
    getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0)
        .waitUntil(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME), DEFAULT_TIMEOUT);
  }

  private void testCreateCompactModeProcessFilters(boolean isExpanded) {
    selectCompactModeProcessFilter(isExpanded, SHOWCASE_DATA_TABLE, "IVY_PROCESS", DATA_TABLE);
    saveCompactModeProcessFilter(isExpanded, MY_FILTER);
    getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0).waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(MY_FILTER));

    saveCompactModeProcessFilter(isExpanded, SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    getCompactModeProcessFilterPanelSavedFilter(isExpanded, 1).waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME));
  }

  private void testSearchCompactModeProcessFilters(boolean isExpanded) {
    SelenideElement searchInput = getCompactModeProcessFilterPanelSearchInput(isExpanded);
    searchInput.shouldBe(Condition.appear).clear();
    searchInput.sendKeys(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0)
        .waitUntil(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME), DEFAULT_TIMEOUT);

    searchInput.shouldBe(Condition.appear).clear();
    getCompactModeProcessFilterPanelSavedFilter(isExpanded, 0).waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(MY_FILTER));
  }

  private void testRemoveCompactModeProcessFilter() {
    removeCompactModeProcessFilter(MY_FILTER);
    getManageWidgetFilterDialogFirstSavedFilter()
        .waitUntil(Condition.attribute("data-rk", SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME), DEFAULT_TIMEOUT);
  }

  public void removeCompactModeProcessFilter(String filterName) {
    SelenideElement savedFilter = getManageWidgetFilterDialogFirstSavedFilter();
    savedFilter.waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldHave(Condition.attribute("data-rk", filterName));
    savedFilter.$("td").click();
    savedFilter.waitUntil(Condition.cssClass("ui-state-highlight"), DEFAULT_TIMEOUT);
    getManageWidgetFilterDialogRemoveButton().shouldBe(Condition.enabled).click();
  }

  public ElementsCollection getCompactModeProcessInfoProcessTypes() {
    return getCompactModeProcessInfoProcessTypes(false);
  }

  public void openCompactModeProcessInforPanel() {
    openCompactModeProcessInforPanel(false);
  }

  public ElementsCollection getCompactModeProcessInfoProcessTypesWhenExpanded() {
    return getCompactModeProcessInfoProcessTypes(true);
  }

  public void openCompactModeProcessInforPanelWhenExpanded() {
    openCompactModeProcessInforPanel(true);
  }

  public void openCompactModeProcessInforPanel(boolean isExpanded) {
    getCompactModeProcessInfoLink(isExpanded).shouldBe(Condition.appear).click();
    getCompactModeProcessInfoPanel(isExpanded).waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getCompactModeProcessInfoProcessTypes(boolean isExpanded) {
    return getCompactModeProcessInfoPanel(isExpanded).$$("span.dashboard-processes__type-text");
  }

  public SelenideElement getConfigureDashboardMenu() {
    $("#user-settings-menu").click();
    $("#user-setting-container").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    return $("#dashboard-configuration");
  }

  public void openDashboardConfigurationDialog() {
    SelenideElement configureButton = getConfigureDashboardMenu();
    configureButton.click();
  }

  public NewDashboardConfigurationPage navigateToEditPublicDashboardPage() {
    $("a[id$=':edit-public-dashboard']").click();
    NewDashboardConfigurationPage newDashboardConfigurationPage = new NewDashboardConfigurationPage();
    newDashboardConfigurationPage.waitPageDisplay();
    return newDashboardConfigurationPage;
  }

  public NewDashboardConfigurationPage navigateToEditPrivateDashboardPage() {
    $("a[id$=':edit-private-dashboard']").click();
    NewDashboardConfigurationPage newDashboardConfigurationPage = new NewDashboardConfigurationPage();
    newDashboardConfigurationPage.waitPageDisplay();
    return newDashboardConfigurationPage;
  }

  public void openCreatePublicDashboardMenu() {
    $("a[id$=':create-public-dashboard']").click();
    $("div[id$='create-new-dashboard-section']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void openCreatePrivateDashboardMenu() {
    $("a[id$=':create-private-dashboard']").click();
    $("div[id$='create-new-dashboard-section']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void createPrivateDashboardFromScratch(String newName, String newDescription) {
    $("a[id$=':create-from-scratch']").click();
    inputCreateDashboardDialog(newName, newDescription, null);
  }

  public void createPrivateDashboardFromTemplate(String newName, String newDescription, int templateIndex) {
    $("div[id$='create-new-dashboard-section']").$("a[id$='" + templateIndex + ":template']").click();
    inputCreateDashboardDialog(newName, newDescription, null);
  }

  public void createPublicDashboardFromScratch(String newName, String newDescription, List<String> permissions) {
    $("a[id$=':create-from-scratch']").click();
    inputCreateDashboardDialog(newName, newDescription, permissions);
  }

  public void createPublicDashboardFromTemplate(String newName, String newDescription, List<String> permissions, int templateIndex) {
    $("div[id$='create-new-dashboard-section']").$("a[id$='" + templateIndex + ":template']").click();
    inputCreateDashboardDialog(newName, newDescription, permissions);
  }

  private void inputCreateDashboardDialog(String newName, String newDescription, List<String> permissions) {
    SelenideElement createDashboardDialog = $("div[id$='dashboard-detail-dialog']");
    createDashboardDialog.waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    createDashboardDialog.$("input[id$=':dashboard-title']").clear();
    createDashboardDialog.$("input[id$=':dashboard-title']").sendKeys(newName);
    createDashboardDialog.$("input[id$=':dashboard-description']").clear();
    createDashboardDialog.$("input[id$=':dashboard-description']").sendKeys(newDescription);
    
    if (permissions != null) {
      ElementsCollection selectedPermissions = createDashboardDialog.$("div[id$=':dashboard-permission']").$$("li.ui-state-active");
      if (!selectedPermissions.isEmpty()) {
        for(SelenideElement permission : selectedPermissions) {
          permission.$("span.ui-icon-close").click();
        }
      }

      createDashboardDialog.$("div[id$=':dashboard-permission']").$("button.ui-autocomplete-dropdown").click();
      
      $("span[id$=':dashboard-permission_panel']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
      $("span[id$=':dashboard-permission_panel']").$$("tr.ui-autocomplete-item").forEach(item -> {
        for(String permissionName : permissions) {
          if (item.$("td").getText().contains(permissionName)) {
            item.click();
          }
        }
      });
    }

    createDashboardDialog.$("button[id$='dashboard-create-button']").click();
    createDashboardDialog.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }


  public SelenideElement getPrivateDashboardConfigurationSection() {
    return $("div[id$=':private-dashboard-configuration-section']");
  }

  public SelenideElement getPublicDashboardConfigurationSection() {
    return $("div[id$=':public-dashboard-configuration-section']");
  }

  public ElementsCollection getDashboardCollection() {
    return $$("a.dashboard__title");
  }

  public void waitPageDisplay() {
    $("a.dashboard__title").waitUntil(disappears, DEFAULT_TIMEOUT);
  }
}
