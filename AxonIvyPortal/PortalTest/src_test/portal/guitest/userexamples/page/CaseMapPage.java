package portal.guitest.userexamples.page;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class CaseMapPage extends TaskTemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('form:first-name')";
  }

  public void inputFields(String lastName, String firstName, String birthDate, String country, String amount, String reason,
      String salary, String otherCredit) {
    waitForIFrameContentVisible();
    type(By.id("form:last-name"), lastName);
    type(By.id("form:first-name"), firstName);
    typeBirthDay(birthDate);
    type(By.id("form:country"), country);
    type(By.id("form:amount"), amount);
    type(By.id("form:reason"), reason);
    type(By.id("form:salary"), salary);
    type(By.id("form:amount-of-other-credits"), otherCredit);
  }
  
  private void typeBirthDay(String text) {
    WebElement datePicker = findElementByCssSelector("input[id='form:birth-date_input']");
    datePicker.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    datePicker.sendKeys(Keys.BACK_SPACE);
    datePicker.sendKeys(text);
  }
  
  public void inputVerifierComment(String comment) {
    type(By.id("form:verifier-comment"), comment);
  }
  
  public void inputExternalCreditComment(String comment) {
    type(By.id("form:external-comment"), comment);
  }
  
  public void inputInternalCreditComment(String comment) {
    type(By.id("form:internal-comment"), comment);
  }

  public String getCustomerLastName() {
    return findElementByCssSelector("input[id$='last-name']").getAttribute("value");
  }

  public String getCustomerFirstName() {
    return findElementByCssSelector("input[id$='first-name']").getAttribute("value");
  }

  public String getCountry() {
    return findElementByCssSelector("input[id$='customer-country']").getAttribute("value");
  }

  public String getAmount() {
    return findElementByCssSelector("input[id$='request-amount']").getAttribute("value");
  }

  public String getSalary() {
    return findElementByCssSelector("input[id$='request-salary']").getAttribute("value");
  }

  public String getReason() {
    return findElementByCssSelector("input[id$='request-reason']").getAttribute("value");
  }

  public String getOtherCredits() {
    return findElementByCssSelector("input[id$='request-amount-of-other-credits']").getAttribute("value");
  }

  public String getVerifierComment() {
    return findElementByCssSelector("textarea[id$='verifier-comment']").getText();
  }

  public String getInternalCreditComment() {
    return findElementByCssSelector("textarea[id$='internal-comment']").getText();
  }

  @SuppressWarnings("deprecation")
  public String clickSubmitAndGetValidationMsg() {
    clickByCssSelector("button[id$='submit-request']");
    waitForElementDisplayedByCssSelector(".ui-messages-error-icon");
    waitAjaxIndicatorDisappear();
    return getValidationMsg();
  }

  public String getValidationMsg() {
    List<WebElement> messages = findListElementsByCssSelector(("span.ui-messages-error-summary"));
    return StringUtils.join(messages.stream().map(WebElement::getText).collect(Collectors.toList()), ",");
  }

  public void waitUntilCaseMapPageDisplayed() {
    waitForElementDisplayed(By.id("form:submit-request"), true);
  }
  
  @Override
  public HomePage clickSubmitButton() {
    clickByCssSelector("button[id$='submit-button']");
    switchToDefaultContent();
    return new HomePage();
  }
  
  public HomePage clickSubmitRequestButton() {
    clickByCssSelector("button[id$='submit-request']");
    switchToDefaultContent();
    return new HomePage();
  }

  public HomePage clickApproveButton() {
    clickByCssSelector("button[id$='form:approval-button']");
    switchToDefaultContent();
    return new HomePage();
  }
  
  public HomePage clickRejectButton() {
    clickByCssSelector("button[id$='form:rejected-button']");
    switchToDefaultContent();
    return new HomePage();
  }
  
  public UserExamplesEndPage clickSubmitContractButton() {
    clickByCssSelector("button[id$='submit-contract-button']");
    switchToDefaultContent();
    return new UserExamplesEndPage();
  }
  
  public String getTitle() {
    return findElementByCssSelector("[id$='title']").getText();
  }
  
  public String getHeader() {
    return findElementByCssSelector("#header").getText();
  }

  public TaskWidgetPage clickSubmitButtonAndBackToTaskList() {
    clickByCssSelector("button[id$='submit-button']");
    switchToDefaultContent();
    return new TaskWidgetPage();
  }
}
