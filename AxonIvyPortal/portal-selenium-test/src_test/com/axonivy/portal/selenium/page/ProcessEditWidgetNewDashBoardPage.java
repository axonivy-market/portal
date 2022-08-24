package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class ProcessEditWidgetNewDashBoardPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id$='process-display-mode']";
  }

  public void selectImageModeAndSaveWidget(String processName) {
    selectImageMode();
    selectImageProcess(processName);
    clickSaveProcessWidget();
  }

  private void selectImageProcess(String processName) {
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
    selectImageProcess(processName);
    getPreviewButton().click();
    getImageModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
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
    selectImageProcess(newProcessName);
    clickSaveProcessWidget();
  }

  public void clickSaveProcessWidget() {
    $("button[id='widget-configuration-save-button']")
        .waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void previewFullModeProcess(String processName) {
    selectFullMode();
    selectFullProcess(processName);
    getPreviewButton().click();
    getFullModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
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

  public void previewCombinedModeProcess(String processName, String description) {
    selectCombinedMode();
    selectCombinedProcess(processName, description);
    getPreviewButton().click();
    getCombinedModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
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
    return $$(".combined-process-widget-button-selection .ui-button").get(0);
  }

  public SelenideElement getTasksTabDataContainer() {
    return $("div[id$=':dashboard-process-tasks-container']");
  }

  public SelenideElement getFirstTaskDisplayedDisabledStartAction() {
    return $("div[id$=':dashboard-process-tasks-container'] span.start-task-action").waitUntil(Condition.appear,
        DEFAULT_TIMEOUT);
  }

  public SelenideElement getCasesTab() {
    return $$(".combined-process-widget-button-selection .ui-button").get(1);
  }

  public SelenideElement getSelectedCasesTab() {
    return $(".combined-process-widget-button-selection .ui-button.ui-state-active");
  }

  public SelenideElement getCasesTabDataContainer() {
    return $("div[id$=':dashboard-process-cases-container']");
  }

  public void selectCasesTab() {
    getCasesTab().shouldBe(Condition.appear).click();
  }

  public SelenideElement getFirstDisplayedCaseName() {
    return $("div[id$=':dashboard-process-cases-container'] .dashboard-cases__column-small-screen-name")
        .waitUntil(Condition.exist, DEFAULT_TIMEOUT);
  }

  public void selectCombinedModeProcessAndSaveWidget(String processName) {
    selectCombinedModeProcessAndSaveWidget(processName, null);
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

  public void previewCompactModeProcess() {
    selectCompactModeFromCombinedMode();
    getPreviewButton().click();
    getCompactModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
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
    getCompactModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
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
    return $("div[id$=':widget-filter-category-panel']");
  }

  private SelenideElement getFilterApplyButton() {
    return $("button[id$=':update-command']");
  }

  public void previewCompactModeProcessFilterProcess(String processName) {
    selectCompactModeFromCombinedMode();
    selectCompactModeProcess(processName);
    getPreviewButton().click();
    getCompactModeProcessPreview().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
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

  public SelenideElement getCombinedModeProcessSelectedProcessInput() {
    return getCombinedModeProcessSelectedProcess().$("input[id$=':selected-combined-process_input']");
  }

  public SelenideElement getImageModeProcessSelectedProcessInput() {
    return getImageModeProcessSelectedProcess().$("input[id$=':selected-image-process_input']");
  }

  public SelenideElement getFullModeProcessSelectedProcessInput() {
    return getFullModeProcessSelectedProcess().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .$("input[id$=':selected-full-process_input']");
  }

  public SelenideElement getCompactModeProcessSelectedProcess() {
    return getCompactModeProcessProcessFilter().waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .$("span.ui-selectcheckboxmenu-token-label");
  }

  public SelenideElement getCompactModeProcessDisplayedCategoryFilter() {
    return getCompactModeProcessCategoryFilter().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
}
