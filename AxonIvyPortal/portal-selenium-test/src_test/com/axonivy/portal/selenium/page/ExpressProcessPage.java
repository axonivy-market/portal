package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.bean.ExpressResponsible;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.codeborne.selenide.Condition;

public class ExpressProcessPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='form:process-setting-fieldset']";
  }

  public void createDefaultTask(int taskIndex, String taskName, List<ExpressResponsible> responsibles) {
    if (taskName != null) {
      $("[id='" + String.format("form:defined-tasks-list:%d:default-task-name", taskIndex) + "']")
          .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).sendKeys(taskName);
    }
    $("[id='" + String.format("form:defined-tasks-list:%d:default-task-responsible-link", taskIndex) + "']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    addResponsible(responsibles);
  }

  private void addResponsible(List<ExpressResponsible> responsibles) {
    $("[id='choose-responsible-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    for (ExpressResponsible responsible : responsibles) {
      chooseResponsible(responsible.getResponsibleName(), responsible.getIsGroup());
    }
    $("[id='assignee-selection-form:save-assignee-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='choose-responsible-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  private void chooseResponsible(String responsible, boolean isGroup) {
    if (isGroup) {
      $("label[for='assignee-selection-form:assignee-type:1']").shouldBe(appear, DEFAULT_TIMEOUT).click();
      $("[id='assignee-selection-form:role-selection-component:role-selection_input']")
          .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).clear();
      $("[id='assignee-selection-form:role-selection-component:role-selection_input']").sendKeys(responsible);
      $("[id='assignee-selection-form:role-selection-component:role-selection_panel']").shouldBe(appear,
          DEFAULT_TIMEOUT);
      $("span[id='assignee-selection-form:role-selection-component:role-selection_panel'] .gravatar")
          .shouldBe(appear, DEFAULT_TIMEOUT).click();
    } else {
      $("[id='assignee-selection-form:user-selection-component:user-selection_input']")
          .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).clear();
      $("[id='assignee-selection-form:user-selection-component:user-selection_input']").sendKeys(responsible);
      $("[id='assignee-selection-form:user-selection-component:user-selection_panel']").shouldBe(appear,
          DEFAULT_TIMEOUT);
      $("span[id='assignee-selection-form:user-selection-component:user-selection_panel'] .gravatar")
          .shouldBe(appear, DEFAULT_TIMEOUT).click();
    }

    $("[id='assignee-selection-form:add-assignee-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(".assignee-name-col").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void addNewTask(int currentTaskIndex) {
    $("[id='" + String.format("form:defined-tasks-list:%d:add-step-button", currentTaskIndex) + "']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='" + String.format("form:defined-tasks-list:%d:process-flow-field", currentTaskIndex + 1) + "']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickSave() {
    $("[id='form:save']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public ExpressFormDefinitionPage goToFormDefinition() {
    goToFormDefinitionDefaultResolution();
    ScreenshotUtils.resizeBrowser(new Dimension(2560, 1440));
    return new ExpressFormDefinitionPage();
  }

  public ExpressFormDefinitionPage goToFormDefinitionDefaultResolution() {
    clickSave();
    return new ExpressFormDefinitionPage();
  }

  public WebElement getDefineTaskStep(int stepIndex) {
    String defineTaskStepId = String.format(":defined-tasks-list:%s:process-flow-field", stepIndex);
    return $("[id$='" + defineTaskStepId + "']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void fillProcessProperties(boolean isAdhocWF, boolean isCreateOwn, String processName,
      String processDescription) {
    if (isAdhocWF) {
      $("div[id='form:process-type']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      $("span[id='form:process-type-group']").$(".switch-active").shouldBe(Condition.text("One time"), DEFAULT_TIMEOUT);
    }

    if (!isCreateOwn) {
      $("div[id='form:user-interface-type']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      agreeToDeleteAllDefineTasks();

    }
    $("[id='form:process-name']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(processName);
    $("[id='form:process-description']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(processDescription);
  }

  private void agreeToDeleteAllDefineTasks() {
    $("[id='delete-all-defined-tasks-warning']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id='delete-all-defined-tasks-warning-ok']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='delete-all-defined-tasks-warning']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void createTask(int taskIndex, int typeIndex, String taskName, String taskDescription,
      List<ExpressResponsible> responsibles) {
    final String TASK_NAME_FORMAT = "input[id$='%d:task-name']";
    final int INFORMATION_EMAIL_INDEX = 2;

    chooseTaskType(taskIndex, typeIndex);
    if (typeIndex != INFORMATION_EMAIL_INDEX) {
      $("[id='" + String.format("form:defined-tasks-list:%d:task-responsible-link", taskIndex) + "']")
          .shouldBe(clickable(), DEFAULT_TIMEOUT).click();
      $("[id='choose-responsible-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
      addResponsible(responsibles);

      $(String.format(TASK_NAME_FORMAT, taskIndex)).shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(taskName);
      $(String.format("input[id$='%d:task-description']", taskIndex)).shouldBe(appear, DEFAULT_TIMEOUT)
          .sendKeys(taskDescription);
    }
  }

  private void chooseTaskType(int taskIndex, int typeIndex) {
    if (typeIndex == 0) {
      // If the selected task type is already task type? ignore click on the drop-down
      return;
    }

    final String TASK_TYPE_FORMAT = "li[id$=':%d:task-type_%d']";
    final String TASK_TYPE_LABEL_FORMAT = "label[id$=':%d:task-type_label']";

    $(String.format(TASK_TYPE_LABEL_FORMAT, taskIndex)).shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $(String.format("[id$=':%d:task-type_panel']", taskIndex)).shouldBe(appear, DEFAULT_TIMEOUT);
    $(String.format(TASK_TYPE_FORMAT, taskIndex, typeIndex)).shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void waitUntilExpressProcessDisplay() {
    $("[id='form:process-setting-fieldset']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String getProcessName() {
    return findElementById("form:process-name").getAttribute("value");
  }

  public String getProcessOwnerNames() {
    return findElementById("form:process-owner-link").getText();
  }

  public String getAbleToStartNames() {
    return getResponsiblesOfTask(0);
  }

  public String getResponsiblesOfTask(int taskIndex) {
    return findElementById(String.format("form:defined-tasks-list:%d:task-responsible-link", taskIndex)).getText();
  }

  public void executeDirectly() {
    waitForElementClickableThenClick("[id$='form:save']");
  }

  public NewDashboardPage cancelWorkflowDefinition() {
    waitForElementClickableThenClick(By.id("form:cancel-workflow-button"));
    return new NewDashboardPage();
  }

  public void fillProcessOwners(List<ExpressResponsible> responsibles) {
    waitForElementClickableThenClick(By.id("form:process-owner-link"));
    addResponsible(responsibles);
  }
}
