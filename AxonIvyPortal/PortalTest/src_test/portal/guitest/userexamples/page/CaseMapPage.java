package portal.guitest.userexamples.page;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.page.TemplatePage;

public class CaseMapPage extends TemplatePage {

  
  public void inputFields(String name, String firstName, String birthDate, String country, String amount, String reason, String salary, String otherCredit) {
    type(By.id("form:name"),name );
    type(By.id("form:firstName"), firstName);
    type(By.id("form:birthdate_input"), birthDate);
    type(By.id("form:country"), country);
    type(By.id("form:amount"), amount);
    type(By.id("form:reason"), reason);
    type(By.id("form:salary"), salary);
    type(By.id("form:amountOfOtherCredits"), otherCredit);
  }
  
  public void clickSubmitButton() {
    String submitButton = "button[id$='submit-request']";
    clickByCssSelector(submitButton);
    waitAjaxIndicatorDisappear();
  }
  
  
  public String getCustomerName() {
    return findElementByCssSelector("input[id$='customer-name']").getAttribute("value");
  }
  
  public String getCustomerFirstName() {
    return findElementByCssSelector("input[id$='customer-first-name']").getAttribute("value");
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
  
  public String clickSubmitAndGetValidationMsg() {
    clickSubmitButton();
    waitForElementDisplayed(By.cssSelector(".ui-messages-error-icon"), true); 
    return getValidationMsg();
  }
  
  public String getValidationMsg() {
    List<WebElement> messages = findListElementsByCssSelector(("span.ui-messages-error-summary"));
    return StringUtils.join(messages.stream().map(WebElement::getText).collect(Collectors.toList()), ","); 
  }
}
