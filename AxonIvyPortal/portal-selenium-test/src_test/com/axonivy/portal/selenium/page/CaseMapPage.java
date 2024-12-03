package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.test.userexample.page.UserExamplesEndPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class CaseMapPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id$='content-container']";
  }

  public void waitForIFrameContentVisible() {
    $("button[id='form:submit-request']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public NewDashboardPage clickSubmitRequestButton() {
    $("button[id$='submit-request']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    switchBackToParent();
    return new NewDashboardPage();
  }

  public NewDashboardPage clickSubmitButtonAndBackToTaskList() {
    clickByJavaScript($("[id$='submit-button']"));
    switchBackToParent();
    return new NewDashboardPage();
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

  public void inputField(String id, String value) {
    $(By.id(id)).clear();
    $(By.id(id)).sendKeys(value);
  }

  public TaskWidgetPage clickSubmitButton() {
    $("button[id$='form:submit-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    switchBackToParent();
    return NavigationHelper.navigateToTaskList();
  }

  public TaskWidgetPage clickApproveButton() {
    waitForElementClickableThenClick("button[id$='form:approval-button']");
    switchToDefaultContent();
    return NavigationHelper.navigateToTaskList();
  }

  public UserExamplesEndPage clickSubmitContractButton() {
    waitForElementClickableThenClick("button[id$='submit-contract-button']");
    switchToDefaultContent();
    return new UserExamplesEndPage();
  }

  public TaskWidgetPage clickRejectButton() {
    waitForElementClickableThenClick("button[id$='form:rejected-button']");
    switchToDefaultContent();
    return NavigationHelper.navigateToTaskList();
  }

  public String getValidationMsg() {
    $(".ui-messages-error-summary").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    List<SelenideElement> messages = $$(".ui-messages-error-summary");
    return StringUtils.join(messages.stream().map(SelenideElement::getText).collect(Collectors.toList()), ",");
  }
  
  public String clickSubmitAndGetValidationMsg() {
    $("button[id$='submit-request']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(".ui-messages-error-icon").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return getValidationMsg();
  }

  public void waitForCollectPersonalDataFormVisible() {
    $("form[id='form']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

}
