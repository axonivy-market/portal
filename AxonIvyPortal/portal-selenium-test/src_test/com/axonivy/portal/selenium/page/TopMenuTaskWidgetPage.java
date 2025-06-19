package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TopMenuTaskWidgetPage extends TaskWidgetNewDashBoardPage {

  private static final String YOUR_TASKS_WIDGET = "Your Tasks";
  private String taskWidgetId;

  public TopMenuTaskWidgetPage() {
    this("div[id$='dashboard-tasks']", YOUR_TASKS_WIDGET);
  }

  public TopMenuTaskWidgetPage(String taskWidgetName) {
    this("div[id$='dashboard-tasks']", taskWidgetName);
  }

  public TopMenuTaskWidgetPage(String taskWidgetId, String taskWidgetName) {
    this.taskWidgetId = taskWidgetId;
  }

  @Override
  protected String getLoadedLocator() {
    return "[id$='dashboard-tasks-container']";
  }

  public TaskTemplatePage startTaskByIndex(int taskIndex) {
    getColumnOfTaskHasIndex(taskIndex, "Start").shouldBe(getClickableCondition()).click();
    return new TaskTemplatePage();
  }

  public void startTaskByNameInIFrame(String taskName) {
    int taskIndex =
        getAllTasksOfTaskWidget().asFixedIterable().stream().map(WebElement::getText).collect(Collectors.toList())
        .indexOf(taskName);
    startTaskByIndex(taskIndex);
    switchToIFrameOfTask();
  }

  public boolean checkNameOfTaskAt(int taskIndex, String taskName) {
    return getColumnOfTaskHasIndex(taskIndex, "Task name").shouldBe(appear, DEFAULT_TIMEOUT)
        .is(Condition.text(taskName));
  }


  public TaskIFrameTemplatePage startTaskIFrameByIndex(int taskIndex) {
    getColumnOfTaskHasIndex(taskIndex, "Start").shouldBe(getClickableCondition()).click();
    switchToIFrameOfTask();
    return new TaskIFrameTemplatePage();
  }



  public String getRelatedCase() {
    return findElementByCssSelector("a[id$='related-case']").shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public TaskTemplatePage clickOnSideStepAction(int sideStepIndex) {
    SelenideElement sideStepPanel = $("[id$='task-additional-actions']");
    ElementsCollection sideSteps = sideStepPanel.findAll(By.className("option-item"));
    sideSteps.get(sideStepIndex).click();
    return new TaskTemplatePage();
  }


  @Override
  public void waitForPageLoad() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(
        webDriver -> "complete".equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState")));
  }

  @Override
  public ElementsCollection getActiveTaskActions(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    ElementsCollection taskPanels =
        $$(String.format("div.js-task-side-steps-panel-default_task_list_dashboard_task_1-%d", taskIndex))
            .filter(appear);
    taskPanels.shouldHave(CollectionCondition.sizeGreaterThan(0), DEFAULT_TIMEOUT);
    return taskPanels.first().shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-overlaypanel-content")
        .$$("a[class*='option-item']").filter(Condition.not(Condition.cssClass("ui-state-disabled")));
  }

  public ElementsCollection getAllTaskActions(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    return $$(String.format("div.js-task-side-steps-panel-default_task_list_dashboard_task_1-%d", taskIndex))
        .filter(appear).first().shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-overlaypanel-content")
        .$$("a[class*='option-item']");
  }

  public List<String> getActiveTaskActionNames(int index) {
    clickOnTaskActionLink(index);
    ElementsCollection actions =
        $$(String.format("div.js-task-side-steps-panel-default_task_list_dashboard_task_1-%d", index)).filter(appear)
            .first().shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-overlaypanel-content").$$("a[class*='option-item']")
            .filter(Condition.not(Condition.cssClass("ui-state-disabled")));
    return actions.asFixedIterable().stream().map(WebElement::getText).collect(Collectors.toList());
  }


  public TaskDetailsPage openTaskDetailsPageByAction(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    clickTaskAction(taskIndex, "Details");
    return new TaskDetailsPage();
  }

  @Override
  public void reserveTask(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    clickTaskAction(taskIndex, "Reserve");
  }

  private void clickTaskAction(int taskIndex, String actionName) {
    ElementsCollection taskPanels =
        $$(String.format("div.js-task-side-steps-panel-default_task_list_dashboard_task_1-%d", taskIndex))
            .filter(appear);
    taskPanels.shouldHave(CollectionCondition.sizeGreaterThan(0), DEFAULT_TIMEOUT);
    SelenideElement taskPanel = taskPanels.first().shouldBe(appear, DEFAULT_TIMEOUT);

    SelenideElement overlay = taskPanel.$("div.ui-overlaypanel-content").shouldBe(appear, DEFAULT_TIMEOUT);

    ElementsCollection actionItems = overlay.$$("a.option-item")
        .filter(Condition.not(Condition.cssClass("ui-state-disabled"))).filter(text(actionName));

    actionItems.shouldHave(CollectionCondition.sizeGreaterThan(0), DEFAULT_TIMEOUT);
    actionItems.first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void destroyTask(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    clickTaskAction(taskIndex, "Destroy");
    confirmDestroy();
  }

  private void confirmDestroy() {
    $("div[id$='destroy-task-confirmation-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("button[id$='confirm-destruction-dashboard-tasks']").shouldBe(getClickableCondition()).click();
    $("button[id$='confirm-destruction-dashboard-tasks']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void openTaskDelegateDialog(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    clickTaskAction(taskIndex, "Delegate");
  }
  
  public void openTaskProcessViewer(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    clickTaskAction(taskIndex, "Process Viewer");
  }

  @Override
  public void startTask(int taskIndex) {
    getColumnOfTaskHasIndex(taskIndex, "Start").shouldBe(getClickableCondition()).click();
  }
  
  public boolean isTableHidden() {
    return $(taskWidgetId).is(Condition.hidden);
  }

  public boolean isTaskResetNotDisplayed(int index) {
    return getAllTaskActions(index).filter(text("Reset")).isEmpty();
  }

  public boolean isTaskDelegateNotDisplayed(int index) {
    return getAllTaskActions(index).filter(text("Delegate")).isEmpty();
  }

  public boolean isTaskReserverNotDisplayed(int index) {
    return getAllTaskActions(index).filter(text("Reserve")).isEmpty();
  }

  public void checkReserveTaskButtonDisable(int index) {
    clickOnTaskActionLink(index);
    $$(String.format("div.js-task-side-steps-panel-default_task_list_dashboard_task_1-%d", index)).filter(appear)
        .first().shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-overlaypanel-content").$$("a[class*='option-item']")
        .filter(Condition.not(Condition.cssClass("ui-state-disabled"))).filter(text("Reserve")).first()
        .shouldNot(getClickableCondition());
  }

  public void processViewerDashboardTaskList(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    $$(String.format("div.js-task-side-steps-panel-default_task_list_dashboard_task_1-%d", taskIndex)).filter(appear)
        .first().shouldBe(appear, DEFAULT_TIMEOUT).$("div.ui-overlaypanel-content").$$("a[class*='option-item']")
        .filter(Condition.not(Condition.cssClass("ui-state-disabled"))).filter(text("Process Viewer")).first()
        .shouldBe(getClickableCondition()).click();
  }

  public String getNameOfTaskAt(int taskIndex) {
    return getAllTasksOfTaskWidget().get(taskIndex).text();
  }

  public String getResponsibleOfTaskAt(int taskIndex) {
    return getAllResponsibleOfTaskWidget().get(taskIndex).text();
  }

  protected ElementsCollection getAllResponsibleOfTaskWidget() {
    return getColumnsOfTableWidget().filter(Condition.cssClass("dashboard-tasks__responsible"));
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

  public SelenideElement getTaskCategory() {
    return findElementByCssSelector("span[id$='task-category']");
  }

  public SelenideElement getCaseCategory() {
    return findElementByCssSelector("span[id$='case-category']");
  }

  public void waitUntilTaskCountDifferentThanZero() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((driver) -> countAllTasks().size() != 0);
  }

  public void waitUntilTaskFilterReturnResultCount(int count) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((driver) -> countAllTasks().size() == count);
  }

  public void isDelegateTypeSelectAvailable() {
    waitForElementDisplayed($("div[id$=':activator-panel']"), true);
  }

  public void selectDelegateResponsible(String responsibleName, boolean isRole) {
    SelenideElement responsibleElement;
    if (isRole) {
      waitForElementDisplayed(By.cssSelector("[id$=':task-delegate-form:activator-type-select']"), true);
      waitForElementEnabled(By.cssSelector("[id$=':task-delegate-form:activator-type-select:1']"), true);
      waitForElementClickableThenClick("[for$=':task-delegate-form:activator-type-select:1']");
      waitForElementDisplayed(By.cssSelector("input[id$='group-activator-select_input']"), true);
      responsibleElement = $(By.cssSelector("input[id$='group-activator-select_input']"));
      responsibleElement.click();
      responsibleElement.sendKeys(responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='group-activator-select_panel']"), true);
      ElementsCollection foundRoles = $$("span[id$='group-activator-select_panel'] .gravatar");
      waitForElementClickableThenClick(foundRoles.get(0));
    } else {
      waitForElementDisplayed(By.cssSelector("input[id$='user-activator-select_input']"), true);
      responsibleElement = $(By.cssSelector("input[id$='user-activator-select_input']"));
      responsibleElement.click();
      responsibleElement.sendKeys(responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='user-activator-select_panel']"), true);
      ElementsCollection foundUsers = $$("span[id$='user-activator-select_panel'] .name-after-avatar");
      waitForElementClickableThenClick(foundUsers.get(0));
    }
    waitForElementClickableThenClick(By.cssSelector("button[id$='proceed-task-delegate-command']"));
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), false);
  }

  public CaseDetailsPage openRelatedCaseOfTask() {
    WaitHelper.waitForNavigation(() -> {
      $("a[id$='related-case']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    });
    return new CaseDetailsPage();
  }

  public boolean isDelegateTypeDisabled(int index) {
    return getDelegateType(index).getDomAttribute("class").contains("ui-state-disabled");
  }

  public SelenideElement getDelegateType(int index) {
    return $("div[id$=':task-delegate-form:activator-panel']").$$(".ui-radiobutton-box").get(index)
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public boolean isDelegateTypeAvailable() {
    return $("div[id$=':task-delegate-form:activator-panel']").isDisplayed();
  }

  public String getCannotDelegateText() {
    return $("div[id$='task-delegate-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$(".ui-dialog-content span")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).getText();
  }

  public boolean isDelegateListSelectionAvailable() {
    return $("div[id$='select-delegate-panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).exists();
  }
  @Override
  public void triggerEscalationTask(int taskIndex) {
    ElementsCollection actions = getActiveTaskActions(taskIndex);
    ElementsCollection escalationActions = actions.filter(text("Trigger Escalation"));
    
    escalationActions.shouldHave(CollectionCondition.sizeGreaterThan(0), DEFAULT_TIMEOUT);
    escalationActions.first().shouldBe(getClickableCondition()).click();
    
    $("div[id='escalation-task-confirmation-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("button[id='confirm-escalation-dashboard-tasks']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  public void pinTaskByIndex(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    clickTaskAction(taskIndex, "Pin");

  }

  public void unpinTaskByIndex(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    clickTaskAction(taskIndex, "Unpin");
  }

  public boolean isTaskPinned(int taskIndex) {
    return getAllTaskActions(taskIndex)
        .filter(Condition.exactText("Pin")).shouldHave(CollectionCondition.size(0)).isEmpty();
  }

  public boolean isTaskUnpinned(int taskIndex) {
    return getAllTaskActions(taskIndex).filter(Condition.exactText("Unpin")).shouldHave(CollectionCondition.size(0))
        .isEmpty();
  }

  public void togglePinnedTask() {
    SelenideElement toggle = $("[id$='show-pin-toggle']");
    toggle.shouldBe(clickable());
    toggle.find(By.className("ui-toggleswitch-slider")).click();
  }

  public void clickOnPinColumn(int index) {
    getColumnOfTaskHasIndex(index, "Pin").shouldBe(getClickableCondition()).click();
  }
}
