package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.NavigationHelper;
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
    $("button[id='leave-request:button-submit']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    clickByJavaScript($("button[id='leave-request:button-submit']"));
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
    findElementById("leave-request:from_input").sendKeys(from);
    closePanelDatePicker(findElementById("leave-request:from_panel"));
    findElementById("leave-request:to_input").sendKeys(to);
    closePanelDatePicker(findElementById("leave-request:to_panel"));
    findElementById("leave-request:requester-comment").sendKeys(requesterComment);
    selectApprover(approver);
    selectLeaveType(leaveType);
  }

  private void selectLeaveType(String leaveType) {
    $("div[id='leave-request:leave-type']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("div[id='leave-request:leave-type']").$("div.ui-selectonemenu-trigger")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("li[data-label='" + leaveType + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void enterMaternityLeaveRequestInformation(String from, String to, String approver,
      String requesterComment) {
    findElementById("leave-request:from_input").sendKeys(from);
    closePanelDatePicker(findElementById("leave-request:from_panel"));
    findElementById("leave-request:to_input").sendKeys(to);
    closePanelDatePicker(findElementById("leave-request:to_panel"));
    findElementById("leave-request:requester-comment").sendKeys(requesterComment);
    selectApprover(approver);
    selectMaternityLeaveType();
  }

  private void selectMaternityLeaveType() {
    $("div[id='leave-request:leave-type']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("div[id='leave-request:leave-type']").$("div.ui-selectonemenu-trigger")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("li[id='leave-request:leave-type_2']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    
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
    $("button[id='leave-request:approved-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    switchToDefaultContent();
    return NavigationHelper.navigateToTaskList();
  }

  public void finishLeaveRequest() {
    $("button[id='leave-request:finish-btn']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    clickByJavaScript($("button[id='leave-request:finish-btn']"));
    switchToDefaultContent();
    $("[id$='form:go-to-case-detail']").shouldBe(Condition.visible);
    clickByJavaScript($("[id$='form:go-to-case-detail']"));
  }

  public TaskWidgetPage clickRejectBtn() {
    $("button[id='leave-request:rejected-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    switchToDefaultContent();
    return NavigationHelper.navigateToTaskList();
  }
  
  public void waitForLeaveRequestFormVisible() {
    $("form[id='leave-request']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
}
