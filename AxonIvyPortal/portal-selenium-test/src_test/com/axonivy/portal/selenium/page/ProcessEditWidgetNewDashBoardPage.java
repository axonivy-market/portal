package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class ProcessEditWidgetNewDashBoardPage extends TemplatePage {

  private String processEditWidgetId;

  public ProcessEditWidgetNewDashBoardPage() {
    this("div[id='new-widget-configuration-dialog']");
  }

  public ProcessEditWidgetNewDashBoardPage(String processEditWidgetId) {
    this.processEditWidgetId = processEditWidgetId;
  }

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
    getImageModeProcessSelectedProcess().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getImageModeProcessSelectedProcess() {
    return $("span[id$=':selected-image-process']");
  }

  public void previewImageModeProcess(String processName) {
    selectImageMode();
    selectImageProcess(processName);
    getPreviewButton().click();
    getImageModeProcessPreview().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getImageModeProcessPreview() {
    return $(".widget-preview .widget-preview--image-width");
  }

  public SelenideElement getImageModeProcessImage() {
    return $("img.image-process-item-image");
  }

  public SelenideElement getStartButton() {
    return $("button[id$=':start-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getMoreInformationLink() {
    return $("span[id$=':more-information']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void changeImageModeProcessAndSaveWidget(String newProcessName) {
    selectImageProcess(newProcessName);
    clickSaveProcessWidget();
  }

  public void clickSaveProcessWidget() {
    $("button[id='widget-configuration-save-button']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void previewFullModeProcess(String processName) {
    selectFullMode();
    selectFullProcess(processName);
    getPreviewButton().click();
    getFullModeProcessPreview().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void selectFullMode() {
    selectProcessMode("Full mode");
    getFullModeProcessSelectedProcess().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void selectProcessMode(String mode) {
    getProcessDisplayMode().shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
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
    waitUntilElementToBeClickable($("button[id='widget-configuration-save-button']"));
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
    getCombinedModeProcessPreview().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void selectCombinedMode() {
    selectProcessMode("Combined mode");
    getCombinedModeProcessSelectedProcess().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
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
    return $(".combined-process-widget-button-selection .ui-button.ui-state-active input[id$=':0']");
  }

  public SelenideElement getTasksTabDataContainer() {
    return $("div[id$=':dashboard-process-tasks-container']");
  }

  public SelenideElement getFirstTaskDisplayedDisabledStartAction() {
    return $("div[id$=':dashboard-process-tasks-container'] span.start-task-action").shouldBe(Condition.appear,
        DEFAULT_TIMEOUT);
  }

  public SelenideElement getCasesTab() {
    return $(".combined-process-widget-button-selection .ui-button input[id$=':1']").parent();
  }

  public SelenideElement getSelectedCasesTab() {
    return $(".combined-process-widget-button-selection .ui-button.ui-state-active input[id$=':1']");
  }

  public SelenideElement getCasesTabDataContainer() {
    return $("div[id$=':dashboard-process-cases-container']");
  }

  public void selectCasesTab() {
    getCasesTab().shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getFirstDisplayedCaseName() {
    return $("div[id$=':dashboard-process-cases-container'] .dashboard-cases__column-small-screen-name")
        .shouldBe(Condition.exist, DEFAULT_TIMEOUT);
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
    getCompactModeProcessPreview().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  private void selectCompactModeFromCombinedMode() {
    selectProcessMode("Combined mode");
    selectCompactMode();
  }

  public void selectCompactMode() {
    selectProcessMode("Compact mode");
    getCompactModeWidgetTitle().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    getCompactModeProcessCategoryFilter().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    getCompactModeProcessProcessFilter().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void selectCompactProcessSorting(String sorting) {
    getCompactModeSorting().shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("li[data-label='" + sorting + "']").click();
  }

  private SelenideElement getCompactModeSorting() {
    return $("div[id$=':processes-sorting']");
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
    getCompactModeProcessPreview().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  private void selectCompactModeCategory(String category) {
    getCompactModeProcessCategoryFilter().click();

    ElementsCollection categories = getCompactModeProcessCategoryFilterPanel()
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$$(".ui-treenode-label");
    categories.filter(Condition.exactTextCaseSensitive("All Categories")).first().click();
    categories.filter(Condition.exactTextCaseSensitive(category)).first()
        .shouldBe(Condition.not(Condition.cssClass("ui-state-highlight")), DEFAULT_TIMEOUT).click();

    getFilterApplyButton().click();
    getCompactModeProcessCategoryFilterPanel().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
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
    getCompactModeProcessPreview().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  private void selectCompactModeProcess(String processName) {
    getCompactModeProcessProcessFilter().click();

    getCompactModeProcessProcessFilterPanel().shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$$(".ui-selectcheckboxmenu-item label").filter(Condition.exactTextCaseSensitive(processName)).first().click();

    getCompactModeProcessProcessFilterPanel().$(".ui-selectcheckboxmenu-close").click();
    getCompactModeProcessCategoryFilterPanel().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
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

  public void changeProcessTitle(String name) {
	getCompactModeWidgetTitle().clear();
    getCompactModeWidgetTitle().sendKeys(name);
  }

  public void changeCompactModeProcessAndSaveWidget(String category) {
    selectCompactModeFromCombinedMode();
    getCompactModeWidgetTitle().clear();
    getCompactModeWidgetTitle().sendKeys(category);
    selectCompactModeCategory(category);
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
    return getFullModeProcessSelectedProcess().shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("input[id$=':selected-full-process_input']");
  }

  public SelenideElement getCompactModeProcessSelectedProcess() {
    return getCompactModeProcessProcessFilter().shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("span.ui-selectcheckboxmenu-token-label");
  }

  public SelenideElement getCompactModeProcessDisplayedCategoryFilter() {
    return getCompactModeProcessCategoryFilter().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void clickOnProcesses() {
    $("div[id$=':processes-list']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
  }

  public boolean getProcessByName(String processName) {
    return $(".ui-selectcheckboxmenu-items").$$("li.ui-selectcheckboxmenu-item").asDynamicIterable().stream()
        .map(SelenideElement::getText).anyMatch(text -> text.equalsIgnoreCase(processName));
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

  public void save() {
    $(processEditWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='widget-configuration-save-button']")
        .shouldBe(getClickableCondition()).click();
    $("[id$='task-component:loading']").shouldBe(disappear, DEFAULT_TIMEOUT);
    $("[id$='process-process_1:widget-content']").shouldBe(Condition.interactable, DEFAULT_TIMEOUT);
  }
  
  public void dragAndDropProcess(int fromIndex, int toIndex) {
    Actions a = new Actions(WebDriverRunner.getWebDriver());
    SelenideElement processList = $("ul.ui-widget-content");
    processList.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    ElementsCollection findAll = processList.findAll("li.ui-orderlist-item");
    if (findAll.size() > toIndex) {
      SelenideElement fromElement = findAll.get(fromIndex);
      SelenideElement toElement = findAll.get(toIndex);
      a.dragAndDrop(fromElement, toElement).build().perform();
    }
  }
  
  public SelenideElement getPreviewProcessElement(int index) {
    return $("div.compact-processes-container").findAll(".process-start-list-item").get(index);
  }

  public SelenideElement getConfigurationFilterContainer() {
    return $("div#new-widget-configuration-dialog").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='filter-container']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getExpandModeCheckbox() {
    return getConfigurationFilterContainer().$("span[id$='fullscreen-mode-group']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("div[class*='ui-inputgroup']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("div[id$='fullscreen-option']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void clickOnExpandModeCheckbox() {
    getExpandModeCheckbox().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getWidgetInfoIconCheckbox() {
    return getConfigurationFilterContainer().$("span[id$='widget-info-icon-group']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("div[class*='ui-inputgroup']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("div[id$='widget-info']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("div[class*='ui-chkbox-box']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("span");
  }

  public void clickOnWidgetInfoIconCheckbox() {
    getWidgetInfoIconCheckbox().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickOnQuickSearchCheckbox() {
    getConfigurationFilterContainer().$("span[id$='quick-search-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("div[id$='quick-search']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

}
