package portal.guitest.userexamples.page;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;

public class LeaveRequestPage extends TaskTemplatePage {

  public void clickSubmitLeaveRequest() {
    click(By.id("leave-request:button-submit"));
  }
  
  public String clickSubmitAndGetValidationMsg() {
    clickSubmitLeaveRequest();
    waitAjaxIndicatorDisappear();
    return getValidationMsg();
  }
  
  public String getValidationMsg() {
    List<WebElement> messages = findListElementsByCssSelector(("span.ui-messages-error-summary"));
    return StringUtils.join(messages.stream().map(WebElement::getText).collect(Collectors.toList()), ","); 
  }
  
  public void enterLeaveRequestInformation(String leaveType, String from, String to, String approver, String requesterComment) {
    selectLeaveType(leaveType);
    enterKeys(findElementById("leave-request:from_input"), from);
    enterKeys(findElementById("leave-request:to_input"), to);
    enterKeys(findElementById("leave-request:requester-comment"), requesterComment);
    selectApprover(approver);
  }
  
  public void enterApproverComment(String approverComment) {
    enterKeys(findElementById("leave-request:approver-comment"), approverComment);
  }
  
  public HomePage clickApproveBtn() {
    click(By.id("leave-request:approved-btn"));
    return new HomePage();
  }
  
  public HomePage clickRejectBtn() {
    click(By.id("leave-request:rejected-btn"));
    return new HomePage();
  }
  
  public UserExamplesEndPage finishLeaveRequest() {
    click(By.id("leave-request:finish-btn"));
    return new UserExamplesEndPage();
  }
  
  private void selectLeaveType(String leaveType) {
    findElementById("leave-request:leave-type_label").click();
    String leaveTypeSelector = "li[data-label='" + leaveType + "']";
    waitForElementDisplayed(By.cssSelector(leaveTypeSelector), true);
    clickByCssSelector(leaveTypeSelector);
  }
  
  private void selectApprover(String approver) {
    findElementById("leave-request:approver_label").click();
    String approverSelector = "li[data-label='" + approver + "']";
    waitForElementDisplayed(By.cssSelector(approverSelector), true);
    clickByCssSelector(approverSelector);
  }

  public void waitUntilLeaveRequestPageDisplayed() {
    waitForElementDisplayed(By.id("leave-request:button-submit"), true);
  }
}
