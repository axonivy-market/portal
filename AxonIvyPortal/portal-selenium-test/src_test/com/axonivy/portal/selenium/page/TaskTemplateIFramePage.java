package com.axonivy.portal.selenium.page;

public class TaskTemplateIFramePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='task-template-title']";
  }

  public NewDashboardPage clickSubmitButton() {
    clickSubmit();

    try {
      return new NewDashboardPage();
    } catch (AssertionError e) {
      switchToIFrameOfTask();
      clickSubmit();
    }
    return new NewDashboardPage();
  }

  private void clickSubmit() {
    waitForElementClickableThenClick("button[id$='button-submit']");
    waitPageDisappear();
    switchToDefaultContent();
  }

  public NewDashboardPage clickCancelButton() {
    clickCancel();

    try {
      return new NewDashboardPage();
    } catch (AssertionError e) {
      switchToIFrameOfTask();
      clickCancel();
    }
    return new NewDashboardPage();
  }

  private void clickCancel() {
    waitForElementClickableThenClick("a[id$='button-cancel']");
    waitPageDisappear();
    switchToDefaultContent();
  }
}
