package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class DefaultExpressTaskPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='form:cancel-btn']";
  }

  public void enterTextToDefaultTask(String text) {
    var textArea = $("[id='form:user-task-dyna-form']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("textarea[id$=':input-text-area']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    textArea.clear();
    textArea.sendKeys(text);
  }

  public void clickOK() {
    $("[id='form:ok-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }
}
