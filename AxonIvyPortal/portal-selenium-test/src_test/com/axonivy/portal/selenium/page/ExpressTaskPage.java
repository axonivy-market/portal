package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;


import org.openqa.selenium.By;

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
    $(String.format("input[data-p-rmsg*='%s']", label)).shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(data);
  }

  public void finish() {
    $("[id='form:ok-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public boolean isDocumentTableVisible() {
    return isElementPresent(By.xpath("//div[contains(@id, 'fileUploadComponent:document-table')]"));
  }

  public boolean isDocumentUploadButtonVisible() {
    return isElementPresent(By.xpath("//div[contains(@id, 'fileUploadComponent:document-upload')]"));
  }
  
  public void openCaseInfo() {
    clickByCssSelector("#horizontal-case-info");
    waitForElementDisplayed(By.cssSelector("[id$='i-frame-case-details']"), true);
  }
  
  public void clickOnAdditionalBusinessDetailLink() {
    switchToIframeWithId("i-frame-case-details");
    $("a[id$=':action-group:case-details-action-link']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$=':action-group:action-steps-panel']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$=':action-group:show-additional-case-details-link']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

}
