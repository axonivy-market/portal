package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebElement;

public class QRCodePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='mobile-app-dialog']";
  }
  
  public boolean isQRCodeDisplayed() {
    return $("[id='qr-code']").isDisplayed(); 
  }
  
  public WebElement getQRCodeDialog() {
    return $("[id='mobile-app-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void closeQRCodeDialog() {
    $("button[id='mobile-app-close-button']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }

}
