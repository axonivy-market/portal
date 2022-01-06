package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.axonivy.portal.selenium.common.LinkNavigator;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NewDashboardPage extends TemplatePage {

  public CaseWidgetNewDashBoardPage selectCaseWidget(String caseWidgetName) {
    return new CaseWidgetNewDashBoardPage(caseWidgetName);
  }

  public TaskWidgetNewDashBoardPage selectTaskWidget(String taskWidgetName) {
    return new TaskWidgetNewDashBoardPage(taskWidgetName);
  }

  public void waitForAbsencesGrowlMessageDisplay() {
    $("div[id='portal-global-growl_container']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("div.ui-growl-message").waitUntil(disappears, DEFAULT_TIMEOUT);
  }

  public void switchToEditMode() {
    LinkNavigator.redirectToEditPublicDashboard();
    $("a[id='switch-to-view-mode']").waitUntil(appear, DEFAULT_TIMEOUT).should(appear);
  }

  public void addWidget() {
    $("button[id='add-button']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  private void addWidgetByName(String name) {
    $("div[id='new-widget-dialog-content_content']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$$("div.new-widget-dialog__item").filter(text(name)).first().$("div.ui-widget-content")
        .$("button[id^='new-widget-dialog-content']").shouldBe(getClickableCondition()).click();
  }

  public CaseEditWidgetNewDashBoardPage addNewCaseWidget() {
    addWidgetByName("Case List");
    return new CaseEditWidgetNewDashBoardPage();
  }

  public TaskEditWidgetNewDashBoardPage addNewTaskWidget() {
    addWidgetByName("Task List");
    return new TaskEditWidgetNewDashBoardPage();
  }

  public ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration() {
    switchToEditMode();
    $("a[id$=':edit-widget-2']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public SelenideElement getStartButton() {
    return $("button[id$=':start-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getProcessImage() {
    return $("img.image-process-item-image").waitUntil(Condition.exist, DEFAULT_TIMEOUT);
  }

  public ProcessEditWidgetNewDashBoardPage editImageProcess() {
    $("button[id$=':process-action-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("span.si-graphic-tablet-drawing-pen").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public SelenideElement getProcessItemName() {
    return $("span[id$=':process-item-name']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void deleteImageModeProcess() {
    $("button[id$=':process-action-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("span.si-bin-1").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getImageContainer() {
    return $("div.image-process-container");
  }

  public void switchToViewMode() {
    $("a[id='switch-to-view-mode']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("button[id*='-to-edit']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void startProcess() {
    getStartButton().click();
  }

  public void startMoreInfoLink() {
    getMoreInformationLink().click();
  }

  // ==================================
  public SelenideElement getDisabledMoreInformationLink() {
    return $("span[id$=':more-information']");
  }

  public SelenideElement getMoreInformationLink() {
    return $("a[id$=':more-information']");
  }

  public ProcessEditWidgetNewDashBoardPage editFullModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear).click();
    $("span.si-graphic-tablet-drawing-pen").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public SelenideElement getFullModeProcessName() {
    return $("span[id$=':process-item:process-name']").shouldBe(Condition.appear);
  }

  public void deleteFullModeProcess() {
    $("button[id$=':process-action-button']").shouldBe(Condition.appear).click();
    $("span.si-bin-1").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getFullModeProcessContainer() {
    return $(".process-widget.dashboard-widget-panel-container .process-widget--full");
  }

  // ==================================
  public ProcessEditWidgetNewDashBoardPage editCombinedModeProcess() {
    $(".process-grid-item__action--combined .si-pencil").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    //int x = element.getSize().width / 3;
    //int y = element.getSize().height / 3;
    //System.out.println("XY" + x + " " + y);
    //element.click(9,9);
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    return new ProcessEditWidgetNewDashBoardPage();
  }

  public SelenideElement getCombinedModeProcessName() {
    return $(".process-grid-view__name--combined").shouldBe(Condition.appear);
  }

  public void deleteCombinedModeProcess() {
    $(".process-grid-item__action--combined .si-bin-1").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
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

  public SelenideElement getCombinedModeProcessExpandLink() {
    return $(".dashboard-processes-container--combined a[id$=':expand-link']");
  }

  public SelenideElement getCombinedModeProcessCollapseLink() {
    return $(".dashboard-processes-container--combined a[id$=':collapse-link']");
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

  // ==================================
  public ElementsCollection getAllWidgetHeaders() {
    return $$("span[id$=':widget__header']");
  }

  public SelenideElement getCompactModeProcessDisabledFirstProcessItemName() {
    return $(".compact-processes-container span.ui-commandlink.process-item span[id$=':process-name-process-item']");
  }

  public SelenideElement getCompactModeProcessFirstProcessItemName() {
    return $(".compact-processes-container a.process-item span[id$=':process-name-process-item']");
  }

  public void deleteCompactModeProcess() {
    $("a[id$=':delete-widget-2']").shouldBe(Condition.appear).click();
    getRemoveWidgetDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    getRemoveWidgetButton().click();
    getRemoveWidgetDialog().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getRemoveWidgetDialog() {
    return $("div[id='remove-widget-dialog']");
  }

  private SelenideElement getRemoveWidgetButton() {
    return $("button[id='remove-widget-button']");
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

  public void expandCompactModeProcessFilterPanel() {
    SelenideElement filterLink = getCompactModeProcessFilterLink(false);
    filterLink.shouldBe(Condition.appear);

    SelenideElement expandedFilterLink = getCompactModeProcessFilterLink(true);
    expandedFilterLink.shouldBe(Condition.disappear);

    SelenideElement collapseLink = getCompactModeProcessCollapseLink();
    collapseLink.shouldBe(Condition.disappear);

    SelenideElement expandLink = getCompactModeProcessExpandLink();
    expandLink.shouldBe(Condition.appear).click();
    expandLink.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);

    collapseLink.waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    expandedFilterLink.waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    filterLink.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
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

  public void removeCompactModeProcessFilter(String filterName) {
    SelenideElement savedFilter = getManageWidgetFilterDialogFirstSavedFilter();
    savedFilter.waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldHave(Condition.attribute("data-rk", filterName));
    savedFilter.$("td").click();
    savedFilter.waitUntil(Condition.cssClass("ui-state-highlight"), DEFAULT_TIMEOUT);
    getManageWidgetFilterDialogRemoveButton().shouldBe(Condition.enabled).click();
  }

  public void openCompactModeProcessInforPanel(boolean isExpanded) {
    getCompactModeProcessInfoLink(isExpanded).shouldBe(Condition.appear).click();
    getCompactModeProcessInfoPanel(isExpanded).waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getCompactModeProcessInfoProcessTypes(boolean isExpanded) {
    return getCompactModeProcessInfoPanel(isExpanded).$$("span.dashboard-processes__type-text");
  }
}
