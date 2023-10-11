package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import ch.ivyteam.ivy.project.portal.test.ExpressResponsible;

public class ExpressProcessPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='form:process-setting-fieldset']";
  }

  public void createDefaultTask(int taskIndex, String taskName, List<ExpressResponsible> responsibles) {
    if (taskName != null) {
      $("[id='" + String.format("form:defined-tasks-list:%d:default-task-name", taskIndex) + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).sendKeys(taskName);
    }
    $("[id='" + String.format("form:defined-tasks-list:%d:default-task-responsible-link", taskIndex) + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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
      $("[id='assignee-selection-form:assignee-type:1']").shouldBe(appear, DEFAULT_TIMEOUT).click();
      $("[id='assignee-selection-form:role-selection-component:role-selection_input']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).sendKeys(responsible);
      $("[id='assignee-selection-form:role-selection-component:role-selection_panel']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
      $("span[id='assignee-selection-form:role-selection-component:role-selection_panel'] .gravatar")
        .shouldBe(appear, DEFAULT_TIMEOUT).click();
    } else {
      $("[id='assignee-selection-form:user-selection-component:user-selection_input']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).sendKeys(responsible);
      $("[id='assignee-selection-form:user-selection-component:user-selection_panel']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
      $("span[id='assignee-selection-form:user-selection-component:user-selection_panel'] .gravatar")
        .shouldBe(appear, DEFAULT_TIMEOUT).click();
    }

    $("[id='assignee-selection-form:add-assignee-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(".assignee-name-col").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void addNewTask(int currentTaskIndex) {
    $("[id='" + String.format("form:defined-tasks-list:%d:add-step-button", currentTaskIndex) + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='" + String.format("form:defined-tasks-list:%d:process-flow-field", currentTaskIndex + 1) +"']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickSave() {
    $("[id='form:save']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }
}
