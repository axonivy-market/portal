package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.test.userexample.page.UserExamplesEndPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class LeaveRequestPage extends TaskTemplateIFramePage {

  @Override
  protected String getLoadedLocator() {
    return "div[id='content-container']";
  }

  public void waitForIFrameContentVisible() {
    $("div[id='content']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void fulfillAndSendMaternityLeaveRequest() {
    findElementById("leave-request:from_input").sendKeys("Jun 23, 2023 10:37");
    findElementById("leave-request:to_input").sendKeys("Jun 24, 2023 10:37");
    findElementById("leave-request:fullname").sendKeys("John Doe");
    findElementById("leave-request:substitute").sendKeys("John Wick");
    clickByJavaScript($(By.id("leave-request:button-submit")));
  }

  public void clickSubmitLeaveRequest() {
    waitForElementClickableThenClick(By.id("leave-request:button-submit"));
  }

  public String clickSubmitAndGetValidationMsg() {
    int numberOfErrors = $$("span.ui-messages-error-summary").size();
    clickSubmitLeaveRequest();
    $$("span.ui-messages-error-summary").shouldBe(CollectionCondition.sizeNotEqual(numberOfErrors), DEFAULT_TIMEOUT);
    return getValidationMsg();
  }

  public String getValidationMsg() {
    List<SelenideElement> messages = $$("span.ui-messages-error-summary");
    return StringUtils.join(messages.stream().map(WebElement::getText).collect(Collectors.toList()), ",");
  }

  public void enterLeaveRequestInformation(String leaveType, String from, String to, String approver,
      String requesterComment) {
    selectLeaveType(leaveType);
    findElementById("leave-request:from_input").sendKeys(from);
    closePanelDatePicker(findElementById("leave-request:from_panel"));
    findElementById("leave-request:to_input").sendKeys(to);
    closePanelDatePicker(findElementById("leave-request:to_panel"));
    findElementById("leave-request:requester-comment").sendKeys(requesterComment);
    selectApprover(approver);
  }

  private void selectLeaveType(String leaveType) {
    waitForElementClickableThenClick("#leave-request\\:leave-type_label");
    String leaveTypeSelector = "li[data-label='" + leaveType + "']";
    waitForElementDisplayed(By.cssSelector(leaveTypeSelector), true);
    waitForElementClickableThenClick(leaveTypeSelector);
  }

  private void closePanelDatePicker(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].style.display = 'none'", element);
  }

  private void selectApprover(String approver) {
    findElementById("leave-request:approver_label").click();
    String approverSelector = "li[data-label='" + approver + "']";
    waitForElementDisplayed(By.cssSelector(approverSelector), true);
    waitForElementClickableThenClick(approverSelector);
  }

  public void enterApproverComment(String approverComment) {
    findElementById("leave-request:approver-comment").sendKeys(approverComment);
  }

  public TaskWidgetPage clickApproveBtn() {
    waitForElementClickableThenClick(By.id("leave-request:approved-btn"));
    switchToDefaultContent();
    return new TaskWidgetPage();
  }

  public UserExamplesEndPage finishLeaveRequest() {
    waitForElementClickableThenClick(By.id("leave-request:finish-btn"));
    switchToDefaultContent();
    return new UserExamplesEndPage();
  }

  public TaskWidgetPage clickRejectBtn() {
    waitForElementClickableThenClick(By.id("leave-request:rejected-btn"));
    switchToDefaultContent();
    return new TaskWidgetPage();
  }
}
