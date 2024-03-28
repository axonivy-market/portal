package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class WorkingTaskDialogPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='task-leave-warning-component:logo-task-losing-confirmation-dialog']";
  }

  public NewDashboardPage leaveTask() {
    clickByJavaScript($(By.id("task-leave-warning-component:leave-button")));
    return new NewDashboardPage();
  }

  public NewDashboardPage reserveTask() {
    clickByJavaScript($(By.id("task-leave-warning-component:reserve-task-button")));
    return new NewDashboardPage();
  }
}
