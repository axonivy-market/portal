package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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
    $("span[id$=':selected-image-process']").find("input").clear();
    $("span[id$=':selected-image-process']").find("input").sendKeys(processName);
    $("tr[data-item-label='" + processName + "']").click();
  }

  private void selectImageMode() {
    getProcessDisplayMode().waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("li[data-label='Image mode']").click();
    $("span[id$=':selected-image-process']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
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

  private void selectFullMode() {
    getProcessDisplayMode().waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
    $("li[data-label='Full mode']").click();
    $("span[id$=':selected-full-process']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  private void selectFullProcess(String processName) {
    $("span[id$=':selected-full-process']").find("input").clear();
    $("span[id$=':selected-full-process']").find("input").sendKeys(processName);
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
  public void previewCombinedModeProcess(String processName) {
    selectCombinedMode();
    selectCombinedProcess(processName);
    getPreviewButton().click();
  }

  private void selectCombinedMode() {
    getProcessDisplayMode().waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
    $("li[data-label='Combined mode']").click();
    $("span[id$=':selected-combined-process']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  private void selectCombinedProcess(String processName) {
    $("span[id$=':selected-combined-process']").find("input").clear();
    $("span[id$=':selected-combined-process']").find("input").sendKeys(processName);
    $("tr[data-item-label='" + processName + "']").click();
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

  public void selectCombinedModeProcessAndSaveWidget(String processName) {
    selectCombinedMode();
    selectCombinedProcess(processName);
    clickSaveProcessWidget();
  }

  public void changeCombinedModeProcessAndSaveWidget(String newProcessName) {
    selectCombinedProcess(newProcessName);
    clickSaveProcessWidget();
  }

  // ========================================================================================
  public void previewCompactModeProcess() {
    selectCompactMode();
    getPreviewButton().click();
  }

  private void selectCompactMode() {
    getProcessDisplayMode().waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
    $("li[data-label='Combined mode']").click();
    getProcessDisplayMode().click();
    $("li[data-label='Compact mode']").click();
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
    selectCompactMode();
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

  private SelenideElement getCompactModeProcessCategoryFilter() {
    return $("input[id$=':widget-filter-category']");
  }

  private SelenideElement getCompactModeProcessCategoryFilterPanel() {
    return $("span[id$=':compact-filter'] div[id$=':widget-filter-category-panel']");
  }

  private SelenideElement getFilterApplyButton() {
    return $("button[id$=':update-command']");
  }

  public void previewCompactModeProcessFilterProcess(String processName) {
    selectCompactMode();
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

  private SelenideElement getCompactModeProcessProcessFilter() {
    return $("div[id$=':processes-list']");
  }

  private SelenideElement getCompactModeProcessProcessFilterPanel() {
    return $("div[id$=':processes-list_panel']");
  }

  public void changeCompactModeProcessAndSaveWidget(String category, String processName) {
    selectCompactMode();
    getCompactModeWidgetTitle().clear();
    getCompactModeWidgetTitle().sendKeys(processName);
    selectCompactModeCategory(category);
    selectCompactModeProcess(processName);
    clickSaveProcessWidget();
  }
}
