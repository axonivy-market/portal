package portal.guitest.userexamples.page;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;

public class CaseMapPage extends TaskTemplatePage {

  public void inputFields(String lastName, String firstName, String birthDate, String country, String amount, String reason,
      String salary, String otherCredit) {
    type(By.id("form:last-name"), lastName);
    type(By.id("form:first-name"), firstName);
    type(By.id("form:birth-date_input"), birthDate);
    type(By.id("form:country"), country);
    type(By.id("form:amount"), amount);
    type(By.id("form:reason"), reason);
    type(By.id("form:salary"), salary);
    type(By.id("form:amount-of-other-credits"), otherCredit);
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

  public String clickSubmitAndGetValidationMsg() {
    clickByCssSelector("button[id$='submit-request']");
    waitAjaxIndicatorDisappear();
    return getValidationMsg();
  }

  public String getValidationMsg() {
    List<WebElement> messages = findListElementsByCssSelector(("span.ui-messages-error-summary"));
    return StringUtils.join(messages.stream().map(WebElement::getText).collect(Collectors.toList()), ",");
  }

  @Override
  public HomePage clickSubmitButton() {
    clickByCssSelector("button[id$='submit-button']");
    return new HomePage();
  }
  
  public HomePage clickSubmitRequestButton() {
    clickByCssSelector("button[id$='submit-request']");
    return new HomePage();
  }

  public HomePage clickApproveButton() {
    clickByCssSelector("button[id$='form:approval-button']");
    return new HomePage();
  }
  
  public HomePage clickRejectButton() {
    clickByCssSelector("button[id$='form:rejected-button']");
    return new HomePage();
  }
  
  public void clickSubmitContractButton() {
    clickByCssSelector("button[id$='submit-contract-button']");
  }
}
