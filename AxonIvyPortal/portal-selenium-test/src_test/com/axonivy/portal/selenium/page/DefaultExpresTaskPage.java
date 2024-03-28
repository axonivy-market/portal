package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class DefaultExpresTaskPage extends TaskTemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='form:cancel-btn']";
  }

  public void enterTextToDefaultTask(String text) {
    var textArea = findElementByCssSelector("#form\\:user-task-dyna-form textarea[id$='input-text-area']");
    textArea.clear();
    textArea.sendKeys(text);
    var textAreaId = textArea.getAttribute(ID_PROPERTY);
    textAreaId.replace(":", "\\\\:");
  }

  public void finishDefaultTask() {
    waitForElementClickableThenClick($(By.id("form:ok-btn")));
  }

}
