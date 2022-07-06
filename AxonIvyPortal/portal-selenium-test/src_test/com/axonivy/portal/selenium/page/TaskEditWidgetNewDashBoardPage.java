package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class TaskEditWidgetNewDashBoardPage extends TemplatePage {

  private String taskEditWidgetId;
  private static final String TASK_NAME = "Task name";
  private static final String STATE = "State";

  public TaskEditWidgetNewDashBoardPage() {
    this("div[id='new-widget-configuration-dialog']");
  }

  public TaskEditWidgetNewDashBoardPage(String taskWidgetId) {
    this.taskEditWidgetId = taskWidgetId;
  }

  @Override
  protected String getLoadedLocator() {
    return ".task-configuration__responsibles";
  }

  private SelenideElement widgetTitle() {
    return $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$("span[id$='widget-title-group']")
        .$("input[id$='widget-title']");
  }

  public void changeWidgetTitle(String name) {
    widgetTitle().clear();
    widgetTitle().sendKeys(name);
  }
  
  private int getIndexFiltertByName(String name) {
    ElementsCollection elementsTH =
        $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$("div[id$='user-filter']").$$("div");
    for (int i = 0; i < elementsTH.size(); i++) {
      if (elementsTH.get(i).getText().equalsIgnoreCase(name)) {
        return i;
      }
    }
    return 0;
  }
  
  private SelenideElement getAvailableFilterInput(String filterName) {
    int index = getIndexFiltertByName(filterName);
    return $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$("div[id$='user-filter']").$$("div").get(index + 1)
        .$("input");
  }
  
  public void filterTaskName(String taskName) {
    getAvailableFilterInput(TASK_NAME).sendKeys(taskName);
  }
  
  public void clickOnStateToShowDropdown() {
    int index = getIndexFiltertByName(STATE);
    $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$("div[id$='user-filter']").$$("div").get(index + 1)
        .click();
  }
  
  private SelenideElement getValueOfCheckBox(String value) {
    return $("div[style*='display: block;'] div.ui-selectcheckboxmenu-items-wrapper")
        .waitUntil(appear, DEFAULT_TIMEOUT).$$("li.ui-selectcheckboxmenu-item").filter(text(value)).first().$("div.ui-chkbox-box");
  }
  
  private SelenideElement getCloseCheckBox() {
    return $("div.ui-selectcheckboxmenu-panel[style*='display: block;']").waitUntil(appear, DEFAULT_TIMEOUT).$("a.ui-selectcheckboxmenu-close");
  }
  
  public void selectState(String state) {
    getValueOfCheckBox(state).shouldBe(getClickableCondition()).click();
    getCloseCheckBox().shouldBe(getClickableCondition()).click();
  }
  
  public void preview() {
    $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$("button[id$='preview-button']")
        .shouldBe(getClickableCondition()).click();
  }
  
  private ElementsCollection getColumnsOfTableWidget() {
    return $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$$("table tbody tr td");
  }
  
  private ElementsCollection getAllTasksOfTaskWidget() {
    return getColumnsOfTableWidget().filter(Condition.cssClass("dashboard-tasks__name"));
  }
  
  public ElementsCollection countAllTasks() {
    return getAllTasksOfTaskWidget();
  }
  
  
  public void nextPageTable() {
    $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$("div[id$='widget-preview']")
    .waitUntil(appear, DEFAULT_TIMEOUT).$("a.ui-paginator-next").waitUntil(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }
  
  public void waitPageSelected(int pageNumber) {
    $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$("div[id$='widget-preview']")
    .waitUntil(appear, DEFAULT_TIMEOUT).$$("a.ui-paginator-page").get(pageNumber-1).waitUntil(Condition.attributeMatching("class", ".*ui-state-active.*"), DEFAULT_TIMEOUT);
  }
  
  public void save() {
    $(taskEditWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$("button[id$='widget-configuration-save-button']")
        .shouldBe(getClickableCondition()).click();
    $("[id$='task-component:loading']").waitUntil(disappear, DEFAULT_TIMEOUT);
  }

}
