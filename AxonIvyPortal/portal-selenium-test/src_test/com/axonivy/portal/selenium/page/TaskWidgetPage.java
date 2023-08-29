package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TaskWidgetPage extends TemplatePage {

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
    for (int i=0; i < checkboxLabel.size(); i ++) {
      if (checkboxLabel.get(i).getText().equals("Select All")) {
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
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:runnable-task-info']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:runnable-task-info']").click();
  }

  public void openTaskWithAccessTaskDetailsBehaviour(int position) {
    $("div[id='task-widget:task-list-scroller:" + position + ":task-item:task-info']").shouldBe(appear, DEFAULT_TIMEOUT);
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
    WaitHelper.waitNumberOfElementsToBe(WebDriverRunner.getWebDriver(), By.cssSelector(".ui-datascroller-list .ui-datascroller-item"), 1);
  }

  public void clickOnTaskActionLink(int taskIndex) {
    $(String.format("a[id$=':%d:task-item:task-action:additional-options:task-side-steps-menu']", taskIndex))
      .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $(String.format("div[id$=':%d:task-item:task-action:additional-options:side-steps-panel']", taskIndex))
      .shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  private void openTriggerEscalationDialog() {
    $("a[id$='task-trigger-escalation-command']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$='escalation-task-confirmation-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void triggerEscalation() {
    openTriggerEscalationDialog();
    $("button[id$='confirm-escalation']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public void openTaskDelegationDialog() {
    $("a[id$='task-delegate-command']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$='task-delegate-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public boolean isDelegateTypeDisabled(int index) {
    return getDelegateType(index).getDomAttribute("class").contains("ui-state-disabled");
  }

  public boolean isDelegateTypeAvailable() {
    return $("div[id$=':task-delegate-form:activator-panel']").isDisplayed();
  }

  public String getCannotDelegateText() {
    return $("div[id$='task-delegate-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$(".ui-dialog-content span").shouldBe(Condition.appear, DEFAULT_TIMEOUT).getText();
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
    var priorityClassName = $(String.format("span[id$=':%d:task-item:task-priority-component:task-priority']", taskIndex))
          .shouldBe(appear, DEFAULT_TIMEOUT)
          .$("span.priority-icon").$("i.priority").attr("class");
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
}
