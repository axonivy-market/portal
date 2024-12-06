package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

public class LeaveRequestPage extends TaskTemplateIFramePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='content-container']";
  }

  @Override
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
    try {
      clickSubmitAndWaitValidationSummary(numberOfErrors);
    } catch (AssertionError e) {
      if (!$("[id='leave-request:leave-request-validation-msg']").isDisplayed()) {
        clickSubmitAndWaitValidationSummary(numberOfErrors);
      }
    }
    return getValidationMsg();
  }

  private void clickSubmitAndWaitValidationSummary(int numberOfErrors) {
    clickSubmitLeaveRequest();
    waitForPageLoad();
    $$("span.ui-messages-error-summary").shouldBe(CollectionCondition.sizeNotEqual(numberOfErrors), DEFAULT_TIMEOUT);
  }

  public String getValidationMsg() {
    ElementsCollection messages = $$("span.ui-messages-error-summary");
    return StringUtils.join(messages.asFixedIterable().stream().map(WebElement::getText).collect(Collectors.toList()), ",");
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
    boolean isPanelDisplayed = false;
    while(!isPanelDisplayed) {
      waitForElementClickableThenClick("[id='leave-request:leave-type']");
      isPanelDisplayed = $("[id='leave-request:leave-type_panel']").isDisplayed();
    }

    String leaveTypeSelector = "li[data-label='" + leaveType + "']";
    waitForElementDisplayed(By.cssSelector(leaveTypeSelector), true);
    waitForElementClickableThenClick(leaveTypeSelector);
    waitForElementDisplayed($("[id='leave-request:leave-type_panel']"), false);
  }

  private void closePanelDatePicker(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].style.display = 'none'", element);
  }

  private void selectApprover(String approver) {
    boolean isPanelDisplayed = false;
    while(!isPanelDisplayed) {
      waitForElementClickableThenClick("[id='leave-request:approver']");
      isPanelDisplayed = $("[id='leave-request:approver_panel']").isDisplayed();
    }

    String approverSelector = "li[data-label='" + approver + "']";
    waitForElementDisplayed(By.cssSelector(approverSelector), true);
    waitForElementClickableThenClick(approverSelector);
    waitForElementDisplayed($("[id='leave-request:approver_panel']"), false);
  }

  public void enterApproverComment(String approverComment) {
    findElementById("leave-request:approver-comment").sendKeys(approverComment);
  }

  public NewDashboardPage clickApproveBtn() {
    waitForElementClickableThenClick(By.id("leave-request:approved-btn"));
    waitPageDisappear();
    switchBackToParent();
    return new NewDashboardPage();
  }

  public TaskTemplatePage finishLeaveRequest() {
    waitForElementClickableThenClick(By.id("leave-request:finish-btn"));
    waitPageDisappear();
    switchBackToParent();
    return new TaskTemplatePage();
  }

  public NewDashboardPage clickRejectBtn() {
    waitForElementClickableThenClick(By.id("leave-request:rejected-btn"));
    waitPageDisappear();
    switchBackToParent();
    return new NewDashboardPage();
  }
}
