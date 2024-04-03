package com.axonivy.portal.selenium.page;

public class TaskTemplateIFramePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='task-template-title']";
  }

  public NewDashboardPage clickSubmitButton() {
    waitForElementClickableThenClick("button[id$='button-submit']");
    switchToDefaultContent();
    return new NewDashboardPage();
  }

}
