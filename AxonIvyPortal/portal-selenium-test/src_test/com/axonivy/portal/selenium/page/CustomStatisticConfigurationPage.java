package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class CustomStatisticConfigurationPage  extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='config-form']";
  }
  
  public SelenideElement getAdvancedSettings() {
    return $("fieldSet[id$='config-form:advanced-settings']").shouldBe(appear, DEFAULT_TIMEOUT).scrollIntoCenter();
  }
}
