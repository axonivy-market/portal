package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class ProcessWidgetNewDashBoardPage extends TemplatePage {

  private static final String YOUR_PROCESSES_WIDGET = "Your Processes";
  private static final String FILTER_TASK_STATE = "State";

  private String processWidgetId;
  private String processWidgetName;

  public ProcessWidgetNewDashBoardPage() {
    this("form[id$='process-list']", YOUR_PROCESSES_WIDGET);
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
}
