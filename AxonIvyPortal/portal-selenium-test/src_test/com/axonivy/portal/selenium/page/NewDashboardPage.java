package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NewDashboardPage extends TemplatePage {
  private static final String IVY_PROCESS = "IVY_PROCESS";
  public static final String SRC_ATTRIBUTE = "src";
  public static final String IMAGE_URI_PATTERN = ".*PROCESSMODELING.*";
  private static final String SHOWCASE_NAME = "Showcase";
  private static final String SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME = "Showcase filter";
  private static final String MY_FILTER = "My filter";
  private static final String SHOWCASE = "Showcase";

  @Override
  protected String getLoadedLocator() {
    return ".js-dashboard__wrapper";
  }

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
    $("div[id='portal-global-growl_container']").shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-growl-message")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void waitForTaskListDisplay() {
    $("div[id='task-task_1:widget-content']").shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-growl-message")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration() {
    var configurationPage = openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    $("[id$=':edit-widget-2']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    clickByJavaScript($("[id$=':edit-widget-2']"));
    $("div#new-widget-configuration-dialog").shouldBe(appear, DEFAULT_TIMEOUT);
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public ProcessViewerWidgetNewDashBoardPage showEditProcessViewerWidgetConfiguration() {
    $("button[id$=':edit-widget-4']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div#new-widget-configuration-dialog").shouldBe(appear, DEFAULT_TIMEOUT);
    return new ProcessViewerWidgetNewDashBoardPage();
  }

  public void deleteProcessViewerWidget() {
    $("button[id$=':delete-widget-4']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div#remove-widget-dialog").shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$='remove-widget-button']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
    $("div#remove-widget-dialog").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public WelcomeEditWidgetNewDashboardPage editWelcomeWidgetConfiguration(String widgetId) {
    var configurationPage = openDashboardConfigurationPage();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    String actionButtonId = widgetId + ":welcome-widget-action-group-form:welcome-widget-action-button";
    $("[id='" + actionButtonId + "']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();

    String editLinkId = widgetId + ":welcome-widget-action-group-form:edit-welcome-widget";
    $("[id='" + editLinkId + "']").shouldBe(appear, DEFAULT_TIMEOUT)
    .shouldBe(getClickableCondition()).click();

    $("div#new-widget-configuration-dialog").shouldBe(appear, DEFAULT_TIMEOUT);
    return new WelcomeEditWidgetNewDashboardPage();
  }

  public MainMenuPage openMainMenu() {
    $(".dashboard-cases-container").shouldBe(appear, DEFAULT_TIMEOUT);
    if (!isMainMenuOpen()) {
      waitForElementDisplayed(By.id("left-menu"), true);
      $(By.id("left-menu")).shouldBe(appear, DEFAULT_TIMEOUT).hover().scrollTo();
      waitForElementClickableThenClick($(By.id("user-menu-required-login:toggle-menu")));
    }
    return new MainMenuPage();
  }

  public void checkStartButtonAndImageShown() {
    getStartButton().shouldBe(Condition.disabled);
    getDisabledMoreInformationLink().shouldBe(Condition.appear);
    getProcessImage().shouldHave(Condition.attributeMatching(SRC_ATTRIBUTE, IMAGE_URI_PATTERN));
  }

  public SelenideElement getStartButton() {
    return $("button[id$=':start-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getProcessImage() {
    return $("img.image-process-item-image").shouldBe(Condition.exist, DEFAULT_TIMEOUT);
  }

  public ProcessEditWidgetNewDashBoardPage editImageModeProcess() {
    $("button[id$='image-process-action-component:process-action-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("[id$=':image-process-action-component:process-action-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("span.si-graphic-tablet-drawing-pen").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public SelenideElement getProcessItemName() {
    return $("span[id$=':process-item-name']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getProcessRequestPathName() {
    return $("span[id$=':request-path']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getImageContainer() {
    return $("div.image-process-container");
  }

  public SelenideElement findProcessViewerWidget() {
    return $("div[id$=':process-viewer-panel-group-4']");
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
    $(".process-grid-item__action--combined .si-pencil").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").shouldHave(Condition.appear);
  }

  public void resizeCombinedModeProcess() {
    SelenideElement gridStackItem = getStartButton().closest(".grid-stack-item");
    updateElementAttribute(gridStackItem, "gs-w", "7");
    updateElementAttribute(gridStackItem, "gs-x", "0");
    gridStackItem.shouldBe(Condition.attribute("gs-x", "0"), DEFAULT_TIMEOUT)
        .shouldBe(Condition.attribute("gs-w", "7"), DEFAULT_TIMEOUT);
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
    getCombinedModeProcessCollapseLink().shouldBe(disappear);
    getCombinedModeProcessExpandLink().shouldBe(Condition.appear).click();
    getCombinedModeProcessCollapseLink().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    getCombinedModeProcessExpandLink().shouldBe(disappear);
  }

  public void collapseCombinedModeProcess() {
    getCombinedModeProcessCollapseLink().click();
    getCombinedModeProcessExpandLink().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCombinedModeProcessExpandLink() {
    return $(".dashboard-processes-container--combined [id$=':expand-link']");
  }

  public SelenideElement getCombinedModeProcessCollapseLink() {
    return $(".dashboard-processes-container--combined [id$=':collapse-link']");
  }

  public void checkTasksTabDisplayedDataContainer() {
    $("div[id$=':dashboard-process-tasks-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCombinedModeProcessFirstTaskStartAction() {
    return $("div[id$=':dashboard-process-tasks-container'] a.start-task-action");
  }

  public SelenideElement getCombinedModeProcessFirstTaskName() {
    return $("div[id$=':dashboard-process-tasks-container'] td.relevant-tasks__name");
  }

  public SelenideElement getCasesTab() {
    return $$(".combined-process-widget-button-selection .ui-button").get(1);
  }

  public SelenideElement getCombinedModeProcessFirstCaseName() {
    return $("div[id$=':dashboard-process-cases-container'] td.relevant-cases__name");
  }

  public void startCombinedModeProcessFirstTask() {
    getCombinedModeProcessFirstTaskStartAction().shouldBe(Condition.appear).click();
  }

  public void openCombinedModeProcessFirstTask() {
    getCombinedModeProcessFirstTaskName().shouldBe(Condition.appear).click();
  }

  public void openCombinedModeProcessFirstCase() {
    getCasesTab().shouldBe(Condition.appear).click();
    getCombinedModeProcessFirstCaseName().shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getWidgetByName(String widgetName) {
    return getAllWidgetHeaders().find(Condition.textCaseSensitive(widgetName));
  }

  public ElementsCollection getAllWidgetHeaders() {
    return $$("span[id$=':widget__header']");
  }

  public SelenideElement getCompactModeProcessDisplayedDisabledFirstProcessItemName() {
    return $(".compact-processes-container span.ui-commandlink.process-item span[id$=':process-name-process-item']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void startCompactModeProcessByProcessName(String processName) {
    getCompactModeProcessDisplayedProcessByName(processName).click();
  }

  public SelenideElement getCompactModeProcessDisplayedProcessByName(String processName) {
    return getCompactModeProcessProcessItemName(processName).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void checkCompactModeProcessDisplayedProcessItem(String processName) {
    getCompactModeProcessDisplayedProcessByName(processName);
  }

  public void checkDisplayedCompactModeProcessContainer() {
    getCompactModeProcessContainer().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessDisplayedFirstProcessItemName() {
    return getCompactModeProcessFirstProcessItemName().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessFirstProcessItemName() {
    return $(".compact-processes-container a.process-item span[id$=':process-name-process-item']");
  }

  public SelenideElement getCompactModeProcessContainer() {
    return $(".process-widget.dashboard-widget-panel-container .compact-processes-container");
  }

  public ElementsCollection getCompactModeProcessElementList() {
    return $(".process-widget.dashboard-widget-panel-container .compact-processes-container")
        .$$("span.process-start-list-item");
  }

  public SelenideElement getCompactModeProcessProcessItemName(String processName) {
    return $$(".compact-processes-container a.process-item span[id$=':process-name-process-item']")
        .filter(Condition.exactTextCaseSensitive(processName)).first();
  }

  public void openCompactModeProcessFilterPanel() {
    var processFilter = getCompactModeProcessFilterLink().shouldBe(Condition.appear);
    waitUntilElementToBeClickable(processFilter);
    clickByJavaScript(processFilter);
    getCompactModeProcessFilterPanelSaveFilters().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void applyCompactModeProcessFilterPanel() {
    getCompactModeProcessFilterPanelApplyButton().shouldBe(Condition.enabled).click();
    getCompactModeProcessFilterPanel().shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void applyFilterCompactModeProcessProcessNameWhenExpanded(String processName) {
    applyFilterCompactModeProcessProcessName(processName);
  }

  public void applyFilterCompactModeProcessProcessName(String processName) {
    openCompactModeProcessFilterPanel();
    filterCompactModeProcessProcessName(processName);
    applyCompactModeProcessFilterPanel();
  }

  public void filterCompactModeProcessProcessName(String processName) {
    SelenideElement filterName = getCompactModeProcessFilterPanelProcessName();
    filterName.shouldBe(Condition.appear).clear();
    filterName.sendKeys(processName);
  }

  public SelenideElement getCompactModeProcessFilterLink() {
    String selector = String.format("[id$=':filter-sidebar-link-2']");
    return $(selector);
  }

  public SelenideElement getCompactModeProcessInfoLink() {
    String selector = String.format("[id$=':info-sidebar-link-2']");
    return $(selector);
  }

  public SelenideElement getCompactModeProcessFilterPanel() {
    String selector = String.format("div[id$=':filter-overlay-panel-2']");
    return $(selector);
  }

  public SelenideElement getCompactModeProcessInfoPanel() {
    String selector = String.format("div[id$=':info-overlay-panel-2']");
    return $(selector);
  }

  public SelenideElement getCompactModeProcessFilterPanelSaveFilters() {
    return getCompactModeProcessFilterPanel().shouldBe(appear, DEFAULT_TIMEOUT)
        .$("div[id$=':widget-saved-filters-items");
  }

  public SelenideElement getCompactModeProcessFilterPanelProcessName() {
    return getCompactModeProcessFilterPanel().$("input[id$=':text-field-input']");
  }

  public SelenideElement getCompactModeProcessFilterPanelApplyButton() {
    return getCompactModeProcessFilterPanel().$("button[id$=':apply-button']");
  }

  public void applyFilterCompactModeProcessProcessTypeWhenExpanded(String processType) {
    applyFilterCompactModeProcessProcessType(processType);
  }

  public void applyFilterCompactModeProcessProcessType(String processType) {
    openCompactModeProcessFilterPanel();
    filterCompactModeProcessProcessType(processType);
    applyCompactModeProcessFilterPanel();
  }

  public void checkCompactModeProcessDisappearedProcessItem(String processName) {
    getCompactModeProcessProcessItemName(processName).shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void filterCompactModeProcessProcessType(String processType) {
    getCompactModeProcessFilterPanelProcessTypes().shouldBe(Condition.appear).click();
    getCompactModeProcessProcessTypesPanel().shouldBe(Condition.cssClass("ui-connected-overlay-enter-done"), DEFAULT_TIMEOUT)
        .$("li[data-item-value='" + processType + "'] label").shouldBe(Condition.appear).click();
    getCompactModeProcessProcessTypesPanel().$(".ui-selectcheckboxmenu-close").click();
    getCompactModeProcessProcessTypesPanel().shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessFilterPanelProcessTypes() {
    return getCompactModeProcessFilterPanel().$("div[id$=':process-types']");
  }

  public SelenideElement getCompactModeProcessProcessTypesPanel() {
    return $("div[id$=':process-types_panel']");
  }

  public void applyFilterCompactModeProcessCategoryWhenExpanded(String category) {
    applyFilterCompactModeProcessCategory(category);
  }

  public void applyFilterCompactModeProcessCategory(String category) {
    openCompactModeProcessFilterPanel();
    filterCompactModeProcessCategory(category);
    applyCompactModeProcessFilterPanel();
  }

  public void filterCompactModeProcessCategory(String category) {
    getCompactModeProcessFilterPanelCategory().shouldBe(Condition.appear).click();
    ElementsCollection categories = getCompactModeProcessCategoryFilterPanel()
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$$(".ui-treenode-label");
    categories.filter(Condition.exactTextCaseSensitive("All Categories")).first().click();
    categories.filter(Condition.exactTextCaseSensitive(category)).first()
        .shouldBe(Condition.not(Condition.cssClass("ui-state-highlight")), DEFAULT_TIMEOUT).click();
    getCompactModeProcessCategoryFilterPanelApplyButton().click();
    getCompactModeProcessCategoryFilterPanel().shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessFilterPanelCategory() {
    return getCompactModeProcessFilterPanel().$("input[id$=':widget-filter-category']");
  }

  public SelenideElement getCompactModeProcessCategoryFilterPanel() {
    return $("div[id$=':widget-filter-category-panel']");
  }

  public SelenideElement getCompactModeProcessCategoryFilterPanelApplyButton() {
    return getCompactModeProcessCategoryFilterPanel().$("button[id$=':update-command']");
  }

  public void expandCompactModeProcess() {
    SelenideElement filterLink = getCompactModeProcessFilterLink();
    filterLink.shouldBe(Condition.appear);

    getCompactModeProcessInfoLink().shouldBe(Condition.appear);

    getCompactModeProcessCollapseLink().shouldBe(disappear);

    SelenideElement expandLink = getCompactModeProcessExpandLink();
    expandLink.shouldBe(Condition.appear).click();
    expandLink.shouldBe(disappear, DEFAULT_TIMEOUT);

    getCompactModeProcessCollapseLink().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void collapseCompactModeProcess() {
    getCompactModeProcessCollapseLink().click();
    getCompactModeProcessCollapseLink().shouldBe(disappear, DEFAULT_TIMEOUT);
    getCompactModeProcessExpandLink().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessExpandLink() {
    return $("[id$=':expand-link-2']");
  }

  public SelenideElement getCompactModeProcessCollapseLink() {
    return $("[id$=':collapse-link-2']");
  }

  public SelenideElement getCompactModeProcessFilterPanelSaveButton() {
    return getCompactModeProcessFilterPanel().$("button[id$=':save-filter']");
  }

  public SelenideElement getCompactModeProcessFilterPanelResetButton() {
    return getCompactModeProcessFilterPanel().$("button[id$=':reset-button']");
  }

  public void resetCompactModeProcessFilterPanel() {
    getCompactModeProcessFilterPanelResetButton().shouldBe(Condition.enabled).click();
    getCompactModeProcessFilterPanel().shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void selectCompactModeProcessFilter(String processName, String processType,
      String category) {
    filterCompactModeProcessProcessName(processName);
    filterCompactModeProcessProcessType(processType);
    filterCompactModeProcessCategory(category);
  }

  public void selectCompactModeProcessFilter(String category) {
    filterCompactModeProcessCategory(category);
  }

  public void testSaveResetApplyCompactModeProcessFilterWhenExpanded() {
    testSaveResetApplyCompactModeProcessFilter();
  }

  public void testSaveResetApplyCompactModeProcessFilter() {
    // Save filter
    openCompactModeProcessFilterPanel();
    selectCompactModeProcessFilter(SHOWCASE_NAME, IVY_PROCESS, SHOWCASE);
    saveCompactModeProcessFilter(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    getCompactModeProcessFilterPanelSavedFilter(0).shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME));
    // Apply filter
    applyCompactModeProcessFilterPanel();
    getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldHave(Condition.exactTextCaseSensitive("Showcase Application"));
    // Reset filter
    openCompactModeProcessFilterPanel();
    resetCompactModeProcessFilterPanel();
    getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldNotHave(Condition.exactTextCaseSensitive("Showcase Application"));
    // Apply saved filter
    openCompactModeProcessFilterPanel();
    selectCompactModeProcessSavedFilter(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    applyCompactModeProcessFilterPanel();
    getCompactModeProcessDisplayedFirstProcessItemName()
        .shouldHave(Condition.exactTextCaseSensitive("Showcase Application"));
  }

  public void saveCompactModeProcessFilter(String savedFilterName) {
    getCompactModeProcessFilterPanelSaveButton().shouldBe(Condition.enabled).click();
    SelenideElement saveWidgetFilterDialog = getSaveWidgetFilterDialog();
    saveWidgetFilterDialog.shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    SelenideElement filterName = getSaveWidgetFilterDialogFilterName();
    filterName.shouldBe(Condition.appear).clear();
    filterName.sendKeys(savedFilterName);

    getSaveWidgetFilterDialogSaveButton().shouldBe(Condition.enabled).click();
    saveWidgetFilterDialog.shouldBe(disappear, DEFAULT_TIMEOUT);
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

  public SelenideElement getCompactModeProcessFilterPanelSavedFilter(int index) {
    return getCompactModeProcessFilterPanelSaveFilters().$$("span.saved-filter-node__text").get(index);
  }

  public void selectCompactModeProcessSavedFilter(String savedFilterName) {
    SelenideElement savedFilter = getCompactModeProcessFilterPanelSavedFilter(0);
    savedFilter.shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(savedFilterName)).click();
    savedFilter.closest(".saved-filter-node").shouldBe(Condition.cssClass("selected"), DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessFilterPanelSearchInput() {
    return getCompactModeProcessFilterPanel().$("input[id$=':search-saved-filter-input']");
  }

  public SelenideElement getCompactModeProcessFilterPanelManageFiltersLink() {
    return getCompactModeProcessFilterPanel().$(".saved-filter__manage-filter");
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

  public void openCompactModeProcessManageFilters() {
    getCompactModeProcessFilterPanelManageFiltersLink().shouldBe(Condition.appear).click();
    getManageWidgetFilterDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void closeCompactModeProcessManagerFilters() {
    getManageWidgetFilterDialogCloseLink().shouldBe(Condition.appear).click();
    getManageWidgetFilterDialog().shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void removeCompactModeProcessFilter() {
    openCompactModeProcessFilterPanel();
    testCreateCompactModeProcessFilters();
    testSearchCompactModeProcessFilters();

    openCompactModeProcessManageFilters();
    testRemoveCompactModeProcessFilter();
    closeCompactModeProcessManagerFilters();
  }

  public void testSavedFilterValue() {
    openCompactModeProcessFilterPanel();
    getCompactModeProcessFilterPanelSavedFilter(0)
        .shouldBe(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME), DEFAULT_TIMEOUT);
  }

  private void testCreateCompactModeProcessFilters() {
    selectCompactModeProcessFilter(SHOWCASE_NAME, "IVY_PROCESS", SHOWCASE);
    saveCompactModeProcessFilter(MY_FILTER);
    getCompactModeProcessFilterPanelSavedFilter(0).shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(MY_FILTER));

    saveCompactModeProcessFilter(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    getCompactModeProcessFilterPanelSavedFilter(1).shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME));
  }

  private void testSearchCompactModeProcessFilters() {
    SelenideElement searchInput = getCompactModeProcessFilterPanelSearchInput();
    searchInput.shouldBe(Condition.appear).click();
    searchInput.clear();
    searchInput.sendKeys(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME);
    getCompactModeProcessFilterPanelSavedFilter(0)
        .shouldBe(Condition.exactTextCaseSensitive(SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME), DEFAULT_TIMEOUT);

    searchInput.shouldBe(Condition.appear).clear();
    getCompactModeProcessFilterPanelSavedFilter(0).shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.exactTextCaseSensitive(MY_FILTER));
  }

  private void testRemoveCompactModeProcessFilter() {
    removeCompactModeProcessFilter(MY_FILTER);
    getManageWidgetFilterDialogFirstSavedFilter()
        .shouldBe(Condition.attribute("data-rk", SHOWCASE_DATA_TABLE_SAVED_FILTER_NAME), DEFAULT_TIMEOUT);
  }

  public void removeCompactModeProcessFilter(String filterName) {
    SelenideElement savedFilter = getManageWidgetFilterDialogFirstSavedFilter();
    savedFilter.shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldHave(Condition.attribute("data-rk", filterName));
    savedFilter.$("td").click();
    savedFilter.shouldBe(Condition.cssClass("ui-state-highlight"), DEFAULT_TIMEOUT);
    getManageWidgetFilterDialogRemoveButton().shouldBe(Condition.enabled).click();
  }

  public ElementsCollection getCompactModeProcessInfoProcessTypesWhenExpanded() {
    return getCompactModeProcessInfoProcessTypes();
  }

  public void openCompactModeProcessInforPanelWhenExpanded() {
    openCompactModeProcessInforPanel();
  }

  public void openCompactModeProcessInforPanel() {
    getCompactModeProcessInfoLink().shouldBe(Condition.appear).click();
    getCompactModeProcessInfoPanel().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getCompactModeProcessInfoProcessTypes() {
    return getCompactModeProcessInfoPanel().$$("span.dashboard-processes__type-text");
  }

  public SelenideElement getConfigureDashboardMenu() {
    $("#user-settings-menu").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("#user-setting-container").shouldBe(Condition.exist, DEFAULT_TIMEOUT)
           .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("a#user-profile").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
           .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    return $("#dashboard-configuration");
  }

  public DashboardConfigurationPage openDashboardConfigurationPage() {
    waitForDashboardPageAvailable();
    if ($("div[id='portal-global-growl_container']").is(appear)) {
        waitForGrowlMessageDisappear();
      }
    SelenideElement configureButton = getConfigureDashboardMenu();
    configureButton.click();
    return new DashboardConfigurationPage();
  }

  public void waitForDashboardPageAvailable() {
    $(".js-dashboard__wrapper").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getDashboardCollection() {
    if (!$(".js-layout-wrapper").shouldBe(appear, DEFAULT_TIMEOUT).attr("class").contains("layout-static")) {
      $(".layout-menu-container").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).contextClick();
      $("a[id$='user-menu-required-login:toggle-menu']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }
    return $(".layout-menu").shouldBe(appear, DEFAULT_TIMEOUT).$$("li.menu-item-dashboard a.DASHBOARD");
  }

  public SelenideElement getDashboardActive() {
    return getDashboardCollection().asFixedIterable().stream().filter(menuItem -> menuItem.parent().has(Condition.cssClass("active-menuitem"))).findFirst().get();
  }
  
  public void selectDashboard(int index) {
    var selectDashboard = getDashboardCollection().get(index);
    selectDashboard.shouldBe(getClickableCondition()).click();
    selectDashboard.parent().shouldBe(Condition.cssClass("active-menuitem"), DEFAULT_TIMEOUT);
  }

  public StatisticWidgetDashboardPage selectStatisticWidget() {
    return new StatisticWidgetDashboardPage();
  }

  public void checkDisplayedCaseWidgetContainer() {
    getCaseWidgetContainer().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getCaseWidgetContainer() {
    return $("div[id$='dashboard-cases-container']");
  }

  public SelenideElement getCaseWidgetTable() {
    return getCaseWidgetContainer().$("div[id$='dashboard-cases']");
  }

  public SelenideElement getCaseWidgetEmptyMessageWhenNotFilter() {
    return getCaseWidgetContainer().$("div[id$='empty-message-when-not-filter']");
  }

  public SelenideElement getCaseWidgetEmptyMessageWhenFilter() {
    return getCaseWidgetContainer().$("div[id$='empty-message-when-filter']");
  }

  public void checkDisplayedTaskWidgetContainer() {
    getTaskWidgetContainer().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getTaskWidgetContainer() {
    return $("div[id$='dashboard-tasks-container']");
  }

  public SelenideElement getTaskWidgetTable() {
    return getTaskWidgetContainer().$("div[id$='dashboard-tasks']");
  }

  public SelenideElement getTaskWidgetEmptyMessageWhenNotFilter() {
    return getTaskWidgetContainer().$("div[id$='empty-message-when-not-filter']");
  }

  public SelenideElement getTaskWidgetEmptyMessageWhenFilter() {
    return getTaskWidgetContainer().$("div[id$='empty-message-when-filter']");
  }

  public SelenideElement getUserMenuAvatar() {
    return $("#user-settings-menu > .has-avatar >.ui-avatar").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getWidgetNoti() {
    return $("div[gs-id$='process_1']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$(".widget__filter-noti-number");
  }

  public DashboardNewsWidgetPage selectNewsFeedWidget(String newWidgetName) {
    return new DashboardNewsWidgetPage(newWidgetName);
  }

  public boolean isDownloadCompleted() {
    return $("#download-status-dialog")
        .shouldBe(Condition.attribute("download-status", "completed"), DEFAULT_TIMEOUT)
        .exists();
  }

  private SelenideElement getGlobalSearchInput() {
    return $("#global-search-component\\:global-search-data").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public GlobalSearchResultPage inputGlobalSearchKeyword(String keyword) {
    $(".topbar-item.search-item").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("input[id$='global-search-component:global-search-data']").shouldBe(appear, DEFAULT_TIMEOUT);
    getGlobalSearchInput().click();
    getGlobalSearchInput().sendKeys(keyword);
    getGlobalSearchInput().sendKeys(Keys.ENTER.toString());
    $("#search-results-tabview").shouldBe(appear, DEFAULT_TIMEOUT);
    return new GlobalSearchResultPage();
  }

  public boolean isInputGlobalSearchDisabled() {
    return $(".topbar-item.search-item").isDisplayed();
  }
  
  public void waitForCaseWidgetLoaded() {
    checkDisplayedCaseWidgetContainer();
    getCaseWidgetTable().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getFirstImageProcess() {
    return $(".image-process-item-image").shouldBe(Condition.exist, DEFAULT_TIMEOUT);
  }
}
