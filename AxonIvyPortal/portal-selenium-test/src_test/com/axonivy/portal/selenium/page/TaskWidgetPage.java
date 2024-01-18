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
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.Sleeper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class TaskWidgetPage extends TemplatePage {

  private String taskWidgetId;

  @Override
  protected String getLoadedLocator() {
    return ".js-task-widget-header";
  }
  
//  public TaskWidgetPage(String taskWidgetId) {
//    this.taskWidgetId = taskWidgetId;
//  }

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
  
  public TaskTemplatePage clickOnSideStepAction(int taskIndex, int sideStepIndex) {
    String sideStepsId = String.format(
        "task-widget:task-list-scroller:%d:task-item:task-action:additional-options:task-additional-actions",
        taskIndex);
    SelenideElement sideStepPanel = $("[id$='" + sideStepsId + "']");
    ElementsCollection sideSteps = sideStepPanel.findAll(By.className("option-item"));
    sideSteps.get(sideStepIndex).click();
    return new TaskTemplatePage();
  }

  public SelenideElement getSaveFilterDialog() {
    $("[id$='task-widget:filter-save-action']").shouldBe(getClickableCondition()).click();
    $(By.id("task-widget:filter-save-form:save-filter-set-name-input")).shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id$=':save-filter-set-name-input']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $(By.id("task-widget:save-filter-set-dialog")).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void closeSaveFilterDialog() {
    $("a[id^='task-widget:filter-save-form']").shouldBe(getClickableCondition()).click();
    $(By.id("task-widget:save-filter-set-dialog")).shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public TaskDetailsPage openTaskDetail(int index) {
    openTaskWithAccessTaskDetailsBehaviour(index);
    TaskDetailsPage detailsPage = new TaskDetailsPage();
    detailsPage.waitPageLoaded();
    return new TaskDetailsPage();
  }

  public void openCompactSortMenu() {
    $("[id$='sort-task-menu_label']").shouldBe(getClickableCondition()).click();
    $(By.cssSelector("div[id$='sort-task-menu_panel']")).shouldBe(appear, TEN_SECOND);
  }

  public void clickToWaitAjaxDisappear() {
    $("[id$='task-widget:filter-add-action']").shouldBe(getClickableCondition()).click();
    $("[id$='task-widget:filter-add-action']").shouldBe(getClickableCondition()).click();
    $("[id$='task-widget:filter-add-action']").shouldBe(getClickableCondition()).click();
    $("[id$='task-widget:filter-add-action']").shouldBe(getClickableCondition()).click();
  }
  
  public void openStateFilter() {
    $(By.cssSelector("button[id$='state-filter:filter-open-form:advanced-filter-command']")).shouldBe(getClickableCondition()).click();
    $(By.cssSelector("[id$='state-filter:filter-input-form:state-selection']")).shouldBe(appear, TEN_SECOND);
  }

  public void clickOnTaskStatesAndApply(List<String> states) {
    openStateFilter();

    $("[id$='state-filter:filter-input-form:advanced-filter-panel']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("label").asFixedIterable().stream().filter(filter -> !states.contains(filter.getText()))
        .forEach(filter -> filter.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    
    $(By.cssSelector("button[id$='state-filter:filter-input-form:update-command']")).shouldBe(getClickableCondition())
        .click();
    waitForElementDisplayed(By.cssSelector("button[id$='state-filter:filter-input-form:update-command']"), false);
  }

  private WebElement getStateFilterPanel() {
    return findElementByCssSelector("div[id$='state-filter:filter-input-form:advanced-filter-panel']");
  }
  
  public void expand() {
    waitForElementDisplayed(By.cssSelector("a[id$=':task-list-link:task-list-link']"), Boolean.TRUE);
    WebElement fullModeButton = findElementById(taskWidgetId + ":task-list-link:task-list-link");
    $(fullModeButton).shouldBe(getClickableCondition()).click();
    WaitHelper.assertTrueWithWait(() -> isElementDisplayed(By.cssSelector("li.topbar-item.breadcrumb-container")));
    WaitHelper.assertTrueWithWait(() -> isElementDisplayed(By.cssSelector("[id$=':filter-save-action']")));
//    waitForLocatorDisplayed("id('" + taskWidgetId + ":filter-save-action')");
    waitForElementDisplayed(By.id(taskWidgetId + ":filter-save-action"), Boolean.TRUE);
  }

  public void clickColumnsButton() {
//    clickByCssSelector("[id$='task-widget:task-columns-configuration:task-config-command']");
    $("a[id$='task-widget:task-columns-configuration:task-config-command']").shouldBe(getClickableCondition()).click();
    waitForElementDisplayed(By.cssSelector("label[for$=':columns-checkbox:3']"), Boolean.TRUE);
//    $("label[for$=':columns-checkbox:3']")
//    Sleeper.sleep(2000);
  }

  

}

