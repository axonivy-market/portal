package portal.guitest.userexamples.page;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import portal.guitest.common.WaitHelper;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplateIFramePage;

public class LeaveRequestPage extends TaskTemplateIFramePage {

  @Override
  protected String getLoadedLocator() {
    return "id('leave-request:button-submit')";
  }

  public void clickSubmitLeaveRequest() {
    click(By.id("leave-request:button-submit"));
  }
  
  @SuppressWarnings("deprecation")
  public String clickSubmitAndGetValidationMsg() {
    int numberOfErrors = findListElementsByCssSelector(("span.ui-messages-error-summary")).size();
    clickSubmitLeaveRequest();
    WaitHelper.assertTrueWithWait(
        () -> findListElementsByCssSelector(("span.ui-messages-error-summary")).size() != numberOfErrors);
    waitAjaxIndicatorDisappear();
    return getValidationMsg();
  }
  
  public String getValidationMsg() {
    List<WebElement> messages = findListElementsByCssSelector(("span.ui-messages-error-summary"));
    return StringUtils.join(messages.stream().map(WebElement::getText).collect(Collectors.toList()), ","); 
  }
  
  public void enterLeaveRequestInformation(String leaveType, String from, String to, String approver, String requesterComment) {
    selectLeaveType(leaveType);
    type(findElementById("leave-request:from_input"), from);
    closePanelDatePicker(findElementById("leave-request:from_panel"));
    type(findElementById("leave-request:to_input"), to);
    closePanelDatePicker(findElementById("leave-request:to_panel"));
    enterKeys(findElementById("leave-request:requester-comment"), requesterComment);
    selectApprover(approver);
  }
  
  private void closePanelDatePicker(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].style.display = 'none'", element);
  }

  public void enterApproverComment(String approverComment) {
    enterKeys(findElementById("leave-request:approver-comment"), approverComment);
  }
  
  public HomePage clickApproveBtn() {
    click(By.id("leave-request:approved-btn"));
    switchToDefaultContent();
    return new HomePage();
  }
  
  public HomePage clickRejectBtn() {
    click(By.id("leave-request:rejected-btn"));
    switchToDefaultContent();
    return new HomePage();
  }
  
  public UserExamplesEndPage finishLeaveRequest() {
    click(By.id("leave-request:finish-btn"));
    switchToDefaultContent();
    return new UserExamplesEndPage();
  }
  
  private void selectLeaveType(String leaveType) {
    clickByCssSelector("#leave-request\\:leave-type_label");
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
