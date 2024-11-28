package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TaskWidgetPage extends TemplatePage {
  private String taskWidgetId;
  private static final String CLASS = "class";
  private static final String KEYWORD_FILTER_SELECTOR_EXPANDED_MODE =
      "input[id='task-widget:expanded-mode-filter-form:expanded-mode-filter-container:ajax-keyword-filter']";

  private static final String ID_END = "*[id$='";
  private static final String TASK_STATE_COMPONENT_ID =
      "task-widget:task-list-scroller:%d:task-item:task-state-component:task-state";
  private static final String TASK_ACTION = "horizontal-task-actions";

  public TaskWidgetPage() {
    this("task-widget");
  }

  public TaskWidgetPage(String taskWidgetId) {
    this.taskWidgetId = taskWidgetId;
  }

  @Override
  protected String getLoadedLocator() {
    return ".js-task-widget-header";
  }

  public void openTask(String taskName) {
    $("div[id='task-widget:task-view-container']").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("div[id='task-widget:task-view-container'] ul li div[id$=':task-item:task-start'] div.task-start-info span")
        .filter(text(taskName)).first().click();
  }

  public void openAdvancedFilter(String filterName, String filterIdName) {
    $("a[id$='filter-add-action']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$='filter-add-action']").click();
    $("table[id$='task-widget:filter-add-form:filter-selection']").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("table[id$='task-widget:filter-add-form:filter-selection'] label").filter(text(filterName)).first().click();
    $("button[id$='task-widget:filter-add-form:update-filter-selected-command']").click();
    $("div[id$='task-widget:filter-add-panel']").shouldBe(disappear, DEFAULT_TIMEOUT);
    $("span[id$='" + filterIdName + "-filter:advanced-filter-component").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void filterTasksByCreatedDate(String fromCreatedDate, String toCreatedDate) {
    $("button[id$='created-filter:filter-open-form:advanced-filter-command']").click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").shouldBe(appear, DEFAULT_TIMEOUT);
    $("input[id$='created-filter:filter-input-form:from-created-calendar_input'").sendKeys(fromCreatedDate);
    closePanelDatePicker($("div[id$='created-filter:filter-input-form:from-created-calendar_panel'"));
    $("input[id$='created-filter:filter-input-form:to-created-calendar_input'").sendKeys(toCreatedDate);
    closePanelDatePicker($("div[id$='created-filter:filter-input-form:to-created-calendar_panel'"));
    $("button[id$='created-filter:filter-input-form:update-command']").click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void filterFirstApp() {
    $("button[id$=':application-filter:filter-open-form:advanced-filter-command']").click();
    $("div[id$=':application-filter:filter-input-form:advanced-filter-panel'").shouldBe(appear, DEFAULT_TIMEOUT);
    final ElementsCollection checkboxLabel = $$("span.ui-chkbox-label");
    for (int i = 0; i < checkboxLabel.size(); i++) {
      if ("Select All".equals(checkboxLabel.get(i).getText())) {
        checkboxLabel.get(i).click();
      }
    }
    $("button[id$=':application-filter:filter-input-form:update-command']").click();
    $("div[id$=':application-filter:filter-input-form:advanced-filter-panel'").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  private void closePanelDatePicker(SelenideElement element) {
    Selenide.executeJavaScript("arguments[0].style.display = 'none'", element);
  }

  public ElementsCollection countTasks() {
    return $$("div[id$='task-widget:task-view-container'] ul li");
  }

  public void runTaskWithRunTheTaskBehaviour(int position) {
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:runnable-task-info']").shouldBe(appear,
        DEFAULT_TIMEOUT);
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:runnable-task-info']").click();
  }

  public void openTaskWithAccessTaskDetailsBehaviour(int position) {
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:task-info']").shouldBe(appear,
        DEFAULT_TIMEOUT);
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:task-info']").click();
  }

  public SelenideElement getFilterTasksByKeyword() {
    return $("input[id='task-widget:expanded-mode-filter-form:expanded-mode-filter-container:ajax-keyword-filter']");
  }

  public void filterTasksBy(String keyword) {
    getFilterTasksByKeyword().shouldBe(appear, DEFAULT_TIMEOUT).clear();
    getFilterTasksByKeyword().click();
    getFilterTasksByKeyword().sendKeys(keyword);
    getFilterTasksByKeyword().hover();
    $("div[id$='task-widget:task-view-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitTillNameOfFirstTaskToBe(String name) {
    $("div[id$='task-widget:task-view-container']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("[id='task-widget:task-list-scroller:0:task-item:task-name-component:task-name']")
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(Condition.text(name), DEFAULT_TIMEOUT);
  }

  public void clickOnTaskActionLink(int taskIndex) {
    $(String.format("a[id$=':%d:task-item:task-action:additional-options:task-side-steps-menu']", taskIndex))
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $(String.format("div[id$=':%d:task-item:task-action:additional-options:side-steps-panel']", taskIndex))
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public TaskTemplatePage clickOnSideStepAction(int taskIndex, int sideStepIndex) {
    String sideStepsId = String.format(
        "task-widget:task-list-scroller:%d:task-item:task-action:additional-options:task-additional-actions",
        taskIndex);
    SelenideElement sideStepPanel = $("[id$='" + sideStepsId + "']");
    ElementsCollection sideSteps = sideStepPanel.findAll(By.className("option-item"));
    sideSteps.get(sideStepIndex).click();
    return new TaskTemplatePage();
  }

  private void openTriggerEscalationDialog() {
    $("a[id$='task-trigger-escalation-command']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id$='escalation-task-confirmation-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void triggerEscalation() {
    openTriggerEscalationDialog();
    $("button[id$='confirm-escalation']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
  }

  public void openTaskDelegationDialog() {
    $("a[id$='task-delegate-command']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
    $$("div[id$='task-delegate-dialog']").shouldBe(CollectionCondition.size(1), DEFAULT_TIMEOUT);
    $("div[id$='task-delegate-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public boolean isDelegateTypeDisabled(int index) {
    return getDelegateType(index).getDomAttribute("class").contains("ui-state-disabled");
  }

  public boolean isDelegateTypeAvailable() {
    return $("div[id$=':task-delegate-form:activator-panel']").isDisplayed();
  }

  public String getCannotDelegateText() {
    return $("div[id$='task-delegate-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$(".ui-dialog-content span")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).getText();
  }

  public SelenideElement getDelegateType(int index) {
    return $("div[id$=':task-delegate-form:activator-panel']").$$(".ui-radiobutton-box").get(index)
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public boolean isDelegateListSelectionAvailable() {
    return $("div[id$='select-delegate-panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).exists();
  }

  public SelenideElement getTaskState(int taskRowIndex) {
    return $(String.format("[id='task-widget:task-list-scroller:%d:task-item:task-state-component:task-state']",
        taskRowIndex)).shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("span");
  }

  public String getPriorityOfTask(int taskIndex) {
    var priorityClassName =
        $(String.format("span[id$=':%d:task-item:task-priority-component:task-priority']", taskIndex))
            .shouldBe(appear, DEFAULT_TIMEOUT).$("span.priority-icon").$("i.priority").attr("class");
    if (StringUtils.isBlank(priorityClassName)) {
      return priorityClassName;
    }
    if (priorityClassName.contains("low-priority")) {
      return "low";
    } else if (priorityClassName.contains("normal-priority")) {
      return "normal";
    } else if (priorityClassName.contains("high-priority")) {
      return "high";
    } else {
      return "exception";
    }
  }

  public SelenideElement getResponsibleAvatar() {
    return $(".security-member-container > .has-avatar > .ui-avatar").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitTillOnlyOneTaskAppear() {
    $$("div[id$='task-widget:task-view-container'] ul li").shouldBe(CollectionCondition.size(1), DEFAULT_TIMEOUT);
  }

  public String getRelatedCase() {
    return findElementByCssSelector("a[id$='related-case']").shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public void filterTasksInExpandedModeBy(String keyword, int... expectedNumberOfTasksAfterFiltering) {
    waitForElementDisplayed(By.cssSelector(KEYWORD_FILTER_SELECTOR_EXPANDED_MODE), true);
    SelenideElement keywordFilter = $(KEYWORD_FILTER_SELECTOR_EXPANDED_MODE);
    keywordFilter.clear();
    keywordFilter.click();
    keywordFilter.sendKeys(keyword);
    waitForNumberOfTasks(expectedNumberOfTasksAfterFiltering);
  }

  private void waitForNumberOfTasks(int... expectedNumberOfTasksAfterFiltering) {
    int expectedNumber = getExpectedNumberOfTasks(expectedNumberOfTasksAfterFiltering);
    WaitHelper.assertTrueWithWait(() -> this.countTasks().size() == expectedNumber);
  }

  public void startTaskWithoutUI(int index) {
    waitTaskAppearThenClick(index);
  }

  private int getExpectedNumberOfTasks(int... expectedNumberOfTasksAfterFiltering) {
    int expectedNumber;
    if (expectedNumberOfTasksAfterFiltering.length == 0) {
      expectedNumber = 1;
    } else {
      expectedNumber = expectedNumberOfTasksAfterFiltering[0];
    }
    return expectedNumber;
  }

  public void waitTaskAppearThenClick(int index) {
    SelenideElement taskStartElement =
        findElementById(taskWidgetId + ":task-list-scroller").$$(By.className("start-task-action")).get(index);
    if (taskStartElement.getAttribute("id").contains(":task-action:resume-task-action-component")) {
      waitForElementClickableThenClick(taskStartElement);
      resetResumedTask();
    } else {
      String cssSelector = String.format("a[id$='%d:task-item:task-action:task-action-component']", index);
      refreshAndWaitElement(cssSelector);
      waitForElementClickableThenClick($(cssSelector));
    }
  }

  public void resetResumedTask() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-task-dialog_content']"), true);
    waitForElementClickableThenClick($("[id$=':reset-task-form:reset-task-button']"));
  }

  public void reserveTask(int taskId) {
    String reserveCommandButton = String.format(
        taskWidgetId + ":task-list-scroller:%d:task-item:task-action:additional-options:task-reserve-command", taskId);
    waitForElementClickableThenClick($(By.id(reserveCommandButton)));
  }

  public void resetTask(int taskId) {
    String resetButton = String.format(
        taskWidgetId + ":task-list-scroller:%d:task-item:task-action:additional-options:task-reset-command", taskId);
    waitForElementClickableThenClick($(By.id(resetButton)));
  }

  public void filterByResponsible(String text) {
    waitForElementDisplayed(By.cssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"),
        true);
    waitForElementClickableThenClick(
        $(By.cssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command']")));
    SelenideElement input = $("input[id$='responsible-filter:filter-input-form:responsible_input']");
    input.shouldBe(appear, DEFAULT_TIMEOUT).clear();
    input.click();
    input.sendKeys(text);
    waitForElementClickableThenClick($("span[id$='responsible-filter:filter-input-form:responsible_panel'] .gravatar"));
    $("span[id$='responsible-filter:filter-input-form:responsible_panel'] .gravatar").shouldBe(disappear,
        DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(
        $(By.cssSelector("button[id$='responsible-filter:filter-input-form:update-command']")));
  }

  public void openStateFilter() {
    waitForElementClickableThenClick(
        $(By.cssSelector("button[id$='state-filter:filter-open-form:advanced-filter-command']")));
    waitForElementDisplayed(By.cssSelector("[id$='state-filter:filter-input-form:state-selection']"), true);
  }

  private SelenideElement getStateFilterPanel() {
    return $("div[id$='state-filter:filter-input-form:advanced-filter-panel']");
  }

  public void clickOnTaskStatesAndApply(List<String> states) {
    openStateFilter();
    List<String> labelList = findChildElementsByTagName(getStateFilterPanel(), "label").asFixedIterable().stream()
        .map(SelenideElement::getText).collect(Collectors.toList());
    List<Integer> statesSelectedIndex = new ArrayList<>();
    states.forEach(state -> {
      int stateIndex = labelList.indexOf(state);
      if (stateIndex >= 0 && stateIndex < labelList.size()) {
        statesSelectedIndex.add(stateIndex);
      }
    });
    clickOnUnCheckSelectAllStates();
    WaitHelper.assertTrueWithWait(() -> {
      return getStateFilterPanel()
          .findElements(By.cssSelector(
              "table[id$=':filter-input-form:state-selection'] div.ui-chkbox-box span.ui-chkbox-icon.ui-icon-blank"))
          .size() == labelList.size();
    });
    List<SelenideElement> checkBoxList = getStateFilterPanel()
        .$$(By.cssSelector("table[id$=':filter-input-form:state-selection'] div.ui-chkbox-box.ui-state-default"));
    statesSelectedIndex.forEach(index -> {
      waitForElementClickableThenClick(checkBoxList.get(index));
    });
    // waitForAjaxStatusPositionDisappear();
    waitForElementClickableThenClick($(By.cssSelector("button[id$='state-filter:filter-input-form:update-command']")));
    waitForElementDisplayed(By.cssSelector("button[id$='state-filter:filter-input-form:update-command']"), false);
  }

  public void clickOnUnCheckSelectAllStates() {
    var selectAll = getStateFilterPanel().$(By.cssSelector("[id$=':filter-input-form:states-select-all']"));
    if (selectAll.$(By.className("ui-chkbox-box")).getAttribute(CLASS_PROPERTY).contains("ui-state-active")) {
      waitForElementClickableThenClick($(selectAll.$(By.cssSelector("span.ui-chkbox-label"))));
      WaitHelper.assertTrueWithWait(() -> {
        return $("[id$=':filter-input-form:states-select-all'] span.ui-chkbox-icon").getAttribute(CLASS)
            .contains("ui-icon-blank");
      });
    } else {
      waitForElementClickableThenClick($(selectAll.$(By.cssSelector("span.ui-chkbox-label"))));
      WaitHelper.assertTrueWithWait(() -> {
        return $("[id$=':filter-input-form:states-select-all'] span.ui-chkbox-icon").getAttribute(CLASS)
            .contains("ui-icon-check");
      });
      clickOnUnCheckSelectAllStates();
    }
  }

  public boolean isTaskStartEnabled(int taskId) {
    String startCommandButton =
        String.format(taskWidgetId + ":task-list-scroller:%d:task-item:task-action:task-action-component", taskId);
    WebElement element = findElementById(startCommandButton);
    return !element.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public List<String> getActiveTaskAction(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    WebElement actionPanel = findElementByCssSelector(String.format(
        "div[id$='task-list-scroller:%d:task-item:task-action:additional-options:side-steps-panel']", taskIndex));
    return actionPanel.findElements(By.cssSelector("a[class*='option-item']")).stream()
        .filter(elem -> !elem.getAttribute("class").contains("ui-state-disabled")).map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public boolean isActionLinkEnable() {
    return !$(".action-link").getAttribute("class").contains("ui-state-disabled");
  }

  public TaskIFrameTemplatePage startTaskIFrame(int index) {
    waitTaskAppearThenClick(index);
    $(By.id(TASK_ACTION)).shouldBe(appear, DEFAULT_TIMEOUT);
    switchToIFrameOfTask();
    return new TaskIFrameTemplatePage();
  }

  public TaskTemplatePage startTask(int index) {
    waitTaskAppearThenClick(index);
    $(By.id(TASK_ACTION)).shouldBe(appear, DEFAULT_TIMEOUT);
    return new TaskTemplatePage();
  }

  public boolean isTaskStateReserved(int index) {
    try {
      $(By.id(String.format(TASK_STATE_COMPONENT_ID, index))).$(By.className("open-task-state"));
    } catch (NoSuchElementException e) {
      return false;
    }
    return true;
  }

  private boolean isTaskActionDisplayed(String action, int taskIndex, boolean expected) {
    return isElementDisplayed(By.id(String
        .format("task-widget:task-list-scroller:%d:task-item:task-action:additional-options:%s", taskIndex, action)),
        expected);
  }

  public boolean isTaskResetDisplayed(boolean expected) {
    return isTaskActionDisplayed("task-reset-command", 0, expected);
  }

  public boolean isTaskDelegateDisplayed(boolean expected) {
    return isTaskActionDisplayed("task-delegate-command", 0, expected);
  }

  public boolean isTaskReserverDisplayed(boolean expected) {
    return isTaskActionDisplayed("task-reserve-command", 0, expected);
  }

  public void sideStepMenuOnActionButton(int index) {
    String actionButton =
        String.format("[id$='%d\\:task-item\\:task-action\\:additional-options\\:task-side-steps-menu']", index);
    waitForElementClickableThenClick($(actionButton));
    String actionPanel = String
        .format("task-widget:task-list-scroller:%d:task-item:task-action:additional-options:side-steps-panel", index);
    waitForElementDisplayed(By.id(actionPanel), true);
  }

  public void clickOnProcessViewerOption() {
    waitForElementDisplayed(
        By.cssSelector("[id$=':task-item:task-action:additional-options:side-steps-panel'].options-panel"), true);
    waitForElementClickableThenClick("a[id$=':task-item:task-action:additional-options:show-process-viewer-link']");
  }

  public TaskDetailsPage openTaskDetailsFromActionMenu(int index) {
    sideStepMenuOnActionButton(index);
    String detailOptionCssSelector = "a[id$='additional-options:task-open-detail-command']";
    waitForElementClickableThenClick(detailOptionCssSelector);
    return new TaskDetailsPage();
  }

  public SelenideElement getSaveFilterDialog() {
    $("[id$='task-widget:filter-save-action']").shouldBe(getClickableCondition()).click();
    $(By.id("task-widget:filter-save-form:save-filter-set-name-input")).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("[id$=':save-filter-set-name-input']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $(By.id("task-widget:save-filter-set-dialog")).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void closeSaveFilterDialog() {
    $("a[id^='task-widget:filter-save-form']").shouldBe(getClickableCondition()).click();
    $(By.id("task-widget:save-filter-set-dialog")).shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public TaskDetailsPage openTaskDetail(int index) {
    openTaskWithAccessTaskDetailsBehaviour(index);
    TaskDetailsPage detailsPage = new TaskDetailsPage();
    detailsPage.waitPageLoaded();
    return new TaskDetailsPage();
  }

  public CaseDetailsPage openRelatedCaseOfTask() {
    WaitHelper.waitForNavigation(() -> {
      $("a[id$='related-case']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    });
    return new CaseDetailsPage();
  }

  public boolean checkNameOfTaskAt(int index, String name) {
    return $(ID_END + index + ":task-item:task-name-component:task-name']").shouldBe(appear, DEFAULT_TIMEOUT)
        .is(Condition.text(name));
  }

  public boolean isReserveLinkDisabled(int taskId) {
    return !$(getReserveTaskLinkSelector(taskId)).isDisplayed();
  }

  public String getReserveTaskLinkSelector(int taskId) {
    String reserveCommandButton = String.format(
        "task-widget:task-list-scroller:%d:task-item:task-action:additional-options:task-reserve-command", taskId);
    return "[id='" + reserveCommandButton + "']";
  }

  public boolean checkTaskState(int index, String state) {
    return getTaskState(index).is(Condition.cssClass(state));
  }

  public boolean isResetLinkDisabled(int taskId) {
    return !$(getResetTaskLinkSelector(taskId)).isDisplayed();
  }

  private String getResetTaskLinkSelector(int taskId) {
    return String.format(
        "[id='task-widget:task-list-scroller:%s:task-item:task-action:additional-options:task-reset-command']", taskId);
  }

  public void isTaskEnabled(int index) {
    getStartTaskElement(index).shouldBe(appear, DEFAULT_TIMEOUT).shouldNot(Condition.cssClass("ui-state-disabled"));
  }

  public void isTaskDisabled(int index) {
    getStartTaskElement(index).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(Condition.cssClass("ui-state-disabled"));
  }

  private SelenideElement getStartTaskElement(int index) {
    String startCommandButton =
        String.format("task-widget:task-list-scroller:%d:task-item:task-action:task-action-component", index);
    return $("[id='" + startCommandButton + "']");
  }

  public void isTaskDelegationEnabled(int index) {
    getDelegateCommandElement(index).shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldNot(Condition.cssClass("ui-state-disabled"));
  }

  public void isTaskDelegationDisabled(int index) {
    getDelegateCommandElement(index).shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(Condition.cssClass("ui-state-disabled"));
  }

  private SelenideElement getDelegateCommandElement(int index) {
    sideStepMenuOnActionButton(index);
    return $("[id='task-widget:task-list-scroller:" + index
        + ":task-item:task-action:additional-options:task-delegate-command']");
  }

  public void isTaskDestroyEnabled(int index) {
    sideStepMenuOnActionButton(index);
    getDestroyCommandElement(index).shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldNot(Condition.cssClass("ui-state-disabled"));
  }

  private SelenideElement getDestroyCommandElement(int index) {
    return $("[id='task-widget:task-list-scroller:" + index
        + ":task-item:task-action:additional-options:task-destroy-command']");
  }

  public void destroyTask(int index) {
    getDestroyCommandElement(index).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void confimDestruction() {
    SelenideElement destroyDialog = $("[id='task-widget:destroy-task-confirmation-dialog']");
    destroyDialog.shouldBe(appear, DEFAULT_TIMEOUT).$("[id='task-widget:confirm-destruction']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    destroyDialog.shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public boolean isTaskDelegateOptionDisable(String taskName) {
    int index = 1;
    List<SelenideElement> taskElements = $$("span[id$=':task-item:task-name-component:task-name']");
    for (int i = 0; i < taskElements.size(); i++) {
      if (taskElements.get(i).getText().equals(taskName)) {
        index = i;
        break;
      }
    }

    return isTaskDelegateOptionDisable(index);
  }

  public boolean isTaskDelegateOptionDisable(int index) {
    sideStepMenuOnActionButton(index);
    waitForElementDisplayed(By.id(taskWidgetId + ":task-list-scroller:" + index
        + ":task-item:task-action:additional-options:task-delegate-command"), true);
    SelenideElement delegateButton = findElementById(taskWidgetId + ":task-list-scroller:" + index
        + ":task-item:task-action:additional-options:task-delegate-command");
    return delegateButton.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public String getNameOfTaskAt(int index) {
    SelenideElement name = findElementByCssSelector(ID_END + index + ":task-item:task-name-component:task-name']");
    return name.getText();
  }

  public void clickOnStartTaskLink(int index) {
    String startLinkId =
        String.format("a[id$='task-list-scroller:%d:task-item:task-action:task-action-component']", index);
    refreshAndWaitElement(startLinkId);
    waitForElementClickableThenClick(findElementByCssSelector(startLinkId));
    switchToIFrameOfTask();
  }

  public void resetFilter() {
    waitForElementClickableThenClick("[id$='task-widget:filter-reset-action']");
    waitForElementClickableThenClick("[id$='task-widget:filter-reset-command']");
    waitForElementDisplayed(By.id("task-widget:reset-filter-set-dialog"), false);
  }

  public void waitForActionGroupDisplay() {
    waitForElementDisplayed(By.cssSelector("div[class='action-container']"), true);
  }

  public String getFilterValue(String filterId) {
    $("[id$='" + filterId + ":advanced-filter-component']").shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    return $("button[id$='" + filterId + ":filter-open-form:advanced-filter-command']")
        .shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public String getResponsibleOfTaskAt(int index) {
    List<SelenideElement> responsibles = $$(".responsible-cell .name-after-avatar");
    return responsibles.get(index).getText();
  }

  public void changeDescriptionOfTask(String description) {
    waitForElementClickableThenClick("a[id$=':task-detail-description:edit-description-link']");
    waitForElementDisplayed(By.cssSelector("[id$=':task-detail-description-form:task-description-inplace_content']"),
        true);
    SelenideElement taskNameInput = $("textarea[id$=':task-description-input']");
    waitForElementDisplayed(taskNameInput, true);
    taskNameInput.clear();
    taskNameInput.sendKeys(description);
    waitForElementClickableThenClick("span[id$=':task-description-inplace_editor'] .ui-inplace-save");
    waitForElementDisplayed(findElementByCssSelector("[id$='task-description-output']"), true);
  }

  public String getTaskDescription() {
    return $("[id$='task-description-output'] .task-detail-description-output").getText();
  }

  public boolean isTaskNameChangeComponentPresented(int index) {
    return isElementPresent(By.id(
        String.format(taskWidgetId + ":task-list-scroller:%d:task-item:task-name-edit-form:task-name-input", index)));
  }

  public boolean isTaskPriorityChangeComponentPresented(int index) {
    return isElementPresent(By.id(String.format(
        taskWidgetId + ":task-list-scroller:%d:task-item:general-info:priority-form:edit-priority-inplace", index)));
  }

  public TaskIFrameTemplatePage startTaskWithouWaitForTaskActionPresent(int index) {
    waitTaskAppearThenClick(index);
    switchToIFrameOfTask();
    return new TaskIFrameTemplatePage();
  }

  public boolean isTaskStateOpen(int index) {
    try {
      SelenideElement stateComponent = findElementById(String.format(TASK_STATE_COMPONENT_ID, index));
      stateComponent.findElement(By.className("open-task-state"));
    } catch (NoSuchElementException e) {
      return false;
    }
    return true;
  }

  public boolean isCategoryColumnDisplayed() {
    List<SelenideElement> taskCategoryCells = $$("span[id$=':task-category-cell']");
    for (SelenideElement categoryCell : taskCategoryCells) {
      if (categoryCell.isDisplayed()) {
        return true;
      }
    }
    return false;
  }

  public String getTaskId() {
    String taskTitleCssSelection = "span[id$=':task-id']";
    String taskTitle = findElementByCssSelector(taskTitleCssSelection).getText();
    // String taskId = taskTitle.substring(taskTitle.indexOf("#") + 1, taskTitle.indexOf(")"));
    return taskTitle;
  }

  public SelenideElement getTaskCategory() {
    return findElementByCssSelector("span[id$='task-category']");
  }

  public SelenideElement getCaseCategory() {
    return findElementByCssSelector("span[id$='case-category']");
  }

  public void waitUntilTaskCountDifferentThanZero() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((driver) -> getTaskCount().intValue() != 0);

  }

  public Integer getTaskCount() {
    String title = getTextOfCurrentBreadcrumb();
    String count = StringUtils.substringBetween(title, "(", ")");
    return StringUtils.isNotBlank(count) ? Integer.parseInt(count) : null;
  }

  public void isDelegateTypeSelectAvailable() {
    waitForElementDisplayed($("div[id$=':activator-panel']"), true);
  }

  public void openTaskDelegateDialog(int index) {
    sideStepMenuOnActionButton(index);
    waitForElementClickableThenClick($("a[id$='\\:task-delegate-command']"));
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), true);
  }

  public void selectDelegateResponsible(String responsibleName, boolean isRole) {
    SelenideElement responsibleElement;
    if (isRole) {
      waitForElementDisplayed(By.cssSelector("[id$=':task-delegate-form:activator-type-select']"), true);
      waitForElementEnabled(By.cssSelector("[id$=':task-delegate-form:activator-type-select:1']"), true);
      waitForElementClickableThenClick("[for$=':task-delegate-form:activator-type-select:1']");;
      waitForElementDisplayed(By.cssSelector("input[id$='group-activator-select_input']"), true);
      responsibleElement = $(By.cssSelector("input[id$='group-activator-select_input']"));
      responsibleElement.click();
      responsibleElement.sendKeys(responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='group-activator-select_panel']"), true);
      List<SelenideElement> foundRoles = $$("span[id$='group-activator-select_panel'] .gravatar");
      waitForElementClickableThenClick(foundRoles.get(0));
    } else {
      waitForElementDisplayed(By.cssSelector("input[id$='user-activator-select_input']"), true);
      responsibleElement = $(By.cssSelector("input[id$='user-activator-select_input']"));
      responsibleElement.click();
      responsibleElement.sendKeys(responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='user-activator-select_panel']"), true);
      List<SelenideElement> foundUsers = $$("span[id$='user-activator-select_panel'] .name-after-avatar");
      waitForElementClickableThenClick(foundUsers.get(0));
    }
    waitForElementClickableThenClick(By.cssSelector("button[id$='proceed-task-delegate-command']"));
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), false);
  }

  public void clickExportToExcelLink() {
    // Ensure that attribute is removed before downloading
    JavascriptExecutor js = (JavascriptExecutor) driver;
    SelenideElement statusDialog = $(By.cssSelector("div[id$=':status-dialog']"));
    js.executeScript("arguments[0].removeAttribute('download-status')", statusDialog);

    // click download
    SelenideElement downloadLink = getExportToExcelLink();
    if (downloadLink != null) {
      downloadLink.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
      clickByJavaScript(downloadLink);
    }
  }

  public SelenideElement getExportToExcelLink() {
    return findElementByCssSelector("a[id$=':task-export-to-excel']");
  }

  public boolean isDownloadCompleted() {
    WebElement statusDialog = driver.findElement(By.cssSelector("div[id$=':status-dialog']"));
    WaitHelper.assertTrueWithWait(() -> StringUtils.isNotBlank(statusDialog.getAttribute("download-status")));
    return StringUtils.equals(statusDialog.getAttribute("download-status"), "completed");
  }

  public void sortTaskListByColumn(String columnHeaderText, int rowIndex, String columnId, String expectedValue) {
    WebElement taskListHeader = findElementById(taskWidgetId + ":task-widget-sort-menu");
    for (WebElement column : taskListHeader.findElements(By.tagName("a"))) {
      if (columnHeaderText.equals(column.getText())) {
        column.click();
        break;
      }
    }
    WaitHelper.assertTrueWithWait(() -> getTaskListCustomCellValue(rowIndex, columnId).equals(expectedValue));
  }

  public String getTaskListCustomCellValue(int index, String columnId) {
    WebElement cell = findElementById(
        String.format(taskWidgetId + ":task-list-scroller:%d:task-item:%s-component:%s", index, columnId, columnId));
    return cell.getText();
  }

  public void clearFilterInput() {
    $(KEYWORD_FILTER_SELECTOR_EXPANDED_MODE).click();
    $(KEYWORD_FILTER_SELECTOR_EXPANDED_MODE).clear();
  }

}
