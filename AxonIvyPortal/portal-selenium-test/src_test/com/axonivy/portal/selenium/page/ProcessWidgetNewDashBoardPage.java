package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;

public class ProcessWidgetNewDashBoardPage extends TemplatePage {

  private static final String YOUR_PROCESSES_WIDGET = "Your Processes";

  String processWidgetId;
  private String processWidgetName;

  public ProcessWidgetNewDashBoardPage() {
    this("div[id$='process-list']", YOUR_PROCESSES_WIDGET);
  }

  @Override
  protected String getLoadedLocator() {
    return "[id$='dashboard-cases-container']";
  }

  public ProcessWidgetNewDashBoardPage(String processWidgetName) {
    this("div[id$='dashboard-process']", processWidgetName);
  }

  public ProcessWidgetNewDashBoardPage(String processWidgetId, String processWidgetName) {
    this.processWidgetId = processWidgetId;
    this.processWidgetName = processWidgetName;
  }
  
  public ElementsCollection expand() {
    return $$("div.widget__header").filter(text(processWidgetName));
  }
  
  public void startProcessByName(String processName) {
    var startProcess = $(processWidgetId).waitUntil(appear, DEFAULT_TIMEOUT).$$("span.process-start-list-item").filter(text(processName))
        .first().$("a");
    waitUntilElementToBeClickable(startProcess);
    startProcess.click();
  }
  
  public void deleteProcessWidget() {
    $$("div.table-widget-panel div.widget__header").filter(text(processWidgetName)).first()
        .waitUntil(appear, DEFAULT_TIMEOUT).$("div[id$='widget-header-actions']").$("[id*='delete-widget']")
        .shouldBe(getClickableCondition()).click();
  }
}
