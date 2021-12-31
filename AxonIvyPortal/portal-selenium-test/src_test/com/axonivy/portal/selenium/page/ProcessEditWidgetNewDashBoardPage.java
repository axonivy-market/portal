package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class ProcessEditWidgetNewDashBoardPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'new-widget-configuration-dialog')]";
  }

  public void selectImageModeAndSaveWidget(String processName) {
    selectImageMode();
    selectProcess(processName);
    clickSaveProcessWidget();
  }

  private void selectProcess(String processName) {
    getImageModeProcessSelectedProcess().click();
    getImageModeProcessSelectedProcess().find("input").clear();
    getImageModeProcessSelectedProcess().find("input").sendKeys(processName);
    $("tr[data-item-label='" + processName + "']").click();
  }

  public void selectImageMode() {
    selectProcessMode("Image mode");
    getImageModeProcessSelectedProcess().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getImageModeProcessSelectedProcess() {
    return $("span[id$=':selected-image-process']");
  }

  public void previewImageModeProcess(String processName) {
    selectImageMode();
    selectProcess(processName);
    getPreviewButton().click();
  }

  public SelenideElement getImageModeProcessPreview() {
    return $(".widget-preview .widget-preview--image-width");
  }

  public SelenideElement getImageModeProcessImage() {
    return $("img.image-process-item-image");
  }

  public SelenideElement getStartButton() {
    return $("button[id$=':start-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getMoreInformationLink() {
    return $("span[id$=':more-information']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void changeImageModeProcessAndSaveWidget(String newProcessName) {
    selectProcess(newProcessName);
    clickSaveProcessWidget();
  }

  private void clickSaveProcessWidget() {
    $("button[id='widget-configuration-save-button']").click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  // ========================================================================================
  public void previewFullModeProcess(String processName) {
    selectFullMode();
    selectFullProcess(processName);
    getPreviewButton().click();
  }

  public void selectFullMode() {
    selectProcessMode("Full mode");
    getFullModeProcessSelectedProcess().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void selectProcessMode(String mode) {
    getProcessDisplayMode().waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("li[data-label='" + mode + "']").click();
  }

  public SelenideElement getFullModeProcessSelectedProcess() {
    return $("span[id$=':selected-full-process']");
  }

  private void selectFullProcess(String processName) {
    getFullModeProcessSelectedProcess().click();
    getFullModeProcessSelectedProcess().find("input").clear();
    getFullModeProcessSelectedProcess().find("input").sendKeys(processName);
    $("tr[data-item-label='" + processName + "']").click();
  }

  public SelenideElement getPreviewButton() {
    return $("button[id$='preview-button']");
  }

  public SelenideElement getFullModeProcessPreview() {
    return $(".widget-preview .widget-preview--full");
  }

  public SelenideElement getDisabledMoreInformationLink() {
    return $("span[id$=':more-information']");
  }

  public void selectFullModeProcessAndSaveWidget(String processName) {
    selectFullMode();
    selectFullProcess(processName);
    clickSaveProcessWidget();
  }

  public void changeFullModeProcessAndSaveWidget(String newProcessName) {
    selectFullProcess(newProcessName);
    clickSaveProcessWidget();
  }

  // ========================================================================================
  public void previewCombinedModeProcess(String processName, String description) {
    selectCombinedMode();
    selectCombinedProcess(processName, description);
    getPreviewButton().click();
  }

  public void selectCombinedMode() {
    selectProcessMode("Combined mode");
    getCombinedModeProcessSelectedProcess().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCombinedModeProcessSelectedProcess() {
    return $("span[id$=':selected-combined-process']");
  }

  private void selectCombinedProcess(String processName, String description) {
    getCombinedModeProcessSelectedProcess().click();
    getCombinedModeProcessSelectedProcess().find("input").clear();
    getCombinedModeProcessSelectedProcess().find("input").sendKeys(processName);
    String processSelector = String.format("tr[data-item-label='%s']", processName);
    if (description != null) {
      String descriptionSelector = String.format(" span[title='%s']", description);
      processSelector = processSelector.concat(descriptionSelector);
    }
    $(processSelector).click();
  }

  public SelenideElement getCombinedModeProcessPreview() {
    return $(".widget-preview .widget-preview--combined");
  }

  public SelenideElement getSelectedTasksTab() {
    return $(".combined-process-widget__button-tabs.first-tab-button.active-button-tab");
  }

  public SelenideElement getTasksTabDataContainer() {
    return $("div[id$=':dashboard-process-tasks-container']");
  }

  public SelenideElement getFirstTaskDisabledStartAction() {
    return $("div[id$=':dashboard-process-tasks-container'] span.start-task-action");
  }

  public SelenideElement getCasesTab() {
    return $(".combined-process-widget__button-tabs.last-tab-button");
  }

  public SelenideElement getSelectedCasesTab() {
    return $(".combined-process-widget__button-tabs.last-tab-button.active-button-tab");
  }

  public SelenideElement getCasesTabDataContainer() {
    return $("div[id$=':dashboard-process-cases-container']");
  }

  public SelenideElement getFirstCaseName() {
    return $("div[id$=':dashboard-process-cases-container'] .dashboard-cases__column-small-screen-name");
  }

  public void selectCombinedModeProcessAndSaveWidget(String processName, String description) {
    selectCombinedMode();
    selectCombinedProcess(processName, description);
    clickSaveProcessWidget();
  }

  public void changeCombinedModeProcessAndSaveWidget(String newProcessName) {
    selectCombinedProcess(newProcessName, null);
    clickSaveProcessWidget();
  }

  // ========================================================================================
  public void previewCompactModeProcess() {
    selectCompactModeFromCombinedMode();
    getPreviewButton().click();
  }

  private void selectCompactModeFromCombinedMode() {
    selectProcessMode("Combined mode");
    selectCompactMode();
  }

  public void selectCompactMode() {
    selectProcessMode("Compact mode");
    getCompactModeWidgetTitle().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    getCompactModeProcessCategoryFilter().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    getCompactModeProcessProcessFilter().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getCompactModeWidgetTitle() {
    return $("input[id$=':widget-title']");
  }

  private SelenideElement getProcessDisplayMode() {
    return $("div[id$=':process-display-mode']");
  }

  public SelenideElement getCompactModeProcessPreviewLoading() {
    return $(".widget-preview .widget-preview--compact span[id$=':loading']");
  }

  public SelenideElement getCompactModeProcessPreview() {
    return $(".widget-preview .widget-preview--compact div[id$=':dashboard-processes-container']");
  }

  public SelenideElement getCompactModeProcessDisabledFirstProcessItem() {
    return $(".widget-preview--compact span.ui-commandlink.process-item");
  }

  public SelenideElement getCompactModeProcessDisabledFirstProcessItemName() {
    return $(".widget-preview--compact span.ui-commandlink.process-item span[id$=':process-name-process-item']");
  }

  public void previewCompactModeProcessFilterCategory(String category) {
    selectCompactModeFromCombinedMode();
    selectCompactModeCategory(category);
    getPreviewButton().click();
  }

  private void selectCompactModeCategory(String category) {
    getCompactModeProcessCategoryFilter().click();

    ElementsCollection categories = getCompactModeProcessCategoryFilterPanel()
        .waitUntil(Condition.appear, DEFAULT_TIMEOUT).$$(".ui-treenode-label");
    categories.filter(Condition.exactTextCaseSensitive("All Categories")).first().click();
    categories.filter(Condition.exactTextCaseSensitive(category)).first()
        .waitUntil(Condition.not(Condition.cssClass("ui-state-highlight")), DEFAULT_TIMEOUT).click();

    getFilterApplyButton().click();
    getCompactModeProcessCategoryFilterPanel().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessCategoryFilter() {
    return $("input[id$=':widget-filter-category']");
  }

  private SelenideElement getCompactModeProcessCategoryFilterPanel() {
    return $("span[id$=':compact-filter'] div[id$=':widget-filter-category-panel']");
  }

  private SelenideElement getFilterApplyButton() {
    return $("button[id$=':update-command']");
  }

  public void previewCompactModeProcessFilterProcess(String processName) {
    selectCompactModeFromCombinedMode();
    selectCompactModeProcess(processName);
    getPreviewButton().click();
  }

  private void selectCompactModeProcess(String processName) {
    getCompactModeProcessProcessFilter().click();

    getCompactModeProcessProcessFilterPanel().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .$$(".ui-selectcheckboxmenu-item label").filter(Condition.exactTextCaseSensitive(processName)).first().click();

    getCompactModeProcessProcessFilterPanel().$(".ui-selectcheckboxmenu-close").click();
    getCompactModeProcessCategoryFilterPanel().waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessProcessFilter() {
    return $("div[id$=':processes-list']");
  }

  private SelenideElement getCompactModeProcessProcessFilterPanel() {
    return $("div[id$=':processes-list_panel']");
  }

  public void changeCompactModeProcessAndSaveWidget(String category, String processName) {
    selectCompactModeFromCombinedMode();
    getCompactModeWidgetTitle().clear();
    getCompactModeWidgetTitle().sendKeys(processName);
    selectCompactModeCategory(category);
    selectCompactModeProcess(processName);
    clickSaveProcessWidget();
  }

  public void changeToCompactModeProcess(String category, String processName) {
    selectCompactMode();
    getCompactModeWidgetTitle().clear();
    getCompactModeWidgetTitle().sendKeys(processName);
    selectCompactModeCategory(category);
    selectCompactModeProcess(processName);
  }
}
