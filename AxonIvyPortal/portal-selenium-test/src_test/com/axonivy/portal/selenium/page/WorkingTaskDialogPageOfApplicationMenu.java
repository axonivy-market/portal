package com.axonivy.portal.selenium.page;

import org.openqa.selenium.By;

public class WorkingTaskDialogPageOfApplicationMenu extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id$='user-menu-required-login:warning-before-leaving-task-component:task-leave-warning-dialog']";
  }

  public NewDashboardPage leaveTask() {
    waitForElementClickableThenClick(
        By.id("user-menu-required-login:warning-before-leaving-task-component:leave-button"));
    return new NewDashboardPage();
  }
}
