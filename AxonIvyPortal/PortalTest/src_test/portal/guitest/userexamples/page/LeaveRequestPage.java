package portal.guitest.userexamples.page;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.page.TaskTemplatePage;

public class LeaveRequestPage extends TaskTemplatePage {

  public void clickSubmitLeaveRequest() {
    click(By.id("leave-request:button-submit"));
  }
  
  public String clickSubmitAndGetValidationMsg() {
    clickSubmitLeaveRequest();
    waitForElementExisted("span.ui-messages-error-summary", true, DEFAULT_TIMEOUT);
    return getValidationMsg();
  }
  
  public String getValidationMsg() {
    List<WebElement> messages = findListElementsByCssSelector(("span.ui-messages-error-summary"));
    return StringUtils.join(messages.stream().map(WebElement::getText).collect(Collectors.toList()), ","); 
  }
}
