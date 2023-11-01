package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebElement;

public class ChangePasswordPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='change-password-dialog_title']";
  }

  public WebElement getChangePasswordDialog() {
    return $("[id='change-password-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}
