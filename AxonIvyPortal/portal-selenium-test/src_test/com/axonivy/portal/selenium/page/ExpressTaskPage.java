package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class ExpressTaskPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".task-template-container";
  }

  public SelenideElement findExpressTask() {
    return $(".js-task-header-container").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id='task-template-title']");
  }

  public void waitForExpressFieldSetDisplay() {
    $(".express-fieldset").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void enterRequiredInputFieldByLabel(String label, String data) {
    $(String.format("input[data-p-rmsg*='%s']",  label)).shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(data);
  }

  public void finish() {
    $("[id='form:ok-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }
}
