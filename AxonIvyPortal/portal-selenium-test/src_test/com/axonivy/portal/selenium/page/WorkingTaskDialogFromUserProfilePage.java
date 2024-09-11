package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class WorkingTaskDialogFromUserProfilePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='task-leave-warning-component:logo-task-losing-confirmation-dialog']";
  }

  public UserProfilePage leaveTask() {
    waitForElementClickableThenClick($(By.id("task-leave-warning-component:leave-button")));
    return new UserProfilePage();
  }

  public UserProfilePage reserveTask() {
    waitForElementClickableThenClick($(By.id("task-leave-warning-component:reserve-task-button")));
    return new UserProfilePage();
  }
}
