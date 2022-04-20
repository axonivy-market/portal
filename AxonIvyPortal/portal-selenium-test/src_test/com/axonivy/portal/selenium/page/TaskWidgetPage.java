package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class TaskWidgetPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'task-widget:task-view')]";
  }

  public void openTask(String taskName) {
    $("div[id='task-widget:task-view-container']").waitUntil(appear, DEFAULT_TIMEOUT);
    $$("div[id='task-widget:task-view-container'] ul li div[id$=':task-item:task-start'] div.task-start-info span")
        .filter(text(taskName)).first().click();
  }

  public void openAdvancedFilter(String filterName, String filterIdName) {
    $("a[id$='filter-add-action']").waitUntil(appear, DEFAULT_TIMEOUT);
    $("a[id$='filter-add-action']").click();
    $("table[id$='task-widget:filter-add-form:filter-selection']").waitUntil(appear, DEFAULT_TIMEOUT);
    $$("table[id$='task-widget:filter-add-form:filter-selection'] label").filter(text(filterName)).first().click();
    $("button[id$='task-widget:filter-add-form:update-filter-selected-command']").click();
    $("div[id$='task-widget:filter-add-panel']").waitUntil(disappear, DEFAULT_TIMEOUT);
    $("span[id$='" + filterIdName + "-filter:advanced-filter-component").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public void filterTasksByCreatedDate(String fromCreatedDate, String toCreatedDate) {
    $("button[id$='created-filter:filter-open-form:advanced-filter-command']").click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").waitUntil(appear, DEFAULT_TIMEOUT);
    $("input[id$='created-filter:filter-input-form:from-created-calendar_input'").sendKeys(fromCreatedDate);
    closePanelDatePicker($("div[id$='created-filter:filter-input-form:from-created-calendar_panel'"));
    $("input[id$='created-filter:filter-input-form:to-created-calendar_input'").sendKeys(toCreatedDate);
    closePanelDatePicker($("div[id$='created-filter:filter-input-form:to-created-calendar_panel'"));
    $("button[id$='created-filter:filter-input-form:update-command']").click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").waitUntil(disappear, DEFAULT_TIMEOUT);
  }
  
  private void closePanelDatePicker(SelenideElement element) {
    Selenide.executeJavaScript("arguments[0].style.display = 'none'", element);
  }

  public ElementsCollection countTasks() {
    return $$("div[id$='task-widget:task-view-container'] ul li");
  }

  public void runTaskWithRunTheTaskBehaviour(int position) {
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:runnable-task-info']").waitUntil(appear, DEFAULT_TIMEOUT);
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:runnable-task-info']").click();
  }

  public void openTaskWithAccessTaskDetailsBehaviour(int position) {
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:task-info']").waitUntil(appear, DEFAULT_TIMEOUT);
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:task-info']").click();
  }
  
  public SelenideElement getFilterTasksByKeyword() {
    return $("input[id='task-widget:expanded-mode-filter-form:expanded-mode-filter-container:ajax-keyword-filter']");
  }
  
  public void filterTasksBy(String keyword) {
    getFilterTasksByKeyword().waitUntil(appear, DEFAULT_TIMEOUT).clear();
    getFilterTasksByKeyword().click();
    getFilterTasksByKeyword().sendKeys(keyword);
  }
  
  public void clickOnTaskActionLink(int taskIndex) {
    $(String.format("a[id$='task-list-scroller:%d:task-item:task-action:additional-options:task-side-steps-menu'",
        taskIndex)).waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }
  
  private void openTriggerEscalationDialog() {
    $("a[id$='\\:task-trigger-escalation-command']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$='\\:escalation-task-confirmation-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void triggerEscalation() {
    openTriggerEscalationDialog();
    $("button[id$='\\:confirm-escalation']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }
  
  public SelenideElement getTaskState(int taskRowIndex) {
    return $(String.format("[id='task-widget:task-list-scroller:%d:task-item:task-state-component:task-state']",
        taskRowIndex)).waitUntil(Condition.appear, DEFAULT_TIMEOUT).$("span");
  }
  
  public String getPriorityOfTask(int row) {
    String priorityClassName = $("span[id$='" + row +":task-item:task-priority-component:task-priority'] > span > i").attr("class");
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
}
