package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;

public class AdhocPage extends TaskTemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='main-area-panel']";
  }

  public void enterSubject(String subject) {
    $("[id='form:defined-tasks-list:0:default-task-name']").sendKeys(subject);
  }

  public void addResponsible(String fullname) {
    $("[id$=':default-task-responsible-link']").click();
    $("[id='choose-responsible-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id='assignee-selection-form:user-selection-component:user-selection_input']").sendKeys(fullname);
    SelenideElement panel = $("[id='assignee-selection-form:user-selection-component:user-selection_panel']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    panel.click();
    panel.shouldBe(disappear, DEFAULT_TIMEOUT);
    $("[id='assignee-selection-form:add-assignee-button']").shouldBe(clickable(), DEFAULT_TIMEOUT).click();
    $$(".ui-panelgrid-cell.assignee-name-col").shouldHave(CollectionCondition.size(1), DEFAULT_TIMEOUT);
    $("[id='assignee-selection-form:save-assignee-button']").shouldBe(clickable(), DEFAULT_TIMEOUT).click();
    $("[id='choose-responsible-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void addTask() {
    waitForElementClickableThenClick($("[id$=':add-step-button']"));
  }

  public void addComment(String comment) {
    findElementByCssSelector("*[id$='" + ":comments']").sendKeys(comment);
  }

  public void startWorkflow() {
    waitForElementClickableThenClick($("[id='form:save']"));
    $("[id='form:save']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public TaskWidgetPage send() {
    waitForElementClickableThenClick($("*[id$='" + ":send']"));
    return new TaskWidgetPage();
  }

  public void addDescription(String description) {
    $("[id$=':input-text-area']").sendKeys(description);
    $("[id='form:ok-btn']").click();
    $("[id='form:ok-btn']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
}
