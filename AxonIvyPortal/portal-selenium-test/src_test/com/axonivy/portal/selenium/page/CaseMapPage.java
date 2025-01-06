package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class CaseMapPage extends TaskTemplatePage {

  @Override
  public void waitForIFrameContentVisible() {
    $("button[id='form:submit-request']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void clickSubmitRequestButton() {
    $("button[id$='submit-request']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    switchBackToParent();
  }

  public void clickSubmitButtonAndBackToTaskList() {
    $("button[id$='submit-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    switchBackToParent();
  }

  public String getHeader() {
    return findElementByCssSelector("#header").getText();
  }

  public void inputFields(String lastName, String firstName, String birthDate, String country, String amount,
      String reason, String salary, String otherCredit) {
    waitForIFrameContentVisible();
    inputField("form:last-name", lastName);
    inputField("form:first-name", firstName);
    typeBirthDay(birthDate);
    inputField("form:country", country);
    inputField("form:amount", amount);
    inputField("form:reason", reason);
    inputField("form:salary", salary);
    inputField("form:amount-of-other-credits", otherCredit);
  }

  private void typeBirthDay(String text) {
    SelenideElement datePicker = findElementByCssSelector("input[id='form:birth-date_input']");
    datePicker.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    datePicker.sendKeys(Keys.BACK_SPACE);
    datePicker.sendKeys(text);
  }

  public SelenideElement getCustomerLastName() {
    return findElementByCssSelector("input[id$='last-name']");
  }

  public SelenideElement getCustomerFirstName() {
    return findElementByCssSelector("input[id$='first-name']");
  }

  public SelenideElement getCountry() {
    return findElementByCssSelector("input[id$='customer-country']");
  }

  public SelenideElement getAmount() {
    return findElementByCssSelector("input[id$='request-amount']");
  }

  public SelenideElement getSalary() {
    return findElementByCssSelector("input[id$='request-salary']");
  }

  public SelenideElement getReason() {
    return findElementByCssSelector("input[id$='request-reason']");
  }

  public SelenideElement getOtherCredits() {
    return findElementByCssSelector("input[id$='request-amount-of-other-credits']");
  }

  public SelenideElement getVerifierComment() {
    return findElementByCssSelector("textarea[id$='verifier-comment']");
  }

  public SelenideElement getInternalCreditComment() {
    return findElementByCssSelector("textarea[id$='internal-comment']");
  }

  @Override
  public void inputField(String id, String value) {
    $(By.id(id)).clear();
    $(By.id(id)).sendKeys(value);
  }

  public void clickSubmitButtonCaseMap() {
    clickByJavaScript($("button[id$='form:submit-button']"));
    waitPageDisappear();
    switchBackToParent();
  }

  public void clickApproveButton() {
    clickByJavaScript($("button[id$='form:approval-button']"));
    waitPageDisappear();
    switchBackToParent();
  }

  public void clickSubmitContractButton() {
    clickByJavaScript($("button[id$='submit-contract-button']"));
    waitPageDisappear();
    switchBackToParent();
  }

  public void clickRejectButton() {
    clickByJavaScript($("button[id$='form:rejected-button']"));
    waitPageDisappear();
    switchBackToParent();
  }

}
