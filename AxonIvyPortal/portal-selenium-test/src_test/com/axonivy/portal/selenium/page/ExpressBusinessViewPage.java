package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.SelenideElement;

public class ExpressBusinessViewPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='express-view-form']";
  }

  public SelenideElement getEmptyFinishedTask() {
    return $("[id$='express-view-form:empty-finished-tasks-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String getEmptyFinishedTaskMessage() {
    return getEmptyFinishedTask().$(".no-item-header").shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public void clickOnCloseButton() {
    WaitHelper.waitForNavigation(
        () -> $("[id$='express-view-form:cancel-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
  }

  public SelenideElement getLegendFisnishedTaskFieldset(int index) {
    return $(
        String.format("span[id$=':finished-tasks-component:approval-result:%d:finished-task-fieldset-legend']", index))
            .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String getTextOfLegendFinishedTask(int index) {
    return getLegendFisnishedTaskFieldset(index).getText();
  }

  public String getTextOfLegendApprovalResult(int index) {
    return $(String.format("span[id$=':approval-result-container:0:approval-result-fieldset-legend']", index))
        .shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public void clickOnLegendOfFieldset(int index) {
    getLegendFisnishedTaskFieldset(index).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public String getApprovalResultsText() {
    return $(".approval-result-content").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("tbody[id$=':approval-result-table_data'] td").asFixedIterable().stream().map(WebElement::getText)
        .collect(Collectors.joining(","));
  }

  public Integer getIndexOfCurrentProcessChain() {
    SelenideElement processChainContainer =
        $("[id$='process-chain-component:process-chain-component-id']").shouldBe(appear, DEFAULT_TIMEOUT);

    for (var step : processChainContainer.$$("div[id^='process-chain-component:step-info-']")) {
      if (step.getAttribute(CLASS_PROPERTY).contains("current")) {
        String stepId = step.getAttribute("id");
        return Integer.valueOf(stepId.replace("process-chain-component:step-info-", "").trim()).intValue();
      }
    }
    return 0;
  }

  public void clickTaskTitle() {
    $("[id='task-template-title']").should(appear, DEFAULT_TIMEOUT).click();
  }
  
//  public HomePage clickOnCloseButton() {
//    $("[id$='express-view-form:cancel-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
//    return new HomePage();
//
}
