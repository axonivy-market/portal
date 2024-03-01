package com.axonivy.portal.selenium.page;

public class TaskTemplateIFramePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='task-template-title']";
  }

  public NewDashboardPage clickSubmitButton() {
    waitForElementClickableThenClick("button[id$='button-submit']");
    waitPageDisappear();
    switchToDefaultContent();
    return new NewDashboardPage();
  }

  public NewDashboardPage clickCancelButton() {
    waitForElementClickableThenClick("a[id$='button-cancel']");
    waitPageDisappear();
    switchToDefaultContent();
    return new NewDashboardPage();
  }
}
