package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import org.openqa.selenium.interactions.Action;
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
    clickPreviewButton();
    clickSaveProcessWidget();
  }

  private void selectImageProcess(String processName) {
    getImageModeProcessSelectedProcess().click();
    getImageModeProcessSelectedProcess().find("input").clear();
    getImageModeProcessSelectedProcess().find("input").sendKeys(processName);
    $("tr[data-item-label='" + processName + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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
    getImageModeProcessPreview();
  }

  public SelenideElement getImageModeProcessPreview() {
    $(".widget-preview .widget-preview--image-width").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("[id$=':process-item-name']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    return $(".widget-preview .widget-preview--image-width").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
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
    $("button[id='widget-configuration-save-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void previewFullModeProcess(String processName) {
    selectFullMode();
    selectFullProcess(processName);
    getPreviewButton().click();
    getFullModeProcessPreview();
  }

  public void selectFullMode() {
    selectProcessMode("Full mode");
    getFullModeProcessSelectedProcess().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void selectProcessMode(String mode) {
    getProcessDisplayMode().click();
    $("li[data-label='" + mode + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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
    $(".widget-preview .widget-preview--full").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("[id$='process-grid-item:process-item:start-process-link']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    return $(".widget-preview .widget-preview--full").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getDisabledMoreInformationLink() {
    return getProcessActionMenu().$$("span[class$='ui-menuitem-text']").filter(Condition.text("More Information")).first();
  }
  
  private SelenideElement getProcessActionMenu() {
    $("div[class*='image-process-item-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("button[id$='process-action-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $("div[id$='process-action-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
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
    getCombinedModeProcessPreview();
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
    $(".widget-preview .widget-preview--combined")
        .$("div[id$='process-task-widget-component:dashboard-process-tasks-container']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $(".widget-preview .widget-preview--combined").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getSelectedTasksTab() {
    return $(".combined-process-widget-button-selection .ui-button.ui-state-active input[id$=':0']");
  }

  public SelenideElement getTasksTabDataContainer() {
    return $("div[id$=':dashboard-process-tasks-container']");
  }

  public SelenideElement getFirstTaskDisplayedDisabledStartAction() {
    return $("div[id$=':dashboard-process-tasks-container'] .start-task-action")
        .shouldBe(Condition.appear,
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
    getCasesTab().shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
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
    getCompactModeProcessPreview();
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

  public SelenideElement getProcessDisplayMode() {
    return $("div[id$=':process-display-mode']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessPreviewLoading() {
    return $(".widget-preview .widget-preview--compact span[id$=':loading']");
  }

  public SelenideElement getCompactModeProcessPreview() {
    $(".widget-preview .widget-preview--compact .widget-preview__header").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return $(".widget-preview .widget-preview--compact div[id$=':dashboard-processes-container']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getCompactModeProcessDisabledFirstProcessItem() {
    return $(".widget-preview--compact .ui-commandlink.process-item");
  }

  public SelenideElement getCompactModeProcessDisabledFirstProcessItemName() {
    return $(
        ".widget-preview--compact .ui-commandlink.process-item span[id$=':process-name-process-item']");
  }

  public void previewCompactModeProcessFilterCategory(String category) {
    selectCompactModeFromCombinedMode();
    selectCompactModeCategory(category);
    getPreviewButton().click();
    getCompactModeProcessPreview();
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
    getCompactModeProcessPreview();
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
    SelenideElement processList = $("ul.ui-widget-content");
    processList.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    ElementsCollection findAll = processList.findAll("li.ui-orderlist-item");
    if (findAll.size() > toIndex) {
      SelenideElement fromElement = findAll.get(fromIndex)
          .$(".process-start-list-item").shouldBe(clickable(), DEFAULT_TIMEOUT);
      SelenideElement toElement = findAll.get(toIndex)
          .$(".process-start-list-item").shouldBe(clickable(), DEFAULT_TIMEOUT);

      Actions builder = new Actions(WebDriverRunner.getWebDriver());
      Action dragAndDrop = builder.clickAndHold(fromElement).pause(500)
          .moveToElement(toElement, 50, 20).pause(500).release(toElement)
          .pause(500)
          .build();
      dragAndDrop.perform();
    }
  }

  public SelenideElement getPreviewProcessElement(int index) {
    return $("div.compact-processes-container").findAll(".process-start-list-item").get(index);
  }

  public SelenideElement getTranslationOverlayPanel(int index) {
    SelenideElement translationOverlay = $(String.format("div[id$=':%s:overlay-panel-input']", index));
    waitUntilElementToBeClickable(translationOverlay);
    translationOverlay.shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    return translationOverlay;
  }

  public void selectProcessForCombinedMode(String processName) {
    $("[id='widget-configuration-form:new-widget-configuration-component:selected-combined-process_input']")
        .shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(processName);

    $("[id='widget-configuration-form:new-widget-configuration-component:selected-combined-process_panel']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("tr").get(0).shouldBe(appear, DEFAULT_TIMEOUT).click();

    clickDialogTitle();
    $("[id='widget-configuration-form:new-widget-configuration-component:selected-combined-process_panel']")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void selectProcessesForCompactMode(List<String> processes) {
    $("[id='widget-configuration-form:new-widget-configuration-component:processes-list']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$("ul").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id='widget-configuration-form:new-widget-configuration-component:processes-list_panel']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("li").asFixedIterable().forEach(process -> {
          if (processes.contains(process.$("label").getText())) {
            process.$(".ui-chkbox-box").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
          }
        });

    clickDialogTitle();
    $("div[id='widget-configuration-form:new-widget-configuration-component:processes-list_panel']").shouldBe(disappear,
        DEFAULT_TIMEOUT);
  }

  public void selectProcessForFullMode(String processName) {
    $("[id='widget-configuration-form:new-widget-configuration-component:selected-full-process_input']")
        .shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(processName);

    $("[id='widget-configuration-form:new-widget-configuration-component:selected-full-process_panel']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("tr").get(0).shouldBe(appear, DEFAULT_TIMEOUT).click();

    clickDialogTitle();
    $("[id='widget-configuration-form:new-widget-configuration-component:selected-full-process_panel']")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void selectProcessForImageMode(String processName) {
    $("[id='widget-configuration-form:new-widget-configuration-component:selected-image-process_input']")
        .shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(processName);
    $("[id='widget-configuration-form:new-widget-configuration-component:selected-image-process_panel']")
        .shouldBe(appear, DEFAULT_TIMEOUT).$$("tr").get(0).shouldBe(appear, DEFAULT_TIMEOUT).click();

    clickDialogTitle();
    $("[id='widget-configuration-form:new-widget-configuration-component:selected-image-process_panel']")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  private void clickDialogTitle() {
    $("[id='new-widget-configuration-dialog_title']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

  public void clickPreviewButton() {
    getPreviewButton().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getConfigurationDialog() {
    return $("div[id='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void closeConfigurationDialog() {
    getConfigurationDialog().$(".ui-dialog-footer").$("a").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
  
  public void enableQuickSearchCheckbox() {
     SelenideElement quickSearchCheckBox = getConfigurationFilterContainer().$("span[id$='quick-search-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("div[id$='quick-search']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
     if ($("input[id$='widget-configuration-form:new-widget-configuration-component:quick-search_input']").isSelected() == false) {
       quickSearchCheckBox.click();
     }
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
}
