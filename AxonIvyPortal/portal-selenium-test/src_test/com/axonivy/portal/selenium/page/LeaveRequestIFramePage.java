package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

public class LeaveRequestIFramePage extends TaskTemplateIFramePage {

  @Override
  protected String getLoadedLocator() {
    return "#content";
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
    clickSubmitLeaveRequest();
    $$("span.ui-messages-error-summary").shouldBe(CollectionCondition.sizeNotEqual(numberOfErrors), DEFAULT_TIMEOUT);
    return getValidationMsg();
  }

  public String getValidationMsg() {
    ElementsCollection messages = $$("span.ui-messages-error-summary");
    return StringUtils.join(messages.asFixedIterable().stream().map(WebElement::getText).collect(Collectors.toList()), ",");
  }

  public void enterLeaveRequestInformation(String leaveType, String from, String to, String approver,
      String requesterComment) {
    selectLeaveType(leaveType);
    findElementById("leave-request:from_input").sendKeys(from);
    closePanelDatePicker(findElementById("leave-request:from_input"));
    findElementById("leave-request:to_input").sendKeys(to);
    closePanelDatePicker(findElementById("leave-request:to_input"));
    findElementById("leave-request:requester-comment").sendKeys(requesterComment);
    selectApprover(approver);
  }

  private void selectLeaveType(String leaveType) {
    waitForElementClickableThenClick("#leave-request\\:leave-type_label");
    WaitHelper.waitPageNoAnimation();
    String leaveTypeSelector = "li[data-label='" + leaveType + "']";
    waitForElementDisplayed(By.cssSelector(leaveTypeSelector), true);
    waitForElementClickableThenClick(leaveTypeSelector);
  }

  private void closePanelDatePicker(WebElement inputElement) {
    WaitHelper.waitPageNoAnimation();
    inputElement.sendKeys(Keys.ESCAPE);
    By panel = By.id(inputElement.getDomAttribute("id").replace("_input", "_panel"));
    if ($(panel).exists()) {
      waitForElementDisplayed(panel, false);
    }
  }

  private void selectApprover(String approver) {
    findElementById("leave-request:approver_label").click();
    WaitHelper.waitPageNoAnimation();
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

  public TaskWidgetPage clickRejectBtn() {
    waitForElementClickableThenClick(By.id("leave-request:rejected-btn"));
    switchToDefaultContent();
    return new TaskWidgetPage();
  }
}
